package androidx.core.widget;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public interface b {
    public static final boolean P;

    static {
        P = Build.VERSION.SDK_INT >= 27;
    }
}
