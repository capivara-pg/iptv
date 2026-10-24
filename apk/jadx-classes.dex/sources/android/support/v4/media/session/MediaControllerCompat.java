package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
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

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
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
                    mediaControllerImplApi21.e.d(android.support.v4.media.session.b.a.i0(n.o.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.e.e(r0.a.b(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                    mediaControllerImplApi21.f();
                }
            }
        }

        private static class a extends a.c {
            a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void G(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void P(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void W(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void l(List list) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void t() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void v(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }
        }

        MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.e = token;
            this.a = new MediaController(context, n.a(token.c()));
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
                            this.e.a().R(aVar2);
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
        public e c() {
            return new f(this.a.getTransportControls());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void d(a aVar, Handler handler) {
            this.a.registerCallback(aVar.a, handler);
            synchronized (this.b) {
                if (this.e.a() != null) {
                    a aVar2 = new a(aVar);
                    this.d.put(aVar, aVar2);
                    aVar.c = aVar2;
                    try {
                        this.e.a().H(aVar2);
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

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat e() {
            if (this.e.a() != null) {
                try {
                    return this.e.a().e();
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

        void f() {
            if (this.e.a() == null) {
                return;
            }
            for (a aVar : this.c) {
                a aVar2 = new a(aVar);
                this.d.put(aVar, aVar2);
                aVar.c = aVar2;
                try {
                    this.e.a().H(aVar2);
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
        final MediaController.Callback a;
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
                    aVar.a(new d(playbackInfo.getPlaybackType(), AudioAttributesCompat.c(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.c(bundle);
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
                MediaSessionCompat.c(bundle);
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
                            MediaSessionCompat.c(data);
                            a.this.j((String) message.obj, data);
                            break;
                        case 2:
                            a.this.e((PlaybackStateCompat) message.obj);
                            break;
                        case 3:
                            a.this.d((MediaMetadataCompat) message.obj);
                            break;
                        case 4:
                            a.this.a((d) message.obj);
                            break;
                        case 5:
                            a.this.f((List) message.obj);
                            break;
                        case 6:
                            a.this.g((CharSequence) message.obj);
                            break;
                        case 7:
                            Bundle bundle = (Bundle) message.obj;
                            MediaSessionCompat.c(bundle);
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

            public void G(ParcelableVolumeInfo parcelableVolumeInfo) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(4, parcelableVolumeInfo != null ? new d(parcelableVolumeInfo.a, parcelableVolumeInfo.b, parcelableVolumeInfo.c, parcelableVolumeInfo.d, parcelableVolumeInfo.e) : null, null);
                }
            }

            public void P(Bundle bundle) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(7, bundle, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void T(boolean z) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(11, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void V(boolean z) {
            }

            public void W(CharSequence charSequence) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(6, charSequence, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void Z(int i) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(9, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void h0(PlaybackStateCompat playbackStateCompat) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void k() {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(13, null, null);
                }
            }

            public void l(List list) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(5, list, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void onEvent(String str, Bundle bundle) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(1, str, bundle);
                }
            }

            public void t() {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(8, null, null);
                }
            }

            public void v(MediaMetadataCompat mediaMetadataCompat) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(3, mediaMetadataCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void z(int i) {
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.m(12, Integer.valueOf(i), null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.a = new C0000a(this);
            } else {
                this.a = null;
                this.c = new c(this);
            }
        }

        public void a(d dVar) {
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

        e c();

        void d(a aVar, Handler handler);

        PlaybackStateCompat e();

        MediaMetadataCompat getMetadata();
    }

    static class c implements b {
        private android.support.v4.media.session.b a;
        private e b;

        c(MediaSessionCompat.Token token) {
            this.a = android.support.v4.media.session.b.a.i0((IBinder) token.c());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void a(a aVar) {
            if (aVar == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.a.R(aVar.c);
                this.a.asBinder().unlinkToDeath(aVar, 0);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PendingIntent b() {
            try {
                return this.a.i();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public e c() {
            if (this.b == null) {
                this.b = new g(this.a);
            }
            return this.b;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void d(a aVar, Handler handler) {
            if (aVar == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.a.asBinder().linkToDeath(aVar, 0);
                this.a.H(aVar.c);
                aVar.m(13, null, null);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                aVar.m(8, null, null);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat e() {
            try {
                return this.a.e();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat getMetadata() {
            try {
                return this.a.getMetadata();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata.", e);
                return null;
            }
        }
    }

    public static final class d {
        private final int a;
        private final AudioAttributesCompat b;
        private final int c;
        private final int d;
        private final int e;

        d(int i, int i2, int i3, int i4, int i5) {
            this(i, new AudioAttributesCompat.a().b(i2).a(), i3, i4, i5);
        }

        d(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.a = i;
            this.b = audioAttributesCompat;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    public static abstract class e {
        e() {
        }

        public abstract void a();

        public abstract void b();

        public abstract void c();
    }

    static class f extends e {
        protected final MediaController.TransportControls a;

        f(MediaController.TransportControls transportControls) {
            this.a = transportControls;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void a() {
            this.a.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void b() {
            this.a.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void c() {
            this.a.stop();
        }
    }

    static class g extends e {
        private android.support.v4.media.session.b a;

        public g(android.support.v4.media.session.b bVar) {
            this.a = bVar;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void a() {
            try {
                this.a.pause();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in pause.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void b() {
            try {
                this.a.a0();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in play.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void c() {
            try {
                this.a.stop();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in stop.", e);
            }
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        MediaSessionCompat.Token tokenF = mediaSessionCompat.f();
        this.b = tokenF;
        if (Build.VERSION.SDK_INT >= 21) {
            this.a = new MediaControllerImplApi21(context, tokenF);
        } else {
            this.a = new c(tokenF);
        }
    }

    public MediaMetadataCompat a() {
        return this.a.getMetadata();
    }

    public PlaybackStateCompat b() {
        return this.a.e();
    }

    public PendingIntent c() {
        return this.a.b();
    }

    public e d() {
        return this.a.c();
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
        this.a.d(aVar, handler);
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
            if (Build.VERSION.SDK_INT >= 21) {
                this.a = new MediaControllerImplApi21(context, token);
                return;
            } else {
                this.a = new c(token);
                return;
            }
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }
}
