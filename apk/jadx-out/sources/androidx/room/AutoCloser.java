package androidx.room;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class AutoCloser {
    public static final Companion Companion = new Companion(null);
    public static final String autoCloseBug = "https://issuetracker.google.com/issues/new?component=413107&template=1096568";
    private long autoCloseTimeoutInMs;
    private final Runnable autoCloser;
    private t0.h delegateDatabase;
    public t0.i delegateOpenHelper;
    private final Runnable executeAutoCloser;
    private final Executor executor;
    private final Handler handler;
    private long lastDecrementRefCountTimeStamp;
    private final Object lock;
    private boolean manuallyClosed;
    private Runnable onAutoCloseCallback;
    private int refCount;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public AutoCloser(long j, TimeUnit timeUnit, Executor executor) {
        kotlin.jvm.internal.m.g(timeUnit, "autoCloseTimeUnit");
        kotlin.jvm.internal.m.g(executor, "autoCloseExecutor");
        this.handler = new Handler(Looper.getMainLooper());
        this.lock = new Object();
        this.autoCloseTimeoutInMs = timeUnit.toMillis(j);
        this.executor = executor;
        this.lastDecrementRefCountTimeStamp = SystemClock.uptimeMillis();
        this.executeAutoCloser = new Runnable() { // from class: androidx.room.a
            @Override // java.lang.Runnable
            public final void run() {
                AutoCloser.executeAutoCloser$lambda$0(this.a);
            }
        };
        this.autoCloser = new Runnable() { // from class: androidx.room.b
            @Override // java.lang.Runnable
            public final void run() {
                AutoCloser.autoCloser$lambda$3(this.a);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void autoCloser$lambda$3(AutoCloser autoCloser) {
        ea.v vVar;
        kotlin.jvm.internal.m.g(autoCloser, "this$0");
        synchronized (autoCloser.lock) {
            if (SystemClock.uptimeMillis() - autoCloser.lastDecrementRefCountTimeStamp < autoCloser.autoCloseTimeoutInMs) {
                return;
            }
            if (autoCloser.refCount != 0) {
                return;
            }
            Runnable runnable = autoCloser.onAutoCloseCallback;
            if (runnable != null) {
                runnable.run();
                vVar = ea.v.a;
            } else {
                vVar = null;
            }
            if (vVar == null) {
                throw new IllegalStateException("onAutoCloseCallback is null but it should have been set before use. Please file a bug against Room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568".toString());
            }
            t0.h hVar = autoCloser.delegateDatabase;
            if (hVar != null && hVar.isOpen()) {
                hVar.close();
            }
            autoCloser.delegateDatabase = null;
            ea.v vVar2 = ea.v.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeAutoCloser$lambda$0(AutoCloser autoCloser) {
        kotlin.jvm.internal.m.g(autoCloser, "this$0");
        autoCloser.executor.execute(autoCloser.autoCloser);
    }

    public final void closeDatabaseIfOpen() {
        synchronized (this.lock) {
            this.manuallyClosed = true;
            t0.h hVar = this.delegateDatabase;
            if (hVar != null) {
                hVar.close();
            }
            this.delegateDatabase = null;
            ea.v vVar = ea.v.a;
        }
    }

    public final void decrementCountAndScheduleClose() {
        synchronized (this.lock) {
            int i = this.refCount;
            if (!(i > 0)) {
                throw new IllegalStateException("ref count is 0 or lower but we're supposed to decrement".toString());
            }
            int i2 = i - 1;
            this.refCount = i2;
            if (i2 == 0) {
                if (this.delegateDatabase == null) {
                    return;
                } else {
                    this.handler.postDelayed(this.executeAutoCloser, this.autoCloseTimeoutInMs);
                }
            }
            ea.v vVar = ea.v.a;
        }
    }

    public final <V> V executeRefCountingFunction(pa.l lVar) {
        kotlin.jvm.internal.m.g(lVar, "block");
        try {
            return (V) lVar.invoke(incrementCountAndEnsureDbIsOpen());
        } finally {
            decrementCountAndScheduleClose();
        }
    }

    public final t0.h getDelegateDatabase$room_runtime_release() {
        return this.delegateDatabase;
    }

    public final t0.i getDelegateOpenHelper() {
        t0.i iVar = this.delegateOpenHelper;
        if (iVar != null) {
            return iVar;
        }
        kotlin.jvm.internal.m.x("delegateOpenHelper");
        return null;
    }

    public final long getLastDecrementRefCountTimeStamp$room_runtime_release() {
        return this.lastDecrementRefCountTimeStamp;
    }

    public final Runnable getOnAutoCloseCallback$room_runtime_release() {
        return this.onAutoCloseCallback;
    }

    public final int getRefCount$room_runtime_release() {
        return this.refCount;
    }

    public final int getRefCountForTest$room_runtime_release() {
        int i;
        synchronized (this.lock) {
            i = this.refCount;
        }
        return i;
    }

    public final t0.h incrementCountAndEnsureDbIsOpen() {
        synchronized (this.lock) {
            this.handler.removeCallbacks(this.executeAutoCloser);
            this.refCount++;
            if (!(!this.manuallyClosed)) {
                throw new IllegalStateException("Attempting to open already closed database.".toString());
            }
            t0.h hVar = this.delegateDatabase;
            if (hVar != null && hVar.isOpen()) {
                return hVar;
            }
            t0.h writableDatabase = getDelegateOpenHelper().getWritableDatabase();
            this.delegateDatabase = writableDatabase;
            return writableDatabase;
        }
    }

    public final void init(t0.i iVar) {
        kotlin.jvm.internal.m.g(iVar, "delegateOpenHelper");
        setDelegateOpenHelper(iVar);
    }

    public final boolean isActive() {
        return !this.manuallyClosed;
    }

    public final void setAutoCloseCallback(Runnable runnable) {
        kotlin.jvm.internal.m.g(runnable, "onAutoClose");
        this.onAutoCloseCallback = runnable;
    }

    public final void setDelegateDatabase$room_runtime_release(t0.h hVar) {
        this.delegateDatabase = hVar;
    }

    public final void setDelegateOpenHelper(t0.i iVar) {
        kotlin.jvm.internal.m.g(iVar, "<set-?>");
        this.delegateOpenHelper = iVar;
    }

    public final void setLastDecrementRefCountTimeStamp$room_runtime_release(long j) {
        this.lastDecrementRefCountTimeStamp = j;
    }

    public final void setOnAutoCloseCallback$room_runtime_release(Runnable runnable) {
        this.onAutoCloseCallback = runnable;
    }

    public final void setRefCount$room_runtime_release(int i) {
        this.refCount = i;
    }
}
