package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class y1 implements androidx.appcompat.view.menu.p {
    private static Method H;
    private static Method I;
    private static Method J;
    private final c A;
    private Runnable B;
    final Handler C;
    private final Rect D;
    private Rect E;
    private boolean F;
    PopupWindow G;
    private Context a;
    private ListAdapter b;
    r1 c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;
    private boolean n;
    private boolean o;
    int p;
    private View q;
    private int r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private DataSetObserver f18s;
    private View t;
    private Drawable u;
    private AdapterView.OnItemClickListener v;
    private AdapterView.OnItemSelectedListener w;
    final g x;
    private final f y;
    private final e z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewQ = y1.this.q();
            if (viewQ == null || viewQ.getWindowToken() == null) {
                return;
            }
            y1.this.show();
        }
    }

    class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            r1 r1Var;
            if (i == -1 || (r1Var = y1.this.c) == null) {
                return;
            }
            r1Var.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    private class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            y1.this.o();
        }
    }

    private class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (y1.this.isShowing()) {
                y1.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            y1.this.dismiss();
        }
    }

    private class e implements AbsListView.OnScrollListener {
        e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || y1.this.t() || y1.this.G.getContentView() == null) {
                return;
            }
            y1 y1Var = y1.this;
            y1Var.C.removeCallbacks(y1Var.x);
            y1.this.x.run();
        }
    }

    private class f implements View.OnTouchListener {
        f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = y1.this.G) != null && popupWindow.isShowing() && x >= 0 && x < y1.this.G.getWidth() && y >= 0 && y < y1.this.G.getHeight()) {
                y1 y1Var = y1.this;
                y1Var.C.postDelayed(y1Var.x, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            y1 y1Var2 = y1.this;
            y1Var2.C.removeCallbacks(y1Var2.x);
            return false;
        }
    }

    private class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r1 r1Var = y1.this.c;
            if (r1Var == null || !androidx.core.view.c1.P(r1Var) || y1.this.c.getCount() <= y1.this.c.getChildCount()) {
                return;
            }
            int childCount = y1.this.c.getChildCount();
            y1 y1Var = y1.this;
            if (childCount <= y1Var.p) {
                y1Var.G.setInputMethodMode(2);
                y1.this.show();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                H = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            try {
                J = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                I = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
            }
        }
    }

    public y1(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    private void G(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.G.setIsClippedToScreen(z);
            return;
        }
        Method method = H;
        if (method != null) {
            try {
                method.invoke(this.G, Boolean.valueOf(z));
            } catch (Exception unused) {
            }
        }
    }

    private int n() {
        int measuredHeight;
        int i;
        int iMakeMeasureSpec;
        View view;
        int i2;
        if (this.c == null) {
            Context context = this.a;
            this.B = new a();
            r1 r1VarP = p(context, !this.F);
            this.c = r1VarP;
            Drawable drawable = this.u;
            if (drawable != null) {
                r1VarP.setSelector(drawable);
            }
            this.c.setAdapter(this.b);
            this.c.setOnItemClickListener(this.v);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new b());
            this.c.setOnScrollListener(this.z);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.w;
            if (onItemSelectedListener != null) {
                this.c.setOnItemSelectedListener(onItemSelectedListener);
            }
            r1 r1Var = this.c;
            View view2 = this.q;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i3 = this.r;
                if (i3 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(r1Var, layoutParams);
                } else if (i3 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.r);
                } else {
                    linearLayout.addView(r1Var, layoutParams);
                    linearLayout.addView(view2);
                }
                int i4 = this.e;
                if (i4 >= 0) {
                    i2 = Integer.MIN_VALUE;
                } else {
                    i4 = 0;
                    i2 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i4, i2), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = r1Var;
            }
            this.G.setContentView(view);
        } else {
            View view3 = this.q;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.G.getBackground();
        if (background != null) {
            background.getPadding(this.D);
            Rect rect = this.D;
            int i5 = rect.top;
            i = rect.bottom + i5;
            if (!this.i) {
                this.g = -i5;
            }
        } else {
            this.D.setEmpty();
            i = 0;
        }
        int iR = r(q(), this.g, this.G.getInputMethodMode() == 2);
        if (this.n || this.d == -1) {
            return iR + i;
        }
        int i6 = this.e;
        if (i6 == -2) {
            int i7 = this.a.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.D;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i6 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
        } else {
            int i8 = this.a.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.D;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect3.left + rect3.right), 1073741824);
        }
        int iD = this.c.d(iMakeMeasureSpec, 0, -1, iR - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i + this.c.getPaddingTop() + this.c.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    private int r(View view, int i, boolean z) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.G.getMaxAvailableHeight(view, i, z);
        }
        Method method = I;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.G, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
            }
        }
        return this.G.getMaxAvailableHeight(view, i);
    }

    private void v() {
        View view = this.q;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.q);
            }
        }
    }

    public void A(Rect rect) {
        this.E = rect != null ? new Rect(rect) : null;
    }

    public void B(int i) {
        this.G.setInputMethodMode(i);
    }

    public void C(boolean z) {
        this.F = z;
        this.G.setFocusable(z);
    }

    public void D(PopupWindow.OnDismissListener onDismissListener) {
        this.G.setOnDismissListener(onDismissListener);
    }

    public void E(AdapterView.OnItemClickListener onItemClickListener) {
        this.v = onItemClickListener;
    }

    public void F(boolean z) {
        this.l = true;
        this.k = z;
    }

    public void H(int i) {
        this.r = i;
    }

    public void I(int i) {
        r1 r1Var = this.c;
        if (!isShowing() || r1Var == null) {
            return;
        }
        r1Var.setListSelectionHidden(false);
        r1Var.setSelection(i);
        if (r1Var.getChoiceMode() != 0) {
            r1Var.setItemChecked(i, true);
        }
    }

    public void J(int i) {
        this.e = i;
    }

    public int a() {
        return this.f;
    }

    public void c(int i) {
        this.f = i;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        this.G.dismiss();
        v();
        this.G.setContentView(null);
        this.c = null;
        this.C.removeCallbacks(this.x);
    }

    public Drawable e() {
        return this.G.getBackground();
    }

    public void g(int i) {
        this.g = i;
        this.i = true;
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean isShowing() {
        return this.G.isShowing();
    }

    public int j() {
        if (this.i) {
            return this.g;
        }
        return 0;
    }

    public void k(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f18s;
        if (dataSetObserver == null) {
            this.f18s = new d();
        } else {
            ListAdapter listAdapter2 = this.b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f18s);
        }
        r1 r1Var = this.c;
        if (r1Var != null) {
            r1Var.setAdapter(this.b);
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView m() {
        return this.c;
    }

    public void o() {
        r1 r1Var = this.c;
        if (r1Var != null) {
            r1Var.setListSelectionHidden(true);
            r1Var.requestLayout();
        }
    }

    r1 p(Context context, boolean z) {
        return new r1(context, z);
    }

    public View q() {
        return this.t;
    }

    public int s() {
        return this.e;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.G.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        int iN = n();
        boolean zT = t();
        androidx.core.widget.t.b(this.G, this.h);
        if (this.G.isShowing()) {
            if (androidx.core.view.c1.P(q())) {
                int width = this.e;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = q().getWidth();
                }
                int i = this.d;
                if (i == -1) {
                    if (!zT) {
                        iN = -1;
                    }
                    if (zT) {
                        this.G.setWidth(this.e == -1 ? -1 : 0);
                        this.G.setHeight(0);
                    } else {
                        this.G.setWidth(this.e == -1 ? -1 : 0);
                        this.G.setHeight(-1);
                    }
                } else if (i != -2) {
                    iN = i;
                }
                this.G.setOutsideTouchable((this.o || this.n) ? false : true);
                this.G.update(q(), this.f, this.g, width < 0 ? -1 : width, iN < 0 ? -1 : iN);
                return;
            }
            return;
        }
        int width2 = this.e;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = q().getWidth();
        }
        int i2 = this.d;
        if (i2 == -1) {
            iN = -1;
        } else if (i2 != -2) {
            iN = i2;
        }
        this.G.setWidth(width2);
        this.G.setHeight(iN);
        G(true);
        this.G.setOutsideTouchable((this.o || this.n) ? false : true);
        this.G.setTouchInterceptor(this.y);
        if (this.l) {
            androidx.core.widget.t.a(this.G, this.k);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = J;
            if (method != null) {
                try {
                    method.invoke(this.G, this.E);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.G.setEpicenterBounds(this.E);
        }
        androidx.core.widget.t.c(this.G, q(), this.f, this.g, this.m);
        this.c.setSelection(-1);
        if (!this.F || this.c.isInTouchMode()) {
            o();
        }
        if (this.F) {
            return;
        }
        this.C.post(this.A);
    }

    public boolean t() {
        return this.G.getInputMethodMode() == 2;
    }

    public boolean u() {
        return this.F;
    }

    public void w(View view) {
        this.t = view;
    }

    public void x(int i) {
        this.G.setAnimationStyle(i);
    }

    public void y(int i) {
        Drawable background = this.G.getBackground();
        if (background == null) {
            J(i);
            return;
        }
        background.getPadding(this.D);
        Rect rect = this.D;
        this.e = rect.left + rect.right + i;
    }

    public void z(int i) {
        this.m = i;
    }

    public y1(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public y1(Context context, AttributeSet attributeSet, int i, int i2) {
        this.d = -2;
        this.e = -2;
        this.h = 1002;
        this.j = true;
        this.m = 0;
        this.n = false;
        this.o = false;
        this.p = Integer.MAX_VALUE;
        this.r = 0;
        this.x = new g();
        this.y = new f();
        this.z = new e();
        this.A = new c();
        this.D = new Rect();
        this.a = context;
        this.C = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.z, i, i2);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.i = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        s sVar = new s(context, attributeSet, i, i2);
        this.G = sVar;
        sVar.setInputMethodMode(1);
    }
}
