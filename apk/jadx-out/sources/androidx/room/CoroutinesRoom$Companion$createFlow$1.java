package androidx.room;

import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;
import za.i0;
import za.j0;
import za.k0;

/* JADX INFO: loaded from: classes.dex */
@kotlin.coroutines.jvm.internal.f(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", f = "CoroutinesRoom.kt", l = {110}, m = "invokeSuspend")
final class CoroutinesRoom$Companion$createFlow$1 extends kotlin.coroutines.jvm.internal.k implements pa.p {
    final /* synthetic */ Callable<R> $callable;
    final /* synthetic */ RoomDatabase $db;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ String[] $tableNames;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1, reason: invalid class name */
    @kotlin.coroutines.jvm.internal.f(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", f = "CoroutinesRoom.kt", l = {136}, m = "invokeSuspend")
    static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.k implements pa.p {
        final /* synthetic */ kotlinx.coroutines.flow.c $$this$flow;
        final /* synthetic */ Callable<R> $callable;
        final /* synthetic */ RoomDatabase $db;
        final /* synthetic */ boolean $inTransaction;
        final /* synthetic */ String[] $tableNames;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        @kotlin.coroutines.jvm.internal.f(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1", f = "CoroutinesRoom.kt", l = {127, 129}, m = "invokeSuspend")
        static final class C00511 extends kotlin.coroutines.jvm.internal.k implements pa.p {
            final /* synthetic */ Callable<R> $callable;
            final /* synthetic */ RoomDatabase $db;
            final /* synthetic */ CoroutinesRoom$Companion$createFlow$1$1$observer$1 $observer;
            final /* synthetic */ bb.f $observerChannel;
            final /* synthetic */ bb.f $resultChannel;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00511(RoomDatabase roomDatabase, CoroutinesRoom$Companion$createFlow$1$1$observer$1 coroutinesRoom$Companion$createFlow$1$1$observer$1, bb.f fVar, Callable<R> callable, bb.f fVar2, Continuation<? super C00511> continuation) {
                super(2, continuation);
                this.$db = roomDatabase;
                this.$observer = coroutinesRoom$Companion$createFlow$1$1$observer$1;
                this.$observerChannel = fVar;
                this.$callable = callable;
                this.$resultChannel = fVar2;
            }

            public final Continuation<ea.v> create(Object obj, Continuation<?> continuation) {
                return new C00511(this.$db, this.$observer, this.$observerChannel, this.$callable, this.$resultChannel, continuation);
            }

            /* JADX WARN: Code duplicated, block: B:19:0x0048 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:23:0x0054 A[Catch: all -> 0x007a, TRY_LEAVE, TryCatch #0 {all -> 0x007a, blocks: (B:17:0x003e, B:21:0x004c, B:23:0x0054), top: B:35:0x003e }] */
            /* JADX WARN: Code duplicated, block: B:25:0x0069 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:26:0x006a  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x006a -> B:35:0x003e). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Object r0 = ja.b.c()
                    int r1 = r7.label
                    r2 = 2
                    r3 = 1
                    if (r1 == 0) goto L29
                    if (r1 == r3) goto L1f
                    if (r1 != r2) goto L17
                    java.lang.Object r1 = r7.L$0
                    bb.h r1 = (bb.h) r1
                    ea.o.b(r8)     // Catch: java.lang.Throwable -> L7c
                    r8 = r1
                    goto L3d
                L17:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L1f:
                    java.lang.Object r1 = r7.L$0
                    bb.h r1 = (bb.h) r1
                    ea.o.b(r8)     // Catch: java.lang.Throwable -> L7c
                    r4 = r1
                    r1 = r7
                    goto L4c
                L29:
                    ea.o.b(r8)
                    androidx.room.RoomDatabase r8 = r7.$db
                    androidx.room.InvalidationTracker r8 = r8.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r1 = r7.$observer
                    r8.addObserver(r1)
                    bb.f r8 = r7.$observerChannel     // Catch: java.lang.Throwable -> L7c
                    bb.h r8 = r8.iterator()     // Catch: java.lang.Throwable -> L7c
                L3d:
                    r1 = r7
                L3e:
                    r1.L$0 = r8     // Catch: java.lang.Throwable -> L7a
                    r1.label = r3     // Catch: java.lang.Throwable -> L7a
                    java.lang.Object r4 = r8.a(r1)     // Catch: java.lang.Throwable -> L7a
                    if (r4 != r0) goto L49
                    return r0
                L49:
                    r6 = r4
                    r4 = r8
                    r8 = r6
                L4c:
                    java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L7a
                    boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L7a
                    if (r8 == 0) goto L6c
                    r4.next()     // Catch: java.lang.Throwable -> L7a
                    java.util.concurrent.Callable<R> r8 = r1.$callable     // Catch: java.lang.Throwable -> L7a
                    java.lang.Object r8 = r8.call()     // Catch: java.lang.Throwable -> L7a
                    bb.f r5 = r1.$resultChannel     // Catch: java.lang.Throwable -> L7a
                    r1.L$0 = r4     // Catch: java.lang.Throwable -> L7a
                    r1.label = r2     // Catch: java.lang.Throwable -> L7a
                    java.lang.Object r8 = r5.z(r8, r1)     // Catch: java.lang.Throwable -> L7a
                    if (r8 != r0) goto L6a
                    return r0
                L6a:
                    r8 = r4
                    goto L3e
                L6c:
                    androidx.room.RoomDatabase r8 = r1.$db
                    androidx.room.InvalidationTracker r8 = r8.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r0 = r1.$observer
                    r8.removeObserver(r0)
                    ea.v r8 = ea.v.a
                    return r8
                L7a:
                    r8 = move-exception
                    goto L7e
                L7c:
                    r8 = move-exception
                    r1 = r7
                L7e:
                    androidx.room.RoomDatabase r0 = r1.$db
                    androidx.room.InvalidationTracker r0 = r0.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r1 = r1.$observer
                    r0.removeObserver(r1)
                    throw r8
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.CoroutinesRoom$Companion$createFlow$1.AnonymousClass1.C00511.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            public final Object invoke(i0 i0Var, Continuation<? super ea.v> continuation) {
                return create(i0Var, continuation).invokeSuspend(ea.v.a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, RoomDatabase roomDatabase, kotlinx.coroutines.flow.c cVar, String[] strArr, Callable<R> callable, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$inTransaction = z;
            this.$db = roomDatabase;
            this.$$this$flow = cVar;
            this.$tableNames = strArr;
            this.$callable = callable;
        }

        public final Continuation<ea.v> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$inTransaction, this.$db, this.$$this$flow, this.$tableNames, this.$callable, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Type inference failed for: r7v0, types: [androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1] */
        public final Object invokeSuspend(Object obj) {
            ia.d transactionDispatcher;
            Object objC = ja.b.c();
            int i = this.label;
            if (i == 0) {
                ea.o.b(obj);
                i0 i0Var = (i0) this.L$0;
                final bb.f fVarB = bb.i.b(-1, (bb.e) null, (pa.l) null, 6, (Object) null);
                final String[] strArr = this.$tableNames;
                ?? r7 = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1
                    @Override // androidx.room.InvalidationTracker.Observer
                    public void onInvalidated(Set<String> set) {
                        kotlin.jvm.internal.m.g(set, "tables");
                        fVarB.o(ea.v.a);
                    }
                };
                fVarB.o(ea.v.a);
                TransactionElement transactionElement = (TransactionElement) i0Var.w().get(TransactionElement.Key);
                if (transactionElement == null || (transactionDispatcher = transactionElement.getTransactionDispatcher$room_ktx_release()) == null) {
                    transactionDispatcher = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
                }
                bb.f fVarB2 = bb.i.b(0, (bb.e) null, (pa.l) null, 7, (Object) null);
                za.h.d(i0Var, transactionDispatcher, (k0) null, new C00511(this.$db, r7, fVarB, this.$callable, fVarB2, null), 2, (Object) null);
                kotlinx.coroutines.flow.c cVar = this.$$this$flow;
                this.label = 1;
                if (kotlinx.coroutines.flow.d.e(cVar, fVarB2, this) == objC) {
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

        public final Object invoke(i0 i0Var, Continuation<? super ea.v> continuation) {
            return create(i0Var, continuation).invokeSuspend(ea.v.a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutinesRoom$Companion$createFlow$1(boolean z, RoomDatabase roomDatabase, String[] strArr, Callable<R> callable, Continuation<? super CoroutinesRoom$Companion$createFlow$1> continuation) {
        super(2, continuation);
        this.$inTransaction = z;
        this.$db = roomDatabase;
        this.$tableNames = strArr;
        this.$callable = callable;
    }

    public final Continuation<ea.v> create(Object obj, Continuation<?> continuation) {
        CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.$inTransaction, this.$db, this.$tableNames, this.$callable, continuation);
        coroutinesRoom$Companion$createFlow$1.L$0 = obj;
        return coroutinesRoom$Companion$createFlow$1;
    }

    public final Object invokeSuspend(Object obj) {
        Object objC = ja.b.c();
        int i = this.label;
        if (i == 0) {
            ea.o.b(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$inTransaction, this.$db, (kotlinx.coroutines.flow.c) this.L$0, this.$tableNames, this.$callable, null);
            this.label = 1;
            if (j0.b(anonymousClass1, this) == objC) {
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

    public final Object invoke(kotlinx.coroutines.flow.c cVar, Continuation<? super ea.v> continuation) {
        return create(cVar, continuation).invokeSuspend(ea.v.a);
    }
}
