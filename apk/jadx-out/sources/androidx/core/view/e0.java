package androidx.core.view;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: loaded from: classes.dex */
public final class e0 {
    private Object a;

    private e0(Object obj) {
        this.a = obj;
    }

    public static e0 b(Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? new e0(PointerIcon.getSystemIcon(context, i)) : new e0(null);
    }

    public Object a() {
        return this.a;
    }
}
