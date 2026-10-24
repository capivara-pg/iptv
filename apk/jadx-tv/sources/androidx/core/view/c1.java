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
public abstract class c1 {
    private static WeakHashMap b;
    private static Field d;
    private static ThreadLocal f;
    private static final AtomicInteger a = new AtomicInteger(1);
    private static WeakHashMap c = null;
    private static boolean e = false;
    private static final int[] g = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
    private static final c0 h = new a();
    private static f i = new f();

    class a implements c0 {
        a() {
        }
    }

    class b extends g {
        b(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setScreenReaderFocusable(bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
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
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getAccessibilityPaneTitle();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
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
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getStateDescription();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
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
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setAccessibilityHeading(bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.c1.g
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
                c1.U(view, z2 ? 16 : 32);
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
                c1.C(view);
                view.setTag(this.a, obj);
                c1.U(view, this.d);
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
            f2 a = null;
            final /* synthetic */ View b;
            final /* synthetic */ b0 c;

            a(View view, b0 b0Var) {
                this.b = view;
                this.c = b0Var;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                f2 f2VarU = f2.u(windowInsets, view);
                int i = Build.VERSION.SDK_INT;
                if (i < 30) {
                    h.a(windowInsets, this.b);
                    if (f2VarU.equals(this.a)) {
                        return this.c.onApplyWindowInsets(view, f2VarU).s();
                    }
                }
                this.a = f2VarU;
                f2 f2VarOnApplyWindowInsets = this.c.onApplyWindowInsets(view, f2VarU);
                if (i >= 30) {
                    return f2VarOnApplyWindowInsets.s();
                }
                c1.h0(view);
                return f2VarOnApplyWindowInsets.s();
            }
        }

        static void a(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        public static f2 b(View view) {
            return f2.a.a(view);
        }

        static void c(View view, b0 b0Var) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(R$id.tag_on_apply_window_listener, b0Var);
            }
            if (b0Var == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new a(view, b0Var));
            }
        }
    }

    private static class i {
        public static f2 a(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            f2 f2VarT = f2.t(rootWindowInsets);
            f2VarT.q(f2VarT);
            f2VarT.d(view.getRootView());
            return f2VarT;
        }
    }

    private static class j {
        static void a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
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
            androidx.appcompat.app.m.a(arrayList.get(size));
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
            if (view != null && c1.P(view)) {
                e(view, keyEvent);
            }
            return true;
        }
    }

    public static int A(View view) {
        return view.getMinimumHeight();
    }

    public static void A0(View view, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i2, i3);
        }
    }

    public static int B(View view) {
        return view.getMinimumWidth();
    }

    public static void B0(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (b == null) {
            b = new WeakHashMap();
        }
        b.put(view, str);
    }

    static androidx.core.view.a C(View view) {
        androidx.core.view.a aVarK = k(view);
        if (aVarK == null) {
            aVarK = new androidx.core.view.a();
        }
        k0(view, aVarK);
        return aVarK;
    }

    private static void C0(View view) {
        if (x(view) == 0) {
            u0(view, 1);
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (x((View) parent) == 4) {
                u0(view, 2);
                return;
            }
        }
    }

    public static int D(View view) {
        return view.getOverScrollMode();
    }

    private static g D0() {
        return new d(R$id.tag_state_description, CharSequence.class, 64, 30);
    }

    public static int E(View view) {
        return view.getPaddingEnd();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void E0(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof v) {
            ((v) view).stopNestedScroll();
        }
    }

    public static int F(View view) {
        return view.getPaddingStart();
    }

    private static void F0(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static f2 G(View view) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            return i.a(view);
        }
        if (i2 >= 21) {
            return h.b(view);
        }
        return null;
    }

    public static final CharSequence H(View view) {
        return (CharSequence) D0().f(view);
    }

    public static String I(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap weakHashMap = b;
        if (weakHashMap == null) {
            return null;
        }
        return (String) weakHashMap.get(view);
    }

    public static int J(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static float K(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    public static boolean L(View view) {
        return view.hasOnClickListeners();
    }

    public static boolean M(View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean N(View view) {
        return view.hasTransientState();
    }

    public static boolean O(View view) {
        Boolean bool = (Boolean) a().f(view);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static boolean P(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean Q(View view) {
        return view.isLaidOut();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean R(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        if (view instanceof v) {
            return ((v) view).isNestedScrollingEnabled();
        }
        return false;
    }

    public static boolean S(View view) {
        return view.isPaddingRelative();
    }

    public static boolean T(View view) {
        Boolean bool = (Boolean) j0().f(view);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    static void U(View view, int i2) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = o(view) != null && view.getVisibility() == 0;
            if (n(view) != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : RecyclerView.ItemAnimator.FLAG_MOVED);
                accessibilityEventObtain.setContentChangeTypes(i2);
                if (z) {
                    accessibilityEventObtain.getText().add(o(view));
                    C0(view);
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
                accessibilityEventObtain2.getText().add(o(view));
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

    public static void V(View view, int i2) {
        boolean z;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetLeftAndRight(i2);
            return;
        }
        if (i3 < 21) {
            e(view, i2);
            return;
        }
        Rect rectV = v();
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectV.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectV.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            z = false;
        }
        e(view, i2);
        if (z && rectV.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectV);
        }
    }

    public static void W(View view, int i2) {
        boolean z;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetTopAndBottom(i2);
            return;
        }
        if (i3 < 21) {
            f(view, i2);
            return;
        }
        Rect rectV = v();
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectV.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectV.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            z = false;
        }
        f(view, i2);
        if (z && rectV.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectV);
        }
    }

    public static f2 X(View view, f2 f2Var) {
        WindowInsets windowInsetsS;
        if (Build.VERSION.SDK_INT >= 21 && (windowInsetsS = f2Var.s()) != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsS);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsS)) {
                return f2.u(windowInsetsOnApplyWindowInsets, view);
            }
        }
        return f2Var;
    }

    public static void Y(View view, y.h0 h0Var) {
        view.onInitializeAccessibilityNodeInfo(h0Var.v0());
    }

    private static g Z() {
        return new c(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    private static g a() {
        return new e(R$id.tag_accessibility_heading, Boolean.class, 28);
    }

    public static boolean a0(View view, int i2, Bundle bundle) {
        return view.performAccessibilityAction(i2, bundle);
    }

    private static void b(View view, y.h0.a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            C(view);
            f0(aVar.b(), view);
            p(view).add(aVar);
            U(view, 0);
        }
    }

    public static void b0(View view) {
        view.postInvalidateOnAnimation();
    }

    public static a2 c(View view) {
        if (c == null) {
            c = new WeakHashMap();
        }
        a2 a2Var = (a2) c.get(view);
        if (a2Var != null) {
            return a2Var;
        }
        a2 a2Var2 = new a2(view);
        c.put(view, a2Var2);
        return a2Var2;
    }

    public static void c0(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static boolean d(View view, int i2) {
        return view.canScrollVertically(i2);
    }

    public static void d0(View view, Runnable runnable, long j2) {
        view.postOnAnimationDelayed(runnable, j2);
    }

    private static void e(View view, int i2) {
        view.offsetLeftAndRight(i2);
        if (view.getVisibility() == 0) {
            F0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                F0((View) parent);
            }
        }
    }

    public static void e0(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            f0(i2, view);
            U(view, 0);
        }
    }

    private static void f(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (view.getVisibility() == 0) {
            F0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                F0((View) parent);
            }
        }
    }

    private static void f0(int i2, View view) {
        List listP = p(view);
        for (int i3 = 0; i3 < listP.size(); i3++) {
            if (((y.h0.a) listP.get(i3)).b() == i2) {
                listP.remove(i3);
                return;
            }
        }
    }

    public static f2 g(View view, f2 f2Var) {
        WindowInsets windowInsetsS;
        if (Build.VERSION.SDK_INT >= 21 && (windowInsetsS = f2Var.s()) != null) {
            WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsetsS);
            if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsetsS)) {
                return f2.u(windowInsetsDispatchApplyWindowInsets, view);
            }
        }
        return f2Var;
    }

    public static void g0(View view, y.h0.a aVar, CharSequence charSequence, y.m0 m0Var) {
        if (m0Var == null && charSequence == null) {
            e0(view, aVar.b());
        } else {
            b(view, aVar.a(charSequence, m0Var));
        }
    }

    static boolean h(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).b(view, keyEvent);
    }

    public static void h0(View view) {
        if (Build.VERSION.SDK_INT >= 20) {
            view.requestApplyInsets();
        } else {
            view.requestFitSystemWindows();
        }
    }

    static boolean i(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).f(keyEvent);
    }

    public static void i0(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 29) {
            j.a(view, context, iArr, attributeSet, typedArray, i2, i3);
        }
    }

    public static int j() {
        return View.generateViewId();
    }

    private static g j0() {
        return new b(R$id.tag_screen_reader_focusable, Boolean.class, 28);
    }

    public static androidx.core.view.a k(View view) {
        View.AccessibilityDelegate accessibilityDelegateL = l(view);
        if (accessibilityDelegateL == null) {
            return null;
        }
        return accessibilityDelegateL instanceof androidx.core.view.a.C0022a ? ((androidx.core.view.a.C0022a) accessibilityDelegateL).a : new androidx.core.view.a(accessibilityDelegateL);
    }

    public static void k0(View view, androidx.core.view.a aVar) {
        if (aVar == null && (l(view) instanceof androidx.core.view.a.C0022a)) {
            aVar = new androidx.core.view.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.getBridge());
    }

    private static View.AccessibilityDelegate l(View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getAccessibilityDelegate() : m(view);
    }

    public static void l0(View view, boolean z) {
        a().g(view, Boolean.valueOf(z));
    }

    private static View.AccessibilityDelegate m(View view) {
        if (e) {
            return null;
        }
        if (d == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                d = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                e = true;
                return null;
            }
        }
        try {
            Object obj = d.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            e = true;
            return null;
        }
    }

    public static void m0(View view, int i2) {
        view.setAccessibilityLiveRegion(i2);
    }

    public static int n(View view) {
        return view.getAccessibilityLiveRegion();
    }

    public static void n0(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static CharSequence o(View view) {
        return (CharSequence) Z().f(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void o0(View view, ColorStateList colorStateList) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21) {
            if (view instanceof g0) {
                ((g0) view).setSupportBackgroundTintList(colorStateList);
                return;
            }
            return;
        }
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

    private static List p(View view) {
        int i2 = R$id.tag_accessibility_actions;
        ArrayList arrayList = (ArrayList) view.getTag(i2);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(i2, arrayList2);
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void p0(View view, PorterDuff.Mode mode) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21) {
            if (view instanceof g0) {
                ((g0) view).setSupportBackgroundTintMode(mode);
                return;
            }
            return;
        }
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

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList q(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof g0) {
            return ((g0) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static void q0(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode r(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof g0) {
            return ((g0) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static void r0(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            m.c.a(view, f2);
        }
    }

    public static Rect s(View view) {
        return view.getClipBounds();
    }

    public static void s0(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    public static Display t(View view) {
        return view.getDisplay();
    }

    public static void t0(View view, boolean z) {
        view.setHasTransientState(z);
    }

    public static float u(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    public static void u0(View view, int i2) {
        view.setImportantForAccessibility(i2);
    }

    private static Rect v() {
        if (f == null) {
            f = new ThreadLocal();
        }
        Rect rect = (Rect) f.get();
        if (rect == null) {
            rect = new Rect();
            f.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static void v0(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i2);
        }
    }

    public static boolean w(View view) {
        return view.getFitsSystemWindows();
    }

    public static void w0(View view, int i2, Paint paint) {
        view.setLayerType(i2, paint);
    }

    public static int x(View view) {
        return view.getImportantForAccessibility();
    }

    public static void x0(View view, b0 b0Var) {
        if (Build.VERSION.SDK_INT >= 21) {
            h.c(view, b0Var);
        }
    }

    public static int y(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static void y0(View view, int i2, int i3, int i4, int i5) {
        view.setPaddingRelative(i2, i3, i4, i5);
    }

    public static int z(View view) {
        return view.getLayoutDirection();
    }

    public static void z0(View view, f0 f0Var) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon(o0.a(f0Var != null ? f0Var.a() : null));
        }
    }
}
