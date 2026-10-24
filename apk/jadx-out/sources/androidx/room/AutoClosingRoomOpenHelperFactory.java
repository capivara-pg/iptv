package androidx.room;

/* JADX INFO: loaded from: classes.dex */
public final class AutoClosingRoomOpenHelperFactory implements t0.i.c {
    private final AutoCloser autoCloser;
    private final t0.i.c delegate;

    public AutoClosingRoomOpenHelperFactory(t0.i.c cVar, AutoCloser autoCloser) {
        kotlin.jvm.internal.m.g(cVar, "delegate");
        kotlin.jvm.internal.m.g(autoCloser, "autoCloser");
        this.delegate = cVar;
        this.autoCloser = autoCloser;
    }

    /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
    public AutoClosingRoomOpenHelper m4create(t0.i.b bVar) {
        kotlin.jvm.internal.m.g(bVar, "configuration");
        return new AutoClosingRoomOpenHelper(this.delegate.create(bVar), this.autoCloser);
    }
}
