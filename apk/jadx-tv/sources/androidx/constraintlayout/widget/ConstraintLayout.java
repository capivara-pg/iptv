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
import l.e;
import l.f;
import l.g;
import l.i;
import l.m;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    SparseArray a;
    private ArrayList b;
    private final ArrayList c;
    g d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private int j;
    private b k;
    private int l;
    private HashMap m;
    private int n;
    private int o;
    int p;
    int q;
    int r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    int f19s;

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new SparseArray();
        this.b = new ArrayList(4);
        this.c = new ArrayList(100);
        this.d = new g();
        this.e = 0;
        this.f = 0;
        this.g = Integer.MAX_VALUE;
        this.h = Integer.MAX_VALUE;
        this.i = true;
        this.j = 7;
        this.k = null;
        this.l = -1;
        this.m = new HashMap();
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = 0;
        this.f19s = 0;
        g(attributeSet);
    }

    private final f d(int i) {
        if (i == 0) {
            return this.d;
        }
        View viewFindViewById = (View) this.a.get(i);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.d;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((a) viewFindViewById.getLayoutParams()).l0;
    }

    private void g(AttributeSet attributeSet) {
        this.d.W(this);
        this.a.put(getId(), this);
        this.k = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.a);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.e);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.h);
                } else if (index == R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.j = typedArrayObtainStyledAttributes.getInt(index, this.j);
                } else if (index == R$styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        b bVar = new b();
                        this.k = bVar;
                        bVar.d(getContext(), resourceId);
                    } catch (Resources.NotFoundException unused) {
                        this.k = null;
                    }
                    this.l = resourceId;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.d.c1(this.j);
    }

    private void h(int i, int i2) {
        boolean z;
        boolean z2;
        int baseline;
        int childMeasureSpec;
        int childMeasureSpec2;
        boolean z3;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                f fVar = aVar.l0;
                if (!aVar.Y && !aVar.Z) {
                    fVar.x0(childAt.getVisibility());
                    int measuredWidth = ((ViewGroup.MarginLayoutParams) aVar).width;
                    int measuredHeight = ((ViewGroup.MarginLayoutParams) aVar).height;
                    boolean z4 = aVar.V;
                    if (z4 || (z3 = aVar.W) || (!z4 && aVar.I == 1) || measuredWidth == -1 || (!z3 && (aVar.J == 1 || measuredHeight == -1))) {
                        if (measuredWidth == 0) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -2);
                            z = true;
                        } else if (measuredWidth == -1) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -1);
                            z = false;
                        } else {
                            z = measuredWidth == -2;
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, measuredWidth);
                        }
                        if (measuredHeight == 0) {
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
                            z2 = true;
                        } else if (measuredHeight == -1) {
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, -1);
                            z2 = false;
                        } else {
                            z2 = measuredHeight == -2;
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, measuredHeight);
                        }
                        childAt.measure(childMeasureSpec, childMeasureSpec2);
                        fVar.z0(measuredWidth == -2);
                        fVar.c0(measuredHeight == -2);
                        measuredWidth = childAt.getMeasuredWidth();
                        measuredHeight = childAt.getMeasuredHeight();
                    } else {
                        z = false;
                        z2 = false;
                    }
                    fVar.y0(measuredWidth);
                    fVar.b0(measuredHeight);
                    if (z) {
                        fVar.B0(measuredWidth);
                    }
                    if (z2) {
                        fVar.A0(measuredHeight);
                    }
                    if (aVar.X && (baseline = childAt.getBaseline()) != -1) {
                        fVar.V(baseline);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:155:0x029b  */
    private void i(int i, int i2) {
        int i3;
        int i4;
        boolean z;
        int childMeasureSpec;
        int i5;
        boolean z2;
        int i6;
        int childMeasureSpec2;
        int baseline;
        int baseline2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        int i7 = 0;
        while (true) {
            i3 = 8;
            if (i7 >= childCount) {
                break;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                f fVar = aVar.l0;
                if (!aVar.Y && !aVar.Z) {
                    fVar.x0(childAt.getVisibility());
                    int i8 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    int i9 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    if (i8 == 0 || i9 == 0) {
                        fVar.x().c();
                        fVar.w().c();
                    } else {
                        boolean z3 = i8 == -2;
                        int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(i, paddingLeft, i8);
                        boolean z4 = i9 == -2;
                        childAt.measure(childMeasureSpec3, ViewGroup.getChildMeasureSpec(i2, paddingTop, i9));
                        fVar.z0(i8 == -2);
                        fVar.c0(i9 == -2);
                        int measuredWidth = childAt.getMeasuredWidth();
                        int measuredHeight = childAt.getMeasuredHeight();
                        fVar.y0(measuredWidth);
                        fVar.b0(measuredHeight);
                        if (z3) {
                            fVar.B0(measuredWidth);
                        }
                        if (z4) {
                            fVar.A0(measuredHeight);
                        }
                        if (aVar.X && (baseline2 = childAt.getBaseline()) != -1) {
                            fVar.V(baseline2);
                        }
                        if (aVar.V && aVar.W) {
                            fVar.x().h(measuredWidth);
                            fVar.w().h(measuredHeight);
                        }
                    }
                }
            }
            i7++;
        }
        this.d.e1();
        int i10 = 0;
        while (i10 < childCount) {
            View childAt2 = getChildAt(i10);
            if (childAt2.getVisibility() == i3) {
                i4 = childCount;
            } else {
                a aVar2 = (a) childAt2.getLayoutParams();
                f fVar2 = aVar2.l0;
                if (aVar2.Y || aVar2.Z) {
                    i4 = childCount;
                } else {
                    fVar2.x0(childAt2.getVisibility());
                    int iK = ((ViewGroup.MarginLayoutParams) aVar2).width;
                    int i11 = ((ViewGroup.MarginLayoutParams) aVar2).height;
                    if (iK == 0 || i11 == 0) {
                        e.d dVar = e.d.b;
                        m mVarF = fVar2.h(dVar).f();
                        e.d dVar2 = e.d.d;
                        m mVarF2 = fVar2.h(dVar2).f();
                        boolean z5 = (fVar2.h(dVar).i() == null || fVar2.h(dVar2).i() == null) ? false : true;
                        e.d dVar3 = e.d.c;
                        m mVarF3 = fVar2.h(dVar3).f();
                        e.d dVar4 = e.d.e;
                        m mVarF4 = fVar2.h(dVar4).f();
                        boolean z6 = (fVar2.h(dVar3).i() == null || fVar2.h(dVar4).i() == null) ? false : true;
                        if (iK == 0 && i11 == 0 && z5 && z6) {
                            i4 = childCount;
                        } else {
                            f.b bVarS = this.d.s();
                            f.b bVar = f.b.b;
                            i4 = childCount;
                            boolean z7 = bVarS != bVar;
                            boolean z8 = this.d.B() != bVar;
                            if (!z7) {
                                fVar2.x().c();
                            }
                            if (!z8) {
                                fVar2.w().c();
                            }
                            if (iK == 0) {
                                if (z7 && fVar2.P() && z5 && mVarF.d() && mVarF2.d()) {
                                    iK = (int) (mVarF2.k() - mVarF.k());
                                    fVar2.x().h(iK);
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, iK);
                                    z = false;
                                } else {
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -2);
                                    z = true;
                                    z7 = false;
                                }
                            } else if (iK == -1) {
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -1);
                                z = false;
                            } else {
                                z = iK == -2;
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, iK);
                            }
                            if (i11 == 0) {
                                if (z8 && fVar2.O() && z6 && mVarF3.d() && mVarF4.d()) {
                                    int iK2 = (int) (mVarF4.k() - mVarF3.k());
                                    fVar2.w().h(iK2);
                                    i6 = iK2;
                                    z2 = false;
                                    childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, iK2);
                                    i5 = -2;
                                } else {
                                    i5 = -2;
                                    i6 = i11;
                                    z2 = true;
                                    childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
                                    z8 = false;
                                }
                            } else if (i11 == -1) {
                                i6 = i11;
                                childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, -1);
                                i5 = -2;
                                z2 = false;
                            } else {
                                i5 = -2;
                                z2 = i11 == -2;
                                i6 = i11;
                                childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, i11);
                            }
                            childAt2.measure(childMeasureSpec, childMeasureSpec2);
                            fVar2.z0(iK == i5);
                            fVar2.c0(i6 == i5);
                            int measuredWidth2 = childAt2.getMeasuredWidth();
                            int measuredHeight2 = childAt2.getMeasuredHeight();
                            fVar2.y0(measuredWidth2);
                            fVar2.b0(measuredHeight2);
                            if (z) {
                                fVar2.B0(measuredWidth2);
                            }
                            if (z2) {
                                fVar2.A0(measuredHeight2);
                            }
                            if (z7) {
                                fVar2.x().h(measuredWidth2);
                            } else {
                                fVar2.x().g();
                            }
                            if (z8) {
                                fVar2.w().h(measuredHeight2);
                            } else {
                                fVar2.w().g();
                            }
                            if (aVar2.X && (baseline = childAt2.getBaseline()) != -1) {
                                fVar2.V(baseline);
                            }
                        }
                    } else {
                        i4 = childCount;
                    }
                }
            }
            i10++;
            childCount = i4;
            i3 = 8;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r25v0, types: [android.view.View, android.view.ViewGroup, androidx.constraintlayout.widget.ConstraintLayout] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v32 */
    private void j() {
        float f;
        int i;
        int i2;
        f fVarD;
        f fVarD2;
        f fVarD3;
        f fVarD4;
        int i3;
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        ?? r3 = 0;
        if (zIsInEditMode) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    k(0, resourceName, Integer.valueOf(childAt.getId()));
                    int iIndexOf = resourceName.indexOf(47);
                    if (iIndexOf != -1) {
                        resourceName = resourceName.substring(iIndexOf + 1);
                    }
                    d(childAt.getId()).X(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            f fVarF = f(getChildAt(i5));
            if (fVarF != null) {
                fVarF.Q();
            }
        }
        if (this.l != -1) {
            for (int i6 = 0; i6 < childCount; i6++) {
                getChildAt(i6).getId();
                int i7 = this.l;
            }
        }
        b bVar = this.k;
        if (bVar != null) {
            bVar.a(this);
        }
        this.d.M0();
        int size = this.b.size();
        if (size > 0) {
            for (int i8 = 0; i8 < size; i8++) {
                ((androidx.constraintlayout.widget.a) this.b.get(i8)).e(this);
            }
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            getChildAt(i9);
        }
        int i10 = 0;
        while (i10 < childCount) {
            View childAt2 = getChildAt(i10);
            i iVarF = f(childAt2);
            if (iVarF != null) {
                a aVar = (a) childAt2.getLayoutParams();
                aVar.a();
                if (aVar.m0) {
                    aVar.m0 = r3;
                } else if (zIsInEditMode) {
                    try {
                        String resourceName2 = getResources().getResourceName(childAt2.getId());
                        k(r3, resourceName2, Integer.valueOf(childAt2.getId()));
                        d(childAt2.getId()).X(resourceName2.substring(resourceName2.indexOf("id/") + 3));
                    } catch (Resources.NotFoundException unused2) {
                    }
                }
                iVarF.x0(childAt2.getVisibility());
                if (aVar.a0) {
                    iVarF.x0(8);
                }
                iVarF.W(childAt2);
                this.d.I0(iVarF);
                if (!aVar.W || !aVar.V) {
                    this.c.add(iVarF);
                }
                if (aVar.Y) {
                    i iVar = iVarF;
                    int i11 = aVar.i0;
                    int i12 = aVar.j0;
                    float f2 = aVar.k0;
                    if (f2 != -1.0f) {
                        iVar.L0(f2);
                    } else if (i11 != -1) {
                        iVar.J0(i11);
                    } else if (i12 != -1) {
                        iVar.K0(i12);
                    }
                } else if (aVar.d != -1 || aVar.e != -1 || aVar.f != -1 || aVar.g != -1 || aVar.q != -1 || aVar.p != -1 || aVar.r != -1 || aVar.f20s != -1 || aVar.h != -1 || aVar.i != -1 || aVar.j != -1 || aVar.k != -1 || aVar.l != -1 || aVar.Q != -1 || aVar.R != -1 || aVar.m != -1 || ((ViewGroup.MarginLayoutParams) aVar).width == -1 || ((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i13 = aVar.b0;
                    int i14 = aVar.c0;
                    int i15 = aVar.d0;
                    int i16 = aVar.e0;
                    int i17 = aVar.f0;
                    int i18 = aVar.g0;
                    float f3 = aVar.h0;
                    int i19 = aVar.m;
                    if (i19 != -1) {
                        f fVarD5 = d(i19);
                        if (fVarD5 != null) {
                            iVarF.f(fVarD5, aVar.o, aVar.n);
                        }
                    } else {
                        if (i13 != -1) {
                            f fVarD6 = d(i13);
                            if (fVarD6 != null) {
                                e.d dVar = e.d.b;
                                f = f3;
                                i = i18;
                                i2 = i16;
                                iVarF.J(dVar, fVarD6, dVar, ((ViewGroup.MarginLayoutParams) aVar).leftMargin, i17);
                            } else {
                                f = f3;
                                i = i18;
                                i2 = i16;
                            }
                        } else {
                            f = f3;
                            i = i18;
                            i2 = i16;
                            if (i14 != -1 && (fVarD = d(i14)) != null) {
                                iVarF.J(e.d.b, fVarD, e.d.d, ((ViewGroup.MarginLayoutParams) aVar).leftMargin, i17);
                            }
                        }
                        if (i15 != -1) {
                            f fVarD7 = d(i15);
                            if (fVarD7 != null) {
                                iVarF.J(e.d.d, fVarD7, e.d.b, ((ViewGroup.MarginLayoutParams) aVar).rightMargin, i);
                            }
                        } else {
                            int i20 = i2;
                            if (i20 != -1 && (fVarD2 = d(i20)) != null) {
                                e.d dVar2 = e.d.d;
                                iVarF.J(dVar2, fVarD2, dVar2, ((ViewGroup.MarginLayoutParams) aVar).rightMargin, i);
                            }
                        }
                        int i21 = aVar.h;
                        if (i21 != -1) {
                            f fVarD8 = d(i21);
                            if (fVarD8 != null) {
                                e.d dVar3 = e.d.c;
                                iVarF.J(dVar3, fVarD8, dVar3, ((ViewGroup.MarginLayoutParams) aVar).topMargin, aVar.u);
                            }
                        } else {
                            int i22 = aVar.i;
                            if (i22 != -1 && (fVarD3 = d(i22)) != null) {
                                iVarF.J(e.d.c, fVarD3, e.d.e, ((ViewGroup.MarginLayoutParams) aVar).topMargin, aVar.u);
                            }
                        }
                        int i23 = aVar.j;
                        if (i23 != -1) {
                            f fVarD9 = d(i23);
                            if (fVarD9 != null) {
                                iVarF.J(e.d.e, fVarD9, e.d.c, ((ViewGroup.MarginLayoutParams) aVar).bottomMargin, aVar.w);
                            }
                        } else {
                            int i24 = aVar.k;
                            if (i24 != -1 && (fVarD4 = d(i24)) != null) {
                                e.d dVar4 = e.d.e;
                                iVarF.J(dVar4, fVarD4, dVar4, ((ViewGroup.MarginLayoutParams) aVar).bottomMargin, aVar.w);
                            }
                        }
                        int i25 = aVar.l;
                        if (i25 != -1) {
                            View view = (View) this.a.get(i25);
                            f fVarD10 = d(aVar.l);
                            if (fVarD10 != null && view != null && (view.getLayoutParams() instanceof a)) {
                                a aVar2 = (a) view.getLayoutParams();
                                aVar.X = true;
                                aVar2.X = true;
                                e.d dVar5 = e.d.f;
                                iVarF.h(dVar5).a(fVarD10.h(dVar5), 0, -1, e.c.b, 0, true);
                                iVarF.h(e.d.c).m();
                                iVarF.h(e.d.e).m();
                            }
                        }
                        if (f >= 0.0f && f != 0.5f) {
                            iVarF.d0(f);
                        }
                        float f4 = aVar.A;
                        if (f4 >= 0.0f && f4 != 0.5f) {
                            iVarF.r0(f4);
                        }
                    }
                    if (zIsInEditMode && ((i3 = aVar.Q) != -1 || aVar.R != -1)) {
                        iVarF.o0(i3, aVar.R);
                    }
                    if (aVar.V) {
                        iVarF.g0(f.b.a);
                        iVarF.y0(((ViewGroup.MarginLayoutParams) aVar).width);
                    } else if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                        iVarF.g0(f.b.d);
                        iVarF.h(e.d.b).e = ((ViewGroup.MarginLayoutParams) aVar).leftMargin;
                        iVarF.h(e.d.d).e = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                    } else {
                        iVarF.g0(f.b.c);
                        iVarF.y0(0);
                    }
                    if (aVar.W) {
                        r3 = 0;
                        iVarF.u0(f.b.a);
                        iVarF.b0(((ViewGroup.MarginLayoutParams) aVar).height);
                    } else if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                        iVarF.u0(f.b.d);
                        iVarF.h(e.d.c).e = ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                        iVarF.h(e.d.e).e = ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                        r3 = 0;
                    } else {
                        iVarF.u0(f.b.c);
                        r3 = 0;
                        iVarF.b0(0);
                    }
                    String str = aVar.B;
                    if (str != null) {
                        iVarF.Y(str);
                    }
                    iVarF.i0(aVar.E);
                    iVarF.w0(aVar.F);
                    iVarF.e0(aVar.G);
                    iVarF.s0(aVar.H);
                    iVarF.h0(aVar.I, aVar.K, aVar.M, aVar.O);
                    iVarF.v0(aVar.J, aVar.L, aVar.N, aVar.P);
                }
            }
            i10++;
            r3 = r3;
        }
    }

    private void l(int i, int i2) {
        int iMin;
        f.b bVar;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        f.b bVar2 = f.b.a;
        getLayoutParams();
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                bVar = f.b.b;
            } else if (mode != 1073741824) {
                bVar = bVar2;
            } else {
                iMin = Math.min(this.g, size) - paddingLeft;
                bVar = bVar2;
            }
            iMin = 0;
        } else {
            iMin = size;
            bVar = f.b.b;
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                bVar2 = f.b.b;
            } else if (mode2 == 1073741824) {
                size2 = Math.min(this.h, size2) - paddingTop;
            }
            size2 = 0;
        } else {
            bVar2 = f.b.b;
        }
        this.d.m0(0);
        this.d.l0(0);
        this.d.g0(bVar);
        this.d.y0(iMin);
        this.d.u0(bVar2);
        this.d.b0(size2);
        this.d.m0((this.e - getPaddingLeft()) - getPaddingRight());
        this.d.l0((this.f - getPaddingTop()) - getPaddingBottom());
    }

    private void n() {
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).isLayoutRequested()) {
                z = true;
                break;
            }
        }
        if (z) {
            this.c.clear();
            j();
        }
    }

    private void o() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i);
        }
        int size = this.b.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                ((androidx.constraintlayout.widget.a) this.b.get(i2)).d(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    public Object c(int i, Object obj) {
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

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = getWidth();
            float height = getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i2 = Integer.parseInt(strArrSplit[0]);
                        int i3 = Integer.parseInt(strArrSplit[1]);
                        int i4 = Integer.parseInt(strArrSplit[2]);
                        int i5 = (int) ((i2 / 1080.0f) * width);
                        int i6 = (int) ((i3 / 1920.0f) * height);
                        int i7 = (int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = i5;
                        float f2 = i6;
                        float f3 = i5 + ((int) ((i4 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float f4 = i6 + i7;
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

    public View e(int i) {
        return (View) this.a.get(i);
    }

    public final f f(View view) {
        if (view == this) {
            return this.d;
        }
        if (view == null) {
            return null;
        }
        return ((a) view.getLayoutParams()).l0;
    }

    public int getMaxHeight() {
        return this.h;
    }

    public int getMaxWidth() {
        return this.g;
    }

    public int getMinHeight() {
        return this.f;
    }

    public int getMinWidth() {
        return this.e;
    }

    public int getOptimizationLevel() {
        return this.d.R0();
    }

    public void k(int i, Object obj, Object obj2) {
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

    protected void m(String str) {
        this.d.K0();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            a aVar = (a) childAt.getLayoutParams();
            f fVar = aVar.l0;
            if ((childAt.getVisibility() != 8 || aVar.Y || aVar.Z || zIsInEditMode) && !aVar.a0) {
                int iP = fVar.p();
                int iQ = fVar.q();
                childAt.layout(iP, iQ, fVar.D() + iP, fVar.r() + iQ);
            }
        }
        int size = this.b.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((androidx.constraintlayout.widget.a) this.b.get(i6)).c(this);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0117 A[PHI: r11
      0x0117: PHI (r11v1 int) = (r11v0 int), (r11v10 int), (r11v10 int) binds: [B:38:0x00ca, B:54:0x010a, B:56:0x010e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        int i4;
        int baseline;
        int i5;
        ConstraintLayout constraintLayout = this;
        System.currentTimeMillis();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        constraintLayout.d.C0(paddingLeft);
        constraintLayout.d.D0(paddingTop);
        constraintLayout.d.k0(constraintLayout.g);
        constraintLayout.d.j0(constraintLayout.h);
        int iCombineMeasuredStates = 0;
        constraintLayout.d.d1(getLayoutDirection() == 1);
        l(i, i2);
        int iD = constraintLayout.d.D();
        int iR = constraintLayout.d.r();
        if (constraintLayout.i) {
            constraintLayout.i = false;
            n();
            z = true;
        } else {
            z = false;
        }
        boolean z4 = (constraintLayout.j & 8) == 8;
        if (z4) {
            constraintLayout.d.a1();
            constraintLayout.d.Y0(iD, iR);
            i(i, i2);
        } else {
            h(i, i2);
        }
        o();
        if (getChildCount() > 0 && z) {
            l.a.a(constraintLayout.d);
        }
        g gVar = constraintLayout.d;
        if (gVar.I0) {
            if (gVar.J0 && mode == Integer.MIN_VALUE) {
                int i6 = gVar.L0;
                if (i6 < size) {
                    gVar.y0(i6);
                }
                constraintLayout.d.g0(f.b.a);
            }
            g gVar2 = constraintLayout.d;
            if (gVar2.K0 && mode2 == Integer.MIN_VALUE) {
                int i7 = gVar2.M0;
                if (i7 < size2) {
                    gVar2.b0(i7);
                }
                constraintLayout.d.u0(f.b.a);
            }
        }
        if ((constraintLayout.j & 32) == 32) {
            int iD2 = constraintLayout.d.D();
            int iR2 = constraintLayout.d.r();
            if (constraintLayout.n != iD2 && mode == 1073741824) {
                l.a.i(constraintLayout.d.H0, 0, iD2);
            }
            if (constraintLayout.o != iR2 && mode2 == 1073741824) {
                l.a.i(constraintLayout.d.H0, 1, iR2);
            }
            g gVar3 = constraintLayout.d;
            if (!gVar3.J0 || gVar3.L0 <= size) {
                iCombineMeasuredStates = 0;
            } else {
                iCombineMeasuredStates = 0;
                l.a.i(gVar3.H0, 0, size);
            }
            g gVar4 = constraintLayout.d;
            if (gVar4.K0 && gVar4.M0 > size2) {
                l.a.i(gVar4.H0, 1, size2);
            }
        }
        if (getChildCount() > 0) {
            constraintLayout.m("First pass");
        }
        int size3 = constraintLayout.c.size();
        int paddingBottom = paddingTop + getPaddingBottom();
        int paddingRight = paddingLeft + getPaddingRight();
        if (size3 > 0) {
            f.b bVarS = constraintLayout.d.s();
            f.b bVar = f.b.b;
            boolean z5 = bVarS == bVar;
            boolean z6 = constraintLayout.d.B() == bVar;
            int iMax = Math.max(constraintLayout.d.D(), constraintLayout.e);
            int iMax2 = Math.max(constraintLayout.d.r(), constraintLayout.f);
            int iMax3 = iMax;
            int i8 = 0;
            boolean z7 = false;
            while (i8 < size3) {
                f fVar = (f) constraintLayout.c.get(i8);
                int i9 = size3;
                View view = (View) fVar.m();
                if (view == null) {
                    i4 = iD;
                    i3 = iR;
                } else {
                    i3 = iR;
                    a aVar = (a) view.getLayoutParams();
                    i4 = iD;
                    if (!aVar.Z && !aVar.Y && view.getVisibility() != 8 && (!z4 || !fVar.x().d() || !fVar.w().d())) {
                        int i10 = ((ViewGroup.MarginLayoutParams) aVar).width;
                        int childMeasureSpec = (i10 == -2 && aVar.V) ? ViewGroup.getChildMeasureSpec(i, paddingRight, i10) : View.MeasureSpec.makeMeasureSpec(fVar.D(), 1073741824);
                        int i11 = ((ViewGroup.MarginLayoutParams) aVar).height;
                        view.measure(childMeasureSpec, (i11 == -2 && aVar.W) ? ViewGroup.getChildMeasureSpec(i2, paddingBottom, i11) : View.MeasureSpec.makeMeasureSpec(fVar.r(), 1073741824));
                        int measuredWidth = view.getMeasuredWidth();
                        int measuredHeight = view.getMeasuredHeight();
                        if (measuredWidth != fVar.D()) {
                            fVar.y0(measuredWidth);
                            if (z4) {
                                fVar.x().h(measuredWidth);
                            }
                            if (z5) {
                                i5 = iMax3;
                                if (fVar.y() > i5) {
                                    iMax3 = Math.max(i5, fVar.y() + fVar.h(e.d.d).d());
                                }
                                z7 = true;
                            } else {
                                i5 = iMax3;
                            }
                            iMax3 = i5;
                            z7 = true;
                        }
                        if (measuredHeight != fVar.r()) {
                            fVar.b0(measuredHeight);
                            if (z4) {
                                fVar.w().h(measuredHeight);
                            }
                            if (z6 && fVar.l() > iMax2) {
                                iMax2 = Math.max(iMax2, fVar.l() + fVar.h(e.d.e).d());
                            }
                            z7 = true;
                        }
                        if (aVar.X && (baseline = view.getBaseline()) != -1 && baseline != fVar.j()) {
                            fVar.V(baseline);
                            z7 = true;
                        }
                        iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                    }
                    i8++;
                    constraintLayout = this;
                    iD = i4;
                    size3 = i9;
                    iR = i3;
                }
                iMax3 = iMax3;
                i8++;
                constraintLayout = this;
                iD = i4;
                size3 = i9;
                iR = i3;
            }
            int i12 = size3;
            int i13 = iD;
            int i14 = iR;
            int i15 = iMax3;
            constraintLayout = this;
            if (z7) {
                constraintLayout.d.y0(i13);
                constraintLayout.d.b0(i14);
                if (z4) {
                    constraintLayout.d.e1();
                }
                constraintLayout.m("2nd pass");
                if (constraintLayout.d.D() < i15) {
                    constraintLayout.d.y0(i15);
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (constraintLayout.d.r() < iMax2) {
                    constraintLayout.d.b0(iMax2);
                    z3 = true;
                } else {
                    z3 = z2;
                }
                if (z3) {
                    constraintLayout.m("3rd pass");
                }
            }
            for (int i16 = 0; i16 < i12; i16++) {
                f fVar2 = (f) constraintLayout.c.get(i16);
                View view2 = (View) fVar2.m();
                if (view2 != null && (view2.getMeasuredWidth() != fVar2.D() || view2.getMeasuredHeight() != fVar2.r())) {
                    if (fVar2.C() != 8) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(fVar2.D(), 1073741824), View.MeasureSpec.makeMeasureSpec(fVar2.r(), 1073741824));
                    }
                }
            }
        } else {
            iCombineMeasuredStates = 0;
        }
        int iD3 = constraintLayout.d.D() + paddingRight;
        int iR3 = constraintLayout.d.r() + paddingBottom;
        int iResolveSizeAndState = View.resolveSizeAndState(iD3, i, iCombineMeasuredStates);
        int iResolveSizeAndState2 = View.resolveSizeAndState(iR3, i2, iCombineMeasuredStates << 16) & 16777215;
        int iMin = Math.min(constraintLayout.g, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(constraintLayout.h, iResolveSizeAndState2);
        if (constraintLayout.d.V0()) {
            iMin |= 16777216;
        }
        if (constraintLayout.d.T0()) {
            iMin2 |= 16777216;
        }
        constraintLayout.setMeasuredDimension(iMin, iMin2);
        constraintLayout.n = iMin;
        constraintLayout.o = iMin2;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        f fVarF = f(view);
        if ((view instanceof c) && !(fVarF instanceof i)) {
            a aVar = (a) view.getLayoutParams();
            i iVar = new i();
            aVar.l0 = iVar;
            aVar.Y = true;
            iVar.M0(aVar.S);
        }
        if (view instanceof androidx.constraintlayout.widget.a) {
            androidx.constraintlayout.widget.a aVar2 = (androidx.constraintlayout.widget.a) view;
            aVar2.f();
            ((a) view.getLayoutParams()).Z = true;
            if (!this.b.contains(aVar2)) {
                this.b.add(aVar2);
            }
        }
        this.a.put(view.getId(), view);
        this.i = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.a.remove(view.getId());
        f fVarF = f(view);
        this.d.L0(fVarF);
        this.b.remove(view);
        this.c.remove(fVarF);
        this.i = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        this.i = true;
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = 0;
        this.f19s = 0;
    }

    public void setConstraintSet(b bVar) {
        this.k = bVar;
    }

    @Override // android.view.View
    public void setId(int i) {
        this.a.remove(getId());
        super.setId(i);
        this.a.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i == this.h) {
            return;
        }
        this.h = i;
        requestLayout();
    }

    public void setMaxWidth(int i) {
        if (i == this.g) {
            return;
        }
        this.g = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.f) {
            return;
        }
        this.f = i;
        requestLayout();
    }

    public void setMinWidth(int i) {
        if (i == this.e) {
            return;
        }
        this.e = i;
        requestLayout();
    }

    public void setOptimizationLevel(int i) {
        this.d.c1(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    public static class a extends ViewGroup.MarginLayoutParams {
        public float A;
        public String B;
        float C;
        int D;
        public float E;
        public float F;
        public int G;
        public int H;
        public int I;
        public int J;
        public int K;
        public int L;
        public int M;
        public int N;
        public float O;
        public float P;
        public int Q;
        public int R;
        public int S;
        public boolean T;
        public boolean U;
        boolean V;
        boolean W;
        boolean X;
        boolean Y;
        boolean Z;
        public int a;
        boolean a0;
        public int b;
        int b0;
        public float c;
        int c0;
        public int d;
        int d0;
        public int e;
        int e0;
        public int f;
        int f0;
        public int g;
        int g0;
        public int h;
        float h0;
        public int i;
        int i0;
        public int j;
        int j0;
        public int k;
        float k0;
        public int l;
        f l0;
        public int m;
        public boolean m0;
        public int n;
        public float o;
        public int p;
        public int q;
        public int r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f20s;
        public int t;
        public int u;
        public int v;
        public int w;
        public int x;
        public int y;
        public float z;

        /* JADX INFO: renamed from: androidx.constraintlayout.widget.ConstraintLayout$a$a, reason: collision with other inner class name */
        private static class C0019a {
            public static final SparseIntArray a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                a = sparseIntArray;
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
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
            }
        }

        public a(Context context, AttributeSet attributeSet) {
            int i;
            super(context, attributeSet);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.f20s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 0.0f;
            this.D = 1;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 1.0f;
            this.P = 1.0f;
            this.Q = -1;
            this.R = -1;
            this.S = -1;
            this.T = false;
            this.U = false;
            this.V = true;
            this.W = true;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = 0.5f;
            this.l0 = new f();
            this.m0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.a);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                int i3 = C0019a.a.get(index);
                switch (i3) {
                    case 1:
                        this.S = typedArrayObtainStyledAttributes.getInt(index, this.S);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                        this.m = resourceId;
                        if (resourceId == -1) {
                            this.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.n);
                        break;
                    case 4:
                        float f = typedArrayObtainStyledAttributes.getFloat(index, this.o) % 360.0f;
                        this.o = f;
                        if (f < 0.0f) {
                            this.o = (360.0f - f) % 360.0f;
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
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.d);
                        this.d = resourceId2;
                        if (resourceId2 == -1) {
                            this.d = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
                        this.e = resourceId3;
                        if (resourceId3 == -1) {
                            this.e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.f);
                        this.f = resourceId4;
                        if (resourceId4 == -1) {
                            this.f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                        this.g = resourceId5;
                        if (resourceId5 == -1) {
                            this.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.h);
                        this.h = resourceId6;
                        if (resourceId6 == -1) {
                            this.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.i);
                        this.i = resourceId7;
                        if (resourceId7 == -1) {
                            this.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                        this.j = resourceId8;
                        if (resourceId8 == -1) {
                            this.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                        this.k = resourceId9;
                        if (resourceId9 == -1) {
                            this.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.l);
                        this.l = resourceId10;
                        if (resourceId10 == -1) {
                            this.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                        this.p = resourceId11;
                        if (resourceId11 == -1) {
                            this.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.q);
                        this.q = resourceId12;
                        if (resourceId12 == -1) {
                            this.q = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.r);
                        this.r = resourceId13;
                        if (resourceId13 == -1) {
                            this.r = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.f20s);
                        this.f20s = resourceId14;
                        if (resourceId14 == -1) {
                            this.f20s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.t);
                        break;
                    case 22:
                        this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.u);
                        break;
                    case 23:
                        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.v);
                        break;
                    case 24:
                        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        break;
                    case 25:
                        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        break;
                    case 26:
                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        break;
                    case 27:
                        this.T = typedArrayObtainStyledAttributes.getBoolean(index, this.T);
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_actionModeStyle /* 28 */:
                        this.U = typedArrayObtainStyledAttributes.getBoolean(index, this.U);
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_actionModeWebSearchDrawable /* 29 */:
                        this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_actionOverflowButtonStyle /* 30 */:
                        this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                        break;
                    case 31:
                        int i4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.I = i4;
                        if (i4 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        int i5 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.J = i5;
                        if (i5 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        }
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogButtonGroupStyle /* 33 */:
                        try {
                            this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.K) == -2) {
                                this.K = -2;
                            }
                        }
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogCenterButtons /* 34 */:
                        try {
                            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.M) == -2) {
                                this.M = -2;
                            }
                        }
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogStyle /* 35 */:
                        this.O = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.O));
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogTheme /* 36 */:
                        try {
                            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.L) == -2) {
                                this.L = -2;
                            }
                        }
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_autoCompleteTextViewStyle /* 37 */:
                        try {
                            this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.N) == -2) {
                                this.N = -2;
                            }
                        }
                        break;
                    case androidx.appcompat.R$styleable.AppCompatTheme_borderlessButtonStyle /* 38 */:
                        this.P = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.P));
                        break;
                    default:
                        switch (i3) {
                            case androidx.appcompat.R$styleable.AppCompatTheme_buttonStyle /* 44 */:
                                String string = typedArrayObtainStyledAttributes.getString(index);
                                this.B = string;
                                this.C = Float.NaN;
                                this.D = -1;
                                if (string != null) {
                                    int length = string.length();
                                    int iIndexOf = this.B.indexOf(44);
                                    if (iIndexOf <= 0 || iIndexOf >= length - 1) {
                                        i = 0;
                                    } else {
                                        String strSubstring = this.B.substring(0, iIndexOf);
                                        if (strSubstring.equalsIgnoreCase("W")) {
                                            this.D = 0;
                                        } else if (strSubstring.equalsIgnoreCase("H")) {
                                            this.D = 1;
                                        }
                                        i = iIndexOf + 1;
                                    }
                                    int iIndexOf2 = this.B.indexOf(58);
                                    if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                                        String strSubstring2 = this.B.substring(i);
                                        if (strSubstring2.length() > 0) {
                                            this.C = Float.parseFloat(strSubstring2);
                                        }
                                    } else {
                                        String strSubstring3 = this.B.substring(i, iIndexOf2);
                                        String strSubstring4 = this.B.substring(iIndexOf2 + 1);
                                        if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                                            try {
                                                float f2 = Float.parseFloat(strSubstring3);
                                                float f3 = Float.parseFloat(strSubstring4);
                                                if (f2 > 0.0f && f3 > 0.0f) {
                                                    if (this.D == 1) {
                                                        this.C = Math.abs(f3 / f2);
                                                    } else {
                                                        this.C = Math.abs(f2 / f3);
                                                    }
                                                }
                                            } catch (NumberFormatException unused5) {
                                            }
                                        }
                                    }
                                }
                                break;
                            case androidx.appcompat.R$styleable.AppCompatTheme_buttonStyleSmall /* 45 */:
                                this.E = typedArrayObtainStyledAttributes.getFloat(index, this.E);
                                break;
                            case androidx.appcompat.R$styleable.AppCompatTheme_checkboxStyle /* 46 */:
                                this.F = typedArrayObtainStyledAttributes.getFloat(index, this.F);
                                break;
                            case androidx.appcompat.R$styleable.AppCompatTheme_checkedTextViewStyle /* 47 */:
                                this.G = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case androidx.appcompat.R$styleable.AppCompatTheme_colorAccent /* 48 */:
                                this.H = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case androidx.appcompat.R$styleable.AppCompatTheme_colorBackgroundFloating /* 49 */:
                                this.Q = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.Q);
                                break;
                            case androidx.appcompat.R$styleable.AppCompatTheme_colorButtonNormal /* 50 */:
                                this.R = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.R);
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            a();
        }

        public void a() {
            this.Y = false;
            this.V = true;
            this.W = true;
            int i = ((ViewGroup.MarginLayoutParams) this).width;
            if (i == -2 && this.T) {
                this.V = false;
                this.I = 1;
            }
            int i2 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i2 == -2 && this.U) {
                this.W = false;
                this.J = 1;
            }
            if (i == 0 || i == -1) {
                this.V = false;
                if (i == 0 && this.I == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.T = true;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.W = false;
                if (i2 == 0 && this.J == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.U = true;
                }
            }
            if (this.c == -1.0f && this.a == -1 && this.b == -1) {
                return;
            }
            this.Y = true;
            this.V = true;
            this.W = true;
            if (!(this.l0 instanceof i)) {
                this.l0 = new i();
            }
            this.l0.M0(this.S);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0048  */
        /* JADX WARN: Code duplicated, block: B:19:0x004f  */
        /* JADX WARN: Code duplicated, block: B:22:0x0056  */
        /* JADX WARN: Code duplicated, block: B:25:0x005c  */
        /* JADX WARN: Code duplicated, block: B:28:0x0062  */
        /* JADX WARN: Code duplicated, block: B:35:0x0078  */
        /* JADX WARN: Code duplicated, block: B:36:0x0080  */
        /* JADX WARN: Code duplicated, block: B:38:0x0084  */
        /* JADX WARN: Code duplicated, block: B:39:0x008b  */
        /* JADX WARN: Code duplicated, block: B:41:0x008f  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        public void resolveLayoutDirection(int i) {
            int i2;
            int i3;
            int i4;
            int i5;
            float f;
            int i6;
            int i7;
            int i8 = ((ViewGroup.MarginLayoutParams) this).leftMargin;
            int i9 = ((ViewGroup.MarginLayoutParams) this).rightMargin;
            super.resolveLayoutDirection(i);
            this.d0 = -1;
            this.e0 = -1;
            this.b0 = -1;
            this.c0 = -1;
            this.f0 = this.t;
            this.g0 = this.v;
            this.h0 = this.z;
            this.i0 = this.a;
            this.j0 = this.b;
            this.k0 = this.c;
            boolean z = false;
            if (1 == getLayoutDirection()) {
                int i10 = this.p;
                if (i10 != -1) {
                    this.d0 = i10;
                } else {
                    int i11 = this.q;
                    if (i11 != -1) {
                        this.e0 = i11;
                    } else {
                        i2 = this.r;
                        if (i2 != -1) {
                            this.c0 = i2;
                            z = true;
                        }
                        i3 = this.f20s;
                        if (i3 != -1) {
                            this.b0 = i3;
                            z = true;
                        }
                        i4 = this.x;
                        if (i4 != -1) {
                            this.g0 = i4;
                        }
                        i5 = this.y;
                        if (i5 != -1) {
                            this.f0 = i5;
                        }
                        if (z) {
                            this.h0 = 1.0f - this.z;
                        }
                        if (this.Y && this.S == 1) {
                            f = this.c;
                            if (f != -1.0f) {
                                this.k0 = 1.0f - f;
                                this.i0 = -1;
                                this.j0 = -1;
                            } else {
                                i6 = this.a;
                                if (i6 != -1) {
                                    this.j0 = i6;
                                    this.i0 = -1;
                                    this.k0 = -1.0f;
                                } else {
                                    i7 = this.b;
                                    if (i7 != -1) {
                                        this.i0 = i7;
                                        this.j0 = -1;
                                        this.k0 = -1.0f;
                                    }
                                }
                            }
                        }
                    }
                }
                z = true;
                i2 = this.r;
                if (i2 != -1) {
                    this.c0 = i2;
                    z = true;
                }
                i3 = this.f20s;
                if (i3 != -1) {
                    this.b0 = i3;
                    z = true;
                }
                i4 = this.x;
                if (i4 != -1) {
                    this.g0 = i4;
                }
                i5 = this.y;
                if (i5 != -1) {
                    this.f0 = i5;
                }
                if (z) {
                    this.h0 = 1.0f - this.z;
                }
                if (this.Y) {
                    f = this.c;
                    if (f != -1.0f) {
                        this.k0 = 1.0f - f;
                        this.i0 = -1;
                        this.j0 = -1;
                    } else {
                        i6 = this.a;
                        if (i6 != -1) {
                            this.j0 = i6;
                            this.i0 = -1;
                            this.k0 = -1.0f;
                        } else {
                            i7 = this.b;
                            if (i7 != -1) {
                                this.i0 = i7;
                                this.j0 = -1;
                                this.k0 = -1.0f;
                            }
                        }
                    }
                }
            } else {
                int i12 = this.p;
                if (i12 != -1) {
                    this.c0 = i12;
                }
                int i13 = this.q;
                if (i13 != -1) {
                    this.b0 = i13;
                }
                int i14 = this.r;
                if (i14 != -1) {
                    this.d0 = i14;
                }
                int i15 = this.f20s;
                if (i15 != -1) {
                    this.e0 = i15;
                }
                int i16 = this.x;
                if (i16 != -1) {
                    this.f0 = i16;
                }
                int i17 = this.y;
                if (i17 != -1) {
                    this.g0 = i17;
                }
            }
            if (this.r == -1 && this.f20s == -1 && this.q == -1 && this.p == -1) {
                int i18 = this.f;
                if (i18 != -1) {
                    this.d0 = i18;
                    if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i9 > 0) {
                        ((ViewGroup.MarginLayoutParams) this).rightMargin = i9;
                    }
                } else {
                    int i19 = this.g;
                    if (i19 != -1) {
                        this.e0 = i19;
                        if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i9 > 0) {
                            ((ViewGroup.MarginLayoutParams) this).rightMargin = i9;
                        }
                    }
                }
                int i20 = this.d;
                if (i20 != -1) {
                    this.b0 = i20;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i8 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i8;
                    return;
                }
                int i21 = this.e;
                if (i21 != -1) {
                    this.c0 = i21;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i8 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i8;
                }
            }
        }

        public a(int i, int i2) {
            super(i, i2);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.f20s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 0.0f;
            this.D = 1;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 1.0f;
            this.P = 1.0f;
            this.Q = -1;
            this.R = -1;
            this.S = -1;
            this.T = false;
            this.U = false;
            this.V = true;
            this.W = true;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = 0.5f;
            this.l0 = new f();
            this.m0 = false;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.f20s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 0.0f;
            this.D = 1;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 1.0f;
            this.P = 1.0f;
            this.Q = -1;
            this.R = -1;
            this.S = -1;
            this.T = false;
            this.U = false;
            this.V = true;
            this.W = true;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = 0.5f;
            this.l0 = new f();
            this.m0 = false;
        }
    }
}
