package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class SwitchPreferenceCompat extends TwoStatePreference {
    private final a I;
    private CharSequence J;
    private CharSequence K;

    private class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SwitchPreferenceCompat.this.a(Boolean.valueOf(z))) {
                SwitchPreferenceCompat.this.G(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.I = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.c0, i, i2);
        J(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_summaryOn, R$styleable.SwitchPreferenceCompat_android_summaryOn));
        I(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_summaryOff, R$styleable.SwitchPreferenceCompat_android_summaryOff));
        M(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_switchTextOn, R$styleable.SwitchPreferenceCompat_android_switchTextOn));
        L(i.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_switchTextOff, R$styleable.SwitchPreferenceCompat_android_switchTextOff));
        H(i.b(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_disableDependentsState, R$styleable.SwitchPreferenceCompat_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void N(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.D);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOn(this.J);
            switchCompat.setTextOff(this.K);
            switchCompat.setOnCheckedChangeListener(this.I);
        }
    }

    private void O(View view) {
        if (((AccessibilityManager) c().getSystemService("accessibility")).isEnabled()) {
            N(view.findViewById(R$id.switchWidget));
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

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchPreferenceCompatStyle);
    }
}
