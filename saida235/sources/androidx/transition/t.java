package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: loaded from: classes.dex */
class t implements u {
    private final ViewGroupOverlay a;

    t(ViewGroup viewGroup) {
        this.a = viewGroup.getOverlay();
    }

    @Override // androidx.transition.y
    public void a(Drawable drawable) {
        this.a.add(drawable);
    }

    @Override // androidx.transition.y
    public void b(Drawable drawable) {
        this.a.remove(drawable);
    }

    @Override // androidx.transition.u
    public void c(View view) {
        this.a.add(view);
    }

    @Override // androidx.transition.u
    public void d(View view) {
        this.a.remove(view);
    }
}
