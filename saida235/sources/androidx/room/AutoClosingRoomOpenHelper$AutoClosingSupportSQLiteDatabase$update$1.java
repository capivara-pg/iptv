package androidx.room;

import android.content.ContentValues;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ int $conflictAlgorithm;
    final /* synthetic */ String $table;
    final /* synthetic */ ContentValues $values;
    final /* synthetic */ Object[] $whereArgs;
    final /* synthetic */ String $whereClause;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        super(1);
        this.$table = str;
        this.$conflictAlgorithm = i;
        this.$values = contentValues;
        this.$whereClause = str2;
        this.$whereArgs = objArr;
    }

    public final Integer invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        return Integer.valueOf(hVar.update(this.$table, this.$conflictAlgorithm, this.$values, this.$whereClause, this.$whereArgs));
    }
}
