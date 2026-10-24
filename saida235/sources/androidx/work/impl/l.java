package androidx.work.impl;

import a1.s;
import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import com.google.common.util.concurrent.ListenableFuture;
import i1.o;
import i1.p;
import i1.q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public class l implements Runnable {
    static final String t = a1.k.f("WorkerWrapper");
    Context a;
    private String b;
    private List c;
    private WorkerParameters.a d;
    WorkSpec e;
    ListenableWorker f;
    j1.a g;
    private androidx.work.a i;
    private g1.a j;
    private WorkDatabase k;
    private WorkSpecDao l;
    private DependencyDao m;
    private WorkTagDao n;
    private List o;
    private String p;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private volatile boolean f35s;
    ListenableWorker.a h = ListenableWorker.a.a();
    androidx.work.impl.utils.futures.c q = androidx.work.impl.utils.futures.c.s();
    ListenableFuture r = null;

    class a implements Runnable {
        final /* synthetic */ ListenableFuture a;
        final /* synthetic */ androidx.work.impl.utils.futures.c b;

        a(ListenableFuture listenableFuture, androidx.work.impl.utils.futures.c cVar) {
            this.a = listenableFuture;
            this.b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.get();
                a1.k.c().a(l.t, String.format("Starting work for %s", l.this.e.workerClassName), new Throwable[0]);
                l lVar = l.this;
                lVar.r = lVar.f.p();
                this.b.q(l.this.r);
            } catch (Throwable th) {
                this.b.p(th);
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ androidx.work.impl.utils.futures.c a;
        final /* synthetic */ String b;

        b(androidx.work.impl.utils.futures.c cVar, String str) {
            this.a = cVar;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    ListenableWorker.a aVar = (ListenableWorker.a) this.a.get();
                    if (aVar == null) {
                        a1.k.c().b(l.t, String.format("%s returned a null result. Treating it as a failure.", l.this.e.workerClassName), new Throwable[0]);
                    } else {
                        a1.k.c().a(l.t, String.format("%s returned a %s result.", l.this.e.workerClassName, aVar), new Throwable[0]);
                        l.this.h = aVar;
                    }
                } catch (InterruptedException e) {
                    e = e;
                    a1.k.c().b(l.t, String.format("%s failed because it threw an exception/error", this.b), new Throwable[]{e});
                } catch (CancellationException e2) {
                    a1.k.c().d(l.t, String.format("%s was cancelled", this.b), new Throwable[]{e2});
                } catch (ExecutionException e3) {
                    e = e3;
                    a1.k.c().b(l.t, String.format("%s failed because it threw an exception/error", this.b), new Throwable[]{e});
                }
                l.this.f();
            } catch (Throwable th) {
                l.this.f();
                throw th;
            }
        }
    }

    public static class c {
        Context a;
        ListenableWorker b;
        g1.a c;
        j1.a d;
        androidx.work.a e;
        WorkDatabase f;
        String g;
        List h;
        WorkerParameters.a i = new WorkerParameters.a();

        public c(Context context, androidx.work.a aVar, j1.a aVar2, g1.a aVar3, WorkDatabase workDatabase, String str) {
            this.a = context.getApplicationContext();
            this.d = aVar2;
            this.c = aVar3;
            this.e = aVar;
            this.f = workDatabase;
            this.g = str;
        }

        public l a() {
            return new l(this);
        }

        public c b(WorkerParameters.a aVar) {
            if (aVar != null) {
                this.i = aVar;
            }
            return this;
        }

        public c c(List list) {
            this.h = list;
            return this;
        }
    }

    l(c cVar) {
        this.a = cVar.a;
        this.g = cVar.d;
        this.j = cVar.c;
        this.b = cVar.g;
        this.c = cVar.h;
        this.d = cVar.i;
        this.f = cVar.b;
        this.i = cVar.e;
        WorkDatabase workDatabase = cVar.f;
        this.k = workDatabase;
        this.l = workDatabase.workSpecDao();
        this.m = this.k.dependencyDao();
        this.n = this.k.workTagDao();
    }

    private String a(List list) {
        StringBuilder sb = new StringBuilder("Work [ id=");
        sb.append(this.b);
        sb.append(", tags={ ");
        Iterator it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(" } ]");
        return sb.toString();
    }

    private void c(ListenableWorker.a aVar) {
        if (aVar instanceof ListenableWorker.a.c) {
            a1.k.c().d(t, String.format("Worker result SUCCESS for %s", this.p), new Throwable[0]);
            if (this.e.isPeriodic()) {
                h();
                return;
            } else {
                m();
                return;
            }
        }
        if (aVar instanceof ListenableWorker.a.b) {
            a1.k.c().d(t, String.format("Worker result RETRY for %s", this.p), new Throwable[0]);
            g();
            return;
        }
        a1.k.c().d(t, String.format("Worker result FAILURE for %s", this.p), new Throwable[0]);
        if (this.e.isPeriodic()) {
            h();
        } else {
            l();
        }
    }

    private void e(String str) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            if (this.l.getState(str2) != s.a.f) {
                this.l.setState(s.a.d, str2);
            }
            linkedList.addAll(this.m.getDependentWorkIds(str2));
        }
    }

    private void g() {
        this.k.beginTransaction();
        try {
            this.l.setState(s.a.a, this.b);
            this.l.setPeriodStartTime(this.b, System.currentTimeMillis());
            this.l.markWorkSpecScheduled(this.b, -1L);
            this.k.setTransactionSuccessful();
        } finally {
            this.k.endTransaction();
            i(true);
        }
    }

    private void h() {
        this.k.beginTransaction();
        try {
            this.l.setPeriodStartTime(this.b, System.currentTimeMillis());
            this.l.setState(s.a.a, this.b);
            this.l.resetWorkSpecRunAttemptCount(this.b);
            this.l.markWorkSpecScheduled(this.b, -1L);
            this.k.setTransactionSuccessful();
        } finally {
            this.k.endTransaction();
            i(false);
        }
    }

    private void i(boolean z) {
        ListenableWorker listenableWorker;
        this.k.beginTransaction();
        try {
            if (!this.k.workSpecDao().hasUnfinishedWork()) {
                i1.g.a(this.a, RescheduleReceiver.class, false);
            }
            if (z) {
                this.l.setState(s.a.a, this.b);
                this.l.markWorkSpecScheduled(this.b, -1L);
            }
            if (this.e != null && (listenableWorker = this.f) != null && listenableWorker.j()) {
                this.j.a(this.b);
            }
            this.k.setTransactionSuccessful();
            this.k.endTransaction();
            this.q.o(Boolean.valueOf(z));
        } catch (Throwable th) {
            this.k.endTransaction();
            throw th;
        }
    }

    private void j() {
        s.a state = this.l.getState(this.b);
        if (state == s.a.b) {
            a1.k.c().a(t, String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", this.b), new Throwable[0]);
            i(true);
        } else {
            a1.k.c().a(t, String.format("Status for %s is %s; not doing any work", this.b, state), new Throwable[0]);
            i(false);
        }
    }

    private void k() {
        androidx.work.b bVarB;
        if (n()) {
            return;
        }
        this.k.beginTransaction();
        try {
            WorkSpec workSpec = this.l.getWorkSpec(this.b);
            this.e = workSpec;
            if (workSpec == null) {
                a1.k.c().b(t, String.format("Didn't find WorkSpec for id %s", this.b), new Throwable[0]);
                i(false);
                this.k.setTransactionSuccessful();
                this.k.endTransaction();
                return;
            }
            if (workSpec.state != s.a.a) {
                j();
                this.k.setTransactionSuccessful();
                a1.k.c().a(t, String.format("%s is not in ENQUEUED state. Nothing more to do.", this.e.workerClassName), new Throwable[0]);
                this.k.endTransaction();
                return;
            }
            if (workSpec.isPeriodic() || this.e.isBackedOff()) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                WorkSpec workSpec2 = this.e;
                if (!(workSpec2.periodStartTime == 0) && jCurrentTimeMillis < workSpec2.calculateNextRunTime()) {
                    a1.k.c().a(t, String.format("Delaying execution for %s because it is being executed before schedule.", this.e.workerClassName), new Throwable[0]);
                    i(true);
                    this.k.setTransactionSuccessful();
                    this.k.endTransaction();
                    return;
                }
            }
            this.k.setTransactionSuccessful();
            this.k.endTransaction();
            if (this.e.isPeriodic()) {
                bVarB = this.e.input;
            } else {
                a1.h hVarB = this.i.f().b(this.e.inputMergerClassName);
                if (hVarB == null) {
                    a1.k.c().b(t, String.format("Could not create Input Merger %s", this.e.inputMergerClassName), new Throwable[0]);
                    l();
                    return;
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(this.e.input);
                    arrayList.addAll(this.l.getInputsFromPrerequisites(this.b));
                    bVarB = hVarB.b(arrayList);
                }
            }
            WorkerParameters workerParameters = new WorkerParameters(UUID.fromString(this.b), bVarB, this.o, this.d, this.e.runAttemptCount, this.i.e(), this.g, this.i.m(), new q(this.k, this.g), new p(this.k, this.j, this.g));
            if (this.f == null) {
                this.f = this.i.m().b(this.a, this.e.workerClassName, workerParameters);
            }
            ListenableWorker listenableWorker = this.f;
            if (listenableWorker == null) {
                a1.k.c().b(t, String.format("Could not create Worker %s", this.e.workerClassName), new Throwable[0]);
                l();
                return;
            }
            if (listenableWorker.l()) {
                a1.k.c().b(t, String.format("Received an already-used Worker %s; WorkerFactory should return new instances", this.e.workerClassName), new Throwable[0]);
                l();
                return;
            }
            this.f.o();
            if (!o()) {
                j();
                return;
            }
            if (n()) {
                return;
            }
            androidx.work.impl.utils.futures.c cVarS = androidx.work.impl.utils.futures.c.s();
            Runnable oVar = new o(this.a, this.e, this.f, workerParameters.b(), this.g);
            this.g.a().execute(oVar);
            ListenableFuture listenableFutureA = oVar.a();
            listenableFutureA.addListener(new a(listenableFutureA, cVarS), this.g.a());
            cVarS.addListener(new b(cVarS, this.p), this.g.c());
        } catch (Throwable th) {
            this.k.endTransaction();
            throw th;
        }
    }

    private void m() {
        this.k.beginTransaction();
        try {
            this.l.setState(s.a.c, this.b);
            this.l.setOutput(this.b, ((ListenableWorker.a.c) this.h).e());
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (String str : this.m.getDependentWorkIds(this.b)) {
                if (this.l.getState(str) == s.a.e && this.m.hasCompletedAllPrerequisites(str)) {
                    a1.k.c().d(t, String.format("Setting status to enqueued for %s", str), new Throwable[0]);
                    this.l.setState(s.a.a, str);
                    this.l.setPeriodStartTime(str, jCurrentTimeMillis);
                }
            }
            this.k.setTransactionSuccessful();
        } finally {
            this.k.endTransaction();
            i(false);
        }
    }

    private boolean n() {
        if (!this.f35s) {
            return false;
        }
        a1.k.c().a(t, String.format("Work interrupted for %s", this.p), new Throwable[0]);
        s.a state = this.l.getState(this.b);
        if (state == null) {
            i(false);
        } else {
            i(!state.a());
        }
        return true;
    }

    private boolean o() {
        this.k.beginTransaction();
        try {
            boolean z = false;
            if (this.l.getState(this.b) == s.a.a) {
                this.l.setState(s.a.b, this.b);
                this.l.incrementWorkSpecRunAttemptCount(this.b);
                z = true;
            }
            this.k.setTransactionSuccessful();
            return z;
        } finally {
            this.k.endTransaction();
        }
    }

    public ListenableFuture b() {
        return this.q;
    }

    public void d() {
        boolean zIsDone;
        this.f35s = true;
        n();
        ListenableFuture listenableFuture = this.r;
        if (listenableFuture != null) {
            zIsDone = listenableFuture.isDone();
            this.r.cancel(true);
        } else {
            zIsDone = false;
        }
        ListenableWorker listenableWorker = this.f;
        if (listenableWorker == null || zIsDone) {
            a1.k.c().a(t, String.format("WorkSpec %s is already done. Not interrupting.", this.e), new Throwable[0]);
        } else {
            listenableWorker.q();
        }
    }

    void f() {
        if (!n()) {
            this.k.beginTransaction();
            try {
                s.a state = this.l.getState(this.b);
                this.k.workProgressDao().delete(this.b);
                if (state == null) {
                    i(false);
                } else if (state == s.a.b) {
                    c(this.h);
                } else if (!state.a()) {
                    g();
                }
                this.k.setTransactionSuccessful();
                this.k.endTransaction();
            } catch (Throwable th) {
                this.k.endTransaction();
                throw th;
            }
        }
        List list = this.c;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((e) it.next()).cancel(this.b);
            }
            f.b(this.i, this.k, this.c);
        }
    }

    void l() {
        this.k.beginTransaction();
        try {
            e(this.b);
            this.l.setOutput(this.b, ((ListenableWorker.a.C0059a) this.h).e());
            this.k.setTransactionSuccessful();
        } finally {
            this.k.endTransaction();
            i(false);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        List<String> tagsForWorkSpecId = this.n.getTagsForWorkSpecId(this.b);
        this.o = tagsForWorkSpecId;
        this.p = a(tagsForWorkSpecId);
        k();
    }
}
