package androidx.lifecycle;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import za.d2;
import za.r1;
import za.x0;

/* JADX INFO: loaded from: classes.dex */
final class RepeatOnLifecycleKt$repeatOnLifecycle$3 extends kotlin.coroutines.jvm.internal.k implements pa.p {
    int a;
    private /* synthetic */ Object b;
    final /* synthetic */ f c;
    final /* synthetic */ f.b d;
    final /* synthetic */ pa.p e;

    /* JADX INFO: renamed from: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1, reason: invalid class name */
    static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.k implements pa.p {
        Object a;
        Object b;
        Object c;
        Object d;
        Object e;
        Object f;
        int g;
        final /* synthetic */ f h;
        final /* synthetic */ f.b i;
        final /* synthetic */ za.i0 j;
        final /* synthetic */ pa.p k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(f fVar, f.b bVar, za.i0 i0Var, pa.p pVar, Continuation continuation) {
            super(2, continuation);
            this.h = fVar;
            this.i = bVar;
            this.j = i0Var;
            this.k = pVar;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.h, this.i, this.j, this.k, continuation);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x00b2  */
        /* JADX WARN: Code duplicated, block: B:28:0x00bc  */
        /* JADX WARN: Code duplicated, block: B:35:0x00cd  */
        /* JADX WARN: Code duplicated, block: B:38:0x00d7  */
        public final Object invokeSuspend(Object obj) throws Throwable {
            kotlin.jvm.internal.a0 a0Var;
            kotlin.jvm.internal.a0 a0Var2;
            r1 r1Var;
            i iVar;
            r1 r1Var2;
            i iVar2;
            Object objC = ja.b.c();
            int i = this.g;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                a0Var2 = (kotlin.jvm.internal.a0) this.b;
                a0Var = (kotlin.jvm.internal.a0) this.a;
                try {
                    ea.o.b(obj);
                    r1Var2 = (r1) a0Var.a;
                    if (r1Var2 != null) {
                        r1.a.a(r1Var2, (CancellationException) null, 1, (Object) null);
                    }
                    iVar2 = (i) a0Var2.a;
                    if (iVar2 != null) {
                        this.h.d(iVar2);
                    }
                    return ea.v.a;
                } catch (Throwable th) {
                    th = th;
                    r1Var = (r1) a0Var.a;
                    if (r1Var != null) {
                        r1.a.a(r1Var, (CancellationException) null, 1, (Object) null);
                    }
                    iVar = (i) a0Var2.a;
                    if (iVar != null) {
                        this.h.d(iVar);
                    }
                    throw th;
                }
            }
            ea.o.b(obj);
            if (this.h.b() == f.b.DESTROYED) {
                return ea.v.a;
            }
            final kotlin.jvm.internal.a0 a0Var3 = new kotlin.jvm.internal.a0();
            kotlin.jvm.internal.a0 a0Var4 = new kotlin.jvm.internal.a0();
            try {
                f.b bVar = this.i;
                f fVar = this.h;
                final za.i0 i0Var = this.j;
                final pa.p pVar = this.k;
                this.a = a0Var3;
                this.b = a0Var4;
                this.c = bVar;
                this.d = fVar;
                this.e = i0Var;
                this.f = pVar;
                this.g = 1;
                final za.o oVar = new za.o(ja.b.b(this), 1);
                oVar.z();
                f.a.C0034a c0034a = f.a.Companion;
                final f.a aVarC = c0034a.c(bVar);
                final f.a aVarA = c0034a.a(bVar);
                final kotlinx.coroutines.sync.b bVarB = kotlinx.coroutines.sync.d.b(false, 1, (Object) null);
                i iVar3 = new i() { // from class: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1

                    static final class a extends kotlin.coroutines.jvm.internal.k implements pa.p {
                        Object a;
                        Object b;
                        int c;
                        final /* synthetic */ kotlinx.coroutines.sync.b d;
                        final /* synthetic */ pa.p e;

                        /* JADX INFO: renamed from: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$a$a, reason: collision with other inner class name */
                        static final class C0032a extends kotlin.coroutines.jvm.internal.k implements pa.p {
                            int a;
                            private /* synthetic */ Object b;
                            final /* synthetic */ pa.p c;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C0032a(pa.p pVar, Continuation continuation) {
                                super(2, continuation);
                                this.c = pVar;
                            }

                            public final Continuation create(Object obj, Continuation continuation) {
                                C0032a c0032a = new C0032a(this.c, continuation);
                                c0032a.b = obj;
                                return c0032a;
                            }

                            public final Object invokeSuspend(Object obj) {
                                Object objC = ja.b.c();
                                int i = this.a;
                                if (i == 0) {
                                    ea.o.b(obj);
                                    za.i0 i0Var = (za.i0) this.b;
                                    pa.p pVar = this.c;
                                    this.a = 1;
                                    if (pVar.invoke(i0Var, this) == objC) {
                                        return objC;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ea.o.b(obj);
                                }
                                return ea.v.a;
                            }

                            public final Object invoke(za.i0 i0Var, Continuation continuation) {
                                return create(i0Var, continuation).invokeSuspend(ea.v.a);
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        a(kotlinx.coroutines.sync.b bVar, pa.p pVar, Continuation continuation) {
                            super(2, continuation);
                            this.d = bVar;
                            this.e = pVar;
                        }

                        public final Continuation create(Object obj, Continuation continuation) {
                            return new a(this.d, this.e, continuation);
                        }

                        public final Object invokeSuspend(Object obj) throws Throwable {
                            kotlinx.coroutines.sync.b bVar;
                            pa.p pVar;
                            kotlinx.coroutines.sync.b bVar2;
                            Throwable th;
                            Object objC = ja.b.c();
                            int i = this.c;
                            try {
                                if (i == 0) {
                                    ea.o.b(obj);
                                    bVar = this.d;
                                    pVar = this.e;
                                    this.a = bVar;
                                    this.b = pVar;
                                    this.c = 1;
                                    if (bVar.a((Object) null, this) == objC) {
                                        return objC;
                                    }
                                } else {
                                    if (i != 1) {
                                        if (i != 2) {
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        bVar2 = (kotlinx.coroutines.sync.b) this.a;
                                        try {
                                            ea.o.b(obj);
                                            ea.v vVar = ea.v.a;
                                            bVar2.b((Object) null);
                                            return ea.v.a;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            bVar2.b((Object) null);
                                            throw th;
                                        }
                                    }
                                    pVar = (pa.p) this.b;
                                    kotlinx.coroutines.sync.b bVar3 = (kotlinx.coroutines.sync.b) this.a;
                                    ea.o.b(obj);
                                    bVar = bVar3;
                                }
                                C0032a c0032a = new C0032a(pVar, null);
                                this.a = bVar;
                                this.b = null;
                                this.c = 2;
                                if (za.j0.b(c0032a, this) == objC) {
                                    return objC;
                                }
                                bVar2 = bVar;
                                ea.v vVar2 = ea.v.a;
                                bVar2.b((Object) null);
                                return ea.v.a;
                            } catch (Throwable th3) {
                                bVar2 = bVar;
                                th = th3;
                                bVar2.b((Object) null);
                                throw th;
                            }
                        }

                        public final Object invoke(za.i0 i0Var, Continuation continuation) {
                            return create(i0Var, continuation).invokeSuspend(ea.v.a);
                        }
                    }

                    @Override // androidx.lifecycle.i
                    public final void a(m mVar, f.a aVar) {
                        kotlin.jvm.internal.m.g(mVar, "<anonymous parameter 0>");
                        kotlin.jvm.internal.m.g(aVar, "event");
                        if (aVar == aVarC) {
                            a0Var3.a = za.h.d(i0Var, (ia.f) null, (za.k0) null, new a(bVarB, pVar, null), 3, (Object) null);
                            return;
                        }
                        if (aVar == aVarA) {
                            r1 r1Var3 = (r1) a0Var3.a;
                            if (r1Var3 != null) {
                                r1.a.a(r1Var3, (CancellationException) null, 1, (Object) null);
                            }
                            a0Var3.a = null;
                        }
                        if (aVar == f.a.ON_DESTROY) {
                            za.n nVar = oVar;
                            ea.n.a aVar2 = ea.n.a;
                            nVar.resumeWith(ea.n.a(ea.v.a));
                        }
                    }
                };
                a0Var4.a = iVar3;
                kotlin.jvm.internal.m.e(iVar3, "null cannot be cast to non-null type androidx.lifecycle.LifecycleEventObserver");
                fVar.a(iVar3);
                Object objW = oVar.w();
                if (objW == ja.b.c()) {
                    kotlin.coroutines.jvm.internal.h.c(this);
                }
                if (objW == objC) {
                    return objC;
                }
                a0Var = a0Var3;
                a0Var2 = a0Var4;
                r1Var2 = (r1) a0Var.a;
                if (r1Var2 != null) {
                    r1.a.a(r1Var2, (CancellationException) null, 1, (Object) null);
                }
                iVar2 = (i) a0Var2.a;
                if (iVar2 != null) {
                    this.h.d(iVar2);
                }
                return ea.v.a;
            } catch (Throwable th2) {
                th = th2;
                a0Var = a0Var3;
                a0Var2 = a0Var4;
                r1Var = (r1) a0Var.a;
                if (r1Var != null) {
                    r1.a.a(r1Var, (CancellationException) null, 1, (Object) null);
                }
                iVar = (i) a0Var2.a;
                if (iVar != null) {
                    this.h.d(iVar);
                }
                throw th;
            }
        }

        public final Object invoke(za.i0 i0Var, Continuation continuation) {
            return create(i0Var, continuation).invokeSuspend(ea.v.a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RepeatOnLifecycleKt$repeatOnLifecycle$3(f fVar, f.b bVar, pa.p pVar, Continuation continuation) {
        super(2, continuation);
        this.c = fVar;
        this.d = bVar;
        this.e = pVar;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        RepeatOnLifecycleKt$repeatOnLifecycle$3 repeatOnLifecycleKt$repeatOnLifecycle$3 = new RepeatOnLifecycleKt$repeatOnLifecycle$3(this.c, this.d, this.e, continuation);
        repeatOnLifecycleKt$repeatOnLifecycle$3.b = obj;
        return repeatOnLifecycleKt$repeatOnLifecycle$3;
    }

    public final Object invokeSuspend(Object obj) {
        Object objC = ja.b.c();
        int i = this.a;
        if (i == 0) {
            ea.o.b(obj);
            za.i0 i0Var = (za.i0) this.b;
            d2 d2VarH = x0.c().H();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.c, this.d, i0Var, this.e, null);
            this.a = 1;
            if (za.h.f(d2VarH, anonymousClass1, this) == objC) {
                return objC;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ea.o.b(obj);
        }
        return ea.v.a;
    }

    public final Object invoke(za.i0 i0Var, Continuation continuation) {
        return create(i0Var, continuation).invokeSuspend(ea.v.a);
    }
}
