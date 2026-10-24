package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$2 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ Object[] $bindArgs;
    final /* synthetic */ String $sql;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$2(String str, Object[] objArr) {
        super(1);
        this.$sql = str;
        this.$bindArgs = objArr;
    }

    public final Object invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        hVar.execSQL(this.$sql, this.$bindArgs);
        return null;
    }
}
