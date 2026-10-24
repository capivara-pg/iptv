package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$pageSize$2 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ long $numBytes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$pageSize$2(long j) {
        super(1);
        this.$numBytes = j;
    }

    public final Object invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        hVar.setPageSize(this.$numBytes);
        return null;
    }
}
