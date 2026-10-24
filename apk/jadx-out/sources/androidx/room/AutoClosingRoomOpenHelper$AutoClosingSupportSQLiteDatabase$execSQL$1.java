package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ String $sql;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$1(String str) {
        super(1);
        this.$sql = str;
    }

    public final Object invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        hVar.execSQL(this.$sql);
        return null;
    }
}
