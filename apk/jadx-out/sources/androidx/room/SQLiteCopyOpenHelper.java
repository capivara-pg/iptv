package androidx.room;

import android.content.Context;
import androidx.room.util.DBUtil;
import androidx.room.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteCopyOpenHelper implements t0.i, DelegatingOpenHelper {
    private final Context context;
    private final String copyFromAssetPath;
    private final File copyFromFile;
    private final Callable<InputStream> copyFromInputStream;
    private DatabaseConfiguration databaseConfiguration;
    private final int databaseVersion;
    private final t0.i delegate;
    private boolean verified;

    public SQLiteCopyOpenHelper(Context context, String str, File file, Callable<InputStream> callable, int i, t0.i iVar) {
        kotlin.jvm.internal.m.g(context, "context");
        kotlin.jvm.internal.m.g(iVar, "delegate");
        this.context = context;
        this.copyFromAssetPath = str;
        this.copyFromFile = file;
        this.copyFromInputStream = callable;
        this.databaseVersion = i;
        this.delegate = iVar;
    }

    private final void copyDatabaseFile(File file, boolean z) throws IOException {
        ReadableByteChannel readableByteChannelNewChannel;
        if (this.copyFromAssetPath != null) {
            readableByteChannelNewChannel = Channels.newChannel(this.context.getAssets().open(this.copyFromAssetPath));
            kotlin.jvm.internal.m.f(readableByteChannelNewChannel, "newChannel(context.assets.open(copyFromAssetPath))");
        } else if (this.copyFromFile != null) {
            readableByteChannelNewChannel = new FileInputStream(this.copyFromFile).getChannel();
            kotlin.jvm.internal.m.f(readableByteChannelNewChannel, "FileInputStream(copyFromFile).channel");
        } else {
            Callable<InputStream> callable = this.copyFromInputStream;
            if (callable == null) {
                throw new IllegalStateException("copyFromAssetPath, copyFromFile and copyFromInputStream are all null!");
            }
            try {
                readableByteChannelNewChannel = Channels.newChannel(callable.call());
                kotlin.jvm.internal.m.f(readableByteChannelNewChannel, "newChannel(inputStream)");
            } catch (Exception e) {
                throw new IOException("inputStreamCallable exception on call", e);
            }
        }
        File fileCreateTempFile = File.createTempFile("room-copy-helper", ".tmp", this.context.getCacheDir());
        fileCreateTempFile.deleteOnExit();
        FileChannel channel = new FileOutputStream(fileCreateTempFile).getChannel();
        kotlin.jvm.internal.m.f(channel, "output");
        FileUtil.copy(readableByteChannelNewChannel, channel);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Failed to create directories for " + file.getAbsolutePath());
        }
        kotlin.jvm.internal.m.f(fileCreateTempFile, "intermediateFile");
        dispatchOnOpenPrepackagedDatabase(fileCreateTempFile, z);
        if (fileCreateTempFile.renameTo(file)) {
            return;
        }
        throw new IOException("Failed to move intermediate file (" + fileCreateTempFile.getAbsolutePath() + ") to destination (" + file.getAbsolutePath() + ").");
    }

    private final t0.i createFrameworkOpenHelper(File file) {
        try {
            final int version = DBUtil.readVersion(file);
            u0.f fVar = new u0.f();
            t0.i.b.a aVarC = t0.i.b.f.a(this.context).c(file.getAbsolutePath());
            final int iB = va.g.b(version, 1);
            return fVar.create(aVarC.b(new t0.i.a(iB) { // from class: androidx.room.SQLiteCopyOpenHelper$createFrameworkOpenHelper$configuration$1
                public void onCreate(t0.h hVar) {
                    kotlin.jvm.internal.m.g(hVar, "db");
                }

                public void onOpen(t0.h hVar) {
                    kotlin.jvm.internal.m.g(hVar, "db");
                    int i = version;
                    if (i < 1) {
                        hVar.setVersion(i);
                    }
                }

                public void onUpgrade(t0.h hVar, int i, int i2) {
                    kotlin.jvm.internal.m.g(hVar, "db");
                }
            }).a());
        } catch (IOException e) {
            throw new RuntimeException("Malformed database file, unable to read version.", e);
        }
    }

    private final void dispatchOnOpenPrepackagedDatabase(File file, boolean z) {
        DatabaseConfiguration databaseConfiguration = this.databaseConfiguration;
        if (databaseConfiguration == null) {
            kotlin.jvm.internal.m.x("databaseConfiguration");
            databaseConfiguration = null;
        }
        if (databaseConfiguration.prepackagedDatabaseCallback == null) {
            return;
        }
        t0.i iVarCreateFrameworkOpenHelper = createFrameworkOpenHelper(file);
        try {
            t0.h writableDatabase = z ? iVarCreateFrameworkOpenHelper.getWritableDatabase() : iVarCreateFrameworkOpenHelper.getReadableDatabase();
            DatabaseConfiguration databaseConfiguration2 = this.databaseConfiguration;
            if (databaseConfiguration2 == null) {
                kotlin.jvm.internal.m.x("databaseConfiguration");
                databaseConfiguration2 = null;
            }
            RoomDatabase.PrepackagedDatabaseCallback prepackagedDatabaseCallback = databaseConfiguration2.prepackagedDatabaseCallback;
            kotlin.jvm.internal.m.d(prepackagedDatabaseCallback);
            prepackagedDatabaseCallback.onOpenPrepackagedDatabase(writableDatabase);
            ea.v vVar = ea.v.a;
            na.c.a(iVarCreateFrameworkOpenHelper, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                na.c.a(iVarCreateFrameworkOpenHelper, th);
                throw th2;
            }
        }
    }

    private final void verifyDatabaseFile(boolean z) {
        String databaseName = getDatabaseName();
        if (databaseName == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        File databasePath = this.context.getDatabasePath(databaseName);
        DatabaseConfiguration databaseConfiguration = this.databaseConfiguration;
        DatabaseConfiguration databaseConfiguration2 = null;
        if (databaseConfiguration == null) {
            kotlin.jvm.internal.m.x("databaseConfiguration");
            databaseConfiguration = null;
        }
        v0.a aVar = new v0.a(databaseName, this.context.getFilesDir(), databaseConfiguration.multiInstanceInvalidation);
        try {
            v0.a.c(aVar, false, 1, (Object) null);
            if (!databasePath.exists()) {
                try {
                    kotlin.jvm.internal.m.f(databasePath, "databaseFile");
                    copyDatabaseFile(databasePath, z);
                    aVar.d();
                    return;
                } catch (IOException e) {
                    throw new RuntimeException("Unable to copy database file.", e);
                }
            }
            try {
                kotlin.jvm.internal.m.f(databasePath, "databaseFile");
                int version = DBUtil.readVersion(databasePath);
                if (version == this.databaseVersion) {
                    aVar.d();
                    return;
                }
                DatabaseConfiguration databaseConfiguration3 = this.databaseConfiguration;
                if (databaseConfiguration3 == null) {
                    kotlin.jvm.internal.m.x("databaseConfiguration");
                } else {
                    databaseConfiguration2 = databaseConfiguration3;
                }
                if (databaseConfiguration2.isMigrationRequired(version, this.databaseVersion)) {
                    aVar.d();
                    return;
                }
                if (this.context.deleteDatabase(databaseName)) {
                    try {
                        copyDatabaseFile(databasePath, z);
                    } catch (IOException unused) {
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to delete database file (");
                    sb.append(databaseName);
                    sb.append(") for a copy destructive migration.");
                }
                aVar.d();
            } catch (IOException unused2) {
                aVar.d();
            }
        } catch (Throwable th) {
            aVar.d();
            throw th;
        }
    }

    public synchronized void close() {
        getDelegate().close();
        this.verified = false;
    }

    public String getDatabaseName() {
        return getDelegate().getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    public t0.i getDelegate() {
        return this.delegate;
    }

    public t0.h getReadableDatabase() {
        if (!this.verified) {
            verifyDatabaseFile(false);
            this.verified = true;
        }
        return getDelegate().getReadableDatabase();
    }

    public t0.h getWritableDatabase() {
        if (!this.verified) {
            verifyDatabaseFile(true);
            this.verified = true;
        }
        return getDelegate().getWritableDatabase();
    }

    public final void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
        kotlin.jvm.internal.m.g(databaseConfiguration, "databaseConfiguration");
        this.databaseConfiguration = databaseConfiguration;
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        getDelegate().setWriteAheadLoggingEnabled(z);
    }
}
