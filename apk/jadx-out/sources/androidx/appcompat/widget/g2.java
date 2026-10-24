package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class g2 extends x1 {
    private final WeakReference b;

    public g2(Context context, Resources resources) {
        super(resources);
        this.b = new WeakReference(context);
    }

    @Override // androidx.appcompat.widget.x1, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = (Context) this.b.get();
        if (drawable != null && context != null) {
            w1.h().x(context, i, drawable);
        }
        return drawable;
    }
}
