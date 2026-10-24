package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class f0 extends c0 {
    private static boolean f = true;
    private static boolean g = true;

    f0() {
    }

    @Override // androidx.transition.l0
    public void h(View view, Matrix matrix) {
        if (f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f = false;
            }
        }
    }

    @Override // androidx.transition.l0
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
