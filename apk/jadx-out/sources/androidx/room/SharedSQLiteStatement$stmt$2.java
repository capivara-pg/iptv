package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class SharedSQLiteStatement$stmt$2 extends kotlin.jvm.internal.n implements pa.a {
    final /* synthetic */ SharedSQLiteStatement this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SharedSQLiteStatement$stmt$2(SharedSQLiteStatement sharedSQLiteStatement) {
        super(0);
        this.this$0 = sharedSQLiteStatement;
    }

    public final t0.l invoke() {
        return this.this$0.createNewStatement();
    }
}
