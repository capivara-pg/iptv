package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class f extends Button implements androidx.core.widget.b, androidx.core.widget.w {
    private final e mBackgroundTintHelper;
    private final l0 mTextHelper;

    public f(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
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

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.o(z, i, i2, i3, i4);
        }
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
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.v.q(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        l0 l0Var = this.mTextHelper;
        if (l0Var != null) {
            l0Var.r(z);
        }
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

    public f(Context context, AttributeSet attributeSet, int i) {
        super(e2.b(context), attributeSet, i);
        d2.a(this, getContext());
        e eVar = new e(this);
        this.mBackgroundTintHelper = eVar;
        eVar.e(attributeSet, i);
        l0 l0Var = new l0(this);
        this.mTextHelper = l0Var;
        l0Var.m(attributeSet, i);
        l0Var.b();
    }
}
