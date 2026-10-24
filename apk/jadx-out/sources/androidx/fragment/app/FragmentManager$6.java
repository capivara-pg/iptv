package androidx.fragment.app;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
class FragmentManager$6 implements androidx.lifecycle.i {
    final /* synthetic */ String a;
    final /* synthetic */ androidx.lifecycle.f b;
    final /* synthetic */ n c;

    @Override // androidx.lifecycle.i
    public void a(androidx.lifecycle.m mVar, androidx.lifecycle.f.a aVar) {
        if (aVar == androidx.lifecycle.f.a.ON_START && ((Bundle) this.c.j.get(this.a)) != null) {
            throw null;
        }
        if (aVar == androidx.lifecycle.f.a.ON_DESTROY) {
            this.b.d(this);
            this.c.k.remove(this.a);
        }
    }
}
