package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class e extends androidx.fragment.app.g0 {

    class a extends n.f {
        final /* synthetic */ Rect a;

        a(Rect rect) {
            this.a = rect;
        }

        @Override // androidx.transition.n.f
        public Rect a(n nVar) {
            return this.a;
        }
    }

    class b implements n.g {
        final /* synthetic */ View a;
        final /* synthetic */ ArrayList b;

        b(View view, ArrayList arrayList) {
            this.a = view;
            this.b = arrayList;
        }

        @Override // androidx.transition.n.g
        public void a(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void b(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            nVar.removeListener(this);
            this.a.setVisibility(8);
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((View) this.b.get(i)).setVisibility(0);
            }
        }

        @Override // androidx.transition.n.g
        public void e(n nVar) {
        }
    }

    class c extends o {
        final /* synthetic */ Object a;
        final /* synthetic */ ArrayList b;
        final /* synthetic */ Object c;
        final /* synthetic */ ArrayList d;
        final /* synthetic */ Object e;
        final /* synthetic */ ArrayList f;

        c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.a = obj;
            this.b = arrayList;
            this.c = obj2;
            this.d = arrayList2;
            this.e = obj3;
            this.f = arrayList3;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void b(n nVar) {
            Object obj = this.a;
            if (obj != null) {
                e.this.q(obj, this.b, null);
            }
            Object obj2 = this.c;
            if (obj2 != null) {
                e.this.q(obj2, this.d, null);
            }
            Object obj3 = this.e;
            if (obj3 != null) {
                e.this.q(obj3, this.f, null);
            }
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            nVar.removeListener(this);
        }
    }

    class d extends n.f {
        final /* synthetic */ Rect a;

        d(Rect rect) {
            this.a = rect;
        }

        @Override // androidx.transition.n.f
        public Rect a(n nVar) {
            Rect rect = this.a;
            if (rect == null || rect.isEmpty()) {
                return null;
            }
            return this.a;
        }
    }

    private static boolean C(n nVar) {
        return (androidx.fragment.app.g0.l(nVar.getTargetIds()) && androidx.fragment.app.g0.l(nVar.getTargetNames()) && androidx.fragment.app.g0.l(nVar.getTargetTypes())) ? false : true;
    }

    @Override // androidx.fragment.app.g0
    public void A(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        r rVar = (r) obj;
        if (rVar != null) {
            rVar.getTargets().clear();
            rVar.getTargets().addAll(arrayList2);
            q(rVar, arrayList, arrayList2);
        }
    }

    @Override // androidx.fragment.app.g0
    public Object B(Object obj) {
        if (obj == null) {
            return null;
        }
        r rVar = new r();
        rVar.w((n) obj);
        return rVar;
    }

    @Override // androidx.fragment.app.g0
    public void a(Object obj, View view) {
        if (obj != null) {
            ((n) obj).addTarget(view);
        }
    }

    @Override // androidx.fragment.app.g0
    public void b(Object obj, ArrayList arrayList) {
        n nVar = (n) obj;
        if (nVar == null) {
            return;
        }
        int i = 0;
        if (nVar instanceof r) {
            r rVar = (r) nVar;
            int iZ = rVar.z();
            while (i < iZ) {
                b(rVar.y(i), arrayList);
                i++;
            }
            return;
        }
        if (C(nVar) || !androidx.fragment.app.g0.l(nVar.getTargets())) {
            return;
        }
        int size = arrayList.size();
        while (i < size) {
            nVar.addTarget((View) arrayList.get(i));
            i++;
        }
    }

    @Override // androidx.fragment.app.g0
    public void c(ViewGroup viewGroup, Object obj) {
        p.a(viewGroup, (n) obj);
    }

    @Override // androidx.fragment.app.g0
    public boolean e(Object obj) {
        return obj instanceof n;
    }

    @Override // androidx.fragment.app.g0
    public Object g(Object obj) {
        if (obj != null) {
            return ((n) obj).mo4clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.g0
    public Object m(Object obj, Object obj2, Object obj3) {
        n nVarH = (n) obj;
        n nVar = (n) obj2;
        n nVar2 = (n) obj3;
        if (nVarH != null && nVar != null) {
            nVarH = new r().w(nVarH).w(nVar).H(1);
        } else if (nVarH == null) {
            nVarH = nVar != null ? nVar : null;
        }
        if (nVar2 == null) {
            return nVarH;
        }
        r rVar = new r();
        if (nVarH != null) {
            rVar.w(nVarH);
        }
        rVar.w(nVar2);
        return rVar;
    }

    @Override // androidx.fragment.app.g0
    public Object n(Object obj, Object obj2, Object obj3) {
        r rVar = new r();
        if (obj != null) {
            rVar.w((n) obj);
        }
        if (obj2 != null) {
            rVar.w((n) obj2);
        }
        if (obj3 != null) {
            rVar.w((n) obj3);
        }
        return rVar;
    }

    @Override // androidx.fragment.app.g0
    public void p(Object obj, View view) {
        if (obj != null) {
            ((n) obj).removeTarget(view);
        }
    }

    @Override // androidx.fragment.app.g0
    public void q(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        n nVar = (n) obj;
        int i = 0;
        if (nVar instanceof r) {
            r rVar = (r) nVar;
            int iZ = rVar.z();
            while (i < iZ) {
                q(rVar.y(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (C(nVar)) {
            return;
        }
        List<View> targets = nVar.getTargets();
        if (targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i < size) {
                nVar.addTarget((View) arrayList2.get(i));
                i++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                nVar.removeTarget((View) arrayList.get(size2));
            }
        }
    }

    @Override // androidx.fragment.app.g0
    public void r(Object obj, View view, ArrayList arrayList) {
        ((n) obj).addListener(new b(view, arrayList));
    }

    @Override // androidx.fragment.app.g0
    public void t(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((n) obj).addListener(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.g0
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((n) obj).setEpicenterCallback(new d(rect));
        }
    }

    @Override // androidx.fragment.app.g0
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((n) obj).setEpicenterCallback(new a(rect));
        }
    }

    @Override // androidx.fragment.app.g0
    public void z(Object obj, View view, ArrayList arrayList) {
        r rVar = (r) obj;
        List<View> targets = rVar.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            androidx.fragment.app.g0.d(targets, (View) arrayList.get(i));
        }
        targets.add(view);
        arrayList.add(view);
        b(rVar, arrayList);
    }
}
