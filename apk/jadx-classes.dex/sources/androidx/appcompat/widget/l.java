package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class l extends EditText implements androidx.core.view.g0 {
    private final f mBackgroundTintHelper;
    private final g0 mTextClassifierHelper;
    private final p0 mTextHelper;

    public l(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.b();
        }
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.b();
        }
    }

    @Override // androidx.core.view.g0
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    @Override // androidx.core.view.g0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            return fVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        g0 g0Var;
        return (Build.VERSION.SDK_INT >= 28 || (g0Var = this.mTextClassifierHelper) == null) ? super.getTextClassifier() : g0Var.a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.e0.q(this, callback));
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.i(colorStateList);
        }
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        p0 p0Var = this.mTextHelper;
        if (p0Var != null) {
            p0Var.p(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        g0 g0Var;
        if (Build.VERSION.SDK_INT >= 28 || (g0Var = this.mTextClassifierHelper) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            g0Var.b(textClassifier);
        }
    }

    public l(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.editTextStyle);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    public l(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        f fVar = new f(this);
        this.mBackgroundTintHelper = fVar;
        fVar.e(attributeSet, i);
        p0 p0Var = new p0(this);
        this.mTextHelper = p0Var;
        p0Var.m(attributeSet, i);
        p0Var.b();
        this.mTextClassifierHelper = new g0(this);
    }
}
