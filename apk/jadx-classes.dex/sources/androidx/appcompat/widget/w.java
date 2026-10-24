package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class w extends t {
    private final SeekBar d;
    private Drawable e;
    private ColorStateList f;
    private PorterDuff.Mode g;
    private boolean h;
    private boolean i;

    w(SeekBar seekBar) {
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
                Drawable drawableR = q.h.r(drawable.mutate());
                this.e = drawableR;
                if (this.h) {
                    q.h.o(drawableR, this.f);
                }
                if (this.i) {
                    q.h.p(this.e, this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(this.d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.t
    void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        r2 r2VarU = r2.u(this.d.getContext(), attributeSet, R$styleable.l, i, 0);
        Drawable drawableH = r2VarU.h(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.d.setThumb(drawableH);
        }
        j(r2VarU.g(R$styleable.AppCompatSeekBar_tickMark));
        int i2 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
        if (r2VarU.r(i2)) {
            this.g = o1.e(r2VarU.k(i2, -1), this.g);
            this.i = true;
        }
        int i3 = R$styleable.AppCompatSeekBar_tickMarkTint;
        if (r2VarU.r(i3)) {
            this.f = r2VarU.c(i3);
            this.h = true;
        }
        r2VarU.v();
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
            q.h.m(drawable, androidx.core.view.c1.z(this.d));
            if (drawable.isStateful()) {
                drawable.setState(this.d.getDrawableState());
            }
            f();
        }
        this.d.invalidate();
    }
}
