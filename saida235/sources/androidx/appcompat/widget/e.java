package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
class e {
    private final View a;
    private f2 d;
    private f2 e;
    private f2 f;
    private int c = -1;
    private final j b = j.b();

    e(View view) {
        this.a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new f2();
        }
        f2 f2Var = this.f;
        f2Var.a();
        ColorStateList colorStateListR = androidx.core.view.m0.r(this.a);
        if (colorStateListR != null) {
            f2Var.d = true;
            f2Var.a = colorStateListR;
        }
        PorterDuff.Mode modeS = androidx.core.view.m0.s(this.a);
        if (modeS != null) {
            f2Var.c = true;
            f2Var.b = modeS;
        }
        if (!f2Var.d && !f2Var.c) {
            return false;
        }
        j.i(drawable, f2Var, this.a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i = Build.VERSION.SDK_INT;
        if (i > 21) {
            return this.d != null;
        }
        return i == 21;
    }

    void b() {
        Drawable background = this.a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            f2 f2Var = this.e;
            if (f2Var != null) {
                j.i(background, f2Var, this.a.getDrawableState());
                return;
            }
            f2 f2Var2 = this.d;
            if (f2Var2 != null) {
                j.i(background, f2Var2, this.a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        f2 f2Var = this.e;
        if (f2Var != null) {
            return f2Var.a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        f2 f2Var = this.e;
        if (f2Var != null) {
            return f2Var.b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i) {
        Context context = this.a.getContext();
        int[] iArr = R$styleable.O;
        h2 h2VarV = h2.v(context, attributeSet, iArr, i, 0);
        View view = this.a;
        androidx.core.view.m0.j0(view, view.getContext(), iArr, attributeSet, h2VarV.r(), i, 0);
        try {
            int i2 = R$styleable.ViewBackgroundHelper_android_background;
            if (h2VarV.s(i2)) {
                this.c = h2VarV.n(i2, -1);
                ColorStateList colorStateListF = this.b.f(this.a.getContext(), this.c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = R$styleable.ViewBackgroundHelper_backgroundTint;
            if (h2VarV.s(i3)) {
                androidx.core.view.m0.p0(this.a, h2VarV.c(i3));
            }
            int i4 = R$styleable.ViewBackgroundHelper_backgroundTintMode;
            if (h2VarV.s(i4)) {
                androidx.core.view.m0.q0(this.a, i1.e(h2VarV.k(i4, -1), null));
            }
        } finally {
            h2VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.c = i;
        j jVar = this.b;
        h(jVar != null ? jVar.f(this.a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new f2();
            }
            f2 f2Var = this.d;
            f2Var.a = colorStateList;
            f2Var.d = true;
        } else {
            this.d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new f2();
        }
        f2 f2Var = this.e;
        f2Var.a = colorStateList;
        f2Var.d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new f2();
        }
        f2 f2Var = this.e;
        f2Var.b = mode;
        f2Var.c = true;
        b();
    }
}
