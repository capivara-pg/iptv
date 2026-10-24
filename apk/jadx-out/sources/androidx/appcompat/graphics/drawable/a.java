package androidx.appcompat.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.resources.R$styleable;
import androidx.appcompat.widget.w1;
import androidx.collection.h;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import t.i;
import v.p;

/* JADX INFO: loaded from: classes.dex */
public class a extends androidx.appcompat.graphics.drawable.f implements p {
    private c o;
    private g p;
    private int q;
    private int r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f6s;

    private static class b extends g {
        private final Animatable a;

        b(Animatable animatable) {
            super();
            this.a = animatable;
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public void c() {
            this.a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public void d() {
            this.a.stop();
        }
    }

    static class c extends androidx.appcompat.graphics.drawable.f.a {
        androidx.collection.d K;
        h L;

        c(c cVar, a aVar, Resources resources) {
            super(cVar, aVar, resources);
            if (cVar != null) {
                this.K = cVar.K;
                this.L = cVar.L;
            } else {
                this.K = new androidx.collection.d();
                this.L = new h();
            }
        }

        private static long D(int i, int i2) {
            return ((long) i2) | (((long) i) << 32);
        }

        int B(int[] iArr, Drawable drawable, int i) {
            int iZ = super.z(iArr, drawable);
            this.L.i(iZ, Integer.valueOf(i));
            return iZ;
        }

        int C(int i, int i2, Drawable drawable, boolean z) {
            int iA = super.a(drawable);
            long jD = D(i, i2);
            long j = z ? 8589934592L : 0L;
            long j2 = iA;
            this.K.a(jD, Long.valueOf(j2 | j));
            if (z) {
                this.K.a(D(i2, i), Long.valueOf(4294967296L | j2 | j));
            }
            return iA;
        }

        int E(int i) {
            if (i < 0) {
                return 0;
            }
            return ((Integer) this.L.f(i, 0)).intValue();
        }

        int F(int[] iArr) {
            int iA = super.A(iArr);
            return iA >= 0 ? iA : super.A(StateSet.WILD_CARD);
        }

        int G(int i, int i2) {
            return (int) ((Long) this.K.g(D(i, i2), -1L)).longValue();
        }

        boolean H(int i, int i2) {
            return (((Long) this.K.g(D(i, i2), -1L)).longValue() & 4294967296L) != 0;
        }

        boolean I(int i, int i2) {
            return (((Long) this.K.g(D(i, i2), -1L)).longValue() & 8589934592L) != 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new a(this, null);
        }

        @Override // androidx.appcompat.graphics.drawable.f.a, androidx.appcompat.graphics.drawable.d.c
        void r() {
            this.K = this.K.clone();
            this.L = this.L.clone();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }
    }

    private static class d extends g {
        private final androidx.vectordrawable.graphics.drawable.c a;

        d(androidx.vectordrawable.graphics.drawable.c cVar) {
            super();
            this.a = cVar;
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public void c() {
            this.a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public void d() {
            this.a.stop();
        }
    }

    private static class e extends g {
        private final ObjectAnimator a;
        private final boolean b;

        e(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            f fVar = new f(animationDrawable, z);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            objectAnimatorOfInt.setAutoCancel(true);
            objectAnimatorOfInt.setDuration(fVar.a());
            objectAnimatorOfInt.setInterpolator(fVar);
            this.b = z2;
            this.a = objectAnimatorOfInt;
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public boolean a() {
            return this.b;
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public void b() {
            this.a.reverse();
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public void c() {
            this.a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.a.g
        public void d() {
            this.a.cancel();
        }
    }

    private static class f implements TimeInterpolator {
        private int[] a;
        private int b;
        private int c;

        f(AnimationDrawable animationDrawable, boolean z) {
            b(animationDrawable, z);
        }

        int a() {
            return this.c;
        }

        int b(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.b = numberOfFrames;
            int[] iArr = this.a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.a = new int[numberOfFrames];
            }
            int[] iArr2 = this.a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.c = i;
            return i;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            int i = (int) ((f * this.c) + 0.5f);
            int i2 = this.b;
            int[] iArr = this.a;
            int i3 = 0;
            while (i3 < i2) {
                int i4 = iArr[i3];
                if (i < i4) {
                    break;
                }
                i -= i4;
                i3++;
            }
            return (i3 / i2) + (i3 < i2 ? i / this.c : 0.0f);
        }
    }

    private static abstract class g {
        private g() {
        }

        public boolean a() {
            return false;
        }

        public void b() {
        }

        public abstract void c();

        public abstract void d();
    }

    public a() {
        this(null, null);
    }

    public static a l(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            a aVar = new a();
            aVar.m(context, resources, xmlPullParser, attributeSet, theme);
            return aVar;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    private void n(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                return;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals("item")) {
                    p(context, resources, xmlPullParser, attributeSet, theme);
                } else if (xmlPullParser.getName().equals("transition")) {
                    q(context, resources, xmlPullParser, attributeSet, theme);
                }
            }
        }
    }

    private void o() {
        onStateChange(getState());
    }

    private int p(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayS = i.s(resources, theme, attributeSet, R$styleable.b);
        int resourceId = typedArrayS.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = typedArrayS.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable drawableJ = resourceId2 > 0 ? w1.h().j(context, resourceId2) : null;
        typedArrayS.recycle();
        int[] iArrJ = j(attributeSet);
        if (drawableJ == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableJ = xmlPullParser.getName().equals("vector") ? androidx.vectordrawable.graphics.drawable.h.c(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
        }
        if (drawableJ != null) {
            return this.o.B(iArrJ, drawableJ, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    private int q(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayS = i.s(resources, theme, attributeSet, R$styleable.c);
        int resourceId = typedArrayS.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = typedArrayS.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = typedArrayS.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable drawableJ = resourceId3 > 0 ? w1.h().j(context, resourceId3) : null;
        boolean z = typedArrayS.getBoolean(R$styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        typedArrayS.recycle();
        if (drawableJ == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableJ = xmlPullParser.getName().equals("animated-vector") ? androidx.vectordrawable.graphics.drawable.c.a(context, resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
        }
        if (drawableJ == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.o.C(resourceId, resourceId2, drawableJ, z);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
    }

    private boolean r(int i) {
        int iC;
        int iG;
        g bVar;
        g gVar = this.p;
        if (gVar == null) {
            iC = c();
        } else {
            if (i == this.q) {
                return true;
            }
            if (i == this.r && gVar.a()) {
                gVar.b();
                this.q = this.r;
                this.r = i;
                return true;
            }
            iC = this.q;
            gVar.d();
        }
        this.p = null;
        this.r = -1;
        this.q = -1;
        c cVar = this.o;
        int iE = cVar.E(iC);
        int iE2 = cVar.E(i);
        if (iE2 == 0 || iE == 0 || (iG = cVar.G(iE, iE2)) < 0) {
            return false;
        }
        boolean zI = cVar.I(iE, iE2);
        g(iG);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            bVar = new e((AnimationDrawable) current, cVar.H(iE, iE2), zI);
        } else {
            if (!(current instanceof androidx.vectordrawable.graphics.drawable.c)) {
                if (current instanceof Animatable) {
                    bVar = new b((Animatable) current);
                }
                return false;
            }
            bVar = new d((androidx.vectordrawable.graphics.drawable.c) current);
        }
        bVar.c();
        this.p = bVar;
        this.r = iC;
        this.q = i;
        return true;
    }

    private void s(TypedArray typedArray) {
        c cVar = this.o;
        cVar.d |= typedArray.getChangingConfigurations();
        cVar.x(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_variablePadding, cVar.i));
        cVar.t(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_constantSize, cVar.l));
        cVar.u(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, cVar.A));
        cVar.v(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, cVar.B));
        setDither(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_dither, cVar.x));
    }

    @Override // androidx.appcompat.graphics.drawable.f, androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // androidx.appcompat.graphics.drawable.f, androidx.appcompat.graphics.drawable.d
    void h(androidx.appcompat.graphics.drawable.d.c cVar) {
        super.h(cVar);
        if (cVar instanceof c) {
            this.o = (c) cVar;
        }
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        g gVar = this.p;
        if (gVar != null) {
            gVar.d();
            this.p = null;
            g(this.q);
            this.q = -1;
            this.r = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.d
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public c b() {
        return new c(this.o, this, null);
    }

    public void m(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayS = i.s(resources, theme, attributeSet, R$styleable.a);
        setVisible(typedArrayS.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        s(typedArrayS);
        i(resources);
        typedArrayS.recycle();
        n(context, resources, xmlPullParser, attributeSet, theme);
        o();
    }

    @Override // androidx.appcompat.graphics.drawable.f, androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f6s && super.mutate() == this) {
            this.o.r();
            this.f6s = true;
        }
        return this;
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    @Override // androidx.appcompat.graphics.drawable.f, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int iF = this.o.F(iArr);
        boolean z = iF != c() && (r(iF) || g(iF));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        g gVar = this.p;
        if (gVar != null && (visible || z2)) {
            if (z) {
                gVar.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    @Override // androidx.appcompat.graphics.drawable.d, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    a(c cVar, Resources resources) {
        super(null);
        this.q = -1;
        this.r = -1;
        h(new c(cVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }
}
