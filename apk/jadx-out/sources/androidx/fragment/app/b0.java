package androidx.fragment.app;

import android.os.Bundle;
import androidx.lifecycle.j0;
import androidx.lifecycle.k0;

/* JADX INFO: loaded from: classes.dex */
class b0 implements androidx.lifecycle.e, s0.c, k0 {
    private final Fragment a;
    private final j0 b;
    private androidx.lifecycle.o c = null;
    private s0.b d = null;

    b0(Fragment fragment, j0 j0Var) {
        this.a = fragment;
        this.b = j0Var;
    }

    void a(androidx.lifecycle.f.a aVar) {
        this.c.i(aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void b() {
        if (this.c == null) {
            this.c = new androidx.lifecycle.o(this);
            this.d = s0.b.a(this);
        }
    }

    boolean c() {
        return this.c != null;
    }

    void d(Bundle bundle) {
        this.d.d(bundle);
    }

    void e(Bundle bundle) {
        this.d.e(bundle);
    }

    void f(androidx.lifecycle.f.b bVar) {
        this.c.o(bVar);
    }

    @Override // androidx.lifecycle.e
    public /* synthetic */ j0.a getDefaultViewModelCreationExtras() {
        return androidx.lifecycle.d.a(this);
    }

    public androidx.lifecycle.f getLifecycle() {
        b();
        return this.c;
    }

    public androidx.savedstate.a getSavedStateRegistry() {
        b();
        return this.d.b();
    }

    @Override // androidx.lifecycle.k0
    public j0 getViewModelStore() {
        b();
        return this.b;
    }
}
