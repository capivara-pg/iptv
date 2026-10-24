package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class e extends a {
    private e() {
    }

    public static e i() {
        return new e();
    }

    @Override // androidx.concurrent.futures.a
    public boolean set(Object obj) {
        return super.set(obj);
    }

    @Override // androidx.concurrent.futures.a
    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    @Override // androidx.concurrent.futures.a
    public boolean setFuture(ListenableFuture listenableFuture) {
        return super.setFuture(listenableFuture);
    }
}
