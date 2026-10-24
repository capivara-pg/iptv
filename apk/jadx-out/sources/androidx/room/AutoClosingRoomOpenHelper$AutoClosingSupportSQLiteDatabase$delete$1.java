package androidx.room;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$delete$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ String $table;
    final /* synthetic */ Object[] $whereArgs;
    final /* synthetic */ String $whereClause;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$delete$1(String str, String str2, Object[] objArr) {
        super(1);
        this.$table = str;
        this.$whereClause = str2;
        this.$whereArgs = objArr;
    }

    public final Integer invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        return Integer.valueOf(hVar.delete(this.$table, this.$whereClause, this.$whereArgs));
    }
}
