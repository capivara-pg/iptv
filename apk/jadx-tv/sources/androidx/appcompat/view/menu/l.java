package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.core.view.c1;

/* JADX INFO: loaded from: classes.dex */
public class l {
    private final Context a;
    private final g b;
    private final boolean c;
    private final int d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
    private m.a i;
    private k j;
    private PopupWindow.OnDismissListener k;
    private final PopupWindow.OnDismissListener l;

    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.e();
        }
    }

    public l(Context context, g gVar, View view, boolean z, int i) {
        this(context, gVar, view, z, i, 0);
    }

    private k a() {
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        k dVar = Math.min(point.x, point.y) >= this.a.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width) ? new d(this.a, this.f, this.d, this.e, this.c) : new q(this.a, this.b, this.f, this.d, this.e, this.c);
        dVar.a(this.b);
        dVar.j(this.l);
        dVar.e(this.f);
        dVar.setCallback(this.i);
        dVar.g(this.h);
        dVar.h(this.g);
        return dVar;
    }

    private void l(int i, int i2, boolean z, boolean z2) {
        k kVarC = c();
        kVarC.k(z2);
        if (z) {
            if ((androidx.core.view.j.b(this.g, c1.z(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            kVarC.i(i);
            kVarC.l(i2);
            int i3 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarC.f(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        kVarC.show();
    }

    public void b() {
        if (d()) {
            this.j.dismiss();
        }
    }

    public k c() {
        if (this.j == null) {
            this.j = a();
        }
        return this.j;
    }

    public boolean d() {
        k kVar = this.j;
        return kVar != null && kVar.isShowing();
    }

    protected void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f = view;
    }

    public void g(boolean z) {
        this.h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.g(z);
        }
    }

    public void h(int i) {
        this.g = i;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.i = aVar;
        k kVar = this.j;
        if (kVar != null) {
            kVar.setCallback(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i, int i2) {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(i, i2, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new a();
        this.a = context;
        this.b = gVar;
        this.f = view;
        this.c = z;
        this.d = i;
        this.e = i2;
    }
}
