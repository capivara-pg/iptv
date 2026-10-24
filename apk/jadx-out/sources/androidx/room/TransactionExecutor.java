package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class TransactionExecutor implements Executor {
    private Runnable active;
    private final Executor executor;
    private final Object syncLock;
    private final ArrayDeque<Runnable> tasks;

    public TransactionExecutor(Executor executor) {
        kotlin.jvm.internal.m.g(executor, "executor");
        this.executor = executor;
        this.tasks = new ArrayDeque<>();
        this.syncLock = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$1$lambda$0(Runnable runnable, TransactionExecutor transactionExecutor) {
        kotlin.jvm.internal.m.g(runnable, "$command");
        kotlin.jvm.internal.m.g(transactionExecutor, "this$0");
        try {
            runnable.run();
        } finally {
            transactionExecutor.scheduleNext();
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        kotlin.jvm.internal.m.g(runnable, "command");
        synchronized (this.syncLock) {
            this.tasks.offer(new Runnable() { // from class: androidx.room.a0
                @Override // java.lang.Runnable
                public final void run() {
                    TransactionExecutor.execute$lambda$1$lambda$0(runnable, this);
                }
            });
            if (this.active == null) {
                scheduleNext();
            }
            ea.v vVar = ea.v.a;
        }
    }

    public final void scheduleNext() {
        synchronized (this.syncLock) {
            Runnable runnablePoll = this.tasks.poll();
            Runnable runnable = runnablePoll;
            this.active = runnable;
            if (runnablePoll != null) {
                this.executor.execute(runnable);
            }
            ea.v vVar = ea.v.a;
        }
    }
}
