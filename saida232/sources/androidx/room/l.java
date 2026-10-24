package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
class l implements Executor {
    private final Executor a;
    private final ArrayDeque b = new ArrayDeque();
    private Runnable c;

    class a implements Runnable {
        final /* synthetic */ Runnable a;

        a(Runnable runnable) {
            this.a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.run();
            } finally {
                l.this.a();
            }
        }
    }

    l(Executor executor) {
        this.a = executor;
    }

    synchronized void a() {
        Runnable runnable = (Runnable) this.b.poll();
        this.c = runnable;
        if (runnable != null) {
            this.a.execute(runnable);
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        this.b.offer(new a(runnable));
        if (this.c == null) {
            a();
        }
    }
}
