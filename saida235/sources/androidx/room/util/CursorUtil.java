package androidx.room.util;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.os.Build;
import fa.i;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.m;
import na.c;
import pa.l;

/* JADX INFO: loaded from: classes.dex */
public final class CursorUtil {
    public static final Cursor copyAndClose(Cursor cursor) {
        m.g(cursor, "c");
        Cursor cursor2 = cursor;
        try {
            Cursor cursor3 = cursor2;
            MatrixCursor matrixCursor = new MatrixCursor(cursor3.getColumnNames(), cursor3.getCount());
            while (cursor3.moveToNext()) {
                Object[] objArr = new Object[cursor3.getColumnCount()];
                int columnCount = cursor.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    int type = cursor3.getType(i);
                    if (type == 0) {
                        objArr[i] = null;
                    } else if (type == 1) {
                        objArr[i] = Long.valueOf(cursor3.getLong(i));
                    } else if (type == 2) {
                        objArr[i] = Double.valueOf(cursor3.getDouble(i));
                    } else if (type == 3) {
                        objArr[i] = cursor3.getString(i);
                    } else {
                        if (type != 4) {
                            throw new IllegalStateException();
                        }
                        objArr[i] = cursor3.getBlob(i);
                    }
                }
                matrixCursor.addRow(objArr);
            }
            c.a(cursor2, (Throwable) null);
            return matrixCursor;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                c.a(cursor2, th);
                throw th2;
            }
        }
    }

    private static final int findColumnIndexBySuffix(Cursor cursor, String str) {
        if (Build.VERSION.SDK_INT > 25) {
            return -1;
        }
        if (str.length() == 0) {
            return -1;
        }
        String[] columnNames = cursor.getColumnNames();
        m.f(columnNames, "columnNames");
        return findColumnIndexBySuffix(columnNames, str);
    }

    public static final int getColumnIndex(Cursor cursor, String str) {
        m.g(cursor, "c");
        m.g(str, "name");
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        int columnIndex2 = cursor.getColumnIndex('`' + str + '`');
        return columnIndex2 >= 0 ? columnIndex2 : findColumnIndexBySuffix(cursor, str);
    }

    public static final int getColumnIndexOrThrow(Cursor cursor, String str) {
        String strZ;
        m.g(cursor, "c");
        m.g(str, "name");
        int columnIndex = getColumnIndex(cursor, str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            String[] columnNames = cursor.getColumnNames();
            m.f(columnNames, "c.columnNames");
            strZ = i.z(columnNames, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (l) null, 63, (Object) null);
        } catch (Exception unused) {
            strZ = "unknown";
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + strZ);
    }

    public static final <R> R useCursor(Cursor cursor, l lVar) {
        m.g(cursor, "<this>");
        m.g(lVar, "block");
        Cursor cursor2 = cursor;
        try {
            R r = (R) lVar.invoke(cursor2);
            k.b(1);
            c.a(cursor2, (Throwable) null);
            k.a(1);
            return r;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k.b(1);
                c.a(cursor2, th);
                k.a(1);
                throw th2;
            }
        }
    }

    public static final Cursor wrapMappedColumns(Cursor cursor, final String[] strArr, final int[] iArr) {
        m.g(cursor, "cursor");
        m.g(strArr, "columnNames");
        m.g(iArr, "mapping");
        if (strArr.length == iArr.length) {
            return new CursorWrapper(cursor) { // from class: androidx.room.util.CursorUtil.wrapMappedColumns.2
                @Override // android.database.CursorWrapper, android.database.Cursor
                public int getColumnIndex(String str) {
                    m.g(str, "columnName");
                    String[] strArr2 = strArr;
                    int[] iArr2 = iArr;
                    int length = strArr2.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3 = i2 + 1;
                        if (ya.l.l(strArr2[i], str, true)) {
                            return iArr2[i2];
                        }
                        i++;
                        i2 = i3;
                    }
                    return super.getColumnIndex(str);
                }
            };
        }
        throw new IllegalStateException("Expected columnNames.length == mapping.length".toString());
    }

    public static final int findColumnIndexBySuffix(String[] strArr, String str) {
        m.g(strArr, "columnNames");
        m.g(str, "name");
        String str2 = '.' + str;
        String str3 = '.' + str + '`';
        int length = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str4 = strArr[i];
            int i3 = i2 + 1;
            if (str4.length() >= str.length() + 2) {
                if (ya.l.k(str4, str2, false, 2, (Object) null)) {
                    return i2;
                }
                if (str4.charAt(0) == '`' && ya.l.k(str4, str3, false, 2, (Object) null)) {
                    return i2;
                }
            }
            i++;
            i2 = i3;
        }
        return -1;
    }
}
