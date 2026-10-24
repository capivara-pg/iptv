package androidx.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    private EdgeEffect a;

    public e(Context context) {
        this.a = new EdgeEffect(context);
    }

    public static void d(EdgeEffect edgeEffect, float f, float f2) {
        edgeEffect.onPull(f, f2);
    }

    public boolean a(Canvas canvas) {
        return this.a.draw(canvas);
    }

    public void b() {
        this.a.finish();
    }

    public boolean c() {
        return this.a.isFinished();
    }

    public boolean e(float f) {
        this.a.onPull(f);
        return true;
    }

    public boolean f() {
        this.a.onRelease();
        return this.a.isFinished();
    }

    public void g(int i, int i2) {
        this.a.setSize(i, i2);
    }
}
