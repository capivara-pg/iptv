package androidx.room.util;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.room.RoomDatabase;
import ea.v;
import fa.p;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.m;
import na.c;
import t0.b;
import t0.h;
import t0.k;
import ya.l;

/* JADX INFO: loaded from: classes.dex */
public final class DBUtil {
    public static final CancellationSignal createCancellationSignal() {
        return b.b();
    }

    public static final void dropFtsSyncTriggers(h hVar) {
        m.g(hVar, "db");
        List listC = p.c();
        Cursor cursorQuery = hVar.query("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        try {
            Cursor cursor = cursorQuery;
            while (cursor.moveToNext()) {
                listC.add(cursor.getString(0));
            }
            v vVar = v.a;
            c.a(cursorQuery, (Throwable) null);
            for (String str : p.a(listC)) {
                m.f(str, "triggerName");
                if (l.u(str, "room_fts_content_sync_", false, 2, (Object) null)) {
                    hVar.execSQL("DROP TRIGGER IF EXISTS " + str);
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    public static final void foreignKeyCheck(h hVar, String str) {
        m.g(hVar, "db");
        m.g(str, "tableName");
        Cursor cursorQuery = hVar.query("PRAGMA foreign_key_check(`" + str + "`)");
        try {
            Cursor cursor = cursorQuery;
            if (cursor.getCount() > 0) {
                throw new SQLiteConstraintException(processForeignKeyCheckFailure(cursor));
            }
            v vVar = v.a;
            c.a(cursorQuery, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    private static final String processForeignKeyCheckFailure(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        int count = cursor.getCount();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                sb.append("Foreign key violation(s) detected in '");
                sb.append(cursor.getString(0));
                sb.append("'.\n");
            }
            String string = cursor.getString(3);
            if (!linkedHashMap.containsKey(string)) {
                m.f(string, "constraintIndex");
                String string2 = cursor.getString(2);
                m.f(string2, "cursor.getString(2)");
                linkedHashMap.put(string, string2);
            }
        }
        sb.append("Number of different violations discovered: ");
        sb.append(linkedHashMap.keySet().size());
        sb.append("\n");
        sb.append("Number of rows in violation: ");
        sb.append(count);
        sb.append("\n");
        sb.append("Violation(s) detected in the following constraint(s):\n");
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            sb.append("\tParent Table = ");
            sb.append(str2);
            sb.append(", Foreign Key Constraint Index = ");
            sb.append(str);
            sb.append("\n");
        }
        String string3 = sb.toString();
        m.f(string3, "StringBuilder().apply(builderAction).toString()");
        return string3;
    }

    public static final Cursor query(RoomDatabase roomDatabase, k kVar, boolean z) {
        m.g(roomDatabase, "db");
        m.g(kVar, "sqLiteQuery");
        return query(roomDatabase, kVar, z, null);
    }

    public static final int readVersion(File file) {
        m.g(file, "databaseFile");
        FileChannel channel = new FileInputStream(file).getChannel();
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            channel.tryLock(60L, 4L, true);
            channel.position(60L);
            if (channel.read(byteBufferAllocate) != 4) {
                throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
            }
            byteBufferAllocate.rewind();
            int i = byteBufferAllocate.getInt();
            c.a(channel, (Throwable) null);
            return i;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(channel, th);
                throw th2;
            }
        }
    }

    public static final Cursor query(RoomDatabase roomDatabase, k kVar, boolean z, CancellationSignal cancellationSignal) {
        m.g(roomDatabase, "db");
        m.g(kVar, "sqLiteQuery");
        Cursor cursorQuery = roomDatabase.query(kVar, cancellationSignal);
        if (!z || !(cursorQuery instanceof AbstractWindowedCursor)) {
            return cursorQuery;
        }
        AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) cursorQuery;
        int count = abstractWindowedCursor.getCount();
        return (Build.VERSION.SDK_INT < 23 || (abstractWindowedCursor.hasWindow() ? abstractWindowedCursor.getWindow().getNumRows() : count) < count) ? CursorUtil.copyAndClose(cursorQuery) : cursorQuery;
    }
}
