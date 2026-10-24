package androidx.room.util;

import android.util.Log;
import androidx.room.Room;
import fa.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.m;
import pa.l;

/* JADX INFO: loaded from: classes.dex */
public final class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static final void appendPlaceholders(StringBuilder sb, int i) {
        m.g(sb, "builder");
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("?");
            if (i2 < i - 1) {
                sb.append(",");
            }
        }
    }

    public static /* synthetic */ void getEMPTY_STRING_ARRAY$annotations() {
    }

    public static final String joinIntoString(List<Integer> list) {
        if (list != null) {
            return p.Q(list, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (l) null, 62, (Object) null);
        }
        return null;
    }

    public static final StringBuilder newStringBuilder() {
        return new StringBuilder();
    }

    public static final List<Integer> splitToIntList(String str) {
        List listB0;
        Integer numValueOf;
        if (str == null || (listB0 = ya.l.b0(str, new char[]{','}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = listB0.iterator();
        while (it.hasNext()) {
            try {
                numValueOf = Integer.valueOf(Integer.parseInt((String) it.next()));
            } catch (NumberFormatException e) {
                Log.e(Room.LOG_TAG, "Malformed integer list", e);
                numValueOf = null;
            }
            if (numValueOf != null) {
                arrayList.add(numValueOf);
            }
        }
        return arrayList;
    }
}
