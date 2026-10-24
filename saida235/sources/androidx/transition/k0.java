package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class k0 extends j0 {
    k0() {
    }

    @Override // androidx.transition.c0, androidx.transition.l0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // androidx.transition.h0, androidx.transition.l0
    public void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // androidx.transition.c0, androidx.transition.l0
    public void f(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // androidx.transition.j0, androidx.transition.l0
    public void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // androidx.transition.f0, androidx.transition.l0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // androidx.transition.f0, androidx.transition.l0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
