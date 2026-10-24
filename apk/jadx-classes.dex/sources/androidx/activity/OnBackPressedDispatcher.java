package androidx.activity;

import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class OnBackPressedDispatcher {
    private final Runnable a;
    final ArrayDeque b = new ArrayDeque();

    private class LifecycleOnBackPressedCancellable implements e, androidx.activity.a {
        private final d a;
        private final b b;
        private androidx.activity.a c;

        LifecycleOnBackPressedCancellable(d dVar, b bVar) {
            this.a = dVar;
            this.b = bVar;
            dVar.a(this);
        }

        @Override // androidx.lifecycle.e
        public void a(g gVar, d.b bVar) {
            if (bVar == d.b.ON_START) {
                this.c = OnBackPressedDispatcher.this.b(this.b);
                return;
            }
            if (bVar != d.b.ON_STOP) {
                if (bVar == d.b.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.a.c(this);
            this.b.e(this);
            androidx.activity.a aVar = this.c;
            if (aVar != null) {
                aVar.cancel();
                this.c = null;
            }
        }
    }

    private class a implements androidx.activity.a {
        private final b a;

        a(b bVar) {
            this.a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.b.remove(this.a);
            this.a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.a = runnable;
    }

    public void a(g gVar, b bVar) {
        d lifecycle = gVar.getLifecycle();
        if (lifecycle.b() == d.c.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator itDescendingIterator = this.b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b bVar = (b) itDescendingIterator.next();
            if (bVar.c()) {
                bVar.b();
                return;
            }
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
