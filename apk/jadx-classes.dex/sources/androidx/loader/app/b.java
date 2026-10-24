package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import androidx.collection.h;
import androidx.lifecycle.g;
import androidx.lifecycle.l;
import androidx.lifecycle.m;
import androidx.lifecycle.v;
import androidx.lifecycle.w;
import androidx.lifecycle.x;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class b extends androidx.loader.app.a {
    static boolean c;
    private final g a;
    private final c b;

    public static class a extends l implements androidx.loader.content.b.InterfaceC0036b {
        private final int l;
        private final Bundle m;
        private final androidx.loader.content.b n;
        private g o;
        private C0034b p;
        private androidx.loader.content.b q;

        a(int i, Bundle bundle, androidx.loader.content.b bVar, androidx.loader.content.b bVar2) {
            this.l = i;
            this.m = bundle;
            this.n = bVar;
            this.q = bVar2;
            bVar.registerListener(i, this);
        }

        @Override // androidx.loader.content.b.InterfaceC0036b
        public void a(androidx.loader.content.b bVar, Object obj) {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("onLoadComplete: ");
                sb.append(this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                n(obj);
            } else {
                boolean z = b.c;
                l(obj);
            }
        }

        @Override // androidx.lifecycle.LiveData
        protected void j() {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Starting: ");
                sb.append(this);
            }
            this.n.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        protected void k() {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Stopping: ");
                sb.append(this);
            }
            this.n.stopLoading();
        }

        @Override // androidx.lifecycle.LiveData
        public void m(m mVar) {
            super.m(mVar);
            this.o = null;
            this.p = null;
        }

        @Override // androidx.lifecycle.l, androidx.lifecycle.LiveData
        public void n(Object obj) {
            super.n(obj);
            androidx.loader.content.b bVar = this.q;
            if (bVar != null) {
                bVar.reset();
                this.q = null;
            }
        }

        androidx.loader.content.b o(boolean z) {
            if (b.c) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Destroying: ");
                sb.append(this);
            }
            this.n.cancelLoad();
            this.n.abandon();
            C0034b c0034b = this.p;
            if (c0034b != null) {
                m(c0034b);
                if (z) {
                    c0034b.d();
                }
            }
            this.n.unregisterListener(this);
            if ((c0034b == null || c0034b.c()) && !z) {
                return this.n;
            }
            this.n.reset();
            return this.q;
        }

        public void p(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.l);
            printWriter.print(" mArgs=");
            printWriter.println(this.m);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.n);
            this.n.dump(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.p != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.p);
                this.p.b(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(q().dataToString(f()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(g());
        }

        androidx.loader.content.b q() {
            return this.n;
        }

        void r() {
            g gVar = this.o;
            C0034b c0034b = this.p;
            if (gVar == null || c0034b == null) {
                return;
            }
            super.m(c0034b);
            h(gVar, c0034b);
        }

        androidx.loader.content.b s(g gVar, androidx.loader.app.a.InterfaceC0033a interfaceC0033a) {
            C0034b c0034b = new C0034b(this.n, interfaceC0033a);
            h(gVar, c0034b);
            m mVar = this.p;
            if (mVar != null) {
                m(mVar);
            }
            this.o = gVar;
            this.p = c0034b;
            return this.n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.l);
            sb.append(" : ");
            x.b.a(this.n, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: androidx.loader.app.b$b, reason: collision with other inner class name */
    static class C0034b implements m {
        private final androidx.loader.content.b a;
        private final androidx.loader.app.a.InterfaceC0033a b;
        private boolean c = false;

        C0034b(androidx.loader.content.b bVar, androidx.loader.app.a.InterfaceC0033a interfaceC0033a) {
            this.a = bVar;
            this.b = interfaceC0033a;
        }

        @Override // androidx.lifecycle.m
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

    static class c extends v {
        private static final w.b e = new a();
        private h c = new h();
        private boolean d = false;

        static class a implements w.b {
            a() {
            }

            @Override // androidx.lifecycle.w.b
            public v a(Class cls) {
                return new c();
            }
        }

        c() {
        }

        static c h(x xVar) {
            return (c) new w(xVar, e).a(c.class);
        }

        @Override // androidx.lifecycle.v
        protected void d() {
            super.d();
            int iJ = this.c.j();
            for (int i = 0; i < iJ; i++) {
                ((a) this.c.k(i)).o(true);
            }
            this.c.b();
        }

        public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.c.j() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.c.j(); i++) {
                    a aVar = (a) this.c.k(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.c.h(i));
                    printWriter.print(": ");
                    printWriter.println(aVar.toString());
                    aVar.p(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void g() {
            this.d = false;
        }

        a i(int i) {
            return (a) this.c.e(i);
        }

        boolean j() {
            return this.d;
        }

        void k() {
            int iJ = this.c.j();
            for (int i = 0; i < iJ; i++) {
                ((a) this.c.k(i)).r();
            }
        }

        void l(int i, a aVar) {
            this.c.i(i, aVar);
        }

        void m() {
            this.d = true;
        }
    }

    b(g gVar, x xVar) {
        this.a = gVar;
        this.b = c.h(xVar);
    }

    private androidx.loader.content.b e(int i, Bundle bundle, androidx.loader.app.a.InterfaceC0033a interfaceC0033a, androidx.loader.content.b bVar) {
        try {
            this.b.m();
            androidx.loader.content.b bVarOnCreateLoader = interfaceC0033a.onCreateLoader(i, bundle);
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
            return aVar.s(this.a, interfaceC0033a);
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
    public androidx.loader.content.b c(int i, Bundle bundle, androidx.loader.app.a.InterfaceC0033a interfaceC0033a) {
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
            return e(i, bundle, interfaceC0033a, null);
        }
        if (c) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  Re-using existing loader ");
            sb2.append(aVarI);
        }
        return aVarI.s(this.a, interfaceC0033a);
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
        x.b.a(this.a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
