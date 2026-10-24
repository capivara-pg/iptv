package androidx.work.impl.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.RawQuery;
import java.util.List;
import t0.k;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface RawWorkInfoDao {
    @RawQuery(observedEntities = {WorkSpec.class})
    List<WorkSpec.c> getWorkInfoPojos(k query);

    @RawQuery(observedEntities = {WorkSpec.class})
    LiveData getWorkInfoPojosLiveData(k query);
}
