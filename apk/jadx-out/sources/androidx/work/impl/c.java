package androidx.work.impl;

import a1.n;
import androidx.lifecycle.s;

/* JADX INFO: loaded from: classes.dex */
public class c implements n {
    private final s c = new s();
    private final androidx.work.impl.utils.futures.c d = androidx.work.impl.utils.futures.c.s();

    public c() {
        a(n.b);
    }

    public void a(n.b bVar) {
        this.c.postValue(bVar);
        if (bVar instanceof n.b.c) {
            this.d.o((n.b.c) bVar);
        } else if (bVar instanceof n.b.a) {
            this.d.p(((n.b.a) bVar).a());
        }
    }
}
