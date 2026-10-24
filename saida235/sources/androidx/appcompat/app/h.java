package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.b1;
import androidx.appcompat.widget.h2;
import androidx.appcompat.widget.n2;
import androidx.appcompat.widget.o2;
import androidx.core.view.a0;
import androidx.core.view.c1;
import androidx.core.view.e1;
import androidx.core.view.g1;
import androidx.core.view.m0;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
class h extends androidx.appcompat.app.f implements androidx.appcompat.view.menu.g.a, LayoutInflater.Factory2 {
    private static final androidx.collection.g g0 = new androidx.collection.g();
    private static final boolean h0 = false;
    private static final int[] i0 = {R.attr.windowBackground};
    private static final boolean j0 = !"robolectric".equals(Build.FINGERPRINT);
    private static final boolean k0 = true;
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    private boolean F;
    private r[] G;
    private r H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    boolean M;
    private int N;
    private int Q;
    private boolean S;
    private boolean V;
    private k W;
    private k X;
    boolean Y;
    int Z;
    private final Runnable b0;
    private boolean c0;
    final Object d;
    private Rect d0;
    final Context e;
    private Rect e0;
    Window f;
    private androidx.appcompat.app.p f0;
    private i g;
    final androidx.appcompat.app.e h;
    androidx.appcompat.app.a i;
    MenuInflater j;
    private CharSequence k;
    private b1 l;
    private g m;
    private s n;
    h.b o;
    ActionBarContextView p;
    PopupWindow q;
    Runnable r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    c1 f3s;
    private boolean t;
    private boolean u;
    ViewGroup v;
    private TextView w;
    private View x;
    private boolean y;
    private boolean z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            if ((hVar.Z & 1) != 0) {
                hVar.V(0);
            }
            h hVar2 = h.this;
            if ((hVar2.Z & RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT) != 0) {
                hVar2.V(108);
            }
            h hVar3 = h.this;
            hVar3.Y = false;
            hVar3.Z = 0;
        }
    }

    class b implements a0 {
        b() {
        }

        @Override // androidx.core.view.a0
        public g1 onApplyWindowInsets(View view, g1 g1Var) {
            int iK = g1Var.k();
            int iL0 = h.this.L0(g1Var, null);
            if (iK != iL0) {
                g1Var = g1Var.p(g1Var.i(), iL0, g1Var.j(), g1Var.h());
            }
            return m0.Y(view, g1Var);
        }
    }

    class c implements ContentFrameLayout.a {
        c() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            h.this.T();
        }
    }

    class d implements Runnable {

        class a extends e1 {
            a() {
            }

            @Override // androidx.core.view.d1
            public void b(View view) {
                h.this.p.setAlpha(1.0f);
                h.this.f3s.f(null);
                h.this.f3s = null;
            }

            @Override // androidx.core.view.e1, androidx.core.view.d1
            public void c(View view) {
                h.this.p.setVisibility(0);
            }
        }

        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.q.showAtLocation(hVar.p, 55, 0, 0);
            h.this.W();
            if (!h.this.E0()) {
                h.this.p.setAlpha(1.0f);
                h.this.p.setVisibility(0);
            } else {
                h.this.p.setAlpha(0.0f);
                h hVar2 = h.this;
                hVar2.f3s = m0.c(hVar2.p).a(1.0f);
                h.this.f3s.f(new a());
            }
        }
    }

    class e extends e1 {
        e() {
        }

        @Override // androidx.core.view.d1
        public void b(View view) {
            h.this.p.setAlpha(1.0f);
            h.this.f3s.f(null);
            h.this.f3s = null;
        }

        @Override // androidx.core.view.e1, androidx.core.view.d1
        public void c(View view) {
            h.this.p.setVisibility(0);
            h.this.p.sendAccessibilityEvent(32);
            if (h.this.p.getParent() instanceof View) {
                m0.i0((View) h.this.p.getParent());
            }
        }
    }

    private class f implements androidx.appcompat.app.b {
        f() {
        }
    }

    private final class g implements androidx.appcompat.view.menu.m.a {
        g() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackG0 = h.this.g0();
            if (callbackG0 == null) {
                return true;
            }
            callbackG0.onMenuOpened(108, gVar);
            return true;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            h.this.M(gVar);
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.app.h$h, reason: collision with other inner class name */
    class C0008h implements h.b.a {
        private h.b.a a;

        /* JADX INFO: renamed from: androidx.appcompat.app.h$h$a */
        class a extends e1 {
            a() {
            }

            @Override // androidx.core.view.d1
            public void b(View view) {
                h.this.p.setVisibility(8);
                h hVar = h.this;
                PopupWindow popupWindow = hVar.q;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (hVar.p.getParent() instanceof View) {
                    m0.i0((View) h.this.p.getParent());
                }
                h.this.p.removeAllViews();
                h.this.f3s.f(null);
                h hVar2 = h.this;
                hVar2.f3s = null;
                m0.i0(hVar2.v);
            }
        }

        public C0008h(h.b.a aVar) {
            this.a = aVar;
        }

        public boolean a(h.b bVar, Menu menu) {
            return this.a.a(bVar, menu);
        }

        public boolean b(h.b bVar, Menu menu) {
            m0.i0(h.this.v);
            return this.a.b(bVar, menu);
        }

        public boolean c(h.b bVar, MenuItem menuItem) {
            return this.a.c(bVar, menuItem);
        }

        public void d(h.b bVar) {
            this.a.d(bVar);
            h hVar = h.this;
            if (hVar.q != null) {
                hVar.f.getDecorView().removeCallbacks(h.this.r);
            }
            h hVar2 = h.this;
            if (hVar2.p != null) {
                hVar2.W();
                h hVar3 = h.this;
                hVar3.f3s = m0.c(hVar3.p).a(0.0f);
                h.this.f3s.f(new a());
            }
            h hVar4 = h.this;
            androidx.appcompat.app.e eVar = hVar4.h;
            if (eVar != null) {
                eVar.onSupportActionModeFinished(hVar4.o);
            }
            h hVar5 = h.this;
            hVar5.o = null;
            m0.i0(hVar5.v);
        }
    }

    private class j extends k {
        private final PowerManager c;

        j(Context context) {
            super();
            this.c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.h.k
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.k
        public int c() {
            return this.c.isPowerSaveMode() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.h.k
        public void d() {
            h.this.G();
        }
    }

    abstract class k {
        private BroadcastReceiver a;

        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                k.this.d();
            }
        }

        k() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.a;
            if (broadcastReceiver != null) {
                try {
                    h.this.e.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.a = null;
            }
        }

        abstract IntentFilter b();

        abstract int c();

        abstract void d();

        void e() {
            a();
            IntentFilter intentFilterB = b();
            if (intentFilterB == null || intentFilterB.countActions() == 0) {
                return;
            }
            if (this.a == null) {
                this.a = new a();
            }
            h.this.e.registerReceiver(this.a, intentFilterB);
        }
    }

    private class l extends k {
        private final u c;

        l(u uVar) {
            super();
            this.c = uVar;
        }

        @Override // androidx.appcompat.app.h.k
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.k
        public int c() {
            return this.c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.h.k
        public void d() {
            h.this.G();
        }
    }

    static class m {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i = configuration.densityDpi;
            int i2 = configuration2.densityDpi;
            if (i != i2) {
                configuration3.densityDpi = i2;
            }
        }
    }

    static class n {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }
    }

    static class o {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            if ((configuration.colorMode & 3) != (configuration2.colorMode & 3)) {
                configuration3.colorMode |= configuration2.colorMode & 3;
            }
            if ((configuration.colorMode & 12) != (configuration2.colorMode & 12)) {
                configuration3.colorMode |= configuration2.colorMode & 12;
            }
        }
    }

    private static class p {
        static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    private class q extends ContentFrameLayout {
        public q(Context context) {
            super(context);
        }

        private boolean b(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return h.this.U(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !b((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            h.this.O(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(f.b.d(getContext(), i));
        }
    }

    protected static final class r {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        androidx.appcompat.view.menu.g j;
        androidx.appcompat.view.menu.e k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        Bundle f4s;

        r(int i) {
            this.a = i;
        }

        androidx.appcompat.view.menu.n a(androidx.appcompat.view.menu.m.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                androidx.appcompat.view.menu.e eVar = new androidx.appcompat.view.menu.e(this.l, R$layout.abc_list_menu_item_layout);
                this.k = eVar;
                eVar.setCallback(aVar);
                this.j.addMenuPresenter(this.k);
            }
            return this.k.b(this.g);
        }

        public boolean b() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.a().getCount() > 0;
        }

        void c(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.e eVar;
            androidx.appcompat.view.menu.g gVar2 = this.j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.removeMenuPresenter(this.k);
            }
            this.j = gVar;
            if (gVar == null || (eVar = this.k) == null) {
                return;
            }
            gVar.addMenuPresenter(eVar);
        }

        void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                themeNewTheme.applyStyle(i, true);
            }
            themeNewTheme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            } else {
                themeNewTheme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            h.d dVar = new h.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(R$styleable.o);
            this.b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private final class s implements androidx.appcompat.view.menu.m.a {
        s() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackG0;
            if (gVar != gVar.getRootMenu()) {
                return true;
            }
            h hVar = h.this;
            if (!hVar.A || (callbackG0 = hVar.g0()) == null || h.this.M) {
                return true;
            }
            callbackG0.onMenuOpened(108, gVar);
            return true;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            w.a rootMenu = gVar.getRootMenu();
            boolean z2 = rootMenu != gVar;
            h hVar = h.this;
            w.a aVar = gVar;
            if (z2) {
                aVar = rootMenu;
            }
            r rVarZ = hVar.Z(aVar);
            if (rVarZ != null) {
                if (!z2) {
                    h.this.P(rVarZ, z);
                } else {
                    h.this.L(rVarZ.a, rVarZ, rootMenu);
                    h.this.P(rVarZ, true);
                }
            }
        }
    }

    h(Activity activity, androidx.appcompat.app.e eVar) {
        this(activity, null, eVar, activity);
    }

    private boolean A0(r rVar, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((rVar.m || B0(rVar, keyEvent)) && (gVar = rVar.j) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.l == null) {
            P(rVar, true);
        }
        return zPerformShortcut;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private boolean B0(r rVar, KeyEvent keyEvent) {
        b1 b1Var;
        b1 b1Var2;
        b1 b1Var3;
        if (this.M) {
            return false;
        }
        if (rVar.m) {
            return true;
        }
        r rVar2 = this.H;
        if (rVar2 != null && rVar2 != rVar) {
            P(rVar2, false);
        }
        Window.Callback callbackG0 = g0();
        if (callbackG0 != null) {
            rVar.i = callbackG0.onCreatePanelView(rVar.a);
        }
        int i2 = rVar.a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (b1Var3 = this.l) != null) {
            b1Var3.f();
        }
        if (rVar.i == null && (!z || !(z0() instanceof androidx.appcompat.app.s))) {
            androidx.appcompat.view.menu.g gVar = rVar.j;
            if (gVar == null || rVar.r) {
                if (gVar == null && (!k0(rVar) || rVar.j == null)) {
                    return false;
                }
                if (z && this.l != null) {
                    if (this.m == null) {
                        this.m = new g();
                    }
                    this.l.d(rVar.j, this.m);
                }
                rVar.j.stopDispatchingItemsChanged();
                if (!callbackG0.onCreatePanelMenu(rVar.a, rVar.j)) {
                    rVar.c(null);
                    if (z && (b1Var = this.l) != null) {
                        b1Var.d(null, this.m);
                    }
                    return false;
                }
                rVar.r = false;
            }
            rVar.j.stopDispatchingItemsChanged();
            Bundle bundle = rVar.f4s;
            if (bundle != null) {
                rVar.j.restoreActionViewStates(bundle);
                rVar.f4s = null;
            }
            if (!callbackG0.onPreparePanel(0, rVar.i, rVar.j)) {
                if (z && (b1Var2 = this.l) != null) {
                    b1Var2.d(null, this.m);
                }
                rVar.j.startDispatchingItemsChanged();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            rVar.p = z2;
            rVar.j.setQwertyMode(z2);
            rVar.j.startDispatchingItemsChanged();
        }
        rVar.m = true;
        rVar.n = false;
        this.H = rVar;
        return true;
    }

    private void C0(boolean z) {
        b1 b1Var = this.l;
        if (b1Var == null || !b1Var.a() || (ViewConfiguration.get(this.e).hasPermanentMenuKey() && !this.l.g())) {
            r rVarE0 = e0(0, true);
            rVarE0.q = true;
            P(rVarE0, false);
            y0(rVarE0, null);
            return;
        }
        Window.Callback callbackG0 = g0();
        if (this.l.e() && z) {
            this.l.b();
            if (this.M) {
                return;
            }
            callbackG0.onPanelClosed(108, e0(0, true).j);
            return;
        }
        if (callbackG0 == null || this.M) {
            return;
        }
        if (this.Y && (this.Z & 1) != 0) {
            this.f.getDecorView().removeCallbacks(this.b0);
            this.b0.run();
        }
        r rVarE1 = e0(0, true);
        w.a aVar = rVarE1.j;
        if (aVar == null || rVarE1.r || !callbackG0.onPreparePanel(0, rVarE1.i, aVar)) {
            return;
        }
        callbackG0.onMenuOpened(108, rVarE1.j);
        this.l.c();
    }

    private int D0(int i2) {
        if (i2 == 8) {
            return 108;
        }
        if (i2 == 9) {
            return 109;
        }
        return i2;
    }

    private boolean F0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || m0.Q((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private boolean H(boolean z) {
        if (this.M) {
            return false;
        }
        int iK = K();
        boolean zJ0 = J0(o0(this.e, iK), z);
        if (iK == 0) {
            d0(this.e).e();
        } else {
            k kVar = this.W;
            if (kVar != null) {
                kVar.a();
            }
        }
        if (iK == 3) {
            c0(this.e).e();
        } else {
            k kVar2 = this.X;
            if (kVar2 != null) {
                kVar2.a();
            }
        }
        return zJ0;
    }

    private void H0() {
        if (this.u) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private void I() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.v.findViewById(R.id.content);
        View decorView = this.f.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.e.obtainStyledAttributes(R$styleable.o);
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i2 = R$styleable.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = R$styleable.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = R$styleable.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            typedArrayObtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = R$styleable.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            typedArrayObtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private androidx.appcompat.app.d I0() {
        for (Object baseContext = this.e; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof androidx.appcompat.app.d) {
                return (androidx.appcompat.app.d) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.Window$Callback, androidx.appcompat.app.h$i] */
    private void J(Window window) {
        if (this.f != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof i) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        ?? iVar = new i(callback);
        this.g = iVar;
        window.setCallback(iVar);
        h2 h2VarU = h2.u(this.e, null, i0);
        Drawable drawableH = h2VarU.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        h2VarU.w();
        this.f = window;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0047  */
    private boolean J0(int i2, boolean z) {
        boolean z2;
        Configuration configurationQ = Q(this.e, i2, null);
        boolean zM0 = m0();
        int i3 = this.e.getResources().getConfiguration().uiMode & 48;
        int i4 = configurationQ.uiMode & 48;
        boolean z3 = true;
        if (i3 != i4 && z && !zM0 && this.J && (j0 || this.K)) {
            Object obj = this.d;
            if (!(obj instanceof Activity) || ((Activity) obj).isChild()) {
                z2 = false;
            } else {
                s.c.d((Activity) this.d);
                z2 = true;
            }
        } else {
            z2 = false;
        }
        if (z2 || i3 == i4) {
            z3 = z2;
        } else {
            K0(i4, zM0, null);
        }
        if (z3) {
            Object obj2 = this.d;
            if (obj2 instanceof androidx.appcompat.app.d) {
                ((androidx.appcompat.app.d) obj2).onNightModeChanged(i2);
            }
        }
        return z3;
    }

    private int K() {
        int i2 = this.N;
        return i2 != -100 ? i2 : androidx.appcompat.app.f.h();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void K0(int i2, boolean z, Configuration configuration) {
        Resources resources = this.e.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            androidx.appcompat.app.q.a(resources);
        }
        int i4 = this.Q;
        if (i4 != 0) {
            this.e.setTheme(i4);
            if (i3 >= 23) {
                this.e.getTheme().applyStyle(this.Q, true);
            }
        }
        if (z) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.m) {
                    if (((androidx.lifecycle.m) activity).getLifecycle().b().b(androidx.lifecycle.f.b.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.L) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    private void M0(View view) {
        view.setBackgroundColor((m0.K(view) & 8192) != 0 ? androidx.core.content.a.getColor(this.e, R$color.abc_decor_view_status_guard_light) : androidx.core.content.a.getColor(this.e, R$color.abc_decor_view_status_guard));
    }

    private void N() {
        k kVar = this.W;
        if (kVar != null) {
            kVar.a();
        }
        k kVar2 = this.X;
        if (kVar2 != null) {
            kVar2.a();
        }
    }

    private Configuration Q(Context context, int i2, Configuration configuration) {
        int i3;
        if (i2 != 1) {
            i3 = i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32;
        } else {
            i3 = 16;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    private ViewGroup R() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.e.obtainStyledAttributes(R$styleable.o);
        int i2 = R$styleable.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            y(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            y(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            y(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            y(10);
        }
        this.D = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        Y();
        this.f.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.e);
        if (this.E) {
            viewGroup = this.C ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.D) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.B = false;
            this.A = false;
        } else if (this.A) {
            TypedValue typedValue = new TypedValue();
            this.e.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new h.d(this.e, typedValue.resourceId) : this.e).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            b1 b1Var = (b1) viewGroup.findViewById(R$id.decor_content_parent);
            this.l = b1Var;
            b1Var.setWindowCallback(g0());
            if (this.B) {
                this.l.h(109);
            }
            if (this.y) {
                this.l.h(2);
            }
            if (this.z) {
                this.l.h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.A + ", windowActionBarOverlay: " + this.B + ", android:windowIsFloating: " + this.D + ", windowActionModeOverlay: " + this.C + ", windowNoTitle: " + this.E + " }");
        }
        m0.y0(viewGroup, new b());
        if (this.l == null) {
            this.w = (TextView) viewGroup.findViewById(R$id.title);
        }
        o2.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new c());
        return viewGroup;
    }

    private void X() {
        if (this.u) {
            return;
        }
        this.v = R();
        CharSequence charSequenceF0 = f0();
        if (!TextUtils.isEmpty(charSequenceF0)) {
            b1 b1Var = this.l;
            if (b1Var != null) {
                b1Var.setWindowTitle(charSequenceF0);
            } else if (z0() != null) {
                z0().x(charSequenceF0);
            } else {
                TextView textView = this.w;
                if (textView != null) {
                    textView.setText(charSequenceF0);
                }
            }
        }
        I();
        x0(this.v);
        this.u = true;
        r rVarE0 = e0(0, false);
        if (this.M) {
            return;
        }
        if (rVarE0 == null || rVarE0.j == null) {
            l0(108);
        }
    }

    private void Y() {
        if (this.f == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                J(((Activity) obj).getWindow());
            }
        }
        if (this.f == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private static Configuration a0(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f2 = configuration.fontScale;
            float f3 = configuration2.fontScale;
            if (f2 != f3) {
                configuration3.fontScale = f3;
            }
            int i2 = configuration.mcc;
            int i3 = configuration2.mcc;
            if (i2 != i3) {
                configuration3.mcc = i3;
            }
            int i4 = configuration.mnc;
            int i5 = configuration2.mnc;
            if (i4 != i5) {
                configuration3.mnc = i5;
            }
            int i6 = Build.VERSION.SDK_INT;
            if (i6 >= 24) {
                n.a(configuration, configuration2, configuration3);
            } else if (!c0.c.a(configuration.locale, configuration2.locale)) {
                configuration3.locale = configuration2.locale;
            }
            int i7 = configuration.touchscreen;
            int i8 = configuration2.touchscreen;
            if (i7 != i8) {
                configuration3.touchscreen = i8;
            }
            int i9 = configuration.keyboard;
            int i10 = configuration2.keyboard;
            if (i9 != i10) {
                configuration3.keyboard = i10;
            }
            int i11 = configuration.keyboardHidden;
            int i12 = configuration2.keyboardHidden;
            if (i11 != i12) {
                configuration3.keyboardHidden = i12;
            }
            int i13 = configuration.navigation;
            int i14 = configuration2.navigation;
            if (i13 != i14) {
                configuration3.navigation = i14;
            }
            int i15 = configuration.navigationHidden;
            int i16 = configuration2.navigationHidden;
            if (i15 != i16) {
                configuration3.navigationHidden = i16;
            }
            int i17 = configuration.orientation;
            int i18 = configuration2.orientation;
            if (i17 != i18) {
                configuration3.orientation = i18;
            }
            int i19 = configuration.screenLayout & 15;
            int i20 = configuration2.screenLayout;
            if (i19 != (i20 & 15)) {
                configuration3.screenLayout |= i20 & 15;
            }
            int i21 = configuration.screenLayout & 192;
            int i22 = configuration2.screenLayout;
            if (i21 != (i22 & 192)) {
                configuration3.screenLayout |= i22 & 192;
            }
            int i23 = configuration.screenLayout & 48;
            int i24 = configuration2.screenLayout;
            if (i23 != (i24 & 48)) {
                configuration3.screenLayout |= i24 & 48;
            }
            int i25 = configuration.screenLayout & 768;
            int i26 = configuration2.screenLayout;
            if (i25 != (i26 & 768)) {
                configuration3.screenLayout |= i26 & 768;
            }
            if (i6 >= 26) {
                o.a(configuration, configuration2, configuration3);
            }
            int i27 = configuration.uiMode & 15;
            int i28 = configuration2.uiMode;
            if (i27 != (i28 & 15)) {
                configuration3.uiMode |= i28 & 15;
            }
            int i29 = configuration.uiMode & 48;
            int i30 = configuration2.uiMode;
            if (i29 != (i30 & 48)) {
                configuration3.uiMode |= i30 & 48;
            }
            int i31 = configuration.screenWidthDp;
            int i32 = configuration2.screenWidthDp;
            if (i31 != i32) {
                configuration3.screenWidthDp = i32;
            }
            int i33 = configuration.screenHeightDp;
            int i34 = configuration2.screenHeightDp;
            if (i33 != i34) {
                configuration3.screenHeightDp = i34;
            }
            int i35 = configuration.smallestScreenWidthDp;
            int i36 = configuration2.smallestScreenWidthDp;
            if (i35 != i36) {
                configuration3.smallestScreenWidthDp = i36;
            }
            m.a(configuration, configuration2, configuration3);
        }
        return configuration3;
    }

    private k c0(Context context) {
        if (this.X == null) {
            this.X = new j(context);
        }
        return this.X;
    }

    private k d0(Context context) {
        if (this.W == null) {
            this.W = new l(u.a(context));
        }
        return this.W;
    }

    private void h0() {
        X();
        if (this.A && this.i == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                this.i = new v((Activity) this.d, this.B);
            } else if (obj instanceof Dialog) {
                this.i = new v((Dialog) this.d);
            }
            androidx.appcompat.app.a aVar = this.i;
            if (aVar != null) {
                aVar.r(this.c0);
            }
        }
    }

    private boolean i0(r rVar) {
        View view = rVar.i;
        if (view != null) {
            rVar.h = view;
            return true;
        }
        if (rVar.j == null) {
            return false;
        }
        if (this.n == null) {
            this.n = new s();
        }
        View view2 = (View) rVar.a(this.n);
        rVar.h = view2;
        return view2 != null;
    }

    private boolean j0(r rVar) {
        rVar.d(b0());
        rVar.g = new q(rVar.l);
        rVar.c = 81;
        return true;
    }

    private boolean k0(r rVar) {
        Resources.Theme themeNewTheme;
        Context context = this.e;
        int i2 = rVar.a;
        if ((i2 == 0 || i2 == 108) && this.l != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
                themeNewTheme = null;
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                Context dVar = new h.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
        gVar.setCallback(this);
        rVar.c(gVar);
        return true;
    }

    private void l0(int i2) {
        this.Z = (1 << i2) | this.Z;
        if (this.Y) {
            return;
        }
        m0.d0(this.f.getDecorView(), this.b0);
        this.Y = true;
    }

    private boolean m0() {
        int i2;
        if (!this.V && (this.d instanceof Activity)) {
            PackageManager packageManager = this.e.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 29) {
                    i2 = 269221888;
                } else {
                    i2 = i3 >= 24 ? 786432 : 0;
                }
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.e, this.d.getClass()), i2);
                this.S = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException unused) {
                this.S = false;
            }
        }
        this.V = true;
        return this.S;
    }

    private boolean r0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        r rVarE0 = e0(i2, true);
        if (rVarE0.o) {
            return false;
        }
        return B0(rVarE0, keyEvent);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    private boolean u0(int i2, KeyEvent keyEvent) {
        boolean zB0;
        AudioManager audioManager;
        b1 b1Var;
        if (this.o != null) {
            return false;
        }
        boolean zB = true;
        r rVarE0 = e0(i2, true);
        if (i2 != 0 || (b1Var = this.l) == null || !b1Var.a() || ViewConfiguration.get(this.e).hasPermanentMenuKey()) {
            boolean z = rVarE0.o;
            if (z || rVarE0.n) {
                P(rVarE0, true);
                zB = z;
            } else if (rVarE0.m) {
                if (rVarE0.r) {
                    rVarE0.m = false;
                    zB0 = B0(rVarE0, keyEvent);
                } else {
                    zB0 = true;
                }
                if (zB0) {
                    y0(rVarE0, keyEvent);
                } else {
                    zB = false;
                }
            } else {
                zB = false;
            }
        } else if (this.l.e()) {
            zB = this.l.b();
        } else if (this.M || !B0(rVarE0, keyEvent)) {
            zB = false;
        } else {
            zB = this.l.c();
        }
        if (zB && (audioManager = (AudioManager) this.e.getApplicationContext().getSystemService("audio")) != null) {
            audioManager.playSoundEffect(0);
        }
        return zB;
    }

    private void y0(r rVar, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (rVar.o || this.M) {
            return;
        }
        if (rVar.a == 0) {
            if ((this.e.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackG0 = g0();
        if (callbackG0 != null && !callbackG0.onMenuOpened(rVar.a, rVar.j)) {
            P(rVar, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
        if (windowManager != null && B0(rVar, keyEvent)) {
            ViewGroup viewGroup = rVar.g;
            if (viewGroup != null && !rVar.q) {
                View view = rVar.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                rVar.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, rVar.d, rVar.e, 1002, 8519680, -3);
                layoutParams2.gravity = rVar.c;
                layoutParams2.windowAnimations = rVar.f;
                windowManager.addView(rVar.g, layoutParams2);
                rVar.o = true;
            }
            if (viewGroup == null) {
                if (!j0(rVar) || rVar.g == null) {
                    return;
                }
            } else if (rVar.q && viewGroup.getChildCount() > 0) {
                rVar.g.removeAllViews();
            }
            if (!i0(rVar) || !rVar.b()) {
                rVar.q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = rVar.h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            rVar.g.setBackgroundResource(rVar.b);
            ViewParent parent = rVar.h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(rVar.h);
            }
            rVar.g.addView(rVar.h, layoutParams3);
            if (!rVar.h.hasFocus()) {
                rVar.h.requestFocus();
            }
            i2 = -2;
            rVar.n = false;
            WindowManager.LayoutParams layoutParams4 = new WindowManager.LayoutParams(i2, -2, rVar.d, rVar.e, 1002, 8519680, -3);
            layoutParams4.gravity = rVar.c;
            layoutParams4.windowAnimations = rVar.f;
            windowManager.addView(rVar.g, layoutParams4);
            rVar.o = true;
        }
    }

    @Override // androidx.appcompat.app.f
    public void A(View view) {
        X();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.g.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.f
    public void B(View view, ViewGroup.LayoutParams layoutParams) {
        X();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.f
    public void C(Toolbar toolbar) {
        if (this.d instanceof Activity) {
            androidx.appcompat.app.a aVarL = l();
            if (aVarL instanceof v) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.j = null;
            if (aVarL != null) {
                aVarL.n();
            }
            if (toolbar != null) {
                androidx.appcompat.app.s sVar = new androidx.appcompat.app.s(toolbar, f0(), this.g);
                this.i = sVar;
                this.f.setCallback(sVar.A());
            } else {
                this.i = null;
                this.f.setCallback(this.g);
            }
            n();
        }
    }

    @Override // androidx.appcompat.app.f
    public void D(int i2) {
        this.Q = i2;
    }

    @Override // androidx.appcompat.app.f
    public final void E(CharSequence charSequence) {
        this.k = charSequence;
        b1 b1Var = this.l;
        if (b1Var != null) {
            b1Var.setWindowTitle(charSequence);
            return;
        }
        if (z0() != null) {
            z0().x(charSequence);
            return;
        }
        TextView textView = this.w;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    final boolean E0() {
        ViewGroup viewGroup;
        return this.u && (viewGroup = this.v) != null && m0.R(viewGroup);
    }

    @Override // androidx.appcompat.app.f
    public h.b F(h.b.a aVar) {
        androidx.appcompat.app.e eVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        h.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
        }
        C0008h c0008h = new C0008h(aVar);
        androidx.appcompat.app.a aVarL = l();
        if (aVarL != null) {
            h.b bVarY = aVarL.y(c0008h);
            this.o = bVarY;
            if (bVarY != null && (eVar = this.h) != null) {
                eVar.onSupportActionModeStarted(bVarY);
            }
        }
        if (this.o == null) {
            this.o = G0(c0008h);
        }
        return this.o;
    }

    public boolean G() {
        return H(true);
    }

    h.b G0(h.b.a aVar) {
        h.b bVarOnWindowStartingSupportActionMode;
        h.d dVar;
        androidx.appcompat.app.e eVar;
        W();
        h.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
        }
        if (!(aVar instanceof C0008h)) {
            aVar = new C0008h(aVar);
        }
        androidx.appcompat.app.e eVar2 = this.h;
        if (eVar2 == null || this.M) {
            bVarOnWindowStartingSupportActionMode = null;
        } else {
            try {
                bVarOnWindowStartingSupportActionMode = eVar2.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError unused) {
                bVarOnWindowStartingSupportActionMode = null;
            }
        }
        if (bVarOnWindowStartingSupportActionMode != null) {
            this.o = bVarOnWindowStartingSupportActionMode;
        } else {
            if (this.p == null) {
                if (this.D) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.e.getTheme();
                    theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.e.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new h.d(this.e, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.e;
                    }
                    this.p = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow((Context) dVar, (AttributeSet) null, R$attr.actionModePopupWindowStyle);
                    this.q = popupWindow;
                    androidx.core.widget.k.b(popupWindow, 2);
                    this.q.setContentView(this.p);
                    this.q.setWidth(-1);
                    dVar.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                    this.p.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.q.setHeight(-2);
                    this.r = new d();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.v.findViewById(R$id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(b0()));
                        this.p = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.p != null) {
                W();
                this.p.k();
                h.b eVar3 = new h.e(this.p.getContext(), this.p, aVar, this.q == null);
                if (aVar.a(eVar3, eVar3.c())) {
                    eVar3.i();
                    this.p.h(eVar3);
                    this.o = eVar3;
                    if (E0()) {
                        this.p.setAlpha(0.0f);
                        c1 c1VarA = m0.c(this.p).a(1.0f);
                        this.f3s = c1VarA;
                        c1VarA.f(new e());
                    } else {
                        this.p.setAlpha(1.0f);
                        this.p.setVisibility(0);
                        this.p.sendAccessibilityEvent(32);
                        if (this.p.getParent() instanceof View) {
                            m0.i0((View) this.p.getParent());
                        }
                    }
                    if (this.q != null) {
                        this.f.getDecorView().post(this.r);
                    }
                } else {
                    this.o = null;
                }
            }
        }
        h.b bVar2 = this.o;
        if (bVar2 != null && (eVar = this.h) != null) {
            eVar.onSupportActionModeStarted(bVar2);
        }
        return this.o;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [android.view.Window$Callback] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.view.Menu] */
    /* JADX WARN: Type inference failed for: r5v1, types: [android.view.Menu] */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.appcompat.view.menu.g] */
    void L(int i2, r rVar, Menu menu) {
        if (menu == 0) {
            if (rVar == null && i2 >= 0) {
                r[] rVarArr = this.G;
                if (i2 < rVarArr.length) {
                    rVar = rVarArr[i2];
                }
            }
            if (rVar != null) {
                menu = rVar.j;
            }
        }
        if ((rVar == null || rVar.o) && !this.M) {
            this.g.a().onPanelClosed(i2, menu);
        }
    }

    final int L0(g1 g1Var, Rect rect) {
        int iK;
        boolean z;
        boolean z2;
        if (g1Var != null) {
            iK = g1Var.k();
        } else {
            iK = rect != null ? rect.top : 0;
        }
        ActionBarContextView actionBarContextView = this.p;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.p.getLayoutParams();
            boolean z3 = true;
            if (this.p.isShown()) {
                if (this.d0 == null) {
                    this.d0 = new Rect();
                    this.e0 = new Rect();
                }
                Rect rect2 = this.d0;
                Rect rect3 = this.e0;
                if (g1Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(g1Var.i(), g1Var.k(), g1Var.j(), g1Var.h());
                }
                o2.a(this.v, rect2, rect3);
                int i2 = rect2.top;
                int i3 = rect2.left;
                int i4 = rect2.right;
                g1 g1VarH = m0.H(this.v);
                int i5 = g1VarH == null ? 0 : g1VarH.i();
                int iJ = g1VarH == null ? 0 : g1VarH.j();
                if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i2;
                    marginLayoutParams.leftMargin = i3;
                    marginLayoutParams.rightMargin = i4;
                    z2 = true;
                }
                if (i2 <= 0 || this.x != null) {
                    View view = this.x;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i6 = marginLayoutParams2.height;
                        int i7 = marginLayoutParams.topMargin;
                        if (i6 != i7 || marginLayoutParams2.leftMargin != i5 || marginLayoutParams2.rightMargin != iJ) {
                            marginLayoutParams2.height = i7;
                            marginLayoutParams2.leftMargin = i5;
                            marginLayoutParams2.rightMargin = iJ;
                            this.x.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.e);
                    this.x = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = i5;
                    layoutParams.rightMargin = iJ;
                    this.v.addView(this.x, -1, layoutParams);
                }
                View view3 = this.x;
                z3 = view3 != null;
                if (z3 && view3.getVisibility() != 0) {
                    M0(this.x);
                }
                if (!this.C && z3) {
                    iK = 0;
                }
                z = z3;
                z3 = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                z3 = false;
            }
            if (z3) {
                this.p.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.x;
        if (view4 != null) {
            view4.setVisibility(z ? 0 : 8);
        }
        return iK;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void M(androidx.appcompat.view.menu.g gVar) {
        if (this.F) {
            return;
        }
        this.F = true;
        this.l.i();
        Window.Callback callbackG0 = g0();
        if (callbackG0 != null && !this.M) {
            callbackG0.onPanelClosed(108, gVar);
        }
        this.F = false;
    }

    void O(int i2) {
        P(e0(i2, true), true);
    }

    void P(r rVar, boolean z) {
        ViewGroup viewGroup;
        b1 b1Var;
        if (z && rVar.a == 0 && (b1Var = this.l) != null && b1Var.e()) {
            M(rVar.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
        if (windowManager != null && rVar.o && (viewGroup = rVar.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                L(rVar.a, rVar, null);
            }
        }
        rVar.m = false;
        rVar.n = false;
        rVar.o = false;
        rVar.h = null;
        rVar.q = true;
        if (this.H == rVar) {
            this.H = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View S(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        boolean zF0 = false;
        if (this.f0 == null) {
            String string = this.e.obtainStyledAttributes(R$styleable.o).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.f0 = new androidx.appcompat.app.p();
            } else {
                try {
                    this.f0 = (androidx.appcompat.app.p) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to instantiate custom view inflater ");
                    sb.append(string);
                    sb.append(". Falling back to default.");
                    this.f0 = new androidx.appcompat.app.p();
                }
            }
        }
        boolean z2 = h0;
        if (z2) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zF0 = F0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zF0 = true;
            }
            z = zF0;
        } else {
            z = false;
        }
        return this.f0.createView(view, str, context, attributeSet, z, z2, true, n2.b());
    }

    void T() {
        androidx.appcompat.view.menu.g gVar;
        b1 b1Var = this.l;
        if (b1Var != null) {
            b1Var.i();
        }
        if (this.q != null) {
            this.f.getDecorView().removeCallbacks(this.r);
            if (this.q.isShowing()) {
                try {
                    this.q.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.q = null;
        }
        W();
        r rVarE0 = e0(0, false);
        if (rVarE0 == null || (gVar = rVarE0.j) == null) {
            return;
        }
        gVar.close();
    }

    boolean U(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.d;
        if (((obj instanceof androidx.core.view.k.a) || (obj instanceof androidx.appcompat.app.n)) && (decorView = this.f.getDecorView()) != null && androidx.core.view.k.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.g.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? q0(keyCode, keyEvent) : t0(keyCode, keyEvent);
    }

    void V(int i2) {
        r rVarE0;
        r rVarE1 = e0(i2, true);
        if (rVarE1.j != null) {
            Bundle bundle = new Bundle();
            rVarE1.j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                rVarE1.f4s = bundle;
            }
            rVarE1.j.stopDispatchingItemsChanged();
            rVarE1.j.clear();
        }
        rVarE1.r = true;
        rVarE1.q = true;
        if ((i2 != 108 && i2 != 0) || this.l == null || (rVarE0 = e0(0, false)) == null) {
            return;
        }
        rVarE0.m = false;
        B0(rVarE0, null);
    }

    void W() {
        c1 c1Var = this.f3s;
        if (c1Var != null) {
            c1Var.b();
        }
    }

    r Z(Menu menu) {
        r[] rVarArr = this.G;
        int length = rVarArr != null ? rVarArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            r rVar = rVarArr[i2];
            if (rVar != null && rVar.j == menu) {
                return rVar;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.app.f
    public void b(View view, ViewGroup.LayoutParams layoutParams) {
        X();
        ((ViewGroup) this.v.findViewById(R.id.content)).addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    final Context b0() {
        androidx.appcompat.app.a aVarL = l();
        Context contextK = aVarL != null ? aVarL.k() : null;
        return contextK == null ? this.e : contextK;
    }

    @Override // androidx.appcompat.app.f
    public Context d(Context context) {
        this.J = true;
        int iO0 = o0(context, K());
        if (k0 && (context instanceof ContextThemeWrapper)) {
            try {
                p.a((ContextThemeWrapper) context, Q(context, iO0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof h.d) {
            try {
                ((h.d) context).a(Q(context, iO0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!j0) {
            return super.d(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationQ = Q(context, iO0, configuration.equals(configuration2) ? null : a0(configuration, configuration2));
            h.d dVar = new h.d(context, R$style.Theme_AppCompat_Empty);
            dVar.a(configurationQ);
            boolean z = false;
            try {
                z = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z) {
                t.g.d.a(dVar.getTheme());
            }
            return super.d(dVar);
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("Application failed to obtain resources from itself", e2);
        }
    }

    protected r e0(int i2, boolean z) {
        r[] rVarArr = this.G;
        if (rVarArr == null || rVarArr.length <= i2) {
            r[] rVarArr2 = new r[i2 + 1];
            if (rVarArr != null) {
                System.arraycopy(rVarArr, 0, rVarArr2, 0, rVarArr.length);
            }
            this.G = rVarArr2;
            rVarArr = rVarArr2;
        }
        r rVar = rVarArr[i2];
        if (rVar != null) {
            return rVar;
        }
        r rVar2 = new r(i2);
        rVarArr[i2] = rVar2;
        return rVar2;
    }

    final CharSequence f0() {
        Object obj = this.d;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.k;
    }

    @Override // androidx.appcompat.app.f
    public View g(int i2) {
        X();
        return this.f.findViewById(i2);
    }

    final Window.Callback g0() {
        return this.f.getCallback();
    }

    @Override // androidx.appcompat.app.f
    public final androidx.appcompat.app.b i() {
        return new f();
    }

    @Override // androidx.appcompat.app.f
    public int j() {
        return this.N;
    }

    @Override // androidx.appcompat.app.f
    public MenuInflater k() {
        if (this.j == null) {
            h0();
            androidx.appcompat.app.a aVar = this.i;
            this.j = new h.g(aVar != null ? aVar.k() : this.e);
        }
        return this.j;
    }

    @Override // androidx.appcompat.app.f
    public androidx.appcompat.app.a l() {
        h0();
        return this.i;
    }

    @Override // androidx.appcompat.app.f
    public void m() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.e);
        if (layoutInflaterFrom.getFactory() == null) {
            androidx.core.view.l.a(layoutInflaterFrom, this);
        } else {
            boolean z = layoutInflaterFrom.getFactory2() instanceof h;
        }
    }

    @Override // androidx.appcompat.app.f
    public void n() {
        androidx.appcompat.app.a aVarL = l();
        if (aVarL == null || !aVarL.l()) {
            l0(0);
        }
    }

    public boolean n0() {
        return this.t;
    }

    @Override // androidx.appcompat.app.f
    public void o(Configuration configuration) {
        androidx.appcompat.app.a aVarL;
        if (this.A && this.u && (aVarL = l()) != null) {
            aVarL.m(configuration);
        }
        androidx.appcompat.widget.j.b().g(this.e);
        H(false);
    }

    int o0(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return d0(context).c();
                }
                return -1;
            }
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    return c0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i2;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return S(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        r rVarZ;
        Window.Callback callbackG0 = g0();
        if (callbackG0 == null || this.M || (rVarZ = Z(gVar.getRootMenu())) == null) {
            return false;
        }
        return callbackG0.onMenuItemSelected(rVarZ.a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
        C0(true);
    }

    @Override // androidx.appcompat.app.f
    public void p(Bundle bundle) {
        String strC;
        this.J = true;
        H(false);
        Y();
        Object obj = this.d;
        if (obj instanceof Activity) {
            try {
                strC = s.l.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
                strC = null;
            }
            if (strC != null) {
                androidx.appcompat.app.a aVarZ0 = z0();
                if (aVarZ0 == null) {
                    this.c0 = true;
                } else {
                    aVarZ0.r(true);
                }
            }
            androidx.appcompat.app.f.a(this);
        }
        this.K = true;
    }

    boolean p0() {
        h.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
            return true;
        }
        androidx.appcompat.app.a aVarL = l();
        return aVarL != null && aVarL.h();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0048  */
    @Override // androidx.appcompat.app.f
    public void q() {
        if (this.d instanceof Activity) {
            androidx.appcompat.app.f.w(this);
        }
        if (this.Y) {
            this.f.getDecorView().removeCallbacks(this.b0);
        }
        this.L = false;
        this.M = true;
        if (this.N != -100) {
            Object obj = this.d;
            if ((obj instanceof Activity) && ((Activity) obj).isChangingConfigurations()) {
                g0.put(this.d.getClass().getName(), Integer.valueOf(this.N));
            } else {
                g0.remove(this.d.getClass().getName());
            }
        } else {
            g0.remove(this.d.getClass().getName());
        }
        androidx.appcompat.app.a aVar = this.i;
        if (aVar != null) {
            aVar.n();
        }
        N();
    }

    boolean q0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.I = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            r0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void r(Bundle bundle) {
        X();
    }

    @Override // androidx.appcompat.app.f
    public void s() {
        androidx.appcompat.app.a aVarL = l();
        if (aVarL != null) {
            aVarL.u(true);
        }
    }

    boolean s0(int i2, KeyEvent keyEvent) {
        androidx.appcompat.app.a aVarL = l();
        if (aVarL != null && aVarL.o(i2, keyEvent)) {
            return true;
        }
        r rVar = this.H;
        if (rVar != null && A0(rVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            r rVar2 = this.H;
            if (rVar2 != null) {
                rVar2.n = true;
            }
            return true;
        }
        if (this.H == null) {
            r rVarE0 = e0(0, true);
            B0(rVarE0, keyEvent);
            boolean zA0 = A0(rVarE0, keyEvent.getKeyCode(), keyEvent, 1);
            rVarE0.m = false;
            if (zA0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void t(Bundle bundle) {
    }

    boolean t0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.I;
            this.I = false;
            r rVarE0 = e0(0, false);
            if (rVarE0 != null && rVarE0.o) {
                if (!z) {
                    P(rVarE0, true);
                }
                return true;
            }
            if (p0()) {
                return true;
            }
        } else if (i2 == 82) {
            u0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void u() {
        this.L = true;
        G();
    }

    @Override // androidx.appcompat.app.f
    public void v() {
        this.L = false;
        androidx.appcompat.app.a aVarL = l();
        if (aVarL != null) {
            aVarL.u(false);
        }
    }

    void v0(int i2) {
        androidx.appcompat.app.a aVarL;
        if (i2 != 108 || (aVarL = l()) == null) {
            return;
        }
        aVarL.i(true);
    }

    void w0(int i2) {
        if (i2 == 108) {
            androidx.appcompat.app.a aVarL = l();
            if (aVarL != null) {
                aVarL.i(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            r rVarE0 = e0(i2, true);
            if (rVarE0.o) {
                P(rVarE0, false);
            }
        }
    }

    void x0(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.f
    public boolean y(int i2) {
        int iD0 = D0(i2);
        if (this.E && iD0 == 108) {
            return false;
        }
        if (this.A && iD0 == 1) {
            this.A = false;
        }
        if (iD0 == 1) {
            H0();
            this.E = true;
            return true;
        }
        if (iD0 == 2) {
            H0();
            this.y = true;
            return true;
        }
        if (iD0 == 5) {
            H0();
            this.z = true;
            return true;
        }
        if (iD0 == 10) {
            H0();
            this.C = true;
            return true;
        }
        if (iD0 == 108) {
            H0();
            this.A = true;
            return true;
        }
        if (iD0 != 109) {
            return this.f.requestFeature(iD0);
        }
        H0();
        this.B = true;
        return true;
    }

    @Override // androidx.appcompat.app.f
    public void z(int i2) {
        X();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.e).inflate(i2, viewGroup);
        this.g.a().onContentChanged();
    }

    final androidx.appcompat.app.a z0() {
        return this.i;
    }

    h(Dialog dialog, androidx.appcompat.app.e eVar) {
        this(dialog.getContext(), dialog.getWindow(), eVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private h(Context context, Window window, androidx.appcompat.app.e eVar, Object obj) {
        androidx.appcompat.app.d dVarI0;
        this.f3s = null;
        this.t = true;
        this.N = -100;
        this.b0 = new a();
        this.e = context;
        this.h = eVar;
        this.d = obj;
        if (this.N == -100 && (obj instanceof Dialog) && (dVarI0 = I0()) != null) {
            this.N = dVarI0.getDelegate().j();
        }
        if (this.N == -100) {
            androidx.collection.g gVar = g0;
            Integer num = (Integer) gVar.get(obj.getClass().getName());
            if (num != null) {
                this.N = num.intValue();
                gVar.remove(obj.getClass().getName());
            }
        }
        if (window != null) {
            J(window);
        }
        androidx.appcompat.widget.j.h();
    }

    class i extends h.m {
        i(Window.Callback callback) {
            super(callback);
        }

        final ActionMode b(ActionMode.Callback callback) {
            h.b.a aVar = new h.f.a(h.this.e, callback);
            h.b bVarF = h.this.F(aVar);
            if (bVarF != null) {
                return aVar.e(bVarF);
            }
            return null;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return h.this.U(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || h.this.s0(keyEvent.getKeyCode(), keyEvent);
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof androidx.appcompat.view.menu.g)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            h.this.v0(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            h.this.w0(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean onPreparePanel(int i, View view, Menu menu) {
            androidx.appcompat.view.menu.g gVar = menu instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menu : null;
            if (i == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.setOverrideVisibleItems(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (gVar != null) {
                gVar.setOverrideVisibleItems(false);
            }
            return zOnPreparePanel;
        }

        public void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
            w.a aVar;
            r rVarE0 = h.this.e0(0, true);
            if (rVarE0 == null || (aVar = rVarE0.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, aVar, i);
            }
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return h.this.n0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (h.this.n0() && i == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }
}
