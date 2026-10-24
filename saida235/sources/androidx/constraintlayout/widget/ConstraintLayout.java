package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import p.j;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    private static g x;
    SparseArray a;
    private ArrayList b;
    protected p.f c;
    private int d;
    private int e;
    private int f;
    private int g;
    protected boolean h;
    private int i;
    private d j;
    protected androidx.constraintlayout.widget.c k;
    private int l;
    private HashMap m;
    private int n;
    private int o;
    int p;
    int q;
    int r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    int f18s;
    private SparseArray t;
    c u;
    private int v;
    private int w;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[p.e.b.values().length];
            a = iArr;
            try {
                iArr[p.e.b.a.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[p.e.b.b.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[p.e.b.d.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[p.e.b.c.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class c implements q.b.b {
        ConstraintLayout a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;

        public c(ConstraintLayout constraintLayout) {
            this.a = constraintLayout;
        }

        private boolean d(int i, int i2, int i3) {
            if (i == i2) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i);
            View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (mode2 == 1073741824) {
                return (mode == Integer.MIN_VALUE || mode == 0) && i3 == size;
            }
            return false;
        }

        public final void a() {
            int childCount = this.a.getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.a.getChildAt(i);
            }
            int size = this.a.b.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    ((androidx.constraintlayout.widget.b) this.a.b.get(i2)).j(this.a);
                }
            }
        }

        public final void b(p.e eVar, q.b.a aVar) {
            int iMakeMeasureSpec;
            int iMakeMeasureSpec2;
            int baseline;
            int iMax;
            int iMax2;
            int iMakeMeasureSpec3;
            int i;
            if (eVar == null) {
                return;
            }
            if (eVar.T() == 8 && !eVar.h0()) {
                aVar.e = 0;
                aVar.f = 0;
                aVar.g = 0;
                return;
            }
            if (eVar.I() == null) {
                return;
            }
            p.e.b bVar = aVar.a;
            p.e.b bVar2 = aVar.b;
            int i2 = aVar.c;
            int i3 = aVar.d;
            int i4 = this.b + this.c;
            int i5 = this.d;
            View view = (View) eVar.q();
            int[] iArr = a.a;
            int i6 = iArr[bVar.ordinal()];
            if (i6 == 1) {
                iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
            } else if (i6 == 2) {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i5, -2);
            } else if (i6 == 3) {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i5 + eVar.z(), -1);
            } else if (i6 != 4) {
                iMakeMeasureSpec = 0;
            } else {
                iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i5, -2);
                boolean z = eVar.w == 1;
                int i7 = aVar.j;
                if (i7 == q.b.a.l || i7 == q.b.a.m) {
                    if (aVar.j == q.b.a.m || !z || (z && (view.getMeasuredHeight() == eVar.v())) || eVar.l0()) {
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(eVar.U(), 1073741824);
                    }
                }
            }
            int i8 = iArr[bVar2.ordinal()];
            if (i8 == 1) {
                iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
            } else if (i8 == 2) {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i4, -2);
            } else if (i8 == 3) {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i4 + eVar.S(), -1);
            } else if (i8 != 4) {
                iMakeMeasureSpec2 = 0;
            } else {
                iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i4, -2);
                boolean z2 = eVar.x == 1;
                int i9 = aVar.j;
                if (i9 == q.b.a.l || i9 == q.b.a.m) {
                    if (aVar.j == q.b.a.m || !z2 || (z2 && (view.getMeasuredWidth() == eVar.U())) || eVar.m0()) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(eVar.v(), 1073741824);
                    }
                }
            }
            p.f fVarI = eVar.I();
            if (fVarI != null && j.b(ConstraintLayout.this.i, 256) && view.getMeasuredWidth() == eVar.U() && view.getMeasuredWidth() < fVarI.U() && view.getMeasuredHeight() == eVar.v() && view.getMeasuredHeight() < fVarI.v() && view.getBaseline() == eVar.n() && !eVar.k0()) {
                if (d(eVar.A(), iMakeMeasureSpec, eVar.U()) && d(eVar.B(), iMakeMeasureSpec2, eVar.v())) {
                    aVar.e = eVar.U();
                    aVar.f = eVar.v();
                    aVar.g = eVar.n();
                    return;
                }
            }
            p.e.b bVar3 = p.e.b.c;
            boolean z3 = bVar == bVar3;
            boolean z4 = bVar2 == bVar3;
            p.e.b bVar4 = p.e.b.d;
            boolean z5 = bVar2 == bVar4 || bVar2 == p.e.b.a;
            boolean z6 = bVar == bVar4 || bVar == p.e.b.a;
            boolean z7 = z3 && eVar.d0 > 0.0f;
            boolean z8 = z4 && eVar.d0 > 0.0f;
            if (view == null) {
                return;
            }
            b bVar5 = (b) view.getLayoutParams();
            int i10 = aVar.j;
            if (i10 != q.b.a.l && i10 != q.b.a.m && z3 && eVar.w == 0 && z4 && eVar.x == 0) {
                i = -1;
                iMax2 = 0;
                baseline = 0;
                iMax = 0;
            } else {
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                eVar.R0(iMakeMeasureSpec, iMakeMeasureSpec2);
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                baseline = view.getBaseline();
                int i11 = eVar.z;
                iMax = i11 > 0 ? Math.max(i11, measuredWidth) : measuredWidth;
                int i12 = eVar.A;
                if (i12 > 0) {
                    iMax = Math.min(i12, iMax);
                }
                int i13 = eVar.C;
                iMax2 = i13 > 0 ? Math.max(i13, measuredHeight) : measuredHeight;
                int i14 = eVar.D;
                if (i14 > 0) {
                    iMax2 = Math.min(i14, iMax2);
                }
                if (!j.b(ConstraintLayout.this.i, 1)) {
                    if (z7 && z5) {
                        iMax = (int) ((iMax2 * eVar.d0) + 0.5f);
                    } else if (z8 && z6) {
                        iMax2 = (int) ((iMax / eVar.d0) + 0.5f);
                    }
                }
                if (measuredWidth != iMax || measuredHeight != iMax2) {
                    if (measuredWidth != iMax) {
                        iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(iMax, 1073741824);
                    }
                    if (measuredHeight != iMax2) {
                        iMakeMeasureSpec3 = iMakeMeasureSpec;
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMax2, 1073741824);
                    }
                    iMakeMeasureSpec3 = iMakeMeasureSpec;
                    view.measure(iMakeMeasureSpec3, iMakeMeasureSpec2);
                    eVar.R0(iMakeMeasureSpec3, iMakeMeasureSpec2);
                    iMax = view.getMeasuredWidth();
                    iMax2 = view.getMeasuredHeight();
                    baseline = view.getBaseline();
                }
                i = -1;
            }
            boolean z9 = baseline != i;
            aVar.i = (iMax == aVar.c && iMax2 == aVar.d) ? false : true;
            if (bVar5.g0) {
                z9 = true;
            }
            if (z9 && baseline != -1 && eVar.n() != baseline) {
                aVar.i = true;
            }
            aVar.e = iMax;
            aVar.f = iMax2;
            aVar.h = z9;
            aVar.g = baseline;
        }

        public void c(int i, int i2, int i3, int i4, int i5, int i6) {
            this.b = i3;
            this.c = i4;
            this.d = i5;
            this.e = i6;
            this.f = i;
            this.g = i2;
        }
    }

    public ConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.a = new SparseArray();
        this.b = new ArrayList(4);
        this.c = new p.f();
        this.d = 0;
        this.e = 0;
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.h = true;
        this.i = 257;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = new HashMap();
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = 0;
        this.f18s = 0;
        this.t = new SparseArray();
        this.u = new c(this);
        this.v = 0;
        this.w = 0;
        j(attrs, 0, 0);
    }

    private final p.e g(int i) {
        if (i == 0) {
            return this.c;
        }
        View viewFindViewById = (View) this.a.get(i);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.c;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((b) viewFindViewById.getLayoutParams()).v0;
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int iMax2 = Math.max(0, getPaddingStart()) + Math.max(0, getPaddingEnd());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static g getSharedValues() {
        if (x == null) {
            x = new g();
        }
        return x;
    }

    private void j(AttributeSet attributeSet, int i, int i2) {
        this.c.y0(this);
        this.c.Q1(this.u);
        this.a.put(getId(), this);
        this.j = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.u, i, i2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.d = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.d);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.e);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                } else if (index == R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.i = typedArrayObtainStyledAttributes.getInt(index, this.i);
                } else if (index == R$styleable.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            m(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.k = null;
                        }
                    }
                } else if (index == R$styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        d dVar = new d();
                        this.j = dVar;
                        dVar.h(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.j = null;
                    }
                    this.l = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.c.R1(this.i);
    }

    private void l() {
        this.h = true;
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = 0;
        this.f18s = 0;
    }

    private void p() {
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            p.e eVarI = i(getChildAt(i));
            if (eVarI != null) {
                eVarI.r0();
            }
        }
        if (zIsInEditMode) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    q(0, resourceName, Integer.valueOf(childAt.getId()));
                    int iIndexOf = resourceName.indexOf(47);
                    if (iIndexOf != -1) {
                        resourceName = resourceName.substring(iIndexOf + 1);
                    }
                    g(childAt.getId()).z0(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.l != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                getChildAt(i3).getId();
                int i4 = this.l;
            }
        }
        d dVar = this.j;
        if (dVar != null) {
            dVar.c(this, true);
        }
        this.c.q1();
        int size = this.b.size();
        if (size > 0) {
            for (int i5 = 0; i5 < size; i5++) {
                ((androidx.constraintlayout.widget.b) this.b.get(i5)).l(this);
            }
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            getChildAt(i6);
        }
        this.t.clear();
        this.t.put(0, this.c);
        this.t.put(getId(), this.c);
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt2 = getChildAt(i7);
            this.t.put(childAt2.getId(), i(childAt2));
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt3 = getChildAt(i8);
            p.e eVarI2 = i(childAt3);
            if (eVarI2 != null) {
                b bVar = (b) childAt3.getLayoutParams();
                this.c.a(eVarI2);
                c(zIsInEditMode, childAt3, eVarI2, bVar, this.t);
            }
        }
    }

    private void s(p.e eVar, b bVar, SparseArray sparseArray, int i, p.d.b bVar2) {
        View view = (View) this.a.get(i);
        p.e eVar2 = (p.e) sparseArray.get(i);
        if (eVar2 == null || view == null || !(view.getLayoutParams() instanceof b)) {
            return;
        }
        bVar.g0 = true;
        p.d.b bVar3 = p.d.b.f;
        if (bVar2 == bVar3) {
            b bVar4 = (b) view.getLayoutParams();
            bVar4.g0 = true;
            bVar4.v0.H0(true);
        }
        eVar.m(bVar3).a(eVar2.m(bVar2), bVar.D, bVar.C, true);
        eVar.H0(true);
        eVar.m(p.d.b.c).p();
        eVar.m(p.d.b.e).p();
    }

    private boolean t() {
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).isLayoutRequested()) {
                z = true;
                break;
            }
        }
        if (z) {
            p();
        }
        return z;
    }

    protected void c(boolean z, View view, p.e eVar, b bVar, SparseArray sparseArray) {
        p.e eVar2;
        p.e eVar3;
        p.e eVar4;
        p.e eVar5;
        int i;
        bVar.a();
        bVar.w0 = false;
        eVar.f1(view.getVisibility());
        if (bVar.j0) {
            eVar.Q0(true);
            eVar.f1(8);
        }
        eVar.y0(view);
        if (view instanceof androidx.constraintlayout.widget.b) {
            ((androidx.constraintlayout.widget.b) view).h(eVar, this.c.K1());
        }
        if (bVar.h0) {
            p.g gVar = (p.g) eVar;
            int i2 = bVar.s0;
            int i3 = bVar.t0;
            float f = bVar.u0;
            if (f != -1.0f) {
                gVar.v1(f);
                return;
            } else if (i2 != -1) {
                gVar.t1(i2);
                return;
            } else {
                if (i3 != -1) {
                    gVar.u1(i3);
                    return;
                }
                return;
            }
        }
        int i4 = bVar.l0;
        int i5 = bVar.m0;
        int i6 = bVar.n0;
        int i7 = bVar.o0;
        int i8 = bVar.p0;
        int i9 = bVar.q0;
        float f2 = bVar.r0;
        int i10 = bVar.p;
        if (i10 != -1) {
            p.e eVar6 = (p.e) sparseArray.get(i10);
            if (eVar6 != null) {
                eVar.j(eVar6, bVar.r, bVar.q);
            }
        } else {
            if (i4 != -1) {
                p.e eVar7 = (p.e) sparseArray.get(i4);
                if (eVar7 != null) {
                    p.d.b bVar2 = p.d.b.b;
                    eVar.c0(bVar2, eVar7, bVar2, ((ViewGroup.MarginLayoutParams) bVar).leftMargin, i8);
                }
            } else if (i5 != -1 && (eVar2 = (p.e) sparseArray.get(i5)) != null) {
                eVar.c0(p.d.b.b, eVar2, p.d.b.d, ((ViewGroup.MarginLayoutParams) bVar).leftMargin, i8);
            }
            if (i6 != -1) {
                p.e eVar8 = (p.e) sparseArray.get(i6);
                if (eVar8 != null) {
                    eVar.c0(p.d.b.d, eVar8, p.d.b.b, ((ViewGroup.MarginLayoutParams) bVar).rightMargin, i9);
                }
            } else if (i7 != -1 && (eVar3 = (p.e) sparseArray.get(i7)) != null) {
                p.d.b bVar3 = p.d.b.d;
                eVar.c0(bVar3, eVar3, bVar3, ((ViewGroup.MarginLayoutParams) bVar).rightMargin, i9);
            }
            int i11 = bVar.i;
            if (i11 != -1) {
                p.e eVar9 = (p.e) sparseArray.get(i11);
                if (eVar9 != null) {
                    p.d.b bVar4 = p.d.b.c;
                    eVar.c0(bVar4, eVar9, bVar4, ((ViewGroup.MarginLayoutParams) bVar).topMargin, bVar.x);
                }
            } else {
                int i12 = bVar.j;
                if (i12 != -1 && (eVar4 = (p.e) sparseArray.get(i12)) != null) {
                    eVar.c0(p.d.b.c, eVar4, p.d.b.e, ((ViewGroup.MarginLayoutParams) bVar).topMargin, bVar.x);
                }
            }
            int i13 = bVar.k;
            if (i13 != -1) {
                p.e eVar10 = (p.e) sparseArray.get(i13);
                if (eVar10 != null) {
                    eVar.c0(p.d.b.e, eVar10, p.d.b.c, ((ViewGroup.MarginLayoutParams) bVar).bottomMargin, bVar.z);
                }
            } else {
                int i14 = bVar.l;
                if (i14 != -1 && (eVar5 = (p.e) sparseArray.get(i14)) != null) {
                    p.d.b bVar5 = p.d.b.e;
                    eVar.c0(bVar5, eVar5, bVar5, ((ViewGroup.MarginLayoutParams) bVar).bottomMargin, bVar.z);
                }
            }
            int i15 = bVar.m;
            if (i15 != -1) {
                s(eVar, bVar, sparseArray, i15, p.d.b.f);
            } else {
                int i16 = bVar.n;
                if (i16 != -1) {
                    s(eVar, bVar, sparseArray, i16, p.d.b.c);
                } else {
                    int i17 = bVar.o;
                    if (i17 != -1) {
                        s(eVar, bVar, sparseArray, i17, p.d.b.e);
                    }
                }
            }
            if (f2 >= 0.0f) {
                eVar.J0(f2);
            }
            float f3 = bVar.H;
            if (f3 >= 0.0f) {
                eVar.Z0(f3);
            }
        }
        if (z && ((i = bVar.X) != -1 || bVar.Y != -1)) {
            eVar.X0(i, bVar.Y);
        }
        if (bVar.e0) {
            eVar.M0(p.e.b.a);
            eVar.g1(((ViewGroup.MarginLayoutParams) bVar).width);
            if (((ViewGroup.MarginLayoutParams) bVar).width == -2) {
                eVar.M0(p.e.b.b);
            }
        } else if (((ViewGroup.MarginLayoutParams) bVar).width == -1) {
            if (bVar.a0) {
                eVar.M0(p.e.b.c);
            } else {
                eVar.M0(p.e.b.d);
            }
            eVar.m(p.d.b.b).g = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            eVar.m(p.d.b.d).g = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        } else {
            eVar.M0(p.e.b.c);
            eVar.g1(0);
        }
        if (bVar.f0) {
            eVar.c1(p.e.b.a);
            eVar.I0(((ViewGroup.MarginLayoutParams) bVar).height);
            if (((ViewGroup.MarginLayoutParams) bVar).height == -2) {
                eVar.c1(p.e.b.b);
            }
        } else if (((ViewGroup.MarginLayoutParams) bVar).height == -1) {
            if (bVar.b0) {
                eVar.c1(p.e.b.c);
            } else {
                eVar.c1(p.e.b.d);
            }
            eVar.m(p.d.b.c).g = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            eVar.m(p.d.b.e).g = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        } else {
            eVar.c1(p.e.b.c);
            eVar.I0(0);
        }
        eVar.A0(bVar.I);
        eVar.O0(bVar.L);
        eVar.e1(bVar.M);
        eVar.K0(bVar.N);
        eVar.a1(bVar.O);
        eVar.h1(bVar.d0);
        eVar.N0(bVar.P, bVar.R, bVar.T, bVar.V);
        eVar.d1(bVar.Q, bVar.S, bVar.U, bVar.W);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public b generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.b;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                ((androidx.constraintlayout.widget.b) this.b.get(i)).k(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        int i5 = Integer.parseInt(strArrSplit[2]);
                        int i6 = (int) ((i3 / 1080.0f) * width);
                        int i7 = (int) ((i4 / 1920.0f) * height);
                        int i8 = (int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = i6;
                        float f2 = i7;
                        float f3 = i6 + ((int) ((i5 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float f4 = i7 + i8;
                        canvas.drawLine(f3, f2, f3, f4, paint);
                        canvas.drawLine(f3, f4, f, f4, paint);
                        canvas.drawLine(f, f4, f, f2, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f, f2, f3, f4, paint);
                        canvas.drawLine(f, f4, f3, f2, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    public Object f(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap map = this.m;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.m.get(str);
    }

    @Override // android.view.View
    public void forceLayout() {
        l();
        super.forceLayout();
    }

    public int getMaxHeight() {
        return this.g;
    }

    public int getMaxWidth() {
        return this.f;
    }

    public int getMinHeight() {
        return this.e;
    }

    public int getMinWidth() {
        return this.d;
    }

    public int getOptimizationLevel() {
        return this.c.F1();
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        if (((p.e) this.c).o == null) {
            int id2 = getId();
            if (id2 != -1) {
                ((p.e) this.c).o = getContext().getResources().getResourceEntryName(id2);
            } else {
                ((p.e) this.c).o = "parent";
            }
        }
        if (this.c.r() == null) {
            p.f fVar = this.c;
            fVar.z0(((p.e) fVar).o);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" setDebugName ");
            sb2.append(this.c.r());
        }
        for (p.e eVar : this.c.n1()) {
            View view = (View) eVar.q();
            if (view != null) {
                if (eVar.o == null && (id = view.getId()) != -1) {
                    eVar.o = getContext().getResources().getResourceEntryName(id);
                }
                if (eVar.r() == null) {
                    eVar.z0(eVar.o);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(" setDebugName ");
                    sb3.append(eVar.r());
                }
            }
        }
        this.c.M(sb);
        return sb.toString();
    }

    public View h(int i) {
        return (View) this.a.get(i);
    }

    public final p.e i(View view) {
        if (view == this) {
            return this.c;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof b) {
            return ((b) view.getLayoutParams()).v0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof b) {
            return ((b) view.getLayoutParams()).v0;
        }
        return null;
    }

    protected boolean k() {
        return ((getContext().getApplicationInfo().flags & 4194304) != 0) && 1 == getLayoutDirection();
    }

    protected void m(int i) {
        this.k = new androidx.constraintlayout.widget.c(getContext(), this, i);
    }

    protected void n(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        c cVar = this.u;
        int i5 = cVar.e;
        int iResolveSizeAndState = View.resolveSizeAndState(i3 + cVar.d, i, 0);
        int iResolveSizeAndState2 = View.resolveSizeAndState(i4 + i5, i2, 0) & 16777215;
        int iMin = Math.min(this.f, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.g, iResolveSizeAndState2);
        if (z) {
            iMin |= 16777216;
        }
        if (z2) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.n = iMin;
        this.o = iMin2;
    }

    protected void o(p.f fVar, int i, int i2, int i3) {
        int iMax;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int iMax2 = Math.max(0, getPaddingTop());
        int iMax3 = Math.max(0, getPaddingBottom());
        int i4 = iMax2 + iMax3;
        int paddingWidth = getPaddingWidth();
        this.u.c(i2, i3, iMax2, iMax3, paddingWidth, i4);
        int iMax4 = Math.max(0, getPaddingStart());
        int iMax5 = Math.max(0, getPaddingEnd());
        if (iMax4 > 0 || iMax5 > 0) {
            iMax = k() ? iMax5 : iMax4;
        } else {
            iMax = Math.max(0, getPaddingLeft());
        }
        int i5 = size - paddingWidth;
        int i6 = size2 - i4;
        r(fVar, mode, i5, mode2, i6);
        fVar.M1(i, mode, i5, mode2, i6, this.n, this.o, iMax, iMax2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            b bVar = (b) childAt.getLayoutParams();
            p.e eVar = bVar.v0;
            if ((childAt.getVisibility() != 8 || bVar.h0 || bVar.i0 || bVar.k0 || zIsInEditMode) && !bVar.j0) {
                int iV = eVar.V();
                int iW = eVar.W();
                childAt.layout(iV, iW, eVar.U() + iV, eVar.v() + iW);
            }
        }
        int size = this.b.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((androidx.constraintlayout.widget.b) this.b.get(i6)).i(this);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.v == i) {
            int i3 = this.w;
        }
        if (!this.h) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                if (getChildAt(i4).isLayoutRequested()) {
                    this.h = true;
                    break;
                }
            }
        }
        boolean z = this.h;
        this.v = i;
        this.w = i2;
        this.c.T1(k());
        if (this.h) {
            this.h = false;
            if (t()) {
                this.c.V1();
            }
        }
        o(this.c, this.i, i, i2);
        n(i, i2, this.c.U(), this.c.v(), this.c.L1(), this.c.J1());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        p.e eVarI = i(view);
        if ((view instanceof f) && !(eVarI instanceof p.g)) {
            b bVar = (b) view.getLayoutParams();
            p.g gVar = new p.g();
            bVar.v0 = gVar;
            bVar.h0 = true;
            gVar.w1(bVar.Z);
        }
        if (view instanceof androidx.constraintlayout.widget.b) {
            androidx.constraintlayout.widget.b bVar2 = (androidx.constraintlayout.widget.b) view;
            bVar2.m();
            ((b) view.getLayoutParams()).i0 = true;
            if (!this.b.contains(bVar2)) {
                this.b.add(bVar2);
            }
        }
        this.a.put(view.getId(), view);
        this.h = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.a.remove(view.getId());
        this.c.p1(i(view));
        this.b.remove(view);
        this.h = true;
    }

    public void q(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.m == null) {
                this.m = new HashMap();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.m.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x003e A[PHI: r2
      0x003e: PHI (r2v4 p.e$b) = (r2v3 p.e$b), (r2v0 p.e$b) binds: [B:21:0x004a, B:17:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    protected void r(p.f fVar, int i, int i2, int i3, int i4) {
        p.e.b bVar;
        c cVar = this.u;
        int i5 = cVar.e;
        int i6 = cVar.d;
        p.e.b bVar2 = p.e.b.a;
        int childCount = getChildCount();
        if (i == Integer.MIN_VALUE) {
            bVar = p.e.b.b;
            if (childCount == 0) {
                i2 = Math.max(0, this.d);
            }
        } else if (i == 0) {
            bVar = p.e.b.b;
            i2 = childCount == 0 ? Math.max(0, this.d) : 0;
        } else if (i != 1073741824) {
            bVar = bVar2;
        } else {
            i2 = Math.min(this.f - i6, i2);
            bVar = bVar2;
        }
        if (i3 == Integer.MIN_VALUE) {
            bVar2 = p.e.b.b;
            if (childCount == 0) {
                i4 = Math.max(0, this.e);
            }
        } else if (i3 == 0) {
            bVar2 = p.e.b.b;
            if (childCount == 0) {
                i4 = Math.max(0, this.e);
            } else {
                i4 = 0;
            }
        } else if (i3 != 1073741824) {
            i4 = 0;
        } else {
            i4 = Math.min(this.g - i5, i4);
        }
        if (i2 != fVar.U() || i4 != fVar.v()) {
            fVar.I1();
        }
        fVar.i1(0);
        fVar.j1(0);
        fVar.T0(this.f - i6);
        fVar.S0(this.g - i5);
        fVar.W0(0);
        fVar.V0(0);
        fVar.M0(bVar);
        fVar.g1(i2);
        fVar.c1(bVar2);
        fVar.I0(i4);
        fVar.W0(this.d - i6);
        fVar.V0(this.e - i5);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        l();
        super.requestLayout();
    }

    public void setConstraintSet(d set) {
        this.j = set;
    }

    @Override // android.view.View
    public void setId(int id) {
        this.a.remove(getId());
        super.setId(id);
        this.a.put(getId(), this);
    }

    public void setMaxHeight(int value) {
        if (value == this.g) {
            return;
        }
        this.g = value;
        requestLayout();
    }

    public void setMaxWidth(int value) {
        if (value == this.f) {
            return;
        }
        this.f = value;
        requestLayout();
    }

    public void setMinHeight(int value) {
        if (value == this.e) {
            return;
        }
        this.e = value;
        requestLayout();
    }

    public void setMinWidth(int value) {
        if (value == this.d) {
            return;
        }
        this.d = value;
        requestLayout();
    }

    public void setOnConstraintsChanged(e constraintsChangedListener) {
        androidx.constraintlayout.widget.c cVar = this.k;
        if (cVar != null) {
            cVar.c(constraintsChangedListener);
        }
    }

    public void setOptimizationLevel(int level) {
        this.i = level;
        this.c.R1(level);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    public static class b extends ViewGroup.MarginLayoutParams {
        public int A;
        public int B;
        public int C;
        public int D;
        boolean E;
        boolean F;
        public float G;
        public float H;
        public String I;
        float J;
        int K;
        public float L;
        public float M;
        public int N;
        public int O;
        public int P;
        public int Q;
        public int R;
        public int S;
        public int T;
        public int U;
        public float V;
        public float W;
        public int X;
        public int Y;
        public int Z;
        public int a;
        public boolean a0;
        public int b;
        public boolean b0;
        public float c;
        public String c0;
        public boolean d;
        public int d0;
        public int e;
        boolean e0;
        public int f;
        boolean f0;
        public int g;
        boolean g0;
        public int h;
        boolean h0;
        public int i;
        boolean i0;
        public int j;
        boolean j0;
        public int k;
        boolean k0;
        public int l;
        int l0;
        public int m;
        int m0;
        public int n;
        int n0;
        public int o;
        int o0;
        public int p;
        int p0;
        public int q;
        int q0;
        public float r;
        float r0;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f19s;
        int s0;
        public int t;
        int t0;
        public int u;
        float u0;
        public int v;
        p.e v0;
        public int w;
        public boolean w0;
        public int x;
        public int y;
        public int z;

        private static class a {
            public static final SparseIntArray a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                a = sparseIntArray;
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth, 64);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight, 65);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toTopOf, 52);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBottomOf, 53);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_guidelineUseRtl, 67);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBaseline, 55);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_marginBaseline, 54);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTag, 51);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_wrapBehaviorInParent, 66);
            }
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = true;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.f19s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = true;
            this.F = true;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = true;
            this.f0 = true;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new p.e();
            this.w0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.u);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = a.a.get(index);
                switch (i2) {
                    case 1:
                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                        this.p = resourceId;
                        if (resourceId == -1) {
                            this.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.q);
                        break;
                    case 4:
                        float f = typedArrayObtainStyledAttributes.getFloat(index, this.r) % 360.0f;
                        this.r = f;
                        if (f < 0.0f) {
                            this.r = (360.0f - f) % 360.0f;
                        }
                        break;
                    case 5:
                        this.a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.a);
                        break;
                    case 6:
                        this.b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.b);
                        break;
                    case 7:
                        this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
                        this.e = resourceId2;
                        if (resourceId2 == -1) {
                            this.e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.f);
                        this.f = resourceId3;
                        if (resourceId3 == -1) {
                            this.f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                        this.g = resourceId4;
                        if (resourceId4 == -1) {
                            this.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.h);
                        this.h = resourceId5;
                        if (resourceId5 == -1) {
                            this.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.i);
                        this.i = resourceId6;
                        if (resourceId6 == -1) {
                            this.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                        this.j = resourceId7;
                        if (resourceId7 == -1) {
                            this.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                        this.k = resourceId8;
                        if (resourceId8 == -1) {
                            this.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.l);
                        this.l = resourceId9;
                        if (resourceId9 == -1) {
                            this.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                        this.m = resourceId10;
                        if (resourceId10 == -1) {
                            this.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.f19s);
                        this.f19s = resourceId11;
                        if (resourceId11 == -1) {
                            this.f19s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.t);
                        this.t = resourceId12;
                        if (resourceId12 == -1) {
                            this.t = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.u);
                        this.u = resourceId13;
                        if (resourceId13 == -1) {
                            this.u = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.v);
                        this.v = resourceId14;
                        if (resourceId14 == -1) {
                            this.v = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        break;
                    case 22:
                        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        break;
                    case 23:
                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        break;
                    case 24:
                        this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.z);
                        break;
                    case 25:
                        this.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.A);
                        break;
                    case 26:
                        this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.B);
                        break;
                    case 27:
                        this.a0 = typedArrayObtainStyledAttributes.getBoolean(index, this.a0);
                        break;
                    case 28:
                        this.b0 = typedArrayObtainStyledAttributes.getBoolean(index, this.b0);
                        break;
                    case 29:
                        this.G = typedArrayObtainStyledAttributes.getFloat(index, this.G);
                        break;
                    case 30:
                        this.H = typedArrayObtainStyledAttributes.getFloat(index, this.H);
                        break;
                    case 31:
                        int i3 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.P = i3;
                        if (i3 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        int i4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.Q = i4;
                        if (i4 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        }
                        break;
                    case 33:
                        try {
                            this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.R);
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.R) == -2) {
                                this.R = -2;
                            }
                        }
                        break;
                    case 34:
                        try {
                            this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.T);
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.T) == -2) {
                                this.T = -2;
                            }
                        }
                        break;
                    case 35:
                        this.V = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.V));
                        this.P = 2;
                        break;
                    case 36:
                        try {
                            this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.S);
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.S) == -2) {
                                this.S = -2;
                            }
                        }
                        break;
                    case 37:
                        try {
                            this.U = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.U);
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.U) == -2) {
                                this.U = -2;
                            }
                        }
                        break;
                    case 38:
                        this.W = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.W));
                        this.Q = 2;
                        break;
                    default:
                        switch (i2) {
                            case 44:
                                d.m(this, typedArrayObtainStyledAttributes.getString(index));
                                break;
                            case 45:
                                this.L = typedArrayObtainStyledAttributes.getFloat(index, this.L);
                                break;
                            case 46:
                                this.M = typedArrayObtainStyledAttributes.getFloat(index, this.M);
                                break;
                            case 47:
                                this.N = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.O = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.X = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.X);
                                break;
                            case 50:
                                this.Y = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.Y);
                                break;
                            case 51:
                                this.c0 = typedArrayObtainStyledAttributes.getString(index);
                                break;
                            case 52:
                                int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, this.n);
                                this.n = resourceId15;
                                if (resourceId15 == -1) {
                                    this.n = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 53:
                                int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, this.o);
                                this.o = resourceId16;
                                if (resourceId16 == -1) {
                                    this.o = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 54:
                                this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.D);
                                break;
                            case 55:
                                this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            default:
                                switch (i2) {
                                    case 64:
                                        d.k(this, typedArrayObtainStyledAttributes, index, 0);
                                        this.E = true;
                                        break;
                                    case 65:
                                        d.k(this, typedArrayObtainStyledAttributes, index, 1);
                                        this.F = true;
                                        break;
                                    case 66:
                                        this.d0 = typedArrayObtainStyledAttributes.getInt(index, this.d0);
                                        break;
                                    case 67:
                                        this.d = typedArrayObtainStyledAttributes.getBoolean(index, this.d);
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            a();
        }

        public void a() {
            this.h0 = false;
            this.e0 = true;
            this.f0 = true;
            int i = ((ViewGroup.MarginLayoutParams) this).width;
            if (i == -2 && this.a0) {
                this.e0 = false;
                if (this.P == 0) {
                    this.P = 1;
                }
            }
            int i2 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i2 == -2 && this.b0) {
                this.f0 = false;
                if (this.Q == 0) {
                    this.Q = 1;
                }
            }
            if (i == 0 || i == -1) {
                this.e0 = false;
                if (i == 0 && this.P == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.a0 = true;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.f0 = false;
                if (i2 == 0 && this.Q == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.b0 = true;
                }
            }
            if (this.c == -1.0f && this.a == -1 && this.b == -1) {
                return;
            }
            this.h0 = true;
            this.e0 = true;
            this.f0 = true;
            if (!(this.v0 instanceof p.g)) {
                this.v0 = new p.g();
            }
            this.v0.w1(this.Z);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x004a  */
        /* JADX WARN: Code duplicated, block: B:20:0x0051  */
        /* JADX WARN: Code duplicated, block: B:23:0x0058  */
        /* JADX WARN: Code duplicated, block: B:26:0x005e  */
        /* JADX WARN: Code duplicated, block: B:29:0x0064  */
        /* JADX WARN: Code duplicated, block: B:38:0x007a  */
        /* JADX WARN: Code duplicated, block: B:39:0x0082 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:40:0x0084  */
        /* JADX WARN: Code duplicated, block: B:41:0x008b A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:42:0x008d  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        public void resolveLayoutDirection(int i) {
            int i2;
            int i3;
            int i4;
            int i5;
            int i6 = ((ViewGroup.MarginLayoutParams) this).leftMargin;
            int i7 = ((ViewGroup.MarginLayoutParams) this).rightMargin;
            super.resolveLayoutDirection(i);
            boolean z = false;
            boolean z2 = 1 == getLayoutDirection();
            this.n0 = -1;
            this.o0 = -1;
            this.l0 = -1;
            this.m0 = -1;
            this.p0 = this.w;
            this.q0 = this.y;
            float f = this.G;
            this.r0 = f;
            int i8 = this.a;
            this.s0 = i8;
            int i9 = this.b;
            this.t0 = i9;
            float f2 = this.c;
            this.u0 = f2;
            if (z2) {
                int i10 = this.f19s;
                if (i10 != -1) {
                    this.n0 = i10;
                } else {
                    int i11 = this.t;
                    if (i11 != -1) {
                        this.o0 = i11;
                    } else {
                        i2 = this.u;
                        if (i2 != -1) {
                            this.m0 = i2;
                            z = true;
                        }
                        i3 = this.v;
                        if (i3 != -1) {
                            this.l0 = i3;
                            z = true;
                        }
                        i4 = this.A;
                        if (i4 != Integer.MIN_VALUE) {
                            this.q0 = i4;
                        }
                        i5 = this.B;
                        if (i5 != Integer.MIN_VALUE) {
                            this.p0 = i5;
                        }
                        if (z) {
                            this.r0 = 1.0f - f;
                        }
                        if (this.h0 && this.Z == 1 && this.d) {
                            if (f2 != -1.0f) {
                                this.u0 = 1.0f - f2;
                                this.s0 = -1;
                                this.t0 = -1;
                            } else if (i8 != -1) {
                                this.t0 = i8;
                                this.s0 = -1;
                                this.u0 = -1.0f;
                            } else if (i9 != -1) {
                                this.s0 = i9;
                                this.t0 = -1;
                                this.u0 = -1.0f;
                            }
                        }
                    }
                }
                z = true;
                i2 = this.u;
                if (i2 != -1) {
                    this.m0 = i2;
                    z = true;
                }
                i3 = this.v;
                if (i3 != -1) {
                    this.l0 = i3;
                    z = true;
                }
                i4 = this.A;
                if (i4 != Integer.MIN_VALUE) {
                    this.q0 = i4;
                }
                i5 = this.B;
                if (i5 != Integer.MIN_VALUE) {
                    this.p0 = i5;
                }
                if (z) {
                    this.r0 = 1.0f - f;
                }
                if (this.h0) {
                    if (f2 != -1.0f) {
                        this.u0 = 1.0f - f2;
                        this.s0 = -1;
                        this.t0 = -1;
                    } else if (i8 != -1) {
                        this.t0 = i8;
                        this.s0 = -1;
                        this.u0 = -1.0f;
                    } else if (i9 != -1) {
                        this.s0 = i9;
                        this.t0 = -1;
                        this.u0 = -1.0f;
                    }
                }
            } else {
                int i12 = this.f19s;
                if (i12 != -1) {
                    this.m0 = i12;
                }
                int i13 = this.t;
                if (i13 != -1) {
                    this.l0 = i13;
                }
                int i14 = this.u;
                if (i14 != -1) {
                    this.n0 = i14;
                }
                int i15 = this.v;
                if (i15 != -1) {
                    this.o0 = i15;
                }
                int i16 = this.A;
                if (i16 != Integer.MIN_VALUE) {
                    this.p0 = i16;
                }
                int i17 = this.B;
                if (i17 != Integer.MIN_VALUE) {
                    this.q0 = i17;
                }
            }
            if (this.u == -1 && this.v == -1 && this.t == -1 && this.f19s == -1) {
                int i18 = this.g;
                if (i18 != -1) {
                    this.n0 = i18;
                    if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i7 > 0) {
                        ((ViewGroup.MarginLayoutParams) this).rightMargin = i7;
                    }
                } else {
                    int i19 = this.h;
                    if (i19 != -1) {
                        this.o0 = i19;
                        if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i7 > 0) {
                            ((ViewGroup.MarginLayoutParams) this).rightMargin = i7;
                        }
                    }
                }
                int i20 = this.e;
                if (i20 != -1) {
                    this.l0 = i20;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i6 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i6;
                    return;
                }
                int i21 = this.f;
                if (i21 != -1) {
                    this.m0 = i21;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i6 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i6;
                }
            }
        }

        public b(int i, int i2) {
            super(i, i2);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = true;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.f19s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = true;
            this.F = true;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = true;
            this.f0 = true;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new p.e();
            this.w0 = false;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = true;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.f19s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = true;
            this.F = true;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = true;
            this.f0 = true;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new p.e();
            this.w0 = false;
        }
    }
}
