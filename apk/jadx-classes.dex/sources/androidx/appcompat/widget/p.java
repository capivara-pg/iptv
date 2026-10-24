package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class p {
    private final ImageView a;
    private p2 b;
    private p2 c;
    private p2 d;

    public p(ImageView imageView) {
        this.a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.d == null) {
            this.d = new p2();
        }
        p2 p2Var = this.d;
        p2Var.a();
        ColorStateList colorStateListA = androidx.core.widget.n.a(this.a);
        if (colorStateListA != null) {
            p2Var.d = true;
            p2Var.a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.n.b(this.a);
        if (modeB != null) {
            p2Var.c = true;
            p2Var.b = modeB;
        }
        if (!p2Var.d && !p2Var.c) {
            return false;
        }
        k.i(drawable, p2Var, this.a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        if (i > 21) {
            return this.b != null;
        }
        return i == 21;
    }

    void b() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            o1.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            p2 p2Var = this.c;
            if (p2Var != null) {
                k.i(drawable, p2Var, this.a.getDrawableState());
                return;
            }
            p2 p2Var2 = this.b;
            if (p2Var2 != null) {
                k.i(drawable, p2Var2, this.a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        p2 p2Var = this.c;
        if (p2Var != null) {
            return p2Var.a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        p2 p2Var = this.c;
        if (p2Var != null) {
            return p2Var.b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !o.a(this.a.getBackground());
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        r2 r2VarU = r2.u(this.a.getContext(), attributeSet, R$styleable.k, i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null && (iN = r2VarU.n(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = d.b.d(this.a.getContext(), iN)) != null) {
                this.a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                o1.b(drawable);
            }
            int i2 = R$styleable.AppCompatImageView_tint;
            if (r2VarU.r(i2)) {
                androidx.core.widget.n.c(this.a, r2VarU.c(i2));
            }
            int i3 = R$styleable.AppCompatImageView_tintMode;
            if (r2VarU.r(i3)) {
                androidx.core.widget.n.d(this.a, o1.e(r2VarU.k(i3, -1), null));
            }
        } finally {
            r2VarU.v();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = d.b.d(this.a.getContext(), i);
            if (drawableD != null) {
                o1.b(drawableD);
            }
            this.a.setImageDrawable(drawableD);
        } else {
            this.a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new p2();
        }
        p2 p2Var = this.c;
        p2Var.a = colorStateList;
        p2Var.d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.c == null) {
            this.c = new p2();
        }
        p2 p2Var = this.c;
        p2Var.b = mode;
        p2Var.c = true;
        b();
    }
}
