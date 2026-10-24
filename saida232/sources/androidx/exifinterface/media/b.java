package androidx.exifinterface.media;

import android.content.res.AssetManager;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.system.OsConstants;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private static SimpleDateFormat T;
    private static SimpleDateFormat U;
    private static final d[] Y;
    private static final d[] Z;
    private static final d[] a0;
    private static final d[] b0;
    private static final d[] c0;
    private static final d d0;
    private static final d[] e0;
    private static final d[] f0;
    private static final d[] g0;
    private static final d[] h0;
    static final d[][] i0;
    private static final d[] j0;
    private static final HashMap[] k0;
    private static final HashMap[] l0;
    private static final HashSet m0;
    private static final HashMap n0;
    static final Charset o0;
    static final byte[] p0;
    private static final byte[] q0;
    private static final Pattern r0;
    private static final Pattern s0;
    private static final Pattern t0;
    private static final Pattern u0;
    private String a;
    private FileDescriptor b;
    private AssetManager.AssetInputStream c;
    private int d;
    private boolean e;
    private final HashMap[] f;
    private Set g;
    private ByteOrder h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int l;
    private int m;
    private byte[] n;
    private int o;
    private int p;
    private int q;
    private int r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f24s;
    private boolean t;
    private static final boolean u = Log.isLoggable("ExifInterface", 3);
    private static final List v = Arrays.asList(1, 6, 3, 8);
    private static final List w = Arrays.asList(2, 7, 4, 5);
    public static final int[] x = {8, 8, 8};
    public static final int[] y = {4};
    public static final int[] z = {8};
    static final byte[] A = {-1, -40, -1};
    private static final byte[] B = {102, 116, 121, 112};
    private static final byte[] C = {109, 105, 102, 49};
    private static final byte[] D = {104, 101, 105, 99};
    private static final byte[] E = {79, 76, 89, 77, 80, 0};
    private static final byte[] F = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    private static final byte[] G = {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final byte[] H = {101, 88, 73, 102};
    private static final byte[] I = {73, 72, 68, 82};
    private static final byte[] J = {73, 69, 78, 68};
    private static final byte[] K = {82, 73, 70, 70};
    private static final byte[] L = {87, 69, 66, 80};
    private static final byte[] M = {69, 88, 73, 70};
    private static final byte[] N = {-99, 1, 42};
    private static final byte[] O = "VP8X".getBytes(Charset.defaultCharset());
    private static final byte[] P = "VP8L".getBytes(Charset.defaultCharset());
    private static final byte[] Q = "VP8 ".getBytes(Charset.defaultCharset());
    private static final byte[] R = "ANIM".getBytes(Charset.defaultCharset());
    private static final byte[] S = "ANMF".getBytes(Charset.defaultCharset());
    static final String[] V = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    static final int[] W = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    static final byte[] X = {65, 83, 67, 73, 73, 0, 0, 0};

    class a extends MediaDataSource {
        long a;
        final /* synthetic */ f b;

        a(f fVar) {
            this.b = fVar;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // android.media.MediaDataSource
        public long getSize() {
            return -1L;
        }

        @Override // android.media.MediaDataSource
        public int readAt(long j, byte[] bArr, int i, int i2) {
            if (i2 == 0) {
                return 0;
            }
            if (j < 0) {
                return -1;
            }
            try {
                long j2 = this.a;
                if (j2 != j) {
                    if (j2 >= 0 && j >= j2 + ((long) this.b.available())) {
                        return -1;
                    }
                    this.b.g(j);
                    this.a = j;
                }
                if (i2 > this.b.available()) {
                    i2 = this.b.available();
                }
                int i3 = this.b.read(bArr, i, i2);
                if (i3 >= 0) {
                    this.a += (long) i3;
                    return i3;
                }
            } catch (IOException unused) {
            }
            this.a = -1L;
            return -1;
        }
    }

    /* JADX INFO: renamed from: androidx.exifinterface.media.b$b, reason: collision with other inner class name */
    private static class C0028b extends InputStream implements DataInput {
        private static final ByteOrder e = ByteOrder.LITTLE_ENDIAN;
        private static final ByteOrder f = ByteOrder.BIG_ENDIAN;
        final DataInputStream a;
        private ByteOrder b;
        int c;
        private byte[] d;

        C0028b(byte[] bArr) {
            this(new ByteArrayInputStream(bArr), ByteOrder.BIG_ENDIAN);
        }

        public int a() {
            return this.c;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.a.available();
        }

        public long b() {
            return ((long) readInt()) & 4294967295L;
        }

        public void c(ByteOrder byteOrder) {
            this.b = byteOrder;
        }

        public void f(int i) throws IOException {
            int i2 = 0;
            while (i2 < i) {
                int i3 = i - i2;
                int iSkip = (int) this.a.skip(i3);
                if (iSkip <= 0) {
                    if (this.d == null) {
                        this.d = new byte[8192];
                    }
                    iSkip = this.a.read(this.d, 0, Math.min(8192, i3));
                    if (iSkip == -1) {
                        throw new EOFException("Reached EOF while skipping " + i + " bytes.");
                    }
                }
                i2 += iSkip;
            }
            this.c += i2;
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            throw new UnsupportedOperationException("Mark is currently unsupported");
        }

        @Override // java.io.InputStream
        public int read() {
            this.c++;
            return this.a.read();
        }

        @Override // java.io.DataInput
        public boolean readBoolean() {
            this.c++;
            return this.a.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            this.c++;
            int i = this.a.read();
            if (i >= 0) {
                return (byte) i;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public char readChar() {
            this.c += 2;
            return this.a.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i, int i2) throws IOException {
            this.c += i2;
            this.a.readFully(bArr, i, i2);
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            this.c += 4;
            int i = this.a.read();
            int i2 = this.a.read();
            int i3 = this.a.read();
            int i4 = this.a.read();
            if ((i | i2 | i3 | i4) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.b;
            if (byteOrder == e) {
                return (i4 << 24) + (i3 << 16) + (i2 << 8) + i;
            }
            if (byteOrder == f) {
                return (i << 24) + (i2 << 16) + (i3 << 8) + i4;
            }
            throw new IOException("Invalid byte order: " + this.b);
        }

        @Override // java.io.DataInput
        public String readLine() {
            return null;
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            this.c += 8;
            int i = this.a.read();
            int i2 = this.a.read();
            int i3 = this.a.read();
            int i4 = this.a.read();
            int i5 = this.a.read();
            int i6 = this.a.read();
            int i7 = this.a.read();
            int i8 = this.a.read();
            if ((i | i2 | i3 | i4 | i5 | i6 | i7 | i8) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.b;
            if (byteOrder == e) {
                return (((long) i8) << 56) + (((long) i7) << 48) + (((long) i6) << 40) + (((long) i5) << 32) + (((long) i4) << 24) + (((long) i3) << 16) + (((long) i2) << 8) + ((long) i);
            }
            if (byteOrder == f) {
                return (((long) i) << 56) + (((long) i2) << 48) + (((long) i3) << 40) + (((long) i4) << 32) + (((long) i5) << 24) + (((long) i6) << 16) + (((long) i7) << 8) + ((long) i8);
            }
            throw new IOException("Invalid byte order: " + this.b);
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            this.c += 2;
            int i = this.a.read();
            int i2 = this.a.read();
            if ((i | i2) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.b;
            if (byteOrder == e) {
                return (short) ((i2 << 8) + i);
            }
            if (byteOrder == f) {
                return (short) ((i << 8) + i2);
            }
            throw new IOException("Invalid byte order: " + this.b);
        }

        @Override // java.io.DataInput
        public String readUTF() {
            this.c += 2;
            return this.a.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() {
            this.c++;
            return this.a.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            this.c += 2;
            int i = this.a.read();
            int i2 = this.a.read();
            if ((i | i2) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.b;
            if (byteOrder == e) {
                return (i2 << 8) + i;
            }
            if (byteOrder == f) {
                return (i << 8) + i2;
            }
            throw new IOException("Invalid byte order: " + this.b);
        }

        @Override // java.io.InputStream
        public void reset() {
            throw new UnsupportedOperationException("Reset is currently unsupported");
        }

        @Override // java.io.DataInput
        public int skipBytes(int i) {
            throw new UnsupportedOperationException("skipBytes is currently unsupported");
        }

        C0028b(InputStream inputStream) {
            this(inputStream, ByteOrder.BIG_ENDIAN);
        }

        C0028b(InputStream inputStream, ByteOrder byteOrder) {
            this.b = ByteOrder.BIG_ENDIAN;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.a = dataInputStream;
            dataInputStream.mark(0);
            this.c = 0;
            this.b = byteOrder;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.a.read(bArr, i, i2);
            this.c += i3;
            return i3;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            this.c += bArr.length;
            this.a.readFully(bArr);
        }
    }

    private static class c {
        public final int a;
        public final int b;
        public final long c;
        public final byte[] d;

        c(int i, int i2, byte[] bArr) {
            this(i, i2, -1L, bArr);
        }

        public static c a(String str) {
            byte[] bytes = (str + (char) 0).getBytes(b.o0);
            return new c(2, bytes.length, bytes);
        }

        public static c b(long j, ByteOrder byteOrder) {
            return c(new long[]{j}, byteOrder);
        }

        public static c c(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[b.W[4] * jArr.length]);
            byteBufferWrap.order(byteOrder);
            for (long j : jArr) {
                byteBufferWrap.putInt((int) j);
            }
            return new c(4, jArr.length, byteBufferWrap.array());
        }

        public static c d(e eVar, ByteOrder byteOrder) {
            return e(new e[]{eVar}, byteOrder);
        }

        public static c e(e[] eVarArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[b.W[5] * eVarArr.length]);
            byteBufferWrap.order(byteOrder);
            for (e eVar : eVarArr) {
                byteBufferWrap.putInt((int) eVar.a);
                byteBufferWrap.putInt((int) eVar.b);
            }
            return new c(5, eVarArr.length, byteBufferWrap.array());
        }

        public static c f(int i, ByteOrder byteOrder) {
            return g(new int[]{i}, byteOrder);
        }

        public static c g(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[b.W[3] * iArr.length]);
            byteBufferWrap.order(byteOrder);
            for (int i : iArr) {
                byteBufferWrap.putShort((short) i);
            }
            return new c(3, iArr.length, byteBufferWrap.array());
        }

        public double h(ByteOrder byteOrder) throws Throwable {
            Object objK = k(byteOrder);
            if (objK == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            }
            if (objK instanceof String) {
                return Double.parseDouble((String) objK);
            }
            if (objK instanceof long[]) {
                long[] jArr = (long[]) objK;
                if (jArr.length == 1) {
                    return jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (objK instanceof int[]) {
                int[] iArr = (int[]) objK;
                if (iArr.length == 1) {
                    return iArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (objK instanceof double[]) {
                double[] dArr = (double[]) objK;
                if (dArr.length == 1) {
                    return dArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(objK instanceof e[])) {
                throw new NumberFormatException("Couldn't find a double value");
            }
            e[] eVarArr = (e[]) objK;
            if (eVarArr.length == 1) {
                return eVarArr[0].a();
            }
            throw new NumberFormatException("There are more than one component");
        }

        public int i(ByteOrder byteOrder) throws Throwable {
            Object objK = k(byteOrder);
            if (objK == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            }
            if (objK instanceof String) {
                return Integer.parseInt((String) objK);
            }
            if (objK instanceof long[]) {
                long[] jArr = (long[]) objK;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(objK instanceof int[])) {
                throw new NumberFormatException("Couldn't find a integer value");
            }
            int[] iArr = (int[]) objK;
            if (iArr.length == 1) {
                return iArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }

        public String j(ByteOrder byteOrder) throws Throwable {
            Object objK = k(byteOrder);
            if (objK == null) {
                return null;
            }
            if (objK instanceof String) {
                return (String) objK;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (objK instanceof long[]) {
                long[] jArr = (long[]) objK;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    i++;
                    if (i != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (objK instanceof int[]) {
                int[] iArr = (int[]) objK;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    i++;
                    if (i != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (objK instanceof double[]) {
                double[] dArr = (double[]) objK;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    i++;
                    if (i != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (!(objK instanceof e[])) {
                return null;
            }
            e[] eVarArr = (e[]) objK;
            while (i < eVarArr.length) {
                sb.append(eVarArr[i].a);
                sb.append('/');
                sb.append(eVarArr[i].b);
                i++;
                if (i != eVarArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        Object k(ByteOrder byteOrder) throws Throwable {
            C0028b c0028b;
            byte b;
            byte b2;
            byte[] bArr;
            C0028b c0028b2 = null;
            try {
                c0028b = new C0028b(this.d);
                try {
                    c0028b.c(byteOrder);
                    int length = 0;
                    boolean z = true;
                    switch (this.a) {
                        case 1:
                        case 6:
                            byte[] bArr2 = this.d;
                            if (bArr2.length != 1 || (b = bArr2[0]) < 0 || b > 1) {
                                String str = new String(bArr2, b.o0);
                                try {
                                    c0028b.close();
                                    break;
                                } catch (IOException e) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e);
                                }
                                return str;
                            }
                            String str2 = new String(new char[]{(char) (b + 48)});
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e2) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e2);
                            }
                            return str2;
                        case 2:
                        case 7:
                            if (this.b >= b.X.length) {
                                int i = 0;
                                while (true) {
                                    bArr = b.X;
                                    if (i < bArr.length) {
                                        if (this.d[i] != bArr[i]) {
                                            z = false;
                                        } else {
                                            i++;
                                        }
                                    }
                                }
                                if (z) {
                                    length = bArr.length;
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            while (length < this.b && (b2 = this.d[length]) != 0) {
                                if (b2 >= 32) {
                                    sb.append((char) b2);
                                } else {
                                    sb.append('?');
                                }
                                length++;
                            }
                            String string = sb.toString();
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e3) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e3);
                            }
                            return string;
                        case 3:
                            int[] iArr = new int[this.b];
                            while (length < this.b) {
                                iArr[length] = c0028b.readUnsignedShort();
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e4) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e4);
                            }
                            return iArr;
                        case 4:
                            long[] jArr = new long[this.b];
                            while (length < this.b) {
                                jArr[length] = c0028b.b();
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e5) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e5);
                            }
                            return jArr;
                        case 5:
                            e[] eVarArr = new e[this.b];
                            while (length < this.b) {
                                eVarArr[length] = new e(c0028b.b(), c0028b.b());
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e6) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e6);
                            }
                            return eVarArr;
                        case 8:
                            int[] iArr2 = new int[this.b];
                            while (length < this.b) {
                                iArr2[length] = c0028b.readShort();
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e7) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e7);
                            }
                            return iArr2;
                        case 9:
                            int[] iArr3 = new int[this.b];
                            while (length < this.b) {
                                iArr3[length] = c0028b.readInt();
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e8) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e8);
                            }
                            return iArr3;
                        case 10:
                            e[] eVarArr2 = new e[this.b];
                            while (length < this.b) {
                                eVarArr2[length] = new e(c0028b.readInt(), c0028b.readInt());
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e9) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e9);
                            }
                            return eVarArr2;
                        case 11:
                            double[] dArr = new double[this.b];
                            while (length < this.b) {
                                dArr[length] = c0028b.readFloat();
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e10) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e10);
                            }
                            return dArr;
                        case 12:
                            double[] dArr2 = new double[this.b];
                            while (length < this.b) {
                                dArr2[length] = c0028b.readDouble();
                                length++;
                            }
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e11) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e11);
                            }
                            return dArr2;
                        default:
                            try {
                                c0028b.close();
                                break;
                            } catch (IOException e12) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e12);
                            }
                            return null;
                    }
                } catch (IOException unused) {
                    if (c0028b != null) {
                        try {
                            c0028b.close();
                        } catch (IOException e13) {
                            Log.e("ExifInterface", "IOException occurred while closing InputStream", e13);
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    c0028b2 = c0028b;
                    if (c0028b2 != null) {
                        try {
                            c0028b2.close();
                        } catch (IOException e14) {
                            Log.e("ExifInterface", "IOException occurred while closing InputStream", e14);
                        }
                    }
                    throw th;
                }
            } catch (IOException unused2) {
                c0028b = null;
            } catch (Throwable th2) {
                th = th2;
            }
        }

        public String toString() {
            return "(" + b.V[this.a] + ", data length:" + this.d.length + ")";
        }

        c(int i, int i2, long j, byte[] bArr) {
            this.a = i;
            this.b = i2;
            this.c = j;
            this.d = bArr;
        }
    }

    private static class e {
        public final long a;
        public final long b;

        e(long j, long j2) {
            if (j2 == 0) {
                this.a = 0L;
                this.b = 1L;
            } else {
                this.a = j;
                this.b = j2;
            }
        }

        public double a() {
            double d = this.a;
            double d2 = this.b;
            Double.isNaN(d);
            Double.isNaN(d2);
            return d / d2;
        }

        public String toString() {
            return this.a + "/" + this.b;
        }
    }

    static {
        d[] dVarArr = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d("ImageWidth", 256, 3, 4), new d("ImageLength", 257, 3, 4), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d("StripOffsets", 273, 3, 4), new d("Orientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d("RowsPerStrip", 278, 3, 4), new d("StripByteCounts", 279, 3, 4), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d("Copyright", 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("SensorTopBorder", 4, 4), new d("SensorLeftBorder", 5, 4), new d("SensorBottomBorder", 6, 4), new d("SensorRightBorder", 7, 4), new d("ISO", 23, 3), new d("JpgFromRaw", 46, 7), new d("Xmp", 700, 1)};
        Y = dVarArr;
        d[] dVarArr2 = {new d("ExposureTime", 33434, 5), new d("FNumber", 33437, 5), new d("ExposureProgram", 34850, 3), new d("SpectralSensitivity", 34852, 2), new d("PhotographicSensitivity", 34855, 3), new d("OECF", 34856, 7), new d("SensitivityType", 34864, 3), new d("StandardOutputSensitivity", 34865, 4), new d("RecommendedExposureIndex", 34866, 4), new d("ISOSpeed", 34867, 4), new d("ISOSpeedLatitudeyyy", 34868, 4), new d("ISOSpeedLatitudezzz", 34869, 4), new d("ExifVersion", 36864, 2), new d("DateTimeOriginal", 36867, 2), new d("DateTimeDigitized", 36868, 2), new d("OffsetTime", 36880, 2), new d("OffsetTimeOriginal", 36881, 2), new d("OffsetTimeDigitized", 36882, 2), new d("ComponentsConfiguration", 37121, 7), new d("CompressedBitsPerPixel", 37122, 5), new d("ShutterSpeedValue", 37377, 10), new d("ApertureValue", 37378, 5), new d("BrightnessValue", 37379, 10), new d("ExposureBiasValue", 37380, 10), new d("MaxApertureValue", 37381, 5), new d("SubjectDistance", 37382, 5), new d("MeteringMode", 37383, 3), new d("LightSource", 37384, 3), new d("Flash", 37385, 3), new d("FocalLength", 37386, 5), new d("SubjectArea", 37396, 3), new d("MakerNote", 37500, 7), new d("UserComment", 37510, 7), new d("SubSecTime", 37520, 2), new d("SubSecTimeOriginal", 37521, 2), new d("SubSecTimeDigitized", 37522, 2), new d("FlashpixVersion", 40960, 7), new d("ColorSpace", 40961, 3), new d("PixelXDimension", 40962, 3, 4), new d("PixelYDimension", 40963, 3, 4), new d("RelatedSoundFile", 40964, 2), new d("InteroperabilityIFDPointer", 40965, 4), new d("FlashEnergy", 41483, 5), new d("SpatialFrequencyResponse", 41484, 7), new d("FocalPlaneXResolution", 41486, 5), new d("FocalPlaneYResolution", 41487, 5), new d("FocalPlaneResolutionUnit", 41488, 3), new d("SubjectLocation", 41492, 3), new d("ExposureIndex", 41493, 5), new d("SensingMethod", 41495, 3), new d("FileSource", 41728, 7), new d("SceneType", 41729, 7), new d("CFAPattern", 41730, 7), new d("CustomRendered", 41985, 3), new d("ExposureMode", 41986, 3), new d("WhiteBalance", 41987, 3), new d("DigitalZoomRatio", 41988, 5), new d("FocalLengthIn35mmFilm", 41989, 3), new d("SceneCaptureType", 41990, 3), new d("GainControl", 41991, 3), new d("Contrast", 41992, 3), new d("Saturation", 41993, 3), new d("Sharpness", 41994, 3), new d("DeviceSettingDescription", 41995, 7), new d("SubjectDistanceRange", 41996, 3), new d("ImageUniqueID", 42016, 2), new d("CameraOwnerName", 42032, 2), new d("BodySerialNumber", 42033, 2), new d("LensSpecification", 42034, 5), new d("LensMake", 42035, 2), new d("LensModel", 42036, 2), new d("Gamma", 42240, 5), new d("DNGVersion", 50706, 1), new d("DefaultCropSize", 50720, 3, 4)};
        Z = dVarArr2;
        d[] dVarArr3 = {new d("GPSVersionID", 0, 1), new d("GPSLatitudeRef", 1, 2), new d("GPSLatitude", 2, 5, 10), new d("GPSLongitudeRef", 3, 2), new d("GPSLongitude", 4, 5, 10), new d("GPSAltitudeRef", 5, 1), new d("GPSAltitude", 6, 5), new d("GPSTimeStamp", 7, 5), new d("GPSSatellites", 8, 2), new d("GPSStatus", 9, 2), new d("GPSMeasureMode", 10, 2), new d("GPSDOP", 11, 5), new d("GPSSpeedRef", 12, 2), new d("GPSSpeed", 13, 5), new d("GPSTrackRef", 14, 2), new d("GPSTrack", 15, 5), new d("GPSImgDirectionRef", 16, 2), new d("GPSImgDirection", 17, 5), new d("GPSMapDatum", 18, 2), new d("GPSDestLatitudeRef", 19, 2), new d("GPSDestLatitude", 20, 5), new d("GPSDestLongitudeRef", 21, 2), new d("GPSDestLongitude", 22, 5), new d("GPSDestBearingRef", 23, 2), new d("GPSDestBearing", 24, 5), new d("GPSDestDistanceRef", 25, 2), new d("GPSDestDistance", 26, 5), new d("GPSProcessingMethod", 27, 7), new d("GPSAreaInformation", 28, 7), new d("GPSDateStamp", 29, 2), new d("GPSDifferential", 30, 3), new d("GPSHPositioningError", 31, 5)};
        a0 = dVarArr3;
        d[] dVarArr4 = {new d("InteroperabilityIndex", 1, 2)};
        b0 = dVarArr4;
        d[] dVarArr5 = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d("ThumbnailImageWidth", 256, 3, 4), new d("ThumbnailImageLength", 257, 3, 4), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d("StripOffsets", 273, 3, 4), new d("ThumbnailOrientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d("RowsPerStrip", 278, 3, 4), new d("StripByteCounts", 279, 3, 4), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d("Copyright", 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("DNGVersion", 50706, 1), new d("DefaultCropSize", 50720, 3, 4)};
        c0 = dVarArr5;
        d0 = new d("StripOffsets", 273, 3);
        d[] dVarArr6 = {new d("ThumbnailImage", 256, 7), new d("CameraSettingsIFDPointer", 8224, 4), new d("ImageProcessingIFDPointer", 8256, 4)};
        e0 = dVarArr6;
        d[] dVarArr7 = {new d("PreviewImageStart", 257, 4), new d("PreviewImageLength", 258, 4)};
        f0 = dVarArr7;
        d[] dVarArr8 = {new d("AspectFrame", 4371, 3)};
        g0 = dVarArr8;
        d[] dVarArr9 = {new d("ColorSpace", 55, 3)};
        h0 = dVarArr9;
        d[][] dVarArr10 = {dVarArr, dVarArr2, dVarArr3, dVarArr4, dVarArr5, dVarArr, dVarArr6, dVarArr7, dVarArr8, dVarArr9};
        i0 = dVarArr10;
        j0 = new d[]{new d("SubIFDPointer", 330, 4), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("InteroperabilityIFDPointer", 40965, 4), new d("CameraSettingsIFDPointer", 8224, 1), new d("ImageProcessingIFDPointer", 8256, 1)};
        k0 = new HashMap[dVarArr10.length];
        l0 = new HashMap[dVarArr10.length];
        m0 = new HashSet(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        n0 = new HashMap();
        Charset charsetForName = Charset.forName("US-ASCII");
        o0 = charsetForName;
        p0 = "Exif\u0000\u0000".getBytes(charsetForName);
        q0 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(charsetForName);
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale);
        T = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        U = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i = 0;
        while (true) {
            d[][] dVarArr11 = i0;
            if (i >= dVarArr11.length) {
                HashMap map = n0;
                d[] dVarArr12 = j0;
                map.put(Integer.valueOf(dVarArr12[0].a), 5);
                map.put(Integer.valueOf(dVarArr12[1].a), 1);
                map.put(Integer.valueOf(dVarArr12[2].a), 2);
                map.put(Integer.valueOf(dVarArr12[3].a), 3);
                map.put(Integer.valueOf(dVarArr12[4].a), 7);
                map.put(Integer.valueOf(dVarArr12[5].a), 8);
                r0 = Pattern.compile(".*[1-9].*");
                s0 = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
                t0 = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                u0 = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                return;
            }
            k0[i] = new HashMap();
            l0[i] = new HashMap();
            for (d dVar : dVarArr11[i]) {
                k0[i].put(Integer.valueOf(dVar.a), dVar);
                l0[i].put(dVar.b, dVar);
            }
            i++;
        }
    }

    public b(InputStream inputStream) {
        this(inputStream, 0);
    }

    private boolean A(byte[] bArr) {
        int i = 0;
        while (true) {
            byte[] bArr2 = K;
            if (i >= bArr2.length) {
                int i2 = 0;
                while (true) {
                    byte[] bArr3 = L;
                    if (i2 >= bArr3.length) {
                        return true;
                    }
                    if (bArr[K.length + i2 + 4] != bArr3[i2]) {
                        return false;
                    }
                    i2++;
                }
            } else {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
                i++;
            }
        }
    }

    private void B(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("inputstream shouldn't be null");
        }
        for (int i = 0; i < i0.length; i++) {
            try {
                try {
                    this.f[i] = new HashMap();
                } catch (IOException | UnsupportedOperationException unused) {
                    boolean z2 = u;
                    a();
                    if (!z2) {
                        return;
                    }
                }
            } catch (Throwable th) {
                a();
                if (u) {
                    D();
                }
                throw th;
            }
        }
        if (!this.e) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
            this.d = g(bufferedInputStream);
            inputStream = bufferedInputStream;
        }
        if (K(this.d)) {
            f fVar = new f(inputStream);
            if (this.e) {
                m(fVar);
            } else {
                int i2 = this.d;
                if (i2 == 12) {
                    e(fVar);
                } else if (i2 == 7) {
                    h(fVar);
                } else if (i2 == 10) {
                    l(fVar);
                } else {
                    k(fVar);
                }
            }
            fVar.g(this.p);
            J(fVar);
        } else {
            C0028b c0028b = new C0028b(inputStream);
            int i3 = this.d;
            if (i3 == 4) {
                f(c0028b, 0, 0);
            } else if (i3 == 13) {
                i(c0028b);
            } else if (i3 == 9) {
                j(c0028b);
            } else if (i3 == 14) {
                n(c0028b);
            }
        }
        a();
        if (!u) {
            return;
        }
        D();
    }

    private void C(C0028b c0028b) throws IOException {
        ByteOrder byteOrderE = E(c0028b);
        this.h = byteOrderE;
        c0028b.c(byteOrderE);
        int unsignedShort = c0028b.readUnsignedShort();
        int i = this.d;
        if (i != 7 && i != 10 && unsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(unsignedShort));
        }
        int i2 = c0028b.readInt();
        if (i2 < 8) {
            throw new IOException("Invalid first Ifd offset: " + i2);
        }
        int i3 = i2 - 8;
        if (i3 > 0) {
            c0028b.f(i3);
        }
    }

    private void D() {
        for (int i = 0; i < this.f.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("The size of tag group[");
            sb.append(i);
            sb.append("]: ");
            sb.append(this.f[i].size());
            for (Map.Entry entry : this.f[i].entrySet()) {
                c cVar = (c) entry.getValue();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("tagName: ");
                sb2.append((String) entry.getKey());
                sb2.append(", tagType: ");
                sb2.append(cVar.toString());
                sb2.append(", tagValue: '");
                sb2.append(cVar.j(this.h));
                sb2.append("'");
            }
        }
    }

    private ByteOrder E(C0028b c0028b) throws IOException {
        short s2 = c0028b.readShort();
        if (s2 == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (s2 == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(s2));
    }

    private void F(byte[] bArr, int i) throws IOException {
        f fVar = new f(bArr);
        C(fVar);
        G(fVar, i);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0288  */
    /* JADX WARN: Code duplicated, block: B:104:0x0295  */
    /* JADX WARN: Code duplicated, block: B:106:0x029f  */
    /* JADX WARN: Code duplicated, block: B:115:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:139:0x02ce A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x010a  */
    /* JADX WARN: Code duplicated, block: B:47:0x0113  */
    /* JADX WARN: Code duplicated, block: B:49:0x0119  */
    /* JADX WARN: Code duplicated, block: B:51:0x011f  */
    /* JADX WARN: Code duplicated, block: B:52:0x012f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0136  */
    /* JADX WARN: Code duplicated, block: B:57:0x0144  */
    /* JADX WARN: Code duplicated, block: B:58:0x0147  */
    /* JADX WARN: Code duplicated, block: B:60:0x014a  */
    /* JADX WARN: Code duplicated, block: B:64:0x0191  */
    /* JADX WARN: Code duplicated, block: B:66:0x019c  */
    /* JADX WARN: Code duplicated, block: B:69:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:72:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:74:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:76:0x01d1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:77:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:79:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:83:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:84:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:85:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:88:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:91:0x020e  */
    /* JADX WARN: Code duplicated, block: B:93:0x021b  */
    /* JADX WARN: Code duplicated, block: B:94:0x0226 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x0228  */
    /* JADX WARN: Code duplicated, block: B:96:0x0243 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x0245  */
    /* JADX WARN: Code duplicated, block: B:99:0x0259  */
    private void G(f fVar, int i) throws IOException {
        d dVar;
        int i2;
        long j;
        boolean z2;
        d dVar2;
        short s2;
        Integer num;
        long j2;
        int unsignedShort;
        long jB;
        int i3;
        this.g.add(Integer.valueOf(fVar.c));
        short s3 = fVar.readShort();
        if (u) {
            StringBuilder sb = new StringBuilder();
            sb.append("numberOfDirectoryEntry: ");
            sb.append((int) s3);
        }
        if (s3 <= 0) {
            return;
        }
        char c2 = 0;
        short s4 = 0;
        while (s4 < s3) {
            int unsignedShort2 = fVar.readUnsignedShort();
            int unsignedShort3 = fVar.readUnsignedShort();
            int i4 = fVar.readInt();
            long jA = ((long) fVar.a()) + 4;
            d dVar3 = (d) k0[i].get(Integer.valueOf(unsignedShort2));
            boolean z3 = u;
            if (z3) {
                Object[] objArr = new Object[5];
                objArr[c2] = Integer.valueOf(i);
                objArr[1] = Integer.valueOf(unsignedShort2);
                objArr[2] = dVar3 != null ? dVar3.b : null;
                objArr[3] = Integer.valueOf(unsignedShort3);
                objArr[4] = Integer.valueOf(i4);
                String.format("ifdType: %d, tagNumber: %d, tagName: %s, dataFormat: %d, numberOfComponents: %d", objArr);
            }
            if (dVar3 != null) {
                if (unsignedShort3 > 0) {
                    int[] iArr = W;
                    if (unsignedShort3 < iArr.length) {
                        if (dVar3.a(unsignedShort3)) {
                            if (unsignedShort3 == 7) {
                                unsignedShort3 = dVar3.c;
                            }
                            i2 = unsignedShort2;
                            dVar = dVar3;
                            j = ((long) iArr[unsignedShort3]) * ((long) i4);
                            if (j < 0 || j > 2147483647L) {
                                if (z3) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Skip the tag entry since the number of components is invalid: ");
                                    sb2.append(i4);
                                }
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                        } else if (z3) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Skip the tag entry since data format (");
                            sb3.append(V[unsignedShort3]);
                            sb3.append(") is unexpected for tag: ");
                            sb3.append(dVar3.b);
                        }
                    }
                    if (z2) {
                        if (j > 4) {
                            i3 = fVar.readInt();
                            if (z3) {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("seek to data offset: ");
                                sb4.append(i3);
                            }
                            if (this.d == 7) {
                                dVar2 = dVar;
                                s2 = s4;
                                if ("MakerNote".equals(dVar2.b)) {
                                    this.q = i3;
                                } else if (i != 6 && "ThumbnailImage".equals(dVar2.b)) {
                                    this.r = i3;
                                    this.f24s = i4;
                                    c cVarF = c.f(6, this.h);
                                    c cVarB = c.b(this.r, this.h);
                                    c cVarB2 = c.b(this.f24s, this.h);
                                    this.f[4].put("Compression", cVarF);
                                    this.f[4].put("JPEGInterchangeFormat", cVarB);
                                    this.f[4].put("JPEGInterchangeFormatLength", cVarB2);
                                }
                            } else {
                                dVar2 = dVar;
                                s2 = s4;
                            }
                            fVar.g(i3);
                        } else {
                            s3 = s3;
                            jA = jA;
                            dVar2 = dVar;
                            s2 = s4;
                        }
                        num = (Integer) n0.get(Integer.valueOf(i2));
                        if (z3) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("nextIfdType: ");
                            sb5.append(num);
                            sb5.append(" byteCount: ");
                            sb5.append(j);
                        }
                        if (num != null) {
                            if (unsignedShort3 != 3) {
                                if (unsignedShort3 == 4) {
                                    jB = fVar.b();
                                } else if (unsignedShort3 == 8) {
                                    unsignedShort = fVar.readShort();
                                } else if (unsignedShort3 != 9 || unsignedShort3 == 13) {
                                    unsignedShort = fVar.readInt();
                                } else {
                                    jB = -1;
                                }
                                if (z3) {
                                    String.format("Offset: %d, tagName: %s", Long.valueOf(jB), dVar2.b);
                                }
                                if (jB > 0) {
                                    if (!this.g.contains(Integer.valueOf((int) jB))) {
                                        fVar.g(jB);
                                        G(fVar, num.intValue());
                                    } else if (z3) {
                                        StringBuilder sb6 = new StringBuilder();
                                        sb6.append("Skip jump into the IFD since it has already been read: IfdType ");
                                        sb6.append(num);
                                        sb6.append(" (at ");
                                        sb6.append(jB);
                                        sb6.append(")");
                                    }
                                } else if (z3) {
                                    StringBuilder sb7 = new StringBuilder();
                                    sb7.append("Skip jump into the IFD since its offset is invalid: ");
                                    sb7.append(jB);
                                }
                                fVar.g(jA);
                            } else {
                                unsignedShort = fVar.readUnsignedShort();
                            }
                            jB = unsignedShort;
                            if (z3) {
                                String.format("Offset: %d, tagName: %s", Long.valueOf(jB), dVar2.b);
                            }
                            if (jB > 0) {
                                if (!this.g.contains(Integer.valueOf((int) jB))) {
                                    fVar.g(jB);
                                    G(fVar, num.intValue());
                                } else if (z3) {
                                    StringBuilder sb8 = new StringBuilder();
                                    sb8.append("Skip jump into the IFD since it has already been read: IfdType ");
                                    sb8.append(num);
                                    sb8.append(" (at ");
                                    sb8.append(jB);
                                    sb8.append(")");
                                }
                            } else if (z3) {
                                StringBuilder sb9 = new StringBuilder();
                                sb9.append("Skip jump into the IFD since its offset is invalid: ");
                                sb9.append(jB);
                            }
                            fVar.g(jA);
                        } else {
                            j2 = jA;
                            int iA = fVar.a() + this.p;
                            byte[] bArr = new byte[(int) j];
                            fVar.readFully(bArr);
                            c cVar = new c(unsignedShort3, i4, iA, bArr);
                            this.f[i].put(dVar2.b, cVar);
                            if ("DNGVersion".equals(dVar2.b)) {
                                this.d = 3;
                            }
                            if (((!"Make".equals(dVar2.b) || "Model".equals(dVar2.b)) && cVar.j(this.h).contains("PENTAX")) || ("Compression".equals(dVar2.b) && cVar.i(this.h) == 65535)) {
                                this.d = 8;
                            }
                            if (fVar.a() != j2) {
                                fVar.g(j2);
                            }
                        }
                    } else {
                        fVar.g(jA);
                        s3 = s3;
                        s2 = s4;
                    }
                    s4 = (short) (s2 + 1);
                    s3 = s3;
                    c2 = 0;
                }
                dVar = dVar3;
                i2 = unsignedShort2;
                if (z3) {
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append("Skip the tag entry since data format is invalid: ");
                    sb10.append(unsignedShort3);
                }
                z2 = false;
                j = 0;
                if (z2) {
                    fVar.g(jA);
                    s3 = s3;
                    s2 = s4;
                } else {
                    if (j > 4) {
                        i3 = fVar.readInt();
                        if (z3) {
                            StringBuilder sb11 = new StringBuilder();
                            sb11.append("seek to data offset: ");
                            sb11.append(i3);
                        }
                        if (this.d == 7) {
                            dVar2 = dVar;
                            s2 = s4;
                            if ("MakerNote".equals(dVar2.b)) {
                                this.q = i3;
                            } else if (i != 6) {
                            }
                        } else {
                            dVar2 = dVar;
                            s2 = s4;
                        }
                        fVar.g(i3);
                    } else {
                        s3 = s3;
                        jA = jA;
                        dVar2 = dVar;
                        s2 = s4;
                    }
                    num = (Integer) n0.get(Integer.valueOf(i2));
                    if (z3) {
                        StringBuilder sb12 = new StringBuilder();
                        sb12.append("nextIfdType: ");
                        sb12.append(num);
                        sb12.append(" byteCount: ");
                        sb12.append(j);
                    }
                    if (num != null) {
                        if (unsignedShort3 != 3) {
                            if (unsignedShort3 == 4) {
                                jB = fVar.b();
                            } else if (unsignedShort3 == 8) {
                                if (unsignedShort3 != 9) {
                                }
                                unsignedShort = fVar.readInt();
                            } else {
                                unsignedShort = fVar.readShort();
                            }
                            if (z3) {
                                String.format("Offset: %d, tagName: %s", Long.valueOf(jB), dVar2.b);
                            }
                            if (jB > 0) {
                                if (!this.g.contains(Integer.valueOf((int) jB))) {
                                    fVar.g(jB);
                                    G(fVar, num.intValue());
                                } else if (z3) {
                                    StringBuilder sb13 = new StringBuilder();
                                    sb13.append("Skip jump into the IFD since it has already been read: IfdType ");
                                    sb13.append(num);
                                    sb13.append(" (at ");
                                    sb13.append(jB);
                                    sb13.append(")");
                                }
                            } else if (z3) {
                                StringBuilder sb14 = new StringBuilder();
                                sb14.append("Skip jump into the IFD since its offset is invalid: ");
                                sb14.append(jB);
                            }
                            fVar.g(jA);
                        } else {
                            unsignedShort = fVar.readUnsignedShort();
                        }
                        jB = unsignedShort;
                        if (z3) {
                            String.format("Offset: %d, tagName: %s", Long.valueOf(jB), dVar2.b);
                        }
                        if (jB > 0) {
                            if (!this.g.contains(Integer.valueOf((int) jB))) {
                                fVar.g(jB);
                                G(fVar, num.intValue());
                            } else if (z3) {
                                StringBuilder sb15 = new StringBuilder();
                                sb15.append("Skip jump into the IFD since it has already been read: IfdType ");
                                sb15.append(num);
                                sb15.append(" (at ");
                                sb15.append(jB);
                                sb15.append(")");
                            }
                        } else if (z3) {
                            StringBuilder sb16 = new StringBuilder();
                            sb16.append("Skip jump into the IFD since its offset is invalid: ");
                            sb16.append(jB);
                        }
                        fVar.g(jA);
                    } else {
                        j2 = jA;
                        int iA2 = fVar.a() + this.p;
                        byte[] bArr2 = new byte[(int) j];
                        fVar.readFully(bArr2);
                        c cVar2 = new c(unsignedShort3, i4, iA2, bArr2);
                        this.f[i].put(dVar2.b, cVar2);
                        if ("DNGVersion".equals(dVar2.b)) {
                            this.d = 3;
                        }
                        if (!"Make".equals(dVar2.b)) {
                        }
                        this.d = 8;
                        if (fVar.a() != j2) {
                            fVar.g(j2);
                        }
                    }
                }
                s4 = (short) (s2 + 1);
                s3 = s3;
                c2 = 0;
            } else if (z3) {
                StringBuilder sb17 = new StringBuilder();
                sb17.append("Skip the tag entry since tag number is not defined: ");
                sb17.append(unsignedShort2);
            }
            dVar = dVar3;
            i2 = unsignedShort2;
            z2 = false;
            j = 0;
            if (z2) {
                fVar.g(jA);
                s3 = s3;
                s2 = s4;
            } else {
                if (j > 4) {
                    i3 = fVar.readInt();
                    if (z3) {
                        StringBuilder sb18 = new StringBuilder();
                        sb18.append("seek to data offset: ");
                        sb18.append(i3);
                    }
                    if (this.d == 7) {
                        dVar2 = dVar;
                        s2 = s4;
                        if ("MakerNote".equals(dVar2.b)) {
                            this.q = i3;
                        } else if (i != 6) {
                        }
                    } else {
                        dVar2 = dVar;
                        s2 = s4;
                    }
                    fVar.g(i3);
                } else {
                    s3 = s3;
                    jA = jA;
                    dVar2 = dVar;
                    s2 = s4;
                }
                num = (Integer) n0.get(Integer.valueOf(i2));
                if (z3) {
                    StringBuilder sb19 = new StringBuilder();
                    sb19.append("nextIfdType: ");
                    sb19.append(num);
                    sb19.append(" byteCount: ");
                    sb19.append(j);
                }
                if (num != null) {
                    if (unsignedShort3 != 3) {
                        if (unsignedShort3 == 4) {
                            jB = fVar.b();
                        } else if (unsignedShort3 == 8) {
                            if (unsignedShort3 != 9) {
                            }
                            unsignedShort = fVar.readInt();
                        } else {
                            unsignedShort = fVar.readShort();
                        }
                        if (z3) {
                            String.format("Offset: %d, tagName: %s", Long.valueOf(jB), dVar2.b);
                        }
                        if (jB > 0) {
                            if (!this.g.contains(Integer.valueOf((int) jB))) {
                                fVar.g(jB);
                                G(fVar, num.intValue());
                            } else if (z3) {
                                StringBuilder sb110 = new StringBuilder();
                                sb110.append("Skip jump into the IFD since it has already been read: IfdType ");
                                sb110.append(num);
                                sb110.append(" (at ");
                                sb110.append(jB);
                                sb110.append(")");
                            }
                        } else if (z3) {
                            StringBuilder sb111 = new StringBuilder();
                            sb111.append("Skip jump into the IFD since its offset is invalid: ");
                            sb111.append(jB);
                        }
                        fVar.g(jA);
                    } else {
                        unsignedShort = fVar.readUnsignedShort();
                    }
                    jB = unsignedShort;
                    if (z3) {
                        String.format("Offset: %d, tagName: %s", Long.valueOf(jB), dVar2.b);
                    }
                    if (jB > 0) {
                        if (!this.g.contains(Integer.valueOf((int) jB))) {
                            fVar.g(jB);
                            G(fVar, num.intValue());
                        } else if (z3) {
                            StringBuilder sb112 = new StringBuilder();
                            sb112.append("Skip jump into the IFD since it has already been read: IfdType ");
                            sb112.append(num);
                            sb112.append(" (at ");
                            sb112.append(jB);
                            sb112.append(")");
                        }
                    } else if (z3) {
                        StringBuilder sb113 = new StringBuilder();
                        sb113.append("Skip jump into the IFD since its offset is invalid: ");
                        sb113.append(jB);
                    }
                    fVar.g(jA);
                } else {
                    j2 = jA;
                    int iA3 = fVar.a() + this.p;
                    byte[] bArr3 = new byte[(int) j];
                    fVar.readFully(bArr3);
                    c cVar3 = new c(unsignedShort3, i4, iA3, bArr3);
                    this.f[i].put(dVar2.b, cVar3);
                    if ("DNGVersion".equals(dVar2.b)) {
                        this.d = 3;
                    }
                    if (!"Make".equals(dVar2.b)) {
                    }
                    this.d = 8;
                    if (fVar.a() != j2) {
                        fVar.g(j2);
                    }
                }
            }
            s4 = (short) (s2 + 1);
            s3 = s3;
            c2 = 0;
        }
        int i5 = fVar.readInt();
        boolean z4 = u;
        if (z4) {
            String.format("nextIfdOffset: %d", Integer.valueOf(i5));
        }
        long j3 = i5;
        if (j3 <= 0) {
            if (z4) {
                StringBuilder sb20 = new StringBuilder();
                sb20.append("Stop reading file since a wrong offset may cause an infinite loop: ");
                sb20.append(i5);
                return;
            }
            return;
        }
        if (this.g.contains(Integer.valueOf(i5))) {
            if (z4) {
                StringBuilder sb21 = new StringBuilder();
                sb21.append("Stop reading file since re-reading an IFD may cause an infinite loop: ");
                sb21.append(i5);
                return;
            }
            return;
        }
        fVar.g(j3);
        if (this.f[4].isEmpty()) {
            G(fVar, 4);
        } else if (this.f[5].isEmpty()) {
            G(fVar, 5);
        }
    }

    private void H(int i, String str, String str2) {
        if (this.f[i].isEmpty() || this.f[i].get(str) == null) {
            return;
        }
        HashMap map = this.f[i];
        map.put(str2, map.get(str));
        this.f[i].remove(str);
    }

    private void I(f fVar, int i) throws Throwable {
        c cVar = (c) this.f[i].get("ImageLength");
        c cVar2 = (c) this.f[i].get("ImageWidth");
        if (cVar == null || cVar2 == null) {
            c cVar3 = (c) this.f[i].get("JPEGInterchangeFormat");
            c cVar4 = (c) this.f[i].get("JPEGInterchangeFormatLength");
            if (cVar3 == null || cVar4 == null) {
                return;
            }
            int i2 = cVar3.i(this.h);
            int i3 = cVar3.i(this.h);
            fVar.g(i2);
            byte[] bArr = new byte[i3];
            fVar.read(bArr);
            f(new C0028b(bArr), i2, i);
        }
    }

    private void J(C0028b c0028b) throws Throwable {
        HashMap map = this.f[4];
        c cVar = (c) map.get("Compression");
        if (cVar == null) {
            this.o = 6;
            o(c0028b, map);
            return;
        }
        int i = cVar.i(this.h);
        this.o = i;
        if (i != 1) {
            if (i == 6) {
                o(c0028b, map);
                return;
            } else if (i != 7) {
                return;
            }
        }
        if (y(map)) {
            p(c0028b, map);
        }
    }

    private static boolean K(int i) {
        return (i == 4 || i == 9 || i == 13 || i == 14) ? false : true;
    }

    private void L(int i, int i2) throws Throwable {
        if (this.f[i].isEmpty() || this.f[i2].isEmpty()) {
            return;
        }
        c cVar = (c) this.f[i].get("ImageLength");
        c cVar2 = (c) this.f[i].get("ImageWidth");
        c cVar3 = (c) this.f[i2].get("ImageLength");
        c cVar4 = (c) this.f[i2].get("ImageWidth");
        if (cVar == null || cVar2 == null || cVar3 == null || cVar4 == null) {
            return;
        }
        int i3 = cVar.i(this.h);
        int i4 = cVar2.i(this.h);
        int i5 = cVar3.i(this.h);
        int i6 = cVar4.i(this.h);
        if (i3 >= i5 || i4 >= i6) {
            return;
        }
        HashMap[] mapArr = this.f;
        HashMap map = mapArr[i];
        mapArr[i] = mapArr[i2];
        mapArr[i2] = map;
    }

    private void M(f fVar, int i) throws Throwable {
        c cVarF;
        c cVarF2;
        c cVar = (c) this.f[i].get("DefaultCropSize");
        c cVar2 = (c) this.f[i].get("SensorTopBorder");
        c cVar3 = (c) this.f[i].get("SensorLeftBorder");
        c cVar4 = (c) this.f[i].get("SensorBottomBorder");
        c cVar5 = (c) this.f[i].get("SensorRightBorder");
        if (cVar == null) {
            if (cVar2 == null || cVar3 == null || cVar4 == null || cVar5 == null) {
                I(fVar, i);
                return;
            }
            int i2 = cVar2.i(this.h);
            int i3 = cVar4.i(this.h);
            int i4 = cVar5.i(this.h);
            int i5 = cVar3.i(this.h);
            if (i3 <= i2 || i4 <= i5) {
                return;
            }
            c cVarF3 = c.f(i3 - i2, this.h);
            c cVarF4 = c.f(i4 - i5, this.h);
            this.f[i].put("ImageLength", cVarF3);
            this.f[i].put("ImageWidth", cVarF4);
            return;
        }
        if (cVar.a == 5) {
            e[] eVarArr = (e[]) cVar.k(this.h);
            if (eVarArr == null || eVarArr.length != 2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid crop size values. cropSize=");
                sb.append(Arrays.toString(eVarArr));
                return;
            }
            cVarF = c.d(eVarArr[0], this.h);
            cVarF2 = c.d(eVarArr[1], this.h);
        } else {
            int[] iArr = (int[]) cVar.k(this.h);
            if (iArr == null || iArr.length != 2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid crop size values. cropSize=");
                sb2.append(Arrays.toString(iArr));
                return;
            }
            cVarF = c.f(iArr[0], this.h);
            cVarF2 = c.f(iArr[1], this.h);
        }
        this.f[i].put("ImageWidth", cVarF);
        this.f[i].put("ImageLength", cVarF2);
    }

    private void N() throws Throwable {
        L(0, 5);
        L(0, 4);
        L(5, 4);
        c cVar = (c) this.f[1].get("PixelXDimension");
        c cVar2 = (c) this.f[1].get("PixelYDimension");
        if (cVar != null && cVar2 != null) {
            this.f[0].put("ImageWidth", cVar);
            this.f[0].put("ImageLength", cVar2);
        }
        if (this.f[4].isEmpty() && z(this.f[5])) {
            HashMap[] mapArr = this.f;
            mapArr[4] = mapArr[5];
            mapArr[5] = new HashMap();
        }
        z(this.f[4]);
        H(0, "ThumbnailOrientation", "Orientation");
        H(0, "ThumbnailImageLength", "ImageLength");
        H(0, "ThumbnailImageWidth", "ImageWidth");
        H(5, "ThumbnailOrientation", "Orientation");
        H(5, "ThumbnailImageLength", "ImageLength");
        H(5, "ThumbnailImageWidth", "ImageWidth");
        H(4, "Orientation", "ThumbnailOrientation");
        H(4, "ImageLength", "ThumbnailImageLength");
        H(4, "ImageWidth", "ThumbnailImageWidth");
    }

    private void a() {
        String strB = b("DateTimeOriginal");
        if (strB != null && b("DateTime") == null) {
            this.f[0].put("DateTime", c.a(strB));
        }
        if (b("ImageWidth") == null) {
            this.f[0].put("ImageWidth", c.b(0L, this.h));
        }
        if (b("ImageLength") == null) {
            this.f[0].put("ImageLength", c.b(0L, this.h));
        }
        if (b("Orientation") == null) {
            this.f[0].put("Orientation", c.b(0L, this.h));
        }
        if (b("LightSource") == null) {
            this.f[1].put("LightSource", c.b(0L, this.h));
        }
    }

    private c d(String str) {
        if (str == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        if ("ISOSpeedRatings".equals(str)) {
            str = "PhotographicSensitivity";
        }
        for (int i = 0; i < i0.length; i++) {
            c cVar = (c) this.f[i].get(str);
            if (cVar != null) {
                return cVar;
            }
        }
        return null;
    }

    private void e(f fVar) throws IOException {
        String strExtractMetadata;
        String strExtractMetadata2;
        String strExtractMetadata3;
        int i;
        if (Build.VERSION.SDK_INT < 28) {
            throw new UnsupportedOperationException("Reading EXIF from HEIF files is supported from SDK 28 and above");
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                androidx.exifinterface.media.c.b.a(mediaMetadataRetriever, new a(fVar));
                String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(33);
                String strExtractMetadata5 = mediaMetadataRetriever.extractMetadata(34);
                String strExtractMetadata6 = mediaMetadataRetriever.extractMetadata(26);
                String strExtractMetadata7 = mediaMetadataRetriever.extractMetadata(17);
                if ("yes".equals(strExtractMetadata6)) {
                    strExtractMetadata = mediaMetadataRetriever.extractMetadata(29);
                    strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(30);
                    strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(31);
                } else if ("yes".equals(strExtractMetadata7)) {
                    strExtractMetadata = mediaMetadataRetriever.extractMetadata(18);
                    strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                    strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(24);
                } else {
                    strExtractMetadata = null;
                    strExtractMetadata2 = null;
                    strExtractMetadata3 = null;
                }
                if (strExtractMetadata != null) {
                    this.f[0].put("ImageWidth", c.f(Integer.parseInt(strExtractMetadata), this.h));
                }
                if (strExtractMetadata2 != null) {
                    this.f[0].put("ImageLength", c.f(Integer.parseInt(strExtractMetadata2), this.h));
                }
                if (strExtractMetadata3 != null) {
                    int i2 = Integer.parseInt(strExtractMetadata3);
                    if (i2 == 90) {
                        i = 6;
                    } else if (i2 != 180) {
                        i = i2 != 270 ? 1 : 8;
                    } else {
                        i = 3;
                    }
                    this.f[0].put("Orientation", c.f(i, this.h));
                }
                if (strExtractMetadata4 != null && strExtractMetadata5 != null) {
                    int i3 = Integer.parseInt(strExtractMetadata4);
                    int i4 = Integer.parseInt(strExtractMetadata5);
                    if (i4 <= 6) {
                        throw new IOException("Invalid exif length");
                    }
                    fVar.g(i3);
                    byte[] bArr = new byte[6];
                    if (fVar.read(bArr) != 6) {
                        throw new IOException("Can't read identifier");
                    }
                    int i5 = i3 + 6;
                    int i6 = i4 - 6;
                    if (!Arrays.equals(bArr, p0)) {
                        throw new IOException("Invalid identifier");
                    }
                    byte[] bArr2 = new byte[i6];
                    if (fVar.read(bArr2) != i6) {
                        throw new IOException("Can't read exif");
                    }
                    this.p = i5;
                    F(bArr2, 0);
                }
                if (u) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Heif meta: ");
                    sb.append(strExtractMetadata);
                    sb.append("x");
                    sb.append(strExtractMetadata2);
                    sb.append(", rotation ");
                    sb.append(strExtractMetadata3);
                }
                mediaMetadataRetriever.release();
            } catch (RuntimeException unused) {
                throw new UnsupportedOperationException("Failed to read EXIF from HEIF file. Given stream is either malformed or unsupported.");
            }
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00a1 A[FALL_THROUGH] */
    /* JADX WARN: Code duplicated, block: B:36:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:41:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:61:0x0162 A[LOOP:0: B:10:0x002f->B:61:0x0162, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:80:0x016a A[SYNTHETIC] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1095)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    private void f(androidx.exifinterface.media.b.C0028b r20, int r21, int r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 508
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.b.f(androidx.exifinterface.media.b$b, int, int):void");
    }

    private int g(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(5000);
        byte[] bArr = new byte[5000];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (s(bArr)) {
            return 4;
        }
        if (v(bArr)) {
            return 9;
        }
        if (r(bArr)) {
            return 12;
        }
        if (t(bArr)) {
            return 7;
        }
        if (w(bArr)) {
            return 10;
        }
        if (u(bArr)) {
            return 13;
        }
        return A(bArr) ? 14 : 0;
    }

    private void h(f fVar) throws Throwable {
        int i;
        int i2;
        k(fVar);
        c cVar = (c) this.f[1].get("MakerNote");
        if (cVar != null) {
            f fVar2 = new f(cVar.d);
            fVar2.c(this.h);
            byte[] bArr = E;
            byte[] bArr2 = new byte[bArr.length];
            fVar2.readFully(bArr2);
            fVar2.g(0L);
            byte[] bArr3 = F;
            byte[] bArr4 = new byte[bArr3.length];
            fVar2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                fVar2.g(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                fVar2.g(12L);
            }
            G(fVar2, 6);
            c cVar2 = (c) this.f[7].get("PreviewImageStart");
            c cVar3 = (c) this.f[7].get("PreviewImageLength");
            if (cVar2 != null && cVar3 != null) {
                this.f[5].put("JPEGInterchangeFormat", cVar2);
                this.f[5].put("JPEGInterchangeFormatLength", cVar3);
            }
            c cVar4 = (c) this.f[8].get("AspectFrame");
            if (cVar4 != null) {
                int[] iArr = (int[]) cVar4.k(this.h);
                if (iArr == null || iArr.length != 4) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid aspect frame values. frame=");
                    sb.append(Arrays.toString(iArr));
                    return;
                }
                int i3 = iArr[2];
                int i4 = iArr[0];
                if (i3 <= i4 || (i = iArr[3]) <= (i2 = iArr[1])) {
                    return;
                }
                int i5 = (i3 - i4) + 1;
                int i6 = (i - i2) + 1;
                if (i5 < i6) {
                    int i7 = i5 + i6;
                    i6 = i7 - i6;
                    i5 = i7 - i6;
                }
                c cVarF = c.f(i5, this.h);
                c cVarF2 = c.f(i6, this.h);
                this.f[0].put("ImageWidth", cVarF);
                this.f[0].put("ImageLength", cVarF2);
            }
        }
    }

    private void i(C0028b c0028b) throws Throwable {
        if (u) {
            StringBuilder sb = new StringBuilder();
            sb.append("getPngAttributes starting with: ");
            sb.append(c0028b);
        }
        c0028b.c(ByteOrder.BIG_ENDIAN);
        byte[] bArr = G;
        c0028b.f(bArr.length);
        int length = bArr.length + 0;
        while (true) {
            try {
                int i = c0028b.readInt();
                int i2 = length + 4;
                byte[] bArr2 = new byte[4];
                if (c0028b.read(bArr2) != 4) {
                    throw new IOException("Encountered invalid length while parsing PNG chunktype");
                }
                int i3 = i2 + 4;
                if (i3 == 16 && !Arrays.equals(bArr2, I)) {
                    throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                }
                if (Arrays.equals(bArr2, J)) {
                    return;
                }
                if (Arrays.equals(bArr2, H)) {
                    byte[] bArr3 = new byte[i];
                    if (c0028b.read(bArr3) != i) {
                        throw new IOException("Failed to read given length for given PNG chunk type: " + androidx.exifinterface.media.c.a(bArr2));
                    }
                    int i4 = c0028b.readInt();
                    CRC32 crc32 = new CRC32();
                    crc32.update(bArr2);
                    crc32.update(bArr3);
                    if (((int) crc32.getValue()) == i4) {
                        this.p = i3;
                        F(bArr3, 0);
                        N();
                        J(new C0028b(bArr3));
                        return;
                    }
                    throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + i4 + ", calculated CRC value: " + crc32.getValue());
                }
                int i5 = i + 4;
                c0028b.f(i5);
                length = i3 + i5;
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    private void j(C0028b c0028b) throws Throwable {
        boolean z2 = u;
        if (z2) {
            StringBuilder sb = new StringBuilder();
            sb.append("getRafAttributes starting with: ");
            sb.append(c0028b);
        }
        c0028b.f(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        c0028b.read(bArr);
        c0028b.read(bArr2);
        c0028b.read(bArr3);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        int i3 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i2];
        c0028b.f(i - c0028b.a());
        c0028b.read(bArr4);
        f(new C0028b(bArr4), i, 5);
        c0028b.f(i3 - c0028b.a());
        c0028b.c(ByteOrder.BIG_ENDIAN);
        int i4 = c0028b.readInt();
        if (z2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("numberOfDirectoryEntry: ");
            sb2.append(i4);
        }
        for (int i5 = 0; i5 < i4; i5++) {
            int unsignedShort = c0028b.readUnsignedShort();
            int unsignedShort2 = c0028b.readUnsignedShort();
            if (unsignedShort == d0.a) {
                short s2 = c0028b.readShort();
                short s3 = c0028b.readShort();
                c cVarF = c.f(s2, this.h);
                c cVarF2 = c.f(s3, this.h);
                this.f[0].put("ImageLength", cVarF);
                this.f[0].put("ImageWidth", cVarF2);
                if (u) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Updated to length: ");
                    sb3.append((int) s2);
                    sb3.append(", width: ");
                    sb3.append((int) s3);
                    return;
                }
                return;
            }
            c0028b.f(unsignedShort2);
        }
    }

    private void k(f fVar) throws Throwable {
        c cVar;
        C(fVar);
        G(fVar, 0);
        M(fVar, 0);
        M(fVar, 5);
        M(fVar, 4);
        N();
        if (this.d != 8 || (cVar = (c) this.f[1].get("MakerNote")) == null) {
            return;
        }
        f fVar2 = new f(cVar.d);
        fVar2.c(this.h);
        fVar2.f(6);
        G(fVar2, 9);
        c cVar2 = (c) this.f[9].get("ColorSpace");
        if (cVar2 != null) {
            this.f[1].put("ColorSpace", cVar2);
        }
    }

    private void l(f fVar) throws Throwable {
        if (u) {
            StringBuilder sb = new StringBuilder();
            sb.append("getRw2Attributes starting with: ");
            sb.append(fVar);
        }
        k(fVar);
        c cVar = (c) this.f[0].get("JpgFromRaw");
        if (cVar != null) {
            f(new C0028b(cVar.d), (int) cVar.c, 5);
        }
        c cVar2 = (c) this.f[0].get("ISO");
        c cVar3 = (c) this.f[1].get("PhotographicSensitivity");
        if (cVar2 == null || cVar3 != null) {
            return;
        }
        this.f[1].put("PhotographicSensitivity", cVar2);
    }

    private void m(f fVar) throws IOException {
        byte[] bArr = p0;
        fVar.f(bArr.length);
        byte[] bArr2 = new byte[fVar.available()];
        fVar.readFully(bArr2);
        this.p = bArr.length;
        F(bArr2, 0);
    }

    private void n(C0028b c0028b) throws Throwable {
        if (u) {
            StringBuilder sb = new StringBuilder();
            sb.append("getWebpAttributes starting with: ");
            sb.append(c0028b);
        }
        c0028b.c(ByteOrder.LITTLE_ENDIAN);
        c0028b.f(K.length);
        int i = c0028b.readInt() + 8;
        byte[] bArr = L;
        c0028b.f(bArr.length);
        int length = bArr.length + 8;
        while (true) {
            try {
                byte[] bArr2 = new byte[4];
                if (c0028b.read(bArr2) != 4) {
                    throw new IOException("Encountered invalid length while parsing WebP chunktype");
                }
                int i2 = c0028b.readInt();
                int i3 = length + 4 + 4;
                if (Arrays.equals(M, bArr2)) {
                    byte[] bArr3 = new byte[i2];
                    if (c0028b.read(bArr3) == i2) {
                        this.p = i3;
                        F(bArr3, 0);
                        J(new C0028b(bArr3));
                        return;
                    } else {
                        throw new IOException("Failed to read given length for given PNG chunk type: " + androidx.exifinterface.media.c.a(bArr2));
                    }
                }
                if (i2 % 2 == 1) {
                    i2++;
                }
                length = i3 + i2;
                if (length == i) {
                    return;
                }
                if (length > i) {
                    throw new IOException("Encountered WebP file with invalid chunk size");
                }
                c0028b.f(i2);
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    private void o(C0028b c0028b, HashMap map) throws Throwable {
        c cVar = (c) map.get("JPEGInterchangeFormat");
        c cVar2 = (c) map.get("JPEGInterchangeFormatLength");
        if (cVar == null || cVar2 == null) {
            return;
        }
        int i = cVar.i(this.h);
        int i2 = cVar2.i(this.h);
        if (this.d == 7) {
            i += this.q;
        }
        if (i > 0 && i2 > 0) {
            this.i = true;
            if (this.a == null && this.c == null && this.b == null) {
                byte[] bArr = new byte[i2];
                c0028b.skip(i);
                c0028b.read(bArr);
                this.n = bArr;
            }
            this.l = i;
            this.m = i2;
        }
        if (u) {
            StringBuilder sb = new StringBuilder();
            sb.append("Setting thumbnail attributes with offset: ");
            sb.append(i);
            sb.append(", length: ");
            sb.append(i2);
        }
    }

    private void p(C0028b c0028b, HashMap map) {
        c cVar = (c) map.get("StripOffsets");
        c cVar2 = (c) map.get("StripByteCounts");
        if (cVar == null || cVar2 == null) {
            return;
        }
        long[] jArrB = androidx.exifinterface.media.c.b(cVar.k(this.h));
        long[] jArrB2 = androidx.exifinterface.media.c.b(cVar2.k(this.h));
        if (jArrB == null || jArrB.length == 0 || jArrB2 == null || jArrB2.length == 0 || jArrB.length != jArrB2.length) {
            return;
        }
        long j = 0;
        for (long j2 : jArrB2) {
            j += j2;
        }
        int i = (int) j;
        byte[] bArr = new byte[i];
        int i2 = 1;
        this.k = true;
        this.j = true;
        this.i = true;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < jArrB.length) {
            int i6 = (int) jArrB[i3];
            int i7 = (int) jArrB2[i3];
            if (i3 < jArrB.length - i2 && i6 + i7 != jArrB[i3 + 1]) {
                this.k = false;
            }
            int i8 = i6 - i4;
            if (i8 < 0) {
                return;
            }
            long j3 = i8;
            if (c0028b.skip(j3) != j3) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to skip ");
                sb.append(i8);
                sb.append(" bytes.");
                return;
            }
            int i9 = i4 + i8;
            byte[] bArr2 = new byte[i7];
            if (c0028b.read(bArr2) != i7) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Failed to read ");
                sb2.append(i7);
                sb2.append(" bytes.");
                return;
            }
            i4 = i9 + i7;
            System.arraycopy(bArr2, 0, bArr, i5, i7);
            i5 += i7;
            i3++;
            i2 = 1;
        }
        this.n = bArr;
        if (this.k) {
            this.l = (int) jArrB[0];
            this.m = i;
        }
    }

    private static boolean q(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] bArr = p0;
        bufferedInputStream.mark(bArr.length);
        byte[] bArr2 = new byte[bArr.length];
        bufferedInputStream.read(bArr2);
        bufferedInputStream.reset();
        int i = 0;
        while (true) {
            byte[] bArr3 = p0;
            if (i >= bArr3.length) {
                return true;
            }
            if (bArr2[i] != bArr3[i]) {
                return false;
            }
            i++;
        }
    }

    private boolean r(byte[] bArr) throws Throwable {
        long j;
        C0028b c0028b = null;
        try {
            try {
                C0028b c0028b2 = new C0028b(bArr);
                try {
                    long length = c0028b2.readInt();
                    byte[] bArr2 = new byte[4];
                    c0028b2.read(bArr2);
                    if (!Arrays.equals(bArr2, B)) {
                        c0028b2.close();
                        return false;
                    }
                    if (length == 1) {
                        length = c0028b2.readLong();
                        j = 16;
                        if (length < 16) {
                            c0028b2.close();
                            return false;
                        }
                    } else {
                        j = 8;
                    }
                    if (length > bArr.length) {
                        length = bArr.length;
                    }
                    long j2 = length - j;
                    if (j2 < 8) {
                        c0028b2.close();
                        return false;
                    }
                    byte[] bArr3 = new byte[4];
                    boolean z2 = false;
                    boolean z3 = false;
                    for (long j3 = 0; j3 < j2 / 4; j3++) {
                        if (c0028b2.read(bArr3) != 4) {
                            c0028b2.close();
                            return false;
                        }
                        if (j3 != 1) {
                            if (Arrays.equals(bArr3, C)) {
                                z2 = true;
                            } else if (Arrays.equals(bArr3, D)) {
                                z3 = true;
                            }
                            if (z2 && z3) {
                                c0028b2.close();
                                return true;
                            }
                        }
                    }
                    c0028b2.close();
                } catch (Exception unused) {
                    c0028b = c0028b2;
                    boolean z4 = u;
                    if (c0028b != null) {
                        c0028b.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    c0028b = c0028b2;
                    if (c0028b != null) {
                        c0028b.close();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean s(byte[] bArr) {
        int i = 0;
        while (true) {
            byte[] bArr2 = A;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    private boolean t(byte[] bArr) throws Throwable {
        C0028b c0028b = null;
        try {
            C0028b c0028b2 = new C0028b(bArr);
            try {
                ByteOrder byteOrderE = E(c0028b2);
                this.h = byteOrderE;
                c0028b2.c(byteOrderE);
                short s2 = c0028b2.readShort();
                boolean z2 = s2 == 20306 || s2 == 21330;
                c0028b2.close();
                return z2;
            } catch (Exception unused) {
                c0028b = c0028b2;
                if (c0028b != null) {
                    c0028b.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                c0028b = c0028b2;
                if (c0028b != null) {
                    c0028b.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean u(byte[] bArr) {
        int i = 0;
        while (true) {
            byte[] bArr2 = G;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    private boolean v(byte[] bArr) {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i = 0; i < bytes.length; i++) {
            if (bArr[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean w(byte[] bArr) throws Throwable {
        C0028b c0028b = null;
        try {
            C0028b c0028b2 = new C0028b(bArr);
            try {
                ByteOrder byteOrderE = E(c0028b2);
                this.h = byteOrderE;
                c0028b2.c(byteOrderE);
                boolean z2 = c0028b2.readShort() == 85;
                c0028b2.close();
                return z2;
            } catch (Exception unused) {
                c0028b = c0028b2;
                if (c0028b != null) {
                    c0028b.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                c0028b = c0028b2;
                if (c0028b != null) {
                    c0028b.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean x(FileDescriptor fileDescriptor) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                androidx.exifinterface.media.c.a.c(fileDescriptor, 0L, OsConstants.SEEK_CUR);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private boolean y(HashMap map) throws Throwable {
        c cVar;
        c cVar2 = (c) map.get("BitsPerSample");
        if (cVar2 == null) {
            return false;
        }
        int[] iArr = (int[]) cVar2.k(this.h);
        int[] iArr2 = x;
        if (Arrays.equals(iArr2, iArr)) {
            return true;
        }
        if (this.d != 3 || (cVar = (c) map.get("PhotometricInterpretation")) == null) {
            return false;
        }
        int i = cVar.i(this.h);
        return (i == 1 && Arrays.equals(iArr, z)) || (i == 6 && Arrays.equals(iArr, iArr2));
    }

    private boolean z(HashMap map) {
        c cVar = (c) map.get("ImageLength");
        c cVar2 = (c) map.get("ImageWidth");
        if (cVar == null || cVar2 == null) {
            return false;
        }
        return cVar.i(this.h) <= 512 && cVar2.i(this.h) <= 512;
    }

    public String b(String str) {
        if (str == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        c cVarD = d(str);
        if (cVarD != null) {
            if (!m0.contains(str)) {
                return cVarD.j(this.h);
            }
            if (str.equals("GPSTimeStamp")) {
                int i = cVarD.a;
                if (i != 5 && i != 10) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("GPS Timestamp format is not rational. format=");
                    sb.append(cVarD.a);
                    return null;
                }
                e[] eVarArr = (e[]) cVarD.k(this.h);
                if (eVarArr == null || eVarArr.length != 3) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Invalid GPS Timestamp array. array=");
                    sb2.append(Arrays.toString(eVarArr));
                    return null;
                }
                e eVar = eVarArr[0];
                e eVar2 = eVarArr[1];
                e eVar3 = eVarArr[2];
                return String.format("%02d:%02d:%02d", Integer.valueOf((int) (eVar.a / eVar.b)), Integer.valueOf((int) (eVar2.a / eVar2.b)), Integer.valueOf((int) (eVar3.a / eVar3.b)));
            }
            try {
                return Double.toString(cVarD.h(this.h));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public int c(String str, int i) {
        if (str == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        c cVarD = d(str);
        if (cVarD == null) {
            return i;
        }
        try {
            return cVarD.i(this.h);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    private static class f extends C0028b {
        f(byte[] bArr) {
            super(bArr);
            this.a.mark(Integer.MAX_VALUE);
        }

        public void g(long j) throws IOException {
            int i = this.c;
            if (i > j) {
                this.c = 0;
                this.a.reset();
            } else {
                j -= (long) i;
            }
            f((int) j);
        }

        f(InputStream inputStream) {
            super(inputStream);
            if (inputStream.markSupported()) {
                this.a.mark(Integer.MAX_VALUE);
                return;
            }
            throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0060  */
    public b(InputStream inputStream, int i) {
        d[][] dVarArr = i0;
        this.f = new HashMap[dVarArr.length];
        this.g = new HashSet(dVarArr.length);
        this.h = ByteOrder.BIG_ENDIAN;
        if (inputStream == null) {
            throw new NullPointerException("inputStream cannot be null");
        }
        this.a = null;
        if (i == 1) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, p0.length);
            if (!q(bufferedInputStream)) {
                return;
            }
            this.e = true;
            this.c = null;
            this.b = null;
            inputStream = bufferedInputStream;
        } else if (inputStream instanceof AssetManager.AssetInputStream) {
            this.c = (AssetManager.AssetInputStream) inputStream;
            this.b = null;
        } else if (inputStream instanceof FileInputStream) {
            FileInputStream fileInputStream = (FileInputStream) inputStream;
            if (x(fileInputStream.getFD())) {
                this.c = null;
                this.b = fileInputStream.getFD();
            } else {
                this.c = null;
                this.b = null;
            }
        } else {
            this.c = null;
            this.b = null;
        }
        B(inputStream);
    }

    static class d {
        public final int a;
        public final String b;
        public final int c;
        public final int d;

        d(String str, int i, int i2) {
            this.b = str;
            this.a = i;
            this.c = i2;
            this.d = -1;
        }

        boolean a(int i) {
            int i2;
            int i3 = this.c;
            if (i3 == 7 || i == 7 || i3 == i || (i2 = this.d) == i) {
                return true;
            }
            if ((i3 == 4 || i2 == 4) && i == 3) {
                return true;
            }
            if ((i3 == 9 || i2 == 9) && i == 8) {
                return true;
            }
            return (i3 == 12 || i2 == 12) && i == 11;
        }

        d(String str, int i, int i2, int i3) {
            this.b = str;
            this.a = i;
            this.c = i2;
            this.d = i3;
        }
    }
}
