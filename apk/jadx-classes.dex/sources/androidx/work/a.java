package androidx.work;

import android.os.Build;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import s0.g;
import s0.i;
import s0.q;
import s0.v;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class a {
    final Executor a;
    final Executor b;
    final v c;
    final i d;
    final q e;
    final String f;
    final int g;
    final int h;
    final int i;
    final int j;
    private final boolean k;

    /* JADX INFO: renamed from: androidx.work.a$a, reason: collision with other inner class name */
    class ThreadFactoryC0053a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(0);
        final /* synthetic */ boolean b;

        ThreadFactoryC0053a(boolean z) {
            this.b = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, (this.b ? "WM.task-" : "androidx.work-") + this.a.incrementAndGet());
        }
    }

    public static final class b {
        Executor a;
        v b;
        i c;
        Executor d;
        q e;
        String f;
        int g = 4;
        int h = 0;
        int i = Integer.MAX_VALUE;
        int j = 20;

        public a a() {
            return new a(this);
        }
    }

    a(b bVar) {
        Executor executor = bVar.a;
        if (executor == null) {
            this.a = a(false);
        } else {
            this.a = executor;
        }
        Executor executor2 = bVar.d;
        if (executor2 == null) {
            this.k = true;
            this.b = a(true);
        } else {
            this.k = false;
            this.b = executor2;
        }
        v vVar = bVar.b;
        if (vVar == null) {
            this.c = v.c();
        } else {
            this.c = vVar;
        }
        i iVar = bVar.c;
        if (iVar == null) {
            this.d = i.c();
        } else {
            this.d = iVar;
        }
        q qVar = bVar.e;
        if (qVar == null) {
            this.e = new t0.a();
        } else {
            this.e = qVar;
        }
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.f = bVar.f;
    }

    private Executor a(boolean z) {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), b(z));
    }

    private ThreadFactory b(boolean z) {
        return new ThreadFactoryC0053a(z);
    }

    public String c() {
        return this.f;
    }

    public g d() {
        return null;
    }

    public Executor e() {
        return this.a;
    }

    public i f() {
        return this.d;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        return Build.VERSION.SDK_INT == 23 ? this.j / 2 : this.j;
    }

    public int i() {
        return this.h;
    }

    public int j() {
        return this.g;
    }

    public q k() {
        return this.e;
    }

    public Executor l() {
        return this.b;
    }

    public v m() {
        return this.c;
    }
}
