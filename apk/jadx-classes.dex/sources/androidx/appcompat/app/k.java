package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class k extends androidx.fragment.app.d {
    @Override // androidx.fragment.app.d
    public Dialog onCreateDialog(Bundle bundle) {
        return new j(getContext(), getTheme());
    }

    @Override // androidx.fragment.app.d
    public void setupDialog(Dialog dialog, int i) {
        if (!(dialog instanceof j)) {
            super.setupDialog(dialog, i);
            return;
        }
        j jVar = (j) dialog;
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        jVar.supportRequestWindowFeature(1);
    }
}
