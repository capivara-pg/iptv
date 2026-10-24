package androidx.core.graphics.drawable;

import android.content.Context;
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
import v.c;
import v.d;
import v.e;
import v.f;
import v.g;
import v.h;
import v.i;
import v.j;
import v.k;
import v.l;
import v.m;
import v.n;
import v.o;

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

    static Bitmap a(Bitmap bitmap, boolean z) {
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

    public static IconCompat b(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.b = bitmap;
        return iconCompat;
    }

    public static IconCompat c(Resources resources, String str, int i) {
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

    private static int f(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return d.a(icon);
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

    private static String h(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return e.a(icon);
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

    private static int j(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return o.a(icon);
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

    private static Uri l(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return f.a(icon);
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

    private static String r(int i) {
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

    public Bitmap d() {
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
            return a((Bitmap) this.b, true);
        }
        throw new IllegalStateException("called getBitmap() on " + this);
    }

    public int e() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return f(c.a(this.b));
        }
        if (i == 2) {
            return this.e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String g() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return h(c.a(this.b));
        }
        if (i == 2) {
            return TextUtils.isEmpty(this.j) ? ((String) this.b).split(":", -1)[0] : this.j;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int i() {
        int i = this.a;
        return (i != -1 || Build.VERSION.SDK_INT < 23) ? i : j(c.a(this.b));
    }

    public Uri k() {
        int i = this.a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return l(c.a(this.b));
        }
        if (i == 4 || i == 6) {
            return Uri.parse((String) this.b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public InputStream m(Context context) {
        Uri uriK = k();
        String scheme = uriK.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(uriK);
            } catch (Exception unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to load image from URI: ");
                sb.append(uriK);
                return null;
            }
        }
        try {
            return new FileInputStream(new File((String) this.b));
        } catch (FileNotFoundException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to load image from path: ");
            sb2.append(uriK);
            return null;
        }
    }

    public void n() {
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

    public void o(boolean z) {
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

    public Icon p() {
        return q(null);
    }

    public Icon q(Context context) {
        Icon iconA;
        switch (this.a) {
            case -1:
                return c.a(this.b);
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                iconA = i.a((Bitmap) this.b);
                break;
            case 2:
                iconA = k.a(g(), this.e);
                break;
            case 3:
                iconA = l.a((byte[]) this.b, this.e, this.f);
                break;
            case 4:
                iconA = m.a((String) this.b);
                break;
            case 5:
                iconA = Build.VERSION.SDK_INT < 26 ? i.a(a((Bitmap) this.b, false)) : j.a((Bitmap) this.b);
                break;
            case 6:
                int i = Build.VERSION.SDK_INT;
                if (i >= 30) {
                    iconA = n.a(k());
                } else {
                    if (context == null) {
                        throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + k());
                    }
                    InputStream inputStreamM = m(context);
                    if (inputStreamM == null) {
                        throw new IllegalStateException("Cannot load adaptive icon from uri: " + k());
                    }
                    if (i < 26) {
                        iconA = i.a(a(BitmapFactory.decodeStream(inputStreamM), false));
                    } else {
                        iconA = j.a(BitmapFactory.decodeStream(inputStreamM));
                    }
                }
                break;
        }
        ColorStateList colorStateList = this.g;
        if (colorStateList != null) {
            g.a(iconA, colorStateList);
        }
        PorterDuff.Mode mode = this.h;
        if (mode != k) {
            h.a(iconA, mode);
        }
        return iconA;
    }

    public String toString() {
        if (this.a == -1) {
            return String.valueOf(this.b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(r(this.a));
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
                sb.append(String.format("0x%08x", Integer.valueOf(e())));
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
