package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.preference.Preference
    public boolean D() {
        return !super.p();
    }

    @Override // androidx.preference.Preference
    public boolean p() {
        return false;
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.preferenceCategoryStyle, R.attr.preferenceCategoryStyle));
    }
}
