package androidx.core.view;

import android.graphics.Rect;
import android.view.Gravity;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class j {
    public static void a(int i, int i2, int i3, Rect rect, Rect rect2, int i4) {
        Gravity.apply(i, i2, i3, rect, rect2, i4);
    }

    public static int b(int i, int i2) {
        return Gravity.getAbsoluteGravity(i, i2);
    }
}
