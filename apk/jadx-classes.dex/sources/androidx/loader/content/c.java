package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
abstract class c {
    private static final ThreadFactory f;
    private static final BlockingQueue g;
    public static final Executor h;
    private static f i;
    private static volatile Executor j;
    private final h a;
    private final FutureTask b;
    private volatile g c = g.PENDING;
    final AtomicBoolean d = new AtomicBoolean();
    final AtomicBoolean e = new AtomicBoolean();

    static class a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.a.getAndIncrement());
        }
    }

    class b extends h {
        b() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            c.this.e.set(true);
            Object objB = null;
            try {
                Process.setThreadPriority(10);
                objB = c.this.b(this.a);
                Binder.flushPendingCommands();
                c.this.k(objB);
                return objB;
            } catch (Throwable th) {
                try {
                    c.this.d.set(true);
                    throw th;
                } catch (Throwable th2) {
                    c.this.k(objB);
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: renamed from: androidx.loader.content.c$c, reason: collision with other inner class name */
    class C0037c extends FutureTask {
        C0037c(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                c.this.l(get());
            } catch (InterruptedException unused) {
            } catch (CancellationException unused2) {
                c.this.l(null);
            } catch (ExecutionException e) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    }

    static /* synthetic */ class d {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.values().length];
            a = iArr;
            try {
                iArr[g.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class e {
        final c a;
        final Object[] b;

        e(c cVar, Object... objArr) {
            this.a = cVar;
            this.b = objArr;
        }
    }

    private static class f extends Handler {
        f() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            e eVar = (e) message.obj;
            int i = message.what;
            if (i == 1) {
                eVar.a.d(eVar.b[0]);
            } else {
                if (i != 2) {
                    return;
                }
                eVar.a.j(eVar.b);
            }
        }
    }

    public enum g {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static abstract class h implements Callable {
        Object[] a;

        h() {
        }
    }

    static {
        a aVar = new a();
        f = aVar;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        g = linkedBlockingQueue;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, linkedBlockingQueue, aVar);
        h = threadPoolExecutor;
        j = threadPoolExecutor;
    }

    c() {
        b bVar = new b();
        this.a = bVar;
        this.b = new C0037c(bVar);
    }

    private static Handler e() {
        f fVar;
        synchronized (c.class) {
            if (i == null) {
                i = new f();
            }
            fVar = i;
        }
        return fVar;
    }

    public final boolean a(boolean z) {
        this.d.set(true);
        return this.b.cancel(z);
    }

    protected abstract Object b(Object... objArr);

    public final c c(Executor executor, Object... objArr) {
        if (this.c == g.PENDING) {
            this.c = g.RUNNING;
            i();
            this.a.a = objArr;
            executor.execute(this.b);
            return this;
        }
        int i2 = d.a[this.c.ordinal()];
        if (i2 == 1) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }
        if (i2 != 2) {
            throw new IllegalStateException("We should never reach this state");
        }
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    void d(Object obj) {
        if (f()) {
            g(obj);
        } else {
            h(obj);
        }
        this.c = g.FINISHED;
    }

    public final boolean f() {
        return this.d.get();
    }

    protected abstract void g(Object obj);

    protected abstract void h(Object obj);

    protected void i() {
    }

    protected void j(Object... objArr) {
    }

    Object k(Object obj) {
        e().obtainMessage(1, new e(this, obj)).sendToTarget();
        return obj;
    }

    void l(Object obj) {
        if (this.e.get()) {
            return;
        }
        k(obj);
    }
}
