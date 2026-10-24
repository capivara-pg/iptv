package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class p0 {
    private final TextView a;
    private p2 b;
    private p2 c;
    private p2 d;
    private p2 e;
    private p2 f;
    private p2 g;
    private p2 h;
    private final e1 i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    private static class a extends o.h.c {
        private final WeakReference a;
        private final int b;
        private final int c;

        /* JADX INFO: renamed from: androidx.appcompat.widget.p0$a$a, reason: collision with other inner class name */
        private class RunnableC0013a implements Runnable {
            private final WeakReference a;
            private final Typeface b;

            RunnableC0013a(WeakReference weakReference, Typeface typeface) {
                this.a = weakReference;
                this.b = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                p0 p0Var = (p0) this.a.get();
                if (p0Var == null) {
                    return;
                }
                p0Var.B(this.b);
            }
        }

        a(p0 p0Var, int i, int i2) {
            this.a = new WeakReference(p0Var);
            this.b = i;
            this.c = i2;
        }

        public void onFontRetrievalFailed(int i) {
        }

        public void onFontRetrieved(Typeface typeface) {
            int i;
            p0 p0Var = (p0) this.a.get();
            if (p0Var == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 28 && (i = this.b) != -1) {
                typeface = Typeface.create(typeface, i, (this.c & 2) != 0);
            }
            p0Var.q(new RunnableC0013a(this.a, typeface));
        }
    }

    p0(TextView textView) {
        this.a = textView;
        this.i = new e1(textView);
    }

    private void A(int i, float f) {
        this.i.v(i, f);
    }

    private void C(Context context, r2 r2Var) {
        String strO;
        this.j = r2Var.k(R$styleable.TextAppearance_android_textStyle, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iK = r2Var.k(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = R$styleable.TextAppearance_android_fontFamily;
        if (!r2Var.r(i2) && !r2Var.r(R$styleable.TextAppearance_fontFamily)) {
            int i3 = R$styleable.TextAppearance_android_typeface;
            if (r2Var.r(i3)) {
                this.m = false;
                int iK2 = r2Var.k(i3, 1);
                if (iK2 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (iK2 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (iK2 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i4 = R$styleable.TextAppearance_fontFamily;
        if (r2Var.r(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = r2Var.j(i2, this.j, new a(this, i5, i6));
                if (typefaceJ != null) {
                    if (i < 28 || this.k == -1) {
                        this.l = typefaceJ;
                    } else {
                        this.l = Typeface.create(Typeface.create(typefaceJ, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strO = r2Var.o(i2)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strO, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strO, 0), this.k, (this.j & 2) != 0);
        }
    }

    private void a(Drawable drawable, p2 p2Var) {
        if (drawable == null || p2Var == null) {
            return;
        }
        k.i(drawable, p2Var, this.a.getDrawableState());
    }

    private static p2 d(Context context, k kVar, int i) {
        ColorStateList colorStateListF = kVar.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        p2 p2Var = new p2();
        p2Var.d = true;
        p2Var.a = colorStateListF;
        return p2Var;
    }

    private void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            TextView textView = this.a;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative2 = this.a.getCompoundDrawablesRelative();
        Drawable drawable7 = compoundDrawablesRelative2[0];
        if (drawable7 != null || compoundDrawablesRelative2[2] != null) {
            TextView textView2 = this.a;
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative2[1];
            }
            Drawable drawable8 = compoundDrawablesRelative2[2];
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative2[3];
            }
            textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
            return;
        }
        Drawable[] compoundDrawables = this.a.getCompoundDrawables();
        TextView textView3 = this.a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    private void y() {
        p2 p2Var = this.h;
        this.b = p2Var;
        this.c = p2Var;
        this.d = p2Var;
        this.e = p2Var;
        this.f = p2Var;
        this.g = p2Var;
    }

    public void B(Typeface typeface) {
        if (this.m) {
            this.a.setTypeface(typeface);
            this.l = typeface;
        }
    }

    void b() {
        if (this.b != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f == null && this.g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f);
        a(compoundDrawablesRelative[2], this.g);
    }

    void c() {
        this.i.a();
    }

    int e() {
        return this.i.h();
    }

    int f() {
        return this.i.i();
    }

    int g() {
        return this.i.j();
    }

    int[] h() {
        return this.i.k();
    }

    int i() {
        return this.i.l();
    }

    ColorStateList j() {
        p2 p2Var = this.h;
        if (p2Var != null) {
            return p2Var.a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        p2 p2Var = this.h;
        if (p2Var != null) {
            return p2Var.b;
        }
        return null;
    }

    boolean l() {
        return this.i.p();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:51:0x0105  */
    /* JADX WARN: Code duplicated, block: B:59:0x012c  */
    /* JADX WARN: Code duplicated, block: B:70:0x0157 A[PHI: r11 r13
      0x0157: PHI (r11v4 android.content.res.ColorStateList) = (r11v3 android.content.res.ColorStateList), (r11v9 android.content.res.ColorStateList) binds: [B:60:0x0130, B:68:0x0150] A[DONT_GENERATE, DONT_INLINE]
      0x0157: PHI (r13v2 android.content.res.ColorStateList) = (r13v1 android.content.res.ColorStateList), (r13v4 android.content.res.ColorStateList) binds: [B:60:0x0130, B:68:0x0150] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:86:0x0192  */
    void m(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListC;
        String strO;
        ColorStateList colorStateListC2;
        String strO2;
        ColorStateList colorStateListC3;
        boolean zA;
        boolean z;
        int i2;
        ColorStateList colorStateListC4;
        k kVar;
        int i3;
        Context context = this.a.getContext();
        k kVarB = k.b();
        r2 r2VarU = r2.u(context, attributeSet, R$styleable.m, i, 0);
        int iN = r2VarU.n(R$styleable.AppCompatTextHelper_android_textAppearance, -1);
        int i4 = R$styleable.AppCompatTextHelper_android_drawableLeft;
        if (r2VarU.r(i4)) {
            this.b = d(context, kVarB, r2VarU.n(i4, 0));
        }
        int i5 = R$styleable.AppCompatTextHelper_android_drawableTop;
        if (r2VarU.r(i5)) {
            this.c = d(context, kVarB, r2VarU.n(i5, 0));
        }
        int i6 = R$styleable.AppCompatTextHelper_android_drawableRight;
        if (r2VarU.r(i6)) {
            this.d = d(context, kVarB, r2VarU.n(i6, 0));
        }
        int i7 = R$styleable.AppCompatTextHelper_android_drawableBottom;
        if (r2VarU.r(i7)) {
            this.e = d(context, kVarB, r2VarU.n(i7, 0));
        }
        int i8 = Build.VERSION.SDK_INT;
        int i9 = R$styleable.AppCompatTextHelper_android_drawableStart;
        if (r2VarU.r(i9)) {
            this.f = d(context, kVarB, r2VarU.n(i9, 0));
        }
        int i10 = R$styleable.AppCompatTextHelper_android_drawableEnd;
        if (r2VarU.r(i10)) {
            this.g = d(context, kVarB, r2VarU.n(i10, 0));
        }
        r2VarU.v();
        boolean z2 = this.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (iN != -1) {
            r2 r2VarS = r2.s(context, iN, R$styleable.L);
            if (z2) {
                zA = false;
                z = false;
            } else {
                int i11 = R$styleable.TextAppearance_textAllCaps;
                if (r2VarS.r(i11)) {
                    zA = r2VarS.a(i11, false);
                    z = true;
                } else {
                    zA = false;
                    z = false;
                }
            }
            C(context, r2VarS);
            if (i8 < 23) {
                int i12 = R$styleable.TextAppearance_android_textColor;
                colorStateListC2 = r2VarS.r(i12) ? r2VarS.c(i12) : null;
                int i13 = R$styleable.TextAppearance_android_textColorHint;
                colorStateListC3 = r2VarS.r(i13) ? r2VarS.c(i13) : null;
                int i14 = R$styleable.TextAppearance_android_textColorLink;
                colorStateListC = r2VarS.r(i14) ? r2VarS.c(i14) : null;
            } else {
                colorStateListC = null;
                colorStateListC2 = null;
                colorStateListC3 = null;
            }
            int i15 = R$styleable.TextAppearance_textLocale;
            strO2 = r2VarS.r(i15) ? r2VarS.o(i15) : null;
            if (i8 >= 26) {
                int i16 = R$styleable.TextAppearance_fontVariationSettings;
                if (r2VarS.r(i16)) {
                    strO = r2VarS.o(i16);
                } else {
                    strO = null;
                }
            } else {
                strO = null;
            }
            r2VarS.v();
        } else {
            colorStateListC = null;
            strO = null;
            colorStateListC2 = null;
            strO2 = null;
            colorStateListC3 = null;
            zA = false;
            z = false;
        }
        r2 r2VarU2 = r2.u(context, attributeSet, R$styleable.L, i, 0);
        if (z2) {
            i2 = 23;
        } else {
            int i17 = R$styleable.TextAppearance_textAllCaps;
            if (r2VarU2.r(i17)) {
                zA = r2VarU2.a(i17, false);
                i2 = 23;
                z = true;
            } else {
                i2 = 23;
            }
        }
        if (i8 < i2) {
            int i18 = R$styleable.TextAppearance_android_textColor;
            if (r2VarU2.r(i18)) {
                colorStateListC2 = r2VarU2.c(i18);
            }
            int i19 = R$styleable.TextAppearance_android_textColorHint;
            if (r2VarU2.r(i19)) {
                colorStateListC3 = r2VarU2.c(i19);
            }
            int i20 = R$styleable.TextAppearance_android_textColorLink;
            if (r2VarU2.r(i20)) {
                colorStateListC4 = r2VarU2.c(i20);
            } else {
                colorStateListC4 = colorStateListC;
            }
        } else {
            colorStateListC4 = colorStateListC;
        }
        int i21 = R$styleable.TextAppearance_textLocale;
        if (r2VarU2.r(i21)) {
            strO2 = r2VarU2.o(i21);
        }
        if (i8 >= 26) {
            int i22 = R$styleable.TextAppearance_fontVariationSettings;
            if (r2VarU2.r(i22)) {
                strO = r2VarU2.o(i22);
            }
        }
        if (i8 >= 28) {
            int i23 = R$styleable.TextAppearance_android_textSize;
            if (r2VarU2.r(i23)) {
                kVar = kVarB;
                if (r2VarU2.f(i23, -1) == 0) {
                    this.a.setTextSize(0, 0.0f);
                }
            } else {
                kVar = kVarB;
            }
        } else {
            kVar = kVarB;
        }
        C(context, r2VarU2);
        r2VarU2.v();
        if (colorStateListC2 != null) {
            this.a.setTextColor(colorStateListC2);
        }
        if (colorStateListC3 != null) {
            this.a.setHintTextColor(colorStateListC3);
        }
        if (colorStateListC4 != null) {
            this.a.setLinkTextColor(colorStateListC4);
        }
        if (!z2 && z) {
            r(zA);
        }
        Typeface typeface = this.l;
        if (typeface != null) {
            if (this.k == -1) {
                this.a.setTypeface(typeface, this.j);
            } else {
                this.a.setTypeface(typeface);
            }
        }
        if (strO != null) {
            this.a.setFontVariationSettings(strO);
        }
        if (strO2 != null) {
            if (i8 >= 24) {
                this.a.setTextLocales(LocaleList.forLanguageTags(strO2));
            } else if (i8 >= 21) {
                this.a.setTextLocale(Locale.forLanguageTag(strO2.substring(0, strO2.indexOf(44))));
            }
        }
        this.i.q(attributeSet, i);
        if (androidx.core.widget.b.P && this.i.l() != 0) {
            int[] iArrK = this.i.k();
            if (iArrK.length > 0) {
                if (this.a.getAutoSizeStepGranularity() != -1.0f) {
                    this.a.setAutoSizeTextTypeUniformWithConfiguration(this.i.i(), this.i.h(), this.i.j(), 0);
                } else {
                    this.a.setAutoSizeTextTypeUniformWithPresetSizes(iArrK, 0);
                }
            }
        }
        r2 r2VarT = r2.t(context, attributeSet, R$styleable.n);
        int iN2 = r2VarT.n(R$styleable.AppCompatTextView_drawableLeftCompat, -1);
        k kVar2 = kVar;
        Drawable drawableC = iN2 != -1 ? kVar2.c(context, iN2) : null;
        int iN3 = r2VarT.n(R$styleable.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawableC2 = iN3 != -1 ? kVar2.c(context, iN3) : null;
        int iN4 = r2VarT.n(R$styleable.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawableC3 = iN4 != -1 ? kVar2.c(context, iN4) : null;
        int iN5 = r2VarT.n(R$styleable.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawableC4 = iN5 != -1 ? kVar2.c(context, iN5) : null;
        int iN6 = r2VarT.n(R$styleable.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawableC5 = iN6 != -1 ? kVar2.c(context, iN6) : null;
        int iN7 = r2VarT.n(R$styleable.AppCompatTextView_drawableEndCompat, -1);
        x(drawableC, drawableC2, drawableC3, drawableC4, drawableC5, iN7 != -1 ? kVar2.c(context, iN7) : null);
        int i24 = R$styleable.AppCompatTextView_drawableTint;
        if (r2VarT.r(i24)) {
            androidx.core.widget.e0.h(this.a, r2VarT.c(i24));
        }
        int i25 = R$styleable.AppCompatTextView_drawableTintMode;
        if (r2VarT.r(i25)) {
            i3 = -1;
            androidx.core.widget.e0.i(this.a, o1.e(r2VarT.k(i25, -1), null));
        } else {
            i3 = -1;
        }
        int iF = r2VarT.f(R$styleable.AppCompatTextView_firstBaselineToTopHeight, i3);
        int iF2 = r2VarT.f(R$styleable.AppCompatTextView_lastBaselineToBottomHeight, i3);
        int iF3 = r2VarT.f(R$styleable.AppCompatTextView_lineHeight, i3);
        r2VarT.v();
        if (iF != i3) {
            androidx.core.widget.e0.k(this.a, iF);
        }
        if (iF2 != i3) {
            androidx.core.widget.e0.l(this.a, iF2);
        }
        if (iF3 != i3) {
            androidx.core.widget.e0.m(this.a, iF3);
        }
    }

    void n(boolean z, int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.P) {
            return;
        }
        c();
    }

    void o() {
        b();
    }

    void p(Context context, int i) {
        String strO;
        ColorStateList colorStateListC;
        r2 r2VarS = r2.s(context, i, R$styleable.L);
        int i2 = R$styleable.TextAppearance_textAllCaps;
        if (r2VarS.r(i2)) {
            r(r2VarS.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = R$styleable.TextAppearance_android_textColor;
            if (r2VarS.r(i4) && (colorStateListC = r2VarS.c(i4)) != null) {
                this.a.setTextColor(colorStateListC);
            }
        }
        int i5 = R$styleable.TextAppearance_android_textSize;
        if (r2VarS.r(i5) && r2VarS.f(i5, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        C(context, r2VarS);
        if (i3 >= 26) {
            int i6 = R$styleable.TextAppearance_fontVariationSettings;
            if (r2VarS.r(i6) && (strO = r2VarS.o(i6)) != null) {
                this.a.setFontVariationSettings(strO);
            }
        }
        r2VarS.v();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.a.setTypeface(typeface, this.j);
        }
    }

    public void q(Runnable runnable) {
        this.a.post(runnable);
    }

    void r(boolean z) {
        this.a.setAllCaps(z);
    }

    void s(int i, int i2, int i3, int i4) {
        this.i.r(i, i2, i3, i4);
    }

    void t(int[] iArr, int i) {
        this.i.s(iArr, i);
    }

    void u(int i) {
        this.i.t(i);
    }

    void v(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new p2();
        }
        p2 p2Var = this.h;
        p2Var.a = colorStateList;
        p2Var.d = colorStateList != null;
        y();
    }

    void w(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new p2();
        }
        p2 p2Var = this.h;
        p2Var.b = mode;
        p2Var.c = mode != null;
        y();
    }

    void z(int i, float f) {
        if (androidx.core.widget.b.P || l()) {
            return;
        }
        A(i, f);
    }
}
