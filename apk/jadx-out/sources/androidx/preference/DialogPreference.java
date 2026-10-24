package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public abstract class DialogPreference extends Preference {
    private CharSequence D;
    private CharSequence E;
    private Drawable F;
    private CharSequence G;
    private CharSequence H;
    private int I;

    public DialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.w, i, i2);
        String strO = i.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogTitle, R$styleable.DialogPreference_android_dialogTitle);
        this.D = strO;
        if (strO == null) {
            this.D = n();
        }
        this.E = i.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogMessage, R$styleable.DialogPreference_android_dialogMessage);
        this.F = i.c(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogIcon, R$styleable.DialogPreference_android_dialogIcon);
        this.G = i.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_positiveButtonText, R$styleable.DialogPreference_android_positiveButtonText);
        this.H = i.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_negativeButtonText, R$styleable.DialogPreference_android_negativeButtonText);
        this.I = i.n(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogLayout, R$styleable.DialogPreference_android_dialogLayout, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    protected void t() {
        k();
        throw null;
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
