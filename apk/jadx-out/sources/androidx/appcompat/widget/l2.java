package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: loaded from: classes.dex */
class l2 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static l2 j;
    private static l2 k;
    private final View a;
    private final CharSequence b;
    private final int c;
    private final Runnable d = new a();
    private final Runnable e = new b();
    private int f;
    private int g;
    private m2 h;
    private boolean i;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l2.this.g(false);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l2.this.c();
        }
    }

    private l2(View view, CharSequence charSequence) {
        this.a = view;
        this.b = charSequence;
        this.c = androidx.core.view.z0.c(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    private void a() {
        this.a.removeCallbacks(this.d);
    }

    private void b() {
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
    }

    private void d() {
        this.a.postDelayed(this.d, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(l2 l2Var) {
        l2 l2Var2 = j;
        if (l2Var2 != null) {
            l2Var2.a();
        }
        j = l2Var;
        if (l2Var != null) {
            l2Var.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        l2 l2Var = j;
        if (l2Var != null && l2Var.a == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new l2(view, charSequence);
            return;
        }
        l2 l2Var2 = k;
        if (l2Var2 != null && l2Var2.a == view) {
            l2Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f) <= this.c && Math.abs(y - this.g) <= this.c) {
            return false;
        }
        this.f = x;
        this.g = y;
        return true;
    }

    void c() {
        if (k == this) {
            k = null;
            m2 m2Var = this.h;
            if (m2Var != null) {
                m2Var.c();
                this.h = null;
                b();
                this.a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            e(null);
        }
        this.a.removeCallbacks(this.e);
    }

    void g(boolean z) {
        long longPressTimeout;
        long j2;
        long j3;
        if (androidx.core.view.m0.Q(this.a)) {
            e(null);
            l2 l2Var = k;
            if (l2Var != null) {
                l2Var.c();
            }
            k = this;
            this.i = z;
            m2 m2Var = new m2(this.a.getContext());
            this.h = m2Var;
            m2Var.e(this.a, this.f, this.g, this.i, this.b);
            this.a.addOnAttachStateChangeListener(this);
            if (this.i) {
                j3 = 2500;
            } else {
                if ((androidx.core.view.m0.K(this.a) & 1) == 1) {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j2 = 3000;
                } else {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j2 = 15000;
                }
                j3 = j2 - longPressTimeout;
            }
            this.a.removeCallbacks(this.e);
            this.a.postDelayed(this.e, j3);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h != null && this.i) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.a.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.a.isEnabled() && this.h == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        g(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        c();
    }
}
