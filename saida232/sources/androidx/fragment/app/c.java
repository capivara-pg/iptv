package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.core.view.c1;
import androidx.core.view.r1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class c extends j0 {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[j0.e.c.values().length];
            a = iArr;
            try {
                iArr[j0.e.c.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[j0.e.c.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[j0.e.c.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[j0.e.c.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ List a;
        final /* synthetic */ j0.e b;

        b(List list, j0.e eVar) {
            this.a = list;
            this.b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.contains(this.b)) {
                this.a.remove(this.b);
                c.this.s(this.b);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.c$c, reason: collision with other inner class name */
    class C0029c extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;
        final /* synthetic */ boolean c;
        final /* synthetic */ j0.e d;
        final /* synthetic */ k e;

        C0029c(ViewGroup viewGroup, View view, boolean z, j0.e eVar, k kVar) {
            this.a = viewGroup;
            this.b = view;
            this.c = z;
            this.d = eVar;
            this.e = kVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.endViewTransition(this.b);
            if (this.c) {
                this.d.e().a(this.b);
            }
            this.e.a();
        }
    }

    class d implements u.b.a {
        final /* synthetic */ Animator a;

        d(Animator animator) {
            this.a = animator;
        }

        public void a() {
            this.a.end();
        }
    }

    class e implements Animation.AnimationListener {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;
        final /* synthetic */ k c;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                eVar.a.endViewTransition(eVar.b);
                e.this.c.a();
            }
        }

        e(ViewGroup viewGroup, View view, k kVar) {
            this.a = viewGroup;
            this.b = view;
            this.c = kVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    class f implements u.b.a {
        final /* synthetic */ View a;
        final /* synthetic */ ViewGroup b;
        final /* synthetic */ k c;

        f(View view, ViewGroup viewGroup, k kVar) {
            this.a = view;
            this.b = viewGroup;
            this.c = kVar;
        }

        public void a() {
            this.a.clearAnimation();
            this.b.endViewTransition(this.a);
            this.c.a();
        }
    }

    class g implements Runnable {
        final /* synthetic */ j0.e a;
        final /* synthetic */ j0.e b;
        final /* synthetic */ boolean c;
        final /* synthetic */ androidx.collection.a d;

        g(j0.e eVar, j0.e eVar2, boolean z, androidx.collection.a aVar) {
            this.a = eVar;
            this.b = eVar2;
            this.c = z;
            this.d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            z.f(this.a.f(), this.b.f(), this.c, this.d, false);
        }
    }

    class h implements Runnable {
        final /* synthetic */ g0 a;
        final /* synthetic */ View b;
        final /* synthetic */ Rect c;

        h(g0 g0Var, View view, Rect rect) {
            this.a = g0Var;
            this.b = view;
            this.c = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.k(this.b, this.c);
        }
    }

    class i implements Runnable {
        final /* synthetic */ ArrayList a;

        i(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            z.A(this.a, 4);
        }
    }

    class j implements Runnable {
        final /* synthetic */ m a;

        j(m mVar) {
            this.a = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a();
        }
    }

    private static class k extends l {
        private boolean c;
        private boolean d;
        private androidx.fragment.app.f.d e;

        k(j0.e eVar, u.b bVar, boolean z) {
            super(eVar, bVar);
            this.d = false;
            this.c = z;
        }

        androidx.fragment.app.f.d e(Context context) {
            if (this.d) {
                return this.e;
            }
            androidx.fragment.app.f.d dVarC = androidx.fragment.app.f.c(context, b().f(), b().e() == j0.e.c.VISIBLE, this.c);
            this.e = dVarC;
            this.d = true;
            return dVarC;
        }
    }

    private static class l {
        private final j0.e a;
        private final u.b b;

        l(j0.e eVar, u.b bVar) {
            this.a = eVar;
            this.b = bVar;
        }

        void a() {
            this.a.d(this.b);
        }

        j0.e b() {
            return this.a;
        }

        u.b c() {
            return this.b;
        }

        boolean d() {
            j0.e.c cVar;
            j0.e.c cVarC = j0.e.c.c(this.a.f().mView);
            j0.e.c cVarE = this.a.e();
            return cVarC == cVarE || !(cVarC == (cVar = j0.e.c.VISIBLE) || cVarE == cVar);
        }
    }

    private static class m extends l {
        private final Object c;
        private final boolean d;
        private final Object e;

        m(j0.e eVar, u.b bVar, boolean z, boolean z2) {
            super(eVar, bVar);
            if (eVar.e() == j0.e.c.VISIBLE) {
                this.c = z ? eVar.f().getReenterTransition() : eVar.f().getEnterTransition();
                this.d = z ? eVar.f().getAllowReturnTransitionOverlap() : eVar.f().getAllowEnterTransitionOverlap();
            } else {
                this.c = z ? eVar.f().getReturnTransition() : eVar.f().getExitTransition();
                this.d = true;
            }
            if (!z2) {
                this.e = null;
            } else if (z) {
                this.e = eVar.f().getSharedElementReturnTransition();
            } else {
                this.e = eVar.f().getSharedElementEnterTransition();
            }
        }

        private g0 f(Object obj) {
            if (obj == null) {
                return null;
            }
            g0 g0Var = z.b;
            if (g0Var != null && g0Var.e(obj)) {
                return g0Var;
            }
            g0 g0Var2 = z.c;
            if (g0Var2 != null && g0Var2.e(obj)) {
                return g0Var2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + b().f() + " is not a valid framework Transition or AndroidX Transition");
        }

        g0 e() {
            g0 g0VarF = f(this.c);
            g0 g0VarF2 = f(this.e);
            if (g0VarF == null || g0VarF2 == null || g0VarF == g0VarF2) {
                return g0VarF != null ? g0VarF : g0VarF2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + b().f() + " returned Transition " + this.c + " which uses a different Transition  type than its shared element transition " + this.e);
        }

        public Object g() {
            return this.e;
        }

        Object h() {
            return this.c;
        }

        public boolean i() {
            return this.e != null;
        }

        boolean j() {
            return this.d;
        }
    }

    c(ViewGroup viewGroup) {
        super(viewGroup);
    }

    private void w(List list, List list2, boolean z, Map map) {
        ViewGroup viewGroupM = m();
        Context context = viewGroupM.getContext();
        ArrayList<k> arrayList = new ArrayList();
        Iterator it = list.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            k kVar = (k) it.next();
            if (kVar.d()) {
                kVar.a();
            } else {
                androidx.fragment.app.f.d dVarE = kVar.e(context);
                if (dVarE == null) {
                    kVar.a();
                } else {
                    Animator animator = dVarE.b;
                    if (animator == null) {
                        arrayList.add(kVar);
                    } else {
                        j0.e eVarB = kVar.b();
                        Fragment fragmentF = eVarB.f();
                        if (Boolean.TRUE.equals(map.get(eVarB))) {
                            if (o.F0(2)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Ignoring Animator set on ");
                                sb.append(fragmentF);
                                sb.append(" as this Fragment was involved in a Transition.");
                            }
                            kVar.a();
                        } else {
                            boolean z3 = eVarB.e() == j0.e.c.GONE;
                            if (z3) {
                                list2.remove(eVarB);
                            }
                            View view = fragmentF.mView;
                            viewGroupM.startViewTransition(view);
                            animator.addListener(new C0029c(viewGroupM, view, z3, eVarB, kVar));
                            animator.setTarget(view);
                            animator.start();
                            kVar.c().c(new d(animator));
                            z2 = true;
                        }
                    }
                }
            }
        }
        for (k kVar2 : arrayList) {
            j0.e eVarB2 = kVar2.b();
            Fragment fragmentF2 = eVarB2.f();
            if (z) {
                if (o.F0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Ignoring Animation set on ");
                    sb2.append(fragmentF2);
                    sb2.append(" as Animations cannot run alongside Transitions.");
                }
                kVar2.a();
            } else if (z2) {
                if (o.F0(2)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Ignoring Animation set on ");
                    sb3.append(fragmentF2);
                    sb3.append(" as Animations cannot run alongside Animators.");
                }
                kVar2.a();
            } else {
                View view2 = fragmentF2.mView;
                Animation animation = (Animation) x.h.e(((androidx.fragment.app.f.d) x.h.e(kVar2.e(context))).a);
                if (eVarB2.e() != j0.e.c.REMOVED) {
                    view2.startAnimation(animation);
                    kVar2.a();
                } else {
                    viewGroupM.startViewTransition(view2);
                    androidx.fragment.app.f.e eVar = new androidx.fragment.app.f.e(animation, viewGroupM, view2);
                    eVar.setAnimationListener(new e(viewGroupM, view2, kVar2));
                    view2.startAnimation(eVar);
                }
                kVar2.c().c(new f(view2, viewGroupM, kVar2));
            }
        }
    }

    private Map x(List list, List list2, boolean z, j0.e eVar, j0.e eVar2) {
        View view;
        Object obj;
        ArrayList arrayList;
        Object obj2;
        ArrayList arrayList2;
        j0.e eVar3;
        j0.e eVar4;
        View view2;
        Object objN;
        ArrayList arrayList3;
        j0.e eVar5;
        j0.e eVar6;
        View view3;
        j0.e eVar7 = eVar;
        j0.e eVar8 = eVar2;
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        g0 g0Var = null;
        while (it.hasNext()) {
            m mVar = (m) it.next();
            if (!mVar.d()) {
                g0 g0VarE = mVar.e();
                if (g0Var == null) {
                    g0Var = g0VarE;
                } else if (g0VarE != null && g0Var != g0VarE) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + mVar.b().f() + " returned Transition " + mVar.h() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (g0Var == null) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                m mVar2 = (m) it2.next();
                map.put(mVar2.b(), Boolean.FALSE);
                mVar2.a();
            }
            return map;
        }
        View view4 = new View(m().getContext());
        Rect rect = new Rect();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        androidx.collection.a aVar = new androidx.collection.a();
        Iterator it3 = list.iterator();
        Object obj3 = null;
        View view5 = null;
        boolean z2 = false;
        while (it3.hasNext()) {
            m mVar3 = (m) it3.next();
            if (!mVar3.i() || eVar7 == null || eVar8 == null) {
                arrayList3 = arrayList5;
                eVar5 = eVar7;
                eVar6 = eVar8;
                view5 = view5;
            } else {
                Object objB = g0Var.B(g0Var.g(mVar3.g()));
                ArrayList<String> sharedElementSourceNames = eVar2.f().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = eVar.f().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = eVar.f().getSharedElementTargetNames();
                View view6 = view5;
                int i2 = 0;
                while (i2 < sharedElementTargetNames.size()) {
                    int iIndexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i2));
                    ArrayList<String> arrayList6 = sharedElementTargetNames;
                    if (iIndexOf != -1) {
                        sharedElementSourceNames.set(iIndexOf, sharedElementSourceNames2.get(i2));
                    }
                    i2++;
                    sharedElementTargetNames = arrayList6;
                }
                ArrayList<String> sharedElementTargetNames2 = eVar2.f().getSharedElementTargetNames();
                if (z == 0) {
                    eVar.f().getExitTransitionCallback();
                    eVar2.f().getEnterTransitionCallback();
                } else {
                    eVar.f().getEnterTransitionCallback();
                    eVar2.f().getExitTransitionCallback();
                }
                int i3 = 0;
                for (int size = sharedElementSourceNames.size(); i3 < size; size = size) {
                    aVar.put(sharedElementSourceNames.get(i3), sharedElementTargetNames2.get(i3));
                    i3++;
                }
                androidx.collection.a aVar2 = new androidx.collection.a();
                u(aVar2, eVar.f().mView);
                aVar2.retainAll(sharedElementSourceNames);
                aVar.retainAll(aVar2.keySet());
                androidx.collection.a aVar3 = new androidx.collection.a();
                u(aVar3, eVar2.f().mView);
                aVar3.retainAll(sharedElementTargetNames2);
                aVar3.retainAll(aVar.values());
                z.x(aVar, aVar3);
                v(aVar2, aVar.keySet());
                v(aVar3, aVar.values());
                if (aVar.isEmpty()) {
                    arrayList4.clear();
                    arrayList5.clear();
                    arrayList3 = arrayList5;
                    eVar5 = eVar7;
                    view5 = view6;
                    obj3 = null;
                    eVar6 = eVar8;
                } else {
                    z.f(eVar2.f(), eVar.f(), z, aVar2, true);
                    ArrayList arrayList7 = arrayList5;
                    androidx.core.view.d0.a(m(), new g(eVar2, eVar, z, aVar3));
                    arrayList4.addAll(aVar2.values());
                    if (sharedElementSourceNames.isEmpty()) {
                        view5 = view6;
                    } else {
                        View view7 = (View) aVar2.get((String) sharedElementSourceNames.get(0));
                        g0Var.v(objB, view7);
                        view5 = view7;
                    }
                    arrayList3 = arrayList7;
                    arrayList3.addAll(aVar3.values());
                    if (!sharedElementTargetNames2.isEmpty() && (view3 = (View) aVar3.get((String) sharedElementTargetNames2.get(0))) != null) {
                        androidx.core.view.d0.a(m(), new h(g0Var, view3, rect));
                        z2 = true;
                    }
                    g0Var.z(objB, view4, arrayList4);
                    g0Var.t(objB, null, null, null, null, objB, arrayList3);
                    Boolean bool = Boolean.TRUE;
                    eVar5 = eVar;
                    map.put(eVar5, bool);
                    eVar6 = eVar2;
                    map.put(eVar6, bool);
                    obj3 = objB;
                }
            }
            eVar7 = eVar5;
            arrayList4 = arrayList4;
            rect = rect;
            view4 = view4;
            eVar8 = eVar6;
            aVar = aVar;
            arrayList5 = arrayList3;
            g0Var = g0Var;
        }
        View view8 = view5;
        androidx.collection.a aVar4 = aVar;
        ArrayList arrayList8 = arrayList5;
        j0.e eVar9 = eVar7;
        ArrayList arrayList9 = arrayList4;
        Rect rect2 = rect;
        View view9 = view4;
        g0 g0Var2 = g0Var;
        j0.e eVar10 = eVar8;
        ArrayList arrayList10 = new ArrayList();
        Iterator it4 = list.iterator();
        Object obj4 = null;
        Object objN2 = null;
        while (it4.hasNext()) {
            m mVar4 = (m) it4.next();
            if (mVar4.d()) {
                map.put(mVar4.b(), Boolean.FALSE);
                mVar4.a();
            } else {
                Object objG = g0Var2.g(mVar4.h());
                j0.e eVarB = mVar4.b();
                boolean z3 = obj3 != null && (eVarB == eVar9 || eVarB == eVar10);
                if (objG == null) {
                    if (!z3) {
                        map.put(eVarB, Boolean.FALSE);
                        mVar4.a();
                    }
                    arrayList2 = arrayList8;
                    arrayList = arrayList9;
                    view = view9;
                    objN = obj4;
                    eVar3 = eVar10;
                    view2 = view8;
                } else {
                    ArrayList arrayList11 = new ArrayList();
                    Object obj5 = obj4;
                    t(arrayList11, eVarB.f().mView);
                    if (z3) {
                        if (eVarB == eVar9) {
                            arrayList11.removeAll(arrayList9);
                        } else {
                            arrayList11.removeAll(arrayList8);
                        }
                    }
                    if (arrayList11.isEmpty()) {
                        g0Var2.a(objG, view9);
                        arrayList2 = arrayList8;
                        arrayList = arrayList9;
                        view = view9;
                        eVar4 = eVarB;
                        obj2 = objN2;
                        eVar3 = eVar10;
                        obj = obj5;
                    } else {
                        g0Var2.b(objG, arrayList11);
                        view = view9;
                        obj = obj5;
                        arrayList = arrayList9;
                        obj2 = objN2;
                        arrayList2 = arrayList8;
                        eVar3 = eVar10;
                        g0Var2.t(objG, objG, arrayList11, null, null, null, null);
                        if (eVarB.e() == j0.e.c.GONE) {
                            eVar4 = eVarB;
                            list2.remove(eVar4);
                            ArrayList arrayList12 = new ArrayList(arrayList11);
                            arrayList12.remove(eVar4.f().mView);
                            g0Var2.r(objG, eVar4.f().mView, arrayList12);
                            androidx.core.view.d0.a(m(), new i(arrayList11));
                        } else {
                            eVar4 = eVarB;
                        }
                    }
                    if (eVar4.e() == j0.e.c.VISIBLE) {
                        arrayList10.addAll(arrayList11);
                        if (z2) {
                            g0Var2.u(objG, rect2);
                        }
                        view2 = view8;
                    } else {
                        view2 = view8;
                        g0Var2.v(objG, view2);
                    }
                    map.put(eVar4, Boolean.TRUE);
                    if (mVar4.j()) {
                        objN2 = g0Var2.n(obj2, objG, null);
                        objN = obj;
                    } else {
                        objN = g0Var2.n(obj, objG, null);
                        objN2 = obj2;
                    }
                }
                eVar10 = eVar3;
                obj4 = objN;
                view8 = view2;
                view9 = view;
                arrayList9 = arrayList;
                arrayList8 = arrayList2;
            }
        }
        ArrayList arrayList13 = arrayList8;
        ArrayList arrayList14 = arrayList9;
        j0.e eVar11 = eVar10;
        Object objM = g0Var2.m(objN2, obj4, obj3);
        Iterator it5 = list.iterator();
        while (it5.hasNext()) {
            m mVar5 = (m) it5.next();
            if (!mVar5.d()) {
                Object objH = mVar5.h();
                j0.e eVarB2 = mVar5.b();
                boolean z4 = obj3 != null && (eVarB2 == eVar9 || eVarB2 == eVar11);
                if (objH != null || z4) {
                    if (c1.Q(m())) {
                        g0Var2.w(mVar5.b().f(), objM, mVar5.c(), new j(mVar5));
                    } else {
                        if (o.F0(2)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("SpecialEffectsController: Container ");
                            sb.append(m());
                            sb.append(" has not been laid out. Completing operation ");
                            sb.append(eVarB2);
                        }
                        mVar5.a();
                    }
                }
            }
        }
        if (!c1.Q(m())) {
            return map;
        }
        z.A(arrayList10, 4);
        ArrayList arrayListO = g0Var2.o(arrayList13);
        g0Var2.c(m(), objM);
        g0Var2.y(m(), arrayList14, arrayList13, arrayListO, aVar4);
        z.A(arrayList10, 0);
        g0Var2.A(obj3, arrayList14, arrayList13);
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0085  */
    @Override // androidx.fragment.app.j0
    void f(List list, boolean z) {
        Iterator it = list.iterator();
        j0.e eVar = null;
        j0.e eVar2 = null;
        while (it.hasNext()) {
            j0.e eVar3 = (j0.e) it.next();
            j0.e.c cVarC = j0.e.c.c(eVar3.f().mView);
            int i2 = a.a[eVar3.e().ordinal()];
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                if (cVarC == j0.e.c.VISIBLE && eVar == null) {
                    eVar = eVar3;
                }
            } else if (i2 == 4 && cVarC != j0.e.c.VISIBLE) {
                eVar2 = eVar3;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList(list);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            j0.e eVar4 = (j0.e) it2.next();
            u.b bVar = new u.b();
            eVar4.j(bVar);
            arrayList.add(new k(eVar4, bVar, z));
            u.b bVar2 = new u.b();
            eVar4.j(bVar2);
            boolean z2 = false;
            if (z) {
                if (eVar4 == eVar) {
                    z2 = true;
                }
            } else if (eVar4 == eVar2) {
                z2 = true;
            }
            arrayList2.add(new m(eVar4, bVar2, z, z2));
            eVar4.a(new b(arrayList3, eVar4));
        }
        Map mapX = x(arrayList2, arrayList3, z, eVar, eVar2);
        w(arrayList, arrayList3, mapX.containsValue(Boolean.TRUE), mapX);
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            s((j0.e) it3.next());
        }
        arrayList3.clear();
    }

    void s(j0.e eVar) {
        eVar.e().a(eVar.f().mView);
    }

    void t(ArrayList arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (r1.a(viewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                t(arrayList, childAt);
            }
        }
    }

    void u(Map map, View view) {
        String strI = c1.I(view);
        if (strI != null) {
            map.put(strI, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    u(map, childAt);
                }
            }
        }
    }

    void v(androidx.collection.a aVar, Collection collection) {
        Iterator<Map.Entry<Object, Object>> it = aVar.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(c1.I((View) it.next().getValue()))) {
                it.remove();
            }
        }
    }
}
