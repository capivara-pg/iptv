package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDatabaseIntegrityOk$1 extends kotlin.jvm.internal.n implements pa.l {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDatabaseIntegrityOk$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDatabaseIntegrityOk$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDatabaseIntegrityOk$1() {
        super(1);
    }

    public final Boolean invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "obj");
        return Boolean.valueOf(hVar.isDatabaseIntegrityOk());
    }
}
