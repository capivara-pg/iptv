package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
class i {
    private final CompoundButton a;
    private ColorStateList b = null;
    private PorterDuff.Mode c = null;
    private boolean d = false;
    private boolean e = false;
    private boolean f;

    i(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.d.a(this.a);
        if (drawableA != null) {
            if (this.d || this.e) {
                Drawable drawableMutate = v.b.r(drawableA).mutate();
                if (this.d) {
                    v.b.o(drawableMutate, this.b);
                }
                if (this.e) {
                    v.b.p(drawableMutate, this.c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.a.getDrawableState());
                }
                this.a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int b(int i) {
        return i;
    }

    ColorStateList c() {
        return this.b;
    }

    PorterDuff.Mode d() {
        return this.c;
    }

    void e(AttributeSet attributeSet, int i) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.a.getContext();
        int[] iArr = R$styleable.r;
        h2 h2VarV = h2.v(context, attributeSet, iArr, i, 0);
        CompoundButton compoundButton = this.a;
        androidx.core.view.m0.j0(compoundButton, compoundButton.getContext(), iArr, attributeSet, h2VarV.r(), i, 0);
        try {
            int i2 = R$styleable.CompoundButton_buttonCompat;
            if (!h2VarV.s(i2) || (iN2 = h2VarV.n(i2, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.a;
                    compoundButton2.setButtonDrawable(f.b.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i3 = R$styleable.CompoundButton_android_button;
                if (h2VarV.s(i3) && (iN = h2VarV.n(i3, 0)) != 0) {
                    CompoundButton compoundButton3 = this.a;
                    compoundButton3.setButtonDrawable(f.b.d(compoundButton3.getContext(), iN));
                }
            }
            int i4 = R$styleable.CompoundButton_buttonTint;
            if (h2VarV.s(i4)) {
                androidx.core.widget.d.c(this.a, h2VarV.c(i4));
            }
            int i5 = R$styleable.CompoundButton_buttonTintMode;
            if (h2VarV.s(i5)) {
                androidx.core.widget.d.d(this.a, i1.e(h2VarV.k(i5, -1), null));
            }
        } finally {
            h2VarV.w();
        }
    }

    void f() {
        if (this.f) {
            this.f = false;
        } else {
            this.f = true;
            a();
        }
    }

    void g(ColorStateList colorStateList) {
        this.b = colorStateList;
        this.d = true;
        a();
    }

    void h(PorterDuff.Mode mode) {
        this.c = mode;
        this.e = true;
        a();
    }
}
