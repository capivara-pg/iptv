package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class c implements f {
    c() {
    }

    private h p(e eVar) {
        return (h) eVar.c();
    }

    @Override // androidx.cardview.widget.f
    public void a(e eVar, float f) {
        p(eVar).h(f);
    }

    @Override // androidx.cardview.widget.f
    public float b(e eVar) {
        return p(eVar).d();
    }

    @Override // androidx.cardview.widget.f
    public void c(e eVar, float f) {
        eVar.f().setElevation(f);
    }

    @Override // androidx.cardview.widget.f
    public float d(e eVar) {
        return p(eVar).c();
    }

    @Override // androidx.cardview.widget.f
    public ColorStateList e(e eVar) {
        return p(eVar).b();
    }

    @Override // androidx.cardview.widget.f
    public float f(e eVar) {
        return b(eVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.f
    public void g(e eVar) {
        o(eVar, d(eVar));
    }

    @Override // androidx.cardview.widget.f
    public void h(e eVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        eVar.a(new h(colorStateList, f));
        View viewF = eVar.f();
        viewF.setClipToOutline(true);
        viewF.setElevation(f2);
        o(eVar, f3);
    }

    @Override // androidx.cardview.widget.f
    public float i(e eVar) {
        return eVar.f().getElevation();
    }

    @Override // androidx.cardview.widget.f
    public void j(e eVar) {
        o(eVar, d(eVar));
    }

    @Override // androidx.cardview.widget.f
    public void k(e eVar) {
        if (!eVar.b()) {
            eVar.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float fD = d(eVar);
        float fB = b(eVar);
        int iCeil = (int) Math.ceil(i.c(fD, fB, eVar.e()));
        int iCeil2 = (int) Math.ceil(i.d(fD, fB, eVar.e()));
        eVar.setShadowPadding(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // androidx.cardview.widget.f
    public void l() {
    }

    @Override // androidx.cardview.widget.f
    public float m(e eVar) {
        return b(eVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.f
    public void n(e eVar, ColorStateList colorStateList) {
        p(eVar).f(colorStateList);
    }

    @Override // androidx.cardview.widget.f
    public void o(e eVar, float f) {
        p(eVar).g(f, eVar.b(), eVar.e());
        k(eVar);
    }
}
