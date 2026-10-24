package androidx.room.util;

import android.database.Cursor;
import ea.v;
import fa.n0;
import fa.p;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Set;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.m;
import na.c;
import t0.h;
import ya.l;

/* JADX INFO: loaded from: classes.dex */
public final class FtsTableInfo {
    public static final Companion Companion = new Companion(null);
    private static final String[] FTS_OPTIONS = {"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    public final Set<String> columns;
    public final String name;
    public final Set<String> options;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(g gVar) {
            this();
        }

        private final Set<String> readColumns(h hVar, String str) {
            Set setB = n0.b();
            Cursor cursorQuery = hVar.query("PRAGMA table_info(`" + str + "`)");
            try {
                Cursor cursor = cursorQuery;
                if (cursor.getColumnCount() > 0) {
                    int columnIndex = cursor.getColumnIndex("name");
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(columnIndex);
                        m.f(string, "cursor.getString(nameIndex)");
                        setB.add(string);
                    }
                }
                v vVar = v.a;
                c.a(cursorQuery, (Throwable) null);
                return n0.a(setB);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    c.a(cursorQuery, th);
                    throw th2;
                }
            }
        }

        private final Set<String> readOptions(h hVar, String str) {
            Cursor cursorQuery = hVar.query("SELECT * FROM sqlite_master WHERE `name` = '" + str + '\'');
            try {
                Cursor cursor = cursorQuery;
                String string = cursor.moveToFirst() ? cursor.getString(cursor.getColumnIndexOrThrow("sql")) : "";
                c.a(cursorQuery, (Throwable) null);
                m.f(string, "sql");
                return parseOptions(string);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    c.a(cursorQuery, th);
                    throw th2;
                }
            }
        }

        public final Set<String> parseOptions(String str) {
            boolean z;
            Character ch;
            m.g(str, "createStatement");
            if (str.length() == 0) {
                return n0.e();
            }
            String strSubstring = str.substring(l.H(str, '(', 0, false, 6, (Object) null) + 1, l.M(str, ')', 0, false, 6, (Object) null));
            m.f(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            ArrayList arrayList = new ArrayList();
            ArrayDeque arrayDeque = new ArrayDeque();
            int i = -1;
            int i2 = 0;
            int i3 = 0;
            while (i2 < strSubstring.length()) {
                char cCharAt = strSubstring.charAt(i2);
                int i4 = i3 + 1;
                if ((cCharAt == '\'' || cCharAt == '\"') || cCharAt == '`') {
                    if (arrayDeque.isEmpty()) {
                        arrayDeque.push(Character.valueOf(cCharAt));
                    } else {
                        Character ch2 = (Character) arrayDeque.peek();
                        if (ch2 != null && ch2.charValue() == cCharAt) {
                            arrayDeque.pop();
                        }
                    }
                } else if (cCharAt == '[') {
                    if (arrayDeque.isEmpty()) {
                        arrayDeque.push(Character.valueOf(cCharAt));
                    }
                } else if (cCharAt == ']') {
                    if (!arrayDeque.isEmpty() && (ch = (Character) arrayDeque.peek()) != null && ch.charValue() == '[') {
                        arrayDeque.pop();
                    }
                } else if (cCharAt == ',' && arrayDeque.isEmpty()) {
                    String strSubstring2 = strSubstring.substring(i + 1, i3);
                    m.f(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    int length = strSubstring2.length() - 1;
                    int i5 = 0;
                    boolean z2 = false;
                    while (i5 <= length) {
                        boolean z3 = m.i(strSubstring2.charAt(!z2 ? i5 : length), 32) <= 0;
                        if (z2) {
                            if (!z3) {
                                break;
                            }
                            length--;
                        } else if (z3) {
                            i5++;
                        } else {
                            z2 = true;
                        }
                    }
                    arrayList.add(strSubstring2.subSequence(i5, length + 1).toString());
                    i = i3;
                }
                i2++;
                i3 = i4;
            }
            String strSubstring3 = strSubstring.substring(i + 1);
            m.f(strSubstring3, "this as java.lang.String).substring(startIndex)");
            arrayList.add(l.q0(strSubstring3).toString());
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                String str2 = (String) obj;
                String[] strArr = FtsTableInfo.FTS_OPTIONS;
                int length2 = strArr.length;
                int i6 = 0;
                while (true) {
                    if (i6 >= length2) {
                        z = false;
                        break;
                    }
                    if (l.u(str2, strArr[i6], false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                    i6++;
                }
                if (z) {
                    arrayList2.add(obj);
                }
            }
            return p.m0(arrayList2);
        }

        public final FtsTableInfo read(h hVar, String str) {
            m.g(hVar, "database");
            m.g(str, "tableName");
            return new FtsTableInfo(str, readColumns(hVar, str), readOptions(hVar, str));
        }
    }

    public FtsTableInfo(String str, Set<String> set, Set<String> set2) {
        m.g(str, "name");
        m.g(set, "columns");
        m.g(set2, "options");
        this.name = str;
        this.columns = set;
        this.options = set2;
    }

    public static final Set<String> parseOptions(String str) {
        return Companion.parseOptions(str);
    }

    public static final FtsTableInfo read(h hVar, String str) {
        return Companion.read(hVar, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FtsTableInfo)) {
            return false;
        }
        FtsTableInfo ftsTableInfo = (FtsTableInfo) obj;
        if (m.b(this.name, ftsTableInfo.name) && m.b(this.columns, ftsTableInfo.columns)) {
            return m.b(this.options, ftsTableInfo.options);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.columns.hashCode()) * 31) + this.options.hashCode();
    }

    public String toString() {
        return "FtsTableInfo{name='" + this.name + "', columns=" + this.columns + ", options=" + this.options + "'}";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FtsTableInfo(String str, Set<String> set, String str2) {
        this(str, set, Companion.parseOptions(str2));
        m.g(str, "name");
        m.g(set, "columns");
        m.g(str2, "createSql");
    }
}
