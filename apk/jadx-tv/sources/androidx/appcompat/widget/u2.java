package androidx.appcompat.widget;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class u2 {
    public static void a(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            v2.f(view, charSequence);
        }
    }
}
