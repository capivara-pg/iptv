package androidx.room.util;

import android.database.Cursor;
import fa.h0;
import fa.n0;
import fa.p;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.jvm.internal.m;
import na.c;
import t0.h;

/* JADX INFO: loaded from: classes.dex */
public final class TableInfoKt {
    private static final Map<String, TableInfo.Column> readColumns(h hVar, String str) {
        Cursor cursorQuery = hVar.query("PRAGMA table_info(`" + str + "`)");
        try {
            Cursor cursor = cursorQuery;
            if (cursor.getColumnCount() <= 0) {
                Map<String, TableInfo.Column> mapH = h0.h();
                c.a(cursorQuery, (Throwable) null);
                return mapH;
            }
            int columnIndex = cursor.getColumnIndex("name");
            int columnIndex2 = cursor.getColumnIndex("type");
            int columnIndex3 = cursor.getColumnIndex("notnull");
            int columnIndex4 = cursor.getColumnIndex("pk");
            int columnIndex5 = cursor.getColumnIndex("dflt_value");
            Map mapC = h0.c();
            while (cursor.moveToNext()) {
                String string = cursor.getString(columnIndex);
                String string2 = cursor.getString(columnIndex2);
                boolean z = cursor.getInt(columnIndex3) != 0;
                int i = cursor.getInt(columnIndex4);
                String string3 = cursor.getString(columnIndex5);
                m.f(string, "name");
                m.f(string2, "type");
                mapC.put(string, new TableInfo.Column(string, string2, z, i, string3, 2));
            }
            Map<String, TableInfo.Column> mapB = h0.b(mapC);
            c.a(cursorQuery, (Throwable) null);
            return mapB;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    private static final List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        List listC = p.c();
        while (cursor.moveToNext()) {
            int i = cursor.getInt(columnIndex);
            int i2 = cursor.getInt(columnIndex2);
            String string = cursor.getString(columnIndex3);
            m.f(string, "cursor.getString(fromColumnIndex)");
            String string2 = cursor.getString(columnIndex4);
            m.f(string2, "cursor.getString(toColumnIndex)");
            listC.add(new TableInfo.ForeignKeyWithSequence(i, i2, string, string2));
        }
        return p.c0(p.a(listC));
    }

    private static final Set<TableInfo.ForeignKey> readForeignKeys(h hVar, String str) {
        Cursor cursorQuery = hVar.query("PRAGMA foreign_key_list(`" + str + "`)");
        try {
            Cursor cursor = cursorQuery;
            int columnIndex = cursor.getColumnIndex("id");
            int columnIndex2 = cursor.getColumnIndex("seq");
            int columnIndex3 = cursor.getColumnIndex("table");
            int columnIndex4 = cursor.getColumnIndex("on_delete");
            int columnIndex5 = cursor.getColumnIndex("on_update");
            List<TableInfo.ForeignKeyWithSequence> foreignKeyFieldMappings = readForeignKeyFieldMappings(cursor);
            cursor.moveToPosition(-1);
            Set setB = n0.b();
            while (cursor.moveToNext()) {
                if (cursor.getInt(columnIndex2) == 0) {
                    int i = cursor.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList<TableInfo.ForeignKeyWithSequence> arrayList3 = new ArrayList();
                    for (Object obj : foreignKeyFieldMappings) {
                        if (((TableInfo.ForeignKeyWithSequence) obj).getId() == i) {
                            arrayList3.add(obj);
                        }
                    }
                    for (TableInfo.ForeignKeyWithSequence foreignKeyWithSequence : arrayList3) {
                        arrayList.add(foreignKeyWithSequence.getFrom());
                        arrayList2.add(foreignKeyWithSequence.getTo());
                    }
                    String string = cursor.getString(columnIndex3);
                    m.f(string, "cursor.getString(tableColumnIndex)");
                    String string2 = cursor.getString(columnIndex4);
                    m.f(string2, "cursor.getString(onDeleteColumnIndex)");
                    String string3 = cursor.getString(columnIndex5);
                    m.f(string3, "cursor.getString(onUpdateColumnIndex)");
                    setB.add(new TableInfo.ForeignKey(string, string2, string3, arrayList, arrayList2));
                }
            }
            Set<TableInfo.ForeignKey> setA = n0.a(setB);
            c.a(cursorQuery, (Throwable) null);
            return setA;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    private static final TableInfo.Index readIndex(h hVar, String str, boolean z) {
        Cursor cursorQuery = hVar.query("PRAGMA index_xinfo(`" + str + "`)");
        try {
            Cursor cursor = cursorQuery;
            int columnIndex = cursor.getColumnIndex("seqno");
            int columnIndex2 = cursor.getColumnIndex("cid");
            int columnIndex3 = cursor.getColumnIndex("name");
            int columnIndex4 = cursor.getColumnIndex("desc");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1 && columnIndex4 != -1) {
                TreeMap treeMap = new TreeMap();
                TreeMap treeMap2 = new TreeMap();
                while (cursor.moveToNext()) {
                    if (cursor.getInt(columnIndex2) >= 0) {
                        int i = cursor.getInt(columnIndex);
                        String string = cursor.getString(columnIndex3);
                        String str2 = cursor.getInt(columnIndex4) > 0 ? "DESC" : "ASC";
                        Integer numValueOf = Integer.valueOf(i);
                        m.f(string, "columnName");
                        treeMap.put(numValueOf, string);
                        treeMap2.put(Integer.valueOf(i), str2);
                    }
                }
                Collection collectionValues = treeMap.values();
                m.f(collectionValues, "columnsMap.values");
                List listI0 = p.i0(collectionValues);
                Collection collectionValues2 = treeMap2.values();
                m.f(collectionValues2, "ordersMap.values");
                TableInfo.Index index = new TableInfo.Index(str, z, listI0, p.i0(collectionValues2));
                c.a(cursorQuery, (Throwable) null);
                return index;
            }
            c.a(cursorQuery, (Throwable) null);
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    private static final Set<TableInfo.Index> readIndices(h hVar, String str) {
        Cursor cursorQuery = hVar.query("PRAGMA index_list(`" + str + "`)");
        try {
            Cursor cursor = cursorQuery;
            int columnIndex = cursor.getColumnIndex("name");
            int columnIndex2 = cursor.getColumnIndex("origin");
            int columnIndex3 = cursor.getColumnIndex("unique");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                Set setB = n0.b();
                while (cursor.moveToNext()) {
                    if (m.b("c", cursor.getString(columnIndex2))) {
                        String string = cursor.getString(columnIndex);
                        boolean z = true;
                        if (cursor.getInt(columnIndex3) != 1) {
                            z = false;
                        }
                        m.f(string, "name");
                        TableInfo.Index index = readIndex(hVar, string, z);
                        if (index == null) {
                            c.a(cursorQuery, (Throwable) null);
                            return null;
                        }
                        setB.add(index);
                    }
                }
                Set<TableInfo.Index> setA = n0.a(setB);
                c.a(cursorQuery, (Throwable) null);
                return setA;
            }
            c.a(cursorQuery, (Throwable) null);
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    public static final TableInfo readTableInfo(h hVar, String str) {
        m.g(hVar, "database");
        m.g(str, "tableName");
        return new TableInfo(str, readColumns(hVar, str), readForeignKeys(hVar, str), readIndices(hVar, str));
    }
}
