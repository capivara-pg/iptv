package androidx.room;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class k implements n0.c.c {
    private final String a;
    private final File b;
    private final n0.c.c c;

    k(String str, File file, n0.c.c cVar) {
        this.a = str;
        this.b = file;
        this.c = cVar;
    }

    public n0.c a(n0.c.b bVar) {
        return new j(bVar.a, this.a, this.b, bVar.c.a, this.c.a(bVar));
    }
}
