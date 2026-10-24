package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.c1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static final Interpolator w = new a();
    private int a;
    private int b;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private OverScroller q;
    private final AbstractC0027c r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private View f23s;
    private boolean t;
    private final ViewGroup u;
    private int c = -1;
    private final Runnable v = new b();

    static class a implements Interpolator {
        a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.F(0);
        }
    }

    /* JADX INFO: renamed from: androidx.customview.widget.c$c, reason: collision with other inner class name */
    public static abstract class AbstractC0027c {
        public abstract int clampViewPositionHorizontal(View view, int i, int i2);

        public abstract int clampViewPositionVertical(View view, int i, int i2);

        public int getOrderedChildIndex(int i) {
            return i;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i, int i2) {
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        public void onEdgeTouched(int i, int i2) {
        }

        public void onViewCaptured(View view, int i) {
        }

        public abstract void onViewDragStateChanged(int i);

        public abstract void onViewPositionChanged(View view, int i, int i2, int i3, int i4);

        public abstract void onViewReleased(View view, float f, float f2);

        public abstract boolean tryCaptureView(View view, int i);
    }

    private c(Context context, ViewGroup viewGroup, AbstractC0027c abstractC0027c) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (abstractC0027c == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.u = viewGroup;
        this.r = abstractC0027c;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.b = viewConfiguration.getScaledTouchSlop();
        this.m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.q = new OverScroller(context, w);
    }

    private void B() {
        this.l.computeCurrentVelocity(1000, this.m);
        n(e(this.l.getXVelocity(this.c), this.n, this.m), e(this.l.getYVelocity(this.c), this.n, this.m));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.customview.widget.c$c] */
    /* JADX WARN: Type inference failed for: r4v2 */
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
    private void C(float f, float f2, int i) {
        int i2;
        boolean zC = c(f, f2, i, 1);
        ?? r0 = zC;
        if (c(f2, f, i, 4)) {
            r0 = (zC ? 1 : 0) | 4;
        }
        ?? r1 = r0;
        if (c(f, f2, i, 2)) {
            r1 = (r0 == true ? 1 : 0) | 2;
        }
        ?? r2 = r1;
        if (c(f2, f, i, 8)) {
            i2 = (r1 == true ? 1 : 0) | 8;
        }
        if (r2 == 0) {
            r2 = i2;
            return;
        }
        r2 = i2;
        int[] iArr = this.i;
        iArr[i] = (iArr[i] | r2) == true ? 1 : 0;
        this.r.onEdgeDragStarted(r2, i);
    }

    private void D(float f, float f2, int i) {
        q(i);
        float[] fArr = this.d;
        this.f[i] = f;
        fArr[i] = f;
        float[] fArr2 = this.e;
        this.g[i] = f2;
        fArr2[i] = f2;
        this.h[i] = t((int) f, (int) f2);
        this.k |= 1 << i;
    }

    private void E(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (y(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    private boolean c(float f, float f2, int i, int i2) {
        float fAbs = Math.abs(f);
        float fAbs2 = Math.abs(f2);
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        int i3 = this.b;
        if (fAbs <= i3 && fAbs2 <= i3) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.r.onEdgeLock(i2)) {
            return (this.i[i] & i2) == 0 && fAbs > ((float) this.b);
        }
        int[] iArr = this.j;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    private boolean d(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.r.getViewHorizontalDragRange(view) > 0;
        boolean z2 = this.r.getViewVerticalDragRange(view) > 0;
        if (z && z2) {
            float f3 = (f * f) + (f2 * f2);
            int i = this.b;
            return f3 > ((float) (i * i));
        }
        if (z) {
            return Math.abs(f) > ((float) this.b);
        }
        return z2 && Math.abs(f2) > ((float) this.b);
    }

    private float e(float f, float f2, float f3) {
        float fAbs = Math.abs(f);
        if (fAbs < f2) {
            return 0.0f;
        }
        if (fAbs > f3) {
            return f > 0.0f ? f3 : -f3;
        }
        return f;
    }

    private int f(int i, int i2, int i3) {
        int iAbs = Math.abs(i);
        if (iAbs < i2) {
            return 0;
        }
        if (iAbs > i3) {
            return i > 0 ? i3 : -i3;
        }
        return i;
    }

    private void g() {
        float[] fArr = this.d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.e, 0.0f);
        Arrays.fill(this.f, 0.0f);
        Arrays.fill(this.g, 0.0f);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.i, 0);
        Arrays.fill(this.j, 0);
        this.k = 0;
    }

    private void h(int i) {
        if (this.d == null || !x(i)) {
            return;
        }
        this.d[i] = 0.0f;
        this.e[i] = 0.0f;
        this.f[i] = 0.0f;
        this.g[i] = 0.0f;
        this.h[i] = 0;
        this.i[i] = 0;
        this.j[i] = 0;
        this.k = ((1 << i) ^ (-1)) & this.k;
    }

    private int i(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f = width / 2;
        float fO = f + (o(Math.min(1.0f, Math.abs(i) / width)) * f);
        int iAbs = Math.abs(i2);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fO / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f), 600);
    }

    private int j(View view, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int iF = f(i3, (int) this.n, (int) this.m);
        int iF2 = f(i4, (int) this.n, (int) this.m);
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(i2);
        int iAbs3 = Math.abs(iF);
        int iAbs4 = Math.abs(iF2);
        int i5 = iAbs3 + iAbs4;
        int i6 = iAbs + iAbs2;
        if (iF != 0) {
            f = iAbs3;
            f2 = i5;
        } else {
            f = iAbs;
            f2 = i6;
        }
        float f5 = f / f2;
        if (iF2 != 0) {
            f3 = iAbs4;
            f4 = i5;
        } else {
            f3 = iAbs2;
            f4 = i6;
        }
        return (int) ((i(i, iF, this.r.getViewHorizontalDragRange(view)) * f5) + (i(i2, iF2, this.r.getViewVerticalDragRange(view)) * (f3 / f4)));
    }

    public static c l(ViewGroup viewGroup, float f, AbstractC0027c abstractC0027c) {
        c cVarM = m(viewGroup, abstractC0027c);
        cVarM.b = (int) (cVarM.b * (1.0f / f));
        return cVarM;
    }

    public static c m(ViewGroup viewGroup, AbstractC0027c abstractC0027c) {
        return new c(viewGroup.getContext(), viewGroup, abstractC0027c);
    }

    private void n(float f, float f2) {
        this.t = true;
        this.r.onViewReleased(this.f23s, f, f2);
        this.t = false;
        if (this.a == 1) {
            F(0);
        }
    }

    private float o(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    private void p(int i, int i2, int i3, int i4) {
        int left = this.f23s.getLeft();
        int top = this.f23s.getTop();
        if (i3 != 0) {
            i = this.r.clampViewPositionHorizontal(this.f23s, i, i3);
            c1.V(this.f23s, i - left);
        }
        int i5 = i;
        if (i4 != 0) {
            i2 = this.r.clampViewPositionVertical(this.f23s, i2, i4);
            c1.W(this.f23s, i2 - top);
        }
        int i6 = i2;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        this.r.onViewPositionChanged(this.f23s, i5, i6, i5 - left, i6 - top);
    }

    private void q(int i) {
        float[] fArr = this.d;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.d = fArr2;
            this.e = fArr3;
            this.f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
    }

    private boolean s(int i, int i2, int i3, int i4) {
        int left = this.f23s.getLeft();
        int top = this.f23s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.q.abortAnimation();
            F(0);
            return false;
        }
        this.q.startScroll(left, top, i5, i6, j(this.f23s, i5, i6, i3, i4));
        F(2);
        return true;
    }

    private int t(int i, int i2) {
        int i3 = i < this.u.getLeft() + this.o ? 1 : 0;
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        return i2 > this.u.getBottom() - this.o ? i3 | 8 : i3;
    }

    private boolean y(int i) {
        if (x(i)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    public void A(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewR = r((int) x, (int) y);
            D(x, y, pointerId);
            J(viewR, pointerId);
            int i3 = this.h[pointerId];
            int i4 = this.p;
            if ((i3 & i4) != 0) {
                this.r.onEdgeTouched(i3 & i4, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.a == 1) {
                B();
            }
            a();
            return;
        }
        if (actionMasked == 2) {
            if (this.a == 1) {
                if (y(this.c)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.c);
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.f;
                    int i5 = this.c;
                    int i6 = (int) (x2 - fArr[i5]);
                    int i7 = (int) (y2 - this.g[i5]);
                    p(this.f23s.getLeft() + i6, this.f23s.getTop() + i7, i6, i7);
                    E(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i2 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i2);
                if (y(pointerId2)) {
                    float x3 = motionEvent.getX(i2);
                    float y3 = motionEvent.getY(i2);
                    float f = x3 - this.d[pointerId2];
                    float f2 = y3 - this.e[pointerId2];
                    C(f, f2, pointerId2);
                    if (this.a != 1) {
                        View viewR2 = r((int) x3, (int) y3);
                        if (d(viewR2, f, f2) && J(viewR2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i2++;
            }
            E(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.a == 1) {
                n(0.0f, 0.0f);
            }
            a();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x4 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            D(x4, y4, pointerId3);
            if (this.a != 0) {
                if (w((int) x4, (int) y4)) {
                    J(this.f23s, pointerId3);
                    return;
                }
                return;
            } else {
                J(r((int) x4, (int) y4), pointerId3);
                int i8 = this.h[pointerId3];
                int i9 = this.p;
                if ((i8 & i9) != 0) {
                    this.r.onEdgeTouched(i8 & i9, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.a == 1 && pointerId4 == this.c) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i2 >= pointerCount2) {
                    i = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i2);
                if (pointerId5 != this.c) {
                    View viewR3 = r((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                    View view = this.f23s;
                    if (viewR3 == view && J(view, pointerId5)) {
                        i = this.c;
                        break;
                    }
                }
                i2++;
            }
            if (i == -1) {
                B();
            }
        }
        h(pointerId4);
    }

    void F(int i) {
        this.u.removeCallbacks(this.v);
        if (this.a != i) {
            this.a = i;
            this.r.onViewDragStateChanged(i);
            if (this.a == 0) {
                this.f23s = null;
            }
        }
    }

    public boolean G(int i, int i2) {
        if (this.t) {
            return s(i, i2, (int) this.l.getXVelocity(this.c), (int) this.l.getYVelocity(this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ff  */
    public boolean H(MotionEvent motionEvent) {
        boolean z;
        View viewR;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                a();
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    a();
                } else if (actionMasked == 5) {
                    int pointerId = motionEvent.getPointerId(actionIndex);
                    float x = motionEvent.getX(actionIndex);
                    float y = motionEvent.getY(actionIndex);
                    D(x, y, pointerId);
                    int i = this.a;
                    if (i == 0) {
                        int i2 = this.h[pointerId];
                        int i3 = this.p;
                        if ((i2 & i3) != 0) {
                            this.r.onEdgeTouched(i2 & i3, pointerId);
                        }
                    } else if (i == 2 && (viewR = r((int) x, (int) y)) == this.f23s) {
                        J(viewR, pointerId);
                    }
                } else if (actionMasked == 6) {
                    h(motionEvent.getPointerId(actionIndex));
                }
            } else if (this.d != null && this.e != null) {
                int pointerCount = motionEvent.getPointerCount();
                for (int i4 = 0; i4 < pointerCount; i4++) {
                    int pointerId2 = motionEvent.getPointerId(i4);
                    if (y(pointerId2)) {
                        float x2 = motionEvent.getX(i4);
                        float y2 = motionEvent.getY(i4);
                        float f = x2 - this.d[pointerId2];
                        float f2 = y2 - this.e[pointerId2];
                        View viewR2 = r((int) x2, (int) y2);
                        boolean z2 = viewR2 != null && d(viewR2, f, f2);
                        if (!z2) {
                            C(f, f2, pointerId2);
                            if (this.a != 1) {
                                break;
                            }
                        } else {
                            int left = viewR2.getLeft();
                            int i5 = (int) f;
                            int iClampViewPositionHorizontal = this.r.clampViewPositionHorizontal(viewR2, left + i5, i5);
                            int top = viewR2.getTop();
                            int i6 = (int) f2;
                            int iClampViewPositionVertical = this.r.clampViewPositionVertical(viewR2, top + i6, i6);
                            int viewHorizontalDragRange = this.r.getViewHorizontalDragRange(viewR2);
                            int viewVerticalDragRange = this.r.getViewVerticalDragRange(viewR2);
                            if ((viewHorizontalDragRange == 0 || (viewHorizontalDragRange > 0 && iClampViewPositionHorizontal == left)) && (viewVerticalDragRange == 0 || (viewVerticalDragRange > 0 && iClampViewPositionVertical == top))) {
                                break;
                            }
                            C(f, f2, pointerId2);
                            if (this.a != 1 || (z2 && J(viewR2, pointerId2))) {
                                break;
                            }
                        }
                    }
                }
                E(motionEvent);
            }
            z = false;
        } else {
            float x3 = motionEvent.getX();
            float y3 = motionEvent.getY();
            z = false;
            int pointerId3 = motionEvent.getPointerId(0);
            D(x3, y3, pointerId3);
            View viewR3 = r((int) x3, (int) y3);
            if (viewR3 == this.f23s && this.a == 2) {
                J(viewR3, pointerId3);
            }
            int i7 = this.h[pointerId3];
            int i8 = this.p;
            if ((i7 & i8) != 0) {
                this.r.onEdgeTouched(i7 & i8, pointerId3);
            }
        }
        if (this.a == 1) {
            return true;
        }
        return z;
    }

    public boolean I(View view, int i, int i2) {
        this.f23s = view;
        this.c = -1;
        boolean zS = s(i, i2, 0, 0);
        if (!zS && this.a == 0 && this.f23s != null) {
            this.f23s = null;
        }
        return zS;
    }

    boolean J(View view, int i) {
        if (view == this.f23s && this.c == i) {
            return true;
        }
        if (view == null || !this.r.tryCaptureView(view, i)) {
            return false;
        }
        this.c = i;
        b(view, i);
        return true;
    }

    public void a() {
        this.c = -1;
        g();
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public void b(View view, int i) {
        if (view.getParent() == this.u) {
            this.f23s = view;
            this.c = i;
            this.r.onViewCaptured(view, i);
            F(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
    }

    public boolean k(boolean z) {
        if (this.a == 2) {
            boolean zComputeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.f23s.getLeft();
            int top = currY - this.f23s.getTop();
            if (left != 0) {
                c1.V(this.f23s, left);
            }
            if (top != 0) {
                c1.W(this.f23s, top);
            }
            if (left != 0 || top != 0) {
                this.r.onViewPositionChanged(this.f23s, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                this.q.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z) {
                    this.u.post(this.v);
                } else {
                    F(0);
                }
            }
        }
        return this.a == 2;
    }

    public View r(int i, int i2) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.getOrderedChildIndex(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public int u() {
        return this.b;
    }

    public int v() {
        return this.a;
    }

    public boolean w(int i, int i2) {
        return z(this.f23s, i, i2);
    }

    public boolean x(int i) {
        return ((1 << i) & this.k) != 0;
    }

    public boolean z(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }
}
