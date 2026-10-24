package androidx.room;

import java.util.Arrays;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationClient$callback$1 extends IMultiInstanceInvalidationCallback.Stub {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    MultiInstanceInvalidationClient$callback$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        this.this$0 = multiInstanceInvalidationClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onInvalidation$lambda$0(MultiInstanceInvalidationClient multiInstanceInvalidationClient, String[] strArr) {
        kotlin.jvm.internal.m.g(multiInstanceInvalidationClient, "this$0");
        kotlin.jvm.internal.m.g(strArr, "$tables");
        multiInstanceInvalidationClient.getInvalidationTracker().notifyObserversByTableNames((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @Override // androidx.room.IMultiInstanceInvalidationCallback
    public void onInvalidation(final String[] strArr) {
        kotlin.jvm.internal.m.g(strArr, "tables");
        Executor executor = this.this$0.getExecutor();
        final MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.this$0;
        executor.execute(new Runnable() { // from class: androidx.room.f
            @Override // java.lang.Runnable
            public final void run() {
                MultiInstanceInvalidationClient$callback$1.onInvalidation$lambda$0(multiInstanceInvalidationClient, strArr);
            }
        });
    }
}
