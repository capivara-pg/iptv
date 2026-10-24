package androidx.work.impl.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

/* JADX INFO: loaded from: classes.dex */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"work_spec_id"}, entity = WorkSpec.class, onDelete = 5, onUpdate = 5, parentColumns = {"id"})}, indices = {@Index({"work_spec_id"})}, primaryKeys = {"name", "work_spec_id"})
public class WorkName {

    @ColumnInfo(name = "name")
    public final String name;

    @ColumnInfo(name = "work_spec_id")
    public final String workSpecId;

    public WorkName(String name, String workSpecId) {
        this.name = name;
        this.workSpecId = workSpecId;
    }
}
