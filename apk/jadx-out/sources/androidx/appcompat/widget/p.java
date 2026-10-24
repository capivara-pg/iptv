package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
class p extends PopupWindow {
    private static final boolean b = false;
    private boolean a;

    public p(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        h2 h2VarV = h2.v(context, attributeSet, R$styleable.D, i, i2);
        int i3 = R$styleable.PopupWindow_overlapAnchor;
        if (h2VarV.s(i3)) {
            b(h2VarV.a(i3, false));
        }
        setBackgroundDrawable(h2VarV.g(R$styleable.PopupWindow_android_popupBackground));
        h2VarV.w();
    }

    private void b(boolean z) {
        if (b) {
            this.a = z;
        } else {
            androidx.core.widget.k.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (b && this.a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (b && this.a) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (b && this.a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }
}
