package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationClient {
    private final Context appContext;
    private final IMultiInstanceInvalidationCallback callback;
    private int clientId;
    private final Executor executor;
    private final InvalidationTracker invalidationTracker;
    private final String name;
    public InvalidationTracker.Observer observer;
    private final Runnable removeObserverRunnable;
    private IMultiInstanceInvalidationService service;
    private final ServiceConnection serviceConnection;
    private final Runnable setUpRunnable;
    private final AtomicBoolean stopped;

    public MultiInstanceInvalidationClient(Context context, String str, Intent intent, InvalidationTracker invalidationTracker, Executor executor) {
        kotlin.jvm.internal.m.g(context, "context");
        kotlin.jvm.internal.m.g(str, "name");
        kotlin.jvm.internal.m.g(intent, "serviceIntent");
        kotlin.jvm.internal.m.g(invalidationTracker, "invalidationTracker");
        kotlin.jvm.internal.m.g(executor, "executor");
        this.name = str;
        this.invalidationTracker = invalidationTracker;
        this.executor = executor;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        this.callback = new MultiInstanceInvalidationClient$callback$1(this);
        this.stopped = new AtomicBoolean(false);
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: androidx.room.MultiInstanceInvalidationClient$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                kotlin.jvm.internal.m.g(componentName, "name");
                kotlin.jvm.internal.m.g(iBinder, "service");
                this.this$0.setService(IMultiInstanceInvalidationService.Stub.asInterface(iBinder));
                this.this$0.getExecutor().execute(this.this$0.getSetUpRunnable());
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                kotlin.jvm.internal.m.g(componentName, "name");
                this.this$0.getExecutor().execute(this.this$0.getRemoveObserverRunnable());
                this.this$0.setService(null);
            }
        };
        this.serviceConnection = serviceConnection;
        this.setUpRunnable = new Runnable() { // from class: androidx.room.d
            @Override // java.lang.Runnable
            public final void run() {
                MultiInstanceInvalidationClient.setUpRunnable$lambda$1(this.a);
            }
        };
        this.removeObserverRunnable = new Runnable() { // from class: androidx.room.e
            @Override // java.lang.Runnable
            public final void run() {
                MultiInstanceInvalidationClient.removeObserverRunnable$lambda$2(this.a);
            }
        };
        Object[] array = invalidationTracker.getTableIdLookup$room_runtime_release().keySet().toArray(new String[0]);
        kotlin.jvm.internal.m.e(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        setObserver(new InvalidationTracker.Observer((String[]) array) { // from class: androidx.room.MultiInstanceInvalidationClient.1
            @Override // androidx.room.InvalidationTracker.Observer
            public boolean isRemote$room_runtime_release() {
                return true;
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(Set<String> set) {
                kotlin.jvm.internal.m.g(set, "tables");
                if (MultiInstanceInvalidationClient.this.getStopped().get()) {
                    return;
                }
                try {
                    IMultiInstanceInvalidationService service = MultiInstanceInvalidationClient.this.getService();
                    if (service != null) {
                        int clientId = MultiInstanceInvalidationClient.this.getClientId();
                        Object[] array2 = set.toArray(new String[0]);
                        kotlin.jvm.internal.m.e(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                        service.broadcastInvalidation(clientId, (String[]) array2);
                    }
                } catch (RemoteException unused) {
                }
            }
        });
        applicationContext.bindService(intent, serviceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeObserverRunnable$lambda$2(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        kotlin.jvm.internal.m.g(multiInstanceInvalidationClient, "this$0");
        multiInstanceInvalidationClient.invalidationTracker.removeObserver(multiInstanceInvalidationClient.getObserver());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUpRunnable$lambda$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        kotlin.jvm.internal.m.g(multiInstanceInvalidationClient, "this$0");
        try {
            IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient.service;
            if (iMultiInstanceInvalidationService != null) {
                multiInstanceInvalidationClient.clientId = iMultiInstanceInvalidationService.registerCallback(multiInstanceInvalidationClient.callback, multiInstanceInvalidationClient.name);
                multiInstanceInvalidationClient.invalidationTracker.addObserver(multiInstanceInvalidationClient.getObserver());
            }
        } catch (RemoteException unused) {
        }
    }

    public final IMultiInstanceInvalidationCallback getCallback() {
        return this.callback;
    }

    public final int getClientId() {
        return this.clientId;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public final String getName() {
        return this.name;
    }

    public final InvalidationTracker.Observer getObserver() {
        InvalidationTracker.Observer observer = this.observer;
        if (observer != null) {
            return observer;
        }
        kotlin.jvm.internal.m.x("observer");
        return null;
    }

    public final Runnable getRemoveObserverRunnable() {
        return this.removeObserverRunnable;
    }

    public final IMultiInstanceInvalidationService getService() {
        return this.service;
    }

    public final ServiceConnection getServiceConnection() {
        return this.serviceConnection;
    }

    public final Runnable getSetUpRunnable() {
        return this.setUpRunnable;
    }

    public final AtomicBoolean getStopped() {
        return this.stopped;
    }

    public final void setClientId(int i) {
        this.clientId = i;
    }

    public final void setObserver(InvalidationTracker.Observer observer) {
        kotlin.jvm.internal.m.g(observer, "<set-?>");
        this.observer = observer;
    }

    public final void setService(IMultiInstanceInvalidationService iMultiInstanceInvalidationService) {
        this.service = iMultiInstanceInvalidationService;
    }

    public final void stop() {
        if (this.stopped.compareAndSet(false, true)) {
            this.invalidationTracker.removeObserver(getObserver());
            try {
                IMultiInstanceInvalidationService iMultiInstanceInvalidationService = this.service;
                if (iMultiInstanceInvalidationService != null) {
                    iMultiInstanceInvalidationService.unregisterCallback(this.callback, this.clientId);
                }
            } catch (RemoteException unused) {
            }
            this.appContext.unbindService(this.serviceConnection);
        }
    }
}
