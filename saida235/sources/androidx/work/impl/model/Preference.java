package androidx.work.impl.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes.dex */
@Entity
public class Preference {

    @PrimaryKey
    @ColumnInfo(name = "key")
    public String mKey;

    @ColumnInfo(name = "long_value")
    public Long mValue;

    public Preference(String key, boolean value) {
        this(key, value ? 1L : 0L);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Preference)) {
            return false;
        }
        Preference preference = (Preference) o;
        if (!this.mKey.equals(preference.mKey)) {
            return false;
        }
        Long l = this.mValue;
        Long l2 = preference.mValue;
        if (l != null) {
            return l.equals(l2);
        }
        return l2 == null;
    }

    public int hashCode() {
        int iHashCode = this.mKey.hashCode() * 31;
        Long l = this.mValue;
        return iHashCode + (l != null ? l.hashCode() : 0);
    }

    public Preference(String key, long value) {
        this.mKey = key;
        this.mValue = Long.valueOf(value);
    }
}
