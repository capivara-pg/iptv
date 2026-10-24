package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    public static final class a {
        Object a;
        d b;
        private e c = e.i();
        private boolean d;

        a() {
        }

        private void d() {
            this.a = null;
            this.b = null;
            this.c = null;
        }

        void a() {
            this.a = null;
            this.b = null;
            this.c.set(null);
        }

        public boolean b(Object obj) {
            this.d = true;
            d dVar = this.b;
            boolean z = dVar != null && dVar.b(obj);
            if (z) {
                d();
            }
            return z;
        }

        public boolean c() {
            this.d = true;
            d dVar = this.b;
            boolean z = dVar != null && dVar.a(true);
            if (z) {
                d();
            }
            return z;
        }

        protected void finalize() {
            e eVar;
            d dVar = this.b;
            if (dVar != null && !dVar.isDone()) {
                dVar.c(new b("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.a));
            }
            if (this.d || (eVar = this.c) == null) {
                return;
            }
            eVar.set(null);
        }
    }

    static final class b extends Throwable {
        b(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    /* JADX INFO: renamed from: androidx.concurrent.futures.c$c, reason: collision with other inner class name */
    public interface InterfaceC0016c {
        Object attachCompleter(a aVar);
    }

    private static final class d implements ListenableFuture {
        final WeakReference a;
        private final androidx.concurrent.futures.a b = new a();

        class a extends androidx.concurrent.futures.a {
            a() {
            }

            @Override // androidx.concurrent.futures.a
            protected String pendingToString() {
                a aVar = (a) d.this.a.get();
                if (aVar == null) {
                    return "Completer object has been garbage collected, future will fail soon";
                }
                return "tag=[" + aVar.a + "]";
            }
        }

        d(a aVar) {
            this.a = new WeakReference(aVar);
        }

        boolean a(boolean z) {
            return this.b.cancel(z);
        }

        public void addListener(Runnable runnable, Executor executor) {
            this.b.addListener(runnable, executor);
        }

        boolean b(Object obj) {
            return this.b.set(obj);
        }

        boolean c(Throwable th) {
            return this.b.setException(th);
        }

        public boolean cancel(boolean z) {
            a aVar = (a) this.a.get();
            boolean zCancel = this.b.cancel(z);
            if (zCancel && aVar != null) {
                aVar.a();
            }
            return zCancel;
        }

        public Object get() {
            return this.b.get();
        }

        public boolean isCancelled() {
            return this.b.isCancelled();
        }

        public boolean isDone() {
            return this.b.isDone();
        }

        public String toString() {
            return this.b.toString();
        }

        public Object get(long j, TimeUnit timeUnit) {
            return this.b.get(j, timeUnit);
        }
    }

    public static ListenableFuture a(InterfaceC0016c interfaceC0016c) {
        a aVar = new a();
        d dVar = new d(aVar);
        aVar.b = dVar;
        aVar.a = interfaceC0016c.getClass();
        try {
            Object objAttachCompleter = interfaceC0016c.attachCompleter(aVar);
            if (objAttachCompleter != null) {
                aVar.a = objAttachCompleter;
            }
        } catch (Exception e) {
            dVar.c(e);
        }
        return dVar;
    }
}
