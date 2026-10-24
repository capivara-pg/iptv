package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: classes.dex */
public class a1 extends ToggleButton {
    private final l0 a;

    public a1(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    public a1(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d2.a(this, getContext());
        l0 l0Var = new l0(this);
        this.a = l0Var;
        l0Var.m(attributeSet, i);
    }
}
