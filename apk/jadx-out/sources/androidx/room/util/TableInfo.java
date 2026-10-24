package androidx.room.util;

import androidx.room.ColumnInfo;
import fa.n0;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.m;
import t0.h;
import ya.l;

/* JADX INFO: loaded from: classes.dex */
public final class TableInfo {
    public static final int CREATED_FROM_DATABASE = 2;
    public static final int CREATED_FROM_ENTITY = 1;
    public static final int CREATED_FROM_UNKNOWN = 0;
    public static final Companion Companion = new Companion(null);
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    public final Set<Index> indices;
    public final String name;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(g gVar) {
            this();
        }

        public final TableInfo read(h hVar, String str) {
            m.g(hVar, "database");
            m.g(str, "tableName");
            return TableInfoKt.readTableInfo(hVar, str);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CreatedFrom {
    }

    public static final class ForeignKey {
        public final List<String> columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List<String> referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String str, String str2, String str3, List<String> list, List<String> list2) {
            m.g(str, "referenceTable");
            m.g(str2, "onDelete");
            m.g(str3, "onUpdate");
            m.g(list, "columnNames");
            m.g(list2, "referenceColumnNames");
            this.referenceTable = str;
            this.onDelete = str2;
            this.onUpdate = str3;
            this.columnNames = list;
            this.referenceColumnNames = list2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForeignKey)) {
                return false;
            }
            ForeignKey foreignKey = (ForeignKey) obj;
            if (m.b(this.referenceTable, foreignKey.referenceTable) && m.b(this.onDelete, foreignKey.onDelete) && m.b(this.onUpdate, foreignKey.onUpdate) && m.b(this.columnNames, foreignKey.columnNames)) {
                return m.b(this.referenceColumnNames, foreignKey.referenceColumnNames);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + "', onDelete='" + this.onDelete + " +', onUpdate='" + this.onUpdate + "', columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
        }
    }

    public static final class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        private final String from;
        private final int id;
        private final int sequence;
        private final String to;

        public ForeignKeyWithSequence(int i, int i2, String str, String str2) {
            m.g(str, "from");
            m.g(str2, "to");
            this.id = i;
            this.sequence = i2;
            this.from = str;
            this.to = str2;
        }

        public final String getFrom() {
            return this.from;
        }

        public final int getId() {
            return this.id;
        }

        public final int getSequence() {
            return this.sequence;
        }

        public final String getTo() {
            return this.to;
        }

        @Override // java.lang.Comparable
        public int compareTo(ForeignKeyWithSequence foreignKeyWithSequence) {
            m.g(foreignKeyWithSequence, "other");
            int i = this.id - foreignKeyWithSequence.id;
            return i == 0 ? this.sequence - foreignKeyWithSequence.sequence : i;
        }
    }

    public TableInfo(String str, Map<String, Column> map, Set<ForeignKey> set, Set<Index> set2) {
        m.g(str, "name");
        m.g(map, "columns");
        m.g(set, "foreignKeys");
        this.name = str;
        this.columns = map;
        this.foreignKeys = set;
        this.indices = set2;
    }

    public static final TableInfo read(h hVar, String str) {
        return Companion.read(hVar, str);
    }

    public boolean equals(Object obj) {
        Set<Index> set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TableInfo)) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) obj;
        if (!m.b(this.name, tableInfo.name) || !m.b(this.columns, tableInfo.columns) || !m.b(this.foreignKeys, tableInfo.foreignKeys)) {
            return false;
        }
        Set<Index> set2 = this.indices;
        if (set2 == null || (set = tableInfo.indices) == null) {
            return true;
        }
        return m.b(set2, set);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.columns.hashCode()) * 31) + this.foreignKeys.hashCode();
    }

    public String toString() {
        return "TableInfo{name='" + this.name + "', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + '}';
    }

    public /* synthetic */ TableInfo(String str, Map map, Set set, Set set2, int i, g gVar) {
        this(str, map, set, (i & 8) != 0 ? null : set2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TableInfo(String str, Map<String, Column> map, Set<ForeignKey> set) {
        this(str, map, set, n0.e());
        m.g(str, "name");
        m.g(map, "columns");
        m.g(set, "foreignKeys");
    }

    public static final class Column {
        public static final Companion Companion = new Companion(null);
        public final int affinity;
        public final int createdFrom;
        public final String defaultValue;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(g gVar) {
                this();
            }

            private final boolean containsSurroundingParenthesis(String str) {
                if (str.length() == 0) {
                    return false;
                }
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                while (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    int i4 = i3 + 1;
                    if (i3 == 0 && cCharAt != '(') {
                        return false;
                    }
                    if (cCharAt == '(') {
                        i2++;
                    } else if (cCharAt == ')' && (i2 = i2 - 1) == 0 && i3 != str.length() - 1) {
                        return false;
                    }
                    i++;
                    i3 = i4;
                }
                return i2 == 0;
            }

            public final boolean defaultValueEquals(String str, String str2) {
                m.g(str, "current");
                if (m.b(str, str2)) {
                    return true;
                }
                if (!containsSurroundingParenthesis(str)) {
                    return false;
                }
                String strSubstring = str.substring(1, str.length() - 1);
                m.f(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                return m.b(l.q0(strSubstring).toString(), str2);
            }
        }

        public Column(String str, String str2, boolean z, int i, String str3, int i2) {
            m.g(str, "name");
            m.g(str2, "type");
            this.name = str;
            this.type = str2;
            this.notNull = z;
            this.primaryKeyPosition = i;
            this.defaultValue = str3;
            this.createdFrom = i2;
            this.affinity = findAffinity(str2);
        }

        public static final boolean defaultValueEquals(String str, String str2) {
            return Companion.defaultValueEquals(str, str2);
        }

        @ColumnInfo.SQLiteTypeAffinity
        private final int findAffinity(String str) {
            if (str == null) {
                return 5;
            }
            Locale locale = Locale.US;
            m.f(locale, "US");
            String upperCase = str.toUpperCase(locale);
            m.f(upperCase, "this as java.lang.String).toUpperCase(locale)");
            if (l.x(upperCase, "INT", false, 2, (Object) null)) {
                return 3;
            }
            if (l.x(upperCase, "CHAR", false, 2, (Object) null) || l.x(upperCase, "CLOB", false, 2, (Object) null) || l.x(upperCase, "TEXT", false, 2, (Object) null)) {
                return 2;
            }
            if (l.x(upperCase, "BLOB", false, 2, (Object) null)) {
                return 5;
            }
            return (l.x(upperCase, "REAL", false, 2, (Object) null) || l.x(upperCase, "FLOA", false, 2, (Object) null) || l.x(upperCase, "DOUB", false, 2, (Object) null)) ? 4 : 1;
        }

        @ColumnInfo.SQLiteTypeAffinity
        public static /* synthetic */ void getAffinity$annotations() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x0072, code lost:
        
            r1 = true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean equals(java.lang.Object r7) {
            /*
                r6 = this;
                r0 = 1
                if (r6 != r7) goto L4
                return r0
            L4:
                boolean r1 = r7 instanceof androidx.room.util.TableInfo.Column
                r2 = 0
                if (r1 != 0) goto La
                return r2
            La:
                int r1 = r6.primaryKeyPosition
                r3 = r7
                androidx.room.util.TableInfo$Column r3 = (androidx.room.util.TableInfo.Column) r3
                int r3 = r3.primaryKeyPosition
                if (r1 == r3) goto L14
                return r2
            L14:
                java.lang.String r1 = r6.name
                androidx.room.util.TableInfo$Column r7 = (androidx.room.util.TableInfo.Column) r7
                java.lang.String r3 = r7.name
                boolean r1 = kotlin.jvm.internal.m.b(r1, r3)
                if (r1 != 0) goto L21
                return r2
            L21:
                boolean r1 = r6.notNull
                boolean r3 = r7.notNull
                if (r1 == r3) goto L28
                return r2
            L28:
                int r1 = r6.createdFrom
                r3 = 2
                if (r1 != r0) goto L40
                int r1 = r7.createdFrom
                if (r1 != r3) goto L40
                java.lang.String r1 = r6.defaultValue
                if (r1 == 0) goto L40
                androidx.room.util.TableInfo$Column$Companion r4 = androidx.room.util.TableInfo.Column.Companion
                java.lang.String r5 = r7.defaultValue
                boolean r1 = r4.defaultValueEquals(r1, r5)
                if (r1 != 0) goto L40
                return r2
            L40:
                int r1 = r6.createdFrom
                if (r1 != r3) goto L57
                int r1 = r7.createdFrom
                if (r1 != r0) goto L57
                java.lang.String r1 = r7.defaultValue
                if (r1 == 0) goto L57
                androidx.room.util.TableInfo$Column$Companion r3 = androidx.room.util.TableInfo.Column.Companion
                java.lang.String r4 = r6.defaultValue
                boolean r1 = r3.defaultValueEquals(r1, r4)
                if (r1 != 0) goto L57
                return r2
            L57:
                int r1 = r6.createdFrom
                if (r1 == 0) goto L78
                int r3 = r7.createdFrom
                if (r1 != r3) goto L78
                java.lang.String r1 = r6.defaultValue
                if (r1 == 0) goto L6e
                androidx.room.util.TableInfo$Column$Companion r3 = androidx.room.util.TableInfo.Column.Companion
                java.lang.String r4 = r7.defaultValue
                boolean r1 = r3.defaultValueEquals(r1, r4)
                if (r1 != 0) goto L74
                goto L72
            L6e:
                java.lang.String r1 = r7.defaultValue
                if (r1 == 0) goto L74
            L72:
                r1 = 1
                goto L75
            L74:
                r1 = 0
            L75:
                if (r1 == 0) goto L78
                return r2
            L78:
                int r1 = r6.affinity
                int r7 = r7.affinity
                if (r1 != r7) goto L7f
                goto L80
            L7f:
                r0 = 0
            L80:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.TableInfo.Column.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public final boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Column{name='");
            sb.append(this.name);
            sb.append("', type='");
            sb.append(this.type);
            sb.append("', affinity='");
            sb.append(this.affinity);
            sb.append("', notNull=");
            sb.append(this.notNull);
            sb.append(", primaryKeyPosition=");
            sb.append(this.primaryKeyPosition);
            sb.append(", defaultValue='");
            String str = this.defaultValue;
            if (str == null) {
                str = "undefined";
            }
            sb.append(str);
            sb.append("'}");
            return sb.toString();
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Column(String str, String str2, boolean z, int i) {
            this(str, str2, z, i, null, 0);
            m.g(str, "name");
            m.g(str2, "type");
        }
    }

    public static final class Index {
        public static final Companion Companion = new Companion(null);
        public static final String DEFAULT_PREFIX = "index_";
        public final List<String> columns;
        public final String name;
        public List<String> orders;
        public final boolean unique;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(g gVar) {
                this();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, java.util.Collection, java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList] */
        public Index(String str, boolean z, List<String> list, List<String> list2) {
            m.g(str, "name");
            m.g(list, "columns");
            m.g((Object) list2, "orders");
            this.name = str;
            this.unique = z;
            this.columns = list;
            this.orders = list2;
            if (list2.isEmpty()) {
                int size = list.size();
                list2 = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    list2.add(androidx.room.Index.Order.ASC.name());
                }
            }
            this.orders = list2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Index)) {
                return false;
            }
            Index index = (Index) obj;
            if (this.unique == index.unique && m.b(this.columns, index.columns) && m.b(this.orders, index.orders)) {
                return l.u(this.name, DEFAULT_PREFIX, false, 2, (Object) null) ? l.u(index.name, DEFAULT_PREFIX, false, 2, (Object) null) : m.b(this.name, index.name);
            }
            return false;
        }

        public int hashCode() {
            return ((((((l.u(this.name, DEFAULT_PREFIX, false, 2, (Object) null) ? -1184239155 : this.name.hashCode()) * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode()) * 31) + this.orders.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.name + "', unique=" + this.unique + ", columns=" + this.columns + ", orders=" + this.orders + "'}";
        }

        public Index(String str, boolean z, List<String> list) {
            m.g(str, "name");
            m.g(list, "columns");
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(androidx.room.Index.Order.ASC.name());
            }
            this(str, z, list, arrayList);
        }
    }
}
