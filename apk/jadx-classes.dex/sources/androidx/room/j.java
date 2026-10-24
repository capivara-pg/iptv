package androidx.room;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class j implements n0.c {
    private final Context a;
    private final String b;
    private final File c;
    private final int d;
    private final n0.c e;
    private a f;
    private boolean g;

    j(Context context, String str, File file, int i, n0.c cVar) {
        this.a = context;
        this.b = str;
        this.c = file;
        this.d = i;
        this.e = cVar;
    }

    private void a(File file) throws IOException {
        ReadableByteChannel channel;
        if (this.b != null) {
            channel = Channels.newChannel(this.a.getAssets().open(this.b));
        } else {
            if (this.c == null) {
                throw new IllegalStateException("copyFromAssetPath and copyFromFile == null!");
            }
            channel = new FileInputStream(this.c).getChannel();
        }
        File fileCreateTempFile = File.createTempFile("room-copy-helper", ".tmp", this.a.getCacheDir());
        fileCreateTempFile.deleteOnExit();
        m0.d.a(channel, new FileOutputStream(fileCreateTempFile).getChannel());
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Failed to create directories for " + file.getAbsolutePath());
        }
        if (fileCreateTempFile.renameTo(file)) {
            return;
        }
        throw new IOException("Failed to move intermediate file (" + fileCreateTempFile.getAbsolutePath() + ") to destination (" + file.getAbsolutePath() + ").");
    }

    private void c() {
        String databaseName = getDatabaseName();
        File databasePath = this.a.getDatabasePath(databaseName);
        a aVar = this.f;
        m0.a aVar2 = new m0.a(databaseName, this.a.getFilesDir(), aVar == null || aVar.j);
        try {
            aVar2.b();
            if (!databasePath.exists()) {
                try {
                    a(databasePath);
                    aVar2.c();
                    return;
                } catch (IOException e) {
                    throw new RuntimeException("Unable to copy database file.", e);
                }
            }
            if (this.f == null) {
                aVar2.c();
                return;
            }
            try {
                int iC = m0.c.c(databasePath);
                int i = this.d;
                if (iC == i) {
                    aVar2.c();
                    return;
                }
                if (this.f.a(iC, i)) {
                    aVar2.c();
                    return;
                }
                if (this.a.deleteDatabase(databaseName)) {
                    try {
                        a(databasePath);
                    } catch (IOException unused) {
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to delete database file (");
                    sb.append(databaseName);
                    sb.append(") for a copy destructive migration.");
                }
                aVar2.c();
            } catch (IOException unused2) {
                aVar2.c();
            }
        } catch (Throwable th) {
            aVar2.c();
            throw th;
        }
    }

    void b(a aVar) {
        this.f = aVar;
    }

    public synchronized void close() {
        this.e.close();
        this.g = false;
    }

    public String getDatabaseName() {
        return this.e.getDatabaseName();
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        this.e.setWriteAheadLoggingEnabled(z);
    }

    public synchronized n0.b x() {
        if (!this.g) {
            c();
            this.g = true;
        }
        return this.e.x();
    }
}
