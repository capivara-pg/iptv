package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e extends androidx.fragment.app.a0 {

    class a extends k.f {
        final /* synthetic */ Rect a;

        a(Rect rect) {
            this.a = rect;
        }

        @Override // androidx.transition.k.f
        public Rect a(k kVar) {
            return this.a;
        }
    }

    class b implements k.g {
        final /* synthetic */ View a;
        final /* synthetic */ ArrayList b;

        b(View view, ArrayList arrayList) {
            this.a = view;
            this.b = arrayList;
        }

        @Override // androidx.transition.k.g
        public void a(k kVar) {
        }

        @Override // androidx.transition.k.g
        public void b(k kVar) {
        }

        @Override // androidx.transition.k.g
        public void c(k kVar) {
        }

        @Override // androidx.transition.k.g
        public void d(k kVar) {
            kVar.removeListener(this);
            this.a.setVisibility(8);
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((View) this.b.get(i)).setVisibility(0);
            }
        }

        @Override // androidx.transition.k.g
        public void e(k kVar) {
        }
    }

    class c extends l {
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

        @Override // androidx.transition.l, androidx.transition.k.g
        public void b(k kVar) {
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

        @Override // androidx.transition.k.g
        public void d(k kVar) {
            kVar.removeListener(this);
        }
    }

    class d extends k.f {
        final /* synthetic */ Rect a;

        d(Rect rect) {
            this.a = rect;
        }

        @Override // androidx.transition.k.f
        public Rect a(k kVar) {
            Rect rect = this.a;
            if (rect == null || rect.isEmpty()) {
                return null;
            }
            return this.a;
        }
    }

    private static boolean C(k kVar) {
        return (androidx.fragment.app.a0.l(kVar.getTargetIds()) && androidx.fragment.app.a0.l(kVar.getTargetNames()) && androidx.fragment.app.a0.l(kVar.getTargetTypes())) ? false : true;
    }

    @Override // androidx.fragment.app.a0
    public void A(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        o oVar = (o) obj;
        if (oVar != null) {
            oVar.getTargets().clear();
            oVar.getTargets().addAll(arrayList2);
            q(oVar, arrayList, arrayList2);
        }
    }

    @Override // androidx.fragment.app.a0
    public Object B(Object obj) {
        if (obj == null) {
            return null;
        }
        o oVar = new o();
        oVar.w((k) obj);
        return oVar;
    }

    @Override // androidx.fragment.app.a0
    public void a(Object obj, View view) {
        if (obj != null) {
            ((k) obj).addTarget(view);
        }
    }

    @Override // androidx.fragment.app.a0
    public void b(Object obj, ArrayList arrayList) {
        k kVar = (k) obj;
        if (kVar == null) {
            return;
        }
        int i = 0;
        if (kVar instanceof o) {
            o oVar = (o) kVar;
            int iZ = oVar.z();
            while (i < iZ) {
                b(oVar.y(i), arrayList);
                i++;
            }
            return;
        }
        if (C(kVar) || !androidx.fragment.app.a0.l(kVar.getTargets())) {
            return;
        }
        int size = arrayList.size();
        while (i < size) {
            kVar.addTarget((View) arrayList.get(i));
            i++;
        }
    }

    @Override // androidx.fragment.app.a0
    public void c(ViewGroup viewGroup, Object obj) {
        m.a(viewGroup, (k) obj);
    }

    @Override // androidx.fragment.app.a0
    public boolean e(Object obj) {
        return obj instanceof k;
    }

    @Override // androidx.fragment.app.a0
    public Object g(Object obj) {
        if (obj != null) {
            return ((k) obj).mo6clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.a0
    public Object m(Object obj, Object obj2, Object obj3) {
        k kVarH = (k) obj;
        k kVar = (k) obj2;
        k kVar2 = (k) obj3;
        if (kVarH != null && kVar != null) {
            kVarH = new o().w(kVarH).w(kVar).H(1);
        } else if (kVarH == null) {
            kVarH = kVar != null ? kVar : null;
        }
        if (kVar2 == null) {
            return kVarH;
        }
        o oVar = new o();
        if (kVarH != null) {
            oVar.w(kVarH);
        }
        oVar.w(kVar2);
        return oVar;
    }

    @Override // androidx.fragment.app.a0
    public Object n(Object obj, Object obj2, Object obj3) {
        o oVar = new o();
        if (obj != null) {
            oVar.w((k) obj);
        }
        if (obj2 != null) {
            oVar.w((k) obj2);
        }
        if (obj3 != null) {
            oVar.w((k) obj3);
        }
        return oVar;
    }

    @Override // androidx.fragment.app.a0
    public void p(Object obj, View view) {
        if (obj != null) {
            ((k) obj).removeTarget(view);
        }
    }

    @Override // androidx.fragment.app.a0
    public void q(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        k kVar = (k) obj;
        int i = 0;
        if (kVar instanceof o) {
            o oVar = (o) kVar;
            int iZ = oVar.z();
            while (i < iZ) {
                q(oVar.y(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (C(kVar)) {
            return;
        }
        List<View> targets = kVar.getTargets();
        if (targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i < size) {
                kVar.addTarget((View) arrayList2.get(i));
                i++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                kVar.removeTarget((View) arrayList.get(size2));
            }
        }
    }

    @Override // androidx.fragment.app.a0
    public void r(Object obj, View view, ArrayList arrayList) {
        ((k) obj).addListener(new b(view, arrayList));
    }

    @Override // androidx.fragment.app.a0
    public void t(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((k) obj).addListener(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.a0
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((k) obj).setEpicenterCallback(new d(rect));
        }
    }

    @Override // androidx.fragment.app.a0
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((k) obj).setEpicenterCallback(new a(rect));
        }
    }

    @Override // androidx.fragment.app.a0
    public void z(Object obj, View view, ArrayList arrayList) {
        o oVar = (o) obj;
        List<View> targets = oVar.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            androidx.fragment.app.a0.d(targets, (View) arrayList.get(i));
        }
        targets.add(view);
        arrayList.add(view);
        b(oVar, arrayList);
    }
}
