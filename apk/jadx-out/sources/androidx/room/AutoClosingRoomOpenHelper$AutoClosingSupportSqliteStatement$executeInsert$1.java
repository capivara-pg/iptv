package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1 extends kotlin.jvm.internal.n implements pa.l {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1() {
        super(1);
    }

    public final Long invoke(t0.l lVar) {
        kotlin.jvm.internal.m.g(lVar, "obj");
        return Long.valueOf(lVar.executeInsert());
    }
}
