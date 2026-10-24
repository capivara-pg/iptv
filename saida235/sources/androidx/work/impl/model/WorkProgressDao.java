package androidx.work.impl.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface WorkProgressDao {
    @Query("DELETE from WorkProgress where work_spec_id=:workSpecId")
    void delete(String workSpecId);

    @Query("DELETE FROM WorkProgress")
    void deleteAll();

    @Query("SELECT progress FROM WorkProgress WHERE work_spec_id=:workSpecId")
    androidx.work.b getProgressForWorkSpecId(String workSpecId);

    @Query("SELECT progress FROM WorkProgress WHERE work_spec_id IN (:workSpecIds)")
    List<androidx.work.b> getProgressForWorkSpecIds(List<String> workSpecIds);

    @Insert(onConflict = 1)
    void insert(WorkProgress progress);
}
