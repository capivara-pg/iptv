package androidx.work.impl.foreground;

import a1.e;
import a1.k;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkSpec;
import d1.c;
import d1.d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class a implements c, androidx.work.impl.b {
    static final String k = k.f("SystemFgDispatcher");
    private Context a;
    private androidx.work.impl.k b;
    private final j1.a c;
    final Object d = new Object();
    String e;
    final Map f;
    final Map g;
    final Set h;
    final d i;
    private b j;

    /* JADX INFO: renamed from: androidx.work.impl.foreground.a$a, reason: collision with other inner class name */
    class RunnableC0061a implements Runnable {
        final /* synthetic */ WorkDatabase a;
        final /* synthetic */ String b;

        RunnableC0061a(WorkDatabase workDatabase, String str) {
            this.a = workDatabase;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            WorkSpec workSpec = this.a.workSpecDao().getWorkSpec(this.b);
            if (workSpec == null || !workSpec.hasConstraints()) {
                return;
            }
            synchronized (a.this.d) {
                a.this.g.put(this.b, workSpec);
                a.this.h.add(workSpec);
                a aVar = a.this;
                aVar.i.d(aVar.h);
            }
        }
    }

    interface b {
        void a(int i, Notification notification);

        void c(int i, int i2, Notification notification);

        void d(int i);

        void stop();
    }

    a(Context context) {
        this.a = context;
        androidx.work.impl.k kVarJ = androidx.work.impl.k.j(context);
        this.b = kVarJ;
        j1.a aVarO = kVarJ.o();
        this.c = aVarO;
        this.e = null;
        this.f = new LinkedHashMap();
        this.h = new HashSet();
        this.g = new HashMap();
        this.i = new d(this.a, aVarO, this);
        this.b.l().c(this);
    }

    public static Intent a(Context context, String str, e eVar) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", eVar.c());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", eVar.a());
        intent.putExtra("KEY_NOTIFICATION", eVar.b());
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    public static Intent c(Context context, String str, e eVar) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_NOTIFICATION_ID", eVar.c());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", eVar.a());
        intent.putExtra("KEY_NOTIFICATION", eVar.b());
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    public static Intent f(Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_STOP_FOREGROUND");
        return intent;
    }

    private void g(Intent intent) {
        k.c().d(k, String.format("Stopping foreground work for %s", intent), new Throwable[0]);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra == null || TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.b.e(UUID.fromString(stringExtra));
    }

    private void h(Intent intent) {
        int iA = 0;
        int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
        k.c().a(k, String.format("Notifying with (id: %s, workSpecId: %s, notificationType: %s)", Integer.valueOf(intExtra), stringExtra, Integer.valueOf(intExtra2)), new Throwable[0]);
        if (notification == null || this.j == null) {
            return;
        }
        this.f.put(stringExtra, new e(intExtra, notification, intExtra2));
        if (TextUtils.isEmpty(this.e)) {
            this.e = stringExtra;
            this.j.c(intExtra, intExtra2, notification);
            return;
        }
        this.j.a(intExtra, notification);
        if (intExtra2 == 0 || Build.VERSION.SDK_INT < 29) {
            return;
        }
        Iterator it = this.f.entrySet().iterator();
        while (it.hasNext()) {
            iA |= ((e) ((Map.Entry) it.next()).getValue()).a();
        }
        e eVar = (e) this.f.get(this.e);
        if (eVar != null) {
            this.j.c(eVar.c(), iA, eVar.b());
        }
    }

    private void i(Intent intent) {
        k.c().d(k, String.format("Started foreground service %s", intent), new Throwable[0]);
        this.c.b(new RunnableC0061a(this.b.n(), intent.getStringExtra("KEY_WORKSPEC_ID")));
    }

    public void b(List list) {
        if (list.isEmpty()) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            k.c().a(k, String.format("Constraints unmet for WorkSpec %s", str), new Throwable[0]);
            this.b.v(str);
        }
    }

    @Override // androidx.work.impl.b
    public void d(String str, boolean z) {
        Map.Entry entry;
        synchronized (this.d) {
            WorkSpec workSpec = (WorkSpec) this.g.remove(str);
            if (workSpec != null ? this.h.remove(workSpec) : false) {
                this.i.d(this.h);
            }
        }
        e eVar = (e) this.f.remove(str);
        if (str.equals(this.e) && this.f.size() > 0) {
            Iterator it = this.f.entrySet().iterator();
            Object next = it.next();
            while (true) {
                entry = (Map.Entry) next;
                if (!it.hasNext()) {
                    break;
                } else {
                    next = it.next();
                }
            }
            this.e = (String) entry.getKey();
            if (this.j != null) {
                e eVar2 = (e) entry.getValue();
                this.j.c(eVar2.c(), eVar2.a(), eVar2.b());
                this.j.d(eVar2.c());
            }
        }
        b bVar = this.j;
        if (eVar == null || bVar == null) {
            return;
        }
        k.c().a(k, String.format("Removing Notification (id: %s, workSpecId: %s ,notificationType: %s)", Integer.valueOf(eVar.c()), str, Integer.valueOf(eVar.a())), new Throwable[0]);
        bVar.d(eVar.c());
    }

    public void e(List list) {
    }

    void j(Intent intent) {
        k.c().d(k, "Stopping foreground service", new Throwable[0]);
        b bVar = this.j;
        if (bVar != null) {
            bVar.stop();
        }
    }

    void k() {
        this.j = null;
        synchronized (this.d) {
            this.i.e();
        }
        this.b.l().i(this);
    }

    void l(Intent intent) {
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            i(intent);
            h(intent);
        } else if ("ACTION_NOTIFY".equals(action)) {
            h(intent);
        } else if ("ACTION_CANCEL_WORK".equals(action)) {
            g(intent);
        } else if ("ACTION_STOP_FOREGROUND".equals(action)) {
            j(intent);
        }
    }

    void m(b bVar) {
        if (this.j != null) {
            k.c().b(k, "A callback already exists.", new Throwable[0]);
        } else {
            this.j = bVar;
        }
    }
}
