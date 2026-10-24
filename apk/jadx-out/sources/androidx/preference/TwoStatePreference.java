package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class TwoStatePreference extends Preference {
    protected boolean D;
    private CharSequence E;
    private CharSequence F;
    private boolean G;
    private boolean H;

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.preference.Preference
    public boolean D() {
        boolean z;
        if (this.H) {
            z = this.D;
        } else {
            z = !this.D;
        }
        return z || super.D();
    }

    public boolean F() {
        return this.D;
    }

    public void G(boolean z) {
        boolean z2 = this.D != z;
        if (z2 || !this.G) {
            this.D = z;
            this.G = true;
            z(z);
            if (z2) {
                s(D());
                r();
            }
        }
    }

    public void H(boolean z) {
        this.H = z;
    }

    public void I(CharSequence charSequence) {
        this.F = charSequence;
        if (F()) {
            return;
        }
        r();
    }

    public void J(CharSequence charSequence) {
        this.E = charSequence;
        if (F()) {
            r();
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0030  */
    /* JADX WARN: Code duplicated, block: B:20:0x003a  */
    /* JADX WARN: Code duplicated, block: B:23:0x0041  */
    /* JADX WARN: Code duplicated, block: B:26:0x0049  */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    protected void K(View view) {
        boolean z;
        int i;
        CharSequence charSequenceL;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (!this.D || TextUtils.isEmpty(this.E)) {
                if (this.D || TextUtils.isEmpty(this.F)) {
                    z = true;
                } else {
                    textView.setText(this.F);
                }
                if (z) {
                    charSequenceL = l();
                    if (!TextUtils.isEmpty(charSequenceL)) {
                        textView.setText(charSequenceL);
                        z = false;
                    }
                }
                i = z ? 8 : 0;
                if (i != textView.getVisibility()) {
                    textView.setVisibility(i);
                }
            }
            textView.setText(this.E);
            z = false;
            if (z) {
                charSequenceL = l();
                if (!TextUtils.isEmpty(charSequenceL)) {
                    textView.setText(charSequenceL);
                    z = false;
                }
            }
            if (z) {
            }
            if (i != textView.getVisibility()) {
                textView.setVisibility(i);
            }
        }
    }

    @Override // androidx.preference.Preference
    protected void t() {
        super.t();
        boolean z = !F();
        if (a(Boolean.valueOf(z))) {
            G(z);
        }
    }

    @Override // androidx.preference.Preference
    protected Object v(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
