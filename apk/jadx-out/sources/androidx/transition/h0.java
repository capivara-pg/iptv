package androidx.transition;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class h0 extends f0 {
    private static boolean h = true;

    h0() {
    }

    @Override // androidx.transition.l0
    public void e(View view, int i, int i2, int i3, int i4) {
        if (h) {
            try {
                view.setLeftTopRightBottom(i, i2, i3, i4);
            } catch (NoSuchMethodError unused) {
                h = false;
            }
        }
    }
}
