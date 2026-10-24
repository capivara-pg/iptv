package androidx.work.impl.workers;

import a1.k;
import android.content.Context;
import android.text.TextUtils;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkSpec;
import com.google.common.util.concurrent.ListenableFuture;
import d1.c;
import d1.d;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintTrackingWorker extends ListenableWorker implements c {
    private static final String k = k.f("ConstraintTrkngWrkr");
    private WorkerParameters f;
    final Object g;
    volatile boolean h;
    androidx.work.impl.utils.futures.c i;
    private ListenableWorker j;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ConstraintTrackingWorker.this.u();
        }
    }

    class b implements Runnable {
        final /* synthetic */ ListenableFuture a;

        b(ListenableFuture listenableFuture) {
            this.a = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (ConstraintTrackingWorker.this.g) {
                if (ConstraintTrackingWorker.this.h) {
                    ConstraintTrackingWorker.this.t();
                } else {
                    ConstraintTrackingWorker.this.i.q(this.a);
                }
            }
        }
    }

    public ConstraintTrackingWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        this.f = workerParams;
        this.g = new Object();
        this.h = false;
        this.i = androidx.work.impl.utils.futures.c.s();
    }

    public void b(List list) {
        k.c().a(k, String.format("Constraints changed for %s", list), new Throwable[0]);
        synchronized (this.g) {
            this.h = true;
        }
    }

    public void e(List list) {
    }

    @Override // androidx.work.ListenableWorker
    public j1.a h() {
        return androidx.work.impl.k.j(a()).o();
    }

    @Override // androidx.work.ListenableWorker
    public boolean j() {
        ListenableWorker listenableWorker = this.j;
        return listenableWorker != null && listenableWorker.j();
    }

    @Override // androidx.work.ListenableWorker
    public void m() {
        super.m();
        ListenableWorker listenableWorker = this.j;
        if (listenableWorker == null || listenableWorker.k()) {
            return;
        }
        this.j.q();
    }

    @Override // androidx.work.ListenableWorker
    public ListenableFuture p() {
        c().execute(new a());
        return this.i;
    }

    public WorkDatabase r() {
        return androidx.work.impl.k.j(a()).n();
    }

    void s() {
        this.i.o(ListenableWorker.a.a());
    }

    void t() {
        this.i.o(ListenableWorker.a.b());
    }

    void u() {
        String strH = g().h("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if (TextUtils.isEmpty(strH)) {
            k.c().b(k, "No worker to delegate to.", new Throwable[0]);
            s();
            return;
        }
        ListenableWorker listenableWorkerB = i().b(a(), strH, this.f);
        this.j = listenableWorkerB;
        if (listenableWorkerB == null) {
            k.c().a(k, "No worker to delegate to.", new Throwable[0]);
            s();
            return;
        }
        WorkSpec workSpec = r().workSpecDao().getWorkSpec(f().toString());
        if (workSpec == null) {
            s();
            return;
        }
        d dVar = new d(a(), h(), this);
        dVar.d(Collections.singletonList(workSpec));
        if (!dVar.c(f().toString())) {
            k.c().a(k, String.format("Constraints not met for delegate %s. Requesting retry.", strH), new Throwable[0]);
            t();
            return;
        }
        k.c().a(k, String.format("Constraints met for delegate %s", strH), new Throwable[0]);
        try {
            ListenableFuture listenableFutureP = this.j.p();
            listenableFutureP.addListener(new b(listenableFutureP), c());
        } catch (Throwable th) {
            k kVarC = k.c();
            String str = k;
            kVarC.a(str, String.format("Delegated worker %s threw exception in startWork.", strH), new Throwable[]{th});
            synchronized (this.g) {
                if (this.h) {
                    k.c().a(str, "Constraints were unmet, Retrying.", new Throwable[0]);
                    t();
                } else {
                    s();
                }
            }
        }
    }
}
