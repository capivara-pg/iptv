package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class i0 extends f0 {
    private static boolean f = true;
    private static boolean g = true;

    i0() {
    }

    @Override // androidx.transition.o0
    public void h(View view, Matrix matrix) {
        if (f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f = false;
            }
        }
    }

    @Override // androidx.transition.o0
    public void i(View view, Matrix matrix) {
        if (g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                g = false;
            }
        }
    }
}
