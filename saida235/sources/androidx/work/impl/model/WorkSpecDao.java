package androidx.work.impl.model;

import a1.s;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface WorkSpecDao {
    @Query("DELETE FROM workspec WHERE id=:id")
    void delete(String id);

    @Query("SELECT * FROM workspec WHERE state=0 ORDER BY period_start_time LIMIT :maxLimit")
    List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int maxLimit);

    @Query("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)")
    List<String> getAllUnfinishedWork();

    @Query("SELECT id FROM workspec")
    List<String> getAllWorkSpecIds();

    @Query("SELECT id FROM workspec")
    @Transaction
    LiveData getAllWorkSpecIdsLiveData();

    @Query("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY period_start_time LIMIT (SELECT MAX(:schedulerLimit-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))")
    List<WorkSpec> getEligibleWorkForScheduling(int schedulerLimit);

    @Query("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=:id)")
    List<androidx.work.b> getInputsFromPrerequisites(String id);

    @Query("SELECT * FROM workspec WHERE period_start_time >= :startingAt AND state IN (2, 3, 5) ORDER BY period_start_time DESC")
    List<WorkSpec> getRecentlyCompletedWork(long startingAt);

    @Query("SELECT * FROM workspec WHERE state=1")
    List<WorkSpec> getRunningWork();

    @Query("SELECT schedule_requested_at FROM workspec WHERE id=:id")
    LiveData getScheduleRequestedAtLiveData(String id);

    @Query("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1")
    List<WorkSpec> getScheduledWork();

    @Query("SELECT state FROM workspec WHERE id=:id")
    s.a getState(String id);

    @Query("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    List<String> getUnfinishedWorkWithName(String name);

    @Query("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=:tag)")
    List<String> getUnfinishedWorkWithTag(String tag);

    @Query("SELECT * FROM workspec WHERE id=:id")
    WorkSpec getWorkSpec(String id);

    @Query("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    List<WorkSpec.b> getWorkSpecIdAndStatesForName(String name);

    @Query("SELECT * FROM workspec WHERE id IN (:ids)")
    WorkSpec[] getWorkSpecs(List<String> ids);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id=:id")
    @Transaction
    WorkSpec.c getWorkStatusPojoForId(String id);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (:ids)")
    @Transaction
    List<WorkSpec.c> getWorkStatusPojoForIds(List<String> ids);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    @Transaction
    List<WorkSpec.c> getWorkStatusPojoForName(String name);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=:tag)")
    @Transaction
    List<WorkSpec.c> getWorkStatusPojoForTag(String tag);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (:ids)")
    @Transaction
    LiveData getWorkStatusPojoLiveDataForIds(List<String> ids);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=:name)")
    @Transaction
    LiveData getWorkStatusPojoLiveDataForName(String name);

    @Query("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=:tag)")
    @Transaction
    LiveData getWorkStatusPojoLiveDataForTag(String tag);

    @Query("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1")
    boolean hasUnfinishedWork();

    @Query("UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=:id")
    int incrementWorkSpecRunAttemptCount(String id);

    @Insert(onConflict = 5)
    void insertWorkSpec(WorkSpec workSpec);

    @Query("UPDATE workspec SET schedule_requested_at=:startTime WHERE id=:id")
    int markWorkSpecScheduled(String id, long startTime);

    @Query("DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))")
    void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();

    @Query("UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)")
    int resetScheduledState();

    @Query("UPDATE workspec SET run_attempt_count=0 WHERE id=:id")
    int resetWorkSpecRunAttemptCount(String id);

    @Query("UPDATE workspec SET output=:output WHERE id=:id")
    void setOutput(String id, androidx.work.b output);

    @Query("UPDATE workspec SET period_start_time=:periodStartTime WHERE id=:id")
    void setPeriodStartTime(String id, long periodStartTime);

    @Query("UPDATE workspec SET state=:state WHERE id IN (:ids)")
    int setState(s.a state, String... ids);
}
