package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.resources.R$drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class e2 {
    private static e2 i;
    private WeakHashMap a;
    private androidx.collection.g b;
    private androidx.collection.h c;
    private final WeakHashMap d = new WeakHashMap(0);
    private TypedValue e;
    private boolean f;
    private e g;
    private static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    private static final c j = new c(6);

    static class a implements d {
        a() {
        }

        @Override // androidx.appcompat.widget.e2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return androidx.appcompat.graphics.drawable.c.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    private static class b implements d {
        b() {
        }

        @Override // androidx.appcompat.widget.e2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return androidx.vectordrawable.graphics.drawable.g.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    private static class c extends androidx.collection.e {
        public c(int i) {
            super(i);
        }

        private static int b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter c(int i, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(b(i, mode)));
        }

        PorterDuffColorFilter d(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(b(i, mode)), porterDuffColorFilter);
        }
    }

    private interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    interface e {
        Drawable a(e2 e2Var, Context context, int i);

        ColorStateList b(Context context, int i);

        boolean c(Context context, int i, Drawable drawable);

        PorterDuff.Mode d(int i);

        boolean e(Context context, int i, Drawable drawable);
    }

    private static class f implements d {
        f() {
        }

        @Override // androidx.appcompat.widget.e2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return androidx.vectordrawable.graphics.drawable.n.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    private void a(String str, d dVar) {
        if (this.b == null) {
            this.b = new androidx.collection.g();
        }
        this.b.put(str, dVar);
    }

    private synchronized boolean b(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        androidx.collection.d dVar = (androidx.collection.d) this.d.get(context);
        if (dVar == null) {
            dVar = new androidx.collection.d();
            this.d.put(context, dVar);
        }
        dVar.j(j2, new WeakReference(constantState));
        return true;
    }

    private void c(Context context, int i2, ColorStateList colorStateList) {
        if (this.a == null) {
            this.a = new WeakHashMap();
        }
        androidx.collection.h hVar = (androidx.collection.h) this.a.get(context);
        if (hVar == null) {
            hVar = new androidx.collection.h();
            this.a.put(context, hVar);
        }
        hVar.a(i2, colorStateList);
    }

    private void d(Context context) {
        if (this.f) {
            return;
        }
        this.f = true;
        Drawable drawableJ = j(context, R$drawable.abc_vector_test);
        if (drawableJ == null || !q(drawableJ)) {
            this.f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static long e(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable f(Context context, int i2) {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        context.getResources().getValue(i2, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        e eVar = this.g;
        Drawable drawableA = eVar == null ? null : eVar.a(this, context, i2);
        if (drawableA != null) {
            drawableA.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jE, drawableA);
        }
        return drawableA;
    }

    private static PorterDuffColorFilter g(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return l(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized e2 h() {
        if (i == null) {
            e2 e2Var = new e2();
            i = e2Var;
            p(e2Var);
        }
        return i;
    }

    private synchronized Drawable i(Context context, long j2) {
        androidx.collection.d dVar = (androidx.collection.d) this.d.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) dVar.f(j2);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.k(j2);
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter l(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterC;
        c cVar = j;
        porterDuffColorFilterC = cVar.c(i2, mode);
        if (porterDuffColorFilterC == null) {
            porterDuffColorFilterC = new PorterDuffColorFilter(i2, mode);
            cVar.d(i2, mode, porterDuffColorFilterC);
        }
        return porterDuffColorFilterC;
    }

    private ColorStateList n(Context context, int i2) {
        androidx.collection.h hVar;
        WeakHashMap weakHashMap = this.a;
        if (weakHashMap == null || (hVar = (androidx.collection.h) weakHashMap.get(context)) == null) {
            return null;
        }
        return (ColorStateList) hVar.e(i2);
    }

    private static void p(e2 e2Var) {
        if (Build.VERSION.SDK_INT < 24) {
            e2Var.a("vector", new f());
            e2Var.a("animated-vector", new b());
            e2Var.a("animated-selector", new a());
        }
    }

    private static boolean q(Drawable drawable) {
        return (drawable instanceof androidx.vectordrawable.graphics.drawable.n) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable r(Context context, int i2) {
        int next;
        androidx.collection.g gVar = this.b;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        androidx.collection.h hVar = this.c;
        if (hVar != null) {
            String str = (String) hVar.e(i2);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.b.get(str) == null)) {
                return null;
            }
        } else {
            this.c = new androidx.collection.h();
        }
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.c.a(i2, name);
                d dVar = (d) this.b.get(name);
                if (dVar != null) {
                    drawableI = dVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableI != null) {
                    drawableI.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, jE, drawableI);
                }
            } catch (Exception e2) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e2);
            }
        }
        if (drawableI == null) {
            this.c.a(i2, "appcompat_skip_skip");
        }
        return drawableI;
    }

    private Drawable v(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList colorStateListM = m(context, i2);
        if (colorStateListM == null) {
            e eVar = this.g;
            if ((eVar == null || !eVar.e(context, i2, drawable)) && !x(context, i2, drawable) && z) {
                return null;
            }
            return drawable;
        }
        if (o1.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableR = q.h.r(drawable);
        q.h.o(drawableR, colorStateListM);
        PorterDuff.Mode modeO = o(i2);
        if (modeO == null) {
            return drawableR;
        }
        q.h.p(drawableR, modeO);
        return drawableR;
    }

    static void w(Drawable drawable, p2 p2Var, int[] iArr) {
        if (!o1.a(drawable) || drawable.mutate() == drawable) {
            boolean z = p2Var.d;
            if (z || p2Var.c) {
                drawable.setColorFilter(g(z ? p2Var.a : null, p2Var.c ? p2Var.b : h, iArr));
            } else {
                drawable.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }

    public synchronized Drawable j(Context context, int i2) {
        return k(context, i2, false);
    }

    synchronized Drawable k(Context context, int i2, boolean z) {
        Drawable drawableR;
        d(context);
        drawableR = r(context, i2);
        if (drawableR == null) {
            drawableR = f(context, i2);
        }
        if (drawableR == null) {
            drawableR = androidx.core.content.a.getDrawable(context, i2);
        }
        if (drawableR != null) {
            drawableR = v(context, i2, z, drawableR);
        }
        if (drawableR != null) {
            o1.b(drawableR);
        }
        return drawableR;
    }

    synchronized ColorStateList m(Context context, int i2) {
        ColorStateList colorStateListN;
        colorStateListN = n(context, i2);
        if (colorStateListN == null) {
            e eVar = this.g;
            colorStateListN = eVar == null ? null : eVar.b(context, i2);
            if (colorStateListN != null) {
                c(context, i2, colorStateListN);
            }
        }
        return colorStateListN;
    }

    PorterDuff.Mode o(int i2) {
        e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        return eVar.d(i2);
    }

    public synchronized void s(Context context) {
        androidx.collection.d dVar = (androidx.collection.d) this.d.get(context);
        if (dVar != null) {
            dVar.b();
        }
    }

    synchronized Drawable t(Context context, x2 x2Var, int i2) {
        Drawable drawableR = r(context, i2);
        if (drawableR == null) {
            drawableR = x2Var.c(i2);
        }
        if (drawableR == null) {
            return null;
        }
        return v(context, i2, false, drawableR);
    }

    public synchronized void u(e eVar) {
        this.g = eVar;
    }

    boolean x(Context context, int i2, Drawable drawable) {
        e eVar = this.g;
        return eVar != null && eVar.c(context, i2, drawable);
    }
}
