package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaximumSize$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ long $numBytes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaximumSize$1(long j) {
        super(1);
        this.$numBytes = j;
    }

    public final Long invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        return Long.valueOf(hVar.setMaximumSize(this.$numBytes));
    }
}
