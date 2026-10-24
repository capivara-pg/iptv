package androidx.room;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class QueryInterceptorProgram implements t0.j {
    private final List<Object> bindArgsCache = new ArrayList();

    private final void saveArgsToCache(int i, Object obj) {
        int size;
        int i2 = i - 1;
        if (i2 >= this.bindArgsCache.size() && (size = this.bindArgsCache.size()) <= i2) {
            while (true) {
                this.bindArgsCache.add(null);
                if (size == i2) {
                    break;
                } else {
                    size++;
                }
            }
        }
        this.bindArgsCache.set(i2, obj);
    }

    public void bindBlob(int i, byte[] bArr) {
        kotlin.jvm.internal.m.g(bArr, "value");
        saveArgsToCache(i, bArr);
    }

    public void bindDouble(int i, double d) {
        saveArgsToCache(i, Double.valueOf(d));
    }

    public void bindLong(int i, long j) {
        saveArgsToCache(i, Long.valueOf(j));
    }

    public void bindNull(int i) {
        saveArgsToCache(i, null);
    }

    public void bindString(int i, String str) {
        kotlin.jvm.internal.m.g(str, "value");
        saveArgsToCache(i, str);
    }

    public void clearBindings() {
        this.bindArgsCache.clear();
    }

    public void close() {
    }

    public final List<Object> getBindArgsCache$room_runtime_release() {
        return this.bindArgsCache;
    }
}
