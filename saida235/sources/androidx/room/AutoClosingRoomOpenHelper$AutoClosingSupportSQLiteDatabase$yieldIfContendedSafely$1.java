package androidx.room;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$yieldIfContendedSafely$1 extends kotlin.jvm.internal.j implements pa.l {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$yieldIfContendedSafely$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$yieldIfContendedSafely$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$yieldIfContendedSafely$1() {
        super(1, t0.h.class, "yieldIfContendedSafely", "yieldIfContendedSafely()Z", 0);
    }

    public final Boolean invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "p0");
        return Boolean.valueOf(hVar.yieldIfContendedSafely());
    }
}
