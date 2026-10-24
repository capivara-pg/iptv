package androidx.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    private EdgeEffect a;

    public i(Context context) {
        this.a = new EdgeEffect(context);
    }

    public static void d(EdgeEffect edgeEffect, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect.onPull(f, f2);
        } else {
            edgeEffect.onPull(f);
        }
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
