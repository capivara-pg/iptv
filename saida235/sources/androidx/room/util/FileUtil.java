package androidx.room.util;

import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import kotlin.jvm.internal.m;

/* JADX INFO: loaded from: classes.dex */
public final class FileUtil {
    public static final void copy(ReadableByteChannel readableByteChannel, FileChannel fileChannel) throws IOException {
        m.g(readableByteChannel, "input");
        m.g(fileChannel, "output");
        try {
            if (Build.VERSION.SDK_INT <= 23) {
                InputStream inputStreamNewInputStream = Channels.newInputStream(readableByteChannel);
                OutputStream outputStreamNewOutputStream = Channels.newOutputStream(fileChannel);
                byte[] bArr = new byte[RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT];
                while (true) {
                    int i = inputStreamNewInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    } else {
                        outputStreamNewOutputStream.write(bArr, 0, i);
                    }
                }
            } else {
                fileChannel.transferFrom(readableByteChannel, 0L, Long.MAX_VALUE);
            }
            fileChannel.force(false);
        } finally {
            readableByteChannel.close();
            fileChannel.close();
        }
    }
}
