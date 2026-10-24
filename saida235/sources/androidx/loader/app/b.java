package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import androidx.collection.h;
import androidx.lifecycle.f0;
import androidx.lifecycle.g0;
import androidx.lifecycle.h0;
import androidx.lifecycle.j0;
import androidx.lifecycle.m;
import androidx.lifecycle.s;
import androidx.lifecycle.t;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.dex */
class b extends androidx.loader.app.a {
    static boolean c;
    private final m a;
    private final c b;

    public static class a extends s implements androidx.loader.content.b.InterfaceC0042b {
        private final int a;
        private final Bundle b;
        private final androidx.loader.content.b c;
        private m d;
        private C0040b e;
        private androidx.loader.content.b f;

        a(int i, Bundle bundle, androidx.loader.content.b bVar, androidx.loader.content.b bVar2) {
            this.a = i;
            this.b = bundle;
            this.c = bVar;
            this.f = bVar2;
            bVar.registerListener(i, this);
        }

        @Override // androidx.loader.content.b.InterfaceC0042b
        public void a(androidx.loader.content.b bVar, Object obj) {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("onLoadComplete: ");
                sb.append(this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(obj);
            } else {
                boolean z = b.c;
                postValue(obj);
            }
        }

        androidx.loader.content.b c(boolean z) {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Destroying: ");
                sb.append(this);
            }
            this.c.cancelLoad();
            this.c.abandon();
            C0040b c0040b = this.e;
            if (c0040b != null) {
                removeObserver(c0040b);
                if (z) {
                    c0040b.d();
                }
            }
            this.c.unregisterListener(this);
            if ((c0040b == null || c0040b.c()) && !z) {
                return this.c;
            }
            this.c.reset();
            return this.f;
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.a);
            printWriter.print(" mArgs=");
            printWriter.println(this.b);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.c);
            this.c.dump(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.e != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.e);
                this.e.b(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(e().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }

        androidx.loader.content.b e() {
            return this.c;
        }

        void f() {
            m mVar = this.d;
            C0040b c0040b = this.e;
            if (mVar == null || c0040b == null) {
                return;
            }
            super.removeObserver(c0040b);
            observe(mVar, c0040b);
        }

        androidx.loader.content.b g(m mVar, androidx.loader.app.a.InterfaceC0039a interfaceC0039a) {
            C0040b c0040b = new C0040b(this.c, interfaceC0039a);
            observe(mVar, c0040b);
            t tVar = this.e;
            if (tVar != null) {
                removeObserver(tVar);
            }
            this.d = mVar;
            this.e = c0040b;
            return this.c;
        }

        @Override // androidx.lifecycle.LiveData
        protected void onActive() {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Starting: ");
                sb.append(this);
            }
            this.c.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        protected void onInactive() {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Stopping: ");
                sb.append(this);
            }
            this.c.stopLoading();
        }

        @Override // androidx.lifecycle.LiveData
        public void removeObserver(t tVar) {
            super.removeObserver(tVar);
            this.d = null;
            this.e = null;
        }

        @Override // androidx.lifecycle.s, androidx.lifecycle.LiveData
        public void setValue(Object obj) {
            super.setValue(obj);
            androidx.loader.content.b bVar = this.f;
            if (bVar != null) {
                bVar.reset();
                this.f = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.a);
            sb.append(" : ");
            c0.b.a(this.c, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: androidx.loader.app.b$b, reason: collision with other inner class name */
    static class C0040b implements t {
        private final androidx.loader.content.b a;
        private final androidx.loader.app.a.InterfaceC0039a b;
        private boolean c = false;

        C0040b(androidx.loader.content.b bVar, androidx.loader.app.a.InterfaceC0039a interfaceC0039a) {
            this.a = bVar;
            this.b = interfaceC0039a;
        }

        @Override // androidx.lifecycle.t
        public void a(Object obj) {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  onLoadFinished in ");
                sb.append(this.a);
                sb.append(": ");
                sb.append(this.a.dataToString(obj));
            }
            this.b.onLoadFinished(this.a, obj);
            this.c = true;
        }

        public void b(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.c);
        }

        boolean c() {
            return this.c;
        }

        void d() {
            if (this.c) {
                if (b.c) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("  Resetting: ");
                    sb.append(this.a);
                }
                this.b.onLoaderReset(this.a);
            }
        }

        public String toString() {
            return this.b.toString();
        }
    }

    static class c extends f0 {
        private static final g0.b f = new a();
        private h d = new h();
        private boolean e = false;

        static class a implements g0.b {
            a() {
            }

            @Override // androidx.lifecycle.g0.b
            public /* synthetic */ f0 a(Class cls, j0.a aVar) {
                return h0.b(this, cls, aVar);
            }

            @Override // androidx.lifecycle.g0.b
            public f0 b(Class cls) {
                return new c();
            }
        }

        c() {
        }

        static c h(j0 j0Var) {
            return (c) new g0(j0Var, f).a(c.class);
        }

        @Override // androidx.lifecycle.f0
        protected void d() {
            super.d();
            int iJ = this.d.j();
            for (int i = 0; i < iJ; i++) {
                ((a) this.d.k(i)).c(true);
            }
            this.d.b();
        }

        public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.d.j() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.d.j(); i++) {
                    a aVar = (a) this.d.k(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.d.h(i));
                    printWriter.print(": ");
                    printWriter.println(aVar.toString());
                    aVar.d(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void g() {
            this.e = false;
        }

        a i(int i) {
            return (a) this.d.e(i);
        }

        boolean j() {
            return this.e;
        }

        void k() {
            int iJ = this.d.j();
            for (int i = 0; i < iJ; i++) {
                ((a) this.d.k(i)).f();
            }
        }

        void l(int i, a aVar) {
            this.d.i(i, aVar);
        }

        void m() {
            this.e = true;
        }
    }

    b(m mVar, j0 j0Var) {
        this.a = mVar;
        this.b = c.h(j0Var);
    }

    private androidx.loader.content.b e(int i, Bundle bundle, androidx.loader.app.a.InterfaceC0039a interfaceC0039a, androidx.loader.content.b bVar) {
        try {
            this.b.m();
            androidx.loader.content.b bVarOnCreateLoader = interfaceC0039a.onCreateLoader(i, bundle);
            if (bVarOnCreateLoader == null) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
            }
            if (bVarOnCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(bVarOnCreateLoader.getClass().getModifiers())) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + bVarOnCreateLoader);
            }
            a aVar = new a(i, bundle, bVarOnCreateLoader, bVar);
            if (c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Created new loader ");
                sb.append(aVar);
            }
            this.b.l(i, aVar);
            this.b.g();
            return aVar.g(this.a, interfaceC0039a);
        } catch (Throwable th) {
            this.b.g();
            throw th;
        }
    }

    @Override // androidx.loader.app.a
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.b.f(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.app.a
    public androidx.loader.content.b c(int i, Bundle bundle, androidx.loader.app.a.InterfaceC0039a interfaceC0039a) {
        if (this.b.j()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        a aVarI = this.b.i(i);
        if (c) {
            StringBuilder sb = new StringBuilder();
            sb.append("initLoader in ");
            sb.append(this);
            sb.append(": args=");
            sb.append(bundle);
        }
        if (aVarI == null) {
            return e(i, bundle, interfaceC0039a, null);
        }
        if (c) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  Re-using existing loader ");
            sb2.append(aVarI);
        }
        return aVarI.g(this.a, interfaceC0039a);
    }

    @Override // androidx.loader.app.a
    public void d() {
        this.b.k();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        c0.b.a(this.a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
