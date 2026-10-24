package androidx.room;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class Room {
    private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    public static final Room INSTANCE = new Room();
    public static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    private Room() {
    }

    public static final <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(Context context, Class<T> cls, String str) {
        kotlin.jvm.internal.m.g(context, "context");
        kotlin.jvm.internal.m.g(cls, "klass");
        if (true ^ (str == null || ya.l.n(str))) {
            return new RoomDatabase.Builder<>(context, cls, str);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder".toString());
    }

    public static final <T, C> T getGeneratedImplementation(Class<C> cls, String str) {
        String str2;
        kotlin.jvm.internal.m.g(cls, "klass");
        kotlin.jvm.internal.m.g(str, "suffix");
        Package r1 = cls.getPackage();
        kotlin.jvm.internal.m.d(r1);
        String name = r1.getName();
        String canonicalName = cls.getCanonicalName();
        kotlin.jvm.internal.m.d(canonicalName);
        kotlin.jvm.internal.m.f(name, "fullPackage");
        if (!(name.length() == 0)) {
            canonicalName = canonicalName.substring(name.length() + 1);
            kotlin.jvm.internal.m.f(canonicalName, "this as java.lang.String).substring(startIndex)");
        }
        String str3 = ya.l.r(canonicalName, '.', '_', false, 4, (Object) null) + str;
        try {
            if (name.length() == 0) {
                str2 = str3;
            } else {
                str2 = name + '.' + str3;
            }
            Class<?> cls2 = Class.forName(str2, true, cls.getClassLoader());
            kotlin.jvm.internal.m.e(cls2, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.Room.getGeneratedImplementation>");
            return (T) cls2.newInstance();
        } catch (ClassNotFoundException unused) {
            throw new RuntimeException("Cannot find implementation for " + cls.getCanonicalName() + ". " + str3 + " does not exist");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("Cannot access the constructor " + cls + ".canonicalName");
        } catch (InstantiationException unused3) {
            throw new RuntimeException("Failed to create an instance of " + cls + ".canonicalName");
        }
    }

    public static final <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(Context context, Class<T> cls) {
        kotlin.jvm.internal.m.g(context, "context");
        kotlin.jvm.internal.m.g(cls, "klass");
        return new RoomDatabase.Builder<>(context, cls, null);
    }
}
