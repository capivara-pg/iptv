package androidx.core.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.R$id;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class m0 {
    private static Field c;
    private static ThreadLocal e;
    private static final AtomicInteger a = new AtomicInteger(1);
    private static WeakHashMap b = null;
    private static boolean d = false;
    private static final int[] f = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
    private static final b0 g = new a();
    private static f h = new f();

    class a implements b0 {
        a() {
        }
    }

    class b extends g {
        b(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setScreenReaderFocusable(bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    class c extends g {
        c(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getAccessibilityPaneTitle();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    class d extends g {
        d(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getStateDescription();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    class e extends g {
        e(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setAccessibilityHeading(bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.m0.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    static class f implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        private WeakHashMap a = new WeakHashMap();

        f() {
        }

        private void a(View view, boolean z) {
            boolean z2 = view.getVisibility() == 0;
            if (z != z2) {
                m0.V(view, z2 ? 16 : 32);
                this.a.put(view, Boolean.valueOf(z2));
            }
        }

        private void b(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT < 28) {
                for (Map.Entry entry : this.a.entrySet()) {
                    a((View) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            b(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    static abstract class g {
        private final int a;
        private final Class b;
        private final int c;
        private final int d;

        g(int i, Class cls, int i2) {
            this(i, cls, 0, i2);
        }

        private boolean b() {
            return true;
        }

        private boolean c() {
            return Build.VERSION.SDK_INT >= this.c;
        }

        boolean a(Boolean bool, Boolean bool2) {
            return (bool == null ? false : bool.booleanValue()) == (bool2 == null ? false : bool2.booleanValue());
        }

        abstract Object d(View view);

        abstract void e(View view, Object obj);

        Object f(View view) {
            if (c()) {
                return d(view);
            }
            if (!b()) {
                return null;
            }
            Object tag = view.getTag(this.a);
            if (this.b.isInstance(tag)) {
                return tag;
            }
            return null;
        }

        void g(View view, Object obj) {
            if (c()) {
                e(view, obj);
            } else if (b() && h(f(view), obj)) {
                m0.D(view);
                view.setTag(this.a, obj);
                m0.V(view, this.d);
            }
        }

        abstract boolean h(Object obj, Object obj2);

        g(int i, Class cls, int i2, int i3) {
            this.a = i;
            this.b = cls;
            this.d = i2;
            this.c = i3;
        }
    }

    private static class h {

        class a implements View.OnApplyWindowInsetsListener {
            g1 a = null;
            final /* synthetic */ View b;
            final /* synthetic */ a0 c;

            a(final View val$v, final a0 val$listener) {
                this.b = val$v;
                this.c = val$listener;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(final View view, final WindowInsets insets) {
                g1 g1VarW = g1.w(insets, view);
                int i = Build.VERSION.SDK_INT;
                if (i < 30) {
                    h.a(insets, this.b);
                    if (g1VarW.equals(this.a)) {
                        return this.c.onApplyWindowInsets(view, g1VarW).u();
                    }
                }
                this.a = g1VarW;
                g1 g1VarOnApplyWindowInsets = this.c.onApplyWindowInsets(view, g1VarW);
                if (i >= 30) {
                    return g1VarOnApplyWindowInsets.u();
                }
                m0.i0(view);
                return g1VarOnApplyWindowInsets.u();
            }
        }

        static void a(final WindowInsets insets, final View v) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) v.getTag(R$id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(v, insets);
            }
        }

        static g1 b(View v, g1 insets, Rect outLocalInsets) {
            WindowInsets windowInsetsU = insets.u();
            if (windowInsetsU != null) {
                return g1.w(v.computeSystemWindowInsets(windowInsetsU, outLocalInsets), v);
            }
            outLocalInsets.setEmpty();
            return insets;
        }

        public static g1 c(View v) {
            return g1.a.a(v);
        }

        static void d(final View v, final a0 listener) {
            if (Build.VERSION.SDK_INT < 30) {
                v.setTag(R$id.tag_on_apply_window_listener, listener);
            }
            if (listener == null) {
                v.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) v.getTag(R$id.tag_window_insets_animation_callback));
            } else {
                v.setOnApplyWindowInsetsListener(new a(v, listener));
            }
        }
    }

    private static class i {
        public static g1 a(View v) {
            WindowInsets rootWindowInsets = v.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            g1 g1VarV = g1.v(rootWindowInsets);
            g1VarV.s(g1VarV);
            g1VarV.d(v.getRootView());
            return g1VarV;
        }
    }

    private static class j {
        static void a(View view, Context context, int[] styleable, AttributeSet attrs, TypedArray t, int defStyleAttr, int defStyleRes) {
            view.saveAttributeDataForStyleable(context, styleable, attrs, t, defStyleAttr, defStyleRes);
        }
    }

    static class k {
        private static final ArrayList d = new ArrayList();
        private WeakHashMap a = null;
        private SparseArray b = null;
        private WeakReference c = null;

        k() {
        }

        static k a(View view) {
            int i = R$id.tag_unhandled_key_event_manager;
            k kVar = (k) view.getTag(i);
            if (kVar != null) {
                return kVar;
            }
            k kVar2 = new k();
            view.setTag(i, kVar2);
            return kVar2;
        }

        private View c(View view, KeyEvent keyEvent) {
            WeakHashMap weakHashMap = this.a;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewC = c(viewGroup.getChildAt(childCount), keyEvent);
                        if (viewC != null) {
                            return viewC;
                        }
                    }
                }
                if (e(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        private SparseArray d() {
            if (this.b == null) {
                this.b = new SparseArray();
            }
            return this.b;
        }

        private boolean e(View view, KeyEvent keyEvent) {
            int size;
            ArrayList arrayList = (ArrayList) view.getTag(R$id.tag_unhandled_key_listeners);
            if (arrayList == null || (size = arrayList.size() - 1) < 0) {
                return false;
            }
            androidx.appcompat.app.r.a(arrayList.get(size));
            throw null;
        }

        private void g() {
            WeakHashMap weakHashMap = this.a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList = d;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.a == null) {
                    this.a = new WeakHashMap();
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ArrayList arrayList2 = d;
                    View view = (View) ((WeakReference) arrayList2.get(size)).get();
                    if (view == null) {
                        arrayList2.remove(size);
                    } else {
                        this.a.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.a.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        boolean b(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                g();
            }
            View viewC = c(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewC != null && !KeyEvent.isModifierKey(keyCode)) {
                    d().put(keyCode, new WeakReference(viewC));
                }
            }
            return viewC != null;
        }

        boolean f(KeyEvent keyEvent) {
            WeakReference weakReference;
            int iIndexOfKey;
            WeakReference weakReference2 = this.c;
            if (weakReference2 != null && weakReference2.get() == keyEvent) {
                return false;
            }
            this.c = new WeakReference(keyEvent);
            SparseArray sparseArrayD = d();
            if (keyEvent.getAction() != 1 || (iIndexOfKey = sparseArrayD.indexOfKey(keyEvent.getKeyCode())) < 0) {
                weakReference = null;
            } else {
                weakReference = (WeakReference) sparseArrayD.valueAt(iIndexOfKey);
                sparseArrayD.removeAt(iIndexOfKey);
            }
            if (weakReference == null) {
                weakReference = (WeakReference) sparseArrayD.get(keyEvent.getKeyCode());
            }
            if (weakReference == null) {
                return false;
            }
            View view = (View) weakReference.get();
            if (view != null && m0.Q(view)) {
                e(view, keyEvent);
            }
            return true;
        }
    }

    public static int A(View view) {
        return view.getLayoutDirection();
    }

    public static void A0(View view, e0 e0Var) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon(j0.a(e0Var != null ? e0Var.a() : null));
        }
    }

    public static int B(View view) {
        return view.getMinimumHeight();
    }

    public static void B0(View view, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i2, i3);
        }
    }

    public static int C(View view) {
        return view.getMinimumWidth();
    }

    public static void C0(View view, String str) {
        view.setTransitionName(str);
    }

    static androidx.core.view.a D(View view) {
        androidx.core.view.a aVarL = l(view);
        if (aVarL == null) {
            aVarL = new androidx.core.view.a();
        }
        l0(view, aVarL);
        return aVarL;
    }

    private static void D0(View view) {
        if (y(view) == 0) {
            v0(view, 1);
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (y((View) parent) == 4) {
                v0(view, 2);
                return;
            }
        }
    }

    public static int E(View view) {
        return view.getOverScrollMode();
    }

    private static g E0() {
        return new d(R$id.tag_state_description, CharSequence.class, 64, 30);
    }

    public static int F(View view) {
        return view.getPaddingEnd();
    }

    public static void F0(View view) {
        view.stopNestedScroll();
    }

    public static int G(View view) {
        return view.getPaddingStart();
    }

    private static void G0(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static g1 H(View view) {
        return Build.VERSION.SDK_INT >= 23 ? i.a(view) : h.c(view);
    }

    public static final CharSequence I(View view) {
        return (CharSequence) E0().f(view);
    }

    public static String J(View view) {
        return view.getTransitionName();
    }

    public static int K(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static float L(View view) {
        return view.getZ();
    }

    public static boolean M(View view) {
        return view.hasOnClickListeners();
    }

    public static boolean N(View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean O(View view) {
        return view.hasTransientState();
    }

    public static boolean P(View view) {
        Boolean bool = (Boolean) a().f(view);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static boolean Q(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean R(View view) {
        return view.isLaidOut();
    }

    public static boolean S(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static boolean T(View view) {
        return view.isPaddingRelative();
    }

    public static boolean U(View view) {
        Boolean bool = (Boolean) k0().f(view);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    static void V(View view, int i2) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = p(view) != null && view.getVisibility() == 0;
            if (o(view) != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : RecyclerView.ItemAnimator.FLAG_MOVED);
                accessibilityEventObtain.setContentChangeTypes(i2);
                if (z) {
                    accessibilityEventObtain.getText().add(p(view));
                    D0(view);
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i2 == 32) {
                AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.setEventType(32);
                accessibilityEventObtain2.setContentChangeTypes(i2);
                accessibilityEventObtain2.setSource(view);
                view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.getText().add(p(view));
                accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i2);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                }
            }
        }
    }

    public static void W(View view, int i2) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i2);
            return;
        }
        Rect rectW = w();
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectW.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectW.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            z = false;
        }
        e(view, i2);
        if (z && rectW.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectW);
        }
    }

    public static void X(View view, int i2) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i2);
            return;
        }
        Rect rectW = w();
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectW.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectW.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            z = false;
        }
        f(view, i2);
        if (z && rectW.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectW);
        }
    }

    public static g1 Y(View view, g1 g1Var) {
        WindowInsets windowInsetsU = g1Var.u();
        if (windowInsetsU != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsU);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsU)) {
                return g1.w(windowInsetsOnApplyWindowInsets, view);
            }
        }
        return g1Var;
    }

    public static void Z(View view, d0.a0 a0Var) {
        view.onInitializeAccessibilityNodeInfo(a0Var.v0());
    }

    private static g a() {
        return new e(R$id.tag_accessibility_heading, Boolean.class, 28);
    }

    private static g a0() {
        return new c(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    private static void b(View view, d0.a0.a aVar) {
        D(view);
        g0(aVar.b(), view);
        q(view).add(aVar);
        V(view, 0);
    }

    public static boolean b0(View view, int i2, Bundle bundle) {
        return view.performAccessibilityAction(i2, bundle);
    }

    public static c1 c(View view) {
        if (b == null) {
            b = new WeakHashMap();
        }
        c1 c1Var = (c1) b.get(view);
        if (c1Var != null) {
            return c1Var;
        }
        c1 c1Var2 = new c1(view);
        b.put(view, c1Var2);
        return c1Var2;
    }

    public static void c0(View view) {
        view.postInvalidateOnAnimation();
    }

    public static boolean d(View view, int i2) {
        return view.canScrollVertically(i2);
    }

    public static void d0(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    private static void e(View view, int i2) {
        view.offsetLeftAndRight(i2);
        if (view.getVisibility() == 0) {
            G0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                G0((View) parent);
            }
        }
    }

    public static void e0(View view, Runnable runnable, long j2) {
        view.postOnAnimationDelayed(runnable, j2);
    }

    private static void f(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (view.getVisibility() == 0) {
            G0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                G0((View) parent);
            }
        }
    }

    public static void f0(View view, int i2) {
        g0(i2, view);
        V(view, 0);
    }

    public static g1 g(View view, g1 g1Var, Rect rect) {
        return h.b(view, g1Var, rect);
    }

    private static void g0(int i2, View view) {
        List listQ = q(view);
        for (int i3 = 0; i3 < listQ.size(); i3++) {
            if (((d0.a0.a) listQ.get(i3)).b() == i2) {
                listQ.remove(i3);
                return;
            }
        }
    }

    public static g1 h(View view, g1 g1Var) {
        WindowInsets windowInsetsU = g1Var.u();
        if (windowInsetsU != null) {
            WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsetsU);
            if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsetsU)) {
                return g1.w(windowInsetsDispatchApplyWindowInsets, view);
            }
        }
        return g1Var;
    }

    public static void h0(View view, d0.a0.a aVar, CharSequence charSequence, d0.d0 d0Var) {
        if (d0Var == null && charSequence == null) {
            f0(view, aVar.b());
        } else {
            b(view, aVar.a(charSequence, d0Var));
        }
    }

    static boolean i(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).b(view, keyEvent);
    }

    public static void i0(View view) {
        view.requestApplyInsets();
    }

    static boolean j(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).f(keyEvent);
    }

    public static void j0(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 29) {
            j.a(view, context, iArr, attributeSet, typedArray, i2, i3);
        }
    }

    public static int k() {
        return View.generateViewId();
    }

    private static g k0() {
        return new b(R$id.tag_screen_reader_focusable, Boolean.class, 28);
    }

    public static androidx.core.view.a l(View view) {
        View.AccessibilityDelegate accessibilityDelegateM = m(view);
        if (accessibilityDelegateM == null) {
            return null;
        }
        return accessibilityDelegateM instanceof androidx.core.view.a.C0021a ? ((androidx.core.view.a.C0021a) accessibilityDelegateM).a : new androidx.core.view.a(accessibilityDelegateM);
    }

    public static void l0(View view, androidx.core.view.a aVar) {
        if (aVar == null && (m(view) instanceof androidx.core.view.a.C0021a)) {
            aVar = new androidx.core.view.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.getBridge());
    }

    private static View.AccessibilityDelegate m(View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getAccessibilityDelegate() : n(view);
    }

    public static void m0(View view, boolean z) {
        a().g(view, Boolean.valueOf(z));
    }

    private static View.AccessibilityDelegate n(View view) {
        if (d) {
            return null;
        }
        if (c == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                c = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                d = true;
                return null;
            }
        }
        try {
            Object obj = c.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            d = true;
            return null;
        }
    }

    public static void n0(View view, int i2) {
        view.setAccessibilityLiveRegion(i2);
    }

    public static int o(View view) {
        return view.getAccessibilityLiveRegion();
    }

    public static void o0(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static CharSequence p(View view) {
        return (CharSequence) a0().f(view);
    }

    public static void p0(View view, ColorStateList colorStateList) {
        int i2 = Build.VERSION.SDK_INT;
        view.setBackgroundTintList(colorStateList);
        if (i2 == 21) {
            Drawable background = view.getBackground();
            boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    private static List q(View view) {
        int i2 = R$id.tag_accessibility_actions;
        ArrayList arrayList = (ArrayList) view.getTag(i2);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(i2, arrayList2);
        return arrayList2;
    }

    public static void q0(View view, PorterDuff.Mode mode) {
        int i2 = Build.VERSION.SDK_INT;
        view.setBackgroundTintMode(mode);
        if (i2 == 21) {
            Drawable background = view.getBackground();
            boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static ColorStateList r(View view) {
        return view.getBackgroundTintList();
    }

    public static void r0(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static PorterDuff.Mode s(View view) {
        return view.getBackgroundTintMode();
    }

    public static void s0(View view, float f2) {
        view.setElevation(f2);
    }

    public static Rect t(View view) {
        return view.getClipBounds();
    }

    public static void t0(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    public static Display u(View view) {
        return view.getDisplay();
    }

    public static void u0(View view, boolean z) {
        view.setHasTransientState(z);
    }

    public static float v(View view) {
        return view.getElevation();
    }

    public static void v0(View view, int i2) {
        view.setImportantForAccessibility(i2);
    }

    private static Rect w() {
        if (e == null) {
            e = new ThreadLocal();
        }
        Rect rect = (Rect) e.get();
        if (rect == null) {
            rect = new Rect();
            e.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static void w0(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i2);
        }
    }

    public static boolean x(View view) {
        return view.getFitsSystemWindows();
    }

    public static void x0(View view, int i2, Paint paint) {
        view.setLayerType(i2, paint);
    }

    public static int y(View view) {
        return view.getImportantForAccessibility();
    }

    public static void y0(View view, a0 a0Var) {
        h.d(view, a0Var);
    }

    public static int z(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static void z0(View view, int i2, int i3, int i4, int i5) {
        view.setPaddingRelative(i2, i3, i4, i5);
    }
}
