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

/* JADX INFO: loaded from: classes.dex */
class l0 {
    private final TextView a;
    private f2 b;
    private f2 c;
    private f2 d;
    private f2 e;
    private f2 f;
    private f2 g;
    private f2 h;
    private final x0 i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    class a extends t.g.c {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ WeakReference c;

        a(int i, int i2, WeakReference weakReference) {
            this.a = i;
            this.b = i2;
            this.c = weakReference;
        }

        public void onFontRetrievalFailed(int i) {
        }

        public void onFontRetrieved(Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.a) != -1) {
                typeface = Typeface.create(typeface, i, (this.b & 2) != 0);
            }
            l0.this.n(this.c, typeface);
        }
    }

    l0(TextView textView) {
        this.a = textView;
        this.i = new x0(textView);
    }

    private void A(int i, float f) {
        this.i.v(i, f);
    }

    private void B(Context context, h2 h2Var) {
        String strO;
        this.j = h2Var.k(R$styleable.TextAppearance_android_textStyle, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iK = h2Var.k(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = R$styleable.TextAppearance_android_fontFamily;
        if (!h2Var.s(i2) && !h2Var.s(R$styleable.TextAppearance_fontFamily)) {
            int i3 = R$styleable.TextAppearance_android_typeface;
            if (h2Var.s(i3)) {
                this.m = false;
                int iK2 = h2Var.k(i3, 1);
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
        if (h2Var.s(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = h2Var.j(i2, this.j, new a(i5, i6, new WeakReference(this.a)));
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
        if (this.l != null || (strO = h2Var.o(i2)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strO, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strO, 0), this.k, (this.j & 2) != 0);
        }
    }

    private void a(Drawable drawable, f2 f2Var) {
        if (drawable == null || f2Var == null) {
            return;
        }
        j.i(drawable, f2Var, this.a.getDrawableState());
    }

    private static f2 d(Context context, j jVar, int i) {
        ColorStateList colorStateListF = jVar.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        f2 f2Var = new f2();
        f2Var.d = true;
        f2Var.a = colorStateListF;
        return f2Var;
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
        f2 f2Var = this.h;
        this.b = f2Var;
        this.c = f2Var;
        this.d = f2Var;
        this.e = f2Var;
        this.f = f2Var;
        this.g = f2Var;
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
        f2 f2Var = this.h;
        if (f2Var != null) {
            return f2Var.a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        f2 f2Var = this.h;
        if (f2Var != null) {
            return f2Var.b;
        }
        return null;
    }

    boolean l() {
        return this.i.p();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:45:0x0100  */
    /* JADX WARN: Code duplicated, block: B:46:0x0105  */
    /* JADX WARN: Code duplicated, block: B:48:0x0108  */
    /* JADX WARN: Code duplicated, block: B:50:0x0110  */
    /* JADX WARN: Code duplicated, block: B:51:0x0115  */
    /* JADX WARN: Code duplicated, block: B:59:0x013c  */
    /* JADX WARN: Code duplicated, block: B:78:0x0183  */
    /* JADX WARN: Code duplicated, block: B:86:0x01a2  */
    void m(AttributeSet attributeSet, int i) {
        String strO;
        ColorStateList colorStateListC;
        String strO2;
        boolean zA;
        ColorStateList colorStateListC2;
        ColorStateList colorStateListC3;
        boolean z;
        int i2;
        String strO3;
        j jVar;
        int i3;
        int i4;
        int i5;
        Context context = this.a.getContext();
        j jVarB = j.b();
        int[] iArr = R$styleable.m;
        h2 h2VarV = h2.v(context, attributeSet, iArr, i, 0);
        TextView textView = this.a;
        androidx.core.view.m0.j0(textView, textView.getContext(), iArr, attributeSet, h2VarV.r(), i, 0);
        int iN = h2VarV.n(R$styleable.AppCompatTextHelper_android_textAppearance, -1);
        int i6 = R$styleable.AppCompatTextHelper_android_drawableLeft;
        if (h2VarV.s(i6)) {
            this.b = d(context, jVarB, h2VarV.n(i6, 0));
        }
        int i7 = R$styleable.AppCompatTextHelper_android_drawableTop;
        if (h2VarV.s(i7)) {
            this.c = d(context, jVarB, h2VarV.n(i7, 0));
        }
        int i8 = R$styleable.AppCompatTextHelper_android_drawableRight;
        if (h2VarV.s(i8)) {
            this.d = d(context, jVarB, h2VarV.n(i8, 0));
        }
        int i9 = R$styleable.AppCompatTextHelper_android_drawableBottom;
        if (h2VarV.s(i9)) {
            this.e = d(context, jVarB, h2VarV.n(i9, 0));
        }
        int i10 = Build.VERSION.SDK_INT;
        int i11 = R$styleable.AppCompatTextHelper_android_drawableStart;
        if (h2VarV.s(i11)) {
            this.f = d(context, jVarB, h2VarV.n(i11, 0));
        }
        int i12 = R$styleable.AppCompatTextHelper_android_drawableEnd;
        if (h2VarV.s(i12)) {
            this.g = d(context, jVarB, h2VarV.n(i12, 0));
        }
        h2VarV.w();
        boolean z2 = this.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (iN != -1) {
            h2 h2VarT = h2.t(context, iN, R$styleable.L);
            if (z2) {
                zA = false;
                z = false;
            } else {
                int i13 = R$styleable.TextAppearance_textAllCaps;
                if (h2VarT.s(i13)) {
                    zA = h2VarT.a(i13, false);
                    z = true;
                } else {
                    zA = false;
                    z = false;
                }
            }
            B(context, h2VarT);
            if (i10 < 23) {
                int i14 = R$styleable.TextAppearance_android_textColor;
                colorStateListC = h2VarT.s(i14) ? h2VarT.c(i14) : null;
                int i15 = R$styleable.TextAppearance_android_textColorHint;
                colorStateListC2 = h2VarT.s(i15) ? h2VarT.c(i15) : null;
                int i16 = R$styleable.TextAppearance_android_textColorLink;
                colorStateListC3 = h2VarT.s(i16) ? h2VarT.c(i16) : null;
                i4 = R$styleable.TextAppearance_textLocale;
                if (h2VarT.s(i4)) {
                    strO2 = h2VarT.o(i4);
                } else {
                    strO2 = null;
                }
                if (i10 >= 26) {
                    i5 = R$styleable.TextAppearance_fontVariationSettings;
                    if (h2VarT.s(i5)) {
                        strO = h2VarT.o(i5);
                    } else {
                        strO = null;
                    }
                } else {
                    strO = null;
                }
                h2VarT.w();
            } else {
                colorStateListC = null;
                colorStateListC2 = null;
            }
            i4 = R$styleable.TextAppearance_textLocale;
            if (h2VarT.s(i4)) {
                strO2 = h2VarT.o(i4);
            } else {
                strO2 = null;
            }
            if (i10 >= 26) {
                i5 = R$styleable.TextAppearance_fontVariationSettings;
                if (h2VarT.s(i5)) {
                    strO = h2VarT.o(i5);
                } else {
                    strO = null;
                }
            } else {
                strO = null;
            }
            h2VarT.w();
        } else {
            strO = null;
            colorStateListC = null;
            strO2 = null;
            zA = false;
            colorStateListC2 = null;
            colorStateListC3 = null;
            z = false;
        }
        h2 h2VarV2 = h2.v(context, attributeSet, R$styleable.L, i, 0);
        if (z2) {
            i2 = 23;
        } else {
            int i17 = R$styleable.TextAppearance_textAllCaps;
            if (h2VarV2.s(i17)) {
                zA = h2VarV2.a(i17, false);
                i2 = 23;
                z = true;
            } else {
                i2 = 23;
            }
        }
        if (i10 < i2) {
            int i18 = R$styleable.TextAppearance_android_textColor;
            if (h2VarV2.s(i18)) {
                colorStateListC = h2VarV2.c(i18);
            }
            int i19 = R$styleable.TextAppearance_android_textColorHint;
            if (h2VarV2.s(i19)) {
                colorStateListC2 = h2VarV2.c(i19);
            }
            int i20 = R$styleable.TextAppearance_android_textColorLink;
            if (h2VarV2.s(i20)) {
                colorStateListC3 = h2VarV2.c(i20);
            }
        }
        int i21 = R$styleable.TextAppearance_textLocale;
        if (h2VarV2.s(i21)) {
            strO2 = h2VarV2.o(i21);
        }
        if (i10 >= 26) {
            int i22 = R$styleable.TextAppearance_fontVariationSettings;
            if (h2VarV2.s(i22)) {
                strO3 = h2VarV2.o(i22);
            } else {
                strO3 = strO;
            }
        } else {
            strO3 = strO;
        }
        if (i10 >= 28) {
            int i23 = R$styleable.TextAppearance_android_textSize;
            if (h2VarV2.s(i23)) {
                jVar = jVarB;
                if (h2VarV2.f(i23, -1) == 0) {
                    this.a.setTextSize(0, 0.0f);
                }
            } else {
                jVar = jVarB;
            }
        } else {
            jVar = jVarB;
        }
        B(context, h2VarV2);
        h2VarV2.w();
        if (colorStateListC != null) {
            this.a.setTextColor(colorStateListC);
        }
        if (colorStateListC2 != null) {
            this.a.setHintTextColor(colorStateListC2);
        }
        if (colorStateListC3 != null) {
            this.a.setLinkTextColor(colorStateListC3);
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
        if (strO3 != null) {
            this.a.setFontVariationSettings(strO3);
        }
        if (strO2 != null) {
            if (i10 >= 24) {
                this.a.setTextLocales(LocaleList.forLanguageTags(strO2));
            } else {
                this.a.setTextLocale(Locale.forLanguageTag(strO2.substring(0, strO2.indexOf(44))));
            }
        }
        this.i.q(attributeSet, i);
        if (androidx.core.widget.b.O && this.i.l() != 0) {
            int[] iArrK = this.i.k();
            if (iArrK.length > 0) {
                if (this.a.getAutoSizeStepGranularity() != -1.0f) {
                    this.a.setAutoSizeTextTypeUniformWithConfiguration(this.i.i(), this.i.h(), this.i.j(), 0);
                } else {
                    this.a.setAutoSizeTextTypeUniformWithPresetSizes(iArrK, 0);
                }
            }
        }
        h2 h2VarU = h2.u(context, attributeSet, R$styleable.n);
        int iN2 = h2VarU.n(R$styleable.AppCompatTextView_drawableLeftCompat, -1);
        j jVar2 = jVar;
        Drawable drawableC = iN2 != -1 ? jVar2.c(context, iN2) : null;
        int iN3 = h2VarU.n(R$styleable.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawableC2 = iN3 != -1 ? jVar2.c(context, iN3) : null;
        int iN4 = h2VarU.n(R$styleable.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawableC3 = iN4 != -1 ? jVar2.c(context, iN4) : null;
        int iN5 = h2VarU.n(R$styleable.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawableC4 = iN5 != -1 ? jVar2.c(context, iN5) : null;
        int iN6 = h2VarU.n(R$styleable.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawableC5 = iN6 != -1 ? jVar2.c(context, iN6) : null;
        int iN7 = h2VarU.n(R$styleable.AppCompatTextView_drawableEndCompat, -1);
        x(drawableC, drawableC2, drawableC3, drawableC4, drawableC5, iN7 != -1 ? jVar2.c(context, iN7) : null);
        int i24 = R$styleable.AppCompatTextView_drawableTint;
        if (h2VarU.s(i24)) {
            androidx.core.widget.v.h(this.a, h2VarU.c(i24));
        }
        int i25 = R$styleable.AppCompatTextView_drawableTintMode;
        if (h2VarU.s(i25)) {
            i3 = -1;
            androidx.core.widget.v.i(this.a, i1.e(h2VarU.k(i25, -1), null));
        } else {
            i3 = -1;
        }
        int iF = h2VarU.f(R$styleable.AppCompatTextView_firstBaselineToTopHeight, i3);
        int iF2 = h2VarU.f(R$styleable.AppCompatTextView_lastBaselineToBottomHeight, i3);
        int iF3 = h2VarU.f(R$styleable.AppCompatTextView_lineHeight, i3);
        h2VarU.w();
        if (iF != i3) {
            androidx.core.widget.v.k(this.a, iF);
        }
        if (iF2 != i3) {
            androidx.core.widget.v.l(this.a, iF2);
        }
        if (iF3 != i3) {
            androidx.core.widget.v.m(this.a, iF3);
        }
    }

    void n(WeakReference weakReference, Typeface typeface) {
        if (this.m) {
            this.l = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.j);
            }
        }
    }

    void o(boolean z, int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.O) {
            return;
        }
        c();
    }

    void p() {
        b();
    }

    void q(Context context, int i) {
        String strO;
        ColorStateList colorStateListC;
        h2 h2VarT = h2.t(context, i, R$styleable.L);
        int i2 = R$styleable.TextAppearance_textAllCaps;
        if (h2VarT.s(i2)) {
            r(h2VarT.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = R$styleable.TextAppearance_android_textColor;
            if (h2VarT.s(i4) && (colorStateListC = h2VarT.c(i4)) != null) {
                this.a.setTextColor(colorStateListC);
            }
        }
        int i5 = R$styleable.TextAppearance_android_textSize;
        if (h2VarT.s(i5) && h2VarT.f(i5, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        B(context, h2VarT);
        if (i3 >= 26) {
            int i6 = R$styleable.TextAppearance_fontVariationSettings;
            if (h2VarT.s(i6) && (strO = h2VarT.o(i6)) != null) {
                this.a.setFontVariationSettings(strO);
            }
        }
        h2VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.a.setTypeface(typeface, this.j);
        }
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
            this.h = new f2();
        }
        f2 f2Var = this.h;
        f2Var.a = colorStateList;
        f2Var.d = colorStateList != null;
        y();
    }

    void w(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new f2();
        }
        f2 f2Var = this.h;
        f2Var.b = mode;
        f2Var.c = mode != null;
        y();
    }

    void z(int i, float f) {
        if (androidx.core.widget.b.O || l()) {
            return;
        }
        A(i, f);
    }
}
