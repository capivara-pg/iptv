package androidx.room;

import android.database.Cursor;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class i extends n0.c.a {
    private androidx.room.a b;
    private final a c;
    private final String d;
    private final String e;

    public static abstract class a {
        public final int a;

        public a(int i) {
            this.a = i;
        }

        protected abstract void a(n0.b bVar);

        protected abstract void b(n0.b bVar);

        protected abstract void c(n0.b bVar);

        protected abstract void d(n0.b bVar);

        protected abstract void e(n0.b bVar);

        protected abstract void f(n0.b bVar);

        protected abstract b g(n0.b bVar);
    }

    public static class b {
        public final boolean a;
        public final String b;

        public b(boolean z, String str) {
            this.a = z;
            this.b = str;
        }
    }

    public i(androidx.room.a aVar, a aVar2, String str, String str2) {
        super(aVar2.a);
        this.b = aVar;
        this.c = aVar2;
        this.d = str;
        this.e = str2;
    }

    private void h(n0.b bVar) {
        if (!k(bVar)) {
            b bVarG = this.c.g(bVar);
            if (bVarG.a) {
                this.c.e(bVar);
                l(bVar);
                return;
            } else {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + bVarG.b);
            }
        }
        Cursor cursorH = bVar.H(new n0.a("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
        try {
            String string = cursorH.moveToFirst() ? cursorH.getString(0) : null;
            cursorH.close();
            if (!this.d.equals(string) && !this.e.equals(string)) {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
            }
        } catch (Throwable th) {
            cursorH.close();
            throw th;
        }
    }

    private void i(n0.b bVar) {
        bVar.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private static boolean j(n0.b bVar) {
        Cursor cursorY = bVar.y("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (cursorY.moveToFirst() && cursorY.getInt(0) == 0) {
                z = true;
            }
            return z;
        } finally {
            cursorY.close();
        }
    }

    private static boolean k(n0.b bVar) {
        Cursor cursorY = bVar.y("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z = false;
            if (cursorY.moveToFirst() && cursorY.getInt(0) != 0) {
                z = true;
            }
            return z;
        } finally {
            cursorY.close();
        }
    }

    private void l(n0.b bVar) {
        i(bVar);
        bVar.execSQL(k0.b.a(this.d));
    }

    public void b(n0.b bVar) {
        super.b(bVar);
    }

    public void d(n0.b bVar) {
        boolean zJ = j(bVar);
        this.c.a(bVar);
        if (!zJ) {
            b bVarG = this.c.g(bVar);
            if (!bVarG.a) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + bVarG.b);
            }
        }
        l(bVar);
        this.c.c(bVar);
    }

    public void e(n0.b bVar, int i, int i2) {
        g(bVar, i, i2);
    }

    public void f(n0.b bVar) {
        super.f(bVar);
        h(bVar);
        this.c.d(bVar);
        this.b = null;
    }

    public void g(n0.b bVar, int i, int i2) {
        boolean z;
        List listC;
        androidx.room.a aVar = this.b;
        if (aVar == null || (listC = aVar.d.c(i, i2)) == null) {
            z = false;
        } else {
            this.c.f(bVar);
            Iterator it = listC.iterator();
            while (it.hasNext()) {
                ((l0.a) it.next()).a(bVar);
            }
            b bVarG = this.c.g(bVar);
            if (!bVarG.a) {
                throw new IllegalStateException("Migration didn't properly handle: " + bVarG.b);
            }
            this.c.e(bVar);
            l(bVar);
            z = true;
        }
        if (z) {
            return;
        }
        androidx.room.a aVar2 = this.b;
        if (aVar2 != null && !aVar2.a(i, i2)) {
            this.c.b(bVar);
            this.c.a(bVar);
            return;
        }
        throw new IllegalStateException("A migration from " + i + " to " + i2 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
    }
}
