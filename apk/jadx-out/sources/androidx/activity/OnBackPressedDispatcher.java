package androidx.activity;

import androidx.lifecycle.f;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    private final Runnable a;
    final ArrayDeque b = new ArrayDeque();

    private class LifecycleOnBackPressedCancellable implements i, androidx.activity.a {
        private final f a;
        private final b b;
        private androidx.activity.a c;

        LifecycleOnBackPressedCancellable(f fVar, b bVar) {
            this.a = fVar;
            this.b = bVar;
            fVar.a(this);
        }

        @Override // androidx.lifecycle.i
        public void a(m mVar, f.a aVar) {
            if (aVar == f.a.ON_START) {
                this.c = OnBackPressedDispatcher.this.b(this.b);
                return;
            }
            if (aVar != f.a.ON_STOP) {
                if (aVar == f.a.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar2 = this.c;
                if (aVar2 != null) {
                    aVar2.cancel();
                }
            }
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.a.d(this);
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

    public void a(m mVar, b bVar) {
        f lifecycle = mVar.getLifecycle();
        if (lifecycle.b() == f.b.DESTROYED) {
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
