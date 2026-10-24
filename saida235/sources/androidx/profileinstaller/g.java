package androidx.profileinstaller;

/* JADX INFO: loaded from: classes.dex */
enum g {
    DEX_FILES(0),
    EXTRA_DESCRIPTORS(1),
    CLASSES(2),
    METHODS(3),
    AGGREGATION_COUNT(4);

    private final long a;

    g(long j) {
        this.a = j;
    }

    public long b() {
        return this.a;
    }
}
