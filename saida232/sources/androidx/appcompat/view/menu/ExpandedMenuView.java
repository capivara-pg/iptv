package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.r2;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements g.b, n, AdapterView.OnItemClickListener {
    private static final int[] c = {R.attr.background, R.attr.divider};
    private g a;
    private int b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.g.b
    public boolean b(i iVar) {
        return this.a.performItemAction(iVar, 0);
    }

    public int getWindowAnimations() {
        return this.b;
    }

    @Override // androidx.appcompat.view.menu.n
    public void initialize(g gVar) {
        this.a = gVar;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        b((i) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        r2 r2VarU = r2.u(context, attributeSet, c, i, 0);
        if (r2VarU.r(0)) {
            setBackgroundDrawable(r2VarU.g(0));
        }
        if (r2VarU.r(1)) {
            setDivider(r2VarU.g(1));
        }
        r2VarU.v();
    }
}
