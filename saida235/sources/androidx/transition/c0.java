package androidx.transition;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
abstract class c0 extends l0 {
    private static boolean e = true;

    c0() {
    }

    @Override // androidx.transition.l0
    public void a(View view) {
    }

    @Override // androidx.transition.l0
    public float c(View view) {
        if (e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // androidx.transition.l0
    public void d(View view) {
    }

    @Override // androidx.transition.l0
    public void f(View view, float f) {
        if (e) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError unused) {
                e = false;
            }
        }
        view.setAlpha(f);
    }
}
