package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class d extends AutoCompleteTextView {
    private static final int[] c = {R.attr.popupBackground};
    private final e a;
    private final l0 b;

    public d(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.a;
        if (eVar != null) {
            eVar.b();
        }
        l0 l0Var = this.b;
        if (l0Var != null) {
            l0Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.a;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return l.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.a;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.a;
        if (eVar != null) {
            eVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.v.q(this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(f.b.d(getContext(), i));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.a;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.a;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l0 l0Var = this.b;
        if (l0Var != null) {
            l0Var.q(context, i);
        }
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(e2.b(context), attributeSet, i);
        d2.a(this, getContext());
        h2 h2VarV = h2.v(getContext(), attributeSet, c, i, 0);
        if (h2VarV.s(0)) {
            setDropDownBackgroundDrawable(h2VarV.g(0));
        }
        h2VarV.w();
        e eVar = new e(this);
        this.a = eVar;
        eVar.e(attributeSet, i);
        l0 l0Var = new l0(this);
        this.b = l0Var;
        l0Var.m(attributeSet, i);
        l0Var.b();
    }
}
