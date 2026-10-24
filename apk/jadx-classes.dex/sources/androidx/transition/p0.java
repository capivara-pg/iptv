package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class p0 extends n {
    private static final String[] b = {"android:visibility:visibility", "android:visibility:parent"};
    private int a = 3;

    class a extends o {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;
        final /* synthetic */ View c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.a = viewGroup;
            this.b = view;
            this.c = view2;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void a(n nVar) {
            if (this.b.getParent() == null) {
                z.a(this.a).c(this.b);
            } else {
                p0.this.cancel();
            }
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void c(n nVar) {
            z.a(this.a).d(this.b);
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            this.c.setTag(R$id.save_overlay_view, null);
            z.a(this.a).d(this.b);
            nVar.removeListener(this);
        }
    }

    private static class b extends AnimatorListenerAdapter implements n.g {
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
                c0.h(this.a, this.b);
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
            z.c(viewGroup, z);
        }

        @Override // androidx.transition.n.g
        public void a(n nVar) {
            g(true);
        }

        @Override // androidx.transition.n.g
        public void b(n nVar) {
        }

        @Override // androidx.transition.n.g
        public void c(n nVar) {
            g(false);
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            f();
            nVar.removeListener(this);
        }

        @Override // androidx.transition.n.g
        public void e(n nVar) {
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
            c0.h(this.a, this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.a, 0);
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

    private void captureValues(u uVar) {
        uVar.a.put("android:visibility:visibility", Integer.valueOf(uVar.b.getVisibility()));
        uVar.a.put("android:visibility:parent", uVar.b.getParent());
        int[] iArr = new int[2];
        uVar.b.getLocationOnScreen(iArr);
        uVar.a.put("android:visibility:screenLocation", iArr);
    }

    private c r(u uVar, u uVar2) {
        c cVar = new c();
        cVar.a = false;
        cVar.b = false;
        if (uVar == null || !uVar.a.containsKey("android:visibility:visibility")) {
            cVar.c = -1;
            cVar.e = null;
        } else {
            cVar.c = ((Integer) uVar.a.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) uVar.a.get("android:visibility:parent");
        }
        if (uVar2 == null || !uVar2.a.containsKey("android:visibility:visibility")) {
            cVar.d = -1;
            cVar.f = null;
        } else {
            cVar.d = ((Integer) uVar2.a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) uVar2.a.get("android:visibility:parent");
        }
        if (uVar != null && uVar2 != null) {
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
        } else if (uVar == null && cVar.d == 0) {
            cVar.b = true;
            cVar.a = true;
        } else if (uVar2 == null && cVar.c == 0) {
            cVar.b = false;
            cVar.a = true;
        }
        return cVar;
    }

    @Override // androidx.transition.n
    public void captureEndValues(u uVar) {
        captureValues(uVar);
    }

    @Override // androidx.transition.n
    public void captureStartValues(u uVar) {
        captureValues(uVar);
    }

    @Override // androidx.transition.n
    public Animator createAnimator(ViewGroup viewGroup, u uVar, u uVar2) {
        c cVarR = r(uVar, uVar2);
        if (!cVarR.a) {
            return null;
        }
        if (cVarR.e == null && cVarR.f == null) {
            return null;
        }
        return cVarR.b ? t(viewGroup, uVar, cVarR.c, uVar2, cVarR.d) : v(viewGroup, uVar, cVarR.c, uVar2, cVarR.d);
    }

    @Override // androidx.transition.n
    public String[] getTransitionProperties() {
        return b;
    }

    @Override // androidx.transition.n
    public boolean isTransitionRequired(u uVar, u uVar2) {
        if (uVar == null && uVar2 == null) {
            return false;
        }
        if (uVar != null && uVar2 != null && uVar2.a.containsKey("android:visibility:visibility") != uVar.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarR = r(uVar, uVar2);
        if (cVarR.a) {
            return cVarR.c == 0 || cVarR.d == 0;
        }
        return false;
    }

    public abstract Animator s(ViewGroup viewGroup, View view, u uVar, u uVar2);

    public Animator t(ViewGroup viewGroup, u uVar, int i, u uVar2, int i2) {
        if ((this.a & 1) != 1 || uVar2 == null) {
            return null;
        }
        if (uVar == null) {
            View view = (View) uVar2.b.getParent();
            if (r(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).a) {
                return null;
            }
        }
        return s(viewGroup, uVar2.b, uVar, uVar2);
    }

    public abstract Animator u(ViewGroup viewGroup, View view, u uVar, u uVar2);

    /* JADX WARN: Code duplicated, block: B:25:0x0045  */
    public Animator v(ViewGroup viewGroup, u uVar, int i, u uVar2, int i2) {
        View view;
        boolean z;
        boolean z2;
        View view2;
        if ((this.a & 2) != 2 || uVar == null) {
            return null;
        }
        View view3 = uVar.b;
        View viewA = uVar2 != null ? uVar2.b : null;
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
                            viewA = t.a(viewGroup, view3, view5);
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
            c0.h(view2, 0);
            Animator animatorU = u(viewGroup, view2, uVar, uVar2);
            if (animatorU != null) {
                b bVar = new b(view2, i2, true);
                animatorU.addListener(bVar);
                androidx.transition.a.a(animatorU, bVar);
                addListener(bVar);
            } else {
                c0.h(view2, visibility);
            }
            return animatorU;
        }
        if (!z2) {
            int[] iArr = (int[]) uVar.a.get("android:visibility:screenLocation");
            int i4 = iArr[0];
            int i5 = iArr[1];
            int[] iArr2 = new int[2];
            viewGroup.getLocationOnScreen(iArr2);
            view4.offsetLeftAndRight((i4 - iArr2[0]) - view4.getLeft());
            view4.offsetTopAndBottom((i5 - iArr2[1]) - view4.getTop());
            z.a(viewGroup).c(view4);
        }
        Animator animatorU2 = u(viewGroup, view4, uVar, uVar2);
        if (!z2) {
            if (animatorU2 == null) {
                z.a(viewGroup).d(view4);
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
