package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class r extends MultiAutoCompleteTextView implements androidx.core.view.g0 {
    private static final int[] c = {R.attr.popupBackground};
    private final f a;
    private final p0 b;

    public r(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.a;
        if (fVar != null) {
            fVar.b();
        }
        p0 p0Var = this.b;
        if (p0Var != null) {
            p0Var.b();
        }
    }

    @Override // androidx.core.view.g0
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.a;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    @Override // androidx.core.view.g0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.a;
        if (fVar != null) {
            return fVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.a;
        if (fVar != null) {
            fVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.a;
        if (fVar != null) {
            fVar.g(i);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(d.b.d(getContext(), i));
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.a;
        if (fVar != null) {
            fVar.i(colorStateList);
        }
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.a;
        if (fVar != null) {
            fVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        p0 p0Var = this.b;
        if (p0Var != null) {
            p0Var.p(context, i);
        }
    }

    public r(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        r2 r2VarU = r2.u(getContext(), attributeSet, c, i, 0);
        if (r2VarU.r(0)) {
            setDropDownBackgroundDrawable(r2VarU.g(0));
        }
        r2VarU.v();
        f fVar = new f(this);
        this.a = fVar;
        fVar.e(attributeSet, i);
        p0 p0Var = new p0(this);
        this.b = p0Var;
        p0Var.m(attributeSet, i);
        p0Var.b();
    }
}
