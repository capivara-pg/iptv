package androidx.core.widget;

import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class g extends a {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final ListView f22s;

    public g(ListView listView) {
        super(listView);
        this.f22s = listView;
    }

    @Override // androidx.core.widget.a
    public boolean a(int i) {
        return false;
    }

    @Override // androidx.core.widget.a
    public boolean b(int i) {
        ListView listView = this.f22s;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i2 = firstVisiblePosition + childCount;
        if (i > 0) {
            if (i2 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.core.widget.a
    public void j(int i, int i2) {
        h.b(this.f22s, i2);
    }
}
