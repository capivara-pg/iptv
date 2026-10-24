package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
abstract class z {
    private static final l0 a;
    static final Property b;
    static final Property c;

    static class a extends Property {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(z.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            z.g(view, f.floatValue());
        }
    }

    static class b extends Property {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return androidx.core.view.m0.t(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            androidx.core.view.m0.r0(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new k0();
        } else if (i >= 23) {
            a = new j0();
        } else if (i >= 22) {
            a = new h0();
        } else {
            a = new f0();
        }
        b = new a(Float.class, "translationAlpha");
        c = new b(Rect.class, "clipBounds");
    }

    static void a(View view) {
        a.a(view);
    }

    static y b(View view) {
        return new x(view);
    }

    static float c(View view) {
        return a.c(view);
    }

    static o0 d(View view) {
        return new n0(view);
    }

    static void e(View view) {
        a.d(view);
    }

    static void f(View view, int i, int i2, int i3, int i4) {
        a.e(view, i, i2, i3, i4);
    }

    static void g(View view, float f) {
        a.f(view, f);
    }

    static void h(View view, int i) {
        a.g(view, i);
    }

    static void i(View view, Matrix matrix) {
        a.h(view, matrix);
    }

    static void j(View view, Matrix matrix) {
        a.i(view, matrix);
    }
}
