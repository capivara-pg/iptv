package androidx.room.migration;

import kotlin.jvm.internal.m;
import pa.l;
import t0.h;

/* JADX INFO: loaded from: classes.dex */
final class MigrationImpl extends Migration {
    private final l migrateCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MigrationImpl(int i, int i2, l lVar) {
        super(i, i2);
        m.g(lVar, "migrateCallback");
        this.migrateCallback = lVar;
    }

    public final l getMigrateCallback() {
        return this.migrateCallback;
    }

    @Override // androidx.room.migration.Migration
    public void migrate(h hVar) {
        m.g(hVar, "database");
        this.migrateCallback.invoke(hVar);
    }
}
