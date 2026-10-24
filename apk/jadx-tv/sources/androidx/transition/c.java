package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.c1;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class c extends n {
    private static final String[] d = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property e = new b(PointF.class, "boundsOrigin");
    private static final Property f = new C0050c(PointF.class, "topLeft");
    private static final Property g = new d(PointF.class, "bottomRight");
    private static final Property h = new e(PointF.class, "bottomRight");
    private static final Property i = new f(PointF.class, "topLeft");
    private static final Property j = new g(PointF.class, "position");
    private static l k = new l();
    private int[] a = new int[2];
    private boolean b = false;
    private boolean c = false;

    class a extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ BitmapDrawable b;
        final /* synthetic */ View c;
        final /* synthetic */ float d;

        a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f) {
            this.a = viewGroup;
            this.b = bitmapDrawable;
            this.c = view;
            this.d = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.b(this.a).b(this.b);
            c0.g(this.c, this.d);
        }
    }

    static class b extends Property {
        private Rect a;

        b(Class cls, String str) {
            super(cls, str);
            this.a = new Rect();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.a);
            Rect rect = this.a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.a);
            this.a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.a);
        }
    }

    /* JADX INFO: renamed from: androidx.transition.c$c, reason: collision with other inner class name */
    static class C0050c extends Property {
        C0050c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.c(pointF);
        }
    }

    static class d extends Property {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.a(pointF);
        }
    }

    static class e extends Property {
        e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            c0.f(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    static class f extends Property {
        f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            c0.f(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    static class g extends Property {
        g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            int iRound = Math.round(pointF.x);
            int iRound2 = Math.round(pointF.y);
            c0.f(view, iRound, iRound2, view.getWidth() + iRound, view.getHeight() + iRound2);
        }
    }

    class h extends AnimatorListenerAdapter {
        final /* synthetic */ k a;
        private k mViewBounds;

        h(k kVar) {
            this.a = kVar;
            this.mViewBounds = kVar;
        }
    }

    class i extends AnimatorListenerAdapter {
        private boolean a;
        final /* synthetic */ View b;
        final /* synthetic */ Rect c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;
        final /* synthetic */ int g;

        i(View view, Rect rect, int i, int i2, int i3, int i4) {
            this.b = view;
            this.c = rect;
            this.d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                return;
            }
            c1.q0(this.b, this.c);
            c0.f(this.b, this.d, this.e, this.f, this.g);
        }
    }

    class j extends o {
        boolean a = false;
        final /* synthetic */ ViewGroup b;

        j(ViewGroup viewGroup) {
            this.b = viewGroup;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void a(n nVar) {
            z.c(this.b, true);
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void c(n nVar) {
            z.c(this.b, false);
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            if (!this.a) {
                z.c(this.b, false);
            }
            nVar.removeListener(this);
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void e(n nVar) {
            z.c(this.b, false);
            this.a = true;
        }
    }

    private static class k {
        private int a;
        private int b;
        private int c;
        private int d;
        private View e;
        private int f;
        private int g;

        k(View view) {
            this.e = view;
        }

        private void b() {
            c0.f(this.e, this.a, this.b, this.c, this.d);
            this.f = 0;
            this.g = 0;
        }

        void a(PointF pointF) {
            this.c = Math.round(pointF.x);
            this.d = Math.round(pointF.y);
            int i = this.g + 1;
            this.g = i;
            if (this.f == i) {
                b();
            }
        }

        void c(PointF pointF) {
            this.a = Math.round(pointF.x);
            this.b = Math.round(pointF.y);
            int i = this.f + 1;
            this.f = i;
            if (i == this.g) {
                b();
            }
        }
    }

    private void captureValues(u uVar) {
        View view = uVar.b;
        if (!c1.Q(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        uVar.a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        uVar.a.put("android:changeBounds:parent", uVar.b.getParent());
        if (this.c) {
            uVar.b.getLocationInWindow(this.a);
            uVar.a.put("android:changeBounds:windowX", Integer.valueOf(this.a[0]));
            uVar.a.put("android:changeBounds:windowY", Integer.valueOf(this.a[1]));
        }
        if (this.b) {
            uVar.a.put("android:changeBounds:clip", c1.s(view));
        }
    }

    private boolean r(View view, View view2) {
        if (!this.c) {
            return true;
        }
        u matchedTransitionValues = getMatchedTransitionValues(view, true);
        if (matchedTransitionValues == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == matchedTransitionValues.b) {
            return true;
        }
        return false;
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
        int i2;
        View view;
        ObjectAnimator objectAnimatorA;
        int i3;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorC;
        if (uVar == null || uVar2 == null) {
            return null;
        }
        Map map = uVar.a;
        Map map2 = uVar2.a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = uVar2.b;
        if (!r(viewGroup2, viewGroup3)) {
            int iIntValue = ((Integer) uVar.a.get("android:changeBounds:windowX")).intValue();
            int iIntValue2 = ((Integer) uVar.a.get("android:changeBounds:windowY")).intValue();
            int iIntValue3 = ((Integer) uVar2.a.get("android:changeBounds:windowX")).intValue();
            int iIntValue4 = ((Integer) uVar2.a.get("android:changeBounds:windowY")).intValue();
            if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.a);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(bitmapCreateBitmap));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            float fC = c0.c(view2);
            c0.g(view2, 0.0f);
            c0.b(viewGroup).a(bitmapDrawable);
            androidx.transition.h pathMotion = getPathMotion();
            int[] iArr = this.a;
            int i4 = iArr[0];
            int i5 = iArr[1];
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, androidx.transition.k.a(e, pathMotion.a(iIntValue - i4, iIntValue2 - i5, iIntValue3 - i4, iIntValue4 - i5)));
            objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fC));
            return objectAnimatorOfPropertyValuesHolder;
        }
        Rect rect2 = (Rect) uVar.a.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) uVar2.a.get("android:changeBounds:bounds");
        int i6 = rect2.left;
        int i7 = rect3.left;
        int i8 = rect2.top;
        int i9 = rect3.top;
        int i10 = rect2.right;
        int i11 = rect3.right;
        int i12 = rect2.bottom;
        int i13 = rect3.bottom;
        int i14 = i10 - i6;
        int i15 = i12 - i8;
        int i16 = i11 - i7;
        int i17 = i13 - i9;
        Rect rect4 = (Rect) uVar.a.get("android:changeBounds:clip");
        Rect rect5 = (Rect) uVar2.a.get("android:changeBounds:clip");
        if ((i14 == 0 || i15 == 0) && (i16 == 0 || i17 == 0)) {
            i2 = 0;
        } else {
            i2 = (i6 == i7 && i8 == i9) ? 0 : 1;
            if (i10 != i11 || i12 != i13) {
                i2++;
            }
        }
        if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
            i2++;
        }
        if (i2 <= 0) {
            return null;
        }
        if (this.b) {
            c0.f(view, i6, i8, Math.max(i14, i16) + i6, Math.max(i15, i17) + i8);
            if (i6 == i7 && i8 == i9) {
                objectAnimatorA = null;
            } else {
                view = view2;
                objectAnimatorA = androidx.transition.g.a(view, j, getPathMotion().a(i6, i8, i7, i9));
            }
            if (rect4 == null) {
                i3 = 0;
                rect = new Rect(0, 0, i14, i15);
            } else {
                i3 = 0;
                rect = rect4;
            }
            Rect rect6 = rect5 == null ? new Rect(i3, i3, i16, i17) : rect5;
            if (rect.equals(rect6)) {
                objectAnimator = null;
            } else {
                c1.q0(view, rect);
                l lVar = k;
                Object[] objArr = new Object[2];
                objArr[i3] = rect;
                objArr[1] = rect6;
                ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", lVar, objArr);
                objectAnimatorOfObject.addListener(new i(view, rect5, i7, i9, i11, i13));
                objectAnimator = objectAnimatorOfObject;
            }
            animatorC = t.c(objectAnimatorA, objectAnimator);
        } else {
            c0.f(view, i6, i8, i10, i12);
            if (i2 != 2) {
                animatorC = (i6 == i7 && i8 == i9) ? androidx.transition.g.a(view, h, getPathMotion().a(i10, i12, i11, i13)) : androidx.transition.g.a(view, i, getPathMotion().a(i6, i8, i7, i9));
            } else if (i14 == i16 && i15 == i17) {
                animatorC = androidx.transition.g.a(view, j, getPathMotion().a(i6, i8, i7, i9));
            } else {
                k kVar = new k(view);
                ObjectAnimator objectAnimatorA2 = androidx.transition.g.a(kVar, f, getPathMotion().a(i6, i8, i7, i9));
                ObjectAnimator objectAnimatorA3 = androidx.transition.g.a(kVar, g, getPathMotion().a(i10, i12, i11, i13));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorA2, objectAnimatorA3);
                animatorSet.addListener(new h(kVar));
                animatorC = animatorSet;
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            z.c(viewGroup4, true);
            addListener(new j(viewGroup4));
        }
        return animatorC;
    }

    @Override // androidx.transition.n
    public String[] getTransitionProperties() {
        return d;
    }
}
