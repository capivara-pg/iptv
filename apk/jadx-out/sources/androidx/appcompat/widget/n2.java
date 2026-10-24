package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class n2 extends Resources {
    private static boolean b;
    private final WeakReference a;

    public n2(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.a = new WeakReference(context);
    }

    public static boolean a() {
        return b;
    }

    public static boolean b() {
        a();
        return false;
    }

    final Drawable c(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        Context context = (Context) this.a.get();
        return context != null ? w1.h().t(context, this, i) : super.getDrawable(i);
    }
}
