package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class g1 {
    public static final g1 b;
    private final l a;

    static class a {
        private static Field a;
        private static Field b;
        private static Field c;
        private static boolean d;

        static {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                a = declaredField;
                declaredField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                Field declaredField2 = cls.getDeclaredField("mStableInsets");
                b = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("mContentInsets");
                c = declaredField3;
                declaredField3.setAccessible(true);
                d = true;
            } catch (ReflectiveOperationException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to get visible insets from AttachInfo ");
                sb.append(e.getMessage());
            }
        }

        public static g1 a(View view) {
            if (d && view.isAttachedToWindow()) {
                try {
                    Object obj = a.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) b.get(obj);
                        Rect rect2 = (Rect) c.get(obj);
                        if (rect != null && rect2 != null) {
                            g1 g1VarA = new b().b(u.c.c(rect)).c(u.c.c(rect2)).a();
                            g1VarA.s(g1VarA);
                            g1VarA.d(view.getRootView());
                            return g1VarA;
                        }
                    }
                } catch (IllegalAccessException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to get insets from AttachInfo. ");
                    sb.append(e.getMessage());
                }
            }
            return null;
        }
    }

    private static class e extends d {
        e() {
        }

        e(g1 insets) {
            super(insets);
        }
    }

    private static class f {
        private final g1 a;
        u.c[] b;

        f() {
            this(new g1((g1) null));
        }

        protected final void a() {
            u.c[] cVarArr = this.b;
            if (cVarArr != null) {
                u.c cVarF = cVarArr[m.a(1)];
                u.c cVarF2 = this.b[m.a(2)];
                if (cVarF2 == null) {
                    cVarF2 = this.a.f(2);
                }
                if (cVarF == null) {
                    cVarF = this.a.f(1);
                }
                f(u.c.a(cVarF, cVarF2));
                u.c cVar = this.b[m.a(16)];
                if (cVar != null) {
                    e(cVar);
                }
                u.c cVar2 = this.b[m.a(32)];
                if (cVar2 != null) {
                    c(cVar2);
                }
                u.c cVar3 = this.b[m.a(64)];
                if (cVar3 != null) {
                    g(cVar3);
                }
            }
        }

        abstract g1 b();

        void c(u.c insets) {
        }

        abstract void d(u.c insets);

        void e(u.c insets) {
        }

        abstract void f(u.c insets);

        void g(u.c insets) {
        }

        f(g1 insets) {
            this.a = insets;
        }
    }

    private static class i extends h {
        i(g1 host, WindowInsets insets) {
            super(host, insets);
        }

        @Override // androidx.core.view.g1.l
        g1 a() {
            return g1.v(this.c.consumeDisplayCutout());
        }

        @Override // androidx.core.view.g1.g, androidx.core.view.g1.l
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof i)) {
                return false;
            }
            i iVar = (i) o;
            return Objects.equals(this.c, iVar.c) && Objects.equals(this.g, iVar.g);
        }

        @Override // androidx.core.view.g1.l
        androidx.core.view.h f() {
            return androidx.core.view.h.e(this.c.getDisplayCutout());
        }

        @Override // androidx.core.view.g1.l
        public int hashCode() {
            return this.c.hashCode();
        }

        i(g1 host, i other) {
            super(host, other);
        }
    }

    private static class k extends j {
        static final g1 r = g1.v(WindowInsets.CONSUMED);

        k(g1 host, WindowInsets insets) {
            super(host, insets);
        }

        @Override // androidx.core.view.g1.g, androidx.core.view.g1.l
        final void d(View rootView) {
        }

        @Override // androidx.core.view.g1.g, androidx.core.view.g1.l
        public u.c g(int typeMask) {
            return u.c.d(this.c.getInsets(n.a(typeMask)));
        }

        k(g1 host, k other) {
            super(host, other);
        }
    }

    private static class l {
        static final g1 b = new b().a().a().b().c();
        final g1 a;

        l(g1 host) {
            this.a = host;
        }

        g1 a() {
            return this.a;
        }

        g1 b() {
            return this.a;
        }

        g1 c() {
            return this.a;
        }

        void d(View rootView) {
        }

        void e(g1 other) {
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof l)) {
                return false;
            }
            l lVar = (l) o;
            return o() == lVar.o() && n() == lVar.n() && c0.c.a(k(), lVar.k()) && c0.c.a(i(), lVar.i()) && c0.c.a(f(), lVar.f());
        }

        androidx.core.view.h f() {
            return null;
        }

        u.c g(int typeMask) {
            return u.c.e;
        }

        u.c h() {
            return k();
        }

        public int hashCode() {
            return c0.c.b(new Object[]{Boolean.valueOf(o()), Boolean.valueOf(n()), k(), i(), f()});
        }

        u.c i() {
            return u.c.e;
        }

        u.c j() {
            return k();
        }

        u.c k() {
            return u.c.e;
        }

        u.c l() {
            return k();
        }

        g1 m(int left, int top, int right, int bottom) {
            return b;
        }

        boolean n() {
            return false;
        }

        boolean o() {
            return false;
        }

        public void p(u.c[] insetsTypeMask) {
        }

        void q(u.c visibleInsets) {
        }

        void r(g1 rootWindowInsets) {
        }

        public void s(u.c stableInsets) {
        }
    }

    public static final class m {
        static int a(int i) {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 8) {
                return 3;
            }
            if (i == 16) {
                return 4;
            }
            if (i == 32) {
                return 5;
            }
            if (i == 64) {
                return 6;
            }
            if (i == 128) {
                return 7;
            }
            if (i == 256) {
                return 8;
            }
            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i);
        }
    }

    private static final class n {
        static int a(final int typeMask) {
            int iStatusBars;
            int i = 0;
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((typeMask & i2) != 0) {
                    if (i2 == 1) {
                        iStatusBars = WindowInsets.Type.statusBars();
                    } else if (i2 == 2) {
                        iStatusBars = WindowInsets.Type.navigationBars();
                    } else if (i2 == 4) {
                        iStatusBars = WindowInsets.Type.captionBar();
                    } else if (i2 == 8) {
                        iStatusBars = WindowInsets.Type.ime();
                    } else if (i2 == 16) {
                        iStatusBars = WindowInsets.Type.systemGestures();
                    } else if (i2 == 32) {
                        iStatusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i2 == 64) {
                        iStatusBars = WindowInsets.Type.tappableElement();
                    } else if (i2 == 128) {
                        iStatusBars = WindowInsets.Type.displayCutout();
                    }
                    i |= iStatusBars;
                }
            }
            return i;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = k.r;
        } else {
            b = l.b;
        }
    }

    private g1(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.a = new k(this, windowInsets);
            return;
        }
        if (i2 >= 29) {
            this.a = new j(this, windowInsets);
        } else if (i2 >= 28) {
            this.a = new i(this, windowInsets);
        } else {
            this.a = new h(this, windowInsets);
        }
    }

    static u.c n(u.c cVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, cVar.a - i2);
        int iMax2 = Math.max(0, cVar.b - i3);
        int iMax3 = Math.max(0, cVar.c - i4);
        int iMax4 = Math.max(0, cVar.d - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? cVar : u.c.b(iMax, iMax2, iMax3, iMax4);
    }

    public static g1 v(WindowInsets windowInsets) {
        return w(windowInsets, null);
    }

    public static g1 w(WindowInsets windowInsets, View view) {
        g1 g1Var = new g1((WindowInsets) c0.h.e(windowInsets));
        if (view != null && view.isAttachedToWindow()) {
            g1Var.s(m0.H(view));
            g1Var.d(view.getRootView());
        }
        return g1Var;
    }

    public g1 a() {
        return this.a.a();
    }

    public g1 b() {
        return this.a.b();
    }

    public g1 c() {
        return this.a.c();
    }

    void d(View view) {
        this.a.d(view);
    }

    public androidx.core.view.h e() {
        return this.a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g1) {
            return c0.c.a(this.a, ((g1) obj).a);
        }
        return false;
    }

    public u.c f(int i2) {
        return this.a.g(i2);
    }

    public u.c g() {
        return this.a.i();
    }

    public int h() {
        return this.a.k().d;
    }

    public int hashCode() {
        l lVar = this.a;
        if (lVar == null) {
            return 0;
        }
        return lVar.hashCode();
    }

    public int i() {
        return this.a.k().a;
    }

    public int j() {
        return this.a.k().c;
    }

    public int k() {
        return this.a.k().b;
    }

    public boolean l() {
        return !this.a.k().equals(u.c.e);
    }

    public g1 m(int i2, int i3, int i4, int i5) {
        return this.a.m(i2, i3, i4, i5);
    }

    public boolean o() {
        return this.a.n();
    }

    public g1 p(int i2, int i3, int i4, int i5) {
        return new b(this).c(u.c.b(i2, i3, i4, i5)).a();
    }

    void q(u.c[] cVarArr) {
        this.a.p(cVarArr);
    }

    void r(u.c cVar) {
        this.a.q(cVar);
    }

    void s(g1 g1Var) {
        this.a.r(g1Var);
    }

    void t(u.c cVar) {
        this.a.s(cVar);
    }

    public WindowInsets u() {
        l lVar = this.a;
        if (lVar instanceof g) {
            return ((g) lVar).c;
        }
        return null;
    }

    private static class c extends f {
        private static Field e;
        private static boolean f;
        private static Constructor g;
        private static boolean h;
        private WindowInsets c;
        private u.c d;

        c() {
            this.c = h();
        }

        private static WindowInsets h() {
            if (!f) {
                try {
                    e = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException unused) {
                }
                f = true;
            }
            Field field = e;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException unused2) {
                }
            }
            if (!h) {
                try {
                    g = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException unused3) {
                }
                h = true;
            }
            Constructor constructor = g;
            if (constructor != null) {
                try {
                    return (WindowInsets) constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException unused4) {
                }
            }
            return null;
        }

        @Override // androidx.core.view.g1.f
        g1 b() {
            a();
            g1 g1VarV = g1.v(this.c);
            g1VarV.q(this.b);
            g1VarV.t(this.d);
            return g1VarV;
        }

        @Override // androidx.core.view.g1.f
        void d(u.c insets) {
            this.d = insets;
        }

        @Override // androidx.core.view.g1.f
        void f(u.c insets) {
            WindowInsets windowInsets = this.c;
            if (windowInsets != null) {
                this.c = windowInsets.replaceSystemWindowInsets(insets.a, insets.b, insets.c, insets.d);
            }
        }

        c(g1 insets) {
            super(insets);
            this.c = insets.u();
        }
    }

    private static class d extends f {
        final WindowInsets.Builder c;

        d() {
            this.c = n1.a();
        }

        @Override // androidx.core.view.g1.f
        g1 b() {
            a();
            g1 g1VarV = g1.v(this.c.build());
            g1VarV.q(this.b);
            return g1VarV;
        }

        @Override // androidx.core.view.g1.f
        void c(u.c insets) {
            this.c.setMandatorySystemGestureInsets(insets.e());
        }

        @Override // androidx.core.view.g1.f
        void d(u.c insets) {
            this.c.setStableInsets(insets.e());
        }

        @Override // androidx.core.view.g1.f
        void e(u.c insets) {
            this.c.setSystemGestureInsets(insets.e());
        }

        @Override // androidx.core.view.g1.f
        void f(u.c insets) {
            this.c.setSystemWindowInsets(insets.e());
        }

        @Override // androidx.core.view.g1.f
        void g(u.c insets) {
            this.c.setTappableElementInsets(insets.e());
        }

        d(g1 insets) {
            WindowInsets.Builder builderA;
            super(insets);
            WindowInsets windowInsetsU = insets.u();
            if (windowInsetsU != null) {
                builderA = o1.a(windowInsetsU);
            } else {
                builderA = n1.a();
            }
            this.c = builderA;
        }
    }

    private static class h extends g {
        private u.c n;

        h(g1 host, WindowInsets insets) {
            super(host, insets);
            this.n = null;
        }

        @Override // androidx.core.view.g1.l
        g1 b() {
            return g1.v(this.c.consumeStableInsets());
        }

        @Override // androidx.core.view.g1.l
        g1 c() {
            return g1.v(this.c.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.g1.l
        final u.c i() {
            if (this.n == null) {
                this.n = u.c.b(this.c.getStableInsetLeft(), this.c.getStableInsetTop(), this.c.getStableInsetRight(), this.c.getStableInsetBottom());
            }
            return this.n;
        }

        @Override // androidx.core.view.g1.l
        boolean n() {
            return this.c.isConsumed();
        }

        @Override // androidx.core.view.g1.l
        public void s(u.c stableInsets) {
            this.n = stableInsets;
        }

        h(g1 host, h other) {
            super(host, other);
            this.n = null;
            this.n = other.n;
        }
    }

    private static class g extends l {
        private static boolean h;
        private static Method i;
        private static Class j;
        private static Class k;
        private static Field l;
        private static Field m;
        final WindowInsets c;
        private u.c[] d;
        private u.c e;
        private g1 f;
        u.c g;

        g(g1 host, WindowInsets insets) {
            super(host);
            this.e = null;
            this.c = insets;
        }

        private u.c t(final int typeMask, final boolean ignoreVisibility) {
            u.c cVarA = u.c.e;
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((typeMask & i2) != 0) {
                    cVarA = u.c.a(cVarA, u(i2, ignoreVisibility));
                }
            }
            return cVarA;
        }

        private u.c v() {
            g1 g1Var = this.f;
            return g1Var != null ? g1Var.g() : u.c.e;
        }

        private u.c w(View rootView) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if (!h) {
                x();
            }
            Method method = i;
            if (method != null && k != null && l != null) {
                try {
                    Object objInvoke = method.invoke(rootView, new Object[0]);
                    if (objInvoke == null) {
                        return null;
                    }
                    Rect rect = (Rect) l.get(m.get(objInvoke));
                    if (rect != null) {
                        return u.c.c(rect);
                    }
                    return null;
                } catch (ReflectiveOperationException e) {
                    Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
                }
            }
            return null;
        }

        private static void x() {
            try {
                i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                j = Class.forName("android.view.ViewRootImpl");
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                k = cls;
                l = cls.getDeclaredField("mVisibleInsets");
                m = j.getDeclaredField("mAttachInfo");
                l.setAccessible(true);
                m.setAccessible(true);
            } catch (ReflectiveOperationException e) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
            h = true;
        }

        @Override // androidx.core.view.g1.l
        void d(View rootView) {
            u.c cVarW = w(rootView);
            if (cVarW == null) {
                cVarW = u.c.e;
            }
            q(cVarW);
        }

        @Override // androidx.core.view.g1.l
        void e(g1 other) {
            other.s(this.f);
            other.r(this.g);
        }

        @Override // androidx.core.view.g1.l
        public boolean equals(Object o) {
            if (super.equals(o)) {
                return Objects.equals(this.g, ((g) o).g);
            }
            return false;
        }

        @Override // androidx.core.view.g1.l
        public u.c g(int typeMask) {
            return t(typeMask, false);
        }

        @Override // androidx.core.view.g1.l
        final u.c k() {
            if (this.e == null) {
                this.e = u.c.b(this.c.getSystemWindowInsetLeft(), this.c.getSystemWindowInsetTop(), this.c.getSystemWindowInsetRight(), this.c.getSystemWindowInsetBottom());
            }
            return this.e;
        }

        @Override // androidx.core.view.g1.l
        g1 m(int left, int top, int right, int bottom) {
            b bVar = new b(g1.v(this.c));
            bVar.c(g1.n(k(), left, top, right, bottom));
            bVar.b(g1.n(i(), left, top, right, bottom));
            return bVar.a();
        }

        @Override // androidx.core.view.g1.l
        boolean o() {
            return this.c.isRound();
        }

        @Override // androidx.core.view.g1.l
        public void p(u.c[] insetsTypeMask) {
            this.d = insetsTypeMask;
        }

        @Override // androidx.core.view.g1.l
        void q(u.c visibleInsets) {
            this.g = visibleInsets;
        }

        @Override // androidx.core.view.g1.l
        void r(g1 rootWindowInsets) {
            this.f = rootWindowInsets;
        }

        protected u.c u(int type, boolean ignoreVisibility) {
            u.c cVarG;
            int i2;
            if (type == 1) {
                return ignoreVisibility ? u.c.b(0, Math.max(v().b, k().b), 0, 0) : u.c.b(0, k().b, 0, 0);
            }
            if (type == 2) {
                if (ignoreVisibility) {
                    u.c cVarV = v();
                    u.c cVarI = i();
                    return u.c.b(Math.max(cVarV.a, cVarI.a), 0, Math.max(cVarV.c, cVarI.c), Math.max(cVarV.d, cVarI.d));
                }
                u.c cVarK = k();
                g1 g1Var = this.f;
                cVarG = g1Var != null ? g1Var.g() : null;
                int iMin = cVarK.d;
                if (cVarG != null) {
                    iMin = Math.min(iMin, cVarG.d);
                }
                return u.c.b(cVarK.a, 0, cVarK.c, iMin);
            }
            if (type != 8) {
                if (type == 16) {
                    return j();
                }
                if (type == 32) {
                    return h();
                }
                if (type == 64) {
                    return l();
                }
                if (type != 128) {
                    return u.c.e;
                }
                g1 g1Var2 = this.f;
                androidx.core.view.h hVarE = g1Var2 != null ? g1Var2.e() : f();
                return hVarE != null ? u.c.b(hVarE.b(), hVarE.d(), hVarE.c(), hVarE.a()) : u.c.e;
            }
            u.c[] cVarArr = this.d;
            cVarG = cVarArr != null ? cVarArr[m.a(8)] : null;
            if (cVarG != null) {
                return cVarG;
            }
            u.c cVarK2 = k();
            u.c cVarV2 = v();
            int i3 = cVarK2.d;
            if (i3 > cVarV2.d) {
                return u.c.b(0, 0, 0, i3);
            }
            u.c cVar = this.g;
            return (cVar == null || cVar.equals(u.c.e) || (i2 = this.g.d) <= cVarV2.d) ? u.c.e : u.c.b(0, 0, 0, i2);
        }

        g(g1 host, g other) {
            this(host, new WindowInsets(other.c));
        }
    }

    private static class j extends i {
        private u.c o;
        private u.c p;
        private u.c q;

        j(g1 host, WindowInsets insets) {
            super(host, insets);
            this.o = null;
            this.p = null;
            this.q = null;
        }

        @Override // androidx.core.view.g1.l
        u.c h() {
            if (this.p == null) {
                this.p = u.c.d(this.c.getMandatorySystemGestureInsets());
            }
            return this.p;
        }

        @Override // androidx.core.view.g1.l
        u.c j() {
            if (this.o == null) {
                this.o = u.c.d(this.c.getSystemGestureInsets());
            }
            return this.o;
        }

        @Override // androidx.core.view.g1.l
        u.c l() {
            if (this.q == null) {
                this.q = u.c.d(this.c.getTappableElementInsets());
            }
            return this.q;
        }

        @Override // androidx.core.view.g1.g, androidx.core.view.g1.l
        g1 m(int left, int top, int right, int bottom) {
            return g1.v(this.c.inset(left, top, right, bottom));
        }

        @Override // androidx.core.view.g1.h, androidx.core.view.g1.l
        public void s(u.c stableInsets) {
        }

        j(g1 host, j other) {
            super(host, other);
            this.o = null;
            this.p = null;
            this.q = null;
        }
    }

    public static final class b {
        private final f a;

        public b() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.a = new e();
            } else if (i >= 29) {
                this.a = new d();
            } else {
                this.a = new c();
            }
        }

        public g1 a() {
            return this.a.b();
        }

        public b b(u.c cVar) {
            this.a.d(cVar);
            return this;
        }

        public b c(u.c cVar) {
            this.a.f(cVar);
            return this;
        }

        public b(g1 g1Var) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.a = new e(g1Var);
            } else if (i >= 29) {
                this.a = new d(g1Var);
            } else {
                this.a = new c(g1Var);
            }
        }
    }

    public g1(g1 g1Var) {
        if (g1Var != null) {
            l lVar = g1Var.a;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30 && (lVar instanceof k)) {
                this.a = new k(this, (k) lVar);
            } else if (i2 >= 29 && (lVar instanceof j)) {
                this.a = new j(this, (j) lVar);
            } else if (i2 >= 28 && (lVar instanceof i)) {
                this.a = new i(this, (i) lVar);
            } else if (lVar instanceof h) {
                this.a = new h(this, (h) lVar);
            } else if (lVar instanceof g) {
                this.a = new g(this, (g) lVar);
            } else {
                this.a = new l(this);
            }
            lVar.e(this);
            return;
        }
        this.a = new l(this);
    }
}
