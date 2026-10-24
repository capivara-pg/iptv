package androidx.transition;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
abstract class z {
    private static boolean a = true;

    static x a(ViewGroup viewGroup) {
        return new w(viewGroup);
    }

    private static void b(ViewGroup viewGroup, boolean z) {
        if (a) {
            try {
                viewGroup.suppressLayout(z);
            } catch (NoSuchMethodError unused) {
                a = false;
            }
        }
    }

    static void c(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            viewGroup.suppressLayout(z);
        } else {
            b(viewGroup, z);
        }
    }
}
