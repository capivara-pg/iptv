package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
class j {
    private final CompoundButton a;
    private ColorStateList b = null;
    private PorterDuff.Mode c = null;
    private boolean d = false;
    private boolean e = false;
    private boolean f;

    j(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.g.a(this.a);
        if (drawableA != null) {
            if (this.d || this.e) {
                Drawable drawableMutate = q.h.r(drawableA).mutate();
                if (this.d) {
                    q.h.o(drawableMutate, this.b);
                }
                if (this.e) {
                    q.h.p(drawableMutate, this.c);
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
        int resourceId;
        int resourceId2;
        TypedArray typedArrayObtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, R$styleable.r, i, 0);
        try {
            int i2 = R$styleable.CompoundButton_buttonCompat;
            if (!typedArrayObtainStyledAttributes.hasValue(i2) || (resourceId2 = typedArrayObtainStyledAttributes.getResourceId(i2, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton = this.a;
                    compoundButton.setButtonDrawable(d.b.d(compoundButton.getContext(), resourceId2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i3 = R$styleable.CompoundButton_android_button;
                if (typedArrayObtainStyledAttributes.hasValue(i3) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i3, 0)) != 0) {
                    CompoundButton compoundButton2 = this.a;
                    compoundButton2.setButtonDrawable(d.b.d(compoundButton2.getContext(), resourceId));
                }
            }
            int i4 = R$styleable.CompoundButton_buttonTint;
            if (typedArrayObtainStyledAttributes.hasValue(i4)) {
                androidx.core.widget.g.c(this.a, typedArrayObtainStyledAttributes.getColorStateList(i4));
            }
            int i5 = R$styleable.CompoundButton_buttonTintMode;
            if (typedArrayObtainStyledAttributes.hasValue(i5)) {
                androidx.core.widget.g.d(this.a, o1.e(typedArrayObtainStyledAttributes.getInt(i5, -1), null));
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
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
