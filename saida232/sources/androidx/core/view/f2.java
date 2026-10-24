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
public class f2 {
    public static final f2 b;
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

        public static f2 a(View view) {
            if (d && view.isAttachedToWindow()) {
                try {
                    Object obj = a.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) b.get(obj);
                        Rect rect2 = (Rect) c.get(obj);
                        if (rect != null && rect2 != null) {
                            f2 f2VarA = new b().b(p.c.c(rect)).c(p.c.c(rect2)).a();
                            f2VarA.q(f2VarA);
                            f2VarA.d(view.getRootView());
                            return f2VarA;
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

        e(f2 f2Var) {
            super(f2Var);
        }
    }

    private static class f {
        private final f2 a;
        p.c[] b;

        f() {
            this(new f2((f2) null));
        }

        protected final void a() {
            p.c[] cVarArr = this.b;
            if (cVarArr != null) {
                p.c cVarF = cVarArr[m.a(1)];
                p.c cVarF2 = this.b[m.a(2)];
                if (cVarF2 == null) {
                    cVarF2 = this.a.f(2);
                }
                if (cVarF == null) {
                    cVarF = this.a.f(1);
                }
                f(p.c.a(cVarF, cVarF2));
                p.c cVar = this.b[m.a(16)];
                if (cVar != null) {
                    e(cVar);
                }
                p.c cVar2 = this.b[m.a(32)];
                if (cVar2 != null) {
                    c(cVar2);
                }
                p.c cVar3 = this.b[m.a(64)];
                if (cVar3 != null) {
                    g(cVar3);
                }
            }
        }

        f2 b() {
            a();
            return this.a;
        }

        void c(p.c cVar) {
        }

        void d(p.c cVar) {
        }

        void e(p.c cVar) {
        }

        void f(p.c cVar) {
        }

        void g(p.c cVar) {
        }

        f(f2 f2Var) {
            this.a = f2Var;
        }
    }

    private static class i extends h {
        i(f2 f2Var, WindowInsets windowInsets) {
            super(f2Var, windowInsets);
        }

        @Override // androidx.core.view.f2.l
        f2 a() {
            return f2.t(this.c.consumeDisplayCutout());
        }

        @Override // androidx.core.view.f2.g, androidx.core.view.f2.l
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return Objects.equals(this.c, iVar.c) && Objects.equals(this.g, iVar.g);
        }

        @Override // androidx.core.view.f2.l
        androidx.core.view.h f() {
            return androidx.core.view.h.e(this.c.getDisplayCutout());
        }

        @Override // androidx.core.view.f2.l
        public int hashCode() {
            return this.c.hashCode();
        }

        i(f2 f2Var, i iVar) {
            super(f2Var, iVar);
        }
    }

    private static class k extends j {
        static final f2 r = f2.t(WindowInsets.CONSUMED);

        k(f2 f2Var, WindowInsets windowInsets) {
            super(f2Var, windowInsets);
        }

        @Override // androidx.core.view.f2.g, androidx.core.view.f2.l
        final void d(View view) {
        }

        @Override // androidx.core.view.f2.g, androidx.core.view.f2.l
        public p.c g(int i) {
            return p.c.d(this.c.getInsets(n.a(i)));
        }

        k(f2 f2Var, k kVar) {
            super(f2Var, kVar);
        }
    }

    private static class l {
        static final f2 b = new b().a().a().b().c();
        final f2 a;

        l(f2 f2Var) {
            this.a = f2Var;
        }

        f2 a() {
            return this.a;
        }

        f2 b() {
            return this.a;
        }

        f2 c() {
            return this.a;
        }

        void d(View view) {
        }

        void e(f2 f2Var) {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof l)) {
                return false;
            }
            l lVar = (l) obj;
            return n() == lVar.n() && m() == lVar.m() && x.c.a(k(), lVar.k()) && x.c.a(i(), lVar.i()) && x.c.a(f(), lVar.f());
        }

        androidx.core.view.h f() {
            return null;
        }

        p.c g(int i) {
            return p.c.e;
        }

        p.c h() {
            return k();
        }

        public int hashCode() {
            return x.c.b(new Object[]{Boolean.valueOf(n()), Boolean.valueOf(m()), k(), i(), f()});
        }

        p.c i() {
            return p.c.e;
        }

        p.c j() {
            return k();
        }

        p.c k() {
            return p.c.e;
        }

        p.c l() {
            return k();
        }

        boolean m() {
            return false;
        }

        boolean n() {
            return false;
        }

        public void o(p.c[] cVarArr) {
        }

        void p(p.c cVar) {
        }

        void q(f2 f2Var) {
        }

        public void r(p.c cVar) {
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
        static int a(int i) {
            int iStatusBars;
            int i2 = 0;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i & i3) != 0) {
                    if (i3 == 1) {
                        iStatusBars = WindowInsets.Type.statusBars();
                    } else if (i3 == 2) {
                        iStatusBars = WindowInsets.Type.navigationBars();
                    } else if (i3 == 4) {
                        iStatusBars = WindowInsets.Type.captionBar();
                    } else if (i3 == 8) {
                        iStatusBars = WindowInsets.Type.ime();
                    } else if (i3 == 16) {
                        iStatusBars = WindowInsets.Type.systemGestures();
                    } else if (i3 == 32) {
                        iStatusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i3 == 64) {
                        iStatusBars = WindowInsets.Type.tappableElement();
                    } else if (i3 == 128) {
                        iStatusBars = WindowInsets.Type.displayCutout();
                    }
                    i2 |= iStatusBars;
                }
            }
            return i2;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = k.r;
        } else {
            b = l.b;
        }
    }

    private f2(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.a = new k(this, windowInsets);
            return;
        }
        if (i2 >= 29) {
            this.a = new j(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.a = new i(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.a = new h(this, windowInsets);
        } else if (i2 >= 20) {
            this.a = new g(this, windowInsets);
        } else {
            this.a = new l(this);
        }
    }

    public static f2 t(WindowInsets windowInsets) {
        return u(windowInsets, null);
    }

    public static f2 u(WindowInsets windowInsets, View view) {
        f2 f2Var = new f2(e2.a(x.h.e(windowInsets)));
        if (view != null && view.isAttachedToWindow()) {
            f2Var.q(c1.G(view));
            f2Var.d(view.getRootView());
        }
        return f2Var;
    }

    public f2 a() {
        return this.a.a();
    }

    public f2 b() {
        return this.a.b();
    }

    public f2 c() {
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
        if (obj instanceof f2) {
            return x.c.a(this.a, ((f2) obj).a);
        }
        return false;
    }

    public p.c f(int i2) {
        return this.a.g(i2);
    }

    public p.c g() {
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
        return !this.a.k().equals(p.c.e);
    }

    public boolean m() {
        return this.a.m();
    }

    public f2 n(int i2, int i3, int i4, int i5) {
        return new b(this).c(p.c.b(i2, i3, i4, i5)).a();
    }

    void o(p.c[] cVarArr) {
        this.a.o(cVarArr);
    }

    void p(p.c cVar) {
        this.a.p(cVar);
    }

    void q(f2 f2Var) {
        this.a.q(f2Var);
    }

    void r(p.c cVar) {
        this.a.r(cVar);
    }

    public WindowInsets s() {
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
        private p.c d;

        c() {
            this.c = h();
        }

        private static WindowInsets h() {
            if (!f) {
                try {
                    e = g2.a().getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException unused) {
                }
                f = true;
            }
            Field field = e;
            if (field != null) {
                try {
                    WindowInsets windowInsetsA = e2.a(field.get(null));
                    if (windowInsetsA != null) {
                        return new WindowInsets(windowInsetsA);
                    }
                } catch (ReflectiveOperationException unused2) {
                }
            }
            if (!h) {
                try {
                    g = g2.a().getConstructor(Rect.class);
                } catch (ReflectiveOperationException unused3) {
                }
                h = true;
            }
            Constructor constructor = g;
            if (constructor != null) {
                try {
                    return e2.a(constructor.newInstance(new Rect()));
                } catch (ReflectiveOperationException unused4) {
                }
            }
            return null;
        }

        @Override // androidx.core.view.f2.f
        f2 b() {
            a();
            f2 f2VarT = f2.t(this.c);
            f2VarT.o(this.b);
            f2VarT.r(this.d);
            return f2VarT;
        }

        @Override // androidx.core.view.f2.f
        void d(p.c cVar) {
            this.d = cVar;
        }

        @Override // androidx.core.view.f2.f
        void f(p.c cVar) {
            WindowInsets windowInsets = this.c;
            if (windowInsets != null) {
                this.c = windowInsets.replaceSystemWindowInsets(cVar.a, cVar.b, cVar.c, cVar.d);
            }
        }

        c(f2 f2Var) {
            super(f2Var);
            this.c = f2Var.s();
        }
    }

    private static class d extends f {
        final WindowInsets.Builder c;

        d() {
            this.c = new WindowInsets.Builder();
        }

        @Override // androidx.core.view.f2.f
        f2 b() {
            a();
            f2 f2VarT = f2.t(this.c.build());
            f2VarT.o(this.b);
            return f2VarT;
        }

        @Override // androidx.core.view.f2.f
        void c(p.c cVar) {
            this.c.setMandatorySystemGestureInsets(cVar.e());
        }

        @Override // androidx.core.view.f2.f
        void d(p.c cVar) {
            this.c.setStableInsets(cVar.e());
        }

        @Override // androidx.core.view.f2.f
        void e(p.c cVar) {
            this.c.setSystemGestureInsets(cVar.e());
        }

        @Override // androidx.core.view.f2.f
        void f(p.c cVar) {
            this.c.setSystemWindowInsets(cVar.e());
        }

        @Override // androidx.core.view.f2.f
        void g(p.c cVar) {
            this.c.setTappableElementInsets(cVar.e());
        }

        d(f2 f2Var) {
            WindowInsets.Builder builder;
            super(f2Var);
            WindowInsets windowInsetsS = f2Var.s();
            if (windowInsetsS != null) {
                builder = new WindowInsets.Builder(windowInsetsS);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.c = builder;
        }
    }

    private static class h extends g {
        private p.c n;

        h(f2 f2Var, WindowInsets windowInsets) {
            super(f2Var, windowInsets);
            this.n = null;
        }

        @Override // androidx.core.view.f2.l
        f2 b() {
            return f2.t(this.c.consumeStableInsets());
        }

        @Override // androidx.core.view.f2.l
        f2 c() {
            return f2.t(this.c.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.f2.l
        final p.c i() {
            if (this.n == null) {
                this.n = p.c.b(this.c.getStableInsetLeft(), this.c.getStableInsetTop(), this.c.getStableInsetRight(), this.c.getStableInsetBottom());
            }
            return this.n;
        }

        @Override // androidx.core.view.f2.l
        boolean m() {
            return this.c.isConsumed();
        }

        @Override // androidx.core.view.f2.l
        public void r(p.c cVar) {
            this.n = cVar;
        }

        h(f2 f2Var, h hVar) {
            super(f2Var, hVar);
            this.n = null;
            this.n = hVar.n;
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
        private p.c[] d;
        private p.c e;
        private f2 f;
        p.c g;

        g(f2 f2Var, WindowInsets windowInsets) {
            super(f2Var);
            this.e = null;
            this.c = windowInsets;
        }

        private p.c s(int i2, boolean z) {
            p.c cVarA = p.c.e;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    cVarA = p.c.a(cVarA, t(i3, z));
                }
            }
            return cVarA;
        }

        private p.c u() {
            f2 f2Var = this.f;
            return f2Var != null ? f2Var.g() : p.c.e;
        }

        private p.c v(View view) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if (!h) {
                w();
            }
            Method method = i;
            if (method != null && k != null && l != null) {
                try {
                    Object objInvoke = method.invoke(view, new Object[0]);
                    if (objInvoke == null) {
                        return null;
                    }
                    Rect rect = (Rect) l.get(m.get(objInvoke));
                    if (rect != null) {
                        return p.c.c(rect);
                    }
                    return null;
                } catch (ReflectiveOperationException e) {
                    Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
                }
            }
            return null;
        }

        private static void w() {
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

        @Override // androidx.core.view.f2.l
        void d(View view) {
            p.c cVarV = v(view);
            if (cVarV == null) {
                cVarV = p.c.e;
            }
            p(cVarV);
        }

        @Override // androidx.core.view.f2.l
        void e(f2 f2Var) {
            f2Var.q(this.f);
            f2Var.p(this.g);
        }

        @Override // androidx.core.view.f2.l
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.g, ((g) obj).g);
            }
            return false;
        }

        @Override // androidx.core.view.f2.l
        public p.c g(int i2) {
            return s(i2, false);
        }

        @Override // androidx.core.view.f2.l
        final p.c k() {
            if (this.e == null) {
                this.e = p.c.b(this.c.getSystemWindowInsetLeft(), this.c.getSystemWindowInsetTop(), this.c.getSystemWindowInsetRight(), this.c.getSystemWindowInsetBottom());
            }
            return this.e;
        }

        @Override // androidx.core.view.f2.l
        boolean n() {
            return this.c.isRound();
        }

        @Override // androidx.core.view.f2.l
        public void o(p.c[] cVarArr) {
            this.d = cVarArr;
        }

        @Override // androidx.core.view.f2.l
        void p(p.c cVar) {
            this.g = cVar;
        }

        @Override // androidx.core.view.f2.l
        void q(f2 f2Var) {
            this.f = f2Var;
        }

        protected p.c t(int i2, boolean z) {
            p.c cVarG;
            int i3;
            if (i2 == 1) {
                return z ? p.c.b(0, Math.max(u().b, k().b), 0, 0) : p.c.b(0, k().b, 0, 0);
            }
            if (i2 == 2) {
                if (z) {
                    p.c cVarU = u();
                    p.c cVarI = i();
                    return p.c.b(Math.max(cVarU.a, cVarI.a), 0, Math.max(cVarU.c, cVarI.c), Math.max(cVarU.d, cVarI.d));
                }
                p.c cVarK = k();
                f2 f2Var = this.f;
                cVarG = f2Var != null ? f2Var.g() : null;
                int iMin = cVarK.d;
                if (cVarG != null) {
                    iMin = Math.min(iMin, cVarG.d);
                }
                return p.c.b(cVarK.a, 0, cVarK.c, iMin);
            }
            if (i2 != 8) {
                if (i2 == 16) {
                    return j();
                }
                if (i2 == 32) {
                    return h();
                }
                if (i2 == 64) {
                    return l();
                }
                if (i2 != 128) {
                    return p.c.e;
                }
                f2 f2Var2 = this.f;
                androidx.core.view.h hVarE = f2Var2 != null ? f2Var2.e() : f();
                return hVarE != null ? p.c.b(hVarE.b(), hVarE.d(), hVarE.c(), hVarE.a()) : p.c.e;
            }
            p.c[] cVarArr = this.d;
            cVarG = cVarArr != null ? cVarArr[m.a(8)] : null;
            if (cVarG != null) {
                return cVarG;
            }
            p.c cVarK2 = k();
            p.c cVarU2 = u();
            int i4 = cVarK2.d;
            if (i4 > cVarU2.d) {
                return p.c.b(0, 0, 0, i4);
            }
            p.c cVar = this.g;
            return (cVar == null || cVar.equals(p.c.e) || (i3 = this.g.d) <= cVarU2.d) ? p.c.e : p.c.b(0, 0, 0, i3);
        }

        g(f2 f2Var, g gVar) {
            this(f2Var, new WindowInsets(gVar.c));
        }
    }

    private static class j extends i {
        private p.c o;
        private p.c p;
        private p.c q;

        j(f2 f2Var, WindowInsets windowInsets) {
            super(f2Var, windowInsets);
            this.o = null;
            this.p = null;
            this.q = null;
        }

        @Override // androidx.core.view.f2.l
        p.c h() {
            if (this.p == null) {
                this.p = p.c.d(this.c.getMandatorySystemGestureInsets());
            }
            return this.p;
        }

        @Override // androidx.core.view.f2.l
        p.c j() {
            if (this.o == null) {
                this.o = p.c.d(this.c.getSystemGestureInsets());
            }
            return this.o;
        }

        @Override // androidx.core.view.f2.l
        p.c l() {
            if (this.q == null) {
                this.q = p.c.d(this.c.getTappableElementInsets());
            }
            return this.q;
        }

        @Override // androidx.core.view.f2.h, androidx.core.view.f2.l
        public void r(p.c cVar) {
        }

        j(f2 f2Var, j jVar) {
            super(f2Var, jVar);
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
                return;
            }
            if (i >= 29) {
                this.a = new d();
            } else if (i >= 20) {
                this.a = new c();
            } else {
                this.a = new f();
            }
        }

        public f2 a() {
            return this.a.b();
        }

        public b b(p.c cVar) {
            this.a.d(cVar);
            return this;
        }

        public b c(p.c cVar) {
            this.a.f(cVar);
            return this;
        }

        public b(f2 f2Var) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.a = new e(f2Var);
                return;
            }
            if (i >= 29) {
                this.a = new d(f2Var);
            } else if (i >= 20) {
                this.a = new c(f2Var);
            } else {
                this.a = new f(f2Var);
            }
        }
    }

    public f2(f2 f2Var) {
        if (f2Var != null) {
            l lVar = f2Var.a;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30 && (lVar instanceof k)) {
                this.a = new k(this, (k) lVar);
            } else if (i2 >= 29 && (lVar instanceof j)) {
                this.a = new j(this, (j) lVar);
            } else if (i2 >= 28 && (lVar instanceof i)) {
                this.a = new i(this, (i) lVar);
            } else if (i2 >= 21 && (lVar instanceof h)) {
                this.a = new h(this, (h) lVar);
            } else if (i2 >= 20 && (lVar instanceof g)) {
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
