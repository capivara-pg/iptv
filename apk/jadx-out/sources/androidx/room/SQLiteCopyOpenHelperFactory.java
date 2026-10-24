package androidx.room;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteCopyOpenHelperFactory implements t0.i.c {
    private final String mCopyFromAssetPath;
    private final File mCopyFromFile;
    private final Callable<InputStream> mCopyFromInputStream;
    private final t0.i.c mDelegate;

    public SQLiteCopyOpenHelperFactory(String str, File file, Callable<InputStream> callable, t0.i.c cVar) {
        kotlin.jvm.internal.m.g(cVar, "mDelegate");
        this.mCopyFromAssetPath = str;
        this.mCopyFromFile = file;
        this.mCopyFromInputStream = callable;
        this.mDelegate = cVar;
    }

    public t0.i create(t0.i.b bVar) {
        kotlin.jvm.internal.m.g(bVar, "configuration");
        return new SQLiteCopyOpenHelper(bVar.a, this.mCopyFromAssetPath, this.mCopyFromFile, this.mCopyFromInputStream, bVar.c.version, this.mDelegate.create(bVar));
    }
}
