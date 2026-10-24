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

/* JADX INFO: loaded from: classes.dex */
public class q1 implements androidx.appcompat.view.menu.p {
    private static Method G;
    private static Method H;
    private static Method I;
    private Runnable A;
    final Handler B;
    private final Rect C;
    private Rect D;
    private boolean E;
    PopupWindow F;
    private Context a;
    private ListAdapter b;
    j1 c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int l;
    private boolean m;
    private boolean n;
    int o;
    private View p;
    private int q;
    private DataSetObserver r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private View f17s;
    private Drawable t;
    private AdapterView.OnItemClickListener u;
    private AdapterView.OnItemSelectedListener v;
    final g w;
    private final f x;
    private final e y;
    private final c z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewQ = q1.this.q();
            if (viewQ == null || viewQ.getWindowToken() == null) {
                return;
            }
            q1.this.show();
        }
    }

    class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            j1 j1Var;
            if (i == -1 || (j1Var = q1.this.c) == null) {
                return;
            }
            j1Var.setListSelectionHidden(false);
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
            q1.this.o();
        }
    }

    private class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (q1.this.isShowing()) {
                q1.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            q1.this.dismiss();
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
            if (i != 1 || q1.this.t() || q1.this.F.getContentView() == null) {
                return;
            }
            q1 q1Var = q1.this;
            q1Var.B.removeCallbacks(q1Var.w);
            q1.this.w.run();
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
            if (action == 0 && (popupWindow = q1.this.F) != null && popupWindow.isShowing() && x >= 0 && x < q1.this.F.getWidth() && y >= 0 && y < q1.this.F.getHeight()) {
                q1 q1Var = q1.this;
                q1Var.B.postDelayed(q1Var.w, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            q1 q1Var2 = q1.this;
            q1Var2.B.removeCallbacks(q1Var2.w);
            return false;
        }
    }

    private class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j1 j1Var = q1.this.c;
            if (j1Var == null || !androidx.core.view.m0.Q(j1Var) || q1.this.c.getCount() <= q1.this.c.getChildCount()) {
                return;
            }
            int childCount = q1.this.c.getChildCount();
            q1 q1Var = q1.this;
            if (childCount <= q1Var.o) {
                q1Var.F.setInputMethodMode(2);
                q1.this.show();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                G = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            try {
                I = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                H = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
            }
        }
    }

    public q1(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    private void G(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.F.setIsClippedToScreen(z);
            return;
        }
        Method method = G;
        if (method != null) {
            try {
                method.invoke(this.F, Boolean.valueOf(z));
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
            this.A = new a();
            j1 j1VarP = p(context, !this.E);
            this.c = j1VarP;
            Drawable drawable = this.t;
            if (drawable != null) {
                j1VarP.setSelector(drawable);
            }
            this.c.setAdapter(this.b);
            this.c.setOnItemClickListener(this.u);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new b());
            this.c.setOnScrollListener(this.y);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.v;
            if (onItemSelectedListener != null) {
                this.c.setOnItemSelectedListener(onItemSelectedListener);
            }
            j1 j1Var = this.c;
            View view2 = this.p;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i3 = this.q;
                if (i3 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(j1Var, layoutParams);
                } else if (i3 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.q);
                } else {
                    linearLayout.addView(j1Var, layoutParams);
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
                view = j1Var;
            }
            this.F.setContentView(view);
        } else {
            View view3 = this.p;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.F.getBackground();
        if (background != null) {
            background.getPadding(this.C);
            Rect rect = this.C;
            int i5 = rect.top;
            i = rect.bottom + i5;
            if (!this.i) {
                this.g = -i5;
            }
        } else {
            this.C.setEmpty();
            i = 0;
        }
        int iR = r(q(), this.g, this.F.getInputMethodMode() == 2);
        if (this.m || this.d == -1) {
            return iR + i;
        }
        int i6 = this.e;
        if (i6 == -2) {
            int i7 = this.a.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.C;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i6 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
        } else {
            int i8 = this.a.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.C;
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
            return this.F.getMaxAvailableHeight(view, i, z);
        }
        Method method = H;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.F, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
            }
        }
        return this.F.getMaxAvailableHeight(view, i);
    }

    private void v() {
        View view = this.p;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.p);
            }
        }
    }

    public void A(Rect rect) {
        this.D = rect != null ? new Rect(rect) : null;
    }

    public void B(int i) {
        this.F.setInputMethodMode(i);
    }

    public void C(boolean z) {
        this.E = z;
        this.F.setFocusable(z);
    }

    public void D(PopupWindow.OnDismissListener onDismissListener) {
        this.F.setOnDismissListener(onDismissListener);
    }

    public void E(AdapterView.OnItemClickListener onItemClickListener) {
        this.u = onItemClickListener;
    }

    public void F(boolean z) {
        this.k = true;
        this.j = z;
    }

    public void H(int i) {
        this.q = i;
    }

    public void I(int i) {
        j1 j1Var = this.c;
        if (!isShowing() || j1Var == null) {
            return;
        }
        j1Var.setListSelectionHidden(false);
        j1Var.setSelection(i);
        if (j1Var.getChoiceMode() != 0) {
            j1Var.setItemChecked(i, true);
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
        this.F.dismiss();
        v();
        this.F.setContentView(null);
        this.c = null;
        this.B.removeCallbacks(this.w);
    }

    public Drawable e() {
        return this.F.getBackground();
    }

    public void g(int i) {
        this.g = i;
        this.i = true;
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean isShowing() {
        return this.F.isShowing();
    }

    public int j() {
        if (this.i) {
            return this.g;
        }
        return 0;
    }

    public void k(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.r;
        if (dataSetObserver == null) {
            this.r = new d();
        } else {
            ListAdapter listAdapter2 = this.b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.r);
        }
        j1 j1Var = this.c;
        if (j1Var != null) {
            j1Var.setAdapter(this.b);
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView m() {
        return this.c;
    }

    public void o() {
        j1 j1Var = this.c;
        if (j1Var != null) {
            j1Var.setListSelectionHidden(true);
            j1Var.requestLayout();
        }
    }

    j1 p(Context context, boolean z) {
        return new j1(context, z);
    }

    public View q() {
        return this.f17s;
    }

    public int s() {
        return this.e;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.F.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        int iN = n();
        boolean zT = t();
        androidx.core.widget.k.b(this.F, this.h);
        if (this.F.isShowing()) {
            if (androidx.core.view.m0.Q(q())) {
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
                        this.F.setWidth(this.e == -1 ? -1 : 0);
                        this.F.setHeight(0);
                    } else {
                        this.F.setWidth(this.e == -1 ? -1 : 0);
                        this.F.setHeight(-1);
                    }
                } else if (i != -2) {
                    iN = i;
                }
                this.F.setOutsideTouchable((this.n || this.m) ? false : true);
                this.F.update(q(), this.f, this.g, width < 0 ? -1 : width, iN < 0 ? -1 : iN);
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
        this.F.setWidth(width2);
        this.F.setHeight(iN);
        G(true);
        this.F.setOutsideTouchable((this.n || this.m) ? false : true);
        this.F.setTouchInterceptor(this.x);
        if (this.k) {
            androidx.core.widget.k.a(this.F, this.j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = I;
            if (method != null) {
                try {
                    method.invoke(this.F, this.D);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.F.setEpicenterBounds(this.D);
        }
        androidx.core.widget.k.c(this.F, q(), this.f, this.g, this.l);
        this.c.setSelection(-1);
        if (!this.E || this.c.isInTouchMode()) {
            o();
        }
        if (this.E) {
            return;
        }
        this.B.post(this.z);
    }

    public boolean t() {
        return this.F.getInputMethodMode() == 2;
    }

    public boolean u() {
        return this.E;
    }

    public void w(View view) {
        this.f17s = view;
    }

    public void x(int i) {
        this.F.setAnimationStyle(i);
    }

    public void y(int i) {
        Drawable background = this.F.getBackground();
        if (background == null) {
            J(i);
            return;
        }
        background.getPadding(this.C);
        Rect rect = this.C;
        this.e = rect.left + rect.right + i;
    }

    public void z(int i) {
        this.l = i;
    }

    public q1(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public q1(Context context, AttributeSet attributeSet, int i, int i2) {
        this.d = -2;
        this.e = -2;
        this.h = 1002;
        this.l = 0;
        this.m = false;
        this.n = false;
        this.o = Integer.MAX_VALUE;
        this.q = 0;
        this.w = new g();
        this.x = new f();
        this.y = new e();
        this.z = new c();
        this.C = new Rect();
        this.a = context;
        this.B = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.z, i, i2);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.i = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        p pVar = new p(context, attributeSet, i, i2);
        this.F = pVar;
        pVar.setInputMethodMode(1);
    }
}
