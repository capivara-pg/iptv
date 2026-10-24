package androidx.transition;

import android.view.View;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class f0 extends o0 {
    private static boolean e = true;

    f0() {
    }

    @Override // androidx.transition.o0
    public void a(View view) {
    }

    @Override // androidx.transition.o0
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

    @Override // androidx.transition.o0
    public void d(View view) {
    }

    @Override // androidx.transition.o0
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
