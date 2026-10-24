package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class o extends androidx.fragment.app.d {
    @Override // androidx.fragment.app.d
    public Dialog onCreateDialog(Bundle bundle) {
        return new n(getContext(), getTheme());
    }

    @Override // androidx.fragment.app.d
    public void setupDialog(Dialog dialog, int i) {
        if (!(dialog instanceof n)) {
            super.setupDialog(dialog, i);
            return;
        }
        n nVar = (n) dialog;
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        nVar.supportRequestWindowFeature(1);
    }
}
