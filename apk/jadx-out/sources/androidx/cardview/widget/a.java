package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class a implements c {
    a() {
    }

    private d p(b bVar) {
        return (d) bVar.c();
    }

    @Override // androidx.cardview.widget.c
    public void a(b bVar, float f) {
        p(bVar).h(f);
    }

    @Override // androidx.cardview.widget.c
    public float b(b bVar) {
        return p(bVar).d();
    }

    @Override // androidx.cardview.widget.c
    public void c(b bVar, float f) {
        bVar.e().setElevation(f);
    }

    @Override // androidx.cardview.widget.c
    public float d(b bVar) {
        return p(bVar).c();
    }

    @Override // androidx.cardview.widget.c
    public ColorStateList e(b bVar) {
        return p(bVar).b();
    }

    @Override // androidx.cardview.widget.c
    public float f(b bVar) {
        return b(bVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.c
    public void g(b bVar) {
        o(bVar, d(bVar));
    }

    @Override // androidx.cardview.widget.c
    public void h(b bVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        bVar.a(new d(colorStateList, f));
        View viewE = bVar.e();
        viewE.setClipToOutline(true);
        viewE.setElevation(f2);
        o(bVar, f3);
    }

    @Override // androidx.cardview.widget.c
    public float i(b bVar) {
        return bVar.e().getElevation();
    }

    @Override // androidx.cardview.widget.c
    public void j(b bVar) {
        o(bVar, d(bVar));
    }

    @Override // androidx.cardview.widget.c
    public void k(b bVar) {
        if (!bVar.b()) {
            bVar.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float fD = d(bVar);
        float fB = b(bVar);
        int iCeil = (int) Math.ceil(e.a(fD, fB, bVar.d()));
        int iCeil2 = (int) Math.ceil(e.b(fD, fB, bVar.d()));
        bVar.setShadowPadding(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // androidx.cardview.widget.c
    public void l() {
    }

    @Override // androidx.cardview.widget.c
    public float m(b bVar) {
        return b(bVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.c
    public void n(b bVar, ColorStateList colorStateList) {
        p(bVar).f(colorStateList);
    }

    @Override // androidx.cardview.widget.c
    public void o(b bVar, float f) {
        p(bVar).g(f, bVar.b(), bVar.d());
        k(bVar);
    }
}
