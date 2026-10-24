package androidx.fragment.app;

import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
final class c0 extends Writer {
    private final String a;
    private StringBuilder b = new StringBuilder(128);

    c0(String str) {
        this.a = str;
    }

    private void b() {
        if (this.b.length() > 0) {
            this.b.toString();
            StringBuilder sb = this.b;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        b();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                b();
            } else {
                this.b.append(c);
            }
        }
    }
}
