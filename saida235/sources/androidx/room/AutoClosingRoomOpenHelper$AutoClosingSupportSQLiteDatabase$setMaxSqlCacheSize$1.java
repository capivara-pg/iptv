package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaxSqlCacheSize$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ int $cacheSize;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaxSqlCacheSize$1(int i) {
        super(1);
        this.$cacheSize = i;
    }

    public final Object invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        hVar.setMaxSqlCacheSize(this.$cacheSize);
        return null;
    }
}
