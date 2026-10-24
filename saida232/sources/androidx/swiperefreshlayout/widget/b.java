package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import x.h;

/* JADX INFO: loaded from: classes.dex */
public class b extends Drawable implements Animatable {
    private static final Interpolator g = new LinearInterpolator();
    private static final Interpolator h = new c0.b();
    private static final int[] i = {-16777216};
    private final c a;
    private float b;
    private Resources c;
    private Animator d;
    float e;
    boolean f;

    class a implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ c a;

        a(c cVar) {
            this.a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            b.this.n(fFloatValue, this.a);
            b.this.b(fFloatValue, this.a, false);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: renamed from: androidx.swiperefreshlayout.widget.b$b, reason: collision with other inner class name */
    class C0049b implements Animator.AnimatorListener {
        final /* synthetic */ c a;

        C0049b(c cVar) {
            this.a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            b.this.b(1.0f, this.a, true);
            this.a.A();
            this.a.l();
            b bVar = b.this;
            if (!bVar.f) {
                bVar.e += 1.0f;
                return;
            }
            bVar.f = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.a.x(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.e = 0.0f;
        }
    }

    private static class c {
        final RectF a = new RectF();
        final Paint b;
        final Paint c;
        final Paint d;
        float e;
        float f;
        float g;
        float h;
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float p;
        float q;
        int r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        int f31s;
        int t;
        int u;

        c() {
            Paint paint = new Paint();
            this.b = paint;
            Paint paint2 = new Paint();
            this.c = paint2;
            Paint paint3 = new Paint();
            this.d = paint3;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 5.0f;
            this.p = 1.0f;
            this.t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        void A() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.a;
            float f = this.q;
            float fMin = (this.h / 2.0f) + f;
            if (f <= 0.0f) {
                fMin = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(rect.centerX() - fMin, rect.centerY() - fMin, rect.centerX() + fMin, rect.centerY() + fMin);
            float f2 = this.e;
            float f3 = this.g;
            float f4 = (f2 + f3) * 360.0f;
            float f5 = ((this.f + f3) * 360.0f) - f4;
            this.b.setColor(this.u);
            this.b.setAlpha(this.t);
            float f6 = this.h / 2.0f;
            rectF.inset(f6, f6);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.d);
            float f7 = -f6;
            rectF.inset(f7, f7);
            canvas.drawArc(rectF, f4, f5, false, this.b);
            b(canvas, f4, f5, rectF);
        }

        void b(Canvas canvas, float f, float f2, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    Path path2 = new Path();
                    this.o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float fMin = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f3 = (this.r * this.p) / 2.0f;
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path3 = this.o;
                float f4 = this.r;
                float f5 = this.p;
                path3.lineTo((f4 * f5) / 2.0f, this.f31s * f5);
                this.o.offset((fMin + rectF.centerX()) - f3, rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.c.setColor(this.u);
                this.c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.c);
                canvas.restore();
            }
        }

        int c() {
            return this.t;
        }

        float d() {
            return this.f;
        }

        int e() {
            return this.i[f()];
        }

        int f() {
            return (this.j + 1) % this.i.length;
        }

        float g() {
            return this.e;
        }

        int h() {
            return this.i[this.j];
        }

        float i() {
            return this.l;
        }

        float j() {
            return this.m;
        }

        float k() {
            return this.k;
        }

        void l() {
            t(f());
        }

        void m() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            y(0.0f);
            v(0.0f);
            w(0.0f);
        }

        void n(int i) {
            this.t = i;
        }

        void o(float f, float f2) {
            this.r = (int) f;
            this.f31s = (int) f2;
        }

        void p(float f) {
            if (f != this.p) {
                this.p = f;
            }
        }

        void q(float f) {
            this.q = f;
        }

        void r(int i) {
            this.u = i;
        }

        void s(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        void t(int i) {
            this.j = i;
            this.u = this.i[i];
        }

        void u(int[] iArr) {
            this.i = iArr;
            t(0);
        }

        void v(float f) {
            this.f = f;
        }

        void w(float f) {
            this.g = f;
        }

        void x(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        void y(float f) {
            this.e = f;
        }

        void z(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
        }
    }

    public b(Context context) {
        this.c = ((Context) h.e(context)).getResources();
        c cVar = new c();
        this.a = cVar;
        cVar.u(i);
        k(2.5f);
        m();
    }

    private void a(float f, c cVar) {
        n(f, cVar);
        float fFloor = (float) (Math.floor(cVar.j() / 0.8f) + 1.0d);
        cVar.y(cVar.k() + (((cVar.i() - 0.01f) - cVar.k()) * f));
        cVar.v(cVar.i());
        cVar.w(cVar.j() + ((fFloor - cVar.j()) * f));
    }

    private int c(float f, int i2, int i3) {
        int i4 = (i2 >> 24) & 255;
        int i5 = (i2 >> 16) & 255;
        int i6 = (i2 >> 8) & 255;
        int i7 = i2 & 255;
        return ((i4 + ((int) ((((i3 >> 24) & 255) - i4) * f))) << 24) | ((i5 + ((int) ((((i3 >> 16) & 255) - i5) * f))) << 16) | ((i6 + ((int) ((((i3 >> 8) & 255) - i6) * f))) << 8) | (i7 + ((int) (f * ((i3 & 255) - i7))));
    }

    private void h(float f) {
        this.b = f;
    }

    private void i(float f, float f2, float f3, float f4) {
        c cVar = this.a;
        float f5 = this.c.getDisplayMetrics().density;
        cVar.z(f2 * f5);
        cVar.q(f * f5);
        cVar.t(0);
        cVar.o(f3 * f5, f4 * f5);
    }

    private void m() {
        c cVar = this.a;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new a(cVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(g);
        valueAnimatorOfFloat.addListener(new C0049b(cVar));
        this.d = valueAnimatorOfFloat;
    }

    void b(float f, c cVar, boolean z) {
        float interpolation;
        float interpolation2;
        if (this.f) {
            a(f, cVar);
            return;
        }
        if (f != 1.0f || z) {
            float fJ = cVar.j();
            if (f < 0.5f) {
                interpolation = cVar.k();
                interpolation2 = (h.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float fK = cVar.k() + 0.79f;
                interpolation = fK - (((1.0f - h.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = fK;
            }
            float f2 = fJ + (0.20999998f * f);
            float f3 = (f + this.e) * 216.0f;
            cVar.y(interpolation);
            cVar.v(interpolation2);
            cVar.w(f2);
            h(f3);
        }
    }

    public void d(boolean z) {
        this.a.x(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.b, bounds.exactCenterX(), bounds.exactCenterY());
        this.a.a(canvas, bounds);
        canvas.restore();
    }

    public void e(float f) {
        this.a.p(f);
        invalidateSelf();
    }

    public void f(int... iArr) {
        this.a.u(iArr);
        this.a.t(0);
        invalidateSelf();
    }

    public void g(float f) {
        this.a.w(f);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.a.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.d.isRunning();
    }

    public void j(float f, float f2) {
        this.a.y(f);
        this.a.v(f2);
        invalidateSelf();
    }

    public void k(float f) {
        this.a.z(f);
        invalidateSelf();
    }

    public void l(int i2) {
        if (i2 == 0) {
            i(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            i(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    void n(float f, c cVar) {
        if (f > 0.75f) {
            cVar.r(c((f - 0.75f) / 0.25f, cVar.h(), cVar.e()));
        } else {
            cVar.r(cVar.h());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.a.n(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.a.s(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.d.cancel();
        this.a.A();
        if (this.a.d() != this.a.g()) {
            this.f = true;
            this.d.setDuration(666L);
            this.d.start();
        } else {
            this.a.t(0);
            this.a.m();
            this.d.setDuration(1332L);
            this.d.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.d.cancel();
        h(0.0f);
        this.a.x(false);
        this.a.t(0);
        this.a.m();
        invalidateSelf();
    }
}
