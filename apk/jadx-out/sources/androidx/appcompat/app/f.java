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

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    private static int a = -100;
    private static final androidx.collection.b b = new androidx.collection.b();
    private static final Object c = new Object();

    f() {
    }

    static void a(f fVar) {
        synchronized (c) {
            x(fVar);
            b.add(new WeakReference(fVar));
        }
    }

    public static f e(Activity activity, e eVar) {
        return new h(activity, eVar);
    }

    public static f f(Dialog dialog, e eVar) {
        return new h(dialog, eVar);
    }

    public static int h() {
        return a;
    }

    static void w(f fVar) {
        synchronized (c) {
            x(fVar);
        }
    }

    private static void x(f fVar) {
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

    public abstract void A(View view);

    public abstract void B(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void C(Toolbar toolbar);

    public abstract void D(int i);

    public abstract void E(CharSequence charSequence);

    public abstract h.b F(h.b.a aVar);

    public abstract void b(View view, ViewGroup.LayoutParams layoutParams);

    public void c(Context context) {
    }

    public Context d(Context context) {
        c(context);
        return context;
    }

    public abstract View g(int i);

    public abstract b i();

    public abstract int j();

    public abstract MenuInflater k();

    public abstract a l();

    public abstract void m();

    public abstract void n();

    public abstract void o(Configuration configuration);

    public abstract void p(Bundle bundle);

    public abstract void q();

    public abstract void r(Bundle bundle);

    public abstract void s();

    public abstract void t(Bundle bundle);

    public abstract void u();

    public abstract void v();

    public abstract boolean y(int i);

    public abstract void z(int i);
}
