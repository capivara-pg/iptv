package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public abstract class t {
    public static b a(MenuItem menuItem) {
        if (menuItem instanceof w.b) {
            return ((w.b) menuItem).a();
        }
        return null;
    }

    public static MenuItem b(MenuItem menuItem, b bVar) {
        return menuItem instanceof w.b ? ((w.b) menuItem).b(bVar) : menuItem;
    }

    public static void c(MenuItem menuItem, char c, int i) {
        if (menuItem instanceof w.b) {
            ((w.b) menuItem).setAlphabeticShortcut(c, i);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setAlphabeticShortcut(c, i);
        }
    }

    public static void d(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof w.b) {
            ((w.b) menuItem).setContentDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setContentDescription(charSequence);
        }
    }

    public static void e(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof w.b) {
            ((w.b) menuItem).setIconTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setIconTintList(colorStateList);
        }
    }

    public static void f(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof w.b) {
            ((w.b) menuItem).setIconTintMode(mode);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setIconTintMode(mode);
        }
    }

    public static void g(MenuItem menuItem, char c, int i) {
        if (menuItem instanceof w.b) {
            ((w.b) menuItem).setNumericShortcut(c, i);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setNumericShortcut(c, i);
        }
    }

    public static void h(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof w.b) {
            ((w.b) menuItem).setTooltipText(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setTooltipText(charSequence);
        }
    }
}
