package androidx.work.impl.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface PreferenceDao {
    @Query("SELECT long_value FROM Preference where `key`=:key")
    Long getLongValue(String key);

    @Query("SELECT long_value FROM Preference where `key`=:key")
    LiveData getObservableLongValue(String key);

    @Insert(onConflict = 1)
    void insertPreference(Preference preference);
}
