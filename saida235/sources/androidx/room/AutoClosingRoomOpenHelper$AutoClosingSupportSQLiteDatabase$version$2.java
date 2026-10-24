package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$version$2 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ int $version;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$version$2(int i) {
        super(1);
        this.$version = i;
    }

    public final Object invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        hVar.setVersion(this.$version);
        return null;
    }
}
