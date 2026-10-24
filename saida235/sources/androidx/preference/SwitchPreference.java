package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
    private final a I;
    private CharSequence J;
    private CharSequence K;

    private class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SwitchPreference.this.a(Boolean.valueOf(z))) {
                SwitchPreference.this.G(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.I = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.b0, i, i2);
        J(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOn, R$styleable.SwitchPreference_android_summaryOn));
        I(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOff, R$styleable.SwitchPreference_android_summaryOff));
        M(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOn, R$styleable.SwitchPreference_android_switchTextOn));
        L(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOff, R$styleable.SwitchPreference_android_switchTextOff));
        H(i.b(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_disableDependentsState, R$styleable.SwitchPreference_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void N(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.D);
        }
        if (z) {
            Switch r4 = (Switch) view;
            r4.setTextOn(this.J);
            r4.setTextOff(this.K);
            r4.setOnCheckedChangeListener(this.I);
        }
    }

    private void O(View view) {
        if (((AccessibilityManager) c().getSystemService("accessibility")).isEnabled()) {
            N(view.findViewById(R.id.switch_widget));
            K(view.findViewById(R.id.summary));
        }
    }

    public void L(CharSequence charSequence) {
        this.K = charSequence;
        r();
    }

    public void M(CharSequence charSequence) {
        this.J = charSequence;
        r();
    }

    @Override // androidx.preference.Preference
    protected void y(View view) {
        super.y(view);
        O(view);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.switchPreferenceStyle, R.attr.switchPreferenceStyle));
    }
}
