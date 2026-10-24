package androidx.concurrent.futures;

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

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements ListenableFuture {
    static final b ATOMIC_HELPER;
    private static final Object NULL;
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    volatile e listeners;
    volatile Object value;
    volatile i waiters;
    static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger log = Logger.getLogger(a.class.getName());

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
            if (a.GENERATE_CANCELLATION_CAUSES) {
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
        static final d b = new d(new C0015a("Failure occurred while trying to finish a future."));
        final Throwable a;

        /* JADX INFO: renamed from: androidx.concurrent.futures.a$d$a, reason: collision with other inner class name */
        class C0015a extends Throwable {
            C0015a(String str) {
                super(str);
            }

            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        }

        d(Throwable th) {
            this.a = (Throwable) a.checkNotNull(th);
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

        @Override // androidx.concurrent.futures.a.b
        boolean a(a aVar, e eVar, e eVar2) {
            return androidx.concurrent.futures.b.a(this.d, aVar, eVar, eVar2);
        }

        @Override // androidx.concurrent.futures.a.b
        boolean b(a aVar, Object obj, Object obj2) {
            return androidx.concurrent.futures.b.a(this.e, aVar, obj, obj2);
        }

        @Override // androidx.concurrent.futures.a.b
        boolean c(a aVar, i iVar, i iVar2) {
            return androidx.concurrent.futures.b.a(this.c, aVar, iVar, iVar2);
        }

        @Override // androidx.concurrent.futures.a.b
        void d(i iVar, i iVar2) {
            this.b.lazySet(iVar, iVar2);
        }

        @Override // androidx.concurrent.futures.a.b
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
            if (this.a.value != this) {
                return;
            }
            if (a.ATOMIC_HELPER.b(this.a, this, a.getFutureValue(this.b))) {
                a.complete(this.a);
            }
        }
    }

    private static final class h extends b {
        h() {
            super();
        }

        @Override // androidx.concurrent.futures.a.b
        boolean a(a aVar, e eVar, e eVar2) {
            synchronized (aVar) {
                if (aVar.listeners != eVar) {
                    return false;
                }
                aVar.listeners = eVar2;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.a.b
        boolean b(a aVar, Object obj, Object obj2) {
            synchronized (aVar) {
                if (aVar.value != obj) {
                    return false;
                }
                aVar.value = obj2;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.a.b
        boolean c(a aVar, i iVar, i iVar2) {
            synchronized (aVar) {
                if (aVar.waiters != iVar) {
                    return false;
                }
                aVar.waiters = iVar2;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.a.b
        void d(i iVar, i iVar2) {
            iVar.b = iVar2;
        }

        @Override // androidx.concurrent.futures.a.b
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
            a.ATOMIC_HELPER.d(this, iVar);
        }

        void b() {
            Thread thread = this.a;
            if (thread != null) {
                this.a = null;
                LockSupport.unpark(thread);
            }
        }

        i() {
            a.ATOMIC_HELPER.e(this, Thread.currentThread());
        }
    }

    static {
        b hVar;
        try {
            hVar = new f(AtomicReferenceFieldUpdater.newUpdater(i.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(i.class, i.class, "b"), AtomicReferenceFieldUpdater.newUpdater(a.class, i.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(a.class, e.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "value"));
            th = null;
        } catch (Throwable th) {
            th = th;
            hVar = new h();
        }
        ATOMIC_HELPER = hVar;
        if (th != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        NULL = new Object();
    }

    protected a() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(StringBuilder sb) {
        try {
            Object uninterruptibly = getUninterruptibly(this);
            sb.append("SUCCESS, result=[");
            sb.append(h(uninterruptibly));
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

    private static CancellationException b(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    private e c(e eVar) {
        e eVar2;
        do {
            eVar2 = this.listeners;
        } while (!ATOMIC_HELPER.a(this, eVar2, e.d));
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

    static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    static void complete(a aVar) {
        e eVar = null;
        while (true) {
            aVar.f();
            aVar.afterDone();
            e eVarC = aVar.c(eVar);
            while (eVarC != null) {
                eVar = eVarC.c;
                Runnable runnable = eVarC.a;
                if (runnable instanceof g) {
                    g gVar = (g) runnable;
                    aVar = gVar.a;
                    if (aVar.value == gVar) {
                        if (ATOMIC_HELPER.b(aVar, gVar, getFutureValue(gVar.b))) {
                        }
                    } else {
                        continue;
                    }
                } else {
                    d(runnable, eVarC.b);
                }
                eVarC = eVar;
            }
            return;
        }
    }

    private static void d(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    private Object e(Object obj) throws ExecutionException {
        if (obj instanceof c) {
            throw b("Task was cancelled.", ((c) obj).b);
        }
        if (obj instanceof d) {
            throw new ExecutionException(((d) obj).a);
        }
        if (obj == NULL) {
            return null;
        }
        return obj;
    }

    private void f() {
        i iVar;
        do {
            iVar = this.waiters;
        } while (!ATOMIC_HELPER.c(this, iVar, i.c));
        while (iVar != null) {
            iVar.b();
            iVar = iVar.b;
        }
    }

    private void g(i iVar) {
        iVar.a = null;
        while (true) {
            i iVar2 = this.waiters;
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
                } else if (!ATOMIC_HELPER.c(this, iVar2, iVar4)) {
                }
                iVar2 = iVar4;
            }
            return;
        }
    }

    static Object getFutureValue(ListenableFuture<?> listenableFuture) {
        if (listenableFuture instanceof a) {
            Object obj = ((a) listenableFuture).value;
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
        if ((!GENERATE_CANCELLATION_CAUSES) && zIsCancelled) {
            return c.d;
        }
        try {
            Object uninterruptibly = getUninterruptibly(listenableFuture);
            return uninterruptibly == null ? NULL : uninterruptibly;
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

    static <V> V getUninterruptibly(Future<V> future) {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
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
        return v;
    }

    private String h(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    public final void addListener(Runnable runnable, Executor executor) {
        checkNotNull(runnable);
        checkNotNull(executor);
        e eVar = this.listeners;
        if (eVar != e.d) {
            e eVar2 = new e(runnable, executor);
            do {
                eVar2.c = eVar;
                if (ATOMIC_HELPER.a(this, eVar, eVar2)) {
                    return;
                } else {
                    eVar = this.listeners;
                }
            } while (eVar != e.d);
        }
        d(runnable, executor);
    }

    protected void afterDone() {
    }

    public final boolean cancel(boolean z) {
        c cVar;
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof g)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            cVar = new c(z, new CancellationException("Future.cancel() was called."));
        } else {
            cVar = z ? c.c : c.d;
        }
        boolean z2 = false;
        a aVar = this;
        while (true) {
            if (ATOMIC_HELPER.b(aVar, obj, cVar)) {
                if (z) {
                    aVar.interruptTask();
                }
                complete(aVar);
                if (!(obj instanceof g)) {
                    return true;
                }
                ListenableFuture listenableFuture = ((g) obj).b;
                if (!(listenableFuture instanceof a)) {
                    listenableFuture.cancel(z);
                    return true;
                }
                aVar = (a) listenableFuture;
                obj = aVar.value;
                if (!(obj == null) && !(obj instanceof g)) {
                    return true;
                }
                z2 = true;
            } else {
                obj = aVar.value;
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
        Object obj = this.value;
        if ((obj != null) && (!(obj instanceof g))) {
            return e(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= SPIN_THRESHOLD_NANOS) {
            i iVar = this.waiters;
            if (iVar != i.c) {
                i iVar2 = new i();
                while (true) {
                    iVar2.a(iVar);
                    if (ATOMIC_HELPER.c(this, iVar, iVar2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                g(iVar2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.value;
                            if ((obj2 != null) && (!(obj2 instanceof g))) {
                                return e(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= SPIN_THRESHOLD_NANOS);
                        g(iVar2);
                        break;
                    }
                    iVar = this.waiters;
                    if (iVar == i.c) {
                    }
                }
            }
            return e(this.value);
        }
        while (nanos > 0) {
            Object obj3 = this.value;
            if ((obj3 != null) && (!(obj3 instanceof g))) {
                return e(obj3);
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
        if (nanos + SPIN_THRESHOLD_NANOS < 0) {
            String str2 = str + " (plus ";
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > SPIN_THRESHOLD_NANOS;
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

    protected void interruptTask() {
    }

    public final boolean isCancelled() {
        return this.value instanceof c;
    }

    public final boolean isDone() {
        Object obj = this.value;
        return (!(obj instanceof g)) & (obj != null);
    }

    final void maybePropagateCancellationTo(Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected String pendingToString() {
        Object obj = this.value;
        if (obj instanceof g) {
            return "setFuture=[" + h(((g) obj).b) + "]";
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    protected boolean set(Object obj) {
        if (obj == null) {
            obj = NULL;
        }
        if (!ATOMIC_HELPER.b(this, null, obj)) {
            return false;
        }
        complete(this);
        return true;
    }

    protected boolean setException(Throwable th) {
        if (!ATOMIC_HELPER.b(this, null, new d((Throwable) checkNotNull(th)))) {
            return false;
        }
        complete(this);
        return true;
    }

    protected boolean setFuture(ListenableFuture<Object> listenableFuture) {
        d dVar;
        checkNotNull(listenableFuture);
        Object obj = this.value;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (!ATOMIC_HELPER.b(this, null, getFutureValue(listenableFuture))) {
                    return false;
                }
                complete(this);
                return true;
            }
            g gVar = new g(this, listenableFuture);
            if (ATOMIC_HELPER.b(this, null, gVar)) {
                try {
                    listenableFuture.addListener(gVar, androidx.concurrent.futures.d.INSTANCE);
                } catch (Throwable th) {
                    try {
                        dVar = new d(th);
                    } catch (Throwable unused) {
                        dVar = d.b;
                    }
                    ATOMIC_HELPER.b(this, gVar, dVar);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof c) {
            listenableFuture.cancel(((c) obj).a);
        }
        return false;
    }

    public String toString() {
        String strPendingToString;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                strPendingToString = pendingToString();
            } catch (RuntimeException e2) {
                strPendingToString = "Exception thrown from implementation: " + e2.getClass();
            }
            if (strPendingToString != null && !strPendingToString.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(strPendingToString);
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

    protected final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof c) && ((c) obj).a;
    }

    public final Object get() throws InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) & (!(obj2 instanceof g))) {
                return e(obj2);
            }
            i iVar = this.waiters;
            if (iVar != i.c) {
                i iVar2 = new i();
                do {
                    iVar2.a(iVar);
                    if (ATOMIC_HELPER.c(this, iVar, iVar2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                g(iVar2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof g))));
                        return e(obj);
                    }
                    iVar = this.waiters;
                } while (iVar != i.c);
            }
            return e(this.value);
        }
        throw new InterruptedException();
    }
}
