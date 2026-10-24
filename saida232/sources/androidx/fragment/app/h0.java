package androidx.fragment.app;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: loaded from: classes.dex */
class h0 implements androidx.savedstate.b, androidx.lifecycle.y {
    private final Fragment a;
    private final androidx.lifecycle.x b;
    private androidx.lifecycle.h c = null;
    private androidx.savedstate.a d = null;

    h0(Fragment fragment, androidx.lifecycle.x xVar) {
        this.a = fragment;
        this.b = xVar;
    }

    void a(androidx.lifecycle.d.b bVar) {
        this.c.h(bVar);
    }

    void b() {
        if (this.c == null) {
            this.c = new androidx.lifecycle.h(this);
            this.d = androidx.savedstate.a.a(this);
        }
    }

    boolean c() {
        return this.c != null;
    }

    void d(Bundle bundle) {
        this.d.c(bundle);
    }

    void e(Bundle bundle) {
        this.d.d(bundle);
    }

    void f(androidx.lifecycle.d.c cVar) {
        this.c.o(cVar);
    }

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d getLifecycle() {
        b();
        return this.c;
    }

    @Override // androidx.savedstate.b
    public SavedStateRegistry getSavedStateRegistry() {
        b();
        return this.d.b();
    }

    @Override // androidx.lifecycle.y
    public androidx.lifecycle.x getViewModelStore() {
        b();
        return this.b;
    }
}
