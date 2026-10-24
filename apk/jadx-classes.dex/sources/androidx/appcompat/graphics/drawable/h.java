package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.SparseArray;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
abstract class h extends Drawable implements Drawable.Callback {
    private c a;
    private Rect b;
    private Drawable c;
    private Drawable d;
    private boolean f;
    private boolean h;
    private Runnable i;
    private long j;
    private long k;
    private b l;
    private int e = 255;
    private int g = -1;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.a(true);
            h.this.invalidateSelf();
        }
    }

    static class b implements Drawable.Callback {
        private Drawable.Callback a;

        b() {
        }

        public Drawable.Callback a() {
            Drawable.Callback callback = this.a;
            this.a = null;
            return callback;
        }

        public b b(Drawable.Callback callback) {
            this.a = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.a;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.a;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    static abstract class c extends Drawable.ConstantState {
        int A;
        int B;
        boolean C;
        ColorFilter D;
        boolean E;
        ColorStateList F;
        PorterDuff.Mode G;
        boolean H;
        boolean I;
        final h a;
        Resources b;
        int c;
        int d;
        int e;
        SparseArray f;
        Drawable[] g;
        int h;
        boolean i;
        boolean j;
        Rect k;
        boolean l;
        boolean m;
        int n;
        int o;
        int p;
        int q;
        boolean r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        int f8s;
        boolean t;
        boolean u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        int z;

        c(c cVar, h hVar, Resources resources) {
            this.c = 160;
            this.i = false;
            this.l = false;
            this.x = true;
            this.A = 0;
            this.B = 0;
            this.a = hVar;
            this.b = resources != null ? resources : cVar != null ? cVar.b : null;
            int iF = h.f(resources, cVar != null ? cVar.c : 0);
            this.c = iF;
            if (cVar == null) {
                this.g = new Drawable[10];
                this.h = 0;
                return;
            }
            this.d = cVar.d;
            this.e = cVar.e;
            this.v = true;
            this.w = true;
            this.i = cVar.i;
            this.l = cVar.l;
            this.x = cVar.x;
            this.y = cVar.y;
            this.z = cVar.z;
            this.A = cVar.A;
            this.B = cVar.B;
            this.C = cVar.C;
            this.D = cVar.D;
            this.E = cVar.E;
            this.F = cVar.F;
            this.G = cVar.G;
            this.H = cVar.H;
            this.I = cVar.I;
            if (cVar.c == iF) {
                if (cVar.j) {
                    this.k = new Rect(cVar.k);
                    this.j = true;
                }
                if (cVar.m) {
                    this.n = cVar.n;
                    this.o = cVar.o;
                    this.p = cVar.p;
                    this.q = cVar.q;
                    this.m = true;
                }
            }
            if (cVar.r) {
                this.f8s = cVar.f8s;
                this.r = true;
            }
            if (cVar.t) {
                this.u = cVar.u;
                this.t = true;
            }
            Drawable[] drawableArr = cVar.g;
            this.g = new Drawable[drawableArr.length];
            this.h = cVar.h;
            SparseArray sparseArray = cVar.f;
            if (sparseArray != null) {
                this.f = sparseArray.clone();
            } else {
                this.f = new SparseArray(this.h);
            }
            int i = this.h;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable != null) {
                    Drawable.ConstantState constantState = drawable.getConstantState();
                    if (constantState != null) {
                        this.f.put(i2, constantState);
                    } else {
                        this.g[i2] = drawableArr[i2];
                    }
                }
            }
        }

        private void e() {
            SparseArray sparseArray = this.f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    this.g[this.f.keyAt(i)] = s(((Drawable.ConstantState) this.f.valueAt(i)).newDrawable(this.b));
                }
                this.f = null;
            }
        }

        private Drawable s(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.z);
            }
            Drawable drawableMutate = drawable.mutate();
            drawableMutate.setCallback(this.a);
            return drawableMutate;
        }

        public final int a(Drawable drawable) {
            int i = this.h;
            if (i >= this.g.length) {
                o(i, i + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.a);
            this.g[i] = drawable;
            this.h++;
            this.e = drawable.getChangingConfigurations() | this.e;
            p();
            this.k = null;
            this.j = false;
            this.m = false;
            this.v = false;
            return i;
        }

        final void b(Resources.Theme theme) {
            if (theme != null) {
                e();
                int i = this.h;
                Drawable[] drawableArr = this.g;
                for (int i2 = 0; i2 < i; i2++) {
                    Drawable drawable = drawableArr[i2];
                    if (drawable != null && drawable.canApplyTheme()) {
                        drawableArr[i2].applyTheme(theme);
                        this.e |= drawableArr[i2].getChangingConfigurations();
                    }
                }
                y(theme.getResources());
            }
        }

        public synchronized boolean c() {
            if (this.v) {
                return this.w;
            }
            e();
            this.v = true;
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getConstantState() == null) {
                    this.w = false;
                    return false;
                }
            }
            this.w = true;
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    Drawable.ConstantState constantState = (Drawable.ConstantState) this.f.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        protected void d() {
            this.m = true;
            e();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            this.o = -1;
            this.n = -1;
            this.q = 0;
            this.p = 0;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.n) {
                    this.n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.o) {
                    this.o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.p) {
                    this.p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.q) {
                    this.q = minimumHeight;
                }
            }
        }

        final int f() {
            return this.g.length;
        }

        public final Drawable g(int i) {
            int iIndexOfKey;
            Drawable drawable = this.g[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray sparseArray = this.f;
            if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i)) < 0) {
                return null;
            }
            Drawable drawableS = s(((Drawable.ConstantState) this.f.valueAt(iIndexOfKey)).newDrawable(this.b));
            this.g[i] = drawableS;
            this.f.removeAt(iIndexOfKey);
            if (this.f.size() == 0) {
                this.f = null;
            }
            return drawableS;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.d | this.e;
        }

        public final int h() {
            return this.h;
        }

        public final int i() {
            if (!this.m) {
                d();
            }
            return this.o;
        }

        public final int j() {
            if (!this.m) {
                d();
            }
            return this.q;
        }

        public final int k() {
            if (!this.m) {
                d();
            }
            return this.p;
        }

        public final Rect l() {
            Rect rect = null;
            if (this.i) {
                return null;
            }
            Rect rect2 = this.k;
            if (rect2 != null || this.j) {
                return rect2;
            }
            e();
            Rect rect3 = new Rect();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getPadding(rect3)) {
                    if (rect == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                    int i3 = rect3.left;
                    if (i3 > rect.left) {
                        rect.left = i3;
                    }
                    int i4 = rect3.top;
                    if (i4 > rect.top) {
                        rect.top = i4;
                    }
                    int i5 = rect3.right;
                    if (i5 > rect.right) {
                        rect.right = i5;
                    }
                    int i6 = rect3.bottom;
                    if (i6 > rect.bottom) {
                        rect.bottom = i6;
                    }
                }
            }
            this.j = true;
            this.k = rect;
            return rect;
        }

        public final int m() {
            if (!this.m) {
                d();
            }
            return this.n;
        }

        public final int n() {
            if (this.r) {
                return this.f8s;
            }
            e();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i2 = 1; i2 < i; i2++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
            }
            this.f8s = opacity;
            this.r = true;
            return opacity;
        }

        public void o(int i, int i2) {
            Drawable[] drawableArr = new Drawable[i2];
            System.arraycopy(this.g, 0, drawableArr, 0, i);
            this.g = drawableArr;
        }

        void p() {
            this.r = false;
            this.t = false;
        }

        public final boolean q() {
            return this.l;
        }

        abstract void r();

        public final void t(boolean z) {
            this.l = z;
        }

        public final void u(int i) {
            this.A = i;
        }

        public final void v(int i) {
            this.B = i;
        }

        final boolean w(int i, int i2) {
            int i3 = this.h;
            Drawable[] drawableArr = this.g;
            boolean z = false;
            for (int i4 = 0; i4 < i3; i4++) {
                Drawable drawable = drawableArr[i4];
                if (drawable != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawable.setLayoutDirection(i) : false;
                    if (i4 == i2) {
                        z = layoutDirection;
                    }
                }
            }
            this.z = i;
            return z;
        }

        public final void x(boolean z) {
            this.i = z;
        }

        final void y(Resources resources) {
            if (resources != null) {
                this.b = resources;
                int iF = h.f(resources, this.c);
                int i = this.c;
                this.c = iF;
                if (i != iF) {
                    this.m = false;
                    this.j = false;
                }
            }
        }
    }

    h() {
    }

    private void d(Drawable drawable) {
        if (this.l == null) {
            this.l = new b();
        }
        drawable.setCallback(this.l.b(drawable.getCallback()));
        try {
            if (this.a.A <= 0 && this.f) {
                drawable.setAlpha(this.e);
            }
            c cVar = this.a;
            if (cVar.E) {
                drawable.setColorFilter(cVar.D);
            } else {
                if (cVar.H) {
                    q.h.o(drawable, cVar.F);
                }
                c cVar2 = this.a;
                if (cVar2.I) {
                    q.h.p(drawable, cVar2.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.a.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            int i = Build.VERSION.SDK_INT;
            if (i >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            drawable.setAutoMirrored(this.a.C);
            Rect rect = this.b;
            if (i >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.l.a());
        }
    }

    private boolean e() {
        return isAutoMirrored() && q.h.f(this) == 1;
    }

    static int f(Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        if (i == 0) {
            return 160;
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x003f  */
    /* JADX WARN: Code duplicated, block: B:16:0x0045  */
    /* JADX WARN: Code duplicated, block: B:18:0x0049  */
    /* JADX WARN: Code duplicated, block: B:19:0x0052  */
    /* JADX WARN: Code duplicated, block: B:20:0x0065  */
    /* JADX WARN: Code duplicated, block: B:23:0x006a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    void a(boolean z) {
        boolean z2;
        Drawable drawable;
        long j;
        boolean z3 = true;
        this.f = true;
        long jUptimeMillis = SystemClock.uptimeMillis();
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            long j2 = this.j;
            if (j2 != 0) {
                if (j2 <= jUptimeMillis) {
                    drawable2.setAlpha(this.e);
                    this.j = 0L;
                } else {
                    drawable2.setAlpha(((255 - (((int) ((j2 - jUptimeMillis) * 255)) / this.a.A)) * this.e) / 255);
                    z2 = true;
                }
            }
            drawable = this.d;
            if (drawable != null) {
                j = this.k;
                if (j == 0) {
                    if (j <= jUptimeMillis) {
                        drawable.setVisible(false, false);
                        this.d = null;
                        this.k = 0L;
                    } else {
                        drawable.setAlpha(((((int) ((j - jUptimeMillis) * 255)) / this.a.B) * this.e) / 255);
                    }
                }
                if (z || !z3) {
                }
                scheduleSelf(this.i, jUptimeMillis + 16);
                return;
            }
            this.k = 0L;
            z3 = z2;
            if (z) {
            }
        }
        this.j = 0L;
        z2 = false;
        drawable = this.d;
        if (drawable != null) {
            j = this.k;
            if (j == 0) {
                if (j <= jUptimeMillis) {
                    drawable.setVisible(false, false);
                    this.d = null;
                    this.k = 0L;
                } else {
                    drawable.setAlpha(((((int) ((j - jUptimeMillis) * 255)) / this.a.B) * this.e) / 255);
                }
            }
            if (z) {
            }
        }
        this.k = 0L;
        z3 = z2;
        if (z) {
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        this.a.b(theme);
    }

    abstract c b();

    int c() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.a.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.c;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.d;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0055  */
    boolean g(int i) {
        if (i == this.g) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.a.B > 0) {
            Drawable drawable = this.d;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
            Drawable drawable2 = this.c;
            if (drawable2 != null) {
                this.d = drawable2;
                this.k = ((long) this.a.B) + jUptimeMillis;
            } else {
                this.d = null;
                this.k = 0L;
            }
        } else {
            Drawable drawable3 = this.c;
            if (drawable3 != null) {
                drawable3.setVisible(false, false);
            }
        }
        if (i >= 0) {
            c cVar = this.a;
            if (i < cVar.h) {
                Drawable drawableG = cVar.g(i);
                this.c = drawableG;
                this.g = i;
                if (drawableG != null) {
                    int i2 = this.a.A;
                    if (i2 > 0) {
                        this.j = jUptimeMillis + ((long) i2);
                    }
                    d(drawableG);
                }
            } else {
                this.c = null;
                this.g = -1;
            }
        } else {
            this.c = null;
            this.g = -1;
        }
        if (this.j != 0 || this.k != 0) {
            Runnable runnable = this.i;
            if (runnable == null) {
                this.i = new a();
            } else {
                unscheduleSelf(runnable);
            }
            a(true);
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.a.c()) {
            return null;
        }
        this.a.d = getChangingConfigurations();
        return this.a;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.b;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.a.q()) {
            return this.a.i();
        }
        Drawable drawable = this.c;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.a.q()) {
            return this.a.m();
        }
        Drawable drawable = this.c;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.a.q()) {
            return this.a.j();
        }
        Drawable drawable = this.c;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.a.q()) {
            return this.a.k();
        }
        Drawable drawable = this.c;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.c;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.a.n();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.c;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect rectL = this.a.l();
        if (rectL != null) {
            rect.set(rectL);
            padding = (rectL.right | ((rectL.left | rectL.top) | rectL.bottom)) != 0;
        } else {
            Drawable drawable = this.c;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (e()) {
            int i = rect.left;
            rect.left = rect.right;
            rect.right = i;
        }
        return padding;
    }

    void h(c cVar) {
        this.a = cVar;
        int i = this.g;
        if (i >= 0) {
            Drawable drawableG = cVar.g(i);
            this.c = drawableG;
            if (drawableG != null) {
                d(drawableG);
            }
        }
        this.d = null;
    }

    final void i(Resources resources) {
        this.a.y(resources);
    }

    public void invalidateDrawable(Drawable drawable) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.p();
        }
        if (drawable != this.c || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.a.C;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.d;
        boolean z2 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.d = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f) {
                this.c.setAlpha(this.e);
            }
        }
        if (this.k != 0) {
            this.k = 0L;
            z = true;
        }
        if (this.j != 0) {
            this.j = 0L;
        } else {
            z2 = z;
        }
        if (z2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.h && super.mutate() == this) {
            c cVarB = b();
            cVarB.r();
            h(cVarB);
            this.h = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.d;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        return this.a.w(i, c());
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.d;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable != this.c || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.f && this.e == i) {
            return;
        }
        this.f = true;
        this.e = i;
        Drawable drawable = this.c;
        if (drawable != null) {
            if (this.j == 0) {
                drawable.setAlpha(i);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        c cVar = this.a;
        if (cVar.C != z) {
            cVar.C = z;
            Drawable drawable = this.c;
            if (drawable != null) {
                q.h.j(drawable, z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        c cVar = this.a;
        cVar.E = true;
        if (cVar.D != colorFilter) {
            cVar.D = colorFilter;
            Drawable drawable = this.c;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        c cVar = this.a;
        if (cVar.x != z) {
            cVar.x = z;
            Drawable drawable = this.c;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        Drawable drawable = this.c;
        if (drawable != null) {
            q.h.k(drawable, f, f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.b;
        if (rect == null) {
            this.b = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.c;
        if (drawable != null) {
            q.h.l(drawable, i, i2, i3, i4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        c cVar = this.a;
        cVar.H = true;
        if (cVar.F != colorStateList) {
            cVar.F = colorStateList;
            q.h.o(this.c, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.a;
        cVar.I = true;
        if (cVar.G != mode) {
            cVar.G = mode;
            q.h.p(this.c, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.d;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.c || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
