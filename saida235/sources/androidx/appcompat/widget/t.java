package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
class t extends q {
    private final SeekBar d;
    private Drawable e;
    private ColorStateList f;
    private PorterDuff.Mode g;
    private boolean h;
    private boolean i;

    t(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.d = seekBar;
    }

    private void f() {
        Drawable drawable = this.e;
        if (drawable != null) {
            if (this.h || this.i) {
                Drawable drawableR = v.b.r(drawable.mutate());
                this.e = drawableR;
                if (this.h) {
                    v.b.o(drawableR, this.f);
                }
                if (this.i) {
                    v.b.p(this.e, this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(this.d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.q
    void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        Context context = this.d.getContext();
        int[] iArr = R$styleable.l;
        h2 h2VarV = h2.v(context, attributeSet, iArr, i, 0);
        SeekBar seekBar = this.d;
        androidx.core.view.m0.j0(seekBar, seekBar.getContext(), iArr, attributeSet, h2VarV.r(), i, 0);
        Drawable drawableH = h2VarV.h(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.d.setThumb(drawableH);
        }
        j(h2VarV.g(R$styleable.AppCompatSeekBar_tickMark));
        int i2 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
        if (h2VarV.s(i2)) {
            this.g = i1.e(h2VarV.k(i2, -1), this.g);
            this.i = true;
        }
        int i3 = R$styleable.AppCompatSeekBar_tickMarkTint;
        if (h2VarV.s(i3)) {
            this.f = h2VarV.c(i3);
            this.h = true;
        }
        h2VarV.w();
        f();
    }

    void g(Canvas canvas) {
        if (this.e != null) {
            int max = this.d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.e.setBounds(-i, -i2, i, i2);
                float width = ((this.d.getWidth() - this.d.getPaddingLeft()) - this.d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.d.getPaddingLeft(), this.d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    void h() {
        Drawable drawable = this.e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.d.getDrawableState())) {
            this.d.invalidateDrawable(drawable);
        }
    }

    void i() {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(Drawable drawable) {
        Drawable drawable2 = this.e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.d);
            v.b.m(drawable, androidx.core.view.m0.A(this.d));
            if (drawable.isStateful()) {
                drawable.setState(this.d.getDrawableState());
            }
            f();
        }
        this.d.invalidate();
    }
}
