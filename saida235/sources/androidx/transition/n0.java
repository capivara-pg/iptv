package androidx.transition;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: loaded from: classes.dex */
class n0 implements o0 {
    private final WindowId a;

    n0(View view) {
        this.a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof n0) && ((n0) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
