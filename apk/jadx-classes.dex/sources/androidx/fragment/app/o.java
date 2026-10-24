package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.fragment.R$id;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class o {
    private static boolean O = false;
    static boolean P = true;
    private androidx.activity.result.c A;
    private androidx.activity.result.c B;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private ArrayList I;
    private ArrayList J;
    private ArrayList K;
    private ArrayList L;
    private r M;
    private boolean b;
    ArrayList d;
    private ArrayList e;
    private OnBackPressedDispatcher g;
    private ArrayList l;
    private androidx.fragment.app.l r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private androidx.fragment.app.g f26s;
    private Fragment t;
    Fragment u;
    private androidx.activity.result.c z;
    private final ArrayList a = new ArrayList();
    private final x c = new x();
    private final androidx.fragment.app.m f = new androidx.fragment.app.m(this);
    private final androidx.activity.b h = new c(false);
    private final AtomicInteger i = new AtomicInteger();
    private final Map j = Collections.synchronizedMap(new HashMap());
    private final Map k = Collections.synchronizedMap(new HashMap());
    private Map m = Collections.synchronizedMap(new HashMap());
    private final z.g n = new d();
    private final androidx.fragment.app.n o = new androidx.fragment.app.n(this);
    private final CopyOnWriteArrayList p = new CopyOnWriteArrayList();
    int q = -1;
    private androidx.fragment.app.k v = null;
    private androidx.fragment.app.k w = new e();
    private k0 x = null;
    private k0 y = new f();
    ArrayDeque C = new ArrayDeque();
    private Runnable N = new g();

    class a implements androidx.activity.result.b {
        a() {
        }

        @Override // androidx.activity.result.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(androidx.activity.result.a aVar) {
            l lVar = (l) o.this.C.pollFirst();
            if (lVar == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("No IntentSenders were started for ");
                sb.append(this);
                return;
            }
            String str = lVar.a;
            int i = lVar.b;
            Fragment fragmentI = o.this.c.i(str);
            if (fragmentI != null) {
                fragmentI.onActivityResult(i, aVar.b(), aVar.a());
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Intent Sender result delivered for unknown Fragment ");
            sb2.append(str);
        }
    }

    class b implements androidx.activity.result.b {
        b() {
        }

        @Override // androidx.activity.result.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Map map) {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            ArrayList arrayList = new ArrayList(map.values());
            int[] iArr = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                iArr[i] = ((Boolean) arrayList.get(i)).booleanValue() ? 0 : -1;
            }
            l lVar = (l) o.this.C.pollFirst();
            if (lVar == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("No permissions were requested for ");
                sb.append(this);
                return;
            }
            String str = lVar.a;
            int i2 = lVar.b;
            Fragment fragmentI = o.this.c.i(str);
            if (fragmentI != null) {
                fragmentI.onRequestPermissionsResult(i2, strArr, iArr);
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Permission request result delivered for unknown Fragment ");
            sb2.append(str);
        }
    }

    class c extends androidx.activity.b {
        c(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void b() {
            o.this.B0();
        }
    }

    class d implements z.g {
        d() {
        }

        @Override // androidx.fragment.app.z.g
        public void a(Fragment fragment, u.b bVar) {
            if (bVar.b()) {
                return;
            }
            o.this.c1(fragment, bVar);
        }

        @Override // androidx.fragment.app.z.g
        public void b(Fragment fragment, u.b bVar) {
            o.this.f(fragment, bVar);
        }
    }

    class e extends androidx.fragment.app.k {
        e() {
        }

        @Override // androidx.fragment.app.k
        public Fragment a(ClassLoader classLoader, String str) {
            return o.this.t0().b(o.this.t0().f(), str, null);
        }
    }

    class f implements k0 {
        f() {
        }

        @Override // androidx.fragment.app.k0
        public j0 a(ViewGroup viewGroup) {
            return new androidx.fragment.app.c(viewGroup);
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            o.this.a0(true);
        }
    }

    class h extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;
        final /* synthetic */ Fragment c;

        h(ViewGroup viewGroup, View view, Fragment fragment) {
            this.a = viewGroup;
            this.b = view;
            this.c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.endViewTransition(this.b);
            animator.removeListener(this);
            Fragment fragment = this.c;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    class i implements s {
        final /* synthetic */ Fragment a;

        i(Fragment fragment) {
            this.a = fragment;
        }

        @Override // androidx.fragment.app.s
        public void a(o oVar, Fragment fragment) {
            this.a.onAttachFragment(fragment);
        }
    }

    class j implements androidx.activity.result.b {
        j() {
        }

        @Override // androidx.activity.result.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(androidx.activity.result.a aVar) {
            l lVar = (l) o.this.C.pollFirst();
            if (lVar == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("No Activities were started for result for ");
                sb.append(this);
                return;
            }
            String str = lVar.a;
            int i = lVar.b;
            Fragment fragmentI = o.this.c.i(str);
            if (fragmentI != null) {
                fragmentI.onActivityResult(i, aVar.b(), aVar.a());
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Activity result delivered for unknown Fragment ");
            sb2.append(str);
        }
    }

    static class k extends c.a {
        k() {
        }

        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, androidx.activity.result.e eVar) {
            Bundle bundleExtra;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent intentA = eVar.a();
            if (intentA != null && (bundleExtra = intentA.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                intentA.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (intentA.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    eVar = new androidx.activity.result.e.b(eVar.d()).b(null).c(eVar.c(), eVar.b()).a();
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", eVar);
            if (o.F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("CreateIntent created the following intent: ");
                sb.append(intent);
            }
            return intent;
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public androidx.activity.result.a c(int i, Intent intent) {
            return new androidx.activity.result.a(i, intent);
        }
    }

    interface m {
        boolean a(ArrayList arrayList, ArrayList arrayList2);
    }

    private class n implements m {
        final String a;
        final int b;
        final int c;

        n(String str, int i, int i2) {
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        @Override // androidx.fragment.app.o.m
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            Fragment fragment = o.this.u;
            if (fragment == null || this.b >= 0 || this.a != null || !fragment.getChildFragmentManager().X0()) {
                return o.this.Z0(arrayList, arrayList2, this.a, this.b, this.c);
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.o$o, reason: collision with other inner class name */
    static class C0031o implements Fragment.l {
        final boolean a;
        final androidx.fragment.app.a b;
        private int c;

        C0031o(androidx.fragment.app.a aVar, boolean z) {
            this.a = z;
            this.b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.l
        public void a() {
            this.c++;
        }

        @Override // androidx.fragment.app.Fragment.l
        public void b() {
            int i = this.c - 1;
            this.c = i;
            if (i != 0) {
                return;
            }
            this.b.t.l1();
        }

        void c() {
            androidx.fragment.app.a aVar = this.b;
            aVar.t.t(aVar, this.a, false, false);
        }

        void d() {
            boolean z = this.c > 0;
            for (Fragment fragment : this.b.t.s0()) {
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            androidx.fragment.app.a aVar = this.b;
            aVar.t.t(aVar, this.a, !z, true);
        }

        public boolean e() {
            return this.c == 0;
        }
    }

    static boolean F0(int i2) {
        return O || Log.isLoggable("FragmentManager", i2);
    }

    private boolean G0(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.o();
    }

    private void L(Fragment fragment) {
        if (fragment == null || !fragment.equals(f0(fragment.mWho))) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    private void O0(androidx.collection.b bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = (Fragment) bVar.h(i2);
            if (!fragment.mAdded) {
                View viewRequireView = fragment.requireView();
                fragment.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(0.0f);
            }
        }
    }

    private void S(int i2) {
        try {
            this.b = true;
            this.c.d(i2);
            Q0(i2, false);
            if (P) {
                Iterator it = r().iterator();
                while (it.hasNext()) {
                    ((j0) it.next()).j();
                }
            }
            this.b = false;
            a0(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    private void V() {
        if (this.H) {
            this.H = false;
            r1();
        }
    }

    private void X() {
        if (P) {
            Iterator it = r().iterator();
            while (it.hasNext()) {
                ((j0) it.next()).j();
            }
        } else {
            if (this.m.isEmpty()) {
                return;
            }
            for (Fragment fragment : this.m.keySet()) {
                n(fragment);
                R0(fragment);
            }
        }
    }

    private boolean Y0(String str, int i2, int i3) {
        a0(false);
        Z(true);
        Fragment fragment = this.u;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().X0()) {
            return true;
        }
        boolean zZ0 = Z0(this.I, this.J, str, i2, i3);
        if (zZ0) {
            this.b = true;
            try {
                e1(this.I, this.J);
                q();
            } catch (Throwable th) {
                q();
                throw th;
            }
        }
        t1();
        V();
        this.c.b();
        return zZ0;
    }

    private void Z(boolean z) {
        if (this.b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.r == null) {
            if (!this.G) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.r.g().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            p();
        }
        if (this.I == null) {
            this.I = new ArrayList();
            this.J = new ArrayList();
        }
        this.b = true;
        try {
            e0(null, null);
        } finally {
            this.b = false;
        }
    }

    private int a1(ArrayList arrayList, ArrayList arrayList2, int i2, int i3, androidx.collection.b bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i5);
            boolean zBooleanValue = ((Boolean) arrayList2.get(i5)).booleanValue();
            if (aVar.G() && !aVar.E(arrayList, i5 + 1, i3)) {
                if (this.L == null) {
                    this.L = new ArrayList();
                }
                C0031o c0031o = new C0031o(aVar, zBooleanValue);
                this.L.add(c0031o);
                aVar.I(c0031o);
                if (zBooleanValue) {
                    aVar.z();
                } else {
                    aVar.A(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                d(bVar);
            }
        }
        return i4;
    }

    private static void c0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i2);
            if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                aVar.v(-1);
                aVar.A(i2 == i3 + (-1));
            } else {
                aVar.v(1);
                aVar.z();
            }
            i2++;
        }
    }

    private void d(androidx.collection.b bVar) {
        int i2 = this.q;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 5);
        for (Fragment fragment : this.c.n()) {
            if (fragment.mState < iMin) {
                S0(fragment, iMin);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    bVar.add(fragment);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    private void d0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        ?? r1;
        int i4;
        int i5;
        int iA1;
        boolean z;
        int i6;
        ArrayList arrayList3 = arrayList2;
        boolean z2 = ((androidx.fragment.app.a) arrayList.get(i2)).r;
        ArrayList arrayList4 = this.K;
        if (arrayList4 == null) {
            this.K = new ArrayList();
        } else {
            arrayList4.clear();
        }
        this.K.addAll(this.c.n());
        Fragment fragmentX0 = x0();
        boolean z3 = false;
        for (int i7 = i2; i7 < i3; i7++) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i7);
            fragmentX0 = !((Boolean) arrayList3.get(i7)).booleanValue() ? aVar.B(this.K, fragmentX0) : aVar.J(this.K, fragmentX0);
            z3 = z3 || aVar.i;
        }
        this.K.clear();
        if (z2 || this.q < 1) {
            r1 = 1;
        } else if (P) {
            for (int i8 = i2; i8 < i3; i8++) {
                Iterator it = ((androidx.fragment.app.a) arrayList.get(i8)).c.iterator();
                while (it.hasNext()) {
                    Fragment fragment = ((y.a) it.next()).b;
                    if (fragment != null && fragment.mFragmentManager != null) {
                        this.c.p(v(fragment));
                    }
                }
            }
            r1 = 1;
        } else {
            r1 = 1;
            z.B(this.r.f(), this.f26s, arrayList, arrayList2, i2, i3, false, this.n);
        }
        c0(arrayList, arrayList2, i2, i3);
        if (P) {
            boolean zBooleanValue = ((Boolean) arrayList3.get(i3 - 1)).booleanValue();
            for (int i9 = i2; i9 < i3; i9++) {
                androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) arrayList.get(i9);
                if (zBooleanValue) {
                    int size = aVar2.c.size() - r1;
                    while (i6 >= 0) {
                        Fragment fragment2 = ((y.a) aVar2.c.get(i6)).b;
                        if (fragment2 != null) {
                            i6 = size;
                            v(fragment2).m();
                        } else {
                            i6 = size;
                        }
                        i6--;
                    }
                    i6 = size;
                } else {
                    Iterator it2 = aVar2.c.iterator();
                    while (it2.hasNext()) {
                        Fragment fragment3 = ((y.a) it2.next()).b;
                        if (fragment3 != null) {
                            v(fragment3).m();
                        }
                    }
                }
            }
            Q0(this.q, r1);
            for (j0 j0Var : s(arrayList, i2, i3)) {
                j0Var.r(zBooleanValue);
                j0Var.p();
                j0Var.g();
            }
            i4 = i3;
            arrayList3 = arrayList3;
        } else {
            if (z2) {
                androidx.collection.b bVar = new androidx.collection.b();
                d(bVar);
                i5 = 1;
                i4 = i3;
                iA1 = a1(arrayList, arrayList2, i2, i3, bVar);
                O0(bVar);
            } else {
                i4 = i3;
                i5 = 1;
                iA1 = i4;
            }
            if (iA1 == i2 || !z2) {
                arrayList3 = arrayList3;
                i4 = i4;
            } else {
                if (this.q >= i5) {
                    z = true;
                    z.B(this.r.f(), this.f26s, arrayList, arrayList2, i2, iA1, true, this.n);
                } else {
                    z = true;
                }
                Q0(this.q, z);
            }
        }
        for (int i10 = i2; i10 < i4; i10++) {
            androidx.fragment.app.a aVar3 = (androidx.fragment.app.a) arrayList.get(i10);
            if (((Boolean) arrayList3.get(i10)).booleanValue() && aVar3.v >= 0) {
                aVar3.v = -1;
            }
            aVar3.H();
        }
        if (z3) {
            g1();
        }
    }

    private void e0(ArrayList arrayList, ArrayList arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList arrayList3 = this.L;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            C0031o c0031o = (C0031o) this.L.get(i2);
            if (arrayList != null && !c0031o.a && (iIndexOf2 = arrayList.indexOf(c0031o.b)) != -1 && arrayList2 != null && ((Boolean) arrayList2.get(iIndexOf2)).booleanValue()) {
                this.L.remove(i2);
                i2--;
                size--;
                c0031o.c();
            } else if (c0031o.e() || (arrayList != null && c0031o.b.E(arrayList, 0, arrayList.size()))) {
                this.L.remove(i2);
                i2--;
                size--;
                if (arrayList == null || c0031o.a || (iIndexOf = arrayList.indexOf(c0031o.b)) == -1 || arrayList2 == null || !((Boolean) arrayList2.get(iIndexOf)).booleanValue()) {
                    c0031o.d();
                } else {
                    c0031o.c();
                }
            }
            i2++;
        }
    }

    private void e1(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        e0(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!((androidx.fragment.app.a) arrayList.get(i2)).r) {
                if (i3 != i2) {
                    d0(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                    while (i3 < size && ((Boolean) arrayList2.get(i3)).booleanValue() && !((androidx.fragment.app.a) arrayList.get(i3)).r) {
                        i3++;
                    }
                }
                d0(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            d0(arrayList, arrayList2, i3, size);
        }
    }

    private void g1() {
        ArrayList arrayList = this.l;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        androidx.appcompat.app.m.a(this.l.get(0));
        throw null;
    }

    static int i1(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private void j0() {
        if (P) {
            Iterator it = r().iterator();
            while (it.hasNext()) {
                ((j0) it.next()).k();
            }
        } else if (this.L != null) {
            while (!this.L.isEmpty()) {
                ((C0031o) this.L.remove(0)).d();
            }
        }
    }

    private boolean k0(ArrayList arrayList, ArrayList arrayList2) {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                return false;
            }
            int size = this.a.size();
            boolean zA = false;
            for (int i2 = 0; i2 < size; i2++) {
                zA |= ((m) this.a.get(i2)).a(arrayList, arrayList2);
            }
            this.a.clear();
            this.r.g().removeCallbacks(this.N);
            return zA;
        }
    }

    private r m0(Fragment fragment) {
        return this.M.i(fragment);
    }

    private void n(Fragment fragment) {
        HashSet hashSet = (HashSet) this.m.get(fragment);
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((u.b) it.next()).a();
            }
            hashSet.clear();
            w(fragment);
            this.m.remove(fragment);
        }
    }

    private void p() {
        if (K0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private ViewGroup p0(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.f26s.d()) {
            View viewC = this.f26s.c(fragment.mContainerId);
            if (viewC instanceof ViewGroup) {
                return (ViewGroup) viewC;
            }
        }
        return null;
    }

    private void p1(Fragment fragment) {
        ViewGroup viewGroupP0 = p0(fragment);
        if (viewGroupP0 == null || fragment.getEnterAnim() + fragment.getExitAnim() + fragment.getPopEnterAnim() + fragment.getPopExitAnim() <= 0) {
            return;
        }
        int i2 = R$id.visible_removing_fragment_view_tag;
        if (viewGroupP0.getTag(i2) == null) {
            viewGroupP0.setTag(i2, fragment);
        }
        ((Fragment) viewGroupP0.getTag(i2)).setPopDirection(fragment.getPopDirection());
    }

    private void q() {
        this.b = false;
        this.J.clear();
        this.I.clear();
    }

    private Set r() {
        HashSet hashSet = new HashSet();
        Iterator it = this.c.k().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((v) it.next()).k().mContainer;
            if (viewGroup != null) {
                hashSet.add(j0.o(viewGroup, y0()));
            }
        }
        return hashSet;
    }

    private void r1() {
        Iterator it = this.c.k().iterator();
        while (it.hasNext()) {
            V0((v) it.next());
        }
    }

    private Set s(ArrayList arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator it = ((androidx.fragment.app.a) arrayList.get(i2)).c.iterator();
            while (it.hasNext()) {
                Fragment fragment = ((y.a) it.next()).b;
                if (fragment != null && (viewGroup = fragment.mContainer) != null) {
                    hashSet.add(j0.n(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    private void s1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new i0("FragmentManager"));
        androidx.fragment.app.l lVar = this.r;
        if (lVar != null) {
            try {
                lVar.h("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            W("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    private void t1() {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                this.h.f(l0() > 0 && I0(this.t));
            } else {
                this.h.f(true);
            }
        }
    }

    private void u(Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            androidx.fragment.app.f.d dVarC = androidx.fragment.app.f.c(this.r.f(), fragment, !fragment.mHidden, fragment.getPopDirection());
            if (dVarC == null || (animator = dVarC.b) == null) {
                if (dVarC != null) {
                    fragment.mView.startAnimation(dVarC.a);
                    dVarC.a.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    ViewGroup viewGroup = fragment.mContainer;
                    View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    dVarC.b.addListener(new h(viewGroup, view, fragment));
                }
                dVarC.b.start();
            }
        }
        D0(fragment);
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    private void w(Fragment fragment) {
        fragment.performDestroyView();
        this.o.n(fragment, false);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.n(null);
        fragment.mInLayout = false;
    }

    static Fragment z0(View view) {
        Object tag = view.getTag(R$id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    void A(Configuration configuration) {
        for (Fragment fragment : this.c.n()) {
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    androidx.lifecycle.x A0(Fragment fragment) {
        return this.M.l(fragment);
    }

    boolean B(MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.c.n()) {
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void B0() {
        a0(true);
        if (this.h.c()) {
            X0();
        } else {
            this.g.c();
        }
    }

    void C() {
        this.E = false;
        this.F = false;
        this.M.o(false);
        S(1);
    }

    void C0(Fragment fragment) {
        if (F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("hide: ");
            sb.append(fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        p1(fragment);
    }

    boolean D(Menu menu, MenuInflater menuInflater) {
        if (this.q < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.c.n()) {
            if (fragment != null && H0(fragment) && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                Fragment fragment2 = (Fragment) this.e.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.e = arrayList;
        return z;
    }

    void D0(Fragment fragment) {
        if (fragment.mAdded && G0(fragment)) {
            this.D = true;
        }
    }

    void E() {
        this.G = true;
        a0(true);
        X();
        S(-1);
        this.r = null;
        this.f26s = null;
        this.t = null;
        if (this.g != null) {
            this.h.d();
            this.g = null;
        }
        androidx.activity.result.c cVar = this.z;
        if (cVar != null) {
            cVar.c();
            this.A.c();
            this.B.c();
        }
    }

    public boolean E0() {
        return this.G;
    }

    void F() {
        S(1);
    }

    void G() {
        for (Fragment fragment : this.c.n()) {
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    void H(boolean z) {
        for (Fragment fragment : this.c.n()) {
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    boolean H0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }

    void I(Fragment fragment) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((s) it.next()).a(this, fragment);
        }
    }

    boolean I0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        o oVar = fragment.mFragmentManager;
        return fragment.equals(oVar.x0()) && I0(oVar.t);
    }

    boolean J(MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.c.n()) {
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    boolean J0(int i2) {
        return this.q >= i2;
    }

    void K(Menu menu) {
        if (this.q < 1) {
            return;
        }
        for (Fragment fragment : this.c.n()) {
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public boolean K0() {
        return this.E || this.F;
    }

    void L0(Fragment fragment, String[] strArr, int i2) {
        if (this.B == null) {
            this.r.k(fragment, strArr, i2);
            return;
        }
        this.C.addLast(new l(fragment.mWho, i2));
        this.B.a(strArr);
    }

    void M() {
        S(5);
    }

    void M0(Fragment fragment, Intent intent, int i2, Bundle bundle) {
        if (this.z == null) {
            this.r.n(fragment, intent, i2, bundle);
            return;
        }
        this.C.addLast(new l(fragment.mWho, i2));
        if (intent != null && bundle != null) {
            intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        }
        this.z.a(intent);
    }

    void N(boolean z) {
        for (Fragment fragment : this.c.n()) {
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    void N0(Fragment fragment, IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        Intent intent2;
        if (this.A == null) {
            this.r.o(fragment, intentSender, i2, intent, i3, i4, i5, bundle);
            return;
        }
        if (bundle != null) {
            if (intent == null) {
                intent2 = new Intent();
                intent2.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            } else {
                intent2 = intent;
            }
            if (F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("ActivityOptions ");
                sb.append(bundle);
                sb.append(" were added to fillInIntent ");
                sb.append(intent2);
                sb.append(" for fragment ");
                sb.append(fragment);
            }
            intent2.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        } else {
            intent2 = intent;
        }
        androidx.activity.result.e eVarA = new androidx.activity.result.e.b(intentSender).b(intent2).c(i4, i3).a();
        this.C.addLast(new l(fragment.mWho, i2));
        if (F0(2)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Fragment ");
            sb2.append(fragment);
            sb2.append("is launching an IntentSender for result ");
        }
        this.A.a(eVarA);
    }

    boolean O(Menu menu) {
        boolean z = false;
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.c.n()) {
            if (fragment != null && H0(fragment) && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    void P() {
        t1();
        L(this.u);
    }

    void P0(Fragment fragment) {
        if (!this.c.c(fragment.mWho)) {
            if (F0(3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Ignoring moving ");
                sb.append(fragment);
                sb.append(" to state ");
                sb.append(this.q);
                sb.append("since it is not added to ");
                sb.append(this);
                return;
            }
            return;
        }
        R0(fragment);
        View view = fragment.mView;
        if (view != null && fragment.mIsNewlyAdded && fragment.mContainer != null) {
            float f2 = fragment.mPostponedAlpha;
            if (f2 > 0.0f) {
                view.setAlpha(f2);
            }
            fragment.mPostponedAlpha = 0.0f;
            fragment.mIsNewlyAdded = false;
            androidx.fragment.app.f.d dVarC = androidx.fragment.app.f.c(this.r.f(), fragment, true, fragment.getPopDirection());
            if (dVarC != null) {
                Animation animation = dVarC.a;
                if (animation != null) {
                    fragment.mView.startAnimation(animation);
                } else {
                    dVarC.b.setTarget(fragment.mView);
                    dVarC.b.start();
                }
            }
        }
        if (fragment.mHiddenChanged) {
            u(fragment);
        }
    }

    void Q() {
        this.E = false;
        this.F = false;
        this.M.o(false);
        S(7);
    }

    void Q0(int i2, boolean z) {
        androidx.fragment.app.l lVar;
        if (this.r == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.q) {
            this.q = i2;
            if (P) {
                this.c.r();
            } else {
                Iterator it = this.c.n().iterator();
                while (it.hasNext()) {
                    P0((Fragment) it.next());
                }
                for (v vVar : this.c.k()) {
                    Fragment fragmentK = vVar.k();
                    if (!fragmentK.mIsNewlyAdded) {
                        P0(fragmentK);
                    }
                    if (fragmentK.mRemoving && !fragmentK.isInBackStack()) {
                        this.c.q(vVar);
                    }
                }
            }
            r1();
            if (this.D && (lVar = this.r) != null && this.q == 7) {
                lVar.p();
                this.D = false;
            }
        }
    }

    void R() {
        this.E = false;
        this.F = false;
        this.M.o(false);
        S(5);
    }

    void R0(Fragment fragment) {
        S0(fragment, this.q);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0151  */
    /* JADX WARN: Code duplicated, block: B:31:0x0061  */
    /* JADX WARN: Code duplicated, block: B:33:0x0066  */
    /* JADX WARN: Code duplicated, block: B:35:0x006b  */
    /* JADX WARN: Code duplicated, block: B:37:0x0070  */
    /* JADX WARN: Code duplicated, block: B:39:0x0075  */
    /* JADX WARN: Code duplicated, block: B:98:0x014e  */
    void S0(Fragment fragment, int i2) {
        ViewGroup viewGroup;
        v vVarM = this.c.m(fragment.mWho);
        int i3 = 1;
        if (vVarM == null) {
            vVarM = new v(this.o, this.c, fragment);
            vVarM.u(1);
        }
        if (fragment.mFromLayout && fragment.mInLayout && fragment.mState == 2) {
            i2 = Math.max(i2, 2);
        }
        int iMin = Math.min(i2, vVarM.d());
        int i4 = fragment.mState;
        if (i4 <= iMin) {
            if (i4 < iMin && !this.m.isEmpty()) {
                n(fragment);
            }
            int i5 = fragment.mState;
            if (i5 != -1) {
                if (i5 != 0) {
                    if (i5 != 1) {
                        if (i5 != 2) {
                            if (i5 != 4) {
                                if (i5 == 5) {
                                }
                            }
                            if (iMin > 5) {
                                vVarM.p();
                            }
                        }
                        if (iMin > 4) {
                            vVarM.v();
                        }
                        if (iMin > 5) {
                            vVarM.p();
                        }
                    }
                    if (iMin > 2) {
                        vVarM.a();
                    }
                    if (iMin > 4) {
                        vVarM.v();
                    }
                    if (iMin > 5) {
                        vVarM.p();
                    }
                }
                if (iMin > -1) {
                    vVarM.j();
                }
                if (iMin > 1) {
                    vVarM.f();
                }
                if (iMin > 2) {
                    vVarM.a();
                }
                if (iMin > 4) {
                    vVarM.v();
                }
                if (iMin > 5) {
                    vVarM.p();
                }
            } else if (iMin > -1) {
                vVarM.c();
            }
            if (iMin > 0) {
                vVarM.e();
            }
            if (iMin > -1) {
                vVarM.j();
            }
            if (iMin > 1) {
                vVarM.f();
            }
            if (iMin > 2) {
                vVarM.a();
            }
            if (iMin > 4) {
                vVarM.v();
            }
            if (iMin > 5) {
                vVarM.p();
            }
        } else if (i4 > iMin) {
            if (i4 == 0) {
                i3 = iMin;
                if (i3 < 0) {
                    vVarM.i();
                }
                iMin = i3;
            } else {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 4) {
                            if (i4 != 5) {
                                if (i4 == 7) {
                                    if (iMin < 7) {
                                        vVarM.n();
                                    }
                                }
                            }
                            if (iMin < 5) {
                                vVarM.w();
                            }
                        }
                        if (iMin < 4) {
                            if (F0(3)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("movefrom ACTIVITY_CREATED: ");
                                sb.append(fragment);
                            }
                            if (fragment.mView != null && this.r.l(fragment) && fragment.mSavedViewState == null) {
                                vVarM.t();
                            }
                        }
                    }
                    if (iMin < 2) {
                        View view = fragment.mView;
                        if (view != null && (viewGroup = fragment.mContainer) != null) {
                            viewGroup.endViewTransition(view);
                            fragment.mView.clearAnimation();
                            if (!fragment.isRemovingParent()) {
                                androidx.fragment.app.f.d dVarC = (this.q <= -1 || this.G || fragment.mView.getVisibility() != 0 || fragment.mPostponedAlpha < 0.0f) ? null : androidx.fragment.app.f.c(this.r.f(), fragment, false, fragment.getPopDirection());
                                fragment.mPostponedAlpha = 0.0f;
                                ViewGroup viewGroup2 = fragment.mContainer;
                                View view2 = fragment.mView;
                                if (dVarC != null) {
                                    androidx.fragment.app.f.a(fragment, dVarC, this.n);
                                }
                                viewGroup2.removeView(view2);
                                if (F0(2)) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Removing view ");
                                    sb2.append(view2);
                                    sb2.append(" for fragment ");
                                    sb2.append(fragment);
                                    sb2.append(" from container ");
                                    sb2.append(viewGroup2);
                                }
                                if (viewGroup2 != fragment.mContainer) {
                                    return;
                                }
                            }
                        }
                        if (this.m.get(fragment) == null) {
                            vVarM.h();
                        }
                    }
                }
                if (iMin >= 1) {
                    i3 = iMin;
                } else if (this.m.get(fragment) == null) {
                    vVarM.g();
                    i3 = iMin;
                }
                if (i3 < 0) {
                    vVarM.i();
                }
                iMin = i3;
            }
        }
        if (fragment.mState != iMin) {
            if (F0(3)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("moveToState: Fragment state for ");
                sb3.append(fragment);
                sb3.append(" not updated inline; expected state ");
                sb3.append(iMin);
                sb3.append(" found ");
                sb3.append(fragment.mState);
            }
            fragment.mState = iMin;
        }
    }

    void T() {
        this.F = true;
        this.M.o(true);
        S(4);
    }

    void T0() {
        if (this.r == null) {
            return;
        }
        this.E = false;
        this.F = false;
        this.M.o(false);
        for (Fragment fragment : this.c.n()) {
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    void U() {
        S(2);
    }

    void U0(androidx.fragment.app.i iVar) {
        View view;
        for (v vVar : this.c.k()) {
            Fragment fragmentK = vVar.k();
            if (fragmentK.mContainerId == iVar.getId() && (view = fragmentK.mView) != null && view.getParent() == null) {
                fragmentK.mContainer = iVar;
                vVar.b();
            }
        }
    }

    void V0(v vVar) {
        Fragment fragmentK = vVar.k();
        if (fragmentK.mDeferStart) {
            if (this.b) {
                this.H = true;
                return;
            }
            fragmentK.mDeferStart = false;
            if (P) {
                vVar.m();
            } else {
                R0(fragmentK);
            }
        }
    }

    public void W(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.c.e(str, fileDescriptor, printWriter, strArr);
        ArrayList arrayList = this.e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment = (Fragment) this.e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList arrayList2 = this.d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.x(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.a) {
            int size3 = this.a.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size3; i4++) {
                    m mVar = (m) this.a.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(mVar);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.r);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f26s);
        if (this.t != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.t);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.q);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.E);
        printWriter.print(" mStopped=");
        printWriter.print(this.F);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.G);
        if (this.D) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.D);
        }
    }

    public void W0(int i2, int i3) {
        if (i2 >= 0) {
            Y(new n(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public boolean X0() {
        return Y0(null, -1, 0);
    }

    void Y(m mVar, boolean z) {
        if (!z) {
            if (this.r == null) {
                if (!this.G) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            p();
        }
        synchronized (this.a) {
            if (this.r == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.a.add(mVar);
                l1();
            }
        }
    }

    boolean Z0(ArrayList arrayList, ArrayList arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList arrayList3 = this.d;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.d.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.d.get(size2);
                    if ((str != null && str.equals(aVar.C())) || (i2 >= 0 && i2 == aVar.v)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) this.d.get(size2);
                        if (str == null || !str.equals(aVar2.C())) {
                            if (i2 < 0 || i2 != aVar2.v) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.d.size() - 1) {
                return false;
            }
            for (int size3 = this.d.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.d.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    boolean a0(boolean z) {
        Z(z);
        boolean z2 = false;
        while (k0(this.I, this.J)) {
            z2 = true;
            this.b = true;
            try {
                e1(this.I, this.J);
                q();
            } catch (Throwable th) {
                q();
                throw th;
            }
        }
        t1();
        V();
        this.c.b();
        return z2;
    }

    void b0(m mVar, boolean z) {
        if (z && (this.r == null || this.G)) {
            return;
        }
        Z(z);
        if (mVar.a(this.I, this.J)) {
            this.b = true;
            try {
                e1(this.I, this.J);
                q();
            } catch (Throwable th) {
                q();
                throw th;
            }
        }
        t1();
        V();
        this.c.b();
    }

    public void b1(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            s1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    void c1(Fragment fragment, u.b bVar) {
        HashSet hashSet = (HashSet) this.m.get(fragment);
        if (hashSet != null && hashSet.remove(bVar) && hashSet.isEmpty()) {
            this.m.remove(fragment);
            if (fragment.mState < 5) {
                w(fragment);
                R0(fragment);
            }
        }
    }

    void d1(Fragment fragment) {
        if (F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("remove: ");
            sb.append(fragment);
            sb.append(" nesting=");
            sb.append(fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            this.c.s(fragment);
            if (G0(fragment)) {
                this.D = true;
            }
            fragment.mRemoving = true;
            p1(fragment);
        }
    }

    void e(androidx.fragment.app.a aVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(aVar);
    }

    void f(Fragment fragment, u.b bVar) {
        if (this.m.get(fragment) == null) {
            this.m.put(fragment, new HashSet());
        }
        ((HashSet) this.m.get(fragment)).add(bVar);
    }

    Fragment f0(String str) {
        return this.c.f(str);
    }

    void f1(Fragment fragment) {
        this.M.n(fragment);
    }

    v g(Fragment fragment) {
        if (F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("add: ");
            sb.append(fragment);
        }
        v vVarV = v(fragment);
        fragment.mFragmentManager = this;
        this.c.p(vVarV);
        if (!fragment.mDetached) {
            this.c.a(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (G0(fragment)) {
                this.D = true;
            }
        }
        return vVarV;
    }

    public Fragment g0(int i2) {
        return this.c.g(i2);
    }

    public void h(s sVar) {
        this.p.add(sVar);
    }

    public Fragment h0(String str) {
        return this.c.h(str);
    }

    void h1(Parcelable parcelable) {
        v vVar;
        if (parcelable == null) {
            return;
        }
        q qVar = (q) parcelable;
        if (qVar.a == null) {
            return;
        }
        this.c.t();
        for (u uVar : qVar.a) {
            if (uVar != null) {
                Fragment fragmentH = this.M.h(uVar.b);
                if (fragmentH != null) {
                    if (F0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("restoreSaveState: re-attaching retained ");
                        sb.append(fragmentH);
                    }
                    vVar = new v(this.o, this.c, fragmentH, uVar);
                } else {
                    vVar = new v(this.o, this.c, this.r.f().getClassLoader(), q0(), uVar);
                }
                Fragment fragmentK = vVar.k();
                fragmentK.mFragmentManager = this;
                if (F0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("restoreSaveState: active (");
                    sb2.append(fragmentK.mWho);
                    sb2.append("): ");
                    sb2.append(fragmentK);
                }
                vVar.o(this.r.f().getClassLoader());
                this.c.p(vVar);
                vVar.u(this.q);
            }
        }
        for (Fragment fragment : this.M.k()) {
            if (!this.c.c(fragment.mWho)) {
                if (F0(2)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Discarding retained Fragment ");
                    sb3.append(fragment);
                    sb3.append(" that was not found in the set of active Fragments ");
                    sb3.append(qVar.a);
                }
                this.M.n(fragment);
                fragment.mFragmentManager = this;
                v vVar2 = new v(this.o, this.c, fragment);
                vVar2.u(1);
                vVar2.m();
                fragment.mRemoving = true;
                vVar2.m();
            }
        }
        this.c.u(qVar.b);
        if (qVar.c != null) {
            this.d = new ArrayList(qVar.c.length);
            int i2 = 0;
            while (true) {
                androidx.fragment.app.b[] bVarArr = qVar.c;
                if (i2 >= bVarArr.length) {
                    break;
                }
                androidx.fragment.app.a aVarA = bVarArr[i2].a(this);
                if (F0(2)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("restoreAllState: back stack #");
                    sb4.append(i2);
                    sb4.append(" (index ");
                    sb4.append(aVarA.v);
                    sb4.append("): ");
                    sb4.append(aVarA);
                    PrintWriter printWriter = new PrintWriter(new i0("FragmentManager"));
                    aVarA.y("  ", printWriter, false);
                    printWriter.close();
                }
                this.d.add(aVarA);
                i2++;
            }
        } else {
            this.d = null;
        }
        this.i.set(qVar.d);
        String str = qVar.e;
        if (str != null) {
            Fragment fragmentF0 = f0(str);
            this.u = fragmentF0;
            L(fragmentF0);
        }
        ArrayList arrayList = qVar.f;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Bundle bundle = (Bundle) qVar.g.get(i3);
                bundle.setClassLoader(this.r.f().getClassLoader());
                this.j.put(arrayList.get(i3), bundle);
            }
        }
        this.C = new ArrayDeque(qVar.h);
    }

    void i(Fragment fragment) {
        this.M.f(fragment);
    }

    Fragment i0(String str) {
        return this.c.i(str);
    }

    int j() {
        return this.i.getAndIncrement();
    }

    Parcelable j1() {
        int size;
        j0();
        X();
        a0(true);
        this.E = true;
        this.M.o(true);
        ArrayList arrayListV = this.c.v();
        androidx.fragment.app.b[] bVarArr = null;
        if (arrayListV.isEmpty()) {
            F0(2);
            return null;
        }
        ArrayList arrayListW = this.c.w();
        ArrayList arrayList = this.d;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            bVarArr = new androidx.fragment.app.b[size];
            for (int i2 = 0; i2 < size; i2++) {
                bVarArr[i2] = new androidx.fragment.app.b((androidx.fragment.app.a) this.d.get(i2));
                if (F0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveAllState: adding back stack #");
                    sb.append(i2);
                    sb.append(": ");
                    sb.append(this.d.get(i2));
                }
            }
        }
        q qVar = new q();
        qVar.a = arrayListV;
        qVar.b = arrayListW;
        qVar.c = bVarArr;
        qVar.d = this.i.get();
        Fragment fragment = this.u;
        if (fragment != null) {
            qVar.e = fragment.mWho;
        }
        qVar.f.addAll(this.j.keySet());
        qVar.g.addAll(this.j.values());
        qVar.h = new ArrayList(this.C);
        return qVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void k(androidx.fragment.app.l lVar, androidx.fragment.app.g gVar, Fragment fragment) {
        String str;
        androidx.lifecycle.g gVar2;
        if (this.r != null) {
            throw new IllegalStateException("Already attached");
        }
        this.r = lVar;
        this.f26s = gVar;
        this.t = fragment;
        if (fragment != null) {
            h(new i(fragment));
        } else if (lVar instanceof s) {
            h((s) lVar);
        }
        if (this.t != null) {
            t1();
        }
        if (lVar instanceof androidx.activity.c) {
            androidx.activity.c cVar = (androidx.activity.c) lVar;
            OnBackPressedDispatcher onBackPressedDispatcher = cVar.getOnBackPressedDispatcher();
            this.g = onBackPressedDispatcher;
            if (fragment != null) {
                gVar2 = cVar;
                gVar2 = fragment;
            }
            gVar2 = cVar;
            onBackPressedDispatcher.a(gVar2, this.h);
        }
        if (fragment != null) {
            this.M = fragment.mFragmentManager.m0(fragment);
        } else if (lVar instanceof androidx.lifecycle.y) {
            this.M = r.j(((androidx.lifecycle.y) lVar).getViewModelStore());
        } else {
            this.M = new r(false);
        }
        this.M.o(K0());
        this.c.x(this.M);
        Object obj = this.r;
        if (obj instanceof androidx.activity.result.d) {
            ActivityResultRegistry activityResultRegistry = ((androidx.activity.result.d) obj).getActivityResultRegistry();
            if (fragment != null) {
                str = fragment.mWho + ":";
            } else {
                str = "";
            }
            String str2 = "FragmentManager:" + str;
            this.z = activityResultRegistry.j(str2 + "StartActivityForResult", new c.c(), new j());
            this.A = activityResultRegistry.j(str2 + "StartIntentSenderForResult", new k(), new a());
            this.B = activityResultRegistry.j(str2 + "RequestPermissions", new c.b(), new b());
        }
    }

    public Fragment.m k1(Fragment fragment) {
        v vVarM = this.c.m(fragment.mWho);
        if (vVarM == null || !vVarM.k().equals(fragment)) {
            s1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        return vVarM.r();
    }

    void l(Fragment fragment) {
        if (F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("attach: ");
            sb.append(fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            this.c.a(fragment);
            if (F0(2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("add from attach: ");
                sb2.append(fragment);
            }
            if (G0(fragment)) {
                this.D = true;
            }
        }
    }

    public int l0() {
        ArrayList arrayList = this.d;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    void l1() {
        synchronized (this.a) {
            ArrayList arrayList = this.L;
            boolean z = (arrayList == null || arrayList.isEmpty()) ? false : true;
            boolean z2 = this.a.size() == 1;
            if (z || z2) {
                this.r.g().removeCallbacks(this.N);
                this.r.g().post(this.N);
                t1();
            }
        }
    }

    public y m() {
        return new androidx.fragment.app.a(this);
    }

    void m1(Fragment fragment, boolean z) {
        ViewGroup viewGroupP0 = p0(fragment);
        if (viewGroupP0 == null || !(viewGroupP0 instanceof androidx.fragment.app.i)) {
            return;
        }
        ((androidx.fragment.app.i) viewGroupP0).setDrawDisappearingViewsLast(!z);
    }

    androidx.fragment.app.g n0() {
        return this.f26s;
    }

    void n1(Fragment fragment, androidx.lifecycle.d.c cVar) {
        if (fragment.equals(f0(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this)) {
            fragment.mMaxState = cVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    boolean o() {
        boolean zG0 = false;
        for (Fragment fragment : this.c.l()) {
            if (fragment != null) {
                zG0 = G0(fragment);
            }
            if (zG0) {
                return true;
            }
        }
        return false;
    }

    public Fragment o0(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragmentF0 = f0(string);
        if (fragmentF0 == null) {
            s1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragmentF0;
    }

    void o1(Fragment fragment) {
        if (fragment == null || (fragment.equals(f0(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this))) {
            Fragment fragment2 = this.u;
            this.u = fragment;
            L(fragment2);
            L(this.u);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public androidx.fragment.app.k q0() {
        androidx.fragment.app.k kVar = this.v;
        if (kVar != null) {
            return kVar;
        }
        Fragment fragment = this.t;
        return fragment != null ? fragment.mFragmentManager.q0() : this.w;
    }

    void q1(Fragment fragment) {
        if (F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("show: ");
            sb.append(fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    x r0() {
        return this.c;
    }

    public List s0() {
        return this.c.n();
    }

    void t(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.A(z3);
        } else {
            aVar.z();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.q >= 1) {
            z.B(this.r.f(), this.f26s, arrayList, arrayList2, 0, 1, true, this.n);
        }
        if (z3) {
            Q0(this.q, true);
        }
        for (Fragment fragment : this.c.l()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && aVar.D(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    androidx.fragment.app.l t0() {
        return this.r;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.t;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.t)));
            sb.append("}");
        } else {
            androidx.fragment.app.l lVar = this.r;
            if (lVar != null) {
                sb.append(lVar.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.r)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    LayoutInflater.Factory2 u0() {
        return this.f;
    }

    v v(Fragment fragment) {
        v vVarM = this.c.m(fragment.mWho);
        if (vVarM != null) {
            return vVarM;
        }
        v vVar = new v(this.o, this.c, fragment);
        vVar.o(this.r.f().getClassLoader());
        vVar.u(this.q);
        return vVar;
    }

    androidx.fragment.app.n v0() {
        return this.o;
    }

    Fragment w0() {
        return this.t;
    }

    void x(Fragment fragment) {
        if (F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("detach: ");
            sb.append(fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (F0(2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("remove from detach: ");
                sb2.append(fragment);
            }
            this.c.s(fragment);
            if (G0(fragment)) {
                this.D = true;
            }
            p1(fragment);
        }
    }

    public Fragment x0() {
        return this.u;
    }

    void y() {
        this.E = false;
        this.F = false;
        this.M.o(false);
        S(4);
    }

    k0 y0() {
        k0 k0Var = this.x;
        if (k0Var != null) {
            return k0Var;
        }
        Fragment fragment = this.t;
        return fragment != null ? fragment.mFragmentManager.y0() : this.y;
    }

    void z() {
        this.E = false;
        this.F = false;
        this.M.o(false);
        S(0);
    }

    static class l implements Parcelable {
        public static final Parcelable.Creator<l> CREATOR = new a();
        String a;
        int b;

        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public l createFromParcel(Parcel parcel) {
                return new l(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public l[] newArray(int i) {
                return new l[i];
            }
        }

        l(String str, int i) {
            this.a = str;
            this.b = i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeInt(this.b);
        }

        l(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readInt();
        }
    }
}
