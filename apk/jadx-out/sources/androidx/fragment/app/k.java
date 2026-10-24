package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends g {
    private final Activity a;
    private final Context b;
    private final Handler c;
    private final int d;
    final n e;

    /* JADX WARN: Multi-variable type inference failed */
    k(e eVar) {
        this(eVar, eVar, new Handler(), 0);
    }

    Activity e() {
        return this.a;
    }

    Context f() {
        return this.b;
    }

    Handler g() {
        return this.c;
    }

    public abstract void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract Object i();

    public abstract LayoutInflater j();

    public void k(Fragment fragment, String[] strArr, int i) {
    }

    public abstract boolean l(Fragment fragment);

    public abstract boolean m(String str);

    public void n(Fragment fragment, Intent intent, int i, Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        androidx.core.content.a.startActivity(this.b, intent, bundle);
    }

    public void o(Fragment fragment, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        s.c.j(this.a, intentSender, i, intent, i2, i3, i4, bundle);
    }

    public abstract void p();

    k(Activity activity, Context context, Handler handler, int i) {
        this.e = new o();
        this.a = activity;
        this.b = (Context) c0.h.f(context, "context == null");
        this.c = (Handler) c0.h.f(handler, "handler == null");
        this.d = i;
    }
}
