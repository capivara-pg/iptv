package androidx.core.widget;

import android.os.Build;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public interface b {
    public static final boolean P;

    static {
        P = Build.VERSION.SDK_INT >= 27;
    }
}
