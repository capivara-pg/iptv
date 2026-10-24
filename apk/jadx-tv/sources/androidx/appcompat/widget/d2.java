package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class d2 extends y1 implements z1 {
    private static Method L;
    private z1 K;

    public static class a extends r1 {
        final int o;
        final int p;
        private z1 q;
        private MenuItem r;

        public a(Context context, boolean z) {
            super(context, z);
            if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
                this.o = 21;
                this.p = 22;
            } else {
                this.o = 22;
                this.p = 21;
            }
        }

        @Override // androidx.appcompat.widget.r1
        public /* bridge */ /* synthetic */ int d(int i, int i2, int i3, int i4, int i5) {
            return super.d(i, i2, i3, i4, i5);
        }

        @Override // androidx.appcompat.widget.r1
        public /* bridge */ /* synthetic */ boolean e(MotionEvent motionEvent, int i) {
            return super.e(motionEvent, i);
        }

        @Override // androidx.appcompat.widget.r1, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // androidx.appcompat.widget.r1, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // androidx.appcompat.widget.r1, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // androidx.appcompat.widget.r1, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // androidx.appcompat.widget.r1, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            androidx.appcompat.view.menu.f fVar;
            int headersCount;
            int iPointToPosition;
            int i;
            if (this.q != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    fVar = (androidx.appcompat.view.menu.f) headerViewListAdapter.getWrappedAdapter();
                } else {
                    fVar = (androidx.appcompat.view.menu.f) adapter;
                    headersCount = 0;
                }
                r.b item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i = iPointToPosition - headersCount) < 0 || i >= fVar.getCount()) ? null : fVar.getItem(i);
                r.b bVar = this.r;
                if (bVar != item) {
                    androidx.appcompat.view.menu.g gVarB = fVar.b();
                    if (bVar != null) {
                        this.q.l(gVarB, bVar);
                    }
                    this.r = item;
                    if (item != null) {
                        this.q.b(gVarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.o) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i != this.p) {
                return super.onKeyDown(i, keyEvent);
            }
            setSelection(-1);
            ((androidx.appcompat.view.menu.f) getAdapter()).b().close(false);
            return true;
        }

        @Override // androidx.appcompat.widget.r1, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(z1 z1Var) {
            this.q = z1Var;
        }

        @Override // androidx.appcompat.widget.r1, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                L = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
        }
    }

    public d2(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void K(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.G.setEnterTransition((Transition) obj);
        }
    }

    public void L(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.G.setExitTransition((Transition) obj);
        }
    }

    public void M(z1 z1Var) {
        this.K = z1Var;
    }

    public void N(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.G.setTouchModal(z);
            return;
        }
        Method method = L;
        if (method != null) {
            try {
                method.invoke(this.G, Boolean.valueOf(z));
            } catch (Exception unused) {
            }
        }
    }

    @Override // androidx.appcompat.widget.z1
    public void b(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        z1 z1Var = this.K;
        if (z1Var != null) {
            z1Var.b(gVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.z1
    public void l(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        z1 z1Var = this.K;
        if (z1Var != null) {
            z1Var.l(gVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.y1
    r1 p(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }
}
