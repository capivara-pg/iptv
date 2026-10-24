package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l0.i;
import s.j;

/* JADX INFO: loaded from: classes.dex */
public class MediaSessionCompat {
    static int d;
    private final c a;
    private final MediaControllerCompat b;
    private final ArrayList c;

    static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();
        ResultReceiver a;

        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        }

        ResultReceiverWrapper(Parcel parcel) {
            this.a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.a.writeToParcel(parcel, i);
        }
    }

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();
        private final Object a;
        private final Object b;
        private android.support.v4.media.session.b c;
        private y0.b d;

        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Token createFromParcel(Parcel parcel) {
                return new Token(parcel.readParcelable(null));
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Token[] newArray(int i) {
                return new Token[i];
            }
        }

        Token(Object obj) {
            this(obj, null, null);
        }

        public android.support.v4.media.session.b a() {
            android.support.v4.media.session.b bVar;
            synchronized (this.a) {
                bVar = this.c;
            }
            return bVar;
        }

        public y0.b b() {
            y0.b bVar;
            synchronized (this.a) {
                bVar = this.d;
            }
            return bVar;
        }

        public Object c() {
            return this.b;
        }

        public void d(android.support.v4.media.session.b bVar) {
            synchronized (this.a) {
                this.c = bVar;
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void e(y0.b bVar) {
            synchronized (this.a) {
                this.d = bVar;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.b;
            if (obj2 == null) {
                return token.b == null;
            }
            Object obj3 = token.b;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        public int hashCode() {
            Object obj = this.b;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable((Parcelable) this.b, i);
        }

        Token(Object obj, android.support.v4.media.session.b bVar, y0.b bVar2) {
            this.a = new Object();
            this.b = obj;
            this.c = bVar;
            this.d = bVar2;
        }
    }

    class a extends b {
        a() {
        }
    }

    public static abstract class b {
        a mCallbackHandler;
        private boolean mMediaPlayPausePendingOnHandler;
        final Object mLock = new Object();
        final MediaSession.Callback mCallbackFwk = new C0001b();
        WeakReference<c> mSessionImpl = new WeakReference<>(null);

        private class a extends Handler {
            a(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                c cVar;
                b bVar;
                a aVar;
                if (message.what == 1) {
                    synchronized (b.this.mLock) {
                        cVar = b.this.mSessionImpl.get();
                        bVar = b.this;
                        aVar = bVar.mCallbackHandler;
                    }
                    if (cVar == null || bVar != cVar.k() || aVar == null) {
                        return;
                    }
                    cVar.f((l0.a) message.obj);
                    b.this.handleMediaPlayPauseIfPendingOnHandler(cVar, aVar);
                    cVar.f(null);
                }
            }
        }

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaSessionCompat$b$b, reason: collision with other inner class name */
        private class C0001b extends MediaSession.Callback {
            C0001b() {
            }

            private void a(c cVar) {
                cVar.f(null);
            }

            private d b() {
                d dVar;
                synchronized (b.this.mLock) {
                    dVar = (d) b.this.mSessionImpl.get();
                }
                if (b.this == dVar.k()) {
                    return dVar;
                }
                return null;
            }

            private void c(c cVar) {
                if (Build.VERSION.SDK_INT >= 28) {
                    return;
                }
                String strH = cVar.h();
                if (TextUtils.isEmpty(strH)) {
                    strH = "android.media.session.MediaController";
                }
                cVar.f(new l0.a(strH, -1, -1));
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                try {
                    QueueItem queueItem = null;
                    IBinder iBinderAsBinder = null;
                    queueItem = null;
                    if (str.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                        Bundle bundle2 = new Bundle();
                        Token tokenG = dVarB.g();
                        android.support.v4.media.session.b bVarA = tokenG.a();
                        if (bVarA != null) {
                            iBinderAsBinder = bVarA.asBinder();
                        }
                        j.b(bundle2, "android.support.v4.media.session.EXTRA_BINDER", iBinderAsBinder);
                        y0.a.c(bundle2, "android.support.v4.media.session.SESSION_TOKEN2", tokenG.b());
                        resultReceiver.send(0, bundle2);
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                        b.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                        b.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                    } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                        b.this.onRemoveQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (!str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                        b.this.onCommand(str, bundle, resultReceiver);
                    } else if (dVarB.h != null) {
                        int i = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                        if (i >= 0 && i < dVarB.h.size()) {
                            queueItem = (QueueItem) dVarB.h.get(i);
                        }
                        if (queueItem != null) {
                            b.this.onRemoveQueueItem(queueItem.c());
                        }
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                }
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCustomAction(String str, Bundle bundle) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                try {
                    if (str.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                        Uri uri = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                        Bundle bundle2 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.b(bundle2);
                        b.this.onPlayFromUri(uri, bundle2);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                        b.this.onPrepare();
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                        String string = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                        Bundle bundle3 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.b(bundle3);
                        b.this.onPrepareFromMediaId(string, bundle3);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                        String string2 = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                        Bundle bundle4 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.b(bundle4);
                        b.this.onPrepareFromSearch(string2, bundle4);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                        Uri uri2 = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                        Bundle bundle5 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.b(bundle5);
                        b.this.onPrepareFromUri(uri2, bundle5);
                    } else if (str.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                        b.this.onSetCaptioningEnabled(bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED"));
                    } else if (str.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                        b.this.onSetRepeatMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
                    } else if (str.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                        b.this.onSetShuffleMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
                    } else if (str.equals("android.support.v4.media.session.action.SET_RATING")) {
                        RatingCompat ratingCompat = (RatingCompat) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
                        Bundle bundle6 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.b(bundle6);
                        b.this.onSetRating(ratingCompat, bundle6);
                    } else if (str.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED")) {
                        b.this.onSetPlaybackSpeed(bundle.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f));
                    } else {
                        b.this.onCustomAction(str, bundle);
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaSessionCompat", "Could not unparcel the data.");
                }
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onFastForward() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onFastForward();
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public boolean onMediaButtonEvent(Intent intent) {
                d dVarB = b();
                if (dVarB == null) {
                    return false;
                }
                c(dVarB);
                boolean zOnMediaButtonEvent = b.this.onMediaButtonEvent(intent);
                a(dVarB);
                return zOnMediaButtonEvent || super.onMediaButtonEvent(intent);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPause() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onPause();
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlay() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onPlay();
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromMediaId(String str, Bundle bundle) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                b.this.onPlayFromMediaId(str, bundle);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromSearch(String str, Bundle bundle) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                b.this.onPlayFromSearch(str, bundle);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromUri(Uri uri, Bundle bundle) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                b.this.onPlayFromUri(uri, bundle);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepare() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onPrepare();
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromMediaId(String str, Bundle bundle) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                b.this.onPrepareFromMediaId(str, bundle);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromSearch(String str, Bundle bundle) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                b.this.onPrepareFromSearch(str, bundle);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                MediaSessionCompat.b(bundle);
                c(dVarB);
                b.this.onPrepareFromUri(uri, bundle);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onRewind() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onRewind();
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSeekTo(long j) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onSeekTo(j);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetPlaybackSpeed(float f) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onSetPlaybackSpeed(f);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetRating(Rating rating) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onSetRating(RatingCompat.a(rating));
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToNext() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onSkipToNext();
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToPrevious() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onSkipToPrevious();
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToQueueItem(long j) {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onSkipToQueueItem(j);
                a(dVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onStop() {
                d dVarB = b();
                if (dVarB == null) {
                    return;
                }
                c(dVarB);
                b.this.onStop();
                a(dVarB);
            }
        }

        void handleMediaPlayPauseIfPendingOnHandler(c cVar, Handler handler) {
            if (this.mMediaPlayPausePendingOnHandler) {
                this.mMediaPlayPausePendingOnHandler = false;
                handler.removeMessages(1);
                PlaybackStateCompat playbackStateCompatC = cVar.c();
                long jB = playbackStateCompatC == null ? 0L : playbackStateCompatC.b();
                boolean z = playbackStateCompatC != null && playbackStateCompatC.g() == 3;
                boolean z2 = (516 & jB) != 0;
                boolean z3 = (jB & 514) != 0;
                if (z && z3) {
                    onPause();
                } else {
                    if (z || !z2) {
                        return;
                    }
                    onPlay();
                }
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            c cVar;
            a aVar;
            KeyEvent keyEvent;
            if (Build.VERSION.SDK_INT >= 27) {
                return false;
            }
            synchronized (this.mLock) {
                cVar = this.mSessionImpl.get();
                aVar = this.mCallbackHandler;
            }
            if (cVar == null || aVar == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
                return false;
            }
            l0.a aVarO = cVar.o();
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 79 && keyCode != 85) {
                handleMediaPlayPauseIfPendingOnHandler(cVar, aVar);
                return false;
            }
            if (keyEvent.getRepeatCount() != 0) {
                handleMediaPlayPauseIfPendingOnHandler(cVar, aVar);
            } else if (this.mMediaPlayPausePendingOnHandler) {
                aVar.removeMessages(1);
                this.mMediaPlayPausePendingOnHandler = false;
                PlaybackStateCompat playbackStateCompatC = cVar.c();
                if (((playbackStateCompatC == null ? 0L : playbackStateCompatC.b()) & 32) != 0) {
                    onSkipToNext();
                }
            } else {
                this.mMediaPlayPausePendingOnHandler = true;
                aVar.sendMessageDelayed(aVar.obtainMessage(1, aVarO), ViewConfiguration.getDoubleTapTimeout());
            }
            return true;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int i) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long j) {
        }

        public void onSetCaptioningEnabled(boolean z) {
        }

        public void onSetPlaybackSpeed(float f) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRepeatMode(int i) {
        }

        public void onSetShuffleMode(int i) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long j) {
        }

        public void onStop() {
        }

        void setSessionImpl(c cVar, Handler handler) {
            synchronized (this.mLock) {
                this.mSessionImpl = new WeakReference<>(cVar);
                a aVar = this.mCallbackHandler;
                a aVar2 = null;
                if (aVar != null) {
                    aVar.removeCallbacksAndMessages(null);
                }
                if (cVar != null && handler != null) {
                    aVar2 = new a(handler.getLooper());
                }
                this.mCallbackHandler = aVar2;
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }
    }

    interface c {
        void a(b bVar, Handler handler);

        void b(i iVar);

        PlaybackStateCompat c();

        void d(MediaMetadataCompat mediaMetadataCompat);

        void e(PlaybackStateCompat playbackStateCompat);

        void f(l0.a aVar);

        Token g();

        String h();

        void i(PendingIntent pendingIntent);

        boolean isActive();

        void j(int i);

        b k();

        void l(PendingIntent pendingIntent);

        Object m();

        void n(boolean z);

        l0.a o();

        void release();

        void setExtras(Bundle bundle);
    }

    static class d implements c {
        final MediaSession a;
        final Token b;
        Bundle d;
        PlaybackStateCompat g;
        List h;
        MediaMetadataCompat i;
        int j;
        boolean k;
        int l;
        int m;
        b n;
        l0.a o;
        final Object c = new Object();
        boolean e = false;
        final RemoteCallbackList f = new RemoteCallbackList();

        class a extends android.support.v4.media.session.b.a {
            a() {
            }

            @Override // android.support.v4.media.session.b
            public void A(long j) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void B(boolean z) {
            }

            @Override // android.support.v4.media.session.b
            public void C(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public String D() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void F(android.support.v4.media.session.a aVar) {
                if (d.this.e) {
                    return;
                }
                d.this.f.register(aVar, new l0.a("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid()));
            }

            @Override // android.support.v4.media.session.b
            public void G(RatingCompat ratingCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void H(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void I(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean J() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void K(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void L(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void M() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public CharSequence O() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void P(android.support.v4.media.session.a aVar) {
                d.this.f.unregister(aVar);
            }

            @Override // android.support.v4.media.session.b
            public void R() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void V(float f) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void Y(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void Z(boolean z) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void b0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat c() {
                d dVar = d.this;
                return MediaSessionCompat.f(dVar.g, dVar.i);
            }

            @Override // android.support.v4.media.session.b
            public void d(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public List d0() {
                return null;
            }

            @Override // android.support.v4.media.session.b
            public boolean e() {
                return false;
            }

            @Override // android.support.v4.media.session.b
            public void e0(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void f(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public long f0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent g() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int g0() {
                return d.this.l;
            }

            @Override // android.support.v4.media.session.b
            public Bundle getExtras() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public String getTag() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int h() {
                return d.this.j;
            }

            @Override // android.support.v4.media.session.b
            public ParcelableVolumeInfo h0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void k(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public Bundle l() {
                if (d.this.d == null) {
                    return null;
                }
                return new Bundle(d.this.d);
            }

            @Override // android.support.v4.media.session.b
            public void m(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void n(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void next() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void o(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean p(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void pause() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void previous() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void q(RatingCompat ratingCompat, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void s(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void seekTo(long j) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void stop() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int u() {
                return d.this.m;
            }

            @Override // android.support.v4.media.session.b
            public void v(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean w() {
                return d.this.k;
            }

            @Override // android.support.v4.media.session.b
            public void y(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void z() {
                throw new AssertionError();
            }
        }

        d(MediaSession mediaSession, y0.b bVar, Bundle bundle) {
            this.a = mediaSession;
            this.b = new Token(mediaSession.getSessionToken(), new a(), bVar);
            this.d = bundle;
            p(3);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void a(b bVar, Handler handler) {
            synchronized (this.c) {
                this.n = bVar;
                this.a.setCallback(bVar == null ? null : bVar.mCallbackFwk, handler);
                if (bVar != null) {
                    bVar.setSessionImpl(this, handler);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void b(i iVar) {
            this.a.setPlaybackToRemote((VolumeProvider) iVar.a());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public PlaybackStateCompat c() {
            return this.g;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            this.i = mediaMetadataCompat;
            this.a.setMetadata(mediaMetadataCompat == null ? null : (MediaMetadata) mediaMetadataCompat.f());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void e(PlaybackStateCompat playbackStateCompat) {
            this.g = playbackStateCompat;
            for (int iBeginBroadcast = this.f.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.f.getBroadcastItem(iBeginBroadcast)).l0(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
            this.a.setPlaybackState(playbackStateCompat == null ? null : (PlaybackState) playbackStateCompat.e());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void f(l0.a aVar) {
            synchronized (this.c) {
                this.o = aVar;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Token g() {
            return this.b;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public String h() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            try {
                return (String) this.a.getClass().getMethod("getCallingPackage", new Class[0]).invoke(this.a, new Object[0]);
            } catch (Exception e) {
                Log.e("MediaSessionCompat", "Cannot execute MediaSession.getCallingPackage()", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void i(PendingIntent pendingIntent) {
            this.a.setSessionActivity(pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public boolean isActive() {
            return this.a.isActive();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void j(int i) {
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(i);
            this.a.setPlaybackToLocal(builder.build());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public b k() {
            b bVar;
            synchronized (this.c) {
                bVar = this.n;
            }
            return bVar;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void l(PendingIntent pendingIntent) {
            this.a.setMediaButtonReceiver(pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Object m() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void n(boolean z) {
            this.a.setActive(z);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public l0.a o() {
            l0.a aVar;
            synchronized (this.c) {
                aVar = this.o;
            }
            return aVar;
        }

        public void p(int i) {
            this.a.setFlags(i | 1 | 2);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void release() {
            this.e = true;
            this.f.kill();
            this.a.setCallback(null);
            this.a.release();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void setExtras(Bundle bundle) {
            this.a.setExtras(bundle);
        }
    }

    static class e extends d {
        e(MediaSession mediaSession, y0.b bVar, Bundle bundle) {
            super(mediaSession, bVar, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d, android.support.v4.media.session.MediaSessionCompat.c
        public void f(l0.a aVar) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d, android.support.v4.media.session.MediaSessionCompat.c
        public final l0.a o() {
            return new l0.a(this.a.getCurrentControllerInfo());
        }
    }

    static class f extends e {
        f(MediaSession mediaSession, y0.b bVar, Bundle bundle) {
            super(mediaSession, bVar, bundle);
        }
    }

    public interface g {
        void a();
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this(context, str, componentName, pendingIntent, null);
    }

    private MediaSession a(Context context, String str, Bundle bundle) {
        return Build.VERSION.SDK_INT >= 29 ? android.support.v4.media.session.c.a(context, str, bundle) : new MediaSession(context, str);
    }

    public static void b(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    static PlaybackStateCompat f(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        long j;
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        long jE = -1;
        if (playbackStateCompat.f() == -1) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.g() != 3 && playbackStateCompat.g() != 4 && playbackStateCompat.g() != 5) {
            return playbackStateCompat;
        }
        long jC = playbackStateCompat.c();
        if (jC <= 0) {
            return playbackStateCompat;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jD = ((long) (playbackStateCompat.d() * (jElapsedRealtime - jC))) + playbackStateCompat.f();
        if (mediaMetadataCompat != null && mediaMetadataCompat.a("android.media.metadata.DURATION")) {
            jE = mediaMetadataCompat.e("android.media.metadata.DURATION");
        }
        if (jE < 0 || jD <= jE) {
            j = jD < 0 ? 0L : jD;
        } else {
            j = jE;
        }
        return new PlaybackStateCompat.b(playbackStateCompat).e(playbackStateCompat.g(), j, playbackStateCompat.d(), jElapsedRealtime).b();
    }

    public static Bundle r(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        b(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public MediaControllerCompat c() {
        return this.b;
    }

    public Object d() {
        return this.a.m();
    }

    public Token e() {
        return this.a.g();
    }

    public boolean g() {
        return this.a.isActive();
    }

    public void h() {
        this.a.release();
    }

    public void i(boolean z) {
        this.a.n(z);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((g) it.next()).a();
        }
    }

    public void j(b bVar) {
        k(bVar, null);
    }

    public void k(b bVar, Handler handler) {
        if (bVar == null) {
            this.a.a(null, null);
            return;
        }
        c cVar = this.a;
        if (handler == null) {
            handler = new Handler();
        }
        cVar.a(bVar, handler);
    }

    public void l(Bundle bundle) {
        this.a.setExtras(bundle);
    }

    public void m(MediaMetadataCompat mediaMetadataCompat) {
        this.a.d(mediaMetadataCompat);
    }

    public void n(PlaybackStateCompat playbackStateCompat) {
        this.a.e(playbackStateCompat);
    }

    public void o(int i) {
        this.a.j(i);
    }

    public void p(i iVar) {
        if (iVar == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.a.b(iVar);
    }

    public void q(PendingIntent pendingIntent) {
        this.a.i(pendingIntent);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        this(context, str, componentName, pendingIntent, bundle, null);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle, y0.b bVar) {
        this.c = new ArrayList();
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                componentName = componentName == null ? n0.a.a(context) : componentName;
                if (componentName != null && pendingIntent == null) {
                    Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.setComponent(componentName);
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                }
                int i = Build.VERSION.SDK_INT;
                MediaSession mediaSessionA = a(context, str, bundle);
                if (i >= 29) {
                    this.a = new f(mediaSessionA, bVar, bundle);
                } else if (i >= 28) {
                    this.a = new e(mediaSessionA, bVar, bundle);
                } else {
                    this.a = new d(mediaSessionA, bVar, bundle);
                }
                k(new a(), new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()));
                this.a.l(pendingIntent);
                this.b = new MediaControllerCompat(context, this);
                if (d == 0) {
                    d = (int) (TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics()) + 0.5f);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        throw new IllegalArgumentException("context must not be null");
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();
        private final MediaDescriptionCompat a;
        private final long b;
        private MediaSession.QueueItem c;

        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        }

        private QueueItem(MediaSession.QueueItem queueItem, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            if (j == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.a = mediaDescriptionCompat;
            this.b = j;
            this.c = queueItem;
        }

        public static QueueItem a(Object obj) {
            if (obj == null) {
                return null;
            }
            MediaSession.QueueItem queueItem = (MediaSession.QueueItem) obj;
            return new QueueItem(queueItem, MediaDescriptionCompat.a(queueItem.getDescription()), queueItem.getQueueId());
        }

        public static List b(List list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next()));
            }
            return arrayList;
        }

        public MediaDescriptionCompat c() {
            return this.a;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.a + ", Id=" + this.b + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.a.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
        }

        QueueItem(Parcel parcel) {
            this.a = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.b = parcel.readLong();
        }
    }
}
