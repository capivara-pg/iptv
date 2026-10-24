package androidx.work.impl.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface WorkNameDao {
    @Query("SELECT name FROM workname WHERE work_spec_id=:workSpecId")
    List<String> getNamesForWorkSpecId(String workSpecId);

    @Query("SELECT work_spec_id FROM workname WHERE name=:name")
    List<String> getWorkSpecIdsWithName(String name);

    @Insert(onConflict = 5)
    void insert(WorkName workName);
}
