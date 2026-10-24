package androidx.work.impl.utils.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class a implements ListenableFuture {
    static final boolean d = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger e = Logger.getLogger(a.class.getName());
    static final b f;
    private static final Object g;
    volatile Object a;
    volatile e b;
    volatile i c;

    private static abstract class b {
        private b() {
        }

        abstract boolean a(a aVar, e eVar, e eVar2);

        abstract boolean b(a aVar, Object obj, Object obj2);

        abstract boolean c(a aVar, i iVar, i iVar2);

        abstract void d(i iVar, i iVar2);

        abstract void e(i iVar, Thread thread);
    }

    private static final class c {
        static final c c;
        static final c d;
        final boolean a;
        final Throwable b;

        static {
            if (a.d) {
                d = null;
                c = null;
            } else {
                d = new c(false, null);
                c = new c(true, null);
            }
        }

        c(boolean z, Throwable th) {
            this.a = z;
            this.b = th;
        }
    }

    private static final class d {
        static final d b = new d(new C0057a("Failure occurred while trying to finish a future."));
        final Throwable a;

        /* JADX INFO: renamed from: androidx.work.impl.utils.futures.a$d$a, reason: collision with other inner class name */
        class C0057a extends Throwable {
            C0057a(String str) {
                super(str);
            }

            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        }

        d(Throwable th) {
            this.a = (Throwable) a.d(th);
        }
    }

    private static final class e {
        static final e d = new e(null, null);
        final Runnable a;
        final Executor b;
        e c;

        e(Runnable runnable, Executor executor) {
            this.a = runnable;
            this.b = executor;
        }
    }

    private static final class f extends b {
        final AtomicReferenceFieldUpdater a;
        final AtomicReferenceFieldUpdater b;
        final AtomicReferenceFieldUpdater c;
        final AtomicReferenceFieldUpdater d;
        final AtomicReferenceFieldUpdater e;

        f(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super();
            this.a = atomicReferenceFieldUpdater;
            this.b = atomicReferenceFieldUpdater2;
            this.c = atomicReferenceFieldUpdater3;
            this.d = atomicReferenceFieldUpdater4;
            this.e = atomicReferenceFieldUpdater5;
        }

        @Override // androidx.work.impl.utils.futures.a.b
        boolean a(a aVar, e eVar, e eVar2) {
            return androidx.concurrent.futures.b.a(this.d, aVar, eVar, eVar2);
        }

        @Override // androidx.work.impl.utils.futures.a.b
        boolean b(a aVar, Object obj, Object obj2) {
            return androidx.concurrent.futures.b.a(this.e, aVar, obj, obj2);
        }

        @Override // androidx.work.impl.utils.futures.a.b
        boolean c(a aVar, i iVar, i iVar2) {
            return androidx.concurrent.futures.b.a(this.c, aVar, iVar, iVar2);
        }

        @Override // androidx.work.impl.utils.futures.a.b
        void d(i iVar, i iVar2) {
            this.b.lazySet(iVar, iVar2);
        }

        @Override // androidx.work.impl.utils.futures.a.b
        void e(i iVar, Thread thread) {
            this.a.lazySet(iVar, thread);
        }
    }

    private static final class g implements Runnable {
        final a a;
        final ListenableFuture b;

        g(a aVar, ListenableFuture listenableFuture) {
            this.a = aVar;
            this.b = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.a != this) {
                return;
            }
            if (a.f.b(this.a, this, a.i(this.b))) {
                a.f(this.a);
            }
        }
    }

    private static final class h extends b {
        h() {
            super();
        }

        @Override // androidx.work.impl.utils.futures.a.b
        boolean a(a aVar, e eVar, e eVar2) {
            synchronized (aVar) {
                if (aVar.b != eVar) {
                    return false;
                }
                aVar.b = eVar2;
                return true;
            }
        }

        @Override // androidx.work.impl.utils.futures.a.b
        boolean b(a aVar, Object obj, Object obj2) {
            synchronized (aVar) {
                if (aVar.a != obj) {
                    return false;
                }
                aVar.a = obj2;
                return true;
            }
        }

        @Override // androidx.work.impl.utils.futures.a.b
        boolean c(a aVar, i iVar, i iVar2) {
            synchronized (aVar) {
                if (aVar.c != iVar) {
                    return false;
                }
                aVar.c = iVar2;
                return true;
            }
        }

        @Override // androidx.work.impl.utils.futures.a.b
        void d(i iVar, i iVar2) {
            iVar.b = iVar2;
        }

        @Override // androidx.work.impl.utils.futures.a.b
        void e(i iVar, Thread thread) {
            iVar.a = thread;
        }
    }

    private static final class i {
        static final i c = new i(false);
        volatile Thread a;
        volatile i b;

        i(boolean z) {
        }

        void a(i iVar) {
            a.f.d(this, iVar);
        }

        void b() {
            Thread thread = this.a;
            if (thread != null) {
                this.a = null;
                LockSupport.unpark(thread);
            }
        }

        i() {
            a.f.e(this, Thread.currentThread());
        }
    }

    static {
        b hVar;
        try {
            hVar = new f(AtomicReferenceFieldUpdater.newUpdater(i.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(i.class, i.class, "b"), AtomicReferenceFieldUpdater.newUpdater(a.class, i.class, "c"), AtomicReferenceFieldUpdater.newUpdater(a.class, e.class, "b"), AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "a"));
            th = null;
        } catch (Throwable th) {
            th = th;
            hVar = new h();
        }
        f = hVar;
        if (th != null) {
            e.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        g = new Object();
    }

    protected a() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(StringBuilder sb) {
        try {
            Object objJ = j(this);
            sb.append("SUCCESS, result=[");
            sb.append(r(objJ));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        }
    }

    private static CancellationException c(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    static Object d(Object obj) {
        obj.getClass();
        return obj;
    }

    private e e(e eVar) {
        e eVar2;
        do {
            eVar2 = this.b;
        } while (!f.a(this, eVar2, e.d));
        e eVar3 = eVar;
        e eVar4 = eVar2;
        while (eVar4 != null) {
            e eVar5 = eVar4.c;
            eVar4.c = eVar3;
            eVar3 = eVar4;
            eVar4 = eVar5;
        }
        return eVar3;
    }

    static void f(a aVar) {
        e eVar = null;
        while (true) {
            aVar.m();
            aVar.b();
            e eVarE = aVar.e(eVar);
            while (eVarE != null) {
                eVar = eVarE.c;
                Runnable runnable = eVarE.a;
                if (runnable instanceof g) {
                    g gVar = (g) runnable;
                    aVar = gVar.a;
                    if (aVar.a == gVar) {
                        if (f.b(aVar, gVar, i(gVar.b))) {
                        }
                    } else {
                        continue;
                    }
                } else {
                    g(runnable, eVarE.b);
                }
                eVarE = eVar;
            }
            return;
        }
    }

    private static void g(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            e.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    private Object h(Object obj) throws ExecutionException {
        if (obj instanceof c) {
            throw c("Task was cancelled.", ((c) obj).b);
        }
        if (obj instanceof d) {
            throw new ExecutionException(((d) obj).a);
        }
        if (obj == g) {
            return null;
        }
        return obj;
    }

    static Object i(ListenableFuture listenableFuture) {
        if (listenableFuture instanceof a) {
            Object obj = ((a) listenableFuture).a;
            if (!(obj instanceof c)) {
                return obj;
            }
            c cVar = (c) obj;
            if (cVar.a) {
                return cVar.b != null ? new c(false, cVar.b) : c.d;
            }
            return obj;
        }
        boolean zIsCancelled = listenableFuture.isCancelled();
        if ((!d) && zIsCancelled) {
            return c.d;
        }
        try {
            Object objJ = j(listenableFuture);
            return objJ == null ? g : objJ;
        } catch (CancellationException e2) {
            if (zIsCancelled) {
                return new c(false, e2);
            }
            return new d(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + listenableFuture, e2));
        } catch (ExecutionException e3) {
            return new d(e3.getCause());
        } catch (Throwable th) {
            return new d(th);
        }
    }

    private static Object j(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private void m() {
        i iVar;
        do {
            iVar = this.c;
        } while (!f.c(this, iVar, i.c));
        while (iVar != null) {
            iVar.b();
            iVar = iVar.b;
        }
    }

    private void n(i iVar) {
        iVar.a = null;
        while (true) {
            i iVar2 = this.c;
            if (iVar2 == i.c) {
                return;
            }
            i iVar3 = null;
            while (iVar2 != null) {
                i iVar4 = iVar2.b;
                if (iVar2.a != null) {
                    iVar3 = iVar2;
                } else if (iVar3 != null) {
                    iVar3.b = iVar4;
                    if (iVar3.a == null) {
                    }
                } else if (!f.c(this, iVar2, iVar4)) {
                }
                iVar2 = iVar4;
            }
            return;
        }
    }

    private String r(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    public final void addListener(Runnable runnable, Executor executor) {
        d(runnable);
        d(executor);
        e eVar = this.b;
        if (eVar != e.d) {
            e eVar2 = new e(runnable, executor);
            do {
                eVar2.c = eVar;
                if (f.a(this, eVar, eVar2)) {
                    return;
                } else {
                    eVar = this.b;
                }
            } while (eVar != e.d);
        }
        g(runnable, executor);
    }

    protected void b() {
    }

    public final boolean cancel(boolean z) {
        c cVar;
        Object obj = this.a;
        if (!(obj == null) && !(obj instanceof g)) {
            return false;
        }
        if (d) {
            cVar = new c(z, new CancellationException("Future.cancel() was called."));
        } else {
            cVar = z ? c.c : c.d;
        }
        boolean z2 = false;
        a aVar = this;
        while (true) {
            if (f.b(aVar, obj, cVar)) {
                if (z) {
                    aVar.k();
                }
                f(aVar);
                if (!(obj instanceof g)) {
                    return true;
                }
                ListenableFuture listenableFuture = ((g) obj).b;
                if (!(listenableFuture instanceof a)) {
                    listenableFuture.cancel(z);
                    return true;
                }
                aVar = (a) listenableFuture;
                obj = aVar.a;
                if (!(obj == null) && !(obj instanceof g)) {
                    return true;
                }
                z2 = true;
            } else {
                obj = aVar.a;
                if (!(obj instanceof g)) {
                    return z2;
                }
            }
        }
    }

    public final Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.a;
        if ((obj != null) && (!(obj instanceof g))) {
            return h(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            i iVar = this.c;
            if (iVar != i.c) {
                i iVar2 = new i();
                while (true) {
                    iVar2.a(iVar);
                    if (f.c(this, iVar, iVar2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                n(iVar2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.a;
                            if ((obj2 != null) && (!(obj2 instanceof g))) {
                                return h(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        n(iVar2);
                        break;
                    }
                    iVar = this.c;
                    if (iVar == i.c) {
                    }
                }
            }
            return h(this.a);
        }
        while (nanos > 0) {
            Object obj3 = this.a;
            if ((obj3 != null) && (!(obj3 instanceof g))) {
                return h(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String str = "Waited " + j + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String str2 = str + " (plus ";
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String str3 = str2 + jConvert + " " + lowerCase;
                if (z) {
                    str3 = str3 + ",";
                }
                str2 = str3 + " ";
            }
            if (z) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + string);
    }

    public final boolean isCancelled() {
        return this.a instanceof c;
    }

    public final boolean isDone() {
        Object obj = this.a;
        return (!(obj instanceof g)) & (obj != null);
    }

    protected void k() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected String l() {
        Object obj = this.a;
        if (obj instanceof g) {
            return "setFuture=[" + r(((g) obj).b) + "]";
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    protected boolean o(Object obj) {
        if (obj == null) {
            obj = g;
        }
        if (!f.b(this, null, obj)) {
            return false;
        }
        f(this);
        return true;
    }

    protected boolean p(Throwable th) {
        if (!f.b(this, null, new d((Throwable) d(th)))) {
            return false;
        }
        f(this);
        return true;
    }

    protected boolean q(ListenableFuture listenableFuture) {
        d dVar;
        d(listenableFuture);
        Object obj = this.a;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (!f.b(this, null, i(listenableFuture))) {
                    return false;
                }
                f(this);
                return true;
            }
            g gVar = new g(this, listenableFuture);
            if (f.b(this, null, gVar)) {
                try {
                    listenableFuture.addListener(gVar, androidx.work.impl.utils.futures.b.INSTANCE);
                } catch (Throwable th) {
                    try {
                        dVar = new d(th);
                    } catch (Throwable unused) {
                        dVar = d.b;
                    }
                    f.b(this, gVar, dVar);
                }
                return true;
            }
            obj = this.a;
        }
        if (obj instanceof c) {
            listenableFuture.cancel(((c) obj).a);
        }
        return false;
    }

    public String toString() {
        String strL;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                strL = l();
            } catch (RuntimeException e2) {
                strL = "Exception thrown from implementation: " + e2.getClass();
            }
            if (strL != null && !strL.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(strL);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final Object get() throws InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.a;
            if ((obj2 != null) & (!(obj2 instanceof g))) {
                return h(obj2);
            }
            i iVar = this.c;
            if (iVar != i.c) {
                i iVar2 = new i();
                do {
                    iVar2.a(iVar);
                    if (f.c(this, iVar, iVar2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.a;
                            } else {
                                n(iVar2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof g))));
                        return h(obj);
                    }
                    iVar = this.c;
                } while (iVar != i.c);
            }
            return h(this.a);
        }
        throw new InterruptedException();
    }
}
