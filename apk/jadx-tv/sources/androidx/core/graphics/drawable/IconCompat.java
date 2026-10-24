package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import o.h;
import q.i;
import q.j;
import q.k;
import q.l;
import q.m;
import q.n;
import q.o;
import q.p;
import q.q;
import q.r;
import q.s;
import q.t;
import q.u;
import q.v;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;
    public int a;
    Object b;
    public byte[] c;
    public Parcelable d;
    public int e;
    public int f;
    public ColorStateList g;
    PorterDuff.Mode h;
    public String i;
    public String j;

    public IconCompat() {
        this.a = -1;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = k;
        this.i = null;
    }

    static Bitmap b(Bitmap bitmap, boolean z) {
        int iMin = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(3);
        float f = iMin;
        float f2 = 0.5f * f;
        float f3 = 0.9166667f * f2;
        if (z) {
            float f4 = 0.010416667f * f;
            paint.setColor(0);
            paint.setShadowLayer(f4, 0.0f, f * 0.020833334f, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - iMin)) / 2, (-(bitmap.getHeight() - iMin)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    public static IconCompat c(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.b = bitmap;
        return iconCompat;
    }

    public static IconCompat d(Context context, int i) {
        if (context != null) {
            return e(context.getResources(), context.getPackageName(), i);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    public static IconCompat e(Resources resources, String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if (i == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.e = i;
        if (resources != null) {
            try {
                iconCompat.b = resources.getResourceName(i);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.b = str;
        }
        iconCompat.j = str;
        return iconCompat;
    }

    private static int h(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return o.a(icon);
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        }
    }

    private static String j(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return l.a(icon);
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        }
    }

    private static Resources k(Context context, String str) {
        if ("android".equals(str)) {
            return Resources.getSystem();
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return packageManager.getResourcesForApplication(applicationInfo);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", str), e);
            return null;
        }
    }

    private static int m(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return n.a(icon);
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e);
            return -1;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e2);
            return -1;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e3);
            return -1;
        }
    }

    private static Uri o(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return m.a(icon);
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon uri", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon uri", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon uri", e3);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Drawable r(Context context) {
        switch (this.a) {
            case 1:
                return new BitmapDrawable(context.getResources(), (Bitmap) this.b);
            case 2:
                String strI = i();
                if (TextUtils.isEmpty(strI)) {
                    strI = context.getPackageName();
                }
                try {
                    return h.d(k(context, strI), this.e, context.getTheme());
                } catch (RuntimeException e) {
                    Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", Integer.valueOf(this.e), this.b), e);
                }
                break;
            case 3:
                return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray((byte[]) this.b, this.e, this.f));
            case 4:
                InputStream inputStreamP = p(context);
                if (inputStreamP != null) {
                    return new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(inputStreamP));
                }
                return null;
            case 5:
                return new BitmapDrawable(context.getResources(), b((Bitmap) this.b, false));
            case 6:
                InputStream inputStreamP2 = p(context);
                if (inputStreamP2 != null) {
                    return Build.VERSION.SDK_INT >= 26 ? new AdaptiveIconDrawable(null, new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(inputStreamP2))) : new BitmapDrawable(context.getResources(), b(BitmapFactory.decodeStream(inputStreamP2), false));
                }
                return null;
            default:
                return null;
        }
    }

    private static String w(int i) {
        switch (i) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case 6:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public void a(Context context) {
        Object obj;
        if (this.a != 2 || (obj = this.b) == null) {
            return;
        }
        String str = (String) obj;
        if (str.contains(":")) {
            String str2 = str.split(":", -1)[1];
            String str3 = str2.split("/", -1)[0];
            String str4 = str2.split("/", -1)[1];
            String str5 = str.split(":", -1)[0];
            if ("0_resource_name_obfuscated".equals(str4)) {
                return;
            }
            String strI = i();
            int identifier = k(context, strI).getIdentifier(str4, str3, str5);
            if (this.e != identifier) {
                StringBuilder sb = new StringBuilder();
                sb.append("Id has changed for ");
                sb.append(strI);
                sb.append(" ");
                sb.append(str);
                this.e = identifier;
            }
        }
    }

    public Bitmap f() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            Object obj = this.b;
            if (obj instanceof Bitmap) {
                return (Bitmap) obj;
            }
            return null;
        }
        if (i == 1) {
            return (Bitmap) this.b;
        }
        if (i == 5) {
            return b((Bitmap) this.b, true);
        }
        throw new IllegalStateException("called getBitmap() on " + this);
    }

    public int g() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return h(i.a(this.b));
        }
        if (i == 2) {
            return this.e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String i() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return j(i.a(this.b));
        }
        if (i == 2) {
            return TextUtils.isEmpty(this.j) ? ((String) this.b).split(":", -1)[0] : this.j;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int l() {
        int i = this.a;
        return (i != -1 || Build.VERSION.SDK_INT < 23) ? i : m(i.a(this.b));
    }

    public Uri n() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return o(i.a(this.b));
        }
        if (i == 4 || i == 6) {
            return Uri.parse((String) this.b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public InputStream p(Context context) {
        Uri uriN = n();
        String scheme = uriN.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(uriN);
            } catch (Exception unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to load image from URI: ");
                sb.append(uriN);
                return null;
            }
        }
        try {
            return new FileInputStream(new File((String) this.b));
        } catch (FileNotFoundException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to load image from path: ");
            sb2.append(uriN);
            return null;
        }
    }

    public Drawable q(Context context) {
        a(context);
        if (Build.VERSION.SDK_INT >= 23) {
            return k.a(v(context), context);
        }
        Drawable drawableR = r(context);
        if (drawableR != null && (this.g != null || this.h != k)) {
            drawableR.mutate();
            q.h.o(drawableR, this.g);
            q.h.p(drawableR, this.h);
        }
        return drawableR;
    }

    public void s() {
        this.h = PorterDuff.Mode.valueOf(this.i);
        switch (this.a) {
            case -1:
                Parcelable parcelable = this.d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.b = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.d;
                if (parcelable2 != null) {
                    this.b = parcelable2;
                    return;
                }
                byte[] bArr = this.c;
                this.b = bArr;
                this.a = 3;
                this.e = 0;
                this.f = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                String str = new String(this.c, Charset.forName("UTF-16"));
                this.b = str;
                if (this.a == 2 && this.j == null) {
                    this.j = str.split(":", -1)[0];
                    return;
                }
                return;
            case 3:
                this.b = this.c;
                return;
        }
    }

    public void t(boolean z) {
        this.i = this.h.name();
        switch (this.a) {
            case -1:
                if (z) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.d = (Parcelable) this.b;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z) {
                    this.d = (Parcelable) this.b;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.b;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.c = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.c = ((String) this.b).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.c = (byte[]) this.b;
                return;
            case 4:
            case 6:
                this.c = this.b.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    public String toString() {
        if (this.a == -1) {
            return String.valueOf(this.b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(w(this.a));
        switch (this.a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(g())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.e);
                if (this.f != 0) {
                    sb.append(" off=");
                    sb.append(this.f);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.b);
                break;
        }
        if (this.g != null) {
            sb.append(" tint=");
            sb.append(this.g);
        }
        if (this.h != k) {
            sb.append(" mode=");
            sb.append(this.h);
        }
        sb.append(")");
        return sb.toString();
    }

    public Icon u() {
        return v(null);
    }

    public Icon v(Context context) {
        Icon iconA;
        switch (this.a) {
            case -1:
                return i.a(this.b);
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                iconA = r.a((Bitmap) this.b);
                break;
            case 2:
                iconA = t.a(i(), this.e);
                break;
            case 3:
                iconA = u.a((byte[]) this.b, this.e, this.f);
                break;
            case 4:
                iconA = v.a((String) this.b);
                break;
            case 5:
                iconA = Build.VERSION.SDK_INT < 26 ? r.a(b((Bitmap) this.b, false)) : s.a((Bitmap) this.b);
                break;
            case 6:
                int i = Build.VERSION.SDK_INT;
                if (i >= 30) {
                    iconA = j.a(n());
                } else {
                    if (context == null) {
                        throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + n());
                    }
                    InputStream inputStreamP = p(context);
                    if (inputStreamP == null) {
                        throw new IllegalStateException("Cannot load adaptive icon from uri: " + n());
                    }
                    if (i < 26) {
                        iconA = r.a(b(BitmapFactory.decodeStream(inputStreamP), false));
                    } else {
                        iconA = s.a(BitmapFactory.decodeStream(inputStreamP));
                    }
                }
                break;
        }
        ColorStateList colorStateList = this.g;
        if (colorStateList != null) {
            p.a(iconA, colorStateList);
        }
        PorterDuff.Mode mode = this.h;
        if (mode != k) {
            q.a(iconA, mode);
        }
        return iconA;
    }

    private IconCompat(int i) {
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = k;
        this.i = null;
        this.a = i;
    }
}
