package androidx.work.impl.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface SystemIdInfoDao {
    @Query("SELECT * FROM SystemIdInfo WHERE work_spec_id=:workSpecId")
    SystemIdInfo getSystemIdInfo(String workSpecId);

    @Query("SELECT DISTINCT work_spec_id FROM SystemIdInfo")
    List<String> getWorkSpecIds();

    @Insert(onConflict = 1)
    void insertSystemIdInfo(SystemIdInfo systemIdInfo);

    @Query("DELETE FROM SystemIdInfo where work_spec_id=:workSpecId")
    void removeSystemIdInfo(String workSpecId);
}
