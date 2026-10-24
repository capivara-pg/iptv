package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
abstract class a {

    /* JADX INFO: renamed from: androidx.profileinstaller.a$a, reason: collision with other inner class name */
    private static class C0048a {
        static File a(Context context) {
            return context.getCodeCacheDir();
        }
    }

    private static class b {
        static File a(Context context) {
            return context.createDeviceProtectedStorageContext().getCodeCacheDir();
        }
    }

    static boolean a(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file2 : fileArrListFiles) {
            z = a(file2) && z;
        }
        return z;
    }

    static void b(Context context, ProfileInstallReceiver.a aVar) {
        File fileA;
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            fileA = b.a(context);
        } else {
            fileA = i >= 23 ? C0048a.a(context) : context.getCacheDir();
        }
        if (a(fileA)) {
            aVar.a(14, null);
        } else {
            aVar.a(15, null);
        }
    }
}
