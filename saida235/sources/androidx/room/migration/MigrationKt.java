package androidx.room.migration;

import kotlin.jvm.internal.m;
import pa.l;

/* JADX INFO: loaded from: classes.dex */
public final class MigrationKt {
    public static final Migration Migration(int i, int i2, l lVar) {
        m.g(lVar, "migrate");
        return new MigrationImpl(i, i2, lVar);
    }
}
