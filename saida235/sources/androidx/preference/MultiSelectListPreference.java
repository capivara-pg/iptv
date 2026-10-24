package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import java.util.HashSet;
import java.util.Set;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class MultiSelectListPreference extends DialogPreference {
    private CharSequence[] J;
    private CharSequence[] K;
    private Set L;

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.L = new HashSet();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.K, i, i2);
        this.J = i.q(typedArrayObtainStyledAttributes, R$styleable.MultiSelectListPreference_entries, R$styleable.MultiSelectListPreference_android_entries);
        this.K = i.q(typedArrayObtainStyledAttributes, R$styleable.MultiSelectListPreference_entryValues, R$styleable.MultiSelectListPreference_android_entryValues);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    protected Object v(TypedArray typedArray, int i) {
        CharSequence[] textArray = typedArray.getTextArray(i);
        HashSet hashSet = new HashSet();
        for (CharSequence charSequence : textArray) {
            hashSet.add(charSequence.toString());
        }
        return hashSet;
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
