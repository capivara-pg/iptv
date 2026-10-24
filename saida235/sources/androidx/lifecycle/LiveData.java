package androidx.lifecycle;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData {
    static final Object NOT_SET = new Object();
    static final int START_VERSION = -1;
    private boolean mChangingActiveState;
    private volatile Object mData;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    volatile Object mPendingData;
    private final Runnable mPostValueRunnable;
    private int mVersion;
    final Object mDataLock = new Object();
    private j.b mObservers = new j.b();
    int mActiveCount = 0;

    class LifecycleBoundObserver extends androidx.lifecycle.LiveData.c implements i {
        final m e;

        LifecycleBoundObserver(m mVar, t tVar) {
            super(tVar);
            this.e = mVar;
        }

        @Override // androidx.lifecycle.i
        public void a(m mVar, f.a aVar) {
            f.b bVarB = this.e.getLifecycle().b();
            if (bVarB == f.b.DESTROYED) {
                LiveData.this.removeObserver(this.a);
                return;
            }
            f.b bVar = null;
            while (bVar != bVarB) {
                b(e());
                bVar = bVarB;
                bVarB = this.e.getLifecycle().b();
            }
        }

        void c() {
            this.e.getLifecycle().d(this);
        }

        boolean d(m mVar) {
            return this.e == mVar;
        }

        boolean e() {
            return this.e.getLifecycle().b().b(f.b.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.mDataLock) {
                obj = LiveData.this.mPendingData;
                LiveData.this.mPendingData = LiveData.NOT_SET;
            }
            LiveData.this.setValue(obj);
        }
    }

    private class b extends c {
        b(t tVar) {
            super(tVar);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean e() {
            return true;
        }
    }

    private abstract class c {
        final t a;
        boolean b;
        int c = -1;

        c(t tVar) {
            this.a = tVar;
        }

        void b(boolean z) {
            if (z == this.b) {
                return;
            }
            this.b = z;
            LiveData.this.changeActiveCounter(z ? 1 : -1);
            if (this.b) {
                LiveData.this.dispatchingValue(this);
            }
        }

        void c() {
        }

        boolean d(m mVar) {
            return false;
        }

        abstract boolean e();
    }

    public LiveData() {
        Object obj = NOT_SET;
        this.mPendingData = obj;
        this.mPostValueRunnable = new a();
        this.mData = obj;
        this.mVersion = -1;
    }

    static void assertMainThread(String str) {
        if (i.c.h().c()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void b(c cVar) {
        if (cVar.b) {
            if (!cVar.e()) {
                cVar.b(false);
                return;
            }
            int i = cVar.c;
            int i2 = this.mVersion;
            if (i >= i2) {
                return;
            }
            cVar.c = i2;
            cVar.a.a(this.mData);
        }
    }

    void changeActiveCounter(int i) {
        int i2 = this.mActiveCount;
        this.mActiveCount = i + i2;
        if (this.mChangingActiveState) {
            return;
        }
        this.mChangingActiveState = true;
        while (true) {
            try {
                int i3 = this.mActiveCount;
                if (i2 == i3) {
                    this.mChangingActiveState = false;
                    return;
                }
                boolean z = i2 == 0 && i3 > 0;
                boolean z2 = i2 > 0 && i3 == 0;
                if (z) {
                    onActive();
                } else if (z2) {
                    onInactive();
                }
                i2 = i3;
            } catch (Throwable th) {
                this.mChangingActiveState = false;
                throw th;
            }
        }
    }

    void dispatchingValue(androidx.lifecycle.LiveData.c cVar) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (cVar != null) {
                b(cVar);
                cVar = null;
            } else {
                j.b.d dVarC = this.mObservers.c();
                while (dVarC.hasNext()) {
                    b((c) ((Map.Entry) dVarC.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    public Object getValue() {
        Object obj = this.mData;
        if (obj != NOT_SET) {
            return obj;
        }
        return null;
    }

    int getVersion() {
        return this.mVersion;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    public boolean hasObservers() {
        return this.mObservers.size() > 0;
    }

    public boolean isInitialized() {
        return this.mData != NOT_SET;
    }

    public void observe(m mVar, t tVar) {
        assertMainThread("observe");
        if (mVar.getLifecycle().b() == f.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(mVar, tVar);
        c cVar = (c) this.mObservers.f(tVar, lifecycleBoundObserver);
        if (cVar != null && !cVar.d(mVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVar != null) {
            return;
        }
        mVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void observeForever(t tVar) {
        assertMainThread("observeForever");
        b bVar = new b(tVar);
        c cVar = (c) this.mObservers.f(tVar, bVar);
        if (cVar instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVar != null) {
            return;
        }
        bVar.b(true);
    }

    protected void onActive() {
    }

    protected void onInactive() {
    }

    protected void postValue(Object obj) {
        boolean z;
        synchronized (this.mDataLock) {
            z = this.mPendingData == NOT_SET;
            this.mPendingData = obj;
        }
        if (z) {
            i.c.h().d(this.mPostValueRunnable);
        }
    }

    public void removeObserver(t tVar) {
        assertMainThread("removeObserver");
        c cVar = (c) this.mObservers.g(tVar);
        if (cVar == null) {
            return;
        }
        cVar.c();
        cVar.b(false);
    }

    public void removeObservers(m mVar) {
        assertMainThread("removeObservers");
        for (Map.Entry entry : this.mObservers) {
            if (((c) entry.getValue()).d(mVar)) {
                removeObserver((t) entry.getKey());
            }
        }
    }

    protected void setValue(Object obj) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = obj;
        dispatchingValue(null);
    }
}
