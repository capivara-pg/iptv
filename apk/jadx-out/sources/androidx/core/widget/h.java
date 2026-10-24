package androidx.core.widget;

import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    public static boolean a(ListView listView, int i) {
        return listView.canScrollList(i);
    }

    public static void b(ListView listView, int i) {
        listView.scrollListBy(i);
    }
}
