package androidx.room.util;

import android.database.Cursor;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.m;
import na.c;
import t0.h;

/* JADX INFO: loaded from: classes.dex */
public final class ViewInfo {
    public static final Companion Companion = new Companion(null);
    public final String name;
    public final String sql;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(g gVar) {
            this();
        }

        public final ViewInfo read(h hVar, String str) {
            ViewInfo viewInfo;
            m.g(hVar, "database");
            m.g(str, "viewName");
            Cursor cursorQuery = hVar.query("SELECT name, sql FROM sqlite_master WHERE type = 'view' AND name = '" + str + '\'');
            try {
                Cursor cursor = cursorQuery;
                if (cursor.moveToFirst()) {
                    String string = cursor.getString(0);
                    m.f(string, "cursor.getString(0)");
                    viewInfo = new ViewInfo(string, cursor.getString(1));
                } else {
                    viewInfo = new ViewInfo(str, null);
                }
                c.a(cursorQuery, (Throwable) null);
                return viewInfo;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    c.a(cursorQuery, th);
                    throw th2;
                }
            }
        }
    }

    public ViewInfo(String str, String str2) {
        m.g(str, "name");
        this.name = str;
        this.sql = str2;
    }

    public static final ViewInfo read(h hVar, String str) {
        return Companion.read(hVar, str);
    }

    public boolean equals(Object obj) {
        boolean zB;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewInfo)) {
            return false;
        }
        ViewInfo viewInfo = (ViewInfo) obj;
        if (m.b(this.name, viewInfo.name)) {
            String str = this.sql;
            String str2 = viewInfo.sql;
            if (str != null) {
                zB = m.b(str, str2);
            } else {
                zB = str2 == null;
            }
            if (zB) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.sql;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "ViewInfo{name='" + this.name + "', sql='" + this.sql + "'}";
    }
}
