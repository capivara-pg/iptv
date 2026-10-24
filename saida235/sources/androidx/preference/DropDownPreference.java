package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/* JADX INFO: loaded from: classes.dex */
public class DropDownPreference extends ListPreference {
    private final Context Q;
    private final ArrayAdapter S;
    private Spinner V;
    private final AdapterView.OnItemSelectedListener W;

    class a implements AdapterView.OnItemSelectedListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            if (i >= 0) {
                String string = DropDownPreference.this.I()[i].toString();
                if (string.equals(DropDownPreference.this.J()) || !DropDownPreference.this.a(string)) {
                    return;
                }
                DropDownPreference.this.L(string);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.dropdownPreferenceStyle);
    }

    private void N() {
        this.S.clear();
        if (G() != null) {
            for (CharSequence charSequence : G()) {
                this.S.add(charSequence.toString());
            }
        }
    }

    protected ArrayAdapter M() {
        return new ArrayAdapter(this.Q, R.layout.simple_spinner_dropdown_item);
    }

    @Override // androidx.preference.Preference
    protected void r() {
        super.r();
        ArrayAdapter arrayAdapter = this.S;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected void t() {
        this.V.performClick();
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.W = new a();
        this.Q = context;
        this.S = M();
        N();
    }
}
