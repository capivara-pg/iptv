package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public abstract class d2 {
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

    public static void a(View view, Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R$styleable.o);
        try {
            if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static int b(Context context, int i2) {
        ColorStateList colorStateListE = e(context, i2);
        if (colorStateListE != null && colorStateListE.isStateful()) {
            return colorStateListE.getColorForState(b, colorStateListE.getDefaultColor());
        }
        TypedValue typedValueF = f();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValueF, true);
        return d(context, i2, typedValueF.getFloat());
    }

    public static int c(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        h2 h2VarU = h2.u(context, null, iArr);
        try {
            return h2VarU.b(0, 0);
        } finally {
            h2VarU.w();
        }
    }

    static int d(Context context, int i2, float f2) {
        int iC = c(context, i2);
        return u.a.m(iC, Math.round(Color.alpha(iC) * f2));
    }

    public static ColorStateList e(Context context, int i2) {
        int[] iArr = j;
        iArr[0] = i2;
        h2 h2VarU = h2.u(context, null, iArr);
        try {
            return h2VarU.c(0);
        } finally {
            h2VarU.w();
        }
    }

    private static TypedValue f() {
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
