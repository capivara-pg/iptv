package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.c1;
import androidx.fragment.R$id;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class v {
    private final n a;
    private final x b;
    private final Fragment c;
    private boolean d = false;
    private int e = -1;

    class a implements View.OnAttachStateChangeListener {
        final /* synthetic */ View a;

        a(View view) {
            this.a = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            this.a.removeOnAttachStateChangeListener(this);
            c1.h0(this.a);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[androidx.lifecycle.d.c.values().length];
            a = iArr;
            try {
                iArr[androidx.lifecycle.d.c.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[androidx.lifecycle.d.c.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[androidx.lifecycle.d.c.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[androidx.lifecycle.d.c.INITIALIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    v(n nVar, x xVar, Fragment fragment) {
        this.a = nVar;
        this.b = xVar;
        this.c = fragment;
    }

    private boolean l(View view) {
        if (view == this.c.mView) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.c.mView) {
                return true;
            }
        }
        return false;
    }

    private Bundle q() {
        Bundle bundle = new Bundle();
        this.c.performSaveInstanceState(bundle);
        this.a.j(this.c, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.c.mView != null) {
            t();
        }
        if (this.c.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.c.mSavedViewState);
        }
        if (this.c.mSavedViewRegistryState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle("android:view_registry_state", this.c.mSavedViewRegistryState);
        }
        if (!this.c.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.c.mUserVisibleHint);
        }
        return bundle;
    }

    void a() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto ACTIVITY_CREATED: ");
            sb.append(this.c);
        }
        Fragment fragment = this.c;
        fragment.performActivityCreated(fragment.mSavedFragmentState);
        n nVar = this.a;
        Fragment fragment2 = this.c;
        nVar.a(fragment2, fragment2.mSavedFragmentState, false);
    }

    void b() {
        int iJ = this.b.j(this.c);
        Fragment fragment = this.c;
        fragment.mContainer.addView(fragment.mView, iJ);
    }

    void c() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto ATTACHED: ");
            sb.append(this.c);
        }
        Fragment fragment = this.c;
        Fragment fragment2 = fragment.mTarget;
        v vVarM = null;
        if (fragment2 != null) {
            v vVarM2 = this.b.m(fragment2.mWho);
            if (vVarM2 == null) {
                throw new IllegalStateException("Fragment " + this.c + " declared target fragment " + this.c.mTarget + " that does not belong to this FragmentManager!");
            }
            Fragment fragment3 = this.c;
            fragment3.mTargetWho = fragment3.mTarget.mWho;
            fragment3.mTarget = null;
            vVarM = vVarM2;
        } else {
            String str = fragment.mTargetWho;
            if (str != null && (vVarM = this.b.m(str)) == null) {
                throw new IllegalStateException("Fragment " + this.c + " declared target fragment " + this.c.mTargetWho + " that does not belong to this FragmentManager!");
            }
        }
        if (vVarM != null && (o.P || vVarM.k().mState < 1)) {
            vVarM.m();
        }
        Fragment fragment4 = this.c;
        fragment4.mHost = fragment4.mFragmentManager.t0();
        Fragment fragment5 = this.c;
        fragment5.mParentFragment = fragment5.mFragmentManager.w0();
        this.a.g(this.c, false);
        this.c.performAttach();
        this.a.b(this.c, false);
    }

    int d() {
        Fragment fragment;
        ViewGroup viewGroup;
        Fragment fragment2 = this.c;
        if (fragment2.mFragmentManager == null) {
            return fragment2.mState;
        }
        int iMin = this.e;
        int i = b.a[fragment2.mMaxState.ordinal()];
        if (i != 1) {
            if (i == 2) {
                iMin = Math.min(iMin, 5);
            } else if (i != 3) {
                iMin = i != 4 ? Math.min(iMin, -1) : Math.min(iMin, 0);
            } else {
                iMin = Math.min(iMin, 1);
            }
        }
        Fragment fragment3 = this.c;
        if (fragment3.mFromLayout) {
            if (fragment3.mInLayout) {
                iMin = Math.max(this.e, 2);
                View view = this.c.mView;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.e < 4 ? Math.min(iMin, fragment3.mState) : Math.min(iMin, 1);
            }
        }
        if (!this.c.mAdded) {
            iMin = Math.min(iMin, 1);
        }
        j0.e.b bVarL = (!o.P || (viewGroup = (fragment = this.c).mContainer) == null) ? null : j0.n(viewGroup, fragment.getParentFragmentManager()).l(this);
        if (bVarL == j0.e.b.ADDING) {
            iMin = Math.min(iMin, 6);
        } else if (bVarL == j0.e.b.REMOVING) {
            iMin = Math.max(iMin, 3);
        } else {
            Fragment fragment4 = this.c;
            if (fragment4.mRemoving) {
                iMin = fragment4.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, -1);
            }
        }
        Fragment fragment5 = this.c;
        if (fragment5.mDeferStart && fragment5.mState < 5) {
            iMin = Math.min(iMin, 4);
        }
        if (o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("computeExpectedState() of ");
            sb.append(iMin);
            sb.append(" for ");
            sb.append(this.c);
        }
        return iMin;
    }

    void e() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto CREATED: ");
            sb.append(this.c);
        }
        Fragment fragment = this.c;
        if (fragment.mIsCreated) {
            fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
            this.c.mState = 1;
            return;
        }
        this.a.h(fragment, fragment.mSavedFragmentState, false);
        Fragment fragment2 = this.c;
        fragment2.performCreate(fragment2.mSavedFragmentState);
        n nVar = this.a;
        Fragment fragment3 = this.c;
        nVar.c(fragment3, fragment3.mSavedFragmentState, false);
    }

    void f() {
        String resourceName;
        if (this.c.mFromLayout) {
            return;
        }
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto CREATE_VIEW: ");
            sb.append(this.c);
        }
        Fragment fragment = this.c;
        LayoutInflater layoutInflaterPerformGetLayoutInflater = fragment.performGetLayoutInflater(fragment.mSavedFragmentState);
        Fragment fragment2 = this.c;
        ViewGroup viewGroup = fragment2.mContainer;
        if (viewGroup == null) {
            int i = fragment2.mContainerId;
            if (i == 0) {
                viewGroup = null;
            } else {
                if (i == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.c + " for a container view with no id");
                }
                viewGroup = (ViewGroup) fragment2.mFragmentManager.n0().c(this.c.mContainerId);
                if (viewGroup == null) {
                    Fragment fragment3 = this.c;
                    if (!fragment3.mRestored) {
                        try {
                            resourceName = fragment3.getResources().getResourceName(this.c.mContainerId);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.c.mContainerId) + " (" + resourceName + ") for fragment " + this.c);
                    }
                }
            }
        }
        Fragment fragment4 = this.c;
        fragment4.mContainer = viewGroup;
        fragment4.performCreateView(layoutInflaterPerformGetLayoutInflater, viewGroup, fragment4.mSavedFragmentState);
        View view = this.c.mView;
        if (view != null) {
            boolean z = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment5 = this.c;
            fragment5.mView.setTag(R$id.fragment_container_view_tag, fragment5);
            if (viewGroup != null) {
                b();
            }
            Fragment fragment6 = this.c;
            if (fragment6.mHidden) {
                fragment6.mView.setVisibility(8);
            }
            if (c1.P(this.c.mView)) {
                c1.h0(this.c.mView);
            } else {
                View view2 = this.c.mView;
                view2.addOnAttachStateChangeListener(new a(view2));
            }
            this.c.performViewCreated();
            n nVar = this.a;
            Fragment fragment7 = this.c;
            nVar.m(fragment7, fragment7.mView, fragment7.mSavedFragmentState, false);
            int visibility = this.c.mView.getVisibility();
            float alpha = this.c.mView.getAlpha();
            if (o.P) {
                this.c.setPostOnViewCreatedAlpha(alpha);
                Fragment fragment8 = this.c;
                if (fragment8.mContainer != null && visibility == 0) {
                    View viewFindFocus = fragment8.mView.findFocus();
                    if (viewFindFocus != null) {
                        this.c.setFocusedView(viewFindFocus);
                        if (o.F0(2)) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("requestFocus: Saved focused view ");
                            sb2.append(viewFindFocus);
                            sb2.append(" for Fragment ");
                            sb2.append(this.c);
                        }
                    }
                    this.c.mView.setAlpha(0.0f);
                }
            } else {
                Fragment fragment9 = this.c;
                if (visibility == 0 && fragment9.mContainer != null) {
                    z = true;
                }
                fragment9.mIsNewlyAdded = z;
            }
        }
        this.c.mState = 2;
    }

    void g() {
        Fragment fragmentF;
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom CREATED: ");
            sb.append(this.c);
        }
        Fragment fragment = this.c;
        boolean zIsChangingConfigurations = true;
        boolean z = fragment.mRemoving && !fragment.isInBackStack();
        if (!(z || this.b.o().p(this.c))) {
            String str = this.c.mTargetWho;
            if (str != null && (fragmentF = this.b.f(str)) != null && fragmentF.mRetainInstance) {
                this.c.mTarget = fragmentF;
            }
            this.c.mState = 0;
            return;
        }
        l lVar = this.c.mHost;
        if (lVar instanceof androidx.lifecycle.y) {
            zIsChangingConfigurations = this.b.o().m();
        } else if (lVar.f() instanceof Activity) {
            zIsChangingConfigurations = true ^ ((Activity) lVar.f()).isChangingConfigurations();
        }
        if (z || zIsChangingConfigurations) {
            this.b.o().g(this.c);
        }
        this.c.performDestroy();
        this.a.d(this.c, false);
        for (v vVar : this.b.k()) {
            if (vVar != null) {
                Fragment fragmentK = vVar.k();
                if (this.c.mWho.equals(fragmentK.mTargetWho)) {
                    fragmentK.mTarget = this.c;
                    fragmentK.mTargetWho = null;
                }
            }
        }
        Fragment fragment2 = this.c;
        String str2 = fragment2.mTargetWho;
        if (str2 != null) {
            fragment2.mTarget = this.b.f(str2);
        }
        this.b.q(this);
    }

    void h() {
        View view;
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom CREATE_VIEW: ");
            sb.append(this.c);
        }
        Fragment fragment = this.c;
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && (view = fragment.mView) != null) {
            viewGroup.removeView(view);
        }
        this.c.performDestroyView();
        this.a.n(this.c, false);
        Fragment fragment2 = this.c;
        fragment2.mContainer = null;
        fragment2.mView = null;
        fragment2.mViewLifecycleOwner = null;
        fragment2.mViewLifecycleOwnerLiveData.n(null);
        this.c.mInLayout = false;
    }

    void i() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom ATTACHED: ");
            sb.append(this.c);
        }
        this.c.performDetach();
        boolean z = false;
        this.a.e(this.c, false);
        Fragment fragment = this.c;
        fragment.mState = -1;
        fragment.mHost = null;
        fragment.mParentFragment = null;
        fragment.mFragmentManager = null;
        if (fragment.mRemoving && !fragment.isInBackStack()) {
            z = true;
        }
        if (z || this.b.o().p(this.c)) {
            if (o.F0(3)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("initState called for fragment: ");
                sb2.append(this.c);
            }
            this.c.initState();
        }
    }

    void j() {
        Fragment fragment = this.c;
        if (fragment.mFromLayout && fragment.mInLayout && !fragment.mPerformedCreateView) {
            if (o.F0(3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("moveto CREATE_VIEW: ");
                sb.append(this.c);
            }
            Fragment fragment2 = this.c;
            fragment2.performCreateView(fragment2.performGetLayoutInflater(fragment2.mSavedFragmentState), null, this.c.mSavedFragmentState);
            View view = this.c.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.c;
                fragment3.mView.setTag(R$id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.c;
                if (fragment4.mHidden) {
                    fragment4.mView.setVisibility(8);
                }
                this.c.performViewCreated();
                n nVar = this.a;
                Fragment fragment5 = this.c;
                nVar.m(fragment5, fragment5.mView, fragment5.mSavedFragmentState, false);
                this.c.mState = 2;
            }
        }
    }

    Fragment k() {
        return this.c;
    }

    void m() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        if (this.d) {
            if (o.F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Ignoring re-entrant call to moveToExpectedState() for ");
                sb.append(k());
                return;
            }
            return;
        }
        try {
            this.d = true;
            while (true) {
                int iD = d();
                Fragment fragment = this.c;
                int i = fragment.mState;
                if (iD == i) {
                    if (o.P && fragment.mHiddenChanged) {
                        if (fragment.mView != null && (viewGroup = fragment.mContainer) != null) {
                            j0 j0VarN = j0.n(viewGroup, fragment.getParentFragmentManager());
                            if (this.c.mHidden) {
                                j0VarN.c(this);
                            } else {
                                j0VarN.e(this);
                            }
                        }
                        Fragment fragment2 = this.c;
                        o oVar = fragment2.mFragmentManager;
                        if (oVar != null) {
                            oVar.D0(fragment2);
                        }
                        Fragment fragment3 = this.c;
                        fragment3.mHiddenChanged = false;
                        fragment3.onHiddenChanged(fragment3.mHidden);
                    }
                    return;
                }
                if (iD <= i) {
                    switch (i - 1) {
                        case -1:
                            i();
                            break;
                        case 0:
                            g();
                            break;
                        case 1:
                            h();
                            this.c.mState = 1;
                            break;
                        case 2:
                            fragment.mInLayout = false;
                            fragment.mState = 2;
                            break;
                        case 3:
                            if (o.F0(3)) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("movefrom ACTIVITY_CREATED: ");
                                sb2.append(this.c);
                            }
                            Fragment fragment4 = this.c;
                            if (fragment4.mView != null && fragment4.mSavedViewState == null) {
                                t();
                            }
                            Fragment fragment5 = this.c;
                            if (fragment5.mView != null && (viewGroup3 = fragment5.mContainer) != null) {
                                j0.n(viewGroup3, fragment5.getParentFragmentManager()).d(this);
                            }
                            this.c.mState = 3;
                            break;
                        case 4:
                            w();
                            break;
                        case 5:
                            fragment.mState = 5;
                            break;
                        case 6:
                            n();
                            break;
                    }
                } else {
                    switch (i + 1) {
                        case 0:
                            c();
                            break;
                        case 1:
                            e();
                            break;
                        case 2:
                            j();
                            f();
                            break;
                        case 3:
                            a();
                            break;
                        case 4:
                            if (fragment.mView != null && (viewGroup2 = fragment.mContainer) != null) {
                                j0.n(viewGroup2, fragment.getParentFragmentManager()).b(j0.e.c.b(this.c.mView.getVisibility()), this);
                            }
                            this.c.mState = 4;
                            break;
                        case 5:
                            v();
                            break;
                        case 6:
                            fragment.mState = 6;
                            break;
                        case 7:
                            p();
                            break;
                    }
                }
            }
        } finally {
            this.d = false;
        }
    }

    void n() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom RESUMED: ");
            sb.append(this.c);
        }
        this.c.performPause();
        this.a.f(this.c, false);
    }

    void o(ClassLoader classLoader) {
        Bundle bundle = this.c.mSavedFragmentState;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.c;
        fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.c;
        fragment2.mSavedViewRegistryState = fragment2.mSavedFragmentState.getBundle("android:view_registry_state");
        Fragment fragment3 = this.c;
        fragment3.mTargetWho = fragment3.mSavedFragmentState.getString("android:target_state");
        Fragment fragment4 = this.c;
        if (fragment4.mTargetWho != null) {
            fragment4.mTargetRequestCode = fragment4.mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        Fragment fragment5 = this.c;
        Boolean bool = fragment5.mSavedUserVisibleHint;
        if (bool != null) {
            fragment5.mUserVisibleHint = bool.booleanValue();
            this.c.mSavedUserVisibleHint = null;
        } else {
            fragment5.mUserVisibleHint = fragment5.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment6 = this.c;
        if (fragment6.mUserVisibleHint) {
            return;
        }
        fragment6.mDeferStart = true;
    }

    void p() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto RESUMED: ");
            sb.append(this.c);
        }
        View focusedView = this.c.getFocusedView();
        if (focusedView != null && l(focusedView)) {
            boolean zRequestFocus = focusedView.requestFocus();
            if (o.F0(2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("requestFocus: Restoring focused view ");
                sb2.append(focusedView);
                sb2.append(" ");
                sb2.append(zRequestFocus ? "succeeded" : "failed");
                sb2.append(" on Fragment ");
                sb2.append(this.c);
                sb2.append(" resulting in focused view ");
                sb2.append(this.c.mView.findFocus());
            }
        }
        this.c.setFocusedView(null);
        this.c.performResume();
        this.a.i(this.c, false);
        Fragment fragment = this.c;
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
    }

    Fragment.m r() {
        Bundle bundleQ;
        if (this.c.mState <= -1 || (bundleQ = q()) == null) {
            return null;
        }
        return new Fragment.m(bundleQ);
    }

    u s() {
        u uVar = new u(this.c);
        Fragment fragment = this.c;
        if (fragment.mState <= -1 || uVar.m != null) {
            uVar.m = fragment.mSavedFragmentState;
        } else {
            Bundle bundleQ = q();
            uVar.m = bundleQ;
            if (this.c.mTargetWho != null) {
                if (bundleQ == null) {
                    uVar.m = new Bundle();
                }
                uVar.m.putString("android:target_state", this.c.mTargetWho);
                int i = this.c.mTargetRequestCode;
                if (i != 0) {
                    uVar.m.putInt("android:target_req_state", i);
                }
            }
        }
        return uVar;
    }

    void t() {
        if (this.c.mView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.c.mView.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.c.mSavedViewState = sparseArray;
        }
        Bundle bundle = new Bundle();
        this.c.mViewLifecycleOwner.e(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        this.c.mSavedViewRegistryState = bundle;
    }

    void u(int i) {
        this.e = i;
    }

    void v() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto STARTED: ");
            sb.append(this.c);
        }
        this.c.performStart();
        this.a.k(this.c, false);
    }

    void w() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom STARTED: ");
            sb.append(this.c);
        }
        this.c.performStop();
        this.a.l(this.c, false);
    }

    v(n nVar, x xVar, ClassLoader classLoader, k kVar, u uVar) {
        this.a = nVar;
        this.b = xVar;
        Fragment fragmentA = kVar.a(classLoader, uVar.a);
        this.c = fragmentA;
        Bundle bundle = uVar.j;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        fragmentA.setArguments(uVar.j);
        fragmentA.mWho = uVar.b;
        fragmentA.mFromLayout = uVar.c;
        fragmentA.mRestored = true;
        fragmentA.mFragmentId = uVar.d;
        fragmentA.mContainerId = uVar.e;
        fragmentA.mTag = uVar.f;
        fragmentA.mRetainInstance = uVar.g;
        fragmentA.mRemoving = uVar.h;
        fragmentA.mDetached = uVar.i;
        fragmentA.mHidden = uVar.k;
        fragmentA.mMaxState = androidx.lifecycle.d.c.values()[uVar.l];
        Bundle bundle2 = uVar.m;
        if (bundle2 != null) {
            fragmentA.mSavedFragmentState = bundle2;
        } else {
            fragmentA.mSavedFragmentState = new Bundle();
        }
        if (o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Instantiated fragment ");
            sb.append(fragmentA);
        }
    }

    v(n nVar, x xVar, Fragment fragment, u uVar) {
        this.a = nVar;
        this.b = xVar;
        this.c = fragment;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        Fragment fragment2 = fragment.mTarget;
        fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
        fragment.mTarget = null;
        Bundle bundle = uVar.m;
        if (bundle != null) {
            fragment.mSavedFragmentState = bundle;
        } else {
            fragment.mSavedFragmentState = new Bundle();
        }
    }
}
