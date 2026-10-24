package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class i extends CheckedTextView {
    private static final int[] b = {R.attr.checkMark};
    private final p0 a;

    public i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        p0 p0Var = this.a;
        if (p0Var != null) {
            p0Var.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(d.b.d(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.e0.q(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        p0 p0Var = this.a;
        if (p0Var != null) {
            p0Var.p(context, i);
        }
    }

    public i(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        p0 p0Var = new p0(this);
        this.a = p0Var;
        p0Var.m(attributeSet, i);
        p0Var.b();
        r2 r2VarU = r2.u(getContext(), attributeSet, b, i, 0);
        setCheckMarkDrawable(r2VarU.g(0));
        r2VarU.v();
    }
}
