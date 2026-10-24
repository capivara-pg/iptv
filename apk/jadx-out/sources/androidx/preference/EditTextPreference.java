package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class EditTextPreference extends DialogPreference {
    private String J;

    public static final class a implements Preference.b {
        private static a a;

        private a() {
        }

        public static a b() {
            if (a == null) {
                a = new a();
            }
            return a;
        }

        @Override // androidx.preference.Preference.b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public CharSequence a(EditTextPreference editTextPreference) {
            return TextUtils.isEmpty(editTextPreference.F()) ? editTextPreference.c().getString(R$string.not_set) : editTextPreference.F();
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.y, i, i2);
        int i3 = R$styleable.EditTextPreference_useSimpleSummaryProvider;
        if (i.b(typedArrayObtainStyledAttributes, i3, i3, false)) {
            C(a.b());
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public boolean D() {
        return TextUtils.isEmpty(this.J) || super.D();
    }

    public String F() {
        return this.J;
    }

    @Override // androidx.preference.Preference
    protected Object v(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public EditTextPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.editTextPreferenceStyle, R.attr.editTextPreferenceStyle));
    }
}
