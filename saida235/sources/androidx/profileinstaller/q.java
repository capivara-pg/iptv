package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class q {
    private static final androidx.concurrent.futures.e a = androidx.concurrent.futures.e.i();
    private static final Object b = new Object();
    private static c c = null;

    private static class a {
        static PackageInfo a(PackageManager packageManager, Context context) {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }

    static class b {
        final int a;
        final int b;
        final long c;
        final long d;

        b(int i, int i2, long j, long j2) {
            this.a = i;
            this.b = i2;
            this.c = j;
            this.d = j2;
        }

        static b a(File file) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                b bVar = new b(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return bVar;
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        void b(File file) throws IOException {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.a);
                dataOutputStream.writeInt(this.b);
                dataOutputStream.writeLong(this.c);
                dataOutputStream.writeLong(this.d);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.b == bVar.b && this.c == bVar.c && this.a == bVar.a && this.d == bVar.d;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.b), Long.valueOf(this.c), Integer.valueOf(this.a), Long.valueOf(this.d));
        }
    }

    public static class c {
        final int a;
        private final boolean b;
        private final boolean c;

        c(int i, boolean z, boolean z2) {
            this.a = i;
            this.c = z2;
            this.b = z;
        }
    }

    private static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? a.a(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    private static c b(int i, boolean z, boolean z2) {
        c cVar = new c(i, z, z2);
        c = cVar;
        a.set(cVar);
        return c;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0044  */
    /* JADX WARN: Code duplicated, block: B:29:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x008a  */
    /* JADX WARN: Code duplicated, block: B:48:0x009b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x009d  */
    /* JADX WARN: Code duplicated, block: B:50:0x009f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:85:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static c c(Context context, boolean z) {
        int i;
        int i2;
        File file;
        boolean z2;
        File file2;
        long length;
        boolean z3;
        File file3;
        b bVarA;
        b bVar;
        int i3;
        c cVar;
        if (!z && (cVar = c) != null) {
            return cVar;
        }
        synchronized (b) {
            if (z) {
                i = Build.VERSION.SDK_INT;
                i2 = 0;
                if (i >= 28) {
                    file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                    long length2 = file.length();
                    if (file.exists()) {
                        z2 = false;
                    } else {
                        z2 = false;
                    }
                    file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                    length = file2.length();
                    if (file2.exists()) {
                        z3 = false;
                    } else {
                        z3 = false;
                    }
                    long jA = a(context);
                    file3 = new File(context.getFilesDir(), "profileInstalled");
                    if (file3.exists()) {
                        bVarA = b.a(file3);
                    } else {
                        bVarA = null;
                    }
                    if (bVarA == null) {
                        if (z2) {
                            i2 = 1;
                        } else if (z3) {
                            i2 = 2;
                        }
                    } else if (z2) {
                        i2 = 1;
                    } else if (z3) {
                        i2 = 2;
                    }
                    if (z) {
                        i2 = 2;
                    }
                    if (bVarA != null) {
                        i2 = 3;
                    }
                    bVar = new b(1, i2, jA, length);
                    if (bVarA != null) {
                        bVar.b(file3);
                    } else {
                        bVar.b(file3);
                    }
                    return b(i2, z2, z3);
                }
                return b(262144, false, false);
            }
            c cVar2 = c;
            if (cVar2 != null) {
                return cVar2;
            }
            i = Build.VERSION.SDK_INT;
            i2 = 0;
            if (i >= 28 && i != 30) {
                file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                long length3 = file.length();
                if (file.exists() || length3 <= 0) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                length = file2.length();
                if (file2.exists() || length <= 0) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                try {
                    long jA2 = a(context);
                    file3 = new File(context.getFilesDir(), "profileInstalled");
                    if (file3.exists()) {
                        try {
                            bVarA = b.a(file3);
                        } catch (IOException unused) {
                            return b(131072, z2, z3);
                        }
                    } else {
                        bVarA = null;
                    }
                    if (bVarA == null && bVarA.c == jA2 && (i3 = bVarA.b) != 2) {
                        i2 = i3;
                    } else if (z2) {
                        i2 = 1;
                    } else if (z3) {
                        i2 = 2;
                    }
                    if (z && z3 && i2 != 1) {
                        i2 = 2;
                    }
                    if (bVarA != null && bVarA.b == 2 && i2 == 1 && length3 < bVarA.d) {
                        i2 = 3;
                    }
                    bVar = new b(1, i2, jA2, length);
                    if (bVarA != null || !bVarA.equals(bVar)) {
                        try {
                            bVar.b(file3);
                        } catch (IOException unused2) {
                            i2 = 196608;
                        }
                    }
                    return b(i2, z2, z3);
                } catch (PackageManager.NameNotFoundException unused3) {
                    return b(65536, z2, z3);
                }
            }
            return b(262144, false, false);
            throw th;
        }
    }
}
