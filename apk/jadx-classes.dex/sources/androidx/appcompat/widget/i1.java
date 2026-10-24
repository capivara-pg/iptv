package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public interface i1 {
    boolean a();

    boolean b();

    boolean c();

    void collapseActionView();

    void d(Menu menu, androidx.appcompat.view.menu.m.a aVar);

    boolean e();

    void f();

    boolean g();

    Context getContext();

    CharSequence getTitle();

    boolean h();

    void i(int i);

    void j(CharSequence charSequence);

    Menu k();

    int l();

    androidx.core.view.a2 m(int i, long j);

    ViewGroup n();

    void o(boolean z);

    void p();

    void q(boolean z);

    void r();

    void s(j2 j2Var);

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setTitle(CharSequence charSequence);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t(int i);

    void u(int i);

    void v(androidx.appcompat.view.menu.m.a aVar, androidx.appcompat.view.menu.g.a aVar2);

    void w(int i);

    int x();

    void y();
}
