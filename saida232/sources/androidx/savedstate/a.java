package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private final b a;
    private final SavedStateRegistry b = new SavedStateRegistry();

    private a(b bVar) {
        this.a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.b;
    }

    public void c(Bundle bundle) {
        d lifecycle = this.a.getLifecycle();
        if (lifecycle.b() != d.c.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.a(new Recreator(this.a));
        this.b.b(lifecycle, bundle);
    }

    public void d(Bundle bundle) {
        this.b.c(bundle);
    }
}
