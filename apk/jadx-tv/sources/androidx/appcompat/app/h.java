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
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
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
import androidx.appcompat.widget.h1;
import androidx.appcompat.widget.r2;
import androidx.appcompat.widget.s1;
import androidx.appcompat.widget.x2;
import androidx.appcompat.widget.y2;
import androidx.core.view.a2;
import androidx.core.view.b0;
import androidx.core.view.c1;
import androidx.core.view.c2;
import androidx.core.view.f2;
import androidx.core.widget.t;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
class h extends androidx.appcompat.app.f implements androidx.appcompat.view.menu.g.a, LayoutInflater.Factory2 {
    private static final Map f0 = new androidx.collection.a();
    private static final boolean g0;
    private static final int[] h0;
    private static boolean i0;
    private static final boolean j0;
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    private boolean F;
    private p[] G;
    private p H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    boolean M;
    private int N;
    private int O;
    private boolean Q;
    private boolean S;
    private m V;
    private m W;
    boolean X;
    int Y;
    private final Runnable Z;
    private boolean b0;
    private Rect c0;
    final Object d;
    private Rect d0;
    final Context e;
    private AppCompatViewInflater e0;
    Window f;
    private k g;
    final androidx.appcompat.app.e h;
    androidx.appcompat.app.a i;
    MenuInflater j;
    private CharSequence k;
    private h1 l;
    private i m;
    private q n;
    f.b o;
    ActionBarContextView p;
    PopupWindow q;
    Runnable r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    a2 f4s;
    private boolean t;
    private boolean u;
    private ViewGroup v;
    private TextView w;
    private View x;
    private boolean y;
    private boolean z;

    static class a implements Thread.UncaughtExceptionHandler {
        final /* synthetic */ Thread.UncaughtExceptionHandler a;

        a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.a = uncaughtExceptionHandler;
        }

        private boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.a.uncaughtException(thread, notFoundException);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            if ((hVar.Y & 1) != 0) {
                hVar.T(0);
            }
            h hVar2 = h.this;
            if ((hVar2.Y & RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT) != 0) {
                hVar2.T(R$styleable.AppCompatTheme_textColorAlertDialogListItem);
            }
            h hVar3 = h.this;
            hVar3.X = false;
            hVar3.Y = 0;
        }
    }

    class c implements b0 {
        c() {
        }

        @Override // androidx.core.view.b0
        public f2 onApplyWindowInsets(View view, f2 f2Var) {
            int iK = f2Var.k();
            int iI0 = h.this.I0(iK);
            if (iK != iI0) {
                f2Var = f2Var.n(f2Var.i(), iI0, f2Var.j(), f2Var.h());
            }
            return c1.X(view, f2Var);
        }
    }

    class d implements s1.a {
        d() {
        }

        @Override // androidx.appcompat.widget.s1.a
        public void a(Rect rect) {
            rect.top = h.this.I0(rect.top);
        }
    }

    class e implements ContentFrameLayout.a {
        e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            h.this.R();
        }
    }

    class f implements Runnable {

        class a extends c2 {
            a() {
            }

            @Override // androidx.core.view.b2
            public void b(View view) {
                h.this.p.setAlpha(1.0f);
                h.this.f4s.f(null);
                h.this.f4s = null;
            }

            @Override // androidx.core.view.c2, androidx.core.view.b2
            public void c(View view) {
                h.this.p.setVisibility(0);
            }
        }

        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.q.showAtLocation(hVar.p, 55, 0, 0);
            h.this.U();
            if (!h.this.B0()) {
                h.this.p.setAlpha(1.0f);
                h.this.p.setVisibility(0);
            } else {
                h.this.p.setAlpha(0.0f);
                h hVar2 = h.this;
                hVar2.f4s = c1.c(hVar2.p).a(1.0f);
                h.this.f4s.f(new a());
            }
        }
    }

    class g extends c2 {
        g() {
        }

        @Override // androidx.core.view.b2
        public void b(View view) {
            h.this.p.setAlpha(1.0f);
            h.this.f4s.f(null);
            h.this.f4s = null;
        }

        @Override // androidx.core.view.c2, androidx.core.view.b2
        public void c(View view) {
            h.this.p.setVisibility(0);
            h.this.p.sendAccessibilityEvent(32);
            if (h.this.p.getParent() instanceof View) {
                c1.h0((View) h.this.p.getParent());
            }
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.app.h$h, reason: collision with other inner class name */
    private class C0008h implements androidx.appcompat.app.b {
        C0008h() {
        }
    }

    private final class i implements androidx.appcompat.view.menu.m.a {
        i() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackD0 = h.this.d0();
            if (callbackD0 == null) {
                return true;
            }
            callbackD0.onMenuOpened(R$styleable.AppCompatTheme_textColorAlertDialogListItem, gVar);
            return true;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            h.this.L(gVar);
        }
    }

    class j implements f.b.a {
        private f.b.a a;

        class a extends c2 {
            a() {
            }

            @Override // androidx.core.view.b2
            public void b(View view) {
                h.this.p.setVisibility(8);
                h hVar = h.this;
                PopupWindow popupWindow = hVar.q;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (hVar.p.getParent() instanceof View) {
                    c1.h0((View) h.this.p.getParent());
                }
                h.this.p.removeAllViews();
                h.this.f4s.f(null);
                h.this.f4s = null;
            }
        }

        public j(f.b.a aVar) {
            this.a = aVar;
        }

        public boolean a(f.b bVar, Menu menu) {
            return this.a.a(bVar, menu);
        }

        public boolean b(f.b bVar, Menu menu) {
            return this.a.b(bVar, menu);
        }

        public void c(f.b bVar) {
            this.a.c(bVar);
            h hVar = h.this;
            if (hVar.q != null) {
                hVar.f.getDecorView().removeCallbacks(h.this.r);
            }
            h hVar2 = h.this;
            if (hVar2.p != null) {
                hVar2.U();
                h hVar3 = h.this;
                hVar3.f4s = c1.c(hVar3.p).a(0.0f);
                h.this.f4s.f(new a());
            }
            h hVar4 = h.this;
            androidx.appcompat.app.e eVar = hVar4.h;
            if (eVar != null) {
                eVar.onSupportActionModeFinished(hVar4.o);
            }
            h.this.o = null;
        }

        public boolean d(f.b bVar, MenuItem menuItem) {
            return this.a.d(bVar, menuItem);
        }
    }

    private class l extends m {
        private final PowerManager c;

        l(Context context) {
            super();
            this.c = (PowerManager) context.getSystemService("power");
        }

        @Override // androidx.appcompat.app.h.m
        IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.m
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.h.m
        public void d() {
            h.this.F();
        }
    }

    abstract class m {
        private BroadcastReceiver a;

        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                m.this.d();
            }
        }

        m() {
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

    private class n extends m {
        private final androidx.appcompat.app.p c;

        n(androidx.appcompat.app.p pVar) {
            super();
            this.c = pVar;
        }

        @Override // androidx.appcompat.app.h.m
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.m
        public int c() {
            return this.c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.h.m
        public void d() {
            h.this.F();
        }
    }

    private class o extends ContentFrameLayout {
        public o(Context context) {
            super(context);
        }

        private boolean c(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return h.this.S(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !c((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            h.this.N(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(d.b.d(getContext(), i));
        }
    }

    protected static final class p {
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
        Bundle f5s;

        p(int i) {
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
            f.d dVar = new f.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(R$styleable.o);
            this.b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private final class q implements androidx.appcompat.view.menu.m.a {
        q() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackD0;
            if (gVar != 0) {
                return true;
            }
            h hVar = h.this;
            if (!hVar.A || (callbackD0 = hVar.d0()) == null || h.this.M) {
                return true;
            }
            callbackD0.onMenuOpened(R$styleable.AppCompatTheme_textColorAlertDialogListItem, gVar);
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
            r.a rootMenu = gVar.getRootMenu();
            boolean z2 = rootMenu != gVar;
            h hVar = h.this;
            r.a aVar = gVar;
            if (z2) {
                aVar = rootMenu;
            }
            p pVarX = hVar.X(aVar);
            if (pVarX != null) {
                if (!z2) {
                    h.this.O(pVarX, z);
                } else {
                    h.this.K(pVarX.a, pVarX, rootMenu);
                    h.this.O(pVarX, true);
                }
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = false;
        boolean z2 = i2 < 21;
        g0 = z2;
        h0 = new int[]{R.attr.windowBackground};
        if (i2 >= 21 && i2 <= 25) {
            z = true;
        }
        j0 = z;
        if (!z2 || i0) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        i0 = true;
    }

    h(Activity activity, androidx.appcompat.app.e eVar) {
        this(activity, null, eVar, activity);
    }

    private int A0(int i2) {
        if (i2 == 8) {
            return R$styleable.AppCompatTheme_textColorAlertDialogListItem;
        }
        return i2 == 9 ? R$styleable.AppCompatTheme_textColorSearchUrl : i2;
    }

    private boolean C0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || c1.P((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private void E0() {
        if (this.u) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private androidx.appcompat.app.d F0() {
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

    private boolean G(boolean z) {
        if (this.M) {
            return false;
        }
        int iJ = J();
        boolean zG0 = G0(l0(iJ), z);
        if (iJ == 0) {
            a0().e();
        } else {
            m mVar = this.V;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (iJ == 3) {
            Z().e();
        } else {
            m mVar2 = this.W;
            if (mVar2 != null) {
                mVar2.a();
            }
        }
        return zG0;
    }

    private boolean G0(int i2, boolean z) {
        int i3;
        int i4 = this.e.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        boolean z2 = true;
        if (i2 != 1) {
            i3 = i2 != 2 ? i4 : 32;
        } else {
            i3 = 16;
        }
        boolean zJ0 = j0();
        boolean z3 = false;
        if ((j0 || i3 != i4) && !zJ0 && !this.J && (this.d instanceof ContextThemeWrapper)) {
            Configuration configuration = new Configuration();
            configuration.uiMode = (configuration.uiMode & (-49)) | i3;
            try {
                ((ContextThemeWrapper) this.d).applyOverrideConfiguration(configuration);
                z3 = true;
            } catch (IllegalStateException e2) {
                Log.e("AppCompatDelegate", "updateForNightMode. Calling applyOverrideConfiguration() failed with an exception. Will fall back to using Resources.updateConfiguration()", e2);
            }
        }
        int i5 = this.e.getResources().getConfiguration().uiMode & 48;
        if (!z3 && i5 != i3 && z && !zJ0 && this.J) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                n.h.e((Activity) obj);
                z3 = true;
            }
        }
        if (z3 || i5 == i3) {
            z2 = z3;
        } else {
            H0(i3, zJ0);
        }
        if (z2) {
            Object obj2 = this.d;
            if (obj2 instanceof androidx.appcompat.app.d) {
                ((androidx.appcompat.app.d) obj2).onNightModeChanged(i2);
            }
        }
        return z2;
    }

    private void H() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.v.findViewById(R.id.content);
        View decorView = this.f.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
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

    /* JADX WARN: Multi-variable type inference failed */
    private void H0(int i2, boolean z) {
        Resources resources = this.e.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            androidx.appcompat.app.l.a(resources);
        }
        int i4 = this.O;
        if (i4 != 0) {
            this.e.setTheme(i4);
            if (i3 >= 23) {
                this.e.getTheme().applyStyle(this.O, true);
            }
        }
        if (z) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.g) {
                    if (((androidx.lifecycle.g) activity).getLifecycle().b().a(androidx.lifecycle.d.c.STARTED)) {
                        activity.onConfigurationChanged(configuration);
                    }
                } else if (this.L) {
                    activity.onConfigurationChanged(configuration);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.Window$Callback, androidx.appcompat.app.h$k] */
    private void I(Window window) {
        if (this.f != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof k) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        ?? kVar = new k(callback);
        this.g = kVar;
        window.setCallback(kVar);
        r2 r2VarT = r2.t(this.e, null, h0);
        Drawable drawableH = r2VarT.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        r2VarT.v();
        this.f = window;
    }

    private int J() {
        int i2 = this.N;
        return i2 != -100 ? i2 : androidx.appcompat.app.f.f();
    }

    private void M() {
        m mVar = this.V;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.W;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    private ViewGroup P() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.e.obtainStyledAttributes(R$styleable.o);
        int i2 = R$styleable.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            x(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            x(R$styleable.AppCompatTheme_textColorAlertDialogListItem);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            x(R$styleable.AppCompatTheme_textColorSearchUrl);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            x(10);
        }
        this.D = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        W();
        this.f.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.e);
        if (this.E) {
            viewGroup = this.C ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
            if (Build.VERSION.SDK_INT >= 21) {
                c1.x0(viewGroup, new c());
            } else {
                ((s1) viewGroup).setOnFitSystemWindowsListener(new d());
            }
        } else if (this.D) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.B = false;
            this.A = false;
        } else if (this.A) {
            TypedValue typedValue = new TypedValue();
            this.e.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new f.d(this.e, typedValue.resourceId) : this.e).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            h1 h1Var = (h1) viewGroup.findViewById(R$id.decor_content_parent);
            this.l = h1Var;
            h1Var.setWindowCallback(d0());
            if (this.B) {
                this.l.h(R$styleable.AppCompatTheme_textColorSearchUrl);
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
        if (this.l == null) {
            this.w = (TextView) viewGroup.findViewById(R$id.title);
        }
        y2.c(viewGroup);
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
        contentFrameLayout.setAttachListener(new e());
        return viewGroup;
    }

    private void V() {
        if (this.u) {
            return;
        }
        this.v = P();
        CharSequence charSequenceC0 = c0();
        if (!TextUtils.isEmpty(charSequenceC0)) {
            h1 h1Var = this.l;
            if (h1Var != null) {
                h1Var.setWindowTitle(charSequenceC0);
            } else if (w0() != null) {
                w0().x(charSequenceC0);
            } else {
                TextView textView = this.w;
                if (textView != null) {
                    textView.setText(charSequenceC0);
                }
            }
        }
        H();
        u0(this.v);
        this.u = true;
        p pVarB0 = b0(0, false);
        if (this.M) {
            return;
        }
        if (pVarB0 == null || pVarB0.j == null) {
            i0(R$styleable.AppCompatTheme_textColorAlertDialogListItem);
        }
    }

    private void W() {
        if (this.f == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                I(((Activity) obj).getWindow());
            }
        }
        if (this.f == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private m Z() {
        if (this.W == null) {
            this.W = new l(this.e);
        }
        return this.W;
    }

    private void e0() {
        V();
        if (this.A && this.i == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                this.i = new androidx.appcompat.app.q((Activity) this.d, this.B);
            } else if (obj instanceof Dialog) {
                this.i = new androidx.appcompat.app.q((Dialog) this.d);
            }
            androidx.appcompat.app.a aVar = this.i;
            if (aVar != null) {
                aVar.r(this.b0);
            }
        }
    }

    private boolean f0(p pVar) {
        View view = pVar.i;
        if (view != null) {
            pVar.h = view;
            return true;
        }
        if (pVar.j == null) {
            return false;
        }
        if (this.n == null) {
            this.n = new q();
        }
        View view2 = (View) pVar.a(this.n);
        pVar.h = view2;
        return view2 != null;
    }

    private boolean g0(p pVar) {
        pVar.d(Y());
        pVar.g = new o(pVar.l);
        pVar.c = 81;
        return true;
    }

    private boolean h0(p pVar) {
        Resources.Theme themeNewTheme;
        Context context = this.e;
        int i2 = pVar.a;
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
                Context dVar = new f.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
        gVar.setCallback(this);
        pVar.c(gVar);
        return true;
    }

    private void i0(int i2) {
        this.Y = (1 << i2) | this.Y;
        if (this.X) {
            return;
        }
        c1.c0(this.f.getDecorView(), this.Z);
        this.X = true;
    }

    private boolean j0() {
        if (!this.S && (this.d instanceof Activity)) {
            PackageManager packageManager = this.e.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.e, this.d.getClass()), 0);
                this.Q = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException unused) {
                this.Q = false;
            }
        }
        this.S = true;
        return this.Q;
    }

    private boolean o0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        p pVarB0 = b0(i2, true);
        if (pVarB0.o) {
            return false;
        }
        return y0(pVarB0, keyEvent);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    private boolean r0(int i2, KeyEvent keyEvent) {
        boolean zY0;
        AudioManager audioManager;
        h1 h1Var;
        if (this.o != null) {
            return false;
        }
        boolean zB = true;
        p pVarB0 = b0(i2, true);
        if (i2 != 0 || (h1Var = this.l) == null || !h1Var.a() || ViewConfiguration.get(this.e).hasPermanentMenuKey()) {
            boolean z = pVarB0.o;
            if (z || pVarB0.n) {
                O(pVarB0, true);
                zB = z;
            } else if (pVarB0.m) {
                if (pVarB0.r) {
                    pVarB0.m = false;
                    zY0 = y0(pVarB0, keyEvent);
                } else {
                    zY0 = true;
                }
                if (zY0) {
                    v0(pVarB0, keyEvent);
                } else {
                    zB = false;
                }
            } else {
                zB = false;
            }
        } else if (this.l.e()) {
            zB = this.l.b();
        } else if (this.M || !y0(pVarB0, keyEvent)) {
            zB = false;
        } else {
            zB = this.l.c();
        }
        if (zB && (audioManager = (AudioManager) this.e.getSystemService("audio")) != null) {
            audioManager.playSoundEffect(0);
        }
        return zB;
    }

    private void v0(p pVar, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (pVar.o || this.M) {
            return;
        }
        if (pVar.a == 0) {
            if ((this.e.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackD0 = d0();
        if (callbackD0 != null && !callbackD0.onMenuOpened(pVar.a, pVar.j)) {
            O(pVar, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
        if (windowManager != null && y0(pVar, keyEvent)) {
            ViewGroup viewGroup = pVar.g;
            if (viewGroup != null && !pVar.q) {
                View view = pVar.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                pVar.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, pVar.d, pVar.e, 1002, 8519680, -3);
                layoutParams2.gravity = pVar.c;
                layoutParams2.windowAnimations = pVar.f;
                windowManager.addView(pVar.g, layoutParams2);
                pVar.o = true;
            }
            if (viewGroup == null) {
                if (!g0(pVar) || pVar.g == null) {
                    return;
                }
            } else if (pVar.q && viewGroup.getChildCount() > 0) {
                pVar.g.removeAllViews();
            }
            if (!f0(pVar) || !pVar.b()) {
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = pVar.h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            pVar.g.setBackgroundResource(pVar.b);
            ViewParent parent = pVar.h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(pVar.h);
            }
            pVar.g.addView(pVar.h, layoutParams3);
            if (!pVar.h.hasFocus()) {
                pVar.h.requestFocus();
            }
            i2 = -2;
            pVar.n = false;
            WindowManager.LayoutParams layoutParams4 = new WindowManager.LayoutParams(i2, -2, pVar.d, pVar.e, 1002, 8519680, -3);
            layoutParams4.gravity = pVar.c;
            layoutParams4.windowAnimations = pVar.f;
            windowManager.addView(pVar.g, layoutParams4);
            pVar.o = true;
        }
    }

    private boolean x0(p pVar, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((pVar.m || y0(pVar, keyEvent)) && (gVar = pVar.j) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.l == null) {
            O(pVar, true);
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
    private boolean y0(p pVar, KeyEvent keyEvent) {
        h1 h1Var;
        h1 h1Var2;
        h1 h1Var3;
        if (this.M) {
            return false;
        }
        if (pVar.m) {
            return true;
        }
        p pVar2 = this.H;
        if (pVar2 != null && pVar2 != pVar) {
            O(pVar2, false);
        }
        Window.Callback callbackD0 = d0();
        if (callbackD0 != null) {
            pVar.i = callbackD0.onCreatePanelView(pVar.a);
        }
        int i2 = pVar.a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (h1Var3 = this.l) != null) {
            h1Var3.f();
        }
        if (pVar.i == null && (!z || !(w0() instanceof androidx.appcompat.app.n))) {
            androidx.appcompat.view.menu.g gVar = pVar.j;
            if (gVar == null || pVar.r) {
                if (gVar == null && (!h0(pVar) || pVar.j == null)) {
                    return false;
                }
                if (z && this.l != null) {
                    if (this.m == null) {
                        this.m = new i();
                    }
                    this.l.d(pVar.j, this.m);
                }
                pVar.j.stopDispatchingItemsChanged();
                if (!callbackD0.onCreatePanelMenu(pVar.a, pVar.j)) {
                    pVar.c(null);
                    if (z && (h1Var = this.l) != null) {
                        h1Var.d(null, this.m);
                    }
                    return false;
                }
                pVar.r = false;
            }
            pVar.j.stopDispatchingItemsChanged();
            Bundle bundle = pVar.f5s;
            if (bundle != null) {
                pVar.j.restoreActionViewStates(bundle);
                pVar.f5s = null;
            }
            if (!callbackD0.onPreparePanel(0, pVar.i, pVar.j)) {
                if (z && (h1Var2 = this.l) != null) {
                    h1Var2.d(null, this.m);
                }
                pVar.j.startDispatchingItemsChanged();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            pVar.p = z2;
            pVar.j.setQwertyMode(z2);
            pVar.j.startDispatchingItemsChanged();
        }
        pVar.m = true;
        pVar.n = false;
        this.H = pVar;
        return true;
    }

    private void z0(androidx.appcompat.view.menu.g gVar, boolean z) {
        h1 h1Var = this.l;
        if (h1Var == null || !h1Var.a() || (ViewConfiguration.get(this.e).hasPermanentMenuKey() && !this.l.g())) {
            p pVarB0 = b0(0, true);
            pVarB0.q = true;
            O(pVarB0, false);
            v0(pVarB0, null);
            return;
        }
        Window.Callback callbackD0 = d0();
        if (this.l.e() && z) {
            this.l.b();
            if (this.M) {
                return;
            }
            callbackD0.onPanelClosed(R$styleable.AppCompatTheme_textColorAlertDialogListItem, b0(0, true).j);
            return;
        }
        if (callbackD0 == null || this.M) {
            return;
        }
        if (this.X && (this.Y & 1) != 0) {
            this.f.getDecorView().removeCallbacks(this.Z);
            this.Z.run();
        }
        p pVarB1 = b0(0, true);
        r.a aVar = pVarB1.j;
        if (aVar == null || pVarB1.r || !callbackD0.onPreparePanel(0, pVarB1.i, aVar)) {
            return;
        }
        callbackD0.onMenuOpened(R$styleable.AppCompatTheme_textColorAlertDialogListItem, pVarB1.j);
        this.l.c();
    }

    @Override // androidx.appcompat.app.f
    public void A(View view, ViewGroup.LayoutParams layoutParams) {
        V();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.f
    public void B(Toolbar toolbar) {
        if (this.d instanceof Activity) {
            androidx.appcompat.app.a aVarJ = j();
            if (aVarJ instanceof androidx.appcompat.app.q) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.j = null;
            if (aVarJ != null) {
                aVarJ.n();
            }
            if (toolbar != null) {
                androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(toolbar, c0(), this.g);
                this.i = nVar;
                this.f.setCallback(nVar.A());
            } else {
                this.i = null;
                this.f.setCallback(this.g);
            }
            l();
        }
    }

    final boolean B0() {
        ViewGroup viewGroup;
        return this.u && (viewGroup = this.v) != null && c1.Q(viewGroup);
    }

    @Override // androidx.appcompat.app.f
    public void C(int i2) {
        this.O = i2;
    }

    @Override // androidx.appcompat.app.f
    public final void D(CharSequence charSequence) {
        this.k = charSequence;
        h1 h1Var = this.l;
        if (h1Var != null) {
            h1Var.setWindowTitle(charSequence);
            return;
        }
        if (w0() != null) {
            w0().x(charSequence);
            return;
        }
        TextView textView = this.w;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    f.b D0(f.b.a aVar) {
        f.b bVarOnWindowStartingSupportActionMode;
        f.d dVar;
        androidx.appcompat.app.e eVar;
        U();
        f.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
        }
        if (!(aVar instanceof j)) {
            aVar = new j(aVar);
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
                        dVar = new f.d(this.e, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.e;
                    }
                    this.p = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow((Context) dVar, (AttributeSet) null, R$attr.actionModePopupWindowStyle);
                    this.q = popupWindow;
                    t.b(popupWindow, 2);
                    this.q.setContentView(this.p);
                    this.q.setWidth(-1);
                    dVar.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                    this.p.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.q.setHeight(-2);
                    this.r = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.v.findViewById(R$id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(Y()));
                        this.p = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.p != null) {
                U();
                this.p.k();
                f.b eVar3 = new f.e(this.p.getContext(), this.p, aVar, this.q == null);
                if (aVar.a(eVar3, eVar3.c())) {
                    eVar3.i();
                    this.p.h(eVar3);
                    this.o = eVar3;
                    if (B0()) {
                        this.p.setAlpha(0.0f);
                        a2 a2VarA = c1.c(this.p).a(1.0f);
                        this.f4s = a2VarA;
                        a2VarA.f(new g());
                    } else {
                        this.p.setAlpha(1.0f);
                        this.p.setVisibility(0);
                        this.p.sendAccessibilityEvent(32);
                        if (this.p.getParent() instanceof View) {
                            c1.h0((View) this.p.getParent());
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
        f.b bVar2 = this.o;
        if (bVar2 != null && (eVar = this.h) != null) {
            eVar.onSupportActionModeStarted(bVar2);
        }
        return this.o;
    }

    @Override // androidx.appcompat.app.f
    public f.b E(f.b.a aVar) {
        androidx.appcompat.app.e eVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        f.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
        }
        j jVar = new j(aVar);
        androidx.appcompat.app.a aVarJ = j();
        if (aVarJ != null) {
            f.b bVarY = aVarJ.y(jVar);
            this.o = bVarY;
            if (bVarY != null && (eVar = this.h) != null) {
                eVar.onSupportActionModeStarted(bVarY);
            }
        }
        if (this.o == null) {
            this.o = D0(jVar);
        }
        return this.o;
    }

    public boolean F() {
        return G(true);
    }

    int I0(int i2) {
        boolean z;
        boolean z2;
        ActionBarContextView actionBarContextView = this.p;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.p.getLayoutParams();
            boolean z3 = true;
            if (this.p.isShown()) {
                if (this.c0 == null) {
                    this.c0 = new Rect();
                    this.d0 = new Rect();
                }
                Rect rect = this.c0;
                Rect rect2 = this.d0;
                rect.set(0, i2, 0, 0);
                y2.a(this.v, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.x;
                    if (view == null) {
                        View view2 = new View(this.e);
                        this.x = view2;
                        view2.setBackgroundColor(this.e.getResources().getColor(R$color.abc_input_method_navigation_guard));
                        this.v.addView(this.x, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.x.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = this.x != null;
                if (!this.C && z3) {
                    i2 = 0;
                }
                boolean z4 = z3;
                z3 = z2;
                z = z4;
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
        View view3 = this.x;
        if (view3 != null) {
            view3.setVisibility(z ? 0 : 8);
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [android.view.Window$Callback] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.view.Menu] */
    /* JADX WARN: Type inference failed for: r5v1, types: [android.view.Menu] */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.appcompat.view.menu.g] */
    void K(int i2, p pVar, Menu menu) {
        if (menu == 0) {
            if (pVar == null && i2 >= 0) {
                p[] pVarArr = this.G;
                if (i2 < pVarArr.length) {
                    pVar = pVarArr[i2];
                }
            }
            if (pVar != null) {
                menu = pVar.j;
            }
        }
        if ((pVar == null || pVar.o) && !this.M) {
            this.g.a().onPanelClosed(i2, menu);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    void L(androidx.appcompat.view.menu.g gVar) {
        if (this.F) {
            return;
        }
        this.F = true;
        this.l.i();
        Window.Callback callbackD0 = d0();
        if (callbackD0 != null && !this.M) {
            callbackD0.onPanelClosed(R$styleable.AppCompatTheme_textColorAlertDialogListItem, gVar);
        }
        this.F = false;
    }

    void N(int i2) {
        O(b0(i2, true), true);
    }

    void O(p pVar, boolean z) {
        ViewGroup viewGroup;
        h1 h1Var;
        if (z && pVar.a == 0 && (h1Var = this.l) != null && h1Var.e()) {
            L(pVar.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
        if (windowManager != null && pVar.o && (viewGroup = pVar.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                K(pVar.a, pVar, null);
            }
        }
        pVar.m = false;
        pVar.n = false;
        pVar.o = false;
        pVar.h = null;
        pVar.q = true;
        if (this.H == pVar) {
            this.H = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View Q(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        boolean zC0 = false;
        if (this.e0 == null) {
            String string = this.e.obtainStyledAttributes(R$styleable.o).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                this.e0 = new AppCompatViewInflater();
            } else {
                try {
                    this.e0 = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to instantiate custom view inflater ");
                    sb.append(string);
                    sb.append(". Falling back to default.");
                    this.e0 = new AppCompatViewInflater();
                }
            }
        }
        boolean z2 = g0;
        if (z2) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zC0 = C0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zC0 = true;
            }
            z = zC0;
        } else {
            z = false;
        }
        return this.e0.createView(view, str, context, attributeSet, z, z2, true, x2.b());
    }

    void R() {
        androidx.appcompat.view.menu.g gVar;
        h1 h1Var = this.l;
        if (h1Var != null) {
            h1Var.i();
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
        U();
        p pVarB0 = b0(0, false);
        if (pVarB0 == null || (gVar = pVarB0.j) == null) {
            return;
        }
        gVar.close();
    }

    boolean S(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.d;
        if (((obj instanceof androidx.core.view.k.a) || (obj instanceof androidx.appcompat.app.j)) && (decorView = this.f.getDecorView()) != null && androidx.core.view.k.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.g.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? n0(keyCode, keyEvent) : q0(keyCode, keyEvent);
    }

    void T(int i2) {
        p pVarB0;
        p pVarB1 = b0(i2, true);
        if (pVarB1.j != null) {
            Bundle bundle = new Bundle();
            pVarB1.j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                pVarB1.f5s = bundle;
            }
            pVarB1.j.stopDispatchingItemsChanged();
            pVarB1.j.clear();
        }
        pVarB1.r = true;
        pVarB1.q = true;
        if ((i2 != 108 && i2 != 0) || this.l == null || (pVarB0 = b0(0, false)) == null) {
            return;
        }
        pVarB0.m = false;
        y0(pVarB0, null);
    }

    void U() {
        a2 a2Var = this.f4s;
        if (a2Var != null) {
            a2Var.b();
        }
    }

    p X(Menu menu) {
        p[] pVarArr = this.G;
        int length = pVarArr != null ? pVarArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            p pVar = pVarArr[i2];
            if (pVar != null && pVar.j == menu) {
                return pVar;
            }
        }
        return null;
    }

    final Context Y() {
        androidx.appcompat.app.a aVarJ = j();
        Context contextK = aVarJ != null ? aVarJ.k() : null;
        return contextK == null ? this.e : contextK;
    }

    @Override // androidx.appcompat.app.f
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        V();
        ((ViewGroup) this.v.findViewById(R.id.content)).addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    final m a0() {
        if (this.V == null) {
            this.V = new n(androidx.appcompat.app.p.a(this.e));
        }
        return this.V;
    }

    @Override // androidx.appcompat.app.f
    public void b(Context context) {
        G(false);
        this.J = true;
    }

    protected p b0(int i2, boolean z) {
        p[] pVarArr = this.G;
        if (pVarArr == null || pVarArr.length <= i2) {
            p[] pVarArr2 = new p[i2 + 1];
            if (pVarArr != null) {
                System.arraycopy(pVarArr, 0, pVarArr2, 0, pVarArr.length);
            }
            this.G = pVarArr2;
            pVarArr = pVarArr2;
        }
        p pVar = pVarArr[i2];
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p(i2);
        pVarArr[i2] = pVar2;
        return pVar2;
    }

    final CharSequence c0() {
        Object obj = this.d;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.k;
    }

    final Window.Callback d0() {
        return this.f.getCallback();
    }

    @Override // androidx.appcompat.app.f
    public View e(int i2) {
        V();
        return this.f.findViewById(i2);
    }

    @Override // androidx.appcompat.app.f
    public final androidx.appcompat.app.b g() {
        return new C0008h();
    }

    @Override // androidx.appcompat.app.f
    public int h() {
        return this.N;
    }

    @Override // androidx.appcompat.app.f
    public MenuInflater i() {
        if (this.j == null) {
            e0();
            androidx.appcompat.app.a aVar = this.i;
            this.j = new f.g(aVar != null ? aVar.k() : this.e);
        }
        return this.j;
    }

    @Override // androidx.appcompat.app.f
    public androidx.appcompat.app.a j() {
        e0();
        return this.i;
    }

    @Override // androidx.appcompat.app.f
    public void k() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.e);
        if (layoutInflaterFrom.getFactory() == null) {
            androidx.core.view.l.b(layoutInflaterFrom, this);
        } else {
            boolean z = layoutInflaterFrom.getFactory2() instanceof h;
        }
    }

    public boolean k0() {
        return this.t;
    }

    @Override // androidx.appcompat.app.f
    public void l() {
        androidx.appcompat.app.a aVarJ = j();
        if (aVarJ == null || !aVarJ.l()) {
            i0(0);
        }
    }

    int l0(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 == -1) {
            return i2;
        }
        if (i2 == 0) {
            if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) this.e.getSystemService(UiModeManager.class)).getNightMode() != 0) {
                return a0().c();
            }
            return -1;
        }
        if (i2 == 1 || i2 == 2) {
            return i2;
        }
        if (i2 == 3) {
            return Z().c();
        }
        throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
    }

    boolean m0() {
        f.b bVar = this.o;
        if (bVar != null) {
            bVar.a();
            return true;
        }
        androidx.appcompat.app.a aVarJ = j();
        return aVarJ != null && aVarJ.h();
    }

    boolean n0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.I = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            o0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void o(Configuration configuration) {
        androidx.appcompat.app.a aVarJ;
        if (this.A && this.u && (aVarJ = j()) != null) {
            aVarJ.m(configuration);
        }
        androidx.appcompat.widget.k.b().g(this.e);
        G(false);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return Q(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        p pVarX;
        Window.Callback callbackD0 = d0();
        if (callbackD0 == null || this.M || (pVarX = X(gVar.getRootMenu())) == null) {
            return false;
        }
        return callbackD0.onMenuItemSelected(pVarX.a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
        z0(gVar, true);
    }

    @Override // androidx.appcompat.app.f
    public void p(Bundle bundle) {
        String strC;
        this.J = true;
        G(false);
        W();
        Object obj = this.d;
        if (obj instanceof Activity) {
            try {
                strC = n.q.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
                strC = null;
            }
            if (strC != null) {
                androidx.appcompat.app.a aVarW0 = w0();
                if (aVarW0 == null) {
                    this.b0 = true;
                } else {
                    aVarW0.r(true);
                }
            }
        }
        this.K = true;
    }

    boolean p0(int i2, KeyEvent keyEvent) {
        androidx.appcompat.app.a aVarJ = j();
        if (aVarJ != null && aVarJ.o(i2, keyEvent)) {
            return true;
        }
        p pVar = this.H;
        if (pVar != null && x0(pVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            p pVar2 = this.H;
            if (pVar2 != null) {
                pVar2.n = true;
            }
            return true;
        }
        if (this.H == null) {
            p pVarB0 = b0(0, true);
            y0(pVarB0, keyEvent);
            boolean zX0 = x0(pVarB0, keyEvent.getKeyCode(), keyEvent, 1);
            pVarB0.m = false;
            if (zX0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void q() {
        androidx.appcompat.app.f.n(this);
        if (this.X) {
            this.f.getDecorView().removeCallbacks(this.Z);
        }
        this.L = false;
        this.M = true;
        androidx.appcompat.app.a aVar = this.i;
        if (aVar != null) {
            aVar.n();
        }
        M();
    }

    boolean q0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.I;
            this.I = false;
            p pVarB0 = b0(0, false);
            if (pVarB0 != null && pVarB0.o) {
                if (!z) {
                    O(pVarB0, true);
                }
                return true;
            }
            if (m0()) {
                return true;
            }
        } else if (i2 == 82) {
            r0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.f
    public void r(Bundle bundle) {
        V();
    }

    @Override // androidx.appcompat.app.f
    public void s() {
        androidx.appcompat.app.a aVarJ = j();
        if (aVarJ != null) {
            aVarJ.u(true);
        }
    }

    void s0(int i2) {
        androidx.appcompat.app.a aVarJ;
        if (i2 != 108 || (aVarJ = j()) == null) {
            return;
        }
        aVarJ.i(true);
    }

    @Override // androidx.appcompat.app.f
    public void t(Bundle bundle) {
        if (this.N != -100) {
            f0.put(this.d.getClass(), Integer.valueOf(this.N));
        }
    }

    void t0(int i2) {
        if (i2 == 108) {
            androidx.appcompat.app.a aVarJ = j();
            if (aVarJ != null) {
                aVarJ.i(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            p pVarB0 = b0(i2, true);
            if (pVarB0.o) {
                O(pVarB0, false);
            }
        }
    }

    @Override // androidx.appcompat.app.f
    public void u() {
        this.L = true;
        F();
        androidx.appcompat.app.f.m(this);
    }

    void u0(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.f
    public void v() {
        this.L = false;
        androidx.appcompat.app.f.n(this);
        androidx.appcompat.app.a aVarJ = j();
        if (aVarJ != null) {
            aVarJ.u(false);
        }
        if (this.d instanceof Dialog) {
            M();
        }
    }

    final androidx.appcompat.app.a w0() {
        return this.i;
    }

    @Override // androidx.appcompat.app.f
    public boolean x(int i2) {
        int iA0 = A0(i2);
        if (this.E && iA0 == 108) {
            return false;
        }
        if (this.A && iA0 == 1) {
            this.A = false;
        }
        if (iA0 == 1) {
            E0();
            this.E = true;
            return true;
        }
        if (iA0 == 2) {
            E0();
            this.y = true;
            return true;
        }
        if (iA0 == 5) {
            E0();
            this.z = true;
            return true;
        }
        if (iA0 == 10) {
            E0();
            this.C = true;
            return true;
        }
        if (iA0 == 108) {
            E0();
            this.A = true;
            return true;
        }
        if (iA0 != 109) {
            return this.f.requestFeature(iA0);
        }
        E0();
        this.B = true;
        return true;
    }

    @Override // androidx.appcompat.app.f
    public void y(int i2) {
        V();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.e).inflate(i2, viewGroup);
        this.g.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.f
    public void z(View view) {
        V();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.g.a().onContentChanged();
    }

    h(Dialog dialog, androidx.appcompat.app.e eVar) {
        this(dialog.getContext(), dialog.getWindow(), eVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private h(Context context, Window window, androidx.appcompat.app.e eVar, Object obj) {
        androidx.appcompat.app.d dVarF0;
        this.f4s = null;
        this.t = true;
        this.N = -100;
        this.Z = new b();
        this.e = context;
        this.h = eVar;
        this.d = obj;
        if (this.N == -100 && (obj instanceof Dialog) && (dVarF0 = F0()) != null) {
            this.N = dVarF0.getDelegate().h();
        }
        if (this.N == -100) {
            Map map = f0;
            Integer num = (Integer) map.get(obj.getClass());
            if (num != null) {
                this.N = num.intValue();
                map.remove(obj.getClass());
            }
        }
        if (window != null) {
            I(window);
        }
        androidx.appcompat.widget.k.h();
    }

    class k extends f.m {
        k(Window.Callback callback) {
            super(callback);
        }

        final ActionMode b(ActionMode.Callback callback) {
            f.b.a aVar = new f.f.a(h.this.e, callback);
            f.b bVarE = h.this.E(aVar);
            if (bVarE != null) {
                return aVar.e(bVarE);
            }
            return null;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return h.this.S(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || h.this.p0(keyEvent.getKeyCode(), keyEvent);
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
            h.this.s0(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            h.this.t0(i);
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
            r.a aVar;
            p pVarB0 = h.this.b0(0, true);
            if (pVarB0 == null || (aVar = pVarB0.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, aVar, i);
            }
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return h.this.k0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (h.this.k0() && i == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }
}
