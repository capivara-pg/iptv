package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.result.ActivityResultRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.g0;
import androidx.lifecycle.j0;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import androidx.lifecycle.m0;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import s.o0;

/* JADX INFO: loaded from: classes.dex */
public abstract class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.m, k0, androidx.lifecycle.e, s0.c {
    static final int ACTIVITY_CREATED = 4;
    static final int ATTACHED = 0;
    static final int AWAITING_ENTER_EFFECTS = 6;
    static final int AWAITING_EXIT_EFFECTS = 3;
    static final int CREATED = 1;
    static final int INITIALIZING = -1;
    static final int RESUMED = 7;
    static final int STARTED = 5;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    static final int VIEW_CREATED = 2;
    boolean mAdded;
    i mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    private boolean mCalled;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    g0.b mDefaultFactory;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    n mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    androidx.fragment.app.k mHost;
    boolean mInLayout;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    androidx.lifecycle.o mLifecycleRegistry;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    s0.b mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    Bundle mSavedViewRegistryState;
    SparseArray<Parcelable> mSavedViewState;
    String mTag;
    Fragment mTarget;
    int mTargetRequestCode;
    View mView;
    b0 mViewLifecycleOwner;
    int mState = -1;
    String mWho = UUID.randomUUID().toString();
    String mTargetWho = null;
    private Boolean mIsPrimaryNavigationFragment = null;
    n mChildFragmentManager = new o();
    boolean mMenuVisible = true;
    boolean mUserVisibleHint = true;
    Runnable mPostponedDurationRunnable = new a();
    androidx.lifecycle.f.b mMaxState = androidx.lifecycle.f.b.RESUMED;
    androidx.lifecycle.s mViewLifecycleOwnerLiveData = new androidx.lifecycle.s();
    private final AtomicInteger mNextLocalRequestCode = new AtomicInteger();
    private final ArrayList<k> mOnPreAttachedListeners = new ArrayList<>();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.startPostponedEnterTransition();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.callStartTransitionListener(false);
        }
    }

    class c implements Runnable {
        final /* synthetic */ d0 a;

        c(d0 d0Var) {
            this.a = d0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.g();
        }
    }

    class d extends androidx.fragment.app.g {
        d() {
        }

        @Override // androidx.fragment.app.g
        public View c(int i) {
            View view = Fragment.this.mView;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException("Fragment " + Fragment.this + " does not have a view");
        }

        @Override // androidx.fragment.app.g
        public boolean d() {
            return Fragment.this.mView != null;
        }
    }

    class e implements k.a {
        e() {
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ActivityResultRegistry apply(Void r3) {
            Fragment fragment = Fragment.this;
            Object obj = fragment.mHost;
            return obj instanceof androidx.activity.result.d ? ((androidx.activity.result.d) obj).getActivityResultRegistry() : fragment.requireActivity().getActivityResultRegistry();
        }
    }

    class f implements k.a {
        final /* synthetic */ ActivityResultRegistry a;

        f(ActivityResultRegistry activityResultRegistry) {
            this.a = activityResultRegistry;
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ActivityResultRegistry apply(Void r1) {
            return this.a;
        }
    }

    class g extends k {
        final /* synthetic */ k.a a;
        final /* synthetic */ AtomicReference b;
        final /* synthetic */ e.a c;
        final /* synthetic */ androidx.activity.result.b d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(k.a aVar, AtomicReference atomicReference, e.a aVar2, androidx.activity.result.b bVar) {
            super(null);
            this.a = aVar;
            this.b = atomicReference;
            this.c = aVar2;
            this.d = bVar;
        }

        @Override // androidx.fragment.app.Fragment.k
        void a() {
            String strGenerateActivityResultKey = Fragment.this.generateActivityResultKey();
            this.b.set(((ActivityResultRegistry) this.a.apply((Object) null)).i(strGenerateActivityResultKey, Fragment.this, this.c, this.d));
        }
    }

    class h extends androidx.activity.result.c {
        final /* synthetic */ AtomicReference a;
        final /* synthetic */ e.a b;

        h(AtomicReference atomicReference, e.a aVar) {
            this.a = atomicReference;
            this.b = aVar;
        }

        @Override // androidx.activity.result.c
        public e.a a() {
            return this.b;
        }

        @Override // androidx.activity.result.c
        public void c(Object obj, s.e eVar) {
            androidx.activity.result.c cVar = (androidx.activity.result.c) this.a.get();
            if (cVar == null) {
                throw new IllegalStateException("Operation cannot be started before fragment is in created state");
            }
            cVar.c(obj, eVar);
        }

        @Override // androidx.activity.result.c
        public void d() {
            androidx.activity.result.c cVar = (androidx.activity.result.c) this.a.getAndSet(null);
            if (cVar != null) {
                cVar.d();
            }
        }
    }

    static class i {
        View a;
        Animator b;
        boolean c;
        int d;
        int e;
        int f;
        int g;
        int h;
        ArrayList i;
        ArrayList j;
        Object k = null;
        Object l;
        Object m;
        Object n;
        Object o;
        Object p;
        Boolean q;
        Boolean r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        float f25s;
        View t;
        boolean u;
        l v;
        boolean w;

        i() {
            Object obj = Fragment.USE_DEFAULT_TRANSITION;
            this.l = obj;
            this.m = null;
            this.n = obj;
            this.o = null;
            this.p = obj;
            this.f25s = 1.0f;
            this.t = null;
        }
    }

    public static class j extends RuntimeException {
        public j(String str, Exception exc) {
            super(str, exc);
        }
    }

    private static abstract class k {
        private k() {
        }

        /* synthetic */ k(a aVar) {
            this();
        }

        abstract void a();
    }

    interface l {
        void a();

        void b();
    }

    public Fragment() {
        c4();
    }

    private i a4() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new i();
        }
        return this.mAnimationInfo;
    }

    private int b4() {
        androidx.lifecycle.f.b bVar = this.mMaxState;
        return (bVar == androidx.lifecycle.f.b.INITIALIZED || this.mParentFragment == null) ? bVar.ordinal() : Math.min(bVar.ordinal(), this.mParentFragment.b4());
    }

    private void c4() {
        this.mLifecycleRegistry = new androidx.lifecycle.o(this);
        this.mSavedStateRegistryController = s0.b.a(this);
        this.mDefaultFactory = null;
    }

    private androidx.activity.result.c d4(e.a aVar, k.a aVar2, androidx.activity.result.b bVar) {
        if (this.mState <= 1) {
            AtomicReference atomicReference = new AtomicReference();
            e4(new g(aVar2, atomicReference, aVar, bVar));
            return new h(atomicReference, aVar);
        }
        throw new IllegalStateException("Fragment " + this + " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).");
    }

    private void e4(k kVar) {
        if (this.mState >= 0) {
            kVar.a();
        } else {
            this.mOnPreAttachedListeners.add(kVar);
        }
    }

    private void f4() {
        if (n.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto RESTORE_VIEW_STATE: ");
            sb.append(this);
        }
        if (this.mView != null) {
            restoreViewState(this.mSavedFragmentState);
        }
        this.mSavedFragmentState = null;
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    void callStartTransitionListener(boolean z) {
        ViewGroup viewGroup;
        n nVar;
        i iVar = this.mAnimationInfo;
        l lVar = null;
        if (iVar != null) {
            iVar.u = false;
            l lVar2 = iVar.v;
            iVar.v = null;
            lVar = lVar2;
        }
        if (lVar != null) {
            lVar.b();
            return;
        }
        if (!n.P || this.mView == null || (viewGroup = this.mContainer) == null || (nVar = this.mFragmentManager) == null) {
            return;
        }
        d0 d0VarN = d0.n(viewGroup, nVar);
        d0VarN.p();
        if (z) {
            this.mHost.g().post(new c(d0VarN));
        } else {
            d0VarN.g();
        }
    }

    androidx.fragment.app.g createFragmentContainer() {
        return new d();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mSavedViewRegistryState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.mSavedViewRegistryState);
        }
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(targetFragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(getPopDirection());
        if (getEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(getEnterAnim());
        }
        if (getExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(getExitAnim());
        }
        if (getPopEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(getPopEnterAnim());
        }
        if (getPopExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(getPopExitAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
        }
        if (getContext() != null) {
            androidx.loader.app.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.W(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    Fragment findFragmentByWho(String str) {
        return str.equals(this.mWho) ? this : this.mChildFragmentManager.i0(str);
    }

    String generateActivityResultKey() {
        return "fragment_" + this.mWho + "_rq#" + this.mNextLocalRequestCode.getAndIncrement();
    }

    public final androidx.fragment.app.e getActivity() {
        androidx.fragment.app.k kVar = this.mHost;
        if (kVar == null) {
            return null;
        }
        return (androidx.fragment.app.e) kVar.e();
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        i iVar = this.mAnimationInfo;
        if (iVar == null || (bool = iVar.r) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        i iVar = this.mAnimationInfo;
        if (iVar == null || (bool = iVar.q) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    View getAnimatingAway() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        return iVar.a;
    }

    Animator getAnimator() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        return iVar.b;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final n getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public Context getContext() {
        androidx.fragment.app.k kVar = this.mHost;
        if (kVar == null) {
            return null;
        }
        return kVar.f();
    }

    @Override // androidx.lifecycle.e
    public /* synthetic */ j0.a getDefaultViewModelCreationExtras() {
        return androidx.lifecycle.d.a(this);
    }

    public g0.b getDefaultViewModelProviderFactory() {
        Application application;
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.mDefaultFactory == null) {
            Context applicationContext = requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    application = null;
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            if (application == null && n.F0(3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not find Application instance from Context ");
                sb.append(requireContext().getApplicationContext());
                sb.append(", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
            }
            this.mDefaultFactory = new androidx.lifecycle.c0(application, this, getArguments());
        }
        return this.mDefaultFactory;
    }

    int getEnterAnim() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return 0;
        }
        return iVar.d;
    }

    public Object getEnterTransition() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        return iVar.k;
    }

    o0 getEnterTransitionCallback() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        iVar.getClass();
        return null;
    }

    int getExitAnim() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return 0;
        }
        return iVar.e;
    }

    public Object getExitTransition() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        return iVar.m;
    }

    o0 getExitTransitionCallback() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        iVar.getClass();
        return null;
    }

    View getFocusedView() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        return iVar.t;
    }

    @Deprecated
    public final n getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        androidx.fragment.app.k kVar = this.mHost;
        if (kVar == null) {
            return null;
        }
        return kVar.i();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Override // androidx.lifecycle.m
    public androidx.lifecycle.f getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public androidx.loader.app.a getLoaderManager() {
        return androidx.loader.app.a.b(this);
    }

    int getNextTransition() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return 0;
        }
        return iVar.h;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public final n getParentFragmentManager() {
        n nVar = this.mFragmentManager;
        if (nVar != null) {
            return nVar;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    boolean getPopDirection() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return false;
        }
        return iVar.c;
    }

    int getPopEnterAnim() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return 0;
        }
        return iVar.f;
    }

    int getPopExitAnim() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return 0;
        }
        return iVar.g;
    }

    float getPostOnViewCreatedAlpha() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return 1.0f;
        }
        return iVar.f25s;
    }

    public Object getReenterTransition() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        Object obj = iVar.n;
        return obj == USE_DEFAULT_TRANSITION ? getExitTransition() : obj;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        Object obj = iVar.l;
        return obj == USE_DEFAULT_TRANSITION ? getEnterTransition() : obj;
    }

    public final androidx.savedstate.a getSavedStateRegistry() {
        return this.mSavedStateRegistryController.b();
    }

    public Object getSharedElementEnterTransition() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        return iVar.o;
    }

    public Object getSharedElementReturnTransition() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return null;
        }
        Object obj = iVar.p;
        return obj == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : obj;
    }

    ArrayList<String> getSharedElementSourceNames() {
        ArrayList<String> arrayList;
        i iVar = this.mAnimationInfo;
        return (iVar == null || (arrayList = iVar.i) == null) ? new ArrayList<>() : arrayList;
    }

    ArrayList<String> getSharedElementTargetNames() {
        ArrayList<String> arrayList;
        i iVar = this.mAnimationInfo;
        return (iVar == null || (arrayList = iVar.j) == null) ? new ArrayList<>() : arrayList;
    }

    public final String getString(int i2) {
        return getResources().getString(i2);
    }

    public final String getTag() {
        return this.mTag;
    }

    @Deprecated
    public final Fragment getTargetFragment() {
        String str;
        Fragment fragment = this.mTarget;
        if (fragment != null) {
            return fragment;
        }
        n nVar = this.mFragmentManager;
        if (nVar == null || (str = this.mTargetWho) == null) {
            return null;
        }
        return nVar.f0(str);
    }

    @Deprecated
    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i2) {
        return getResources().getText(i2);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public androidx.lifecycle.m getViewLifecycleOwner() {
        s0.c cVar = this.mViewLifecycleOwner;
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public LiveData getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // androidx.lifecycle.k0
    public j0 getViewModelStore() {
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (b4() != androidx.lifecycle.f.b.INITIALIZED.ordinal()) {
            return this.mFragmentManager.A0(this);
        }
        throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    void initState() {
        c4();
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new o();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    boolean isHideReplaced() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return false;
        }
        return iVar.w;
    }

    final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        n nVar;
        return this.mMenuVisible && ((nVar = this.mFragmentManager) == null || nVar.H0(this.mParentFragment));
    }

    boolean isPostponed() {
        i iVar = this.mAnimationInfo;
        if (iVar == null) {
            return false;
        }
        return iVar.u;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    final boolean isRemovingParent() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null && (parentFragment.isRemoving() || parentFragment.isRemovingParent());
    }

    public final boolean isResumed() {
        return this.mState >= 7;
    }

    public final boolean isStateSaved() {
        n nVar = this.mFragmentManager;
        if (nVar == null) {
            return false;
        }
        return nVar.K0();
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    void noteStateNotSaved() {
        this.mChildFragmentManager.T0();
    }

    @Deprecated
    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    @Deprecated
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Fragment ");
            sb.append(this);
            sb.append(" received the following in onActivityResult(): requestCode: ");
            sb.append(i2);
            sb.append(" resultCode: ");
            sb.append(i3);
            sb.append(" data: ");
            sb.append(intent);
        }
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        androidx.fragment.app.k kVar = this.mHost;
        Activity activityE = kVar == null ? null : kVar.e();
        if (activityE != null) {
            this.mCalled = false;
            onAttach(activityE);
        }
    }

    @Deprecated
    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        if (this.mChildFragmentManager.J0(1)) {
            return;
        }
        this.mChildFragmentManager.C();
    }

    public Animation onCreateAnimation(int i2, boolean z, int i3) {
        return null;
    }

    public Animator onCreateAnimator(int i2, boolean z, int i3) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2 = this.mContentLayoutId;
        if (i2 != 0) {
            return layoutInflater.inflate(i2, viewGroup, false);
        }
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z) {
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        androidx.fragment.app.k kVar = this.mHost;
        Activity activityE = kVar == null ? null : kVar.e();
        if (activityE != null) {
            this.mCalled = false;
            onInflate(activityE, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean z) {
    }

    @Deprecated
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    void performActivityCreated(Bundle bundle) {
        this.mChildFragmentManager.T0();
        this.mState = 3;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (this.mCalled) {
            f4();
            this.mChildFragmentManager.y();
        } else {
            throw new f0("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
    }

    void performAttach() {
        Iterator<k> it = this.mOnPreAttachedListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.mOnPreAttachedListeners.clear();
        this.mChildFragmentManager.k(this.mHost, createFragmentContainer(), this);
        this.mState = 0;
        this.mCalled = false;
        onAttach(this.mHost.f());
        if (this.mCalled) {
            this.mFragmentManager.I(this);
            this.mChildFragmentManager.z();
        } else {
            throw new f0("Fragment " + this + " did not call through to super.onAttach()");
        }
    }

    void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.mChildFragmentManager.A(configuration);
    }

    boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.B(menuItem);
    }

    void performCreate(Bundle bundle) {
        this.mChildFragmentManager.T0();
        this.mState = 1;
        this.mCalled = false;
        this.mLifecycleRegistry.a(new androidx.lifecycle.i() { // from class: androidx.fragment.app.Fragment.5
            @Override // androidx.lifecycle.i
            public void a(androidx.lifecycle.m mVar, androidx.lifecycle.f.a aVar) {
                View view;
                if (aVar != androidx.lifecycle.f.a.ON_STOP || (view = Fragment.this.mView) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
            }
        });
        this.mSavedStateRegistryController.d(bundle);
        onCreate(bundle);
        this.mIsCreated = true;
        if (this.mCalled) {
            this.mLifecycleRegistry.i(androidx.lifecycle.f.a.ON_CREATE);
            return;
        }
        throw new f0("Fragment " + this + " did not call through to super.onCreate()");
    }

    boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z = true;
        }
        return z | this.mChildFragmentManager.D(menu, menuInflater);
    }

    void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChildFragmentManager.T0();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new b0(this, getViewModelStore());
        View viewOnCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = viewOnCreateView;
        if (viewOnCreateView == null) {
            if (this.mViewLifecycleOwner.c()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.mViewLifecycleOwner = null;
        } else {
            this.mViewLifecycleOwner.b();
            l0.a(this.mView, this.mViewLifecycleOwner);
            m0.a(this.mView, this.mViewLifecycleOwner);
            s0.d.a(this.mView, this.mViewLifecycleOwner);
            this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
        }
    }

    void performDestroy() {
        this.mChildFragmentManager.E();
        this.mLifecycleRegistry.i(androidx.lifecycle.f.a.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (this.mCalled) {
            return;
        }
        throw new f0("Fragment " + this + " did not call through to super.onDestroy()");
    }

    void performDestroyView() {
        this.mChildFragmentManager.F();
        if (this.mView != null && this.mViewLifecycleOwner.getLifecycle().b().b(androidx.lifecycle.f.b.CREATED)) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.f.a.ON_DESTROY);
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            androidx.loader.app.a.b(this).d();
            this.mPerformedCreateView = false;
        } else {
            throw new f0("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    void performDetach() {
        this.mState = -1;
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (this.mCalled) {
            if (this.mChildFragmentManager.E0()) {
                return;
            }
            this.mChildFragmentManager.E();
            this.mChildFragmentManager = new o();
            return;
        }
        throw new f0("Fragment " + this + " did not call through to super.onDetach()");
    }

    LayoutInflater performGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = layoutInflaterOnGetLayoutInflater;
        return layoutInflaterOnGetLayoutInflater;
    }

    void performLowMemory() {
        onLowMemory();
        this.mChildFragmentManager.G();
    }

    void performMultiWindowModeChanged(boolean z) {
        onMultiWindowModeChanged(z);
        this.mChildFragmentManager.H(z);
    }

    boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.J(menuItem);
    }

    void performOptionsMenuClosed(Menu menu) {
        if (this.mHidden) {
            return;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onOptionsMenuClosed(menu);
        }
        this.mChildFragmentManager.K(menu);
    }

    void performPause() {
        this.mChildFragmentManager.M();
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.f.a.ON_PAUSE);
        }
        this.mLifecycleRegistry.i(androidx.lifecycle.f.a.ON_PAUSE);
        this.mState = 6;
        this.mCalled = false;
        onPause();
        if (this.mCalled) {
            return;
        }
        throw new f0("Fragment " + this + " did not call through to super.onPause()");
    }

    void performPictureInPictureModeChanged(boolean z) {
        onPictureInPictureModeChanged(z);
        this.mChildFragmentManager.N(z);
    }

    boolean performPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z = true;
        }
        return z | this.mChildFragmentManager.O(menu);
    }

    void performPrimaryNavigationFragmentChanged() {
        boolean zI0 = this.mFragmentManager.I0(this);
        Boolean bool = this.mIsPrimaryNavigationFragment;
        if (bool == null || bool.booleanValue() != zI0) {
            this.mIsPrimaryNavigationFragment = Boolean.valueOf(zI0);
            onPrimaryNavigationFragmentChanged(zI0);
            this.mChildFragmentManager.P();
        }
    }

    void performResume() {
        this.mChildFragmentManager.T0();
        this.mChildFragmentManager.a0(true);
        this.mState = 7;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new f0("Fragment " + this + " did not call through to super.onResume()");
        }
        androidx.lifecycle.o oVar = this.mLifecycleRegistry;
        androidx.lifecycle.f.a aVar = androidx.lifecycle.f.a.ON_RESUME;
        oVar.i(aVar);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(aVar);
        }
        this.mChildFragmentManager.Q();
    }

    void performSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.e(bundle);
        Parcelable parcelableK1 = this.mChildFragmentManager.k1();
        if (parcelableK1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableK1);
        }
    }

    void performStart() {
        this.mChildFragmentManager.T0();
        this.mChildFragmentManager.a0(true);
        this.mState = 5;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new f0("Fragment " + this + " did not call through to super.onStart()");
        }
        androidx.lifecycle.o oVar = this.mLifecycleRegistry;
        androidx.lifecycle.f.a aVar = androidx.lifecycle.f.a.ON_START;
        oVar.i(aVar);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(aVar);
        }
        this.mChildFragmentManager.R();
    }

    void performStop() {
        this.mChildFragmentManager.T();
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.f.a.ON_STOP);
        }
        this.mLifecycleRegistry.i(androidx.lifecycle.f.a.ON_STOP);
        this.mState = 4;
        this.mCalled = false;
        onStop();
        if (this.mCalled) {
            return;
        }
        throw new f0("Fragment " + this + " did not call through to super.onStop()");
    }

    void performViewCreated() {
        onViewCreated(this.mView, this.mSavedFragmentState);
        this.mChildFragmentManager.U();
    }

    public void postponeEnterTransition() {
        a4().u = true;
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(e.a aVar, androidx.activity.result.b bVar) {
        return d4(aVar, new e(), bVar);
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    @Deprecated
    public final void requestPermissions(String[] strArr, int i2) {
        if (this.mHost != null) {
            getParentFragmentManager().L0(this, strArr, i2);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final androidx.fragment.app.e requireActivity() {
        androidx.fragment.app.e activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException("Fragment " + this + " does not have any arguments.");
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    @Deprecated
    public final n requireFragmentManager() {
        return getParentFragmentManager();
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a host.");
    }

    public final Fragment requireParentFragment() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        if (getContext() == null) {
            throw new IllegalStateException("Fragment " + this + " is not attached to any Fragment or host");
        }
        throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
    }

    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.mChildFragmentManager.i1(parcelable);
        this.mChildFragmentManager.C();
    }

    final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        if (this.mView != null) {
            this.mViewLifecycleOwner.d(this.mSavedViewRegistryState);
            this.mSavedViewRegistryState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (this.mCalled) {
            if (this.mView != null) {
                this.mViewLifecycleOwner.a(androidx.lifecycle.f.a.ON_CREATE);
            }
        } else {
            throw new f0("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        a4().r = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        a4().q = Boolean.valueOf(z);
    }

    void setAnimatingAway(View view) {
        a4().a = view;
    }

    void setAnimations(int i2, int i3, int i4, int i5) {
        if (this.mAnimationInfo == null && i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            return;
        }
        a4().d = i2;
        a4().e = i3;
        a4().f = i4;
        a4().g = i5;
    }

    void setAnimator(Animator animator) {
        a4().b = animator;
    }

    public void setArguments(Bundle bundle) {
        if (this.mFragmentManager != null && isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(o0 o0Var) {
        a4().getClass();
    }

    public void setEnterTransition(Object obj) {
        a4().k = obj;
    }

    public void setExitSharedElementCallback(o0 o0Var) {
        a4().getClass();
    }

    public void setExitTransition(Object obj) {
        a4().m = obj;
    }

    void setFocusedView(View view) {
        a4().t = view;
    }

    public void setHasOptionsMenu(boolean z) {
        if (this.mHasMenu != z) {
            this.mHasMenu = z;
            if (!isAdded() || isHidden()) {
                return;
            }
            this.mHost.p();
        }
    }

    void setHideReplaced(boolean z) {
        a4().w = z;
    }

    public void setInitialSavedState(m mVar) {
        Bundle bundle;
        if (this.mFragmentManager != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (mVar == null || (bundle = mVar.a) == null) {
            bundle = null;
        }
        this.mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean z) {
        if (this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                this.mHost.p();
            }
        }
    }

    void setNextTransition(int i2) {
        if (this.mAnimationInfo == null && i2 == 0) {
            return;
        }
        a4();
        this.mAnimationInfo.h = i2;
    }

    void setOnStartEnterTransitionListener(l lVar) {
        a4();
        i iVar = this.mAnimationInfo;
        l lVar2 = iVar.v;
        if (lVar == lVar2) {
            return;
        }
        if (lVar != null && lVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (iVar.u) {
            iVar.v = lVar;
        }
        if (lVar != null) {
            lVar.a();
        }
    }

    void setPopDirection(boolean z) {
        if (this.mAnimationInfo == null) {
            return;
        }
        a4().c = z;
    }

    void setPostOnViewCreatedAlpha(float f2) {
        a4().f25s = f2;
    }

    public void setReenterTransition(Object obj) {
        a4().n = obj;
    }

    @Deprecated
    public void setRetainInstance(boolean z) {
        this.mRetainInstance = z;
        n nVar = this.mFragmentManager;
        if (nVar == null) {
            this.mRetainInstanceChangedWhileDetached = true;
        } else if (z) {
            nVar.i(this);
        } else {
            nVar.g1(this);
        }
    }

    public void setReturnTransition(Object obj) {
        a4().l = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        a4().o = obj;
    }

    void setSharedElementNames(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        a4();
        i iVar = this.mAnimationInfo;
        iVar.i = arrayList;
        iVar.j = arrayList2;
    }

    public void setSharedElementReturnTransition(Object obj) {
        a4().p = obj;
    }

    @Deprecated
    public void setTargetFragment(Fragment fragment, int i2) {
        n nVar = this.mFragmentManager;
        n nVar2 = fragment != null ? fragment.mFragmentManager : null;
        if (nVar != null && nVar2 != null && nVar != nVar2) {
            throw new IllegalArgumentException("Fragment " + fragment + " must share the same FragmentManager to be set as a target fragment");
        }
        for (Fragment targetFragment = fragment; targetFragment != null; targetFragment = targetFragment.getTargetFragment()) {
            if (targetFragment.equals(this)) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        if (fragment == null) {
            this.mTargetWho = null;
            this.mTarget = null;
        } else if (this.mFragmentManager == null || fragment.mFragmentManager == null) {
            this.mTargetWho = null;
            this.mTarget = fragment;
        } else {
            this.mTargetWho = fragment.mWho;
            this.mTarget = null;
        }
        this.mTargetRequestCode = i2;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z) {
        if (!this.mUserVisibleHint && z && this.mState < 5 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            n nVar = this.mFragmentManager;
            nVar.V0(nVar.v(this));
        }
        this.mUserVisibleHint = z;
        this.mDeferStart = this.mState < 5 && !z;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        androidx.fragment.app.k kVar = this.mHost;
        if (kVar != null) {
            return kVar.m(str);
        }
        return false;
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int i2) {
        startActivityForResult(intent, i2, null);
    }

    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        if (n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Fragment ");
            sb.append(this);
            sb.append(" received the following in startIntentSenderForResult() requestCode: ");
            sb.append(i2);
            sb.append(" IntentSender: ");
            sb.append(intentSender);
            sb.append(" fillInIntent: ");
            sb.append(intent);
            sb.append(" options: ");
            sb.append(bundle);
        }
        getParentFragmentManager().N0(this, intentSender, i2, intent, i3, i4, i5, bundle);
    }

    public void startPostponedEnterTransition() {
        if (this.mAnimationInfo == null || !a4().u) {
            return;
        }
        if (this.mHost == null) {
            a4().u = false;
        } else if (Looper.myLooper() != this.mHost.g().getLooper()) {
            this.mHost.g().postAtFrontOfQueue(new b());
        } else {
            callStartTransitionListener(true);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.mWho);
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        sb.append(")");
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    public static class m implements Parcelable {
        public static final Parcelable.Creator<m> CREATOR = new a();
        final Bundle a;

        class a implements Parcelable.ClassLoaderCreator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public m createFromParcel(Parcel parcel) {
                return new m(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public m createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new m(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public m[] newArray(int i) {
                return new m[i];
            }
        }

        m(Bundle bundle) {
            this.a = bundle;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.a);
        }

        m(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.a = bundle;
            if (classLoader == null || bundle == null) {
                return;
            }
            bundle.setClassLoader(classLoader);
        }
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str, Bundle bundle) {
        try {
            Fragment fragment = (Fragment) androidx.fragment.app.j.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (IllegalAccessException e2) {
            throw new j("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (InstantiationException e3) {
            throw new j("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new j("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new j("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    public final String getString(int i2, Object... objArr) {
        return getResources().getString(i2, objArr);
    }

    public final void postponeEnterTransition(long j2, TimeUnit timeUnit) {
        a4().u = true;
        n nVar = this.mFragmentManager;
        Handler handlerG = nVar != null ? nVar.t0().g() : new Handler(Looper.getMainLooper());
        handlerG.removeCallbacks(this.mPostponedDurationRunnable);
        handlerG.postDelayed(this.mPostponedDurationRunnable, timeUnit.toMillis(j2));
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(e.a aVar, ActivityResultRegistry activityResultRegistry, androidx.activity.result.b bVar) {
        return d4(aVar, new f(activityResultRegistry), bVar);
    }

    public void startActivity(Intent intent, Bundle bundle) {
        androidx.fragment.app.k kVar = this.mHost;
        if (kVar != null) {
            kVar.n(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        if (this.mHost != null) {
            getParentFragmentManager().M0(this, intent, i2, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        androidx.fragment.app.k kVar = this.mHost;
        if (kVar != null) {
            LayoutInflater layoutInflaterJ = kVar.j();
            androidx.core.view.l.a(layoutInflaterJ, this.mChildFragmentManager.u0());
            return layoutInflaterJ;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }
}
