package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.c1;

/* JADX INFO: loaded from: classes.dex */
public class d extends p0 {

    class a extends o {
        final /* synthetic */ View a;

        a(View view) {
            this.a = view;
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            c0.g(this.a, 1.0f);
            c0.a(this.a);
            nVar.removeListener(this);
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
            c0.g(this.a, 1.0f);
            if (this.b) {
                this.a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (c1.M(this.a) && this.a.getLayerType() == 0) {
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
        c0.g(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) c0.b, f2);
        objectAnimatorOfFloat.addListener(new b(view));
        addListener(new a(view));
        return objectAnimatorOfFloat;
    }

    private static float y(u uVar, float f) {
        Float f2;
        return (uVar == null || (f2 = (Float) uVar.a.get("android:fade:transitionAlpha")) == null) ? f : f2.floatValue();
    }

    @Override // androidx.transition.p0, androidx.transition.n
    public void captureStartValues(u uVar) {
        super.captureStartValues(uVar);
        uVar.a.put("android:fade:transitionAlpha", Float.valueOf(c0.c(uVar.b)));
    }

    @Override // androidx.transition.p0
    public Animator s(ViewGroup viewGroup, View view, u uVar, u uVar2) {
        float fY = y(uVar, 0.0f);
        return x(view, fY != 1.0f ? fY : 0.0f, 1.0f);
    }

    @Override // androidx.transition.p0
    public Animator u(ViewGroup viewGroup, View view, u uVar, u uVar2) {
        c0.e(view);
        return x(view, y(uVar, 1.0f), 0.0f);
    }
}
