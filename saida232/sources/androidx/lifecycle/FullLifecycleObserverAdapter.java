package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
class FullLifecycleObserverAdapter implements e {
    private final e a;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[d.b.values().length];
            a = iArr;
            try {
                iArr[d.b.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[d.b.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[d.b.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[d.b.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[d.b.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[d.b.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[d.b.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // androidx.lifecycle.e
    public void a(g gVar, d.b bVar) {
        switch (a.a[bVar.ordinal()]) {
            case 1:
                throw null;
            case 2:
                throw null;
            case 3:
                throw null;
            case 4:
                throw null;
            case 5:
                throw null;
            case 6:
                throw null;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                e eVar = this.a;
                if (eVar != null) {
                    eVar.a(gVar, bVar);
                    return;
                }
                return;
        }
    }
}
