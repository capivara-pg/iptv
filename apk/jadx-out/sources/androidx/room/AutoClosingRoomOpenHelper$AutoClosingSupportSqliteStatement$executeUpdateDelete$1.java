package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1 extends kotlin.jvm.internal.n implements pa.l {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1() {
        super(1);
    }

    public final Integer invoke(t0.l lVar) {
        kotlin.jvm.internal.m.g(lVar, "obj");
        return Integer.valueOf(lVar.executeUpdateDelete());
    }
}
