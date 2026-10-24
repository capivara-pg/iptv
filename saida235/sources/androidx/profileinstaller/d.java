package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private final AssetManager a;
    private final Executor b;
    private final j.c c;
    private final File e;
    private final String f;
    private final String g;
    private final String h;
    private e[] j;
    private byte[] k;
    private boolean i = false;
    private final byte[] d = d();

    public d(AssetManager assetManager, Executor executor, j.c cVar, String str, String str2, String str3, File file) {
        this.a = assetManager;
        this.b = executor;
        this.c = cVar;
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.e = file;
    }

    private d b(e[] eVarArr, byte[] bArr) {
        try {
            InputStream inputStreamH = h(this.a, this.h);
            if (inputStreamH == null) {
                if (inputStreamH != null) {
                    inputStreamH.close();
                }
                return null;
            }
            try {
                this.j = p.q(inputStreamH, p.o(inputStreamH, p.b), bArr, eVarArr);
                inputStreamH.close();
                return this;
            } catch (Throwable th) {
                try {
                    inputStreamH.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException e) {
            this.c.a(9, e);
        } catch (IOException e2) {
            this.c.a(7, e2);
        } catch (IllegalStateException e3) {
            this.j = null;
            this.c.a(8, e3);
        }
    }

    private void c() {
        if (!this.i) {
            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    private static byte[] d() {
        int i = Build.VERSION.SDK_INT;
        if (i < 24 || i > 33) {
            return null;
        }
        switch (i) {
            case 24:
            case 25:
                return r.e;
            case 26:
                return r.d;
            case 27:
                return r.c;
            case 28:
            case 29:
            case 30:
                return r.b;
            case 31:
            case 32:
            case 33:
                return r.a;
            default:
                return null;
        }
    }

    private InputStream f(AssetManager assetManager) {
        try {
            return h(assetManager, this.g);
        } catch (FileNotFoundException e) {
            this.c.a(6, e);
            return null;
        } catch (IOException e2) {
            this.c.a(7, e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i, Object obj) {
        this.c.a(i, obj);
    }

    private InputStream h(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message != null && message.contains("compressed")) {
                this.c.b(5, null);
            }
            return null;
        }
    }

    private e[] j(InputStream inputStream) {
        try {
            try {
                try {
                    e[] eVarArrW = p.w(inputStream, p.o(inputStream, p.a), this.f);
                    try {
                        inputStream.close();
                        return eVarArrW;
                    } catch (IOException e) {
                        this.c.a(7, e);
                        return eVarArrW;
                    }
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        this.c.a(7, e2);
                    }
                    throw th;
                }
            } catch (IOException e3) {
                this.c.a(7, e3);
                inputStream.close();
                return null;
            } catch (IllegalStateException e4) {
                this.c.a(8, e4);
                inputStream.close();
                return null;
            }
        } catch (IOException e5) {
            this.c.a(7, e5);
        }
    }

    private static boolean k() {
        int i = Build.VERSION.SDK_INT;
        if (i < 24 || i > 33) {
            return false;
        }
        if (i != 24 && i != 25) {
            switch (i) {
                case 31:
                case 32:
                case 33:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void l(final int i, final Object obj) {
        this.b.execute(new Runnable() { // from class: androidx.profileinstaller.c
            @Override // java.lang.Runnable
            public final void run() {
                this.a.g(i, obj);
            }
        });
    }

    public boolean e() {
        if (this.d == null) {
            l(3, Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (this.e.canWrite()) {
            this.i = true;
            return true;
        }
        l(4, null);
        return false;
    }

    public d i() {
        d dVarB;
        c();
        if (this.d == null) {
            return this;
        }
        InputStream inputStreamF = f(this.a);
        if (inputStreamF != null) {
            this.j = j(inputStreamF);
        }
        e[] eVarArr = this.j;
        return (eVarArr == null || !k() || (dVarB = b(eVarArr, this.d)) == null) ? this : dVarB;
    }

    public d m() {
        e[] eVarArr = this.j;
        byte[] bArr = this.d;
        if (eVarArr != null && bArr != null) {
            c();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    p.E(byteArrayOutputStream, bArr);
                    if (!p.B(byteArrayOutputStream, bArr, eVarArr)) {
                        this.c.a(5, null);
                        this.j = null;
                        byteArrayOutputStream.close();
                        return this;
                    }
                    this.k = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    this.j = null;
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                this.c.a(7, e);
            } catch (IllegalStateException e2) {
                this.c.a(8, e2);
            }
        }
        return this;
    }

    public boolean n() {
        byte[] bArr = this.k;
        if (bArr == null) {
            return false;
        }
        c();
        try {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.e);
                    try {
                        f.l(byteArrayInputStream, fileOutputStream);
                        l(1, null);
                        fileOutputStream.close();
                        byteArrayInputStream.close();
                        this.k = null;
                        this.j = null;
                        return true;
                    } catch (Throwable th) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                    throw th3;
                }
            } catch (Throwable th5) {
                this.k = null;
                this.j = null;
                throw th5;
            }
        } catch (FileNotFoundException e) {
            l(6, e);
            this.k = null;
            this.j = null;
            return false;
        } catch (IOException e2) {
            l(7, e2);
            this.k = null;
            this.j = null;
            return false;
        }
    }
}
