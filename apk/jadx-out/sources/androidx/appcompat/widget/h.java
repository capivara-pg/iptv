package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* JADX INFO: loaded from: classes.dex */
public class h extends CheckedTextView {
    private static final int[] b = {R.attr.checkMark};
    private final l0 a;

    public h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return l.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(f.b.d(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.v.q(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.q(context, i);
        }
    }

    public h(Context context, AttributeSet attributeSet, int i) {
        super(e2.b(context), attributeSet, i);
        d2.a(this, getContext());
        l0 l0Var = new l0(this);
        this.a = l0Var;
        l0Var.m(attributeSet, i);
        l0Var.b();
        h2 h2VarV = h2.v(getContext(), attributeSet, b, i, 0);
        setCheckMarkDrawable(h2VarV.g(0));
        h2VarV.w();
    }
}
