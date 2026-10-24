package androidx.lifecycle;

import android.os.Handler;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class u {
    private final h a;
    private final Handler b = new Handler();
    private a c;

    static class a implements Runnable {
        private final h a;
        final d.b b;
        private boolean c = false;

        a(h hVar, d.b bVar) {
            this.a = hVar;
            this.b = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.c) {
                return;
            }
            this.a.h(this.b);
            this.c = true;
        }
    }

    public u(g gVar) {
        this.a = new h(gVar);
    }

    private void f(d.b bVar) {
        a aVar = this.c;
        if (aVar != null) {
            aVar.run();
        }
        a aVar2 = new a(this.a, bVar);
        this.c = aVar2;
        this.b.postAtFrontOfQueue(aVar2);
    }

    public d a() {
        return this.a;
    }

    public void b() {
        f(d.b.ON_START);
    }

    public void c() {
        f(d.b.ON_CREATE);
    }

    public void d() {
        f(d.b.ON_STOP);
        f(d.b.ON_DESTROY);
    }

    public void e() {
        f(d.b.ON_START);
    }
}
