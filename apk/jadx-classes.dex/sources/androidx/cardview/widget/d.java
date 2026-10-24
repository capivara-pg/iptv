package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
abstract class d implements f {
    final RectF a = new RectF();

    d() {
    }

    private i p(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new i(context.getResources(), colorStateList, f, f2, f3);
    }

    private i q(e eVar) {
        return (i) eVar.c();
    }

    @Override // androidx.cardview.widget.f
    public void a(e eVar, float f) {
        q(eVar).p(f);
        k(eVar);
    }

    @Override // androidx.cardview.widget.f
    public float b(e eVar) {
        return q(eVar).g();
    }

    @Override // androidx.cardview.widget.f
    public void c(e eVar, float f) {
        q(eVar).r(f);
    }

    @Override // androidx.cardview.widget.f
    public float d(e eVar) {
        return q(eVar).i();
    }

    @Override // androidx.cardview.widget.f
    public ColorStateList e(e eVar) {
        return q(eVar).f();
    }

    @Override // androidx.cardview.widget.f
    public float f(e eVar) {
        return q(eVar).j();
    }

    @Override // androidx.cardview.widget.f
    public void g(e eVar) {
        q(eVar).m(eVar.e());
        k(eVar);
    }

    @Override // androidx.cardview.widget.f
    public void h(e eVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        i iVarP = p(context, colorStateList, f, f2, f3);
        iVarP.m(eVar.e());
        eVar.a(iVarP);
        k(eVar);
    }

    @Override // androidx.cardview.widget.f
    public float i(e eVar) {
        return q(eVar).l();
    }

    @Override // androidx.cardview.widget.f
    public void j(e eVar) {
    }

    @Override // androidx.cardview.widget.f
    public void k(e eVar) {
        Rect rect = new Rect();
        q(eVar).h(rect);
        eVar.d((int) Math.ceil(m(eVar)), (int) Math.ceil(f(eVar)));
        eVar.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // androidx.cardview.widget.f
    public float m(e eVar) {
        return q(eVar).k();
    }

    @Override // androidx.cardview.widget.f
    public void n(e eVar, ColorStateList colorStateList) {
        q(eVar).o(colorStateList);
    }

    @Override // androidx.cardview.widget.f
    public void o(e eVar, float f) {
        q(eVar).q(f);
        k(eVar);
    }
}
