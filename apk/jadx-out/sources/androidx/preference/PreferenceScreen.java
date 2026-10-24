package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {
    private boolean L;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, i.a(context, R$attr.preferenceScreenStyle, R.attr.preferenceScreenStyle));
        this.L = true;
    }

    @Override // androidx.preference.Preference
    protected void t() {
        if (f() == null && e() == null && G() != 0) {
            k();
            throw null;
        }
    }
}
