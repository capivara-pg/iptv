package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ pa.l $block;
    final /* synthetic */ AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1(AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement autoClosingSupportSqliteStatement, pa.l lVar) {
        super(1);
        this.this$0 = autoClosingSupportSqliteStatement;
        this.$block = lVar;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.Object] */
    public final T invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        t0.l lVarCompileStatement = hVar.compileStatement(this.this$0.sql);
        this.this$0.doBinds(lVarCompileStatement);
        return this.$block.invoke(lVarCompileStatement);
    }
}
