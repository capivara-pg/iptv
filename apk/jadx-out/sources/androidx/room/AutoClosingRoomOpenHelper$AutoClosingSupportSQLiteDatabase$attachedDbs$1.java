package androidx.room;

import android.util.Pair;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1 extends kotlin.jvm.internal.n implements pa.l {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1() {
        super(1);
    }

    public final List<Pair<String, String>> invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "obj");
        return hVar.getAttachedDbs();
    }
}
