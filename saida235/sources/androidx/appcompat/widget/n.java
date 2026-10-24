package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class n {
    private final ImageView a;
    private f2 b;
    private f2 c;
    private f2 d;

    public n(ImageView imageView) {
        this.a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.d == null) {
            this.d = new f2();
        }
        f2 f2Var = this.d;
        f2Var.a();
        ColorStateList colorStateListA = androidx.core.widget.f.a(this.a);
        if (colorStateListA != null) {
            f2Var.d = true;
            f2Var.a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.f.b(this.a);
        if (modeB != null) {
            f2Var.c = true;
            f2Var.b = modeB;
        }
        if (!f2Var.d && !f2Var.c) {
            return false;
        }
        j.i(drawable, f2Var, this.a.getDrawableState());
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
            i1.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            f2 f2Var = this.c;
            if (f2Var != null) {
                j.i(drawable, f2Var, this.a.getDrawableState());
                return;
            }
            f2 f2Var2 = this.b;
            if (f2Var2 != null) {
                j.i(drawable, f2Var2, this.a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        f2 f2Var = this.c;
        if (f2Var != null) {
            return f2Var.a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        f2 f2Var = this.c;
        if (f2Var != null) {
            return f2Var.b;
        }
        return null;
    }

    boolean e() {
        return !(this.a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.a.getContext();
        int[] iArr = R$styleable.k;
        h2 h2VarV = h2.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.a;
        androidx.core.view.m0.j0(imageView, imageView.getContext(), iArr, attributeSet, h2VarV.r(), i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null && (iN = h2VarV.n(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = f.b.d(this.a.getContext(), iN)) != null) {
                this.a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                i1.b(drawable);
            }
            int i2 = R$styleable.AppCompatImageView_tint;
            if (h2VarV.s(i2)) {
                androidx.core.widget.f.c(this.a, h2VarV.c(i2));
            }
            int i3 = R$styleable.AppCompatImageView_tintMode;
            if (h2VarV.s(i3)) {
                androidx.core.widget.f.d(this.a, i1.e(h2VarV.k(i3, -1), null));
            }
        } finally {
            h2VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = f.b.d(this.a.getContext(), i);
            if (drawableD != null) {
                i1.b(drawableD);
            }
            this.a.setImageDrawable(drawableD);
        } else {
            this.a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new f2();
        }
        f2 f2Var = this.c;
        f2Var.a = colorStateList;
        f2Var.d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.c == null) {
            this.c = new f2();
        }
        f2 f2Var = this.c;
        f2Var.b = mode;
        f2Var.c = true;
        b();
    }
}
