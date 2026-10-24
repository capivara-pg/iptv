package androidx.core.content;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    private static final String TAG = "ContextCompat";
    private static final Object sLock = new Object();
    private static final Object sSync = new Object();
    private static TypedValue sTempValue;

    /* JADX INFO: renamed from: androidx.core.content.a$a, reason: collision with other inner class name */
    static class C0020a {
        static void a(Context obj, Intent[] intents, Bundle options) {
            obj.startActivities(intents, options);
        }

        static void b(Context obj, Intent intent, Bundle options) {
            obj.startActivity(intent, options);
        }
    }

    static class b {
        static File[] a(Context obj) {
            return obj.getExternalCacheDirs();
        }

        static File[] b(Context obj, String type) {
            return obj.getExternalFilesDirs(type);
        }

        static File[] c(Context obj) {
            return obj.getObbDirs();
        }
    }

    static class c {
        static File a(Context obj) {
            return obj.getCodeCacheDir();
        }

        static Drawable b(Context obj, int id) {
            return obj.getDrawable(id);
        }

        static File c(Context obj) {
            return obj.getNoBackupFilesDir();
        }
    }

    static class d {
        static int a(Context obj, int id) {
            return obj.getColor(id);
        }

        static ColorStateList b(Context obj, int id) {
            return obj.getColorStateList(id);
        }

        static <T> T c(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        static String d(Context obj, Class<?> serviceClass) {
            return obj.getSystemServiceName(serviceClass);
        }
    }

    static class e {
        static Context a(Context obj) {
            return obj.createDeviceProtectedStorageContext();
        }

        static File b(Context obj) {
            return obj.getDataDir();
        }

        static boolean c(Context obj) {
            return obj.isDeviceProtectedStorage();
        }
    }

    static class f {
        static ComponentName a(Context obj, Intent service) {
            return obj.startForegroundService(service);
        }
    }

    static class g {
        static Executor a(Context obj) {
            return obj.getMainExecutor();
        }
    }

    static class h {
        static String a(Context obj) {
            return obj.getAttributionTag();
        }
    }

    private static final class i {
        static final HashMap a;

        static {
            HashMap map = new HashMap();
            a = map;
            if (Build.VERSION.SDK_INT >= 22) {
                map.put(androidx.core.content.b.a(), "telephony_subscription_service");
                map.put(UsageStatsManager.class, "usagestats");
            }
            map.put(AppWidgetManager.class, "appwidget");
            map.put(BatteryManager.class, "batterymanager");
            map.put(CameraManager.class, "camera");
            map.put(JobScheduler.class, "jobscheduler");
            map.put(LauncherApps.class, "launcherapps");
            map.put(MediaProjectionManager.class, "media_projection");
            map.put(MediaSessionManager.class, "media_session");
            map.put(RestrictionsManager.class, "restrictions");
            map.put(TelecomManager.class, "telecom");
            map.put(TvInputManager.class, "tv_input");
            map.put(AppOpsManager.class, "appops");
            map.put(CaptioningManager.class, "captioning");
            map.put(ConsumerIrManager.class, "consumer_ir");
            map.put(PrintManager.class, "print");
            map.put(BluetoothManager.class, "bluetooth");
            map.put(DisplayManager.class, "display");
            map.put(UserManager.class, "user");
            map.put(InputManager.class, "input");
            map.put(MediaRouter.class, "media_router");
            map.put(NsdManager.class, "servicediscovery");
            map.put(AccessibilityManager.class, "accessibility");
            map.put(AccountManager.class, "account");
            map.put(ActivityManager.class, "activity");
            map.put(AlarmManager.class, "alarm");
            map.put(AudioManager.class, "audio");
            map.put(ClipboardManager.class, "clipboard");
            map.put(ConnectivityManager.class, "connectivity");
            map.put(DevicePolicyManager.class, "device_policy");
            map.put(DownloadManager.class, "download");
            map.put(DropBoxManager.class, "dropbox");
            map.put(InputMethodManager.class, "input_method");
            map.put(KeyguardManager.class, "keyguard");
            map.put(LayoutInflater.class, "layout_inflater");
            map.put(LocationManager.class, "location");
            map.put(NfcManager.class, "nfc");
            map.put(NotificationManager.class, "notification");
            map.put(PowerManager.class, "power");
            map.put(SearchManager.class, "search");
            map.put(SensorManager.class, "sensor");
            map.put(StorageManager.class, "storage");
            map.put(TelephonyManager.class, "phone");
            map.put(TextServicesManager.class, "textservices");
            map.put(UiModeManager.class, "uimode");
            map.put(UsbManager.class, "usb");
            map.put(Vibrator.class, "vibrator");
            map.put(WallpaperManager.class, "wallpaper");
            map.put(WifiP2pManager.class, "wifip2p");
            map.put(WifiManager.class, "wifi");
            map.put(WindowManager.class, "window");
        }
    }

    public static int checkSelfPermission(Context context, String permission) {
        if (permission != null) {
            return context.checkPermission(permission, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static Context createDeviceProtectedStorageContext(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return e.a(context);
        }
        return null;
    }

    public static String getAttributionTag(Context context) {
        if (Build.VERSION.SDK_INT >= 30) {
            return h.a(context);
        }
        return null;
    }

    public static File getCodeCacheDir(Context context) {
        return c.a(context);
    }

    public static int getColor(Context context, int id) {
        return Build.VERSION.SDK_INT >= 23 ? d.a(context, id) : context.getResources().getColor(id);
    }

    public static ColorStateList getColorStateList(Context context, int id) {
        return t.g.c(context.getResources(), id, context.getTheme());
    }

    public static File getDataDir(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return e.b(context);
        }
        String str = context.getApplicationInfo().dataDir;
        if (str != null) {
            return new File(str);
        }
        return null;
    }

    public static Drawable getDrawable(Context context, int id) {
        return c.b(context, id);
    }

    public static File[] getExternalCacheDirs(Context context) {
        return b.a(context);
    }

    public static File[] getExternalFilesDirs(Context context, String type) {
        return b.b(context, type);
    }

    public static Executor getMainExecutor(Context context) {
        return Build.VERSION.SDK_INT >= 28 ? g.a(context) : z.e.a(new Handler(context.getMainLooper()));
    }

    public static File getNoBackupFilesDir(Context context) {
        return c.c(context);
    }

    public static File[] getObbDirs(Context context) {
        return b.c(context);
    }

    public static <T> T getSystemService(Context context, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 23) {
            return (T) d.c(context, cls);
        }
        String systemServiceName = getSystemServiceName(context, cls);
        if (systemServiceName != null) {
            return (T) context.getSystemService(systemServiceName);
        }
        return null;
    }

    public static String getSystemServiceName(Context context, Class<?> serviceClass) {
        return Build.VERSION.SDK_INT >= 23 ? d.d(context, serviceClass) : (String) i.a.get(serviceClass);
    }

    public static boolean isDeviceProtectedStorage(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return e.c(context);
        }
        return false;
    }

    public static boolean startActivities(Context context, Intent[] intents) {
        return startActivities(context, intents, null);
    }

    public static void startActivity(Context context, Intent intent, Bundle options) {
        C0020a.b(context, intent, options);
    }

    public static void startForegroundService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            f.a(context, intent);
        } else {
            context.startService(intent);
        }
    }

    public static boolean startActivities(Context context, Intent[] intents, Bundle options) {
        C0020a.a(context, intents, options);
        return true;
    }
}
