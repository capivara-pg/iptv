package androidx.transition;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class j0 extends h0 {
    private static boolean i = true;

    j0() {
    }

    @Override // androidx.transition.l0
    public void g(View view, int i2) {
        if (Build.VERSION.SDK_INT == 28) {
            super.g(view, i2);
        } else if (i) {
            try {
                view.setTransitionVisibility(i2);
            } catch (NoSuchMethodError unused) {
                i = false;
            }
        }
    }
}
