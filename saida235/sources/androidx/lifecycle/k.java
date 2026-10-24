package androidx.lifecycle;

import za.n2;
import za.r1;
import za.x0;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public static final g a(f fVar) {
        LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl;
        kotlin.jvm.internal.m.g(fVar, "<this>");
        do {
            LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl2 = (LifecycleCoroutineScopeImpl) fVar.c().get();
            if (lifecycleCoroutineScopeImpl2 != null) {
                return lifecycleCoroutineScopeImpl2;
            }
            lifecycleCoroutineScopeImpl = new LifecycleCoroutineScopeImpl(fVar, n2.b((r1) null, 1, (Object) null).plus(x0.c().H()));
        } while (!j.a(fVar.c(), null, lifecycleCoroutineScopeImpl));
        lifecycleCoroutineScopeImpl.c();
        return lifecycleCoroutineScopeImpl;
    }
}
