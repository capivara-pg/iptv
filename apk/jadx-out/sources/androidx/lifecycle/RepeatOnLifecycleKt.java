package androidx.lifecycle;

import kotlin.coroutines.Continuation;

/* JADX INFO: loaded from: classes.dex */
public abstract class RepeatOnLifecycleKt {
    public static final Object a(f fVar, f.b bVar, pa.p pVar, Continuation continuation) {
        if (!(bVar != f.b.INITIALIZED)) {
            throw new IllegalArgumentException("repeatOnLifecycle cannot start work with the INITIALIZED lifecycle state.".toString());
        }
        if (fVar.b() == f.b.DESTROYED) {
            return ea.v.a;
        }
        Object objB = za.j0.b(new RepeatOnLifecycleKt$repeatOnLifecycle$3(fVar, bVar, pVar, null), continuation);
        return objB == ja.b.c() ? objB : ea.v.a;
    }

    public static final Object b(m mVar, f.b bVar, pa.p pVar, Continuation continuation) {
        Object objA = a(mVar.getLifecycle(), bVar, pVar, continuation);
        return objA == ja.b.c() ? objA : ea.v.a;
    }
}
