package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
class p {
    private static p d;
    private final Context a;
    private final LocationManager b;
    private final a c = new a();

    private static class a {
        boolean a;
        long b;
        long c;
        long d;
        long e;
        long f;

        a() {
        }
    }

    p(Context context, LocationManager locationManager) {
        this.a = context;
        this.b = locationManager;
    }

    static p a(Context context) {
        if (d == null) {
            Context applicationContext = context.getApplicationContext();
            d = new p(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return d;
    }

    private Location b() {
        Location locationC = androidx.core.content.m.b(this.a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = androidx.core.content.m.b(this.a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        if (locationC2 == null || locationC == null) {
            return locationC2 != null ? locationC2 : locationC;
        }
        return locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    private Location c(String str) {
        try {
            if (this.b.isProviderEnabled(str)) {
                return this.b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean e() {
        return this.c.f > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j;
        long j2;
        a aVar = this.c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        o oVarB = o.b();
        oVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j3 = oVarB.a;
        oVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = oVarB.c == 1;
        long j4 = oVarB.b;
        long j5 = oVarB.a;
        boolean z2 = z;
        oVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j6 = oVarB.b;
        if (j4 == -1 || j5 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            if (jCurrentTimeMillis > j5) {
                j2 = 0 + j6;
            } else {
                j2 = jCurrentTimeMillis > j4 ? 0 + j5 : 0 + j4;
            }
            j = j2 + 60000;
        }
        aVar.a = z2;
        aVar.b = j3;
        aVar.c = j4;
        aVar.d = j5;
        aVar.e = j6;
        aVar.f = j;
    }

    boolean d() {
        a aVar = this.c;
        if (e()) {
            return aVar.a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.a;
        }
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }
}
