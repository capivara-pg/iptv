package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: loaded from: classes.dex */
class x implements y {
    private final ViewOverlay a;

    x(View view) {
        this.a = view.getOverlay();
    }

    @Override // androidx.transition.y
    public void a(Drawable drawable) {
        this.a.add(drawable);
    }

    @Override // androidx.transition.y
    public void b(Drawable drawable) {
        this.a.remove(drawable);
    }
}
