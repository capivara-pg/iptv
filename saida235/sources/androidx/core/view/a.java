package androidx.core.view;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static final View.AccessibilityDelegate DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    private final View.AccessibilityDelegate mBridge;
    private final View.AccessibilityDelegate mOriginalDelegate;

    /* JADX INFO: renamed from: androidx.core.view.a$a, reason: collision with other inner class name */
    static final class C0021a extends View.AccessibilityDelegate {
        final a a;

        C0021a(a aVar) {
            this.a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            d0.b0 accessibilityNodeProvider = this.a.getAccessibilityNodeProvider(view);
            if (accessibilityNodeProvider != null) {
                return (AccessibilityNodeProvider) accessibilityNodeProvider.e();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.a.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            d0.a0 a0VarW0 = d0.a0.w0(accessibilityNodeInfo);
            a0VarW0.o0(m0.U(view));
            a0VarW0.i0(m0.P(view));
            a0VarW0.l0(m0.p(view));
            a0VarW0.s0(m0.I(view));
            this.a.onInitializeAccessibilityNodeInfo(view, a0VarW0);
            a0VarW0.e(accessibilityNodeInfo.getText(), view);
            List<d0.a0.a> actionList = a.getActionList(view);
            for (int i = 0; i < actionList.size(); i++) {
                a0VarW0.b(actionList.get(i));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.a.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.a.performAccessibilityAction(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            this.a.sendAccessibilityEvent(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        }
    }

    public a() {
        this(DEFAULT_DELEGATE);
    }

    private boolean a(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] clickableSpanArrP = d0.a0.p(view.createAccessibilityNodeInfo().getText());
            for (int i = 0; clickableSpanArrP != null && i < clickableSpanArrP.length; i++) {
                if (clickableSpan.equals(clickableSpanArrP[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean b(int i, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (!a(clickableSpan, view)) {
            return false;
        }
        clickableSpan.onClick(view);
        return true;
    }

    static List<d0.a0.a> getActionList(View view) {
        List<d0.a0.a> list = (List) view.getTag(R$id.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
        return this.mOriginalDelegate.dispatchPopulateAccessibilityEvent(host, event);
    }

    public d0.b0 getAccessibilityNodeProvider(View host) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.mOriginalDelegate.getAccessibilityNodeProvider(host);
        if (accessibilityNodeProvider != null) {
            return new d0.b0(accessibilityNodeProvider);
        }
        return null;
    }

    View.AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(host, event);
    }

    public void onInitializeAccessibilityNodeInfo(View view, d0.a0 a0Var) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, a0Var.v0());
    }

    public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
        this.mOriginalDelegate.onPopulateAccessibilityEvent(host, event);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
        return this.mOriginalDelegate.onRequestSendAccessibilityEvent(host, child, event);
    }

    public boolean performAccessibilityAction(View host, int action, Bundle args) {
        List<d0.a0.a> actionList = getActionList(host);
        boolean zPerformAccessibilityAction = false;
        for (int i = 0; i < actionList.size(); i++) {
            d0.a0.a aVar = actionList.get(i);
            if (aVar.b() == action) {
                zPerformAccessibilityAction = aVar.d(host, args);
                break;
            }
        }
        if (!zPerformAccessibilityAction) {
            zPerformAccessibilityAction = this.mOriginalDelegate.performAccessibilityAction(host, action, args);
        }
        return (zPerformAccessibilityAction || action != R$id.accessibility_action_clickable_span) ? zPerformAccessibilityAction : b(args.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), host);
    }

    public void sendAccessibilityEvent(View host, int eventType) {
        this.mOriginalDelegate.sendAccessibilityEvent(host, eventType);
    }

    public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
        this.mOriginalDelegate.sendAccessibilityEventUnchecked(host, event);
    }

    public a(View.AccessibilityDelegate accessibilityDelegate) {
        this.mOriginalDelegate = accessibilityDelegate;
        this.mBridge = new C0021a(this);
    }
}
