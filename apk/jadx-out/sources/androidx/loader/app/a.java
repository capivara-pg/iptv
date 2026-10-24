package androidx.loader.app;

import android.os.Bundle;
import androidx.lifecycle.k0;
import androidx.lifecycle.m;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: androidx.loader.app.a$a, reason: collision with other inner class name */
    public interface InterfaceC0039a {
        androidx.loader.content.b onCreateLoader(int i, Bundle bundle);

        void onLoadFinished(androidx.loader.content.b bVar, Object obj);

        void onLoaderReset(androidx.loader.content.b bVar);
    }

    public static a b(m mVar) {
        return new b(mVar, ((k0) mVar).getViewModelStore());
    }

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract androidx.loader.content.b c(int i, Bundle bundle, InterfaceC0039a interfaceC0039a);

    public abstract void d();
}
