package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes.dex */
abstract class n2 {
    private static final ThreadLocal a = new ThreadLocal();
    static final int[] b = {-16842910};
    static final int[] c = {R.attr.state_focused};
    static final int[] d = {R.attr.state_activated};
    static final int[] e = {R.attr.state_pressed};
    static final int[] f = {R.attr.state_checked};
    static final int[] g = {R.attr.state_selected};
    static final int[] h = {-16842919, -16842908};
    static final int[] i = new int[0];
    private static final int[] j = new int[1];

    public static int a(Context context, int i2) {
        ColorStateList colorStateListD = d(context, i2);
        if (colorStateListD != null && colorStateListD.isStateful()) {
            return colorStateListD.getColorForState(b, colorStateListD.getDefaultColor());
        }
        TypedValue typedValueE = e();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValueE, true);
        return c(context, i2, typedValueE.getFloat());
    }

    public static int b(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        r2 r2VarT = r2.t(context, null, iArr);
        try {
            return r2VarT.b(0, 0);
        } finally {
            r2VarT.v();
        }
    }

    static int c(Context context, int i2, float f2) {
        int iB = b(context, i2);
        return p.a.m(iB, Math.round(Color.alpha(iB) * f2));
    }

    public static ColorStateList d(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        r2 r2VarT = r2.t(context, null, iArr);
        try {
            return r2VarT.c(0);
        } finally {
            r2VarT.v();
        }
    }

    private static TypedValue e() {
        ThreadLocal threadLocal = a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }
}
