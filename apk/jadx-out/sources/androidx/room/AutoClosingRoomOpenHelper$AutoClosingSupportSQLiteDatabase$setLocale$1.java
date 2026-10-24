package androidx.room;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setLocale$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ Locale $locale;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setLocale$1(Locale locale) {
        super(1);
        this.$locale = locale;
    }

    public final Object invoke(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        hVar.setLocale(this.$locale);
        return null;
    }
}
