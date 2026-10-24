package androidx.work;

import android.net.Network;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import s0.f;
import s0.p;
import s0.v;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class WorkerParameters {
    private UUID a;
    private b b;
    private Set c;
    private a d;
    private int e;
    private Executor f;
    private c1.a g;
    private v h;
    private p i;
    private f j;

    public static class a {
        public List a = Collections.emptyList();
        public List b = Collections.emptyList();
        public Network c;
    }

    public WorkerParameters(UUID uuid, b bVar, Collection collection, a aVar, int i, Executor executor, c1.a aVar2, v vVar, p pVar, f fVar) {
        this.a = uuid;
        this.b = bVar;
        this.c = new HashSet(collection);
        this.d = aVar;
        this.e = i;
        this.f = executor;
        this.g = aVar2;
        this.h = vVar;
        this.i = pVar;
        this.j = fVar;
    }

    public Executor a() {
        return this.f;
    }

    public f b() {
        return this.j;
    }

    public UUID c() {
        return this.a;
    }

    public b d() {
        return this.b;
    }

    public c1.a e() {
        return this.g;
    }

    public v f() {
        return this.h;
    }
}
