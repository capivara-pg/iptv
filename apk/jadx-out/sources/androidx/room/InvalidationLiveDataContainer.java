package androidx.room;

import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class InvalidationLiveDataContainer {
    private final RoomDatabase database;
    private final Set<LiveData> liveDataSet;

    public InvalidationLiveDataContainer(RoomDatabase roomDatabase) {
        kotlin.jvm.internal.m.g(roomDatabase, "database");
        this.database = roomDatabase;
        Set<LiveData> setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        kotlin.jvm.internal.m.f(setNewSetFromMap, "newSetFromMap(IdentityHashMap())");
        this.liveDataSet = setNewSetFromMap;
    }

    public final <T> LiveData create(String[] strArr, boolean z, Callable<T> callable) {
        kotlin.jvm.internal.m.g(strArr, "tableNames");
        kotlin.jvm.internal.m.g(callable, "computeFunction");
        return new RoomTrackingLiveData(this.database, this, z, callable, strArr);
    }

    public final Set<LiveData> getLiveDataSet$room_runtime_release() {
        return this.liveDataSet;
    }

    public final void onActive(LiveData liveData) {
        kotlin.jvm.internal.m.g(liveData, "liveData");
        this.liveDataSet.add(liveData);
    }

    public final void onInactive(LiveData liveData) {
        kotlin.jvm.internal.m.g(liveData, "liveData");
        this.liveDataSet.remove(liveData);
    }
}
