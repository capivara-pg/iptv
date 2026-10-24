package androidx.profileinstaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
abstract class p {
    static final byte[] a = {112, 114, 111, 0};
    static final byte[] b = {112, 114, 109, 0};

    private static void A(InputStream inputStream) {
        f.h(inputStream);
        int iJ = f.j(inputStream);
        if (iJ == 6 || iJ == 7) {
            return;
        }
        while (iJ > 0) {
            f.j(inputStream);
            for (int iJ2 = f.j(inputStream); iJ2 > 0; iJ2--) {
                f.h(inputStream);
            }
            iJ--;
        }
    }

    static boolean B(OutputStream outputStream, byte[] bArr, e[] eVarArr) throws IOException {
        if (Arrays.equals(bArr, r.a)) {
            N(outputStream, eVarArr);
            return true;
        }
        if (Arrays.equals(bArr, r.b)) {
            M(outputStream, eVarArr);
            return true;
        }
        if (Arrays.equals(bArr, r.d)) {
            K(outputStream, eVarArr);
            return true;
        }
        if (Arrays.equals(bArr, r.c)) {
            L(outputStream, eVarArr);
            return true;
        }
        if (!Arrays.equals(bArr, r.e)) {
            return false;
        }
        J(outputStream, eVarArr);
        return true;
    }

    private static void C(OutputStream outputStream, e eVar) throws IOException {
        int iIntValue = 0;
        for (int i : eVar.h) {
            Integer numValueOf = Integer.valueOf(i);
            f.p(outputStream, numValueOf.intValue() - iIntValue);
            iIntValue = numValueOf.intValue();
        }
    }

    private static s D(e[] eVarArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f.p(byteArrayOutputStream, eVarArr.length);
            int i = 2;
            for (e eVar : eVarArr) {
                f.q(byteArrayOutputStream, eVar.c);
                f.q(byteArrayOutputStream, eVar.d);
                f.q(byteArrayOutputStream, eVar.g);
                String strJ = j(eVar.a, eVar.b, r.a);
                int iK = f.k(strJ);
                f.p(byteArrayOutputStream, iK);
                i = i + 4 + 4 + 4 + 2 + (iK * 1);
                f.n(byteArrayOutputStream, strJ);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (i == byteArray.length) {
                s sVar = new s(g.DEX_FILES, i, byteArray, false);
                byteArrayOutputStream.close();
                return sVar;
            }
            throw f.c("Expected size " + i + ", does not match actual size " + byteArray.length);
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    static void E(OutputStream outputStream, byte[] bArr) throws IOException {
        outputStream.write(a);
        outputStream.write(bArr);
    }

    private static void F(OutputStream outputStream, e eVar) throws IOException {
        I(outputStream, eVar);
        C(outputStream, eVar);
        H(outputStream, eVar);
    }

    private static void G(OutputStream outputStream, e eVar, String str) throws IOException {
        f.p(outputStream, f.k(str));
        f.p(outputStream, eVar.e);
        f.q(outputStream, eVar.f);
        f.q(outputStream, eVar.c);
        f.q(outputStream, eVar.g);
        f.n(outputStream, str);
    }

    private static void H(OutputStream outputStream, e eVar) throws IOException {
        byte[] bArr = new byte[k(eVar.g)];
        for (Map.Entry entry : eVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                z(bArr, 2, iIntValue, eVar);
            }
            if ((iIntValue2 & 4) != 0) {
                z(bArr, 4, iIntValue, eVar);
            }
        }
        outputStream.write(bArr);
    }

    private static void I(OutputStream outputStream, e eVar) throws IOException {
        int i = 0;
        for (Map.Entry entry : eVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                f.p(outputStream, iIntValue - i);
                f.p(outputStream, 0);
                i = iIntValue;
            }
        }
    }

    private static void J(OutputStream outputStream, e[] eVarArr) throws IOException {
        f.p(outputStream, eVarArr.length);
        for (e eVar : eVarArr) {
            String strJ = j(eVar.a, eVar.b, r.e);
            f.p(outputStream, f.k(strJ));
            f.p(outputStream, eVar.i.size());
            f.p(outputStream, eVar.h.length);
            f.q(outputStream, eVar.c);
            f.n(outputStream, strJ);
            Iterator it = eVar.i.keySet().iterator();
            while (it.hasNext()) {
                f.p(outputStream, ((Integer) it.next()).intValue());
            }
            for (int i : eVar.h) {
                f.p(outputStream, i);
            }
        }
    }

    private static void K(OutputStream outputStream, e[] eVarArr) throws IOException {
        f.r(outputStream, eVarArr.length);
        for (e eVar : eVarArr) {
            int size = eVar.i.size() * 4;
            String strJ = j(eVar.a, eVar.b, r.d);
            f.p(outputStream, f.k(strJ));
            f.p(outputStream, eVar.h.length);
            f.q(outputStream, size);
            f.q(outputStream, eVar.c);
            f.n(outputStream, strJ);
            Iterator it = eVar.i.keySet().iterator();
            while (it.hasNext()) {
                f.p(outputStream, ((Integer) it.next()).intValue());
                f.p(outputStream, 0);
            }
            for (int i : eVar.h) {
                f.p(outputStream, i);
            }
        }
    }

    private static void L(OutputStream outputStream, e[] eVarArr) throws IOException {
        byte[] bArrB = b(eVarArr, r.c);
        f.r(outputStream, eVarArr.length);
        f.m(outputStream, bArrB);
    }

    private static void M(OutputStream outputStream, e[] eVarArr) throws IOException {
        byte[] bArrB = b(eVarArr, r.b);
        f.r(outputStream, eVarArr.length);
        f.m(outputStream, bArrB);
    }

    private static void N(OutputStream outputStream, e[] eVarArr) throws IOException {
        O(outputStream, eVarArr);
    }

    private static void O(OutputStream outputStream, e[] eVarArr) throws IOException {
        int length;
        ArrayList arrayList = new ArrayList(3);
        ArrayList arrayList2 = new ArrayList(3);
        arrayList.add(D(eVarArr));
        arrayList.add(c(eVarArr));
        arrayList.add(d(eVarArr));
        long length2 = ((long) r.a.length) + ((long) a.length) + 4 + ((long) (arrayList.size() * 16));
        f.q(outputStream, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            s sVar = (s) arrayList.get(i);
            f.q(outputStream, sVar.a.b());
            f.q(outputStream, length2);
            if (sVar.d) {
                byte[] bArr = sVar.c;
                long length3 = bArr.length;
                byte[] bArrB = f.b(bArr);
                arrayList2.add(bArrB);
                f.q(outputStream, bArrB.length);
                f.q(outputStream, length3);
                length = bArrB.length;
            } else {
                arrayList2.add(sVar.c);
                f.q(outputStream, sVar.c.length);
                f.q(outputStream, 0L);
                length = sVar.c.length;
            }
            length2 += (long) length;
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            outputStream.write((byte[]) arrayList2.get(i2));
        }
    }

    private static int a(e eVar) {
        Iterator it = eVar.i.entrySet().iterator();
        int iIntValue = 0;
        while (it.hasNext()) {
            iIntValue |= ((Integer) ((Map.Entry) it.next()).getValue()).intValue();
        }
        return iIntValue;
    }

    private static byte[] b(e[] eVarArr, byte[] bArr) throws IOException {
        int i = 0;
        int iK = 0;
        for (e eVar : eVarArr) {
            iK += f.k(j(eVar.a, eVar.b, bArr)) + 16 + (eVar.e * 2) + eVar.f + k(eVar.g);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(iK);
        if (Arrays.equals(bArr, r.c)) {
            int length = eVarArr.length;
            while (i < length) {
                e eVar2 = eVarArr[i];
                G(byteArrayOutputStream, eVar2, j(eVar2.a, eVar2.b, bArr));
                F(byteArrayOutputStream, eVar2);
                i++;
            }
        } else {
            for (e eVar3 : eVarArr) {
                G(byteArrayOutputStream, eVar3, j(eVar3.a, eVar3.b, bArr));
            }
            int length2 = eVarArr.length;
            while (i < length2) {
                F(byteArrayOutputStream, eVarArr[i]);
                i++;
            }
        }
        if (byteArrayOutputStream.size() == iK) {
            return byteArrayOutputStream.toByteArray();
        }
        throw f.c("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + iK);
    }

    private static s c(e[] eVarArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        for (int i2 = 0; i2 < eVarArr.length; i2++) {
            try {
                e eVar = eVarArr[i2];
                f.p(byteArrayOutputStream, i2);
                f.p(byteArrayOutputStream, eVar.e);
                i = i + 2 + 2 + (eVar.e * 2);
                C(byteArrayOutputStream, eVar);
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i == byteArray.length) {
            s sVar = new s(g.CLASSES, i, byteArray, true);
            byteArrayOutputStream.close();
            return sVar;
        }
        throw f.c("Expected size " + i + ", does not match actual size " + byteArray.length);
    }

    private static s d(e[] eVarArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        for (int i2 = 0; i2 < eVarArr.length; i2++) {
            try {
                e eVar = eVarArr[i2];
                int iA = a(eVar);
                byte[] bArrE = e(eVar);
                byte[] bArrF = f(eVar);
                f.p(byteArrayOutputStream, i2);
                int length = bArrE.length + 2 + bArrF.length;
                f.q(byteArrayOutputStream, length);
                f.p(byteArrayOutputStream, iA);
                byteArrayOutputStream.write(bArrE);
                byteArrayOutputStream.write(bArrF);
                i = i + 2 + 4 + length;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i == byteArray.length) {
            s sVar = new s(g.METHODS, i, byteArray, true);
            byteArrayOutputStream.close();
            return sVar;
        }
        throw f.c("Expected size " + i + ", does not match actual size " + byteArray.length);
    }

    private static byte[] e(e eVar) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            H(byteArrayOutputStream, eVar);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static byte[] f(e eVar) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            I(byteArrayOutputStream, eVar);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static String g(String str, String str2) {
        if ("!".equals(str2)) {
            return str.replace(":", "!");
        }
        return ":".equals(str2) ? str.replace("!", ":") : str;
    }

    private static String h(String str) {
        int iIndexOf = str.indexOf("!");
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(":");
        }
        return iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
    }

    private static e i(e[] eVarArr, String str) {
        if (eVarArr.length <= 0) {
            return null;
        }
        String strH = h(str);
        for (int i = 0; i < eVarArr.length; i++) {
            if (eVarArr[i].b.equals(strH)) {
                return eVarArr[i];
            }
        }
        return null;
    }

    private static String j(String str, String str2, byte[] bArr) {
        String strA = r.a(bArr);
        if (str.length() <= 0) {
            return g(str2, strA);
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (str2.contains("!") || str2.contains(":")) {
            return g(str2, strA);
        }
        if (str2.endsWith(".apk")) {
            return str2;
        }
        return str + r.a(bArr) + str2;
    }

    private static int k(int i) {
        return y(i * 2) / 8;
    }

    private static int l(int i, int i2, int i3) {
        if (i == 1) {
            throw f.c("HOT methods are not stored in the bitmap");
        }
        if (i == 2) {
            return i2;
        }
        if (i == 4) {
            return i2 + i3;
        }
        throw f.c("Unexpected flag: " + i);
    }

    private static int[] m(InputStream inputStream, int i) {
        int[] iArr = new int[i];
        int iH = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iH += f.h(inputStream);
            iArr[i2] = iH;
        }
        return iArr;
    }

    private static int n(BitSet bitSet, int i, int i2) {
        int i3 = bitSet.get(l(2, i, i2)) ? 2 : 0;
        return bitSet.get(l(4, i, i2)) ? i3 | 4 : i3;
    }

    static byte[] o(InputStream inputStream, byte[] bArr) {
        if (Arrays.equals(bArr, f.d(inputStream, bArr.length))) {
            return f.d(inputStream, r.b.length);
        }
        throw f.c("Invalid magic");
    }

    private static void p(InputStream inputStream, e eVar) {
        int iAvailable = inputStream.available() - eVar.f;
        int iH = 0;
        while (inputStream.available() > iAvailable) {
            iH += f.h(inputStream);
            eVar.i.put(Integer.valueOf(iH), 1);
            for (int iH2 = f.h(inputStream); iH2 > 0; iH2--) {
                A(inputStream);
            }
        }
        if (inputStream.available() != iAvailable) {
            throw f.c("Read too much data during profile line parse");
        }
    }

    static e[] q(InputStream inputStream, byte[] bArr, byte[] bArr2, e[] eVarArr) {
        if (Arrays.equals(bArr, r.f)) {
            if (Arrays.equals(r.a, bArr2)) {
                throw f.c("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
            }
            return r(inputStream, bArr, eVarArr);
        }
        if (Arrays.equals(bArr, r.g)) {
            return t(inputStream, bArr2, eVarArr);
        }
        throw f.c("Unsupported meta version");
    }

    static e[] r(InputStream inputStream, byte[] bArr, e[] eVarArr) throws IOException {
        if (!Arrays.equals(bArr, r.f)) {
            throw f.c("Unsupported meta version");
        }
        int iJ = f.j(inputStream);
        byte[] bArrE = f.e(inputStream, (int) f.i(inputStream), (int) f.i(inputStream));
        if (inputStream.read() > 0) {
            throw f.c("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrE);
        try {
            e[] eVarArrS = s(byteArrayInputStream, iJ, eVarArr);
            byteArrayInputStream.close();
            return eVarArrS;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static e[] s(InputStream inputStream, int i, e[] eVarArr) {
        if (inputStream.available() == 0) {
            return new e[0];
        }
        if (i != eVarArr.length) {
            throw f.c("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i];
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int iH = f.h(inputStream);
            iArr[i2] = f.h(inputStream);
            strArr[i2] = f.f(inputStream, iH);
        }
        for (int i3 = 0; i3 < i; i3++) {
            e eVar = eVarArr[i3];
            if (!eVar.b.equals(strArr[i3])) {
                throw f.c("Order of dexfiles in metadata did not match baseline");
            }
            int i4 = iArr[i3];
            eVar.e = i4;
            eVar.h = m(inputStream, i4);
        }
        return eVarArr;
    }

    static e[] t(InputStream inputStream, byte[] bArr, e[] eVarArr) throws IOException {
        int iH = f.h(inputStream);
        byte[] bArrE = f.e(inputStream, (int) f.i(inputStream), (int) f.i(inputStream));
        if (inputStream.read() > 0) {
            throw f.c("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrE);
        try {
            e[] eVarArrU = u(byteArrayInputStream, bArr, iH, eVarArr);
            byteArrayInputStream.close();
            return eVarArrU;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static e[] u(InputStream inputStream, byte[] bArr, int i, e[] eVarArr) {
        if (inputStream.available() == 0) {
            return new e[0];
        }
        if (i != eVarArr.length) {
            throw f.c("Mismatched number of dex files found in metadata");
        }
        for (int i2 = 0; i2 < i; i2++) {
            f.h(inputStream);
            String strF = f.f(inputStream, f.h(inputStream));
            long jI = f.i(inputStream);
            int iH = f.h(inputStream);
            e eVarI = i(eVarArr, strF);
            if (eVarI == null) {
                throw f.c("Missing profile key: " + strF);
            }
            eVarI.d = jI;
            int[] iArrM = m(inputStream, iH);
            if (Arrays.equals(bArr, r.e)) {
                eVarI.e = iH;
                eVarI.h = iArrM;
            }
        }
        return eVarArr;
    }

    private static void v(InputStream inputStream, e eVar) {
        BitSet bitSetValueOf = BitSet.valueOf(f.d(inputStream, f.a(eVar.g * 2)));
        int i = 0;
        while (true) {
            int i2 = eVar.g;
            if (i >= i2) {
                return;
            }
            int iN = n(bitSetValueOf, i, i2);
            if (iN != 0) {
                Integer num = (Integer) eVar.i.get(Integer.valueOf(i));
                if (num == null) {
                    num = 0;
                }
                eVar.i.put(Integer.valueOf(i), Integer.valueOf(iN | num.intValue()));
            }
            i++;
        }
    }

    static e[] w(InputStream inputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, r.b)) {
            throw f.c("Unsupported version");
        }
        int iJ = f.j(inputStream);
        byte[] bArrE = f.e(inputStream, (int) f.i(inputStream), (int) f.i(inputStream));
        if (inputStream.read() > 0) {
            throw f.c("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrE);
        try {
            e[] eVarArrX = x(byteArrayInputStream, str, iJ);
            byteArrayInputStream.close();
            return eVarArrX;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static e[] x(InputStream inputStream, String str, int i) {
        if (inputStream.available() == 0) {
            return new e[0];
        }
        e[] eVarArr = new e[i];
        for (int i2 = 0; i2 < i; i2++) {
            int iH = f.h(inputStream);
            int iH2 = f.h(inputStream);
            long jI = f.i(inputStream);
            eVarArr[i2] = new e(str, f.f(inputStream, iH), f.i(inputStream), 0L, iH2, (int) jI, (int) f.i(inputStream), new int[iH2], new TreeMap());
        }
        for (int i3 = 0; i3 < i; i3++) {
            e eVar = eVarArr[i3];
            p(inputStream, eVar);
            eVar.h = m(inputStream, eVar.e);
            v(inputStream, eVar);
        }
        return eVarArr;
    }

    private static int y(int i) {
        return ((i + 8) - 1) & (-8);
    }

    private static void z(byte[] bArr, int i, int i2, e eVar) {
        int iL = l(i, i2, eVar.g);
        int i3 = iL / 8;
        bArr[i3] = (byte) ((1 << (iL % 8)) | bArr[i3]);
    }
}
