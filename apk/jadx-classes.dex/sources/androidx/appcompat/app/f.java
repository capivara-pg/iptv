package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class f {
    private static int a = -100;
    private static final androidx.collection.b b = new androidx.collection.b();
    private static final Object c = new Object();

    f() {
    }

    public static f c(Activity activity, e eVar) {
        return new h(activity, eVar);
    }

    public static f d(Dialog dialog, e eVar) {
        return new h(dialog, eVar);
    }

    public static int f() {
        return a;
    }

    static void m(f fVar) {
        synchronized (c) {
            w(fVar);
            b.add(new WeakReference(fVar));
        }
    }

    static void n(f fVar) {
        synchronized (c) {
            w(fVar);
        }
    }

    private static void w(f fVar) {
        synchronized (c) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                f fVar2 = (f) ((WeakReference) it.next()).get();
                if (fVar2 == fVar || fVar2 == null) {
                    it.remove();
                }
            }
        }
    }

    public abstract void A(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void B(Toolbar toolbar);

    public abstract void C(int i);

    public abstract void D(CharSequence charSequence);

    public abstract f.b E(f.b.a aVar);

    public abstract void a(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void b(Context context);

    public abstract View e(int i);

    public abstract b g();

    public abstract int h();

    public abstract MenuInflater i();

    public abstract a j();

    public abstract void k();

    public abstract void l();

    public abstract void o(Configuration configuration);

    public abstract void p(Bundle bundle);

    public abstract void q();

    public abstract void r(Bundle bundle);

    public abstract void s();

    public abstract void t(Bundle bundle);

    public abstract void u();

    public abstract void v();

    public abstract boolean x(int i);

    public abstract void y(int i);

    public abstract void z(View view);
}
