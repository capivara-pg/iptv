package androidx.core.view;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: loaded from: classes.dex */
public final class f0 {
    private Object a;

    private f0(Object obj) {
        this.a = obj;
    }

    public static f0 b(Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? new f0(PointerIcon.getSystemIcon(context, i)) : new f0(null);
    }

    public Object a() {
        return this.a;
    }
}
