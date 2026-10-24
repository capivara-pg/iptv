package androidx.fragment.app;

import android.content.Context;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.m0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class y {
    private static final int[] a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    static final a0 b = new z();
    static final a0 c = w();

    class a implements Runnable {
        final /* synthetic */ g a;
        final /* synthetic */ Fragment b;
        final /* synthetic */ z.c c;

        a(g gVar, Fragment fragment, z.c cVar) {
            this.a = gVar;
            this.b = fragment;
            this.c = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    class b implements Runnable {
        final /* synthetic */ ArrayList a;

        b(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            y.A(this.a, 4);
        }
    }

    class c implements Runnable {
        final /* synthetic */ g a;
        final /* synthetic */ Fragment b;
        final /* synthetic */ z.c c;

        c(g gVar, Fragment fragment, z.c cVar) {
            this.a = gVar;
            this.b = fragment;
            this.c = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    class d implements Runnable {
        final /* synthetic */ Object a;
        final /* synthetic */ a0 b;
        final /* synthetic */ View c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ ArrayList e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ Object h;

        d(Object obj, a0 a0Var, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.a = obj;
            this.b = a0Var;
            this.c = view;
            this.d = fragment;
            this.e = arrayList;
            this.f = arrayList2;
            this.g = arrayList3;
            this.h = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.a;
            if (obj != null) {
                this.b.p(obj, this.c);
                this.f.addAll(y.k(this.b, this.a, this.d, this.e, this.c));
            }
            if (this.g != null) {
                if (this.h != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(this.c);
                    this.b.q(this.h, this.g, arrayList);
                }
                this.g.clear();
                this.g.add(this.c);
            }
        }
    }

    class e implements Runnable {
        final /* synthetic */ Fragment a;
        final /* synthetic */ Fragment b;
        final /* synthetic */ boolean c;
        final /* synthetic */ androidx.collection.a d;
        final /* synthetic */ View e;
        final /* synthetic */ a0 f;
        final /* synthetic */ Rect g;

        e(Fragment fragment, Fragment fragment2, boolean z, androidx.collection.a aVar, View view, a0 a0Var, Rect rect) {
            this.a = fragment;
            this.b = fragment2;
            this.c = z;
            this.d = aVar;
            this.e = view;
            this.f = a0Var;
            this.g = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            y.f(this.a, this.b, this.c, this.d, false);
            View view = this.e;
            if (view != null) {
                this.f.k(view, this.g);
            }
        }
    }

    class f implements Runnable {
        final /* synthetic */ a0 a;
        final /* synthetic */ androidx.collection.a b;
        final /* synthetic */ Object c;
        final /* synthetic */ h d;
        final /* synthetic */ ArrayList e;
        final /* synthetic */ View f;
        final /* synthetic */ Fragment g;
        final /* synthetic */ Fragment h;
        final /* synthetic */ boolean i;
        final /* synthetic */ ArrayList j;
        final /* synthetic */ Object k;
        final /* synthetic */ Rect l;

        f(a0 a0Var, androidx.collection.a aVar, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.a = a0Var;
            this.b = aVar;
            this.c = obj;
            this.d = hVar;
            this.e = arrayList;
            this.f = view;
            this.g = fragment;
            this.h = fragment2;
            this.i = z;
            this.j = arrayList2;
            this.k = obj2;
            this.l = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            androidx.collection.a aVarH = y.h(this.a, this.b, this.c, this.d);
            if (aVarH != null) {
                this.e.addAll(aVarH.values());
                this.e.add(this.f);
            }
            y.f(this.g, this.h, this.i, aVarH, false);
            Object obj = this.c;
            if (obj != null) {
                this.a.A(obj, this.j, this.e);
                View viewS = y.s(aVarH, this.d, this.k, this.i);
                if (viewS != null) {
                    this.a.k(viewS, this.l);
                }
            }
        }
    }

    interface g {
        void a(Fragment fragment, z.c cVar);

        void b(Fragment fragment, z.c cVar);
    }

    static class h {
        public Fragment a;
        public boolean b;
        public androidx.fragment.app.a c;
        public Fragment d;
        public boolean e;
        public androidx.fragment.app.a f;

        h() {
        }
    }

    static void A(ArrayList arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((View) arrayList.get(size)).setVisibility(i);
        }
    }

    static void B(Context context, androidx.fragment.app.g gVar, ArrayList arrayList, ArrayList arrayList2, int i, int i2, boolean z, g gVar2) {
        ViewGroup viewGroup;
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i3);
            if (((Boolean) arrayList2.get(i3)).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                androidx.collection.a aVarD = d(iKeyAt, arrayList, arrayList2, i, i2);
                h hVar = (h) sparseArray.valueAt(i4);
                if (gVar.d() && (viewGroup = (ViewGroup) gVar.c(iKeyAt)) != null) {
                    if (z) {
                        o(viewGroup, hVar, view, aVarD, gVar2);
                    } else {
                        n(viewGroup, hVar, view, aVarD, gVar2);
                    }
                }
            }
        }
    }

    private static void a(ArrayList arrayList, androidx.collection.a aVar, Collection collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View view = (View) aVar.valueAt(size);
            if (collection.contains(m0.J(view))) {
                arrayList.add(view);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:56:0x006e  */
    /* JADX WARN: Code duplicated, block: B:57:0x0070  */
    /* JADX WARN: Code duplicated, block: B:64:0x007f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x0081  */
    /* JADX WARN: Code duplicated, block: B:66:0x0084  */
    /* JADX WARN: Code duplicated, block: B:70:0x008c  */
    /* JADX WARN: Code duplicated, block: B:71:0x008e  */
    private static void b(androidx.fragment.app.a aVar, x.a aVar2, SparseArray sparseArray, boolean z, boolean z2) {
        int i;
        boolean z3;
        boolean z4;
        boolean z5;
        View view;
        boolean z6;
        Fragment fragment = aVar2.b;
        if (fragment == null || (i = fragment.mContainerId) == 0) {
            return;
        }
        int i2 = z ? a[aVar2.a] : aVar2.a;
        boolean z7 = false;
        boolean z8 = true;
        if (i2 == 1) {
            if (z2) {
                z3 = fragment.mIsNewlyAdded;
            } else if (!fragment.mAdded || fragment.mHidden) {
                z3 = false;
            } else {
                z3 = true;
            }
            z7 = z3;
            z4 = false;
            z5 = false;
        } else if (i2 == 3) {
            if (z2 ? !fragment.mAdded || fragment.mHidden : fragment.mAdded || (view = fragment.mView) == null || view.getVisibility() != 0 || fragment.mPostponedAlpha < 0.0f) {
                z6 = false;
            } else {
                z6 = true;
            }
            z5 = z6;
            z4 = true;
            z8 = false;
        } else if (i2 != 4) {
            if (i2 != 5) {
                if (i2 != 6) {
                    if (i2 != 7) {
                        z4 = false;
                        z8 = false;
                    } else {
                        if (z2) {
                            z3 = fragment.mIsNewlyAdded;
                        } else {
                            if (fragment.mAdded) {
                            }
                            z3 = false;
                        }
                        z7 = z3;
                        z4 = false;
                    }
                }
                if (z2) {
                    z6 = false;
                } else {
                    z6 = false;
                }
                z5 = z6;
                z4 = true;
                z8 = false;
            } else {
                if (!z2) {
                    z3 = fragment.mHidden;
                } else if (fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z7 = z3;
                z4 = false;
            }
            z5 = false;
        } else {
            if (!z2 ? !(!fragment.mAdded || fragment.mHidden) : fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden) {
                z6 = false;
            } else {
                z6 = true;
            }
            z5 = z6;
            z4 = true;
            z8 = false;
        }
        h hVarP = (h) sparseArray.get(i);
        if (z7) {
            hVarP = p(hVarP, sparseArray, i);
            hVarP.a = fragment;
            hVarP.b = z;
            hVarP.c = aVar;
        }
        if (!z2 && z8) {
            if (hVarP != null && hVarP.d == fragment) {
                hVarP.d = null;
            }
            if (!aVar.r) {
                n nVar = aVar.t;
                nVar.r0().p(nVar.v(fragment));
                nVar.R0(fragment);
            }
        }
        if (z5 && (hVarP == null || hVarP.d == null)) {
            hVarP = p(hVarP, sparseArray, i);
            hVarP.d = fragment;
            hVarP.e = z;
            hVarP.f = aVar;
        }
        if (z2 || !z4 || hVarP == null || hVarP.a != fragment) {
            return;
        }
        hVarP.a = null;
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray sparseArray, boolean z) {
        int size = aVar.c.size();
        for (int i = 0; i < size; i++) {
            b(aVar, (x.a) aVar.c.get(i), sparseArray, false, z);
        }
    }

    private static androidx.collection.a d(int i, ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        ArrayList arrayList3;
        ArrayList arrayList4;
        androidx.collection.a aVar = new androidx.collection.a();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) arrayList.get(i4);
            if (aVar2.E(i)) {
                boolean zBooleanValue = ((Boolean) arrayList2.get(i4)).booleanValue();
                ArrayList arrayList5 = aVar2.p;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.p;
                        arrayList4 = aVar2.q;
                    } else {
                        ArrayList arrayList6 = aVar2.p;
                        arrayList3 = aVar2.q;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = (String) arrayList4.get(i5);
                        String str2 = (String) arrayList3.get(i5);
                        String str3 = (String) aVar.remove(str2);
                        if (str3 != null) {
                            aVar.put(str, str3);
                        } else {
                            aVar.put(str, str2);
                        }
                    }
                }
            }
        }
        return aVar;
    }

    public static void e(androidx.fragment.app.a aVar, SparseArray sparseArray, boolean z) {
        if (aVar.t.n0().d()) {
            for (int size = aVar.c.size() - 1; size >= 0; size--) {
                b(aVar, (x.a) aVar.c.get(size), sparseArray, true, z);
            }
        }
    }

    static void f(Fragment fragment, Fragment fragment2, boolean z, androidx.collection.a aVar, boolean z2) {
        if (z) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    private static boolean g(a0 a0Var, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!a0Var.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    static androidx.collection.a h(a0 a0Var, androidx.collection.a aVar, Object obj, h hVar) {
        ArrayList arrayList;
        Fragment fragment = hVar.a;
        View view = fragment.getView();
        if (aVar.isEmpty() || obj == null || view == null) {
            aVar.clear();
            return null;
        }
        androidx.collection.a aVar2 = new androidx.collection.a();
        a0Var.j(aVar2, view);
        androidx.fragment.app.a aVar3 = hVar.c;
        if (hVar.b) {
            fragment.getExitTransitionCallback();
            arrayList = aVar3.p;
        } else {
            fragment.getEnterTransitionCallback();
            arrayList = aVar3.q;
        }
        if (arrayList != null) {
            aVar2.retainAll(arrayList);
            aVar2.retainAll(aVar.values());
        }
        x(aVar, aVar2);
        return aVar2;
    }

    private static androidx.collection.a i(a0 a0Var, androidx.collection.a aVar, Object obj, h hVar) {
        ArrayList arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = hVar.d;
        androidx.collection.a aVar2 = new androidx.collection.a();
        a0Var.j(aVar2, fragment.requireView());
        androidx.fragment.app.a aVar3 = hVar.f;
        if (hVar.e) {
            fragment.getEnterTransitionCallback();
            arrayList = aVar3.q;
        } else {
            fragment.getExitTransitionCallback();
            arrayList = aVar3.p;
        }
        if (arrayList != null) {
            aVar2.retainAll(arrayList);
        }
        aVar.retainAll(aVar2.keySet());
        return aVar2;
    }

    private static a0 j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        a0 a0Var = b;
        if (a0Var != null && g(a0Var, arrayList)) {
            return a0Var;
        }
        a0 a0Var2 = c;
        if (a0Var2 != null && g(a0Var2, arrayList)) {
            return a0Var2;
        }
        if (a0Var == null && a0Var2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList k(a0 a0Var, Object obj, Fragment fragment, ArrayList arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        View view2 = fragment.getView();
        if (view2 != null) {
            a0Var.f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        a0Var.b(obj, arrayList2);
        return arrayList2;
    }

    private static Object l(a0 a0Var, ViewGroup viewGroup, View view, androidx.collection.a aVar, h hVar, ArrayList arrayList, ArrayList arrayList2, Object obj, Object obj2) {
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.b;
        Object objT = aVar.isEmpty() ? null : t(a0Var, fragment, fragment2, z);
        androidx.collection.a aVarI = i(a0Var, aVar, objT, hVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(aVarI.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, aVarI, true);
        if (obj3 != null) {
            rect = new Rect();
            a0Var.z(obj3, view, arrayList);
            z(a0Var, obj3, obj2, aVarI, hVar.e, hVar.f);
            if (obj != null) {
                a0Var.u(obj, rect);
            }
        } else {
            rect = null;
        }
        androidx.core.view.c0.a(viewGroup, new f(a0Var, aVar, obj3, hVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(a0 a0Var, ViewGroup viewGroup, View view, androidx.collection.a aVar, h hVar, ArrayList arrayList, ArrayList arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.b;
        Object objT = aVar.isEmpty() ? null : t(a0Var, fragment, fragment2, z);
        androidx.collection.a aVarI = i(a0Var, aVar, objT, hVar);
        androidx.collection.a aVarH = h(a0Var, aVar, objT, hVar);
        if (aVar.isEmpty()) {
            if (aVarI != null) {
                aVarI.clear();
            }
            if (aVarH != null) {
                aVarH.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, aVarI, aVar.keySet());
            a(arrayList2, aVarH, aVar.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, aVarI, true);
        if (obj3 != null) {
            arrayList2.add(view);
            a0Var.z(obj3, view, arrayList);
            z(a0Var, obj3, obj2, aVarI, hVar.e, hVar.f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, hVar, obj, z);
            if (viewS != null) {
                a0Var.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        androidx.core.view.c0.a(viewGroup, new e(fragment, fragment2, z, aVarH, view2, a0Var, rect));
        return obj3;
    }

    private static void n(ViewGroup viewGroup, h hVar, View view, androidx.collection.a aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.d;
        a0 a0VarJ = j(fragment2, fragment);
        if (a0VarJ == null) {
            return;
        }
        boolean z = hVar.b;
        boolean z2 = hVar.e;
        Object objQ = q(a0VarJ, fragment, z);
        Object objR = r(a0VarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Object objL = l(a0VarJ, viewGroup, view, aVar, hVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList arrayListK = k(a0VarJ, obj, fragment2, arrayList, view);
        if (arrayListK == null || arrayListK.isEmpty()) {
            obj = null;
        }
        Object obj2 = obj;
        a0VarJ.a(objQ, view);
        Object objU = u(a0VarJ, objQ, obj2, objL, fragment, hVar.b);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList.size() > 0)) {
            z.c cVar = new z.c();
            gVar.b(fragment2, cVar);
            a0VarJ.w(fragment2, objU, cVar, new c(gVar, fragment2, cVar));
        }
        if (objU != null) {
            ArrayList arrayList3 = new ArrayList();
            a0VarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(a0VarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            a0VarJ.x(viewGroup, arrayList2, aVar);
            a0VarJ.c(viewGroup, objU);
            a0VarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    private static void o(ViewGroup viewGroup, h hVar, View view, androidx.collection.a aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.d;
        a0 a0VarJ = j(fragment2, fragment);
        if (a0VarJ == null) {
            return;
        }
        boolean z = hVar.b;
        boolean z2 = hVar.e;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Object objQ = q(a0VarJ, fragment, z);
        Object objR = r(a0VarJ, fragment2, z2);
        Object objM = m(a0VarJ, viewGroup, view, aVar, hVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList arrayListK = k(a0VarJ, obj, fragment2, arrayList2, view);
        ArrayList arrayListK2 = k(a0VarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(a0VarJ, objQ, obj, objM, fragment, z);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList2.size() > 0)) {
            z.c cVar = new z.c();
            gVar.b(fragment2, cVar);
            a0VarJ.w(fragment2, objU, cVar, new a(gVar, fragment2, cVar));
        }
        if (objU != null) {
            v(a0VarJ, obj, fragment2, arrayListK);
            ArrayList arrayListO = a0VarJ.o(arrayList);
            a0VarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            a0VarJ.c(viewGroup, objU);
            a0VarJ.y(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            a0VarJ.A(objM, arrayList2, arrayList);
        }
    }

    private static h p(h hVar, SparseArray sparseArray, int i) {
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        sparseArray.put(i, hVar2);
        return hVar2;
    }

    private static Object q(a0 a0Var, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return a0Var.g(z ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    private static Object r(a0 a0Var, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return a0Var.g(z ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    static View s(androidx.collection.a aVar, h hVar, Object obj, boolean z) {
        ArrayList arrayList;
        androidx.fragment.app.a aVar2 = hVar.c;
        if (obj == null || aVar == null || (arrayList = aVar2.p) == null || arrayList.isEmpty()) {
            return null;
        }
        return (View) aVar.get(z ? (String) aVar2.p.get(0) : (String) aVar2.q.get(0));
    }

    private static Object t(a0 a0Var, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return a0Var.B(a0Var.g(z ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    private static Object u(a0 a0Var, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean allowReturnTransitionOverlap;
        if (obj == null || obj2 == null || fragment == null) {
            allowReturnTransitionOverlap = true;
        } else {
            allowReturnTransitionOverlap = z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap();
        }
        return allowReturnTransitionOverlap ? a0Var.n(obj2, obj, obj3) : a0Var.m(obj2, obj, obj3);
    }

    private static void v(a0 a0Var, Object obj, Fragment fragment, ArrayList arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            a0Var.r(obj, fragment.getView(), arrayList);
            androidx.core.view.c0.a(fragment.mContainer, new b(arrayList));
        }
    }

    private static a0 w() {
        try {
            return (a0) androidx.transition.e.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static void x(androidx.collection.a aVar, androidx.collection.a aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey((String) aVar.valueAt(size))) {
                aVar.removeAt(size);
            }
        }
    }

    private static void y(a0 a0Var, ViewGroup viewGroup, Fragment fragment, View view, ArrayList arrayList, Object obj, ArrayList arrayList2, Object obj2, ArrayList arrayList3) {
        androidx.core.view.c0.a(viewGroup, new d(obj, a0Var, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static void z(a0 a0Var, Object obj, Object obj2, androidx.collection.a aVar, boolean z, androidx.fragment.app.a aVar2) {
        ArrayList arrayList = aVar2.p;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = (View) aVar.get(z ? (String) aVar2.q.get(0) : (String) aVar2.p.get(0));
        a0Var.v(obj, view);
        if (obj2 != null) {
            a0Var.v(obj2, view);
        }
    }
}
