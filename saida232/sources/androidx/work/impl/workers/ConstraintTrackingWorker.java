package androidx.work.impl.workers;

import a1.p;
import android.content.Context;
import android.text.TextUtils;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import s0.k;
import t0.j;
import w0.c;
import w0.d;

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

    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.f = workerParameters;
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
    public c1.a h() {
        return j.j(a()).o();
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
        return j.j(a()).n();
    }

    void s() {
        this.i.o(ListenableWorker.a.a());
    }

    void t() {
        this.i.o(ListenableWorker.a.b());
    }

    void u() {
        String strI = g().i("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if (TextUtils.isEmpty(strI)) {
            k.c().b(k, "No worker to delegate to.", new Throwable[0]);
            s();
            return;
        }
        ListenableWorker listenableWorkerB = i().b(a(), strI, this.f);
        this.j = listenableWorkerB;
        if (listenableWorkerB == null) {
            k.c().a(k, "No worker to delegate to.", new Throwable[0]);
            s();
            return;
        }
        p pVarH = r().B().h(f().toString());
        if (pVarH == null) {
            s();
            return;
        }
        d dVar = new d(a(), h(), this);
        dVar.d(Collections.singletonList(pVarH));
        if (!dVar.c(f().toString())) {
            k.c().a(k, String.format("Constraints not met for delegate %s. Requesting retry.", strI), new Throwable[0]);
            t();
            return;
        }
        k.c().a(k, String.format("Constraints met for delegate %s", strI), new Throwable[0]);
        try {
            ListenableFuture listenableFutureP = this.j.p();
            listenableFutureP.addListener(new b(listenableFutureP), c());
        } catch (Throwable th) {
            k kVarC = k.c();
            String str = k;
            kVarC.a(str, String.format("Delegated worker %s threw exception in startWork.", strI), new Throwable[]{th});
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
