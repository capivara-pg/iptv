package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
class f {
    private final View a;
    private p2 d;
    private p2 e;
    private p2 f;
    private int c = -1;
    private final k b = k.b();

    f(View view) {
        this.a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new p2();
        }
        p2 p2Var = this.f;
        p2Var.a();
        ColorStateList colorStateListQ = androidx.core.view.c1.q(this.a);
        if (colorStateListQ != null) {
            p2Var.d = true;
            p2Var.a = colorStateListQ;
        }
        PorterDuff.Mode modeR = androidx.core.view.c1.r(this.a);
        if (modeR != null) {
            p2Var.c = true;
            p2Var.b = modeR;
        }
        if (!p2Var.d && !p2Var.c) {
            return false;
        }
        k.i(drawable, p2Var, this.a.getDrawableState());
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
            p2 p2Var = this.e;
            if (p2Var != null) {
                k.i(background, p2Var, this.a.getDrawableState());
                return;
            }
            p2 p2Var2 = this.d;
            if (p2Var2 != null) {
                k.i(background, p2Var2, this.a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        p2 p2Var = this.e;
        if (p2Var != null) {
            return p2Var.a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        p2 p2Var = this.e;
        if (p2Var != null) {
            return p2Var.b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i) {
        r2 r2VarU = r2.u(this.a.getContext(), attributeSet, R$styleable.O, i, 0);
        try {
            int i2 = R$styleable.ViewBackgroundHelper_android_background;
            if (r2VarU.r(i2)) {
                this.c = r2VarU.n(i2, -1);
                ColorStateList colorStateListF = this.b.f(this.a.getContext(), this.c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = R$styleable.ViewBackgroundHelper_backgroundTint;
            if (r2VarU.r(i3)) {
                androidx.core.view.c1.o0(this.a, r2VarU.c(i3));
            }
            int i4 = R$styleable.ViewBackgroundHelper_backgroundTintMode;
            if (r2VarU.r(i4)) {
                androidx.core.view.c1.p0(this.a, o1.e(r2VarU.k(i4, -1), null));
            }
        } finally {
            r2VarU.v();
        }
    }

    void f(Drawable drawable) {
        this.c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.c = i;
        k kVar = this.b;
        h(kVar != null ? kVar.f(this.a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new p2();
            }
            p2 p2Var = this.d;
            p2Var.a = colorStateList;
            p2Var.d = true;
        } else {
            this.d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new p2();
        }
        p2 p2Var = this.e;
        p2Var.a = colorStateList;
        p2Var.d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new p2();
        }
        p2 p2Var = this.e;
        p2Var.b = mode;
        p2Var.c = true;
        b();
    }
}
