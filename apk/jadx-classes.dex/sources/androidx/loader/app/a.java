package androidx.loader.app;

import android.os.Bundle;
import androidx.lifecycle.g;
import androidx.lifecycle.y;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: androidx.loader.app.a$a, reason: collision with other inner class name */
    public interface InterfaceC0033a {
        androidx.loader.content.b onCreateLoader(int i, Bundle bundle);

        void onLoadFinished(androidx.loader.content.b bVar, Object obj);

        void onLoaderReset(androidx.loader.content.b bVar);
    }

    public static a b(g gVar) {
        return new b(gVar, ((y) gVar).getViewModelStore());
    }

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract androidx.loader.content.b c(int i, Bundle bundle, InterfaceC0033a interfaceC0033a);

    public abstract void d();
}
