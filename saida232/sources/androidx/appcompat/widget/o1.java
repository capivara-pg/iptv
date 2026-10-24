package androidx.appcompat.widget;

import android.R;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public abstract class o1 {
    private static final int[] a = {R.attr.state_checked};
    private static final int[] b = new int[0];
    public static final Rect c = new Rect();
    private static Class d;

    static {
        try {
            d = Class.forName("android.graphics.Insets");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static boolean a(Drawable drawable) {
        if (!(drawable instanceof DrawableContainer)) {
            if (drawable instanceof q.x) {
                return a(((q.x) drawable).a());
            }
            if (drawable instanceof androidx.appcompat.graphics.drawable.m) {
                return a(((androidx.appcompat.graphics.drawable.m) drawable).getWrappedDrawable());
            }
            if (drawable instanceof ScaleDrawable) {
                return a(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
            return true;
        }
        for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
            if (!a(drawable2)) {
                return false;
            }
        }
        return true;
    }

    static void b(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            c(drawable);
        }
    }

    private static void c(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(a);
        } else {
            drawable.setState(b);
        }
        drawable.setState(state);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x008d  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static Rect d(Drawable drawable) {
        byte b2;
        if (Build.VERSION.SDK_INT >= 29) {
            Insets opticalInsets = drawable.getOpticalInsets();
            Rect rect = new Rect();
            rect.left = opticalInsets.left;
            rect.right = opticalInsets.right;
            rect.top = opticalInsets.top;
            rect.bottom = opticalInsets.bottom;
            return rect;
        }
        if (d != null) {
            try {
                Drawable drawableQ = q.h.q(drawable);
                Object objInvoke = drawableQ.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(drawableQ, new Object[0]);
                if (objInvoke != null) {
                    Rect rect2 = new Rect();
                    for (Field field : d.getFields()) {
                        String name = field.getName();
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    b2 = 3;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 115029:
                                if (name.equals("top")) {
                                    b2 = 1;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 3317767:
                                if (name.equals("left")) {
                                    b2 = 0;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 108511772:
                                if (name.equals("right")) {
                                    b2 = 2;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            default:
                                b2 = -1;
                                break;
                        }
                        if (b2 == 0) {
                            rect2.left = field.getInt(objInvoke);
                        } else if (b2 == 1) {
                            rect2.top = field.getInt(objInvoke);
                        } else if (b2 == 2) {
                            rect2.right = field.getInt(objInvoke);
                        } else if (b2 == 3) {
                            rect2.bottom = field.getInt(objInvoke);
                        }
                    }
                    return rect2;
                }
            } catch (Exception unused) {
                Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
            }
        }
        return c;
    }

    public static PorterDuff.Mode e(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
