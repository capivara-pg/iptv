package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class ListPreference extends DialogPreference {
    private CharSequence[] J;
    private CharSequence[] K;
    private String L;
    private String M;
    private boolean N;

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
        public CharSequence a(ListPreference listPreference) {
            return TextUtils.isEmpty(listPreference.H()) ? listPreference.c().getString(R$string.not_set) : listPreference.H();
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.G, i, i2);
        this.J = i.q(typedArrayObtainStyledAttributes, R$styleable.ListPreference_entries, R$styleable.ListPreference_android_entries);
        this.K = i.q(typedArrayObtainStyledAttributes, R$styleable.ListPreference_entryValues, R$styleable.ListPreference_android_entryValues);
        int i3 = R$styleable.ListPreference_useSimpleSummaryProvider;
        if (i.b(typedArrayObtainStyledAttributes, i3, i3, false)) {
            C(a.b());
        }
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.N, i, i2);
        this.M = i.o(typedArrayObtainStyledAttributes2, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        typedArrayObtainStyledAttributes2.recycle();
    }

    private int K() {
        return F(this.L);
    }

    public int F(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.K) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (this.K[length].equals(str)) {
                return length;
            }
        }
        return -1;
    }

    public CharSequence[] G() {
        return this.J;
    }

    public CharSequence H() {
        CharSequence[] charSequenceArr;
        int iK = K();
        if (iK < 0 || (charSequenceArr = this.J) == null) {
            return null;
        }
        return charSequenceArr[iK];
    }

    public CharSequence[] I() {
        return this.K;
    }

    public String J() {
        return this.L;
    }

    public void L(String str) {
        boolean z = !TextUtils.equals(this.L, str);
        if (z || !this.N) {
            this.L = str;
            this.N = true;
            B(str);
            if (z) {
                r();
            }
        }
    }

    @Override // androidx.preference.Preference
    public CharSequence l() {
        if (m() != null) {
            return m().a(this);
        }
        CharSequence charSequenceH = H();
        CharSequence charSequenceL = super.l();
        String str = this.M;
        if (str == null) {
            return charSequenceL;
        }
        Object[] objArr = new Object[1];
        if (charSequenceH == null) {
            charSequenceH = "";
        }
        objArr[0] = charSequenceH;
        String str2 = String.format(str, objArr);
        return TextUtils.equals(str2, charSequenceL) ? charSequenceL : str2;
    }

    @Override // androidx.preference.Preference
    protected Object v(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
