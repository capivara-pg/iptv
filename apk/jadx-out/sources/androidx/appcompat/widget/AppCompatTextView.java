package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements androidx.core.widget.w, androidx.core.widget.b {
    private final e mBackgroundTintHelper;
    private Future<b0.o> mPrecomputedTextFuture;
    private final d0 mTextClassifierHelper;
    private final l0 mTextHelper;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    private void c() {
        Future<b0.o> future = this.mPrecomputedTextFuture;
        if (future != null) {
            try {
                this.mPrecomputedTextFuture = null;
                androidx.appcompat.app.r.a(future.get());
                androidx.core.widget.v.n(this, null);
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.mBackgroundTintHelper;
        if (eVar != null) {
            eVar.b();
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.O) {
            return super.getAutoSizeMaxTextSize();
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            return l0Var.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (androidx.core.widget.b.O) {
            return super.getAutoSizeMinTextSize();
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            return l0Var.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (androidx.core.widget.b.O) {
            return super.getAutoSizeStepGranularity();
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            return l0Var.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (androidx.core.widget.b.O) {
            return super.getAutoSizeTextAvailableSizes();
        }
        l0 l0Var = this.mTextHelper;
        return l0Var != null ? l0Var.h() : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.O) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            return l0Var.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return androidx.core.widget.v.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return androidx.core.widget.v.c(this);
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.mBackgroundTintHelper;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.mBackgroundTintHelper;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        d0 d0Var;
        return (Build.VERSION.SDK_INT >= 28 || (d0Var = this.mTextClassifierHelper) == null) ? super.getTextClassifier() : d0Var.a();
    }

    public b0.o.a getTextMetricsParamsCompat() {
        return androidx.core.widget.v.g(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return l.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.o(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        c();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        l0 l0Var = this.mTextHelper;
        if (l0Var == null || androidx.core.widget.b.O || !l0Var.l()) {
            return;
        }
        this.mTextHelper.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.O) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.s(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (androidx.core.widget.b.O) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.t(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (androidx.core.widget.b.O) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.u(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.mBackgroundTintHelper;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.mBackgroundTintHelper;
        if (eVar != null) {
            eVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.v.q(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            androidx.core.widget.v.k(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            androidx.core.widget.v.l(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        androidx.core.widget.v.m(this, i);
    }

    public void setPrecomputedText(b0.o oVar) {
        androidx.core.widget.v.n(this, oVar);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.mBackgroundTintHelper;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.mBackgroundTintHelper;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    @Override // androidx.core.widget.w
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.mTextHelper.v(colorStateList);
        this.mTextHelper.b();
    }

    @Override // androidx.core.widget.w
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.mTextHelper.w(mode);
        this.mTextHelper.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.q(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        d0 d0Var;
        if (Build.VERSION.SDK_INT >= 28 || (d0Var = this.mTextClassifierHelper) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            d0Var.b(textClassifier);
        }
    }

    public void setTextFuture(Future<b0.o> future) {
        this.mPrecomputedTextFuture = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(b0.o.a aVar) {
        androidx.core.widget.v.p(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (androidx.core.widget.b.O) {
            super.setTextSize(i, f);
            return;
        }
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.z(i, f);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        Typeface typefaceA = (typeface == null || i <= 0) ? null : u.e.a(getContext(), typeface, i);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(e2.b(context), attributeSet, i);
        d2.a(this, getContext());
        e eVar = new e(this);
        this.mBackgroundTintHelper = eVar;
        eVar.e(attributeSet, i);
        l0 l0Var = new l0(this);
        this.mTextHelper = l0Var;
        l0Var.m(attributeSet, i);
        l0Var.b();
        this.mTextClassifierHelper = new d0(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? f.b.d(context, i) : null, i2 != 0 ? f.b.d(context, i2) : null, i3 != 0 ? f.b.d(context, i3) : null, i4 != 0 ? f.b.d(context, i4) : null);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? f.b.d(context, i) : null, i2 != 0 ? f.b.d(context, i2) : null, i3 != 0 ? f.b.d(context, i3) : null, i4 != 0 ? f.b.d(context, i4) : null);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.p();
        }
    }
}
