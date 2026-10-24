package androidx.recyclerview.widget;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public interface ListUpdateCallback {
    void onChanged(int i, int i2, Object obj);

    void onInserted(int i, int i2);

    void onMoved(int i, int i2);

    void onRemoved(int i, int i2);
}
