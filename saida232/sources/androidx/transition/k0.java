package androidx.transition;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class k0 extends i0 {
    private static boolean h = true;

    k0() {
    }

    @Override // androidx.transition.o0
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
