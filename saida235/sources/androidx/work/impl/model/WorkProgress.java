package androidx.work.impl.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes.dex */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"work_spec_id"}, entity = WorkSpec.class, onDelete = 5, onUpdate = 5, parentColumns = {"id"})})
public class WorkProgress {

    @ColumnInfo(name = "progress")
    public final androidx.work.b mProgress;

    @PrimaryKey
    @ColumnInfo(name = "work_spec_id")
    public final String mWorkSpecId;

    public WorkProgress(String workSpecId, androidx.work.b progress) {
        this.mWorkSpecId = workSpecId;
        this.mProgress = progress;
    }
}
