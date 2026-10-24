package androidx.viewpager2.adapter;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.m0;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class a extends RecyclerView.ViewHolder {
    private a(FrameLayout frameLayout) {
        super(frameLayout);
    }

    static a a(ViewGroup viewGroup) {
        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        frameLayout.setId(m0.k());
        frameLayout.setSaveEnabled(false);
        return new a(frameLayout);
    }

    FrameLayout b() {
        return (FrameLayout) this.itemView;
    }
}
