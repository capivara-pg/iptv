package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {
    private static final c a = new a();
    static final c b = new b();

    class a implements c {
        a() {
        }

        @Override // androidx.profileinstaller.j.c
        public void a(int i, Object obj) {
        }

        @Override // androidx.profileinstaller.j.c
        public void b(int i, Object obj) {
        }
    }

    class b implements c {
        b() {
        }

        @Override // androidx.profileinstaller.j.c
        public void a(int i, Object obj) {
            String str;
            switch (i) {
                case 1:
                    str = "RESULT_INSTALL_SUCCESS";
                    break;
                case 2:
                    str = "RESULT_ALREADY_INSTALLED";
                    break;
                case 3:
                    str = "RESULT_UNSUPPORTED_ART_VERSION";
                    break;
                case 4:
                    str = "RESULT_NOT_WRITABLE";
                    break;
                case 5:
                    str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                    break;
                case 6:
                    str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                    break;
                case 7:
                    str = "RESULT_IO_EXCEPTION";
                    break;
                case 8:
                    str = "RESULT_PARSE_EXCEPTION";
                    break;
                case 9:
                default:
                    str = "";
                    break;
                case 10:
                    str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                    break;
                case 11:
                    str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                    break;
            }
            if (i == 6 || i == 7 || i == 8) {
                Log.e("ProfileInstaller", str, (Throwable) obj);
            }
        }

        @Override // androidx.profileinstaller.j.c
        public void b(int i, Object obj) {
        }
    }

    public interface c {
        void a(int i, Object obj);

        void b(int i, Object obj);
    }

    static boolean b(File file) {
        return new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
    }

    static void c(Context context, Executor executor, c cVar) {
        b(context.getFilesDir());
        g(executor, cVar, 11, null);
    }

    static boolean d(PackageInfo packageInfo, File file, c cVar) {
        File file2 = new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
        if (!file2.exists()) {
            return false;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file2));
            try {
                long j = dataInputStream.readLong();
                dataInputStream.close();
                boolean z = j == packageInfo.lastUpdateTime;
                if (z) {
                    cVar.a(2, null);
                }
                return z;
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    static void f(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
        }
    }

    static void g(Executor executor, final c cVar, final int i, final Object obj) {
        executor.execute(new Runnable() { // from class: androidx.profileinstaller.i
            @Override // java.lang.Runnable
            public final void run() {
                cVar.a(i, obj);
            }
        });
    }

    private static boolean h(AssetManager assetManager, String str, PackageInfo packageInfo, File file, String str2, Executor executor, c cVar) {
        d dVar = new d(assetManager, executor, cVar, str2, "dexopt/baseline.prof", "dexopt/baseline.profm", new File(new File("/data/misc/profiles/cur/0", str), "primary.prof"));
        if (!dVar.e()) {
            return false;
        }
        boolean zN = dVar.i().m().n();
        if (zN) {
            f(packageInfo, file);
        }
        return zN;
    }

    public static void i(Context context) {
        j(context, new h(), a);
    }

    public static void j(Context context, Executor executor, c cVar) {
        k(context, executor, cVar, false);
    }

    static void k(Context context, Executor executor, c cVar, boolean z) {
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        AssetManager assets = applicationContext.getAssets();
        String name = new File(applicationInfo.sourceDir).getName();
        boolean z2 = false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            File filesDir = context.getFilesDir();
            if (!z && d(packageInfo, filesDir, cVar)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Skipping profile installation for ");
                sb.append(context.getPackageName());
                q.c(context, false);
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Installing profile for ");
            sb2.append(context.getPackageName());
            if (h(assets, packageName, packageInfo, filesDir, name, executor, cVar) && z) {
                z2 = true;
            }
            q.c(context, z2);
        } catch (PackageManager.NameNotFoundException e) {
            cVar.a(7, e);
            q.c(context, false);
        }
    }

    static void l(Context context, Executor executor, c cVar) {
        try {
            f(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
            g(executor, cVar, 10, null);
        } catch (PackageManager.NameNotFoundException e) {
            g(executor, cVar, 7, e);
        }
    }
}
