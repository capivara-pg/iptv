package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$needUpgrade$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ int $newVersion;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$needUpgrade$1(int i) {
        super(1);
        this.$newVersion = i;
    }

    public final Boolean invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        return Boolean.valueOf(hVar.needUpgrade(this.$newVersion));
    }
}
