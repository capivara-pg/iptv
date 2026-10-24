package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;
import androidx.core.view.m0;
import androidx.recyclerview.R$id;

/* JADX INFO: loaded from: classes.dex */
class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
    static final ItemTouchUIUtil INSTANCE = new ItemTouchUIUtilImpl();

    ItemTouchUIUtilImpl() {
    }

    private static float findMaxElevation(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt != view) {
                float fV = m0.v(childAt);
                if (fV > f) {
                    f = fV;
                }
            }
        }
        return f;
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void clearView(View view) {
        int i = R$id.item_touch_helper_previous_elevation;
        Object tag = view.getTag(i);
        if (tag instanceof Float) {
            m0.s0(view, ((Float) tag).floatValue());
        }
        view.setTag(i, null);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
        if (z) {
            int i2 = R$id.item_touch_helper_previous_elevation;
            if (view.getTag(i2) == null) {
                Float fValueOf = Float.valueOf(m0.v(view));
                m0.s0(view, findMaxElevation(recyclerView, view) + 1.0f);
                view.setTag(i2, fValueOf);
            }
        }
        view.setTranslationX(f);
        view.setTranslationY(f2);
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onSelected(View view) {
    }
}
