package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class d extends m0 {

    class a extends l {
        final /* synthetic */ View a;

        a(View view) {
            this.a = view;
        }

        @Override // androidx.transition.k.g
        public void d(k kVar) {
            z.g(this.a, 1.0f);
            z.a(this.a);
            kVar.removeListener(this);
        }
    }

    private static class b extends AnimatorListenerAdapter {
        private final View a;
        private boolean b = false;

        b(View view) {
            this.a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            z.g(this.a, 1.0f);
            if (this.b) {
                this.a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (androidx.core.view.m0.N(this.a) && this.a.getLayerType() == 0) {
                this.b = true;
                this.a.setLayerType(2, null);
            }
        }
    }

    public d(int i) {
        w(i);
    }

    private Animator x(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        z.g(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) z.b, f2);
        objectAnimatorOfFloat.addListener(new b(view));
        addListener(new a(view));
        return objectAnimatorOfFloat;
    }

    private static float y(r rVar, float f) {
        Float f2;
        return (rVar == null || (f2 = (Float) rVar.a.get("android:fade:transitionAlpha")) == null) ? f : f2.floatValue();
    }

    @Override // androidx.transition.m0, androidx.transition.k
    public void captureStartValues(r rVar) {
        super.captureStartValues(rVar);
        rVar.a.put("android:fade:transitionAlpha", Float.valueOf(z.c(rVar.b)));
    }

    @Override // androidx.transition.m0
    public Animator s(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        float fY = y(rVar, 0.0f);
        return x(view, fY != 1.0f ? fY : 0.0f, 1.0f);
    }

    @Override // androidx.transition.m0
    public Animator u(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        z.e(view);
        return x(view, y(rVar, 1.0f), 0.0f);
    }
}
