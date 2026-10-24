package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class a0 {
    private int a;
    private int b;

    public a0(ViewGroup viewGroup) {
    }

    public int a() {
        return this.a | this.b;
    }

    public void b(View view, View view2, int i) {
        c(view, view2, i, 0);
    }

    public void c(View view, View view2, int i, int i2) {
        if (i2 == 1) {
            this.b = i;
        } else {
            this.a = i;
        }
    }

    public void d(View view) {
        e(view, 0);
    }

    public void e(View view, int i) {
        if (i == 1) {
            this.b = 0;
        } else {
            this.a = 0;
        }
    }
}
