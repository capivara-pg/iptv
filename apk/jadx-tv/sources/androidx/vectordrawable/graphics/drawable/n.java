package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class n extends m {
    static final PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;
    private h b;
    private PorterDuffColorFilter c;
    private ColorFilter d;
    private boolean e;
    private boolean f;
    private Drawable.ConstantState g;
    private final float[] h;
    private final Matrix i;
    private final Rect j;

    private static class b extends f {
        b() {
        }

        private void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.a = p.d.d(string2);
            }
            this.c = o.i.g(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        @Override // androidx.vectordrawable.graphics.drawable.n.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (o.i.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = o.i.k(resources, theme, attributeSet, androidx.vectordrawable.graphics.drawable.a.d);
                f(typedArrayK, xmlPullParser);
                typedArrayK.recycle();
            }
        }

        b(b bVar) {
            super(bVar);
        }
    }

    private static abstract class e {
        private e() {
        }

        public boolean a() {
            return false;
        }

        public boolean b(int[] iArr) {
            return false;
        }
    }

    private static class h extends Drawable.ConstantState {
        int a;
        g b;
        ColorStateList c;
        PorterDuff.Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        PorterDuff.Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public h(h hVar) {
            this.c = null;
            this.d = n.k;
            if (hVar != null) {
                this.a = hVar.a;
                g gVar = new g(hVar.b);
                this.b = gVar;
                if (hVar.b.e != null) {
                    gVar.e = new Paint(hVar.b.e);
                }
                if (hVar.b.d != null) {
                    this.b.d = new Paint(hVar.b.d);
                }
                this.c = hVar.c;
                this.d = hVar.d;
                this.e = hVar.e;
            }
        }

        public boolean a(int i, int i2) {
            return i == this.f.getWidth() && i2 == this.f.getHeight();
        }

        public boolean b() {
            return !this.k && this.g == this.c && this.h == this.d && this.j == this.e && this.i == this.b.getRootAlpha();
        }

        public void c(int i, int i2) {
            if (this.f == null || !a(i, i2)) {
                this.f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, e(colorFilter));
        }

        public Paint e(ColorFilter colorFilter) {
            if (!f() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                Paint paint = new Paint();
                this.l = paint;
                paint.setFilterBitmap(true);
            }
            this.l.setAlpha(this.b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean f() {
            return this.b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.b.g(iArr);
            this.k |= zG;
            return zG;
        }

        public void i() {
            this.g = this.c;
            this.h = this.d;
            this.i = this.b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }

        public void j(int i, int i2) {
            this.f.eraseColor(0);
            this.b.b(new Canvas(this.f), i, i2, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new n(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new n(this);
        }

        public h() {
            this.c = null;
            this.d = n.k;
            this.b = new g();
        }
    }

    n() {
        this.f = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.b = new h();
    }

    static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static n b(Resources resources, int i2, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            n nVar = new n();
            nVar.a = o.h.d(resources, i2, theme);
            nVar.g = new i(nVar.a.getConstantState());
            return nVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i2);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return c(resources, xml, attributeSetAsAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
            return null;
        }
    }

    public static n c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        n nVar = new n();
        nVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return nVar;
    }

    private void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        h hVar = this.b;
        g gVar = hVar.b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.h);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if ("path".equals(name)) {
                    c cVar = new c();
                    cVar.g(resources, attributeSet, theme, xmlPullParser);
                    dVar.b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.p.put(cVar.getPathName(), cVar);
                    }
                    hVar.a = cVar.d | hVar.a;
                    z = false;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.p.put(bVar.getPathName(), bVar);
                    }
                    hVar.a = bVar.d | hVar.a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.p.put(dVar2.getGroupName(), dVar2);
                    }
                    hVar.a = dVar2.k | hVar.a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    private boolean f() {
        return isAutoMirrored() && q.h.f(this) == 1;
    }

    private static PorterDuff.Mode g(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    private void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        h hVar = this.b;
        g gVar = hVar.b;
        hVar.d = g(o.i.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = o.i.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            hVar.c = colorStateListC;
        }
        hVar.e = o.i.a(typedArray, xmlPullParser, "autoMirrored", 5, hVar.e);
        gVar.k = o.i.f(typedArray, xmlPullParser, "viewportWidth", 7, gVar.k);
        float f2 = o.i.f(typedArray, xmlPullParser, "viewportHeight", 8, gVar.l);
        gVar.l = f2;
        if (gVar.k <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f2 <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        gVar.i = typedArray.getDimension(3, gVar.i);
        float dimension = typedArray.getDimension(2, gVar.j);
        gVar.j = dimension;
        if (gVar.i <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        gVar.setAlpha(o.i.f(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.n = string;
            gVar.p.put(string, gVar);
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.a;
        if (drawable == null) {
            return false;
        }
        q.h.b(drawable);
        return false;
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    Object d(String str) {
        return this.b.b.p.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.j);
        if (this.j.width() <= 0 || this.j.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.d;
        if (colorFilter == null) {
            colorFilter = this.c;
        }
        canvas.getMatrix(this.i);
        this.i.getValues(this.h);
        float fAbs = Math.abs(this.h[0]);
        float fAbs2 = Math.abs(this.h[4]);
        float fAbs3 = Math.abs(this.h[1]);
        float fAbs4 = Math.abs(this.h[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iWidth = (int) (this.j.width() * fAbs);
        int iHeight = (int) (this.j.height() * fAbs2);
        int iMin = Math.min(RecyclerView.ItemAnimator.FLAG_MOVED, iWidth);
        int iMin2 = Math.min(RecyclerView.ItemAnimator.FLAG_MOVED, iHeight);
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.j;
        canvas.translate(rect.left, rect.top);
        if (f()) {
            canvas.translate(this.j.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.j.offsetTo(0, 0);
        this.b.c(iMin, iMin2);
        if (!this.f) {
            this.b.j(iMin, iMin2);
        } else if (!this.b.b()) {
            this.b.j(iMin, iMin2);
            this.b.i();
        }
        this.b.d(canvas, colorFilter, this.j);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.a;
        return drawable != null ? q.h.d(drawable) : this.b.b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.b.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.a;
        return drawable != null ? q.h.e(drawable) : this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.a != null && Build.VERSION.SDK_INT >= 24) {
            return new i(this.a.getConstantState());
        }
        this.b.a = getChangingConfigurations();
        return this.b;
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.a;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.b.b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.a;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.b.b.i;
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    void h(boolean z) {
        this.f = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.a;
        return drawable != null ? q.h.h(drawable) : this.b.e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        h hVar;
        ColorStateList colorStateList;
        Drawable drawable = this.a;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return super.isStateful() || ((hVar = this.b) != null && (hVar.g() || ((colorStateList = this.b.c) != null && colorStateList.isStateful())));
    }

    PorterDuffColorFilter j(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.e && super.mutate() == this) {
            this.b = new h(this.b);
            this.e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        Drawable drawable = this.a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        h hVar = this.b;
        ColorStateList colorStateList = hVar.c;
        if (colorStateList == null || (mode = hVar.d) == null) {
            z = false;
        } else {
            this.c = j(this.c, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!hVar.g() || !hVar.h(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.b.b.getRootAlpha() != i2) {
            this.b.b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.a;
        if (drawable != null) {
            q.h.j(drawable, z);
        } else {
            this.b.e = z;
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // androidx.vectordrawable.graphics.drawable.m, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        Drawable drawable = this.a;
        if (drawable != null) {
            q.h.n(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.a;
        if (drawable != null) {
            q.h.o(drawable, colorStateList);
            return;
        }
        h hVar = this.b;
        if (hVar.c != colorStateList) {
            hVar.c = colorStateList;
            this.c = j(this.c, colorStateList, hVar.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.a;
        if (drawable != null) {
            q.h.p(drawable, mode);
            return;
        }
        h hVar = this.b;
        if (hVar.d != mode) {
            hVar.d = mode;
            this.c = j(this.c, hVar.c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.a;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    private static class i extends Drawable.ConstantState {
        private final Drawable.ConstantState a;

        public i(Drawable.ConstantState constantState) {
            this.a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            n nVar = new n();
            nVar.a = o.a(this.a.newDrawable());
            return nVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            n nVar = new n();
            nVar.a = o.a(this.a.newDrawable(resources));
            return nVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            n nVar = new n();
            nVar.a = o.a(this.a.newDrawable(resources, theme));
            return nVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.d = colorFilter;
            invalidateSelf();
        }
    }

    private static abstract class f extends e {
        protected p.d.b[] a;
        String b;
        int c;
        int d;

        public f() {
            super();
            this.a = null;
            this.c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            p.d.b[] bVarArr = this.a;
            if (bVarArr != null) {
                p.d.b.e(bVarArr, path);
            }
        }

        public p.d.b[] getPathData() {
            return this.a;
        }

        public String getPathName() {
            return this.b;
        }

        public void setPathData(p.d.b[] bVarArr) {
            if (p.d.b(this.a, bVarArr)) {
                p.d.j(this.a, bVarArr);
            } else {
                this.a = p.d.f(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.a = null;
            this.c = 0;
            this.b = fVar.b;
            this.d = fVar.d;
            this.a = p.d.f(fVar.a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.a;
        if (drawable != null) {
            q.h.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        h hVar = this.b;
        hVar.b = new g();
        TypedArray typedArrayK = o.i.k(resources, theme, attributeSet, androidx.vectordrawable.graphics.drawable.a.a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        hVar.a = getChangingConfigurations();
        hVar.k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.c = j(this.c, hVar.c, hVar.d);
    }

    n(h hVar) {
        this.f = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.b = hVar;
        this.c = j(this.c, hVar.c, hVar.d);
    }

    private static class c extends f {
        private int[] e;
        o.b f;
        float g;
        o.b h;
        float i;
        float j;
        float k;
        float l;
        float m;
        Paint.Cap n;
        Paint.Join o;
        float p;

        c() {
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        private Paint.Cap e(int i, Paint.Cap cap) {
            if (i == 0) {
                return Paint.Cap.BUTT;
            }
            if (i != 1) {
                return i != 2 ? cap : Paint.Cap.SQUARE;
            }
            return Paint.Cap.ROUND;
        }

        private Paint.Join f(int i, Paint.Join join) {
            if (i == 0) {
                return Paint.Join.MITER;
            }
            if (i != 1) {
                return i != 2 ? join : Paint.Join.BEVEL;
            }
            return Paint.Join.ROUND;
        }

        private void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.e = null;
            if (o.i.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.a = p.d.d(string2);
                }
                this.h = o.i.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = o.i.f(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = e(o.i.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = f(o.i.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = o.i.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f = o.i.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.i = o.i.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.i);
                this.g = o.i.f(typedArray, xmlPullParser, "strokeWidth", 4, this.g);
                this.l = o.i.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = o.i.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = o.i.f(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.c = o.i.g(typedArray, xmlPullParser, "fillType", 13, this.c);
            }
        }

        @Override // androidx.vectordrawable.graphics.drawable.n.e
        public boolean a() {
            return this.h.i() || this.f.i();
        }

        @Override // androidx.vectordrawable.graphics.drawable.n.e
        public boolean b(int[] iArr) {
            return this.f.j(iArr) | this.h.j(iArr);
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = o.i.k(resources, theme, attributeSet, androidx.vectordrawable.graphics.drawable.a.c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        float getFillAlpha() {
            return this.j;
        }

        int getFillColor() {
            return this.h.e();
        }

        float getStrokeAlpha() {
            return this.i;
        }

        int getStrokeColor() {
            return this.f.e();
        }

        float getStrokeWidth() {
            return this.g;
        }

        float getTrimPathEnd() {
            return this.l;
        }

        float getTrimPathOffset() {
            return this.m;
        }

        float getTrimPathStart() {
            return this.k;
        }

        void setFillAlpha(float f) {
            this.j = f;
        }

        void setFillColor(int i) {
            this.h.k(i);
        }

        void setStrokeAlpha(float f) {
            this.i = f;
        }

        void setStrokeColor(int i) {
            this.f.k(i);
        }

        void setStrokeWidth(float f) {
            this.g = f;
        }

        void setTrimPathEnd(float f) {
            this.l = f;
        }

        void setTrimPathOffset(float f) {
            this.m = f;
        }

        void setTrimPathStart(float f) {
            this.k = f;
        }

        c(c cVar) {
            super(cVar);
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.i = cVar.i;
            this.h = cVar.h;
            this.c = cVar.c;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
        }
    }

    private static class g {
        private static final Matrix q = new Matrix();
        private final Path a;
        private final Path b;
        private final Matrix c;
        Paint d;
        Paint e;
        private PathMeasure f;
        private int g;
        final d h;
        float i;
        float j;
        float k;
        float l;
        int m;
        String n;
        Boolean o;
        final androidx.collection.a p;

        public g() {
            this.c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            this.p = new androidx.collection.a();
            this.h = new d();
            this.a = new Path();
            this.b = new Path();
        }

        private static float a(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        private void c(d dVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            dVar.a.set(matrix);
            dVar.a.preConcat(dVar.j);
            canvas.save();
            for (int i3 = 0; i3 < dVar.b.size(); i3++) {
                e eVar = (e) dVar.b.get(i3);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.a, canvas, i, i2, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        private void d(d dVar, f fVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f = i / this.k;
            float f2 = i2 / this.l;
            float fMin = Math.min(f, f2);
            Matrix matrix = dVar.a;
            this.c.set(matrix);
            this.c.postScale(f, f2);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.a);
            Path path = this.a;
            this.b.reset();
            if (fVar.c()) {
                this.b.setFillType(fVar.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.b.addPath(path, this.c);
                canvas.clipPath(this.b);
                return;
            }
            c cVar = (c) fVar;
            float f3 = cVar.k;
            if (f3 != 0.0f || cVar.l != 1.0f) {
                float f4 = cVar.m;
                float f5 = (f3 + f4) % 1.0f;
                float f6 = (cVar.l + f4) % 1.0f;
                if (this.f == null) {
                    this.f = new PathMeasure();
                }
                this.f.setPath(this.a, false);
                float length = this.f.getLength();
                float f7 = f5 * length;
                float f8 = f6 * length;
                path.reset();
                if (f7 > f8) {
                    this.f.getSegment(f7, length, path, true);
                    this.f.getSegment(0.0f, f8, path, true);
                } else {
                    this.f.getSegment(f7, f8, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.b.addPath(path, this.c);
            if (cVar.h.l()) {
                o.b bVar = cVar.h;
                if (this.e == null) {
                    Paint paint = new Paint(1);
                    this.e = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.e;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.c);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(n.a(bVar.e(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.b.setFillType(cVar.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.b, paint2);
            }
            if (cVar.f.l()) {
                o.b bVar2 = cVar.f;
                if (this.d == null) {
                    Paint paint3 = new Paint(1);
                    this.d = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.d;
                Paint.Join join = cVar.o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(cVar.p);
                if (bVar2.h()) {
                    Shader shaderF2 = bVar2.f();
                    shaderF2.setLocalMatrix(this.c);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(n.a(bVar2.e(), cVar.i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.g * fMin * fE);
                canvas.drawPath(this.b, paint4);
            }
        }

        private float e(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float fHypot = (float) Math.hypot(fArr[0], fArr[1]);
            float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float fA = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float fMax = Math.max(fHypot, fHypot2);
            if (fMax > 0.0f) {
                return Math.abs(fA) / fMax;
            }
            return 0.0f;
        }

        public void b(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            c(this.h, q, canvas, i, i2, colorFilter);
        }

        public boolean f() {
            if (this.o == null) {
                this.o = Boolean.valueOf(this.h.a());
            }
            return this.o.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.h.b(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.m;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.m = i;
        }

        public g(g gVar) {
            this.c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            androidx.collection.a aVar = new androidx.collection.a();
            this.p = aVar;
            this.h = new d(gVar.h, aVar);
            this.a = new Path(gVar.a);
            this.b = new Path(gVar.b);
            this.i = gVar.i;
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.g = gVar.g;
            this.m = gVar.m;
            this.n = gVar.n;
            String str = gVar.n;
            if (str != null) {
                aVar.put(str, this);
            }
            this.o = gVar.o;
        }
    }

    private static class d extends e {
        final Matrix a;
        final ArrayList b;
        float c;
        private float d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;
        final Matrix j;
        int k;
        private int[] l;
        private String m;

        public d(d dVar, androidx.collection.a aVar) {
            f bVar;
            super();
            this.a = new Matrix();
            this.b = new ArrayList();
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            Matrix matrix = new Matrix();
            this.j = matrix;
            this.m = null;
            this.c = dVar.c;
            this.d = dVar.d;
            this.e = dVar.e;
            this.f = dVar.f;
            this.g = dVar.g;
            this.h = dVar.h;
            this.i = dVar.i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.j);
            ArrayList arrayList = dVar.b;
            for (int i = 0; i < arrayList.size(); i++) {
                Object obj = arrayList.get(i);
                if (obj instanceof d) {
                    this.b.add(new d((d) obj, aVar));
                } else {
                    if (obj instanceof c) {
                        bVar = new c((c) obj);
                    } else {
                        if (!(obj instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) obj);
                    }
                    this.b.add(bVar);
                    Object obj2 = bVar.b;
                    if (obj2 != null) {
                        aVar.put(obj2, bVar);
                    }
                }
            }
        }

        private void d() {
            this.j.reset();
            this.j.postTranslate(-this.d, -this.e);
            this.j.postScale(this.f, this.g);
            this.j.postRotate(this.c, 0.0f, 0.0f);
            this.j.postTranslate(this.h + this.d, this.i + this.e);
        }

        private void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.c = o.i.f(typedArray, xmlPullParser, "rotation", 5, this.c);
            this.d = typedArray.getFloat(1, this.d);
            this.e = typedArray.getFloat(2, this.e);
            this.f = o.i.f(typedArray, xmlPullParser, "scaleX", 3, this.f);
            this.g = o.i.f(typedArray, xmlPullParser, "scaleY", 4, this.g);
            this.h = o.i.f(typedArray, xmlPullParser, "translateX", 6, this.h);
            this.i = o.i.f(typedArray, xmlPullParser, "translateY", 7, this.i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            d();
        }

        @Override // androidx.vectordrawable.graphics.drawable.n.e
        public boolean a() {
            for (int i = 0; i < this.b.size(); i++) {
                if (((e) this.b.get(i)).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.vectordrawable.graphics.drawable.n.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i = 0; i < this.b.size(); i++) {
                zB |= ((e) this.b.get(i)).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = o.i.k(resources, theme, attributeSet, androidx.vectordrawable.graphics.drawable.a.b);
            e(typedArrayK, xmlPullParser);
            typedArrayK.recycle();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.d;
        }

        public float getPivotY() {
            return this.e;
        }

        public float getRotation() {
            return this.c;
        }

        public float getScaleX() {
            return this.f;
        }

        public float getScaleY() {
            return this.g;
        }

        public float getTranslateX() {
            return this.h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f) {
            if (f != this.d) {
                this.d = f;
                d();
            }
        }

        public void setPivotY(float f) {
            if (f != this.e) {
                this.e = f;
                d();
            }
        }

        public void setRotation(float f) {
            if (f != this.c) {
                this.c = f;
                d();
            }
        }

        public void setScaleX(float f) {
            if (f != this.f) {
                this.f = f;
                d();
            }
        }

        public void setScaleY(float f) {
            if (f != this.g) {
                this.g = f;
                d();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.h) {
                this.h = f;
                d();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.i) {
                this.i = f;
                d();
            }
        }

        public d() {
            super();
            this.a = new Matrix();
            this.b = new ArrayList();
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }
}
