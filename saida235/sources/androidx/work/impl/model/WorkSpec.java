package androidx.work.impl.model;

import a1.k;
import a1.o;
import a1.s;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@Entity(indices = {@Index({"schedule_requested_at"}), @Index({"period_start_time"})})
public final class WorkSpec {
    public static final long SCHEDULE_NOT_REQUESTED_YET = -1;
    private static final String TAG = k.f("WorkSpec");
    public static final k.a WORK_INFO_MAPPER = new a();

    @ColumnInfo(name = "backoff_delay_duration")
    public long backoffDelayDuration;

    @ColumnInfo(name = "backoff_policy")
    public a1.a backoffPolicy;

    @Embedded
    public a1.b constraints;

    @ColumnInfo(name = "run_in_foreground")
    public boolean expedited;

    @ColumnInfo(name = "flex_duration")
    public long flexDuration;

    @PrimaryKey
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "initial_delay")
    public long initialDelay;

    @ColumnInfo(name = "input")
    public androidx.work.b input;

    @ColumnInfo(name = "input_merger_class_name")
    public String inputMergerClassName;

    @ColumnInfo(name = "interval_duration")
    public long intervalDuration;

    @ColumnInfo(name = "minimum_retention_duration")
    public long minimumRetentionDuration;

    @ColumnInfo(name = "out_of_quota_policy")
    public o outOfQuotaPolicy;

    @ColumnInfo(name = "output")
    public androidx.work.b output;

    @ColumnInfo(name = "period_start_time")
    public long periodStartTime;

    @ColumnInfo(name = "run_attempt_count")
    public int runAttemptCount;

    @ColumnInfo(name = "schedule_requested_at")
    public long scheduleRequestedAt;

    @ColumnInfo(name = "state")
    public s.a state;

    @ColumnInfo(name = "worker_class_name")
    public String workerClassName;

    class a implements k.a {
        a() {
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List apply(List list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((c) it.next()).a());
            }
            return arrayList;
        }
    }

    public static class b {
        public String a;
        public s.a b;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (this.b != bVar.b) {
                return false;
            }
            return this.a.equals(bVar.a);
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }
    }

    public static class c {
        public String a;
        public s.a b;
        public androidx.work.b c;
        public int d;
        public List e;
        public List f;

        public s a() {
            List list = this.f;
            return new s(UUID.fromString(this.a), this.b, this.c, this.e, (list == null || list.isEmpty()) ? androidx.work.b.c : (androidx.work.b) this.f.get(0), this.d);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (this.d != cVar.d) {
                return false;
            }
            String str = this.a;
            if (str == null ? cVar.a != null : !str.equals(cVar.a)) {
                return false;
            }
            if (this.b != cVar.b) {
                return false;
            }
            androidx.work.b bVar = this.c;
            if (bVar == null ? cVar.c != null : !bVar.equals(cVar.c)) {
                return false;
            }
            List list = this.e;
            if (list == null ? cVar.e != null : !list.equals(cVar.e)) {
                return false;
            }
            List list2 = this.f;
            List list3 = cVar.f;
            if (list2 != null) {
                return list2.equals(list3);
            }
            return list3 == null;
        }

        public int hashCode() {
            String str = this.a;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            s.a aVar = this.b;
            int iHashCode2 = (iHashCode + (aVar != null ? aVar.hashCode() : 0)) * 31;
            androidx.work.b bVar = this.c;
            int iHashCode3 = (((iHashCode2 + (bVar != null ? bVar.hashCode() : 0)) * 31) + this.d) * 31;
            List list = this.e;
            int iHashCode4 = (iHashCode3 + (list != null ? list.hashCode() : 0)) * 31;
            List list2 = this.f;
            return iHashCode4 + (list2 != null ? list2.hashCode() : 0);
        }
    }

    public WorkSpec(String id, String workerClassName) {
        this.state = s.a.a;
        androidx.work.b bVar = androidx.work.b.c;
        this.input = bVar;
        this.output = bVar;
        this.constraints = a1.b.i;
        this.backoffPolicy = a1.a.a;
        this.backoffDelayDuration = 30000L;
        this.scheduleRequestedAt = -1L;
        this.outOfQuotaPolicy = o.a;
        this.id = id;
        this.workerClassName = workerClassName;
    }

    public long calculateNextRunTime() {
        if (isBackedOff()) {
            return this.periodStartTime + Math.min(18000000L, this.backoffPolicy == a1.a.b ? this.backoffDelayDuration * ((long) this.runAttemptCount) : (long) Math.scalb(this.backoffDelayDuration, this.runAttemptCount - 1));
        }
        if (!isPeriodic()) {
            long jCurrentTimeMillis = this.periodStartTime;
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            return jCurrentTimeMillis + this.initialDelay;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        long j = this.periodStartTime;
        long j2 = j == 0 ? jCurrentTimeMillis2 + this.initialDelay : j;
        long j3 = this.flexDuration;
        long j4 = this.intervalDuration;
        if (j3 != j4) {
            return j2 + j4 + (j == 0 ? j3 * (-1) : 0L);
        }
        return j2 + (j != 0 ? j4 : 0L);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || WorkSpec.class != o.getClass()) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) o;
        if (this.initialDelay != workSpec.initialDelay || this.intervalDuration != workSpec.intervalDuration || this.flexDuration != workSpec.flexDuration || this.runAttemptCount != workSpec.runAttemptCount || this.backoffDelayDuration != workSpec.backoffDelayDuration || this.periodStartTime != workSpec.periodStartTime || this.minimumRetentionDuration != workSpec.minimumRetentionDuration || this.scheduleRequestedAt != workSpec.scheduleRequestedAt || this.expedited != workSpec.expedited || !this.id.equals(workSpec.id) || this.state != workSpec.state || !this.workerClassName.equals(workSpec.workerClassName)) {
            return false;
        }
        String str = this.inputMergerClassName;
        if (str == null ? workSpec.inputMergerClassName == null : str.equals(workSpec.inputMergerClassName)) {
            return this.input.equals(workSpec.input) && this.output.equals(workSpec.output) && this.constraints.equals(workSpec.constraints) && this.backoffPolicy == workSpec.backoffPolicy && this.outOfQuotaPolicy == workSpec.outOfQuotaPolicy;
        }
        return false;
    }

    public boolean hasConstraints() {
        return !a1.b.i.equals(this.constraints);
    }

    public int hashCode() {
        int iHashCode = ((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + this.workerClassName.hashCode()) * 31;
        String str = this.inputMergerClassName;
        int iHashCode2 = (((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.input.hashCode()) * 31) + this.output.hashCode()) * 31;
        long j = this.initialDelay;
        int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.intervalDuration;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.flexDuration;
        int iHashCode3 = (((((((i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31) + this.constraints.hashCode()) * 31) + this.runAttemptCount) * 31) + this.backoffPolicy.hashCode()) * 31;
        long j4 = this.backoffDelayDuration;
        int i3 = (iHashCode3 + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        long j5 = this.periodStartTime;
        int i4 = (i3 + ((int) (j5 ^ (j5 >>> 32)))) * 31;
        long j6 = this.minimumRetentionDuration;
        int i5 = (i4 + ((int) (j6 ^ (j6 >>> 32)))) * 31;
        long j7 = this.scheduleRequestedAt;
        return ((((i5 + ((int) (j7 ^ (j7 >>> 32)))) * 31) + (this.expedited ? 1 : 0)) * 31) + this.outOfQuotaPolicy.hashCode();
    }

    public boolean isBackedOff() {
        return this.state == s.a.a && this.runAttemptCount > 0;
    }

    public boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public void setBackoffDelayDuration(long backoffDelayDuration) {
        if (backoffDelayDuration > 18000000) {
            k.c().h(TAG, "Backoff delay duration exceeds maximum value", new Throwable[0]);
            backoffDelayDuration = 18000000;
        }
        if (backoffDelayDuration < 10000) {
            k.c().h(TAG, "Backoff delay duration less than minimum value", new Throwable[0]);
            backoffDelayDuration = 10000;
        }
        this.backoffDelayDuration = backoffDelayDuration;
    }

    public void setPeriodic(long intervalDuration) {
        if (intervalDuration < 900000) {
            k.c().h(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", 900000L), new Throwable[0]);
            intervalDuration = 900000;
        }
        setPeriodic(intervalDuration, intervalDuration);
    }

    public String toString() {
        return "{WorkSpec: " + this.id + "}";
    }

    public void setPeriodic(long intervalDuration, long flexDuration) {
        if (intervalDuration < 900000) {
            k.c().h(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", 900000L), new Throwable[0]);
            intervalDuration = 900000;
        }
        if (flexDuration < 300000) {
            k.c().h(TAG, String.format("Flex duration lesser than minimum allowed value; Changed to %s", 300000L), new Throwable[0]);
            flexDuration = 300000;
        }
        if (flexDuration > intervalDuration) {
            k.c().h(TAG, String.format("Flex duration greater than interval duration; Changed to %s", Long.valueOf(intervalDuration)), new Throwable[0]);
            flexDuration = intervalDuration;
        }
        this.intervalDuration = intervalDuration;
        this.flexDuration = flexDuration;
    }

    public WorkSpec(WorkSpec other) {
        this.state = s.a.a;
        androidx.work.b bVar = androidx.work.b.c;
        this.input = bVar;
        this.output = bVar;
        this.constraints = a1.b.i;
        this.backoffPolicy = a1.a.a;
        this.backoffDelayDuration = 30000L;
        this.scheduleRequestedAt = -1L;
        this.outOfQuotaPolicy = o.a;
        this.id = other.id;
        this.workerClassName = other.workerClassName;
        this.state = other.state;
        this.inputMergerClassName = other.inputMergerClassName;
        this.input = new androidx.work.b(other.input);
        this.output = new androidx.work.b(other.output);
        this.initialDelay = other.initialDelay;
        this.intervalDuration = other.intervalDuration;
        this.flexDuration = other.flexDuration;
        this.constraints = new a1.b(other.constraints);
        this.runAttemptCount = other.runAttemptCount;
        this.backoffPolicy = other.backoffPolicy;
        this.backoffDelayDuration = other.backoffDelayDuration;
        this.periodStartTime = other.periodStartTime;
        this.minimumRetentionDuration = other.minimumRetentionDuration;
        this.scheduleRequestedAt = other.scheduleRequestedAt;
        this.expedited = other.expedited;
        this.outOfQuotaPolicy = other.outOfQuotaPolicy;
    }
}
