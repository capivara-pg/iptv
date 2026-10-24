package androidx.room.util;

import java.nio.ByteBuffer;
import java.util.UUID;
import kotlin.jvm.internal.m;

/* JADX INFO: loaded from: classes.dex */
public final class UUIDUtil {
    public static final UUID convertByteToUUID(byte[] bArr) {
        m.g(bArr, "bytes");
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        return new UUID(byteBufferWrap.getLong(), byteBufferWrap.getLong());
    }

    public static final byte[] convertUUIDToByte(UUID uuid) {
        m.g(uuid, "uuid");
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[16]);
        byteBufferWrap.putLong(uuid.getMostSignificantBits());
        byteBufferWrap.putLong(uuid.getLeastSignificantBits());
        byte[] bArrArray = byteBufferWrap.array();
        m.f(bArrArray, "buffer.array()");
        return bArrArray;
    }
}
