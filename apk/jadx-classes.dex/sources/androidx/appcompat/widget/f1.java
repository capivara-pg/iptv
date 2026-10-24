package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class f1 extends ToggleButton {
    private final p0 a;

    public f1(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    public f1(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        p0 p0Var = new p0(this);
        this.a = p0Var;
        p0Var.m(attributeSet, i);
    }
}
