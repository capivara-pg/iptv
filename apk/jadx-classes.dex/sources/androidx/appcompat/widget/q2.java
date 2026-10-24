package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class q2 extends h2 {
    private final WeakReference b;

    public q2(Context context, Resources resources) {
        super(resources);
        this.b = new WeakReference(context);
    }

    @Override // androidx.appcompat.widget.h2, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = (Context) this.b.get();
        if (drawable != null && context != null) {
            e2.h().x(context, i, drawable);
        }
        return drawable;
    }
}
