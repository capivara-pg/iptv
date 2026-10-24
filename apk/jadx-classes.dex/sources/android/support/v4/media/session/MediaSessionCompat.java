package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
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
import androidx.appcompat.R$styleable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
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
        private r0.b d;

        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Token createFromParcel(Parcel parcel) {
                return new Token(Build.VERSION.SDK_INT >= 21 ? parcel.readParcelable(null) : parcel.readStrongBinder());
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

        public r0.b b() {
            r0.b bVar;
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

        public void e(r0.b bVar) {
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
            if (Build.VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.b, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.b);
            }
        }

        Token(Object obj, android.support.v4.media.session.b bVar, r0.b bVar2) {
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
        final MediaSession.Callback mCallbackFwk;
        a mCallbackHandler;
        final Object mLock = new Object();
        private boolean mMediaPlayPausePendingOnHandler;
        WeakReference<c> mSessionImpl;

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
                    cVar.f((e0.d) message.obj);
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

            private f b() {
                f fVar;
                synchronized (b.this.mLock) {
                    fVar = (f) b.this.mSessionImpl.get();
                }
                if (b.this == fVar.k()) {
                    return fVar;
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
                cVar.f(new e0.d(strH, -1, -1));
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                try {
                    QueueItem queueItem = null;
                    IBinder iBinderAsBinder = null;
                    queueItem = null;
                    if (str.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                        Bundle bundle2 = new Bundle();
                        Token tokenG = fVarB.g();
                        android.support.v4.media.session.b bVarA = tokenG.a();
                        if (bVarA != null) {
                            iBinderAsBinder = bVarA.asBinder();
                        }
                        n.o.b(bundle2, "android.support.v4.media.session.EXTRA_BINDER", iBinderAsBinder);
                        r0.a.c(bundle2, "android.support.v4.media.session.SESSION_TOKEN2", tokenG.b());
                        resultReceiver.send(0, bundle2);
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                        b.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                        b.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                    } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                        b.this.onRemoveQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (!str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                        b.this.onCommand(str, bundle, resultReceiver);
                    } else if (fVarB.h != null) {
                        int i = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                        if (i >= 0 && i < fVarB.h.size()) {
                            queueItem = (QueueItem) fVarB.h.get(i);
                        }
                        if (queueItem != null) {
                            b.this.onRemoveQueueItem(queueItem.c());
                        }
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                }
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCustomAction(String str, Bundle bundle) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                try {
                    if (str.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                        Uri uri = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                        Bundle bundle2 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle2);
                        b.this.onPlayFromUri(uri, bundle2);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                        b.this.onPrepare();
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                        String string = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                        Bundle bundle3 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle3);
                        b.this.onPrepareFromMediaId(string, bundle3);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                        String string2 = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                        Bundle bundle4 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle4);
                        b.this.onPrepareFromSearch(string2, bundle4);
                    } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                        Uri uri2 = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                        Bundle bundle5 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                        MediaSessionCompat.c(bundle5);
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
                        MediaSessionCompat.c(bundle6);
                        b.this.onSetRating(ratingCompat, bundle6);
                    } else if (str.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED")) {
                        b.this.onSetPlaybackSpeed(bundle.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f));
                    } else {
                        b.this.onCustomAction(str, bundle);
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaSessionCompat", "Could not unparcel the data.");
                }
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onFastForward() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onFastForward();
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public boolean onMediaButtonEvent(Intent intent) {
                f fVarB = b();
                if (fVarB == null) {
                    return false;
                }
                c(fVarB);
                boolean zOnMediaButtonEvent = b.this.onMediaButtonEvent(intent);
                a(fVarB);
                return zOnMediaButtonEvent || super.onMediaButtonEvent(intent);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPause() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onPause();
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlay() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onPlay();
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromMediaId(String str, Bundle bundle) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                b.this.onPlayFromMediaId(str, bundle);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromSearch(String str, Bundle bundle) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                b.this.onPlayFromSearch(str, bundle);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromUri(Uri uri, Bundle bundle) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                b.this.onPlayFromUri(uri, bundle);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepare() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onPrepare();
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromMediaId(String str, Bundle bundle) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                b.this.onPrepareFromMediaId(str, bundle);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromSearch(String str, Bundle bundle) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                b.this.onPrepareFromSearch(str, bundle);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                MediaSessionCompat.c(bundle);
                c(fVarB);
                b.this.onPrepareFromUri(uri, bundle);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onRewind() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onRewind();
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSeekTo(long j) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onSeekTo(j);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetPlaybackSpeed(float f) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onSetPlaybackSpeed(f);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetRating(Rating rating) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onSetRating(RatingCompat.a(rating));
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToNext() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onSkipToNext();
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToPrevious() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onSkipToPrevious();
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToQueueItem(long j) {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onSkipToQueueItem(j);
                a(fVarB);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onStop() {
                f fVarB = b();
                if (fVarB == null) {
                    return;
                }
                c(fVarB);
                b.this.onStop();
                a(fVarB);
            }
        }

        public b() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.mCallbackFwk = new C0001b();
            } else {
                this.mCallbackFwk = null;
            }
            this.mSessionImpl = new WeakReference<>(null);
        }

        void handleMediaPlayPauseIfPendingOnHandler(c cVar, Handler handler) {
            if (this.mMediaPlayPausePendingOnHandler) {
                this.mMediaPlayPausePendingOnHandler = false;
                handler.removeMessages(1);
                PlaybackStateCompat playbackStateCompatE = cVar.e();
                long jB = playbackStateCompatE == null ? 0L : playbackStateCompatE.b();
                boolean z = playbackStateCompatE != null && playbackStateCompatE.g() == 3;
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
            e0.d dVarO = cVar.o();
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
                PlaybackStateCompat playbackStateCompatE = cVar.e();
                if (((playbackStateCompatE == null ? 0L : playbackStateCompatE.b()) & 32) != 0) {
                    onSkipToNext();
                }
            } else {
                this.mMediaPlayPausePendingOnHandler = true;
                aVar.sendMessageDelayed(aVar.obtainMessage(1, dVarO), ViewConfiguration.getDoubleTapTimeout());
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

        void b(e0.l lVar);

        void c(MediaMetadataCompat mediaMetadataCompat);

        void d(PlaybackStateCompat playbackStateCompat);

        PlaybackStateCompat e();

        void f(e0.d dVar);

        Token g();

        String h();

        void i(PendingIntent pendingIntent);

        boolean isActive();

        void j(int i);

        b k();

        void l(PendingIntent pendingIntent);

        Object m();

        void n(boolean z);

        e0.d o();

        void release();

        void setExtras(Bundle bundle);
    }

    static class d extends i {
        private static boolean G = true;

        class a implements RemoteControlClient.OnPlaybackPositionUpdateListener {
            a() {
            }

            @Override // android.media.RemoteControlClient.OnPlaybackPositionUpdateListener
            public void onPlaybackPositionUpdate(long j) {
                d.this.u(18, -1, -1, Long.valueOf(j), null);
            }
        }

        d(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, r0.b bVar, Bundle bundle) {
            super(context, str, componentName, pendingIntent, bVar, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        void B(PlaybackStateCompat playbackStateCompat) {
            long jF = playbackStateCompat.f();
            float fD = playbackStateCompat.d();
            long jC = playbackStateCompat.c();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (playbackStateCompat.g() == 3) {
                long j = 0;
                if (jF > 0) {
                    if (jC > 0) {
                        j = jElapsedRealtime - jC;
                        if (fD > 0.0f && fD != 1.0f) {
                            j = (long) (j * fD);
                        }
                    }
                    jF += j;
                }
            }
            this.j.setPlaybackState(s(playbackStateCompat.g()), jF, fD);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        void D(PendingIntent pendingIntent, ComponentName componentName) {
            if (G) {
                this.i.unregisterMediaButtonEventReceiver(pendingIntent);
            } else {
                super.D(pendingIntent, componentName);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i, android.support.v4.media.session.MediaSessionCompat.c
        public void a(b bVar, Handler handler) {
            super.a(bVar, handler);
            if (bVar == null) {
                this.j.setPlaybackPositionUpdateListener(null);
            } else {
                this.j.setPlaybackPositionUpdateListener(new a());
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        int t(long j) {
            int iT = super.t(j);
            return (j & 256) != 0 ? iT | 256 : iT;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        void v(PendingIntent pendingIntent, ComponentName componentName) {
            if (G) {
                try {
                    this.i.registerMediaButtonEventReceiver(pendingIntent);
                } catch (NullPointerException unused) {
                    G = false;
                }
            }
            if (G) {
                return;
            }
            super.v(pendingIntent, componentName);
        }
    }

    static class e extends d {

        class a implements RemoteControlClient.OnMetadataUpdateListener {
            a() {
            }

            @Override // android.media.RemoteControlClient.OnMetadataUpdateListener
            public void onMetadataUpdate(int i, Object obj) {
                if (i == 268435457 && (obj instanceof Rating)) {
                    e.this.u(19, -1, -1, RatingCompat.a(obj), null);
                }
            }
        }

        e(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, r0.b bVar, Bundle bundle) {
            super(context, str, componentName, pendingIntent, bVar, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d, android.support.v4.media.session.MediaSessionCompat.i, android.support.v4.media.session.MediaSessionCompat.c
        public void a(b bVar, Handler handler) {
            super.a(bVar, handler);
            if (bVar == null) {
                this.j.setMetadataUpdateListener(null);
            } else {
                this.j.setMetadataUpdateListener(new a());
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        RemoteControlClient.MetadataEditor q(Bundle bundle) {
            RemoteControlClient.MetadataEditor metadataEditorQ = super.q(bundle);
            PlaybackStateCompat playbackStateCompat = this.t;
            if (((playbackStateCompat == null ? 0L : playbackStateCompat.b()) & 128) != 0) {
                metadataEditorQ.addEditableKey(268435457);
            }
            if (bundle == null) {
                return metadataEditorQ;
            }
            if (bundle.containsKey("android.media.metadata.YEAR")) {
                metadataEditorQ.putLong(8, bundle.getLong("android.media.metadata.YEAR"));
            }
            if (bundle.containsKey("android.media.metadata.RATING")) {
                metadataEditorQ.putObject(R$styleable.AppCompatTheme_textAppearanceListItem, (Object) bundle.getParcelable("android.media.metadata.RATING"));
            }
            if (bundle.containsKey("android.media.metadata.USER_RATING")) {
                metadataEditorQ.putObject(268435457, (Object) bundle.getParcelable("android.media.metadata.USER_RATING"));
            }
            return metadataEditorQ;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d, android.support.v4.media.session.MediaSessionCompat.i
        int t(long j) {
            int iT = super.t(j);
            return (j & 128) != 0 ? iT | 512 : iT;
        }
    }

    static class f implements c {
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
        e0.d o;
        final Object c = new Object();
        boolean e = false;
        final RemoteCallbackList f = new RemoteCallbackList();

        class a extends android.support.v4.media.session.b.a {
            a() {
            }

            @Override // android.support.v4.media.session.b
            public void A(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void B() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void C(long j) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void D(boolean z) {
            }

            @Override // android.support.v4.media.session.b
            public void E(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public String F() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void H(android.support.v4.media.session.a aVar) {
                if (f.this.e) {
                    return;
                }
                f.this.f.register(aVar, new e0.d("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid()));
            }

            @Override // android.support.v4.media.session.b
            public void I(RatingCompat ratingCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void J(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void K(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean L() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void M(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void N(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void O() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public CharSequence Q() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void R(android.support.v4.media.session.a aVar) {
                f.this.f.unregister(aVar);
            }

            @Override // android.support.v4.media.session.b
            public void S() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void U(float f) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void X(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void Y(boolean z) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void a0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public List b0() {
                return null;
            }

            @Override // android.support.v4.media.session.b
            public void c0(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public long d0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat e() {
                f fVar = f.this;
                return MediaSessionCompat.g(fVar.g, fVar.i);
            }

            @Override // android.support.v4.media.session.b
            public int e0() {
                return f.this.l;
            }

            @Override // android.support.v4.media.session.b
            public void f(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public ParcelableVolumeInfo f0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean g() {
                return false;
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
            public void h(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent i() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int j() {
                return f.this.j;
            }

            @Override // android.support.v4.media.session.b
            public void m(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public Bundle n() {
                if (f.this.d == null) {
                    return null;
                }
                return new Bundle(f.this.d);
            }

            @Override // android.support.v4.media.session.b
            public void next() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void o(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void p(String str, Bundle bundle) {
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
            public void q(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean r(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void s(RatingCompat ratingCompat, Bundle bundle) {
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
            public void u(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int w() {
                return f.this.m;
            }

            @Override // android.support.v4.media.session.b
            public void x(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean y() {
                return f.this.k;
            }
        }

        f(MediaSession mediaSession, r0.b bVar, Bundle bundle) {
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
        public void b(e0.l lVar) {
            this.a.setPlaybackToRemote(b0.a(lVar.d()));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void c(MediaMetadataCompat mediaMetadataCompat) {
            this.i = mediaMetadataCompat;
            this.a.setMetadata(mediaMetadataCompat == null ? null : android.support.v4.media.u.a(mediaMetadataCompat.g()));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void d(PlaybackStateCompat playbackStateCompat) {
            this.g = playbackStateCompat;
            for (int iBeginBroadcast = this.f.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.f.getBroadcastItem(iBeginBroadcast)).h0(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.f.finishBroadcast();
            this.a.setPlaybackState(playbackStateCompat == null ? null : v.a(playbackStateCompat.e()));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public PlaybackStateCompat e() {
            return this.g;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void f(e0.d dVar) {
            synchronized (this.c) {
                this.o = dVar;
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
        public e0.d o() {
            e0.d dVar;
            synchronized (this.c) {
                dVar = this.o;
            }
            return dVar;
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

    static class g extends f {
        g(MediaSession mediaSession, r0.b bVar, Bundle bundle) {
            super(mediaSession, bVar, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.f, android.support.v4.media.session.MediaSessionCompat.c
        public void f(e0.d dVar) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.f, android.support.v4.media.session.MediaSessionCompat.c
        public final e0.d o() {
            return new e0.d(this.a.getCurrentControllerInfo());
        }
    }

    static class h extends g {
        h(MediaSession mediaSession, r0.b bVar, Bundle bundle) {
            super(mediaSession, bVar, bundle);
        }
    }

    static class i implements c {
        int A;
        Bundle B;
        int C;
        int D;
        e0.l E;
        private final Context a;
        private final ComponentName b;
        private final PendingIntent c;
        private final c d;
        private final Token e;
        final String f;
        final Bundle g;
        final String h;
        final AudioManager i;
        final RemoteControlClient j;
        private d m;
        volatile b p;
        private e0.d q;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        MediaMetadataCompat f0s;
        PlaybackStateCompat t;
        PendingIntent u;
        List v;
        CharSequence w;
        int x;
        boolean y;
        int z;
        final Object k = new Object();
        final RemoteCallbackList l = new RemoteCallbackList();
        boolean n = false;
        boolean o = false;
        int r = 3;
        private e0.l.c F = new a();

        class a extends e0.l.c {
            a() {
            }

            public void a(e0.l lVar) {
                if (i.this.E != lVar) {
                    return;
                }
                i iVar = i.this;
                i.this.A(new ParcelableVolumeInfo(iVar.C, iVar.D, lVar.c(), lVar.b(), lVar.a()));
            }
        }

        private static final class b {
            public final String a;
            public final Bundle b;
            public final ResultReceiver c;

            public b(String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.a = str;
                this.b = bundle;
                this.c = resultReceiver;
            }
        }

        class c extends android.support.v4.media.session.b.a {
            c() {
            }

            @Override // android.support.v4.media.session.b
            public void A(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                m0(1, new b(str, bundle, resultReceiverWrapper == null ? null : resultReceiverWrapper.a));
            }

            @Override // android.support.v4.media.session.b
            public void B() {
                k0(17);
            }

            @Override // android.support.v4.media.session.b
            public void C(long j) {
                m0(11, Long.valueOf(j));
            }

            @Override // android.support.v4.media.session.b
            public void D(boolean z) {
            }

            @Override // android.support.v4.media.session.b
            public void E(int i) {
                l0(30, i);
            }

            @Override // android.support.v4.media.session.b
            public String F() {
                return i.this.f;
            }

            @Override // android.support.v4.media.session.b
            public void H(android.support.v4.media.session.a aVar) {
                if (i.this.n) {
                    try {
                        aVar.t();
                    } catch (Exception unused) {
                    }
                } else {
                    i.this.l.register(aVar, new e0.d(i.this.r(Binder.getCallingUid()), Binder.getCallingPid(), Binder.getCallingUid()));
                }
            }

            @Override // android.support.v4.media.session.b
            public void I(RatingCompat ratingCompat) {
                m0(19, ratingCompat);
            }

            @Override // android.support.v4.media.session.b
            public void J(int i, int i2, String str) {
                i.this.C(i, i2);
            }

            @Override // android.support.v4.media.session.b
            public void K(MediaDescriptionCompat mediaDescriptionCompat) {
                m0(27, mediaDescriptionCompat);
            }

            @Override // android.support.v4.media.session.b
            public boolean L() {
                return true;
            }

            @Override // android.support.v4.media.session.b
            public void M(MediaDescriptionCompat mediaDescriptionCompat) {
                m0(25, mediaDescriptionCompat);
            }

            @Override // android.support.v4.media.session.b
            public void N(String str, Bundle bundle) {
                o0(5, str, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void O() {
                k0(3);
            }

            @Override // android.support.v4.media.session.b
            public CharSequence Q() {
                return i.this.w;
            }

            @Override // android.support.v4.media.session.b
            public void R(android.support.v4.media.session.a aVar) {
                i.this.l.unregister(aVar);
            }

            @Override // android.support.v4.media.session.b
            public void S() {
                k0(16);
            }

            @Override // android.support.v4.media.session.b
            public void U(float f) {
                m0(32, Float.valueOf(f));
            }

            @Override // android.support.v4.media.session.b
            public void X(int i, int i2, String str) {
                i.this.p(i, i2);
            }

            @Override // android.support.v4.media.session.b
            public void Y(boolean z) {
                m0(29, Boolean.valueOf(z));
            }

            @Override // android.support.v4.media.session.b
            public void a0() {
                k0(7);
            }

            @Override // android.support.v4.media.session.b
            public List b0() {
                List list;
                synchronized (i.this.k) {
                    list = i.this.v;
                }
                return list;
            }

            @Override // android.support.v4.media.session.b
            public void c0(int i) {
                l0(23, i);
            }

            @Override // android.support.v4.media.session.b
            public long d0() {
                long j;
                synchronized (i.this.k) {
                    j = i.this.r;
                }
                return j;
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat e() {
                PlaybackStateCompat playbackStateCompat;
                MediaMetadataCompat mediaMetadataCompat;
                synchronized (i.this.k) {
                    i iVar = i.this;
                    playbackStateCompat = iVar.t;
                    mediaMetadataCompat = iVar.f0s;
                }
                return MediaSessionCompat.g(playbackStateCompat, mediaMetadataCompat);
            }

            @Override // android.support.v4.media.session.b
            public int e0() {
                return i.this.z;
            }

            @Override // android.support.v4.media.session.b
            public void f(String str, Bundle bundle) {
                o0(20, str, bundle);
            }

            @Override // android.support.v4.media.session.b
            public ParcelableVolumeInfo f0() {
                int i;
                int i2;
                int i3;
                int streamMaxVolume;
                int streamVolume;
                synchronized (i.this.k) {
                    i iVar = i.this;
                    i = iVar.C;
                    i2 = iVar.D;
                    e0.l lVar = iVar.E;
                    i3 = 2;
                    if (i == 2) {
                        int iC = lVar.c();
                        int iB = lVar.b();
                        streamVolume = lVar.a();
                        streamMaxVolume = iB;
                        i3 = iC;
                    } else {
                        streamMaxVolume = iVar.i.getStreamMaxVolume(i2);
                        streamVolume = i.this.i.getStreamVolume(i2);
                    }
                }
                return new ParcelableVolumeInfo(i, i2, i3, streamMaxVolume, streamVolume);
            }

            @Override // android.support.v4.media.session.b
            public boolean g() {
                return false;
            }

            @Override // android.support.v4.media.session.b
            public Bundle getExtras() {
                Bundle bundle;
                synchronized (i.this.k) {
                    bundle = i.this.B;
                }
                return bundle;
            }

            @Override // android.support.v4.media.session.b
            public MediaMetadataCompat getMetadata() {
                return i.this.f0s;
            }

            @Override // android.support.v4.media.session.b
            public String getTag() {
                return i.this.h;
            }

            @Override // android.support.v4.media.session.b
            public void h(Uri uri, Bundle bundle) {
                o0(6, uri, bundle);
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent i() {
                PendingIntent pendingIntent;
                synchronized (i.this.k) {
                    pendingIntent = i.this.u;
                }
                return pendingIntent;
            }

            @Override // android.support.v4.media.session.b
            public int j() {
                return i.this.x;
            }

            void k0(int i) {
                i.this.u(i, 0, 0, null, null);
            }

            void l0(int i, int i2) {
                i.this.u(i, i2, 0, null, null);
            }

            @Override // android.support.v4.media.session.b
            public void m(String str, Bundle bundle) {
                o0(4, str, bundle);
            }

            void m0(int i, Object obj) {
                i.this.u(i, 0, 0, obj, null);
            }

            @Override // android.support.v4.media.session.b
            public Bundle n() {
                if (i.this.g == null) {
                    return null;
                }
                return new Bundle(i.this.g);
            }

            void n0(int i, Object obj, int i2) {
                i.this.u(i, i2, 0, obj, null);
            }

            @Override // android.support.v4.media.session.b
            public void next() {
                k0(14);
            }

            @Override // android.support.v4.media.session.b
            public void o(String str, Bundle bundle) {
                o0(8, str, bundle);
            }

            void o0(int i, Object obj, Bundle bundle) {
                i.this.u(i, 0, 0, obj, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void p(String str, Bundle bundle) {
                o0(9, str, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void pause() {
                k0(12);
            }

            @Override // android.support.v4.media.session.b
            public void previous() {
                k0(15);
            }

            @Override // android.support.v4.media.session.b
            public void q(Uri uri, Bundle bundle) {
                o0(10, uri, bundle);
            }

            @Override // android.support.v4.media.session.b
            public boolean r(KeyEvent keyEvent) {
                m0(21, keyEvent);
                return true;
            }

            @Override // android.support.v4.media.session.b
            public void s(RatingCompat ratingCompat, Bundle bundle) {
                o0(31, ratingCompat, bundle);
            }

            @Override // android.support.v4.media.session.b
            public void seekTo(long j) {
                m0(18, Long.valueOf(j));
            }

            @Override // android.support.v4.media.session.b
            public void stop() {
                k0(13);
            }

            @Override // android.support.v4.media.session.b
            public void u(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                n0(26, mediaDescriptionCompat, i);
            }

            @Override // android.support.v4.media.session.b
            public int w() {
                return i.this.A;
            }

            @Override // android.support.v4.media.session.b
            public void x(int i) {
                l0(28, i);
            }

            @Override // android.support.v4.media.session.b
            public boolean y() {
                return i.this.y;
            }
        }

        class d extends Handler {
            public d(Looper looper) {
                super(looper);
            }

            private void a(KeyEvent keyEvent, b bVar) {
                if (keyEvent == null || keyEvent.getAction() != 0) {
                    return;
                }
                PlaybackStateCompat playbackStateCompat = i.this.t;
                long jB = playbackStateCompat == null ? 0L : playbackStateCompat.b();
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == 126) {
                    if ((jB & 4) != 0) {
                        bVar.onPlay();
                    }
                    return;
                }
                if (keyCode == 127) {
                    if ((jB & 2) != 0) {
                        bVar.onPause();
                        return;
                    }
                    return;
                }
                switch (keyCode) {
                    case R$styleable.AppCompatTheme_panelMenuListWidth /* 86 */:
                        if ((jB & 1) != 0) {
                            bVar.onStop();
                        }
                        break;
                    case R$styleable.AppCompatTheme_popupMenuStyle /* 87 */:
                        if ((jB & 32) != 0) {
                            bVar.onSkipToNext();
                        }
                        break;
                    case R$styleable.AppCompatTheme_popupWindowStyle /* 88 */:
                        if ((jB & 16) != 0) {
                            bVar.onSkipToPrevious();
                        }
                        break;
                    case R$styleable.AppCompatTheme_radioButtonStyle /* 89 */:
                        if ((jB & 8) != 0) {
                            bVar.onRewind();
                        }
                        break;
                    case R$styleable.AppCompatTheme_ratingBarStyle /* 90 */:
                        if ((jB & 64) != 0) {
                            bVar.onFastForward();
                        }
                        break;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                b bVar = i.this.p;
                if (bVar == null) {
                    return;
                }
                Bundle data = message.getData();
                MediaSessionCompat.c(data);
                i.this.f(new e0.d(data.getString("data_calling_pkg"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid")));
                Bundle bundle = data.getBundle("data_extras");
                MediaSessionCompat.c(bundle);
                try {
                    switch (message.what) {
                        case 1:
                            b bVar2 = (b) message.obj;
                            bVar.onCommand(bVar2.a, bVar2.b, bVar2.c);
                            break;
                        case 2:
                            i.this.p(message.arg1, 0);
                            break;
                        case 3:
                            bVar.onPrepare();
                            break;
                        case 4:
                            bVar.onPrepareFromMediaId((String) message.obj, bundle);
                            break;
                        case 5:
                            bVar.onPrepareFromSearch((String) message.obj, bundle);
                            break;
                        case 6:
                            bVar.onPrepareFromUri((Uri) message.obj, bundle);
                            break;
                        case 7:
                            bVar.onPlay();
                            break;
                        case 8:
                            bVar.onPlayFromMediaId((String) message.obj, bundle);
                            break;
                        case 9:
                            bVar.onPlayFromSearch((String) message.obj, bundle);
                            break;
                        case 10:
                            bVar.onPlayFromUri((Uri) message.obj, bundle);
                            break;
                        case 11:
                            bVar.onSkipToQueueItem(((Long) message.obj).longValue());
                            break;
                        case 12:
                            bVar.onPause();
                            break;
                        case 13:
                            bVar.onStop();
                            break;
                        case 14:
                            bVar.onSkipToNext();
                            break;
                        case 15:
                            bVar.onSkipToPrevious();
                            break;
                        case 16:
                            bVar.onFastForward();
                            break;
                        case 17:
                            bVar.onRewind();
                            break;
                        case 18:
                            bVar.onSeekTo(((Long) message.obj).longValue());
                            break;
                        case 19:
                            bVar.onSetRating((RatingCompat) message.obj);
                            break;
                        case 20:
                            bVar.onCustomAction((String) message.obj, bundle);
                            break;
                        case 21:
                            KeyEvent keyEvent = (KeyEvent) message.obj;
                            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                            if (!bVar.onMediaButtonEvent(intent)) {
                                a(keyEvent, bVar);
                            }
                            break;
                        case 22:
                            i.this.C(message.arg1, 0);
                            break;
                        case 23:
                            bVar.onSetRepeatMode(message.arg1);
                            break;
                        case 25:
                            bVar.onAddQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case 26:
                            bVar.onAddQueueItem((MediaDescriptionCompat) message.obj, message.arg1);
                            break;
                        case 27:
                            bVar.onRemoveQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case R$styleable.AppCompatTheme_actionModeStyle /* 28 */:
                            List list = i.this.v;
                            if (list != null) {
                                int i = message.arg1;
                                QueueItem queueItem = (i < 0 || i >= list.size()) ? null : (QueueItem) i.this.v.get(message.arg1);
                                if (queueItem != null) {
                                    bVar.onRemoveQueueItem(queueItem.c());
                                }
                            }
                            break;
                        case R$styleable.AppCompatTheme_actionModeWebSearchDrawable /* 29 */:
                            bVar.onSetCaptioningEnabled(((Boolean) message.obj).booleanValue());
                            break;
                        case R$styleable.AppCompatTheme_actionOverflowButtonStyle /* 30 */:
                            bVar.onSetShuffleMode(message.arg1);
                            break;
                        case 31:
                            bVar.onSetRating((RatingCompat) message.obj, bundle);
                            break;
                        case 32:
                            bVar.onSetPlaybackSpeed(((Float) message.obj).floatValue());
                            break;
                    }
                } finally {
                    i.this.f(null);
                }
            }
        }

        public i(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, r0.b bVar, Bundle bundle) {
            if (componentName == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null");
            }
            this.a = context;
            this.f = context.getPackageName();
            this.g = bundle;
            this.i = (AudioManager) context.getSystemService("audio");
            this.h = str;
            this.b = componentName;
            this.c = pendingIntent;
            c cVar = new c();
            this.d = cVar;
            this.e = new Token(cVar, null, bVar);
            this.x = 0;
            this.C = 1;
            this.D = 3;
            this.j = new RemoteControlClient(pendingIntent);
        }

        private void w(Bundle bundle) {
            for (int iBeginBroadcast = this.l.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(iBeginBroadcast)).P(bundle);
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
        }

        private void x(MediaMetadataCompat mediaMetadataCompat) {
            for (int iBeginBroadcast = this.l.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(iBeginBroadcast)).v(mediaMetadataCompat);
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
        }

        private void y() {
            for (int iBeginBroadcast = this.l.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(iBeginBroadcast)).t();
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
            this.l.kill();
        }

        private void z(PlaybackStateCompat playbackStateCompat) {
            for (int iBeginBroadcast = this.l.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(iBeginBroadcast)).h0(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
        }

        void A(ParcelableVolumeInfo parcelableVolumeInfo) {
            for (int iBeginBroadcast = this.l.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((android.support.v4.media.session.a) this.l.getBroadcastItem(iBeginBroadcast)).G(parcelableVolumeInfo);
                } catch (RemoteException unused) {
                }
            }
            this.l.finishBroadcast();
        }

        abstract void B(PlaybackStateCompat playbackStateCompat);

        void C(int i, int i2) {
            if (this.C != 2) {
                this.i.setStreamVolume(this.D, i, i2);
                return;
            }
            e0.l lVar = this.E;
            if (lVar != null) {
                lVar.f(i);
            }
        }

        void D(PendingIntent pendingIntent, ComponentName componentName) {
            this.i.unregisterMediaButtonEventReceiver(componentName);
        }

        void E() {
            if (!this.o) {
                D(this.c, this.b);
                this.j.setPlaybackState(0);
                this.i.unregisterRemoteControlClient(this.j);
            } else {
                v(this.c, this.b);
                this.i.registerRemoteControlClient(this.j);
                c(this.f0s);
                d(this.t);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void a(b bVar, Handler handler) {
            synchronized (this.k) {
                d dVar = this.m;
                if (dVar != null) {
                    dVar.removeCallbacksAndMessages(null);
                }
                this.m = (bVar == null || handler == null) ? null : new d(handler.getLooper());
                if (this.p != bVar && this.p != null) {
                    this.p.setSessionImpl(null, null);
                }
                this.p = bVar;
                if (this.p != null) {
                    this.p.setSessionImpl(this, handler);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void b(e0.l lVar) {
            if (lVar == null) {
                throw new IllegalArgumentException("volumeProvider may not be null");
            }
            e0.l lVar2 = this.E;
            if (lVar2 != null) {
                lVar2.g((e0.l.c) null);
            }
            this.C = 2;
            this.E = lVar;
            A(new ParcelableVolumeInfo(this.C, this.D, this.E.c(), this.E.b(), this.E.a()));
            lVar.g(this.F);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void c(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                mediaMetadataCompat = new MediaMetadataCompat.b(mediaMetadataCompat, MediaSessionCompat.d).a();
            }
            synchronized (this.k) {
                this.f0s = mediaMetadataCompat;
            }
            x(mediaMetadataCompat);
            if (this.o) {
                q(mediaMetadataCompat == null ? null : mediaMetadataCompat.d()).apply();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void d(PlaybackStateCompat playbackStateCompat) {
            synchronized (this.k) {
                this.t = playbackStateCompat;
            }
            z(playbackStateCompat);
            if (this.o) {
                if (playbackStateCompat == null) {
                    this.j.setPlaybackState(0);
                    this.j.setTransportControlFlags(0);
                } else {
                    B(playbackStateCompat);
                    this.j.setTransportControlFlags(t(playbackStateCompat.b()));
                }
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public PlaybackStateCompat e() {
            PlaybackStateCompat playbackStateCompat;
            synchronized (this.k) {
                playbackStateCompat = this.t;
            }
            return playbackStateCompat;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void f(e0.d dVar) {
            synchronized (this.k) {
                this.q = dVar;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Token g() {
            return this.e;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public String h() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void i(PendingIntent pendingIntent) {
            synchronized (this.k) {
                this.u = pendingIntent;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public boolean isActive() {
            return this.o;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void j(int i) {
            e0.l lVar = this.E;
            if (lVar != null) {
                lVar.g((e0.l.c) null);
            }
            this.D = i;
            this.C = 1;
            int i2 = this.C;
            int i3 = this.D;
            A(new ParcelableVolumeInfo(i2, i3, 2, this.i.getStreamMaxVolume(i3), this.i.getStreamVolume(this.D)));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public b k() {
            b bVar;
            synchronized (this.k) {
                bVar = this.p;
            }
            return bVar;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void l(PendingIntent pendingIntent) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public Object m() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void n(boolean z) {
            if (z == this.o) {
                return;
            }
            this.o = z;
            E();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public e0.d o() {
            e0.d dVar;
            synchronized (this.k) {
                dVar = this.q;
            }
            return dVar;
        }

        void p(int i, int i2) {
            if (this.C != 2) {
                this.i.adjustStreamVolume(this.D, i, i2);
                return;
            }
            e0.l lVar = this.E;
            if (lVar != null) {
                lVar.e(i);
            }
        }

        RemoteControlClient.MetadataEditor q(Bundle bundle) {
            RemoteControlClient.MetadataEditor metadataEditorEditMetadata = this.j.editMetadata(true);
            if (bundle == null) {
                return metadataEditorEditMetadata;
            }
            if (bundle.containsKey("android.media.metadata.ART")) {
                Bitmap bitmapCopy = (Bitmap) bundle.getParcelable("android.media.metadata.ART");
                if (bitmapCopy != null) {
                    bitmapCopy = bitmapCopy.copy(bitmapCopy.getConfig(), false);
                }
                metadataEditorEditMetadata.putBitmap(100, bitmapCopy);
            } else if (bundle.containsKey("android.media.metadata.ALBUM_ART")) {
                Bitmap bitmapCopy2 = (Bitmap) bundle.getParcelable("android.media.metadata.ALBUM_ART");
                if (bitmapCopy2 != null) {
                    bitmapCopy2 = bitmapCopy2.copy(bitmapCopy2.getConfig(), false);
                }
                metadataEditorEditMetadata.putBitmap(100, bitmapCopy2);
            }
            if (bundle.containsKey("android.media.metadata.ALBUM")) {
                metadataEditorEditMetadata.putString(1, bundle.getString("android.media.metadata.ALBUM"));
            }
            if (bundle.containsKey("android.media.metadata.ALBUM_ARTIST")) {
                metadataEditorEditMetadata.putString(13, bundle.getString("android.media.metadata.ALBUM_ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.ARTIST")) {
                metadataEditorEditMetadata.putString(2, bundle.getString("android.media.metadata.ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.AUTHOR")) {
                metadataEditorEditMetadata.putString(3, bundle.getString("android.media.metadata.AUTHOR"));
            }
            if (bundle.containsKey("android.media.metadata.COMPILATION")) {
                metadataEditorEditMetadata.putString(15, bundle.getString("android.media.metadata.COMPILATION"));
            }
            if (bundle.containsKey("android.media.metadata.COMPOSER")) {
                metadataEditorEditMetadata.putString(4, bundle.getString("android.media.metadata.COMPOSER"));
            }
            if (bundle.containsKey("android.media.metadata.DATE")) {
                metadataEditorEditMetadata.putString(5, bundle.getString("android.media.metadata.DATE"));
            }
            if (bundle.containsKey("android.media.metadata.DISC_NUMBER")) {
                metadataEditorEditMetadata.putLong(14, bundle.getLong("android.media.metadata.DISC_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.DURATION")) {
                metadataEditorEditMetadata.putLong(9, bundle.getLong("android.media.metadata.DURATION"));
            }
            if (bundle.containsKey("android.media.metadata.GENRE")) {
                metadataEditorEditMetadata.putString(6, bundle.getString("android.media.metadata.GENRE"));
            }
            if (bundle.containsKey("android.media.metadata.TITLE")) {
                metadataEditorEditMetadata.putString(7, bundle.getString("android.media.metadata.TITLE"));
            }
            if (bundle.containsKey("android.media.metadata.TRACK_NUMBER")) {
                metadataEditorEditMetadata.putLong(0, bundle.getLong("android.media.metadata.TRACK_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.WRITER")) {
                metadataEditorEditMetadata.putString(11, bundle.getString("android.media.metadata.WRITER"));
            }
            return metadataEditorEditMetadata;
        }

        String r(int i) {
            String nameForUid = this.a.getPackageManager().getNameForUid(i);
            return TextUtils.isEmpty(nameForUid) ? "android.media.session.MediaController" : nameForUid;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void release() {
            this.o = false;
            this.n = true;
            E();
            y();
            a(null, null);
        }

        int s(int i) {
            switch (i) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.c
        public void setExtras(Bundle bundle) {
            this.B = bundle;
            w(bundle);
        }

        int t(long j) {
            int i = (1 & j) != 0 ? 32 : 0;
            if ((2 & j) != 0) {
                i |= 16;
            }
            if ((4 & j) != 0) {
                i |= 4;
            }
            if ((8 & j) != 0) {
                i |= 2;
            }
            if ((16 & j) != 0) {
                i |= 1;
            }
            if ((32 & j) != 0) {
                i |= 128;
            }
            if ((64 & j) != 0) {
                i |= 64;
            }
            return (j & 512) != 0 ? i | 8 : i;
        }

        void u(int i, int i2, int i3, Object obj, Bundle bundle) {
            synchronized (this.k) {
                d dVar = this.m;
                if (dVar != null) {
                    Message messageObtainMessage = dVar.obtainMessage(i, i2, i3, obj);
                    Bundle bundle2 = new Bundle();
                    int callingUid = Binder.getCallingUid();
                    bundle2.putInt("data_calling_uid", callingUid);
                    bundle2.putString("data_calling_pkg", r(callingUid));
                    int callingPid = Binder.getCallingPid();
                    if (callingPid > 0) {
                        bundle2.putInt("data_calling_pid", callingPid);
                    } else {
                        bundle2.putInt("data_calling_pid", -1);
                    }
                    if (bundle != null) {
                        bundle2.putBundle("data_extras", bundle);
                    }
                    messageObtainMessage.setData(bundle2);
                    messageObtainMessage.sendToTarget();
                }
            }
        }

        void v(PendingIntent pendingIntent, ComponentName componentName) {
            this.i.registerMediaButtonEventReceiver(componentName);
        }
    }

    public interface j {
        void a();
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this(context, str, componentName, pendingIntent, null);
    }

    private MediaSession b(Context context, String str, Bundle bundle) {
        return Build.VERSION.SDK_INT >= 29 ? new MediaSession(context, str, bundle) : new MediaSession(context, str);
    }

    public static void c(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    static PlaybackStateCompat g(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        long j2;
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        long jF = -1;
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
            jF = mediaMetadataCompat.f("android.media.metadata.DURATION");
        }
        if (jF < 0 || jD <= jF) {
            j2 = jD < 0 ? 0L : jD;
        } else {
            j2 = jF;
        }
        return new PlaybackStateCompat.b(playbackStateCompat).e(playbackStateCompat.g(), j2, playbackStateCompat.d(), jElapsedRealtime).b();
    }

    public static Bundle t(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        c(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public void a(j jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.c.add(jVar);
    }

    public MediaControllerCompat d() {
        return this.b;
    }

    public Object e() {
        return this.a.m();
    }

    public Token f() {
        return this.a.g();
    }

    public boolean h() {
        return this.a.isActive();
    }

    public void i() {
        this.a.release();
    }

    public void j(j jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.c.remove(jVar);
    }

    public void k(boolean z) {
        this.a.n(z);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((j) it.next()).a();
        }
    }

    public void l(b bVar) {
        m(bVar, null);
    }

    public void m(b bVar, Handler handler) {
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

    public void n(Bundle bundle) {
        this.a.setExtras(bundle);
    }

    public void o(MediaMetadataCompat mediaMetadataCompat) {
        this.a.c(mediaMetadataCompat);
    }

    public void p(PlaybackStateCompat playbackStateCompat) {
        this.a.d(playbackStateCompat);
    }

    public void q(int i2) {
        this.a.j(i2);
    }

    public void r(e0.l lVar) {
        if (lVar == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.a.b(lVar);
    }

    public void s(PendingIntent pendingIntent) {
        this.a.i(pendingIntent);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        this(context, str, componentName, pendingIntent, bundle, null);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle, r0.b bVar) {
        this.c = new ArrayList();
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                ComponentName componentNameA = componentName == null ? g0.a.a(context) : componentName;
                if (componentNameA != null && pendingIntent == null) {
                    Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.setComponent(componentNameA);
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                }
                PendingIntent pendingIntent2 = pendingIntent;
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 21) {
                    MediaSession mediaSessionB = b(context, str, bundle);
                    if (i2 >= 29) {
                        this.a = new h(mediaSessionB, bVar, bundle);
                    } else if (i2 >= 28) {
                        this.a = new g(mediaSessionB, bVar, bundle);
                    } else {
                        this.a = new f(mediaSessionB, bVar, bundle);
                    }
                    m(new a(), new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()));
                    this.a.l(pendingIntent2);
                } else {
                    this.a = new e(context, str, componentNameA, pendingIntent2, bVar, bundle);
                }
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
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            MediaSession.QueueItem queueItemA = l0.a(obj);
            return new QueueItem(queueItemA, MediaDescriptionCompat.a(queueItemA.getDescription()), queueItemA.getQueueId());
        }

        public static List b(List list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
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
