package androidx.fragment.app;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
class FragmentManager$6 implements androidx.lifecycle.e {
    final /* synthetic */ String a;
    final /* synthetic */ androidx.lifecycle.d b;
    final /* synthetic */ o c;

    @Override // androidx.lifecycle.e
    public void a(androidx.lifecycle.g gVar, androidx.lifecycle.d.b bVar) {
        if (bVar == androidx.lifecycle.d.b.ON_START && ((Bundle) this.c.j.get(this.a)) != null) {
            throw null;
        }
        if (bVar == androidx.lifecycle.d.b.ON_DESTROY) {
            this.b.c(this);
            this.c.k.remove(this.a);
        }
    }
}
