package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class m0 extends k {
    private static final String[] b = {"android:visibility:visibility", "android:visibility:parent"};
    private int a = 3;

    class a extends l {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;
        final /* synthetic */ View c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.a = viewGroup;
            this.b = view;
            this.c = view2;
        }

        @Override // androidx.transition.l, androidx.transition.k.g
        public void a(k kVar) {
            if (this.b.getParent() == null) {
                w.a(this.a).c(this.b);
            } else {
                m0.this.cancel();
            }
        }

        @Override // androidx.transition.l, androidx.transition.k.g
        public void c(k kVar) {
            w.a(this.a).d(this.b);
        }

        @Override // androidx.transition.k.g
        public void d(k kVar) {
            this.c.setTag(R$id.save_overlay_view, null);
            w.a(this.a).d(this.b);
            kVar.removeListener(this);
        }
    }

    private static class b extends AnimatorListenerAdapter implements k.g {
        private final View a;
        private final int b;
        private final ViewGroup c;
        private final boolean d;
        private boolean e;
        boolean f = false;

        b(View view, int i, boolean z) {
            this.a = view;
            this.b = i;
            this.c = (ViewGroup) view.getParent();
            this.d = z;
            g(true);
        }

        private void f() {
            if (!this.f) {
                z.h(this.a, this.b);
                ViewGroup viewGroup = this.c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        private void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.d || this.e == z || (viewGroup = this.c) == null) {
                return;
            }
            this.e = z;
            w.c(viewGroup, z);
        }

        @Override // androidx.transition.k.g
        public void a(k kVar) {
            g(true);
        }

        @Override // androidx.transition.k.g
        public void b(k kVar) {
        }

        @Override // androidx.transition.k.g
        public void c(k kVar) {
            g(false);
        }

        @Override // androidx.transition.k.g
        public void d(k kVar) {
            f();
            kVar.removeListener(this);
        }

        @Override // androidx.transition.k.g
        public void e(k kVar) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            z.h(this.a, this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            z.h(this.a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    private static class c {
        boolean a;
        boolean b;
        int c;
        int d;
        ViewGroup e;
        ViewGroup f;

        c() {
        }
    }

    private void captureValues(r rVar) {
        rVar.a.put("android:visibility:visibility", Integer.valueOf(rVar.b.getVisibility()));
        rVar.a.put("android:visibility:parent", rVar.b.getParent());
        int[] iArr = new int[2];
        rVar.b.getLocationOnScreen(iArr);
        rVar.a.put("android:visibility:screenLocation", iArr);
    }

    private c r(r rVar, r rVar2) {
        c cVar = new c();
        cVar.a = false;
        cVar.b = false;
        if (rVar == null || !rVar.a.containsKey("android:visibility:visibility")) {
            cVar.c = -1;
            cVar.e = null;
        } else {
            cVar.c = ((Integer) rVar.a.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) rVar.a.get("android:visibility:parent");
        }
        if (rVar2 == null || !rVar2.a.containsKey("android:visibility:visibility")) {
            cVar.d = -1;
            cVar.f = null;
        } else {
            cVar.d = ((Integer) rVar2.a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) rVar2.a.get("android:visibility:parent");
        }
        if (rVar != null && rVar2 != null) {
            int i = cVar.c;
            int i2 = cVar.d;
            if (i == i2 && cVar.e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.b = false;
                    cVar.a = true;
                } else if (i2 == 0) {
                    cVar.b = true;
                    cVar.a = true;
                }
            } else if (cVar.f == null) {
                cVar.b = false;
                cVar.a = true;
            } else if (cVar.e == null) {
                cVar.b = true;
                cVar.a = true;
            }
        } else if (rVar == null && cVar.d == 0) {
            cVar.b = true;
            cVar.a = true;
        } else if (rVar2 == null && cVar.c == 0) {
            cVar.b = false;
            cVar.a = true;
        }
        return cVar;
    }

    @Override // androidx.transition.k
    public void captureEndValues(r rVar) {
        captureValues(rVar);
    }

    @Override // androidx.transition.k
    public void captureStartValues(r rVar) {
        captureValues(rVar);
    }

    @Override // androidx.transition.k
    public Animator createAnimator(ViewGroup viewGroup, r rVar, r rVar2) {
        c cVarR = r(rVar, rVar2);
        if (!cVarR.a) {
            return null;
        }
        if (cVarR.e == null && cVarR.f == null) {
            return null;
        }
        return cVarR.b ? t(viewGroup, rVar, cVarR.c, rVar2, cVarR.d) : v(viewGroup, rVar, cVarR.c, rVar2, cVarR.d);
    }

    @Override // androidx.transition.k
    public String[] getTransitionProperties() {
        return b;
    }

    @Override // androidx.transition.k
    public boolean isTransitionRequired(r rVar, r rVar2) {
        if (rVar == null && rVar2 == null) {
            return false;
        }
        if (rVar != null && rVar2 != null && rVar2.a.containsKey("android:visibility:visibility") != rVar.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarR = r(rVar, rVar2);
        if (cVarR.a) {
            return cVarR.c == 0 || cVarR.d == 0;
        }
        return false;
    }

    public abstract Animator s(ViewGroup viewGroup, View view, r rVar, r rVar2);

    public Animator t(ViewGroup viewGroup, r rVar, int i, r rVar2, int i2) {
        if ((this.a & 1) != 1 || rVar2 == null) {
            return null;
        }
        if (rVar == null) {
            View view = (View) rVar2.b.getParent();
            if (r(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).a) {
                return null;
            }
        }
        return s(viewGroup, rVar2.b, rVar, rVar2);
    }

    public abstract Animator u(ViewGroup viewGroup, View view, r rVar, r rVar2);

    /* JADX WARN: Code duplicated, block: B:25:0x0045  */
    public Animator v(ViewGroup viewGroup, r rVar, int i, r rVar2, int i2) {
        View view;
        boolean z;
        boolean z2;
        View view2;
        if ((this.a & 2) != 2 || rVar == null) {
            return null;
        }
        View view3 = rVar.b;
        View viewA = rVar2 != null ? rVar2.b : null;
        int i3 = R$id.save_overlay_view;
        View view4 = (View) view3.getTag(i3);
        if (view4 != null) {
            view2 = null;
            z2 = true;
        } else {
            if (viewA == null || viewA.getParent() == null) {
                if (viewA != null) {
                    view = null;
                    z = false;
                } else {
                    viewA = null;
                    view = null;
                    z = true;
                }
            } else if (i2 == 4 || view3 == viewA) {
                view = viewA;
                z = false;
                viewA = null;
            } else {
                viewA = null;
                view = null;
                z = true;
            }
            if (z) {
                if (view3.getParent() != null) {
                    if (view3.getParent() instanceof View) {
                        View view5 = (View) view3.getParent();
                        if (r(getTransitionValues(view5, true), getMatchedTransitionValues(view5, true)).a) {
                            int id = view5.getId();
                            if (view5.getParent() != null || id == -1 || viewGroup.findViewById(id) == null || !this.mCanRemoveViews) {
                            }
                        } else {
                            viewA = q.a(viewGroup, view3, view5);
                        }
                    }
                    z2 = false;
                    View view6 = view;
                    view4 = viewA;
                    view2 = view6;
                }
                view2 = view;
                z2 = false;
                view4 = view3;
            } else {
                z2 = false;
                View view7 = view;
                view4 = viewA;
                view2 = view7;
            }
        }
        if (view4 == null) {
            if (view2 == null) {
                return null;
            }
            int visibility = view2.getVisibility();
            z.h(view2, 0);
            Animator animatorU = u(viewGroup, view2, rVar, rVar2);
            if (animatorU != null) {
                b bVar = new b(view2, i2, true);
                animatorU.addListener(bVar);
                androidx.transition.a.a(animatorU, bVar);
                addListener(bVar);
            } else {
                z.h(view2, visibility);
            }
            return animatorU;
        }
        if (!z2) {
            int[] iArr = (int[]) rVar.a.get("android:visibility:screenLocation");
            int i4 = iArr[0];
            int i5 = iArr[1];
            int[] iArr2 = new int[2];
            viewGroup.getLocationOnScreen(iArr2);
            view4.offsetLeftAndRight((i4 - iArr2[0]) - view4.getLeft());
            view4.offsetTopAndBottom((i5 - iArr2[1]) - view4.getTop());
            w.a(viewGroup).c(view4);
        }
        Animator animatorU2 = u(viewGroup, view4, rVar, rVar2);
        if (!z2) {
            if (animatorU2 == null) {
                w.a(viewGroup).d(view4);
            } else {
                view3.setTag(i3, view4);
                addListener(new a(viewGroup, view4, view3));
            }
        }
        return animatorU2;
    }

    public void w(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.a = i;
    }
}
