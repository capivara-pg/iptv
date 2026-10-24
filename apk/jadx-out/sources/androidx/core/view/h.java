package androidx.core.view;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    private final Object a;

    private h(Object obj) {
        this.a = obj;
    }

    static h e(Object obj) {
        if (obj == null) {
            return null;
        }
        return new h(obj);
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 28) {
            return c.a(this.a).getSafeInsetBottom();
        }
        return 0;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 28) {
            return c.a(this.a).getSafeInsetLeft();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 28) {
            return c.a(this.a).getSafeInsetRight();
        }
        return 0;
    }

    public int d() {
        if (Build.VERSION.SDK_INT >= 28) {
            return c.a(this.a).getSafeInsetTop();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h.class != obj.getClass()) {
            return false;
        }
        return c0.c.a(this.a, ((h) obj).a);
    }

    public int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.a + "}";
    }
}
