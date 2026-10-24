package androidx.room;

import android.content.ContentValues;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$insert$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ int $conflictAlgorithm;
    final /* synthetic */ String $table;
    final /* synthetic */ ContentValues $values;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$insert$1(String str, int i, ContentValues contentValues) {
        super(1);
        this.$table = str;
        this.$conflictAlgorithm = i;
        this.$values = contentValues;
    }

    public final Long invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        return Long.valueOf(hVar.insert(this.$table, this.$conflictAlgorithm, this.$values));
    }
}
