package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView$InnerLayout extends LinearLayout {
    private static final int[] a = {R.attr.background};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        r2 r2VarT = r2.t(context, attributeSet, a);
        setBackgroundDrawable(r2VarT.g(0));
        r2VarT.v();
    }
}
