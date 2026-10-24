package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class a2 {
    private WeakReference a;
    Runnable b = null;
    Runnable c = null;
    int d = -1;

    class a extends AnimatorListenerAdapter {
        final /* synthetic */ b2 a;
        final /* synthetic */ View b;

        a(b2 b2Var, View view) {
            this.a = b2Var;
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
        final /* synthetic */ d2 a;
        final /* synthetic */ View b;

        b(d2 d2Var, View view) {
            this.a = d2Var;
            this.b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.a(this.b);
        }
    }

    a2(View view) {
        this.a = new WeakReference(view);
    }

    private void g(View view, b2 b2Var) {
        if (b2Var != null) {
            view.animate().setListener(new a(b2Var, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public a2 a(float f) {
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

    public a2 d(long j) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public a2 e(Interpolator interpolator) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public a2 f(b2 b2Var) {
        View view = (View) this.a.get();
        if (view != null) {
            g(view, b2Var);
        }
        return this;
    }

    public a2 h(long j) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public a2 i(d2 d2Var) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().setUpdateListener(d2Var != null ? new b(d2Var, view) : null);
        }
        return this;
    }

    public void j() {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public a2 k(float f) {
        View view = (View) this.a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
