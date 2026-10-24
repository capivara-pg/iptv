package androidx.work;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.work.impl.utils.futures.c;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes.dex */
public abstract class Worker extends ListenableWorker {
    c f;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Worker.this.f.o(Worker.this.r());
            } catch (Throwable th) {
                Worker.this.f.p(th);
            }
        }
    }

    @Keep
    public Worker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture p() {
        this.f = c.s();
        c().execute(new a());
        return this.f;
    }

    public abstract ListenableWorker.a r();
}
