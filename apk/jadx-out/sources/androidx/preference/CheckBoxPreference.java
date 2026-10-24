package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {
    private final a I;

    private class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (CheckBoxPreference.this.a(Boolean.valueOf(z))) {
                CheckBoxPreference.this.G(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void L(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.D);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.I);
        }
    }

    private void M(View view) {
        if (((AccessibilityManager) c().getSystemService("accessibility")).isEnabled()) {
            L(view.findViewById(R.id.checkbox));
            K(view.findViewById(R.id.summary));
        }
    }

    @Override // androidx.preference.Preference
    protected void y(View view) {
        super.y(view);
        M(view);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.I = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.r, i, i2);
        J(i.o(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOn, R$styleable.CheckBoxPreference_android_summaryOn));
        I(i.o(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOff, R$styleable.CheckBoxPreference_android_summaryOff));
        H(i.b(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_disableDependentsState, R$styleable.CheckBoxPreference_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.checkBoxPreferenceStyle, R.attr.checkBoxPreferenceStyle));
    }
}
