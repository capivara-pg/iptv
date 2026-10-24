package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class c1 {
    private WeakReference a;
    Runnable b = null;
    Runnable c = null;
    int d = -1;

    class a extends AnimatorListenerAdapter {
        final /* synthetic */ d1 a;
        final /* synthetic */ View b;

        a(d1 d1Var, View view) {
            this.a = d1Var;
            this.b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a.a(this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.b(this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.a.c(this.b);
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ f1 a;
        final /* synthetic */ View b;

        b(f1 f1Var, View view) {
            this.a = f1Var;
            this.b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.a(this.b);
        }
    }

    c1(View view) {
        this.a = new WeakReference(view);
    }

    private void g(View view, d1 d1Var) {
        if (d1Var != null) {
            view.animate().setListener(new a(d1Var, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public c1 a(float f) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long c() {
        View view = (View) this.a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public c1 d(long j) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public c1 e(Interpolator interpolator) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public c1 f(d1 d1Var) {
        View view = (View) this.a.get();
        if (view != null) {
            g(view, d1Var);
        }
        return this;
    }

    public c1 h(long j) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public c1 i(f1 f1Var) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setUpdateListener(f1Var != null ? new b(f1Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public c1 k(float f) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
