package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.h;
import androidx.core.view.c1;
import androidx.core.view.z1;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import y.h0;
import y.k0;
import y.l0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends androidx.core.view.a {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final androidx.customview.widget.b.a NODE_ADAPTER = new C0025a();
    private static final androidx.customview.widget.b.InterfaceC0026b SPARSE_VALUES_ADAPTER = new b();
    private final View mHost;
    private final AccessibilityManager mManager;
    private c mNodeProvider;
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final int[] mTempGlobalRect = new int[2];
    int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: androidx.customview.widget.a$a, reason: collision with other inner class name */
    static class C0025a implements androidx.customview.widget.b.a {
        C0025a() {
        }

        @Override // androidx.customview.widget.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(h0 h0Var, Rect rect) {
            h0Var.l(rect);
        }
    }

    static class b implements androidx.customview.widget.b.InterfaceC0026b {
        b() {
        }

        @Override // androidx.customview.widget.b.InterfaceC0026b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public h0 a(h hVar, int i) {
            return (h0) hVar.k(i);
        }

        @Override // androidx.customview.widget.b.InterfaceC0026b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public int b(h hVar) {
            return hVar.j();
        }
    }

    private class c extends k0 {
        c() {
        }

        public h0 b(int i) {
            return h0.M(a.this.obtainAccessibilityNodeInfo(i));
        }

        public h0 d(int i) {
            int i2 = i == 2 ? a.this.mAccessibilityFocusedVirtualViewId : a.this.mKeyboardFocusedVirtualViewId;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return b(i2);
        }

        public boolean f(int i, int i2, Bundle bundle) {
            return a.this.performAction(i, i2, bundle);
        }
    }

    public a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.mHost = view;
        this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (c1.x(view) == 0) {
            c1.u0(view, 1);
        }
    }

    private boolean c(int i) {
        if (this.mAccessibilityFocusedVirtualViewId != i) {
            return false;
        }
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        sendEventForVirtualView(i, 65536);
        return true;
    }

    private boolean d() {
        int i = this.mKeyboardFocusedVirtualViewId;
        return i != Integer.MIN_VALUE && onPerformActionForVirtualView(i, 16, null);
    }

    private AccessibilityEvent e(int i, int i2) {
        return i != -1 ? f(i, i2) : g(i2);
    }

    private AccessibilityEvent f(int i, int i2) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        h0 h0VarObtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i);
        accessibilityEventObtain.getText().add(h0VarObtainAccessibilityNodeInfo.v());
        accessibilityEventObtain.setContentDescription(h0VarObtainAccessibilityNodeInfo.q());
        accessibilityEventObtain.setScrollable(h0VarObtainAccessibilityNodeInfo.H());
        accessibilityEventObtain.setPassword(h0VarObtainAccessibilityNodeInfo.G());
        accessibilityEventObtain.setEnabled(h0VarObtainAccessibilityNodeInfo.C());
        accessibilityEventObtain.setChecked(h0VarObtainAccessibilityNodeInfo.A());
        onPopulateEventForVirtualView(i, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(h0VarObtainAccessibilityNodeInfo.o());
        l0.g(accessibilityEventObtain, this.mHost, i);
        accessibilityEventObtain.setPackageName(this.mHost.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    private AccessibilityEvent g(int i) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i);
        this.mHost.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    private h0 h(int i) {
        h0 h0VarK = h0.K();
        h0VarK.e0(true);
        h0VarK.g0(true);
        h0VarK.X(DEFAULT_CLASS_NAME);
        Rect rect = INVALID_PARENT_BOUNDS;
        h0VarK.T(rect);
        h0VarK.U(rect);
        h0VarK.m0(this.mHost);
        onPopulateNodeForVirtualView(i, h0VarK);
        if (h0VarK.v() == null && h0VarK.q() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        h0VarK.l(this.mTempParentRect);
        if (this.mTempParentRect.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iJ = h0VarK.j();
        if ((iJ & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iJ & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        h0VarK.k0(this.mHost.getContext().getPackageName());
        h0VarK.r0(this.mHost, i);
        if (this.mAccessibilityFocusedVirtualViewId == i) {
            h0VarK.R(true);
            h0VarK.a(128);
        } else {
            h0VarK.R(false);
            h0VarK.a(64);
        }
        boolean z = this.mKeyboardFocusedVirtualViewId == i;
        if (z) {
            h0VarK.a(2);
        } else if (h0VarK.D()) {
            h0VarK.a(1);
        }
        h0VarK.h0(z);
        this.mHost.getLocationOnScreen(this.mTempGlobalRect);
        h0VarK.m(this.mTempScreenRect);
        if (this.mTempScreenRect.equals(rect)) {
            h0VarK.l(this.mTempScreenRect);
            if (h0VarK.b != -1) {
                h0 h0VarK2 = h0.K();
                for (int i2 = h0VarK.b; i2 != -1; i2 = h0VarK2.b) {
                    h0VarK2.n0(this.mHost, -1);
                    h0VarK2.T(INVALID_PARENT_BOUNDS);
                    onPopulateNodeForVirtualView(i2, h0VarK2);
                    h0VarK2.l(this.mTempParentRect);
                    Rect rect2 = this.mTempScreenRect;
                    Rect rect3 = this.mTempParentRect;
                    rect2.offset(rect3.left, rect3.top);
                }
                h0VarK2.O();
            }
            this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
        }
        if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
            this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
            if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                h0VarK.U(this.mTempScreenRect);
                if (m(this.mTempScreenRect)) {
                    h0VarK.u0(true);
                }
            }
        }
        return h0VarK;
    }

    private h0 i() {
        h0 h0VarL = h0.L(this.mHost);
        c1.Y(this.mHost, h0VarL);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (h0VarL.n() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            h0VarL.c(this.mHost, ((Integer) arrayList.get(i)).intValue());
        }
        return h0VarL;
    }

    private h j() {
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        h hVar = new h();
        for (int i = 0; i < arrayList.size(); i++) {
            hVar.i(i, h(i));
        }
        return hVar;
    }

    private void k(int i, Rect rect) {
        obtainAccessibilityNodeInfo(i).l(rect);
    }

    private static Rect l(View view, int i, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i == 17) {
            rect.set(width, 0, width, height);
        } else if (i == 33) {
            rect.set(0, height, width, height);
        } else if (i == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private boolean m(Rect rect) {
        if (rect == null || rect.isEmpty() || this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.mHost.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    private static int n(int i) {
        if (i == 19) {
            return 33;
        }
        if (i != 21) {
            return i != 22 ? 130 : 66;
        }
        return 17;
    }

    private boolean o(int i, Rect rect) {
        h0 h0Var;
        h hVarJ = j();
        int i2 = this.mKeyboardFocusedVirtualViewId;
        h0 h0Var2 = i2 == Integer.MIN_VALUE ? null : (h0) hVarJ.e(i2);
        if (i == 1 || i == 2) {
            h0Var = (h0) androidx.customview.widget.b.d(hVarJ, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, h0Var2, i, c1.z(this.mHost) == 1, false);
        } else {
            if (i != 17 && i != 33 && i != 66 && i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i3 = this.mKeyboardFocusedVirtualViewId;
            if (i3 != Integer.MIN_VALUE) {
                k(i3, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                l(this.mHost, i, rect2);
            }
            h0Var = (h0) androidx.customview.widget.b.c(hVarJ, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, h0Var2, rect2, i);
        }
        return requestKeyboardFocusForVirtualView(h0Var != null ? hVarJ.h(hVarJ.g(h0Var)) : Integer.MIN_VALUE);
    }

    private boolean p(int i, int i2, Bundle bundle) {
        if (i2 == 1) {
            return requestKeyboardFocusForVirtualView(i);
        }
        if (i2 == 2) {
            return clearKeyboardFocusForVirtualView(i);
        }
        if (i2 != 64) {
            return i2 != 128 ? onPerformActionForVirtualView(i, i2, bundle) : c(i);
        }
        return r(i);
    }

    private boolean q(int i, Bundle bundle) {
        return c1.a0(this.mHost, i, bundle);
    }

    private boolean r(int i) {
        int i2;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled() || (i2 = this.mAccessibilityFocusedVirtualViewId) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            c(i2);
        }
        this.mAccessibilityFocusedVirtualViewId = i;
        this.mHost.invalidate();
        sendEventForVirtualView(i, 32768);
        return true;
    }

    private void s(int i) {
        int i2 = this.mHoveredVirtualViewId;
        if (i2 == i) {
            return;
        }
        this.mHoveredVirtualViewId = i;
        sendEventForVirtualView(i, 128);
        sendEventForVirtualView(i2, 256);
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        if (this.mKeyboardFocusedVirtualViewId != i) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i, false);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
            s(virtualViewAt);
            return virtualViewAt != Integer.MIN_VALUE;
        }
        if (action != 10 || this.mHoveredVirtualViewId == Integer.MIN_VALUE) {
            return false;
        }
        s(Integer.MIN_VALUE);
        return true;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int i = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61) {
            if (keyEvent.hasNoModifiers()) {
                return o(2, null);
            }
            if (keyEvent.hasModifiers(1)) {
                return o(1, null);
            }
            return false;
        }
        if (keyCode != 66) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (!keyEvent.hasNoModifiers()) {
                        return false;
                    }
                    int iN = n(keyCode);
                    int repeatCount = keyEvent.getRepeatCount() + 1;
                    boolean z = false;
                    while (i < repeatCount && o(iN, null)) {
                        i++;
                        z = true;
                    }
                    return z;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
            return false;
        }
        d();
        return true;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    @Override // androidx.core.view.a
    public k0 getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new c();
        }
        return this.mNodeProvider;
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float f, float f2);

    protected abstract void getVisibleVirtualViews(List list);

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i) {
        invalidateVirtualView(i, 0);
    }

    h0 obtainAccessibilityNodeInfo(int i) {
        return i == -1 ? i() : h(i);
    }

    public final void onFocusChanged(boolean z, int i, Rect rect) {
        int i2 = this.mKeyboardFocusedVirtualViewId;
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        if (z) {
            o(i, rect);
        }
    }

    @Override // androidx.core.view.a
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        onPopulateEventForHost(accessibilityEvent);
    }

    @Override // androidx.core.view.a
    public void onInitializeAccessibilityNodeInfo(View view, h0 h0Var) {
        super.onInitializeAccessibilityNodeInfo(view, h0Var);
        onPopulateNodeForHost(h0Var);
    }

    protected abstract boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle);

    protected void onPopulateEventForHost(AccessibilityEvent accessibilityEvent) {
    }

    protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
    }

    protected abstract void onPopulateNodeForHost(h0 h0Var);

    protected abstract void onPopulateNodeForVirtualView(int i, h0 h0Var);

    protected abstract void onVirtualViewKeyboardFocusChanged(int i, boolean z);

    boolean performAction(int i, int i2, Bundle bundle) {
        return i != -1 ? p(i, i2, bundle) : q(i2, bundle);
    }

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        int i2;
        if ((!this.mHost.isFocused() && !this.mHost.requestFocus()) || (i2 = this.mKeyboardFocusedVirtualViewId) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        this.mKeyboardFocusedVirtualViewId = i;
        onVirtualViewKeyboardFocusChanged(i, true);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return false;
        }
        return z1.h(parent, this.mHost, e(i, i2));
    }

    public final void invalidateVirtualView(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventE = e(i, RecyclerView.ItemAnimator.FLAG_MOVED);
        y.b.b(accessibilityEventE, i2);
        z1.h(parent, this.mHost, accessibilityEventE);
    }
}
