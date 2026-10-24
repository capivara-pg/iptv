package androidx.loader.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import x.i;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class a extends b {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile androidx.loader.content.a.a mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile androidx.loader.content.a.a mTask;
    long mUpdateThrottle;

    /* JADX INFO: renamed from: androidx.loader.content.a$a, reason: collision with other inner class name */
    final class RunnableC0035a extends c implements Runnable {
        private final CountDownLatch k = new CountDownLatch(1);
        boolean l;

        RunnableC0035a() {
        }

        @Override // androidx.loader.content.c
        protected void g(Object obj) {
            try {
                a.this.dispatchOnCancelled(this, obj);
            } finally {
                this.k.countDown();
            }
        }

        @Override // androidx.loader.content.c
        protected void h(Object obj) {
            try {
                a.this.dispatchOnLoadComplete(this, obj);
            } finally {
                this.k.countDown();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.loader.content.c
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public Object b(Void... voidArr) {
            return a.this.onLoadInBackground();
        }

        public void n() {
            try {
                this.k.await();
            } catch (InterruptedException unused) {
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.l = a.DEBUG;
            a.this.executePendingTask();
        }
    }

    public a(Context context) {
        this(context, c.h);
    }

    public void cancelLoadInBackground() {
    }

    void dispatchOnCancelled(androidx.loader.content.a.a aVar, Object obj) {
        onCanceled(obj);
        if (this.mCancellingTask == aVar) {
            rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            deliverCancellation();
            executePendingTask();
        }
    }

    void dispatchOnLoadComplete(androidx.loader.content.a.a aVar, Object obj) {
        if (this.mTask != aVar) {
            dispatchOnCancelled(aVar, obj);
            return;
        }
        if (isAbandoned()) {
            onCanceled(obj);
            return;
        }
        commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        deliverResult(obj);
    }

    @Override // androidx.loader.content.b
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.mTask != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.mTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mTask.l);
        }
        if (this.mCancellingTask != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.mCancellingTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mCancellingTask.l);
        }
        if (this.mUpdateThrottle != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            i.c(this.mUpdateThrottle, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            i.b(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    void executePendingTask() {
        if (this.mCancellingTask != null || this.mTask == null) {
            return;
        }
        if (this.mTask.l) {
            this.mTask.l = DEBUG;
            this.mHandler.removeCallbacks(this.mTask);
        }
        if (this.mUpdateThrottle <= 0 || SystemClock.uptimeMillis() >= this.mLastLoadCompleteTime + this.mUpdateThrottle) {
            this.mTask.c(this.mExecutor, null);
        } else {
            this.mTask.l = true;
            this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        if (this.mCancellingTask != null) {
            return true;
        }
        return DEBUG;
    }

    public abstract Object loadInBackground();

    @Override // androidx.loader.content.b
    protected boolean onCancelLoad() {
        if (this.mTask == null) {
            return DEBUG;
        }
        if (!this.mStarted) {
            this.mContentChanged = true;
        }
        if (this.mCancellingTask != null) {
            if (this.mTask.l) {
                this.mTask.l = DEBUG;
                this.mHandler.removeCallbacks(this.mTask);
            }
            this.mTask = null;
            return DEBUG;
        }
        if (this.mTask.l) {
            this.mTask.l = DEBUG;
            this.mHandler.removeCallbacks(this.mTask);
            this.mTask = null;
            return DEBUG;
        }
        boolean zA = this.mTask.a(DEBUG);
        if (zA) {
            this.mCancellingTask = this.mTask;
            cancelLoadInBackground();
        }
        this.mTask = null;
        return zA;
    }

    public void onCanceled(Object obj) {
    }

    @Override // androidx.loader.content.b
    protected void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        this.mTask = new RunnableC0035a();
        executePendingTask();
    }

    protected Object onLoadInBackground() {
        return loadInBackground();
    }

    public void setUpdateThrottle(long j) {
        this.mUpdateThrottle = j;
        if (j != 0) {
            this.mHandler = new Handler();
        }
    }

    public void waitForLoader() {
        androidx.loader.content.a.a aVar = this.mTask;
        if (aVar != null) {
            aVar.n();
        }
    }

    private a(Context context, Executor executor) {
        super(context);
        this.mLastLoadCompleteTime = -10000L;
        this.mExecutor = executor;
    }
}
