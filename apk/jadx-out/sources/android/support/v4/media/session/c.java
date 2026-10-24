package android.support.v4.media.session;

import android.content.Context;
import android.media.session.MediaSession;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static /* synthetic */ MediaSession a(Context context, String str, Bundle bundle) {
        return new MediaSession(context, str, bundle);
    }
}
