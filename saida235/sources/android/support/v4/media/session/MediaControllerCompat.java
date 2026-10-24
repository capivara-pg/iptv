package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import androidx.media.AudioAttributesCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import s.j;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {
    private final b a;
    private final MediaSessionCompat.Token b;
    private final ConcurrentHashMap c = new ConcurrentHashMap();

    static class MediaControllerImplApi21 implements b {
        protected final MediaController a;
        final Object b = new Object();
        private final List c = new ArrayList();
        private HashMap d = new HashMap();
        final MediaSessionCompat.Token e;

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference a;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.a = new WeakReference(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = (MediaControllerImplApi21) this.a.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.b) {
                    mediaControllerImplApi21.e.d(android.support.v4.media.session.b.a.m0(j.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.e.e(y0.a.b(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                    mediaControllerImplApi21.f();
                }
            }
        }

        private static class a extends a.c {
            a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void E(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void N(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void X(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void j(List list) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void r() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void t(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }
        }

        MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.e = token;
            this.a = new MediaController(context, (MediaSession.Token) token.c());
            if (token.a() == null) {
                g();
            }
        }

        private void g() {
            h("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void a(a aVar) {
            this.a.unregisterCallback(aVar.a);
            synchronized (this.b) {
                if (this.e.a() != null) {
                    try {
                        a aVar2 = (a) this.d.remove(aVar);
                        if (aVar2 != null) {
                            aVar.c = null;
                            this.e.a().P(aVar2);
                        }
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                    }
                } else {
                    this.c.remove(aVar);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PendingIntent b() {
            return this.a.getSessionActivity();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat c() {
            if (this.e.a() != null) {
                try {
                    return this.e.a().c();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                }
            }
            PlaybackState playbackState = this.a.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.a(playbackState);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public d d() {
            return new e(this.a.getTransportControls());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void e(a aVar, Handler handler) {
            this.a.registerCallback(aVar.a, handler);
            synchronized (this.b) {
                if (this.e.a() != null) {
                    a aVar2 = new a(aVar);
                    this.d.put(aVar, aVar2);
                    aVar.c = aVar2;
                    try {
                        this.e.a().F(aVar2);
                        aVar.m(13, null, null);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                } else {
                    aVar.c = null;
                    this.c.add(aVar);
                }
            }
        }

        void f() {
            if (this.e.a() == null) {
                return;
            }
            for (a aVar : this.c) {
                a aVar2 = new a(aVar);
                this.d.put(aVar, aVar2);
                aVar.c = aVar2;
                try {
                    this.e.a().F(aVar2);
                    aVar.m(13, null, null);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                }
            }
            this.c.clear();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat getMetadata() {
            MediaMetadata metadata = this.a.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.b(metadata);
            }
            return null;
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.a.sendCommand(str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {
        final MediaController.Callback a = new C0000a(this);
        b b;
        android.support.v4.media.session.a c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        private static class C0000a extends MediaController.Callback {
            private final WeakReference a;

            C0000a(a aVar) {
                this.a = new WeakReference(aVar);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.a(new c(playbackInfo.getPlaybackType(), AudioAttributesCompat.c(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.b(bundle);
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.c(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.d(MediaMetadataCompat.b(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                a aVar = (a) this.a.get();
                if (aVar == null || aVar.c != null) {
                    return;
                }
                aVar.e(PlaybackStateCompat.a(playbackState));
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List list) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.f(MediaSessionCompat.QueueItem.b(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.g(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.i();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.b(bundle);
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    if (aVar.c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.j(str, bundle);
                    }
                }
            }
        }

        private class b extends Handler {
            boolean a;

            b(Looper looper) {
                super(looper);
                this.a = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.a) {
                    switch (message.what) {
                        case 1:
                            Bundle data = message.getData();
                            MediaSessionCompat.b(data);
                            a.this.j((String) message.obj, data);
                            break;
                        case 2:
                            a.this.e((PlaybackStateCompat) message.obj);
                            break;
                        case 3:
                            a.this.d((MediaMetadataCompat) message.obj);
                            break;
                        case 4:
                            a.this.a((c) message.obj);
                            break;
                        case 5:
                            a.this.f((List) message.obj);
                            break;
                        case 6:
                            a.this.g((CharSequence) message.obj);
                            break;
                        case 7:
                            Bundle bundle = (Bundle) message.obj;
                            MediaSessionCompat.b(bundle);
                            a.this.c(bundle);
                            break;
                        case 8:
                            a.this.i();
                            break;
                        case 9:
                            a.this.h(((Integer) message.obj).intValue());
                            break;
                        case 11:
                            a.this.b(((Boolean) message.obj).booleanValue());
                            break;
                        case 12:
                            a.this.l(((Integer) message.obj).intValue());
                            break;
                        case 13:
                            a.this.k();
                            break;
                    }
                }
            }
        }

        private static class c extends android.support.v4.media.session.a.AbstractBinderC0002a {
            private final WeakReference a;

            c(a aVar) {
                this.a = new WeakReference(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void S(boolean z) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(11, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void W(boolean z) {
            }

            @Override // android.support.v4.media.session.a
            public void a0(int i) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(9, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void i() {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(13, null, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void l0(PlaybackStateCompat playbackStateCompat) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void onEvent(String str, Bundle bundle) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(1, str, bundle);
                }
            }

            @Override // android.support.v4.media.session.a
            public void x(int i) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(12, Integer.valueOf(i), null);
                }
            }
        }

        public void a(c cVar) {
        }

        public void b(boolean z) {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            m(8, null, null);
        }

        public void c(Bundle bundle) {
        }

        public abstract void d(MediaMetadataCompat mediaMetadataCompat);

        public void e(PlaybackStateCompat playbackStateCompat) {
        }

        public void f(List list) {
        }

        public void g(CharSequence charSequence) {
        }

        public void h(int i) {
        }

        public abstract void i();

        public void j(String str, Bundle bundle) {
        }

        public void k() {
        }

        public void l(int i) {
        }

        void m(int i, Object obj, Bundle bundle) {
            b bVar = this.b;
            if (bVar != null) {
                Message messageObtainMessage = bVar.obtainMessage(i, obj);
                messageObtainMessage.setData(bundle);
                messageObtainMessage.sendToTarget();
            }
        }

        void n(Handler handler) {
            if (handler != null) {
                b bVar = new b(handler.getLooper());
                this.b = bVar;
                bVar.a = true;
            } else {
                b bVar2 = this.b;
                if (bVar2 != null) {
                    bVar2.a = false;
                    bVar2.removeCallbacksAndMessages(null);
                    this.b = null;
                }
            }
        }
    }

    interface b {
        void a(a aVar);

        PendingIntent b();

        PlaybackStateCompat c();

        d d();

        void e(a aVar, Handler handler);

        MediaMetadataCompat getMetadata();
    }

    public static final class c {
        private final int a;
        private final AudioAttributesCompat b;
        private final int c;
        private final int d;
        private final int e;

        c(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.a = i;
            this.b = audioAttributesCompat;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    public static abstract class d {
        d() {
        }

        public abstract void a();

        public abstract void b();

        public abstract void c();
    }

    static class e extends d {
        protected final MediaController.TransportControls a;

        e(MediaController.TransportControls transportControls) {
            this.a = transportControls;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void a() {
            this.a.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void b() {
            this.a.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void c() {
            this.a.stop();
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        MediaSessionCompat.Token tokenE = mediaSessionCompat.e();
        this.b = tokenE;
        this.a = new MediaControllerImplApi21(context, tokenE);
    }

    public MediaMetadataCompat a() {
        return this.a.getMetadata();
    }

    public PlaybackStateCompat b() {
        return this.a.c();
    }

    public PendingIntent c() {
        return this.a.b();
    }

    public d d() {
        return this.a.d();
    }

    public void e(a aVar) {
        f(aVar, null);
    }

    public void f(a aVar, Handler handler) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.c.putIfAbsent(aVar, Boolean.TRUE) != null) {
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        aVar.n(handler);
        this.a.e(aVar, handler);
    }

    public void g(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.c.remove(aVar) == null) {
            return;
        }
        try {
            this.a.a(aVar);
        } finally {
            aVar.n(null);
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        if (token != null) {
            this.b = token;
            this.a = new MediaControllerImplApi21(context, token);
            return;
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }
}
