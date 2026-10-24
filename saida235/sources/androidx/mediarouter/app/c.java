package androidx.mediarouter.app;

import android.R;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$interpolator;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import o0.k0;
import o0.l0;

/* JADX INFO: loaded from: classes.dex */
public class c extends androidx.appcompat.app.c {
    static final boolean t0 = Log.isLoggable("MediaRouteCtrlDialog", 3);
    static final int u0 = (int) TimeUnit.SECONDS.toMillis(30);
    private View A;
    OverlayListView B;
    r C;
    private List D;
    Set E;
    private Set F;
    Set G;
    SeekBar H;
    q I;
    l0.h J;
    private int K;
    private int L;
    private int M;
    private final int N;
    Map Q;
    MediaControllerCompat S;
    o V;
    PlaybackStateCompat W;
    MediaDescriptionCompat X;
    n Y;
    Bitmap Z;
    final l0 a;
    private final p b;
    Uri b0;
    final l0.h c;
    boolean c0;
    Context d;
    Bitmap d0;
    private boolean e;
    int e0;
    private boolean f;
    boolean f0;
    private int g;
    boolean g0;
    private View h;
    boolean h0;
    private Button i;
    boolean i0;
    private Button j;
    boolean j0;
    private ImageButton k;
    int k0;
    private ImageButton l;
    private int l0;
    private MediaRouteExpandCollapseButton m;
    private int m0;
    private FrameLayout n;
    private Interpolator n0;
    private LinearLayout o;
    private Interpolator o0;
    FrameLayout p;
    private Interpolator p0;
    private FrameLayout q;
    private Interpolator q0;
    private ImageView r;
    final AccessibilityManager r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private TextView f29s;
    Runnable s0;
    private TextView t;
    private TextView u;
    private boolean v;
    final boolean w;
    private LinearLayout x;
    private RelativeLayout y;
    LinearLayout z;

    class a implements OverlayListView.a.InterfaceC0044a {
        final /* synthetic */ l0.h a;

        a(l0.h hVar) {
            this.a = hVar;
        }

        @Override // androidx.mediarouter.app.OverlayListView.a.InterfaceC0044a
        public void onAnimationEnd() {
            c.this.G.remove(this.a);
            c.this.C.notifyDataSetChanged();
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            c.this.B.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            c.this.y();
        }
    }

    /* JADX INFO: renamed from: androidx.mediarouter.app.c$c, reason: collision with other inner class name */
    class AnimationAnimationListenerC0046c implements Animation.AnimationListener {
        AnimationAnimationListenerC0046c() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            c.this.h(true);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.x();
        }
    }

    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.dismiss();
        }
    }

    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PendingIntent pendingIntentC;
            MediaControllerCompat mediaControllerCompat = c.this.S;
            if (mediaControllerCompat == null || (pendingIntentC = mediaControllerCompat.c()) == null) {
                return;
            }
            try {
                pendingIntentC.send();
                c.this.dismiss();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("MediaRouteCtrlDialog", pendingIntentC + " was not sent, it had been canceled.");
            }
        }
    }

    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c cVar = c.this;
            boolean z = !cVar.h0;
            cVar.h0 = z;
            if (z) {
                cVar.B.setVisibility(0);
            }
            c.this.s();
            c.this.B(true);
        }
    }

    class i implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ boolean a;

        i(boolean z) {
            this.a = z;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            c.this.p.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            c cVar = c.this;
            if (cVar.i0) {
                cVar.j0 = true;
            } else {
                cVar.C(this.a);
            }
        }
    }

    class j extends Animation {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ View c;

        j(int i, int i2, View view) {
            this.a = i;
            this.b = i2;
            this.c = view;
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            int i = this.a;
            c.v(this.c, i - ((int) ((i - this.b) * f)));
        }
    }

    class k implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ Map a;
        final /* synthetic */ Map b;

        k(Map map, Map map2) {
            this.a = map;
            this.b = map2;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            c.this.B.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            c.this.b(this.a, this.b);
        }
    }

    class l implements Animation.AnimationListener {
        l() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            c.this.B.b();
            c cVar = c.this;
            cVar.B.postDelayed(cVar.s0, cVar.k0);
        }
    }

    private final class m implements View.OnClickListener {
        m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlaybackStateCompat playbackStateCompat;
            int id = view.getId();
            if (id == 16908313 || id == 16908314) {
                if (c.this.c.C()) {
                    c.this.a.z(id == 16908313 ? 2 : 1);
                }
                c.this.dismiss();
                return;
            }
            if (id != R$id.mr_control_playback_ctrl) {
                if (id == R$id.mr_close) {
                    c.this.dismiss();
                    return;
                }
                return;
            }
            c cVar = c.this;
            if (cVar.S == null || (playbackStateCompat = cVar.W) == null) {
                return;
            }
            int i = 0;
            int i2 = playbackStateCompat.g() != 3 ? 0 : 1;
            if (i2 != 0 && c.this.o()) {
                c.this.S.d().a();
                i = R$string.mr_controller_pause;
            } else if (i2 != 0 && c.this.q()) {
                c.this.S.d().c();
                i = R$string.mr_controller_stop;
            } else if (i2 == 0 && c.this.p()) {
                c.this.S.d().b();
                i = R$string.mr_controller_play;
            }
            AccessibilityManager accessibilityManager = c.this.r0;
            if (accessibilityManager == null || !accessibilityManager.isEnabled() || i == 0) {
                return;
            }
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(16384);
            accessibilityEventObtain.setPackageName(c.this.d.getPackageName());
            accessibilityEventObtain.setClassName(m.class.getName());
            accessibilityEventObtain.getText().add(c.this.d.getString(i));
            c.this.r0.sendAccessibilityEvent(accessibilityEventObtain);
        }
    }

    private class n extends AsyncTask {
        private final Bitmap a;
        private final Uri b;
        private int c;
        private long d;

        n() {
            MediaDescriptionCompat mediaDescriptionCompat = c.this.X;
            Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
            this.a = c.l(bitmapB) ? null : bitmapB;
            MediaDescriptionCompat mediaDescriptionCompat2 = c.this.X;
            this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        }

        private InputStream e(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = c.this.d.getContentResolver().openInputStream(uri);
            } else {
                URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
                int i = c.u0;
                uRLConnectionOpenConnection.setConnectTimeout(i);
                uRLConnectionOpenConnection.setReadTimeout(i);
                inputStreamOpenInputStream = uRLConnectionOpenConnection.getInputStream();
            }
            if (inputStreamOpenInputStream == null) {
                return null;
            }
            return new BufferedInputStream(inputStreamOpenInputStream);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v2 */
        /* JADX WARN: Type inference failed for: r3v3, types: [java.io.InputStream] */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) throws Throwable {
            InputStream inputStreamE;
            Bitmap bitmap = this.a;
            ?? r3 = 0;
            if (bitmap == null) {
                Uri uri = this.b;
                try {
                    if (uri != null) {
                        try {
                            inputStreamE = e(uri);
                            try {
                                if (inputStreamE == null) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("Unable to open: ");
                                    sb.append(this.b);
                                    if (inputStreamE != null) {
                                        try {
                                            inputStreamE.close();
                                        } catch (IOException unused) {
                                        }
                                    }
                                    return null;
                                }
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeStream(inputStreamE, null, options);
                                if (options.outWidth == 0 || options.outHeight == 0) {
                                    try {
                                        inputStreamE.close();
                                    } catch (IOException unused2) {
                                    }
                                    return null;
                                }
                                try {
                                    inputStreamE.reset();
                                } catch (IOException unused3) {
                                    inputStreamE.close();
                                    inputStreamE = e(this.b);
                                    if (inputStreamE == null) {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append("Unable to open: ");
                                        sb2.append(this.b);
                                        if (inputStreamE != null) {
                                            try {
                                                inputStreamE.close();
                                            } catch (IOException unused4) {
                                            }
                                        }
                                        return null;
                                    }
                                }
                                options.inJustDecodeBounds = false;
                                options.inSampleSize = Math.max(1, Integer.highestOneBit(options.outHeight / c.this.i(options.outWidth, options.outHeight)));
                                if (isCancelled()) {
                                    try {
                                        inputStreamE.close();
                                    } catch (IOException unused5) {
                                    }
                                    return null;
                                }
                                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamE, null, options);
                                try {
                                    inputStreamE.close();
                                } catch (IOException unused6) {
                                }
                                bitmap = bitmapDecodeStream;
                            } catch (IOException unused7) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("Unable to open: ");
                                sb3.append(this.b);
                                if (inputStreamE != null) {
                                    try {
                                        inputStreamE.close();
                                    } catch (IOException unused8) {
                                    }
                                }
                                bitmap = null;
                            }
                        } catch (IOException unused9) {
                            inputStreamE = null;
                        } catch (Throwable th) {
                            th = th;
                            if (r3 != 0) {
                                try {
                                    r3.close();
                                } catch (IOException unused10) {
                                }
                            }
                            throw th;
                        }
                    } else {
                        bitmap = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r3 = uri;
                }
            }
            if (c.l(bitmap)) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Can't use recycled bitmap: ");
                sb4.append(bitmap);
                return null;
            }
            if (bitmap != null && bitmap.getWidth() < bitmap.getHeight()) {
                q0.b bVarA = new q0.b.b(bitmap).c(1).a();
                this.c = bVarA.f().isEmpty() ? 0 : ((q0.b.d) bVarA.f().get(0)).e();
            }
            return bitmap;
        }

        public Bitmap b() {
            return this.a;
        }

        public Uri c() {
            return this.b;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            c cVar = c.this;
            cVar.Y = null;
            if (c0.c.a(cVar.Z, this.a) && c0.c.a(c.this.b0, this.b)) {
                return;
            }
            c cVar2 = c.this;
            cVar2.Z = this.a;
            cVar2.d0 = bitmap;
            cVar2.b0 = this.b;
            cVar2.e0 = this.c;
            cVar2.c0 = true;
            c.this.z(SystemClock.uptimeMillis() - this.d > 120);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.d = SystemClock.uptimeMillis();
            c.this.f();
        }
    }

    private final class o extends MediaControllerCompat.a {
        o() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            c.this.X = mediaMetadataCompat == null ? null : mediaMetadataCompat.d();
            c.this.A();
            c.this.z(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            c cVar = c.this;
            cVar.W = playbackStateCompat;
            cVar.z(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void i() {
            c cVar = c.this;
            MediaControllerCompat mediaControllerCompat = cVar.S;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.g(cVar.V);
                c.this.S = null;
            }
        }
    }

    private final class p extends l0.a {
        p() {
        }

        public void onRouteChanged(l0 l0Var, l0.h hVar) {
            c.this.z(true);
        }

        public void onRouteUnselected(l0 l0Var, l0.h hVar) {
            c.this.z(false);
        }

        public void onRouteVolumeChanged(l0 l0Var, l0.h hVar) {
            SeekBar seekBar = (SeekBar) c.this.Q.get(hVar);
            int iS = hVar.s();
            if (c.t0) {
                StringBuilder sb = new StringBuilder();
                sb.append("onRouteVolumeChanged(), route.getVolume:");
                sb.append(iS);
            }
            if (seekBar == null || c.this.J == hVar) {
                return;
            }
            seekBar.setProgress(iS);
        }
    }

    private class q implements SeekBar.OnSeekBarChangeListener {
        private final Runnable a = new a();

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                if (cVar.J != null) {
                    cVar.J = null;
                    if (cVar.f0) {
                        cVar.z(cVar.g0);
                    }
                }
            }
        }

        q() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                l0.h hVar = (l0.h) seekBar.getTag();
                if (c.t0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onProgressChanged(): calling MediaRouter.RouteInfo.requestSetVolume(");
                    sb.append(i);
                    sb.append(")");
                }
                hVar.G(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            c cVar = c.this;
            if (cVar.J != null) {
                cVar.H.removeCallbacks(this.a);
            }
            c.this.J = (l0.h) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            c.this.H.postDelayed(this.a, 500L);
        }
    }

    private class r extends ArrayAdapter {
        final float a;

        public r(Context context, List list) {
            super(context, 0, list);
            this.a = androidx.mediarouter.app.i.h(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mr_controller_volume_item, viewGroup, false);
            } else {
                c.this.G(view);
            }
            l0.h hVar = (l0.h) getItem(i);
            if (hVar != null) {
                boolean zX = hVar.x();
                TextView textView = (TextView) view.findViewById(R$id.mr_name);
                textView.setEnabled(zX);
                textView.setText(hVar.m());
                MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(R$id.mr_volume_slider);
                androidx.mediarouter.app.i.w(viewGroup.getContext(), mediaRouteVolumeSlider, c.this.B);
                mediaRouteVolumeSlider.setTag(hVar);
                c.this.Q.put(hVar, mediaRouteVolumeSlider);
                mediaRouteVolumeSlider.c(!zX);
                mediaRouteVolumeSlider.setEnabled(zX);
                if (zX) {
                    if (c.this.r(hVar)) {
                        mediaRouteVolumeSlider.setMax(hVar.u());
                        mediaRouteVolumeSlider.setProgress(hVar.s());
                        mediaRouteVolumeSlider.setOnSeekBarChangeListener(c.this.I);
                    } else {
                        mediaRouteVolumeSlider.setMax(100);
                        mediaRouteVolumeSlider.setProgress(100);
                        mediaRouteVolumeSlider.setEnabled(false);
                    }
                }
                ((ImageView) view.findViewById(R$id.mr_volume_item_icon)).setAlpha(zX ? 255 : (int) (this.a * 255.0f));
                ((LinearLayout) view.findViewById(R$id.volume_item_container)).setVisibility(c.this.G.contains(hVar) ? 4 : 0);
                Set set = c.this.E;
                if (set != null && set.contains(hVar)) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                    alphaAnimation.setDuration(0L);
                    alphaAnimation.setFillEnabled(true);
                    alphaAnimation.setFillAfter(true);
                    view.clearAnimation();
                    view.startAnimation(alphaAnimation);
                }
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return false;
        }
    }

    public c(Context context) {
        this(context, 0);
    }

    private void D(boolean z) {
        int i2 = 0;
        this.A.setVisibility((this.z.getVisibility() == 0 && z) ? 0 : 8);
        LinearLayout linearLayout = this.x;
        if (this.z.getVisibility() == 8 && !z) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0071  */
    /* JADX WARN: Code duplicated, block: B:34:0x0073  */
    /* JADX WARN: Code duplicated, block: B:37:0x007c  */
    /* JADX WARN: Code duplicated, block: B:38:0x007e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0087  */
    /* JADX WARN: Code duplicated, block: B:47:0x009a  */
    /* JADX WARN: Code duplicated, block: B:53:0x00ae A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:54:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:57:0x00bb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:61:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:65:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:68:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:71:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:? A[RETURN, SYNTHETIC] */
    private void E() {
        boolean z;
        boolean z2;
        int i2;
        int i3;
        PlaybackStateCompat playbackStateCompat;
        boolean z3;
        Context context;
        int i4;
        int i5;
        if (d()) {
            MediaDescriptionCompat mediaDescriptionCompat = this.X;
            CharSequence charSequenceF = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.f();
            boolean z4 = true;
            boolean z5 = !TextUtils.isEmpty(charSequenceF);
            MediaDescriptionCompat mediaDescriptionCompat2 = this.X;
            CharSequence charSequenceE = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.e() : null;
            boolean z6 = !TextUtils.isEmpty(charSequenceE);
            if (this.c.p() != -1) {
                this.f29s.setText(R$string.mr_controller_casting_screen);
            } else {
                PlaybackStateCompat playbackStateCompat2 = this.W;
                if (playbackStateCompat2 != null && playbackStateCompat2.g() != 0) {
                    if (z5 || z6) {
                        if (z5) {
                            this.f29s.setText(charSequenceF);
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z6) {
                            this.t.setText(charSequenceE);
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    } else {
                        this.f29s.setText(R$string.mr_controller_no_info_available);
                    }
                    TextView textView = this.f29s;
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 8;
                    }
                    textView.setVisibility(i2);
                    TextView textView2 = this.t;
                    if (z2) {
                        i3 = 0;
                    } else {
                        i3 = 8;
                    }
                    textView2.setVisibility(i3);
                    playbackStateCompat = this.W;
                    if (playbackStateCompat != null) {
                        if (playbackStateCompat.g() != 6 || this.W.g() == 3) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        context = this.k.getContext();
                        if (!z3 && o()) {
                            i4 = R$attr.mediaRoutePauseDrawable;
                            i5 = R$string.mr_controller_pause;
                        } else if (!z3 && q()) {
                            i4 = R$attr.mediaRouteStopDrawable;
                            i5 = R$string.mr_controller_stop;
                        } else if (z3 && p()) {
                            i4 = R$attr.mediaRoutePlayDrawable;
                            i5 = R$string.mr_controller_play;
                        } else {
                            i4 = 0;
                            i5 = 0;
                            z4 = false;
                        }
                        this.k.setVisibility(z4 ? 0 : 8);
                        if (z4) {
                            this.k.setImageResource(androidx.mediarouter.app.i.p(context, i4));
                            this.k.setContentDescription(context.getResources().getText(i5));
                        }
                    }
                }
                this.f29s.setText(R$string.mr_controller_no_media_selected);
            }
            z = true;
            z2 = false;
            TextView textView3 = this.f29s;
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            textView3.setVisibility(i2);
            TextView textView4 = this.t;
            if (z2) {
                i3 = 0;
            } else {
                i3 = 8;
            }
            textView4.setVisibility(i3);
            playbackStateCompat = this.W;
            if (playbackStateCompat != null) {
                if (playbackStateCompat.g() != 6) {
                    z3 = true;
                } else {
                    z3 = true;
                }
                context = this.k.getContext();
                if (!z3) {
                    if (!z3) {
                        if (z3) {
                            i4 = 0;
                            i5 = 0;
                            z4 = false;
                        } else {
                            i4 = 0;
                            i5 = 0;
                            z4 = false;
                        }
                    } else if (z3) {
                        i4 = 0;
                        i5 = 0;
                        z4 = false;
                    } else {
                        i4 = 0;
                        i5 = 0;
                        z4 = false;
                    }
                } else if (!z3) {
                    if (z3) {
                        i4 = 0;
                        i5 = 0;
                        z4 = false;
                    } else {
                        i4 = 0;
                        i5 = 0;
                        z4 = false;
                    }
                } else if (z3) {
                    i4 = 0;
                    i5 = 0;
                    z4 = false;
                } else {
                    i4 = 0;
                    i5 = 0;
                    z4 = false;
                }
                this.k.setVisibility(z4 ? 0 : 8);
                if (z4) {
                    this.k.setImageResource(androidx.mediarouter.app.i.p(context, i4));
                    this.k.setContentDescription(context.getResources().getText(i5));
                }
            }
        }
    }

    private void F() {
        if (!this.w && m()) {
            this.z.setVisibility(8);
            this.h0 = true;
            this.B.setVisibility(0);
            s();
            B(false);
            return;
        }
        if ((this.h0 && !this.w) || !r(this.c)) {
            this.z.setVisibility(8);
        } else if (this.z.getVisibility() == 8) {
            this.z.setVisibility(0);
            this.H.setMax(this.c.u());
            this.H.setProgress(this.c.s());
            this.m.setVisibility(m() ? 0 : 8);
        }
    }

    private static boolean H(Uri uri, Uri uri2) {
        if (uri == null || !uri.equals(uri2)) {
            return uri == null && uri2 == null;
        }
        return true;
    }

    private void a(Map map, Map map2) {
        this.B.setEnabled(false);
        this.B.requestLayout();
        this.i0 = true;
        this.B.getViewTreeObserver().addOnGlobalLayoutListener(new k(map, map2));
    }

    private void c(View view, int i2) {
        j jVar = new j(j(view), i2, view);
        jVar.setDuration(this.k0);
        jVar.setInterpolator(this.n0);
        view.startAnimation(jVar);
    }

    private boolean d() {
        return this.h == null && !(this.X == null && this.W == null);
    }

    private void g() {
        AnimationAnimationListenerC0046c animationAnimationListenerC0046c = new AnimationAnimationListenerC0046c();
        int firstVisiblePosition = this.B.getFirstVisiblePosition();
        boolean z = false;
        for (int i2 = 0; i2 < this.B.getChildCount(); i2++) {
            View childAt = this.B.getChildAt(i2);
            if (this.E.contains((l0.h) this.C.getItem(firstVisiblePosition + i2))) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(this.l0);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                if (!z) {
                    alphaAnimation.setAnimationListener(animationAnimationListenerC0046c);
                    z = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(alphaAnimation);
            }
        }
    }

    private static int j(View view) {
        return view.getLayoutParams().height;
    }

    private int k(boolean z) {
        if (!z && this.z.getVisibility() != 0) {
            return 0;
        }
        int paddingTop = 0 + this.x.getPaddingTop() + this.x.getPaddingBottom();
        if (z) {
            paddingTop += this.y.getMeasuredHeight();
        }
        if (this.z.getVisibility() == 0) {
            paddingTop += this.z.getMeasuredHeight();
        }
        return (z && this.z.getVisibility() == 0) ? paddingTop + this.A.getMeasuredHeight() : paddingTop;
    }

    static boolean l(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    private boolean m() {
        return this.c.y() && this.c.l().size() > 1;
    }

    private boolean n() {
        MediaDescriptionCompat mediaDescriptionCompat = this.X;
        Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.X;
        Uri uriC = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        n nVar = this.Y;
        Bitmap bitmapB2 = nVar == null ? this.Z : nVar.b();
        n nVar2 = this.Y;
        Uri uriC2 = nVar2 == null ? this.b0 : nVar2.c();
        if (bitmapB2 != bitmapB) {
            return true;
        }
        return bitmapB2 == null && !H(uriC2, uriC);
    }

    private void u(boolean z) {
        List listL = this.c.l();
        if (listL.isEmpty()) {
            this.D.clear();
            this.C.notifyDataSetChanged();
            return;
        }
        if (androidx.mediarouter.app.f.i(this.D, listL)) {
            this.C.notifyDataSetChanged();
            return;
        }
        HashMap mapE = z ? androidx.mediarouter.app.f.e(this.B, this.C) : null;
        HashMap mapD = z ? androidx.mediarouter.app.f.d(this.d, this.B, this.C) : null;
        this.E = androidx.mediarouter.app.f.f(this.D, listL);
        this.F = androidx.mediarouter.app.f.g(this.D, listL);
        this.D.addAll(0, this.E);
        this.D.removeAll(this.F);
        this.C.notifyDataSetChanged();
        if (z && this.h0 && this.E.size() + this.F.size() > 0) {
            a(mapE, mapD);
        } else {
            this.E = null;
            this.F = null;
        }
    }

    static void v(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    private void w(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.S;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.g(this.V);
            this.S = null;
        }
        if (token != null && this.f) {
            MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.d, token);
            this.S = mediaControllerCompat2;
            mediaControllerCompat2.e(this.V);
            MediaMetadataCompat mediaMetadataCompatA = this.S.a();
            this.X = mediaMetadataCompatA != null ? mediaMetadataCompatA.d() : null;
            this.W = this.S.b();
            A();
            z(false);
        }
    }

    void A() {
        if (this.h == null && n()) {
            if (!m() || this.w) {
                n nVar = this.Y;
                if (nVar != null) {
                    nVar.cancel(true);
                }
                n nVar2 = new n();
                this.Y = nVar2;
                nVar2.execute(new Void[0]);
            }
        }
    }

    void B(boolean z) {
        this.p.requestLayout();
        this.p.getViewTreeObserver().addOnGlobalLayoutListener(new i(z));
    }

    void C(boolean z) {
        int i2;
        Bitmap bitmap;
        int iJ = j(this.x);
        v(this.x, -1);
        D(d());
        View decorView = getWindow().getDecorView();
        decorView.measure(View.MeasureSpec.makeMeasureSpec(getWindow().getAttributes().width, 1073741824), 0);
        v(this.x, iJ);
        if (this.h == null && (this.r.getDrawable() instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) this.r.getDrawable()).getBitmap()) != null) {
            i2 = i(bitmap.getWidth(), bitmap.getHeight());
            this.r.setScaleType(bitmap.getWidth() >= bitmap.getHeight() ? ImageView.ScaleType.FIT_XY : ImageView.ScaleType.FIT_CENTER);
        } else {
            i2 = 0;
        }
        int iK = k(d());
        int size = this.D.size();
        int size2 = m() ? this.L * this.c.l().size() : 0;
        if (size > 0) {
            size2 += this.N;
        }
        int iMin = Math.min(size2, this.M);
        if (!this.h0) {
            iMin = 0;
        }
        int iMax = Math.max(i2, iMin) + iK;
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int iHeight = rect.height() - (this.o.getMeasuredHeight() - this.p.getMeasuredHeight());
        if (this.h != null || i2 <= 0 || iMax > iHeight) {
            if (j(this.B) + this.x.getMeasuredHeight() >= this.p.getMeasuredHeight()) {
                this.r.setVisibility(8);
            }
            iMax = iMin + iK;
            i2 = 0;
        } else {
            this.r.setVisibility(0);
            v(this.r, i2);
        }
        if (!d() || iMax > iHeight) {
            this.y.setVisibility(8);
        } else {
            this.y.setVisibility(0);
        }
        D(this.y.getVisibility() == 0);
        int iK2 = k(this.y.getVisibility() == 0);
        int iMax2 = Math.max(i2, iMin) + iK2;
        if (iMax2 > iHeight) {
            iMin -= iMax2 - iHeight;
        } else {
            iHeight = iMax2;
        }
        this.x.clearAnimation();
        this.B.clearAnimation();
        this.p.clearAnimation();
        if (z) {
            c(this.x, iK2);
            c(this.B, iMin);
            c(this.p, iHeight);
        } else {
            v(this.x, iK2);
            v(this.B, iMin);
            v(this.p, iHeight);
        }
        v(this.n, rect.height());
        u(z);
    }

    void G(View view) {
        v((LinearLayout) view.findViewById(R$id.volume_item_container), this.L);
        View viewFindViewById = view.findViewById(R$id.mr_volume_item_icon);
        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
        int i2 = this.K;
        layoutParams.width = i2;
        layoutParams.height = i2;
        viewFindViewById.setLayoutParams(layoutParams);
    }

    void b(Map map, Map map2) {
        OverlayListView.a aVarD;
        Set set = this.E;
        if (set == null || this.F == null) {
            return;
        }
        int size = set.size() - this.F.size();
        l lVar = new l();
        int firstVisiblePosition = this.B.getFirstVisiblePosition();
        boolean z = false;
        for (int i2 = 0; i2 < this.B.getChildCount(); i2++) {
            View childAt = this.B.getChildAt(i2);
            Object obj = (l0.h) this.C.getItem(firstVisiblePosition + i2);
            Rect rect = (Rect) map.get(obj);
            int top = childAt.getTop();
            int i3 = rect != null ? rect.top : (this.L * size) + top;
            AnimationSet animationSet = new AnimationSet(true);
            Set set2 = this.E;
            if (set2 != null && set2.contains(obj)) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                alphaAnimation.setDuration(this.l0);
                animationSet.addAnimation(alphaAnimation);
                i3 = top;
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, i3 - top, 0.0f);
            translateAnimation.setDuration(this.k0);
            animationSet.addAnimation(translateAnimation);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            animationSet.setInterpolator(this.n0);
            if (!z) {
                animationSet.setAnimationListener(lVar);
                z = true;
            }
            childAt.clearAnimation();
            childAt.startAnimation(animationSet);
            map.remove(obj);
            map2.remove(obj);
        }
        for (Map.Entry entry : map2.entrySet()) {
            l0.h hVar = (l0.h) entry.getKey();
            BitmapDrawable bitmapDrawable = (BitmapDrawable) entry.getValue();
            Rect rect2 = (Rect) map.get(hVar);
            if (this.F.contains(hVar)) {
                aVarD = new OverlayListView.a(bitmapDrawable, rect2).c(1.0f, 0.0f).e(this.m0).f(this.n0);
            } else {
                aVarD = new OverlayListView.a(bitmapDrawable, rect2).g(this.L * size).e(this.k0).f(this.n0).d(new a(hVar));
                this.G.add(hVar);
            }
            this.B.a(aVarD);
        }
    }

    void e(boolean z) {
        Set set;
        int firstVisiblePosition = this.B.getFirstVisiblePosition();
        for (int i2 = 0; i2 < this.B.getChildCount(); i2++) {
            View childAt = this.B.getChildAt(i2);
            l0.h hVar = (l0.h) this.C.getItem(firstVisiblePosition + i2);
            if (!z || (set = this.E) == null || !set.contains(hVar)) {
                ((LinearLayout) childAt.findViewById(R$id.volume_item_container)).setVisibility(0);
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
                alphaAnimation.setDuration(0L);
                animationSet.addAnimation(alphaAnimation);
                new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f).setDuration(0L);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                childAt.clearAnimation();
                childAt.startAnimation(animationSet);
            }
        }
        this.B.c();
        if (z) {
            return;
        }
        h(false);
    }

    void f() {
        this.c0 = false;
        this.d0 = null;
        this.e0 = 0;
    }

    void h(boolean z) {
        this.E = null;
        this.F = null;
        this.i0 = false;
        if (this.j0) {
            this.j0 = false;
            B(z);
        }
        this.B.setEnabled(true);
    }

    int i(int i2, int i3) {
        return i2 >= i3 ? (int) (((this.g * i3) / i2) + 0.5f) : (int) (((this.g * 9.0f) / 16.0f) + 0.5f);
    }

    boolean o() {
        return (this.W.b() & 514) != 0;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f = true;
        this.a.b(k0.c, this.b, 2);
        w(this.a.k());
    }

    @Override // androidx.appcompat.app.c, androidx.appcompat.app.n, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R$layout.mr_controller_material_dialog_b);
        findViewById(R.id.button3).setVisibility(8);
        m mVar = new m();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.mr_expandable_area);
        this.n = frameLayout;
        frameLayout.setOnClickListener(new e());
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.mr_dialog_area);
        this.o = linearLayout;
        linearLayout.setOnClickListener(new f());
        int iD = androidx.mediarouter.app.i.d(this.d);
        Button button = (Button) findViewById(R.id.button2);
        this.i = button;
        button.setText(R$string.mr_controller_disconnect);
        this.i.setTextColor(iD);
        this.i.setOnClickListener(mVar);
        Button button2 = (Button) findViewById(R.id.button1);
        this.j = button2;
        button2.setText(R$string.mr_controller_stop_casting);
        this.j.setTextColor(iD);
        this.j.setOnClickListener(mVar);
        this.u = (TextView) findViewById(R$id.mr_name);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_close);
        this.l = imageButton;
        imageButton.setOnClickListener(mVar);
        this.q = (FrameLayout) findViewById(R$id.mr_custom_control);
        this.p = (FrameLayout) findViewById(R$id.mr_default_control);
        g gVar = new g();
        ImageView imageView = (ImageView) findViewById(R$id.mr_art);
        this.r = imageView;
        imageView.setOnClickListener(gVar);
        findViewById(R$id.mr_control_title_container).setOnClickListener(gVar);
        this.x = (LinearLayout) findViewById(R$id.mr_media_main_control);
        this.A = findViewById(R$id.mr_control_divider);
        this.y = (RelativeLayout) findViewById(R$id.mr_playback_control);
        this.f29s = (TextView) findViewById(R$id.mr_control_title);
        this.t = (TextView) findViewById(R$id.mr_control_subtitle);
        ImageButton imageButton2 = (ImageButton) findViewById(R$id.mr_control_playback_ctrl);
        this.k = imageButton2;
        imageButton2.setOnClickListener(mVar);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R$id.mr_volume_control);
        this.z = linearLayout2;
        linearLayout2.setVisibility(8);
        SeekBar seekBar = (SeekBar) findViewById(R$id.mr_volume_slider);
        this.H = seekBar;
        seekBar.setTag(this.c);
        q qVar = new q();
        this.I = qVar;
        this.H.setOnSeekBarChangeListener(qVar);
        this.B = (OverlayListView) findViewById(R$id.mr_volume_group_list);
        this.D = new ArrayList();
        r rVar = new r(this.B.getContext(), this.D);
        this.C = rVar;
        this.B.setAdapter((ListAdapter) rVar);
        this.G = new HashSet();
        androidx.mediarouter.app.i.u(this.d, this.x, this.B, m());
        androidx.mediarouter.app.i.w(this.d, (MediaRouteVolumeSlider) this.H, this.x);
        HashMap map = new HashMap();
        this.Q = map;
        map.put(this.c, this.H);
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = (MediaRouteExpandCollapseButton) findViewById(R$id.mr_group_expand_collapse);
        this.m = mediaRouteExpandCollapseButton;
        mediaRouteExpandCollapseButton.setOnClickListener(new h());
        s();
        this.k0 = this.d.getResources().getInteger(R$integer.mr_controller_volume_group_list_animation_duration_ms);
        this.l0 = this.d.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_in_duration_ms);
        this.m0 = this.d.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_out_duration_ms);
        View viewT = t(bundle);
        this.h = viewT;
        if (viewT != null) {
            this.q.addView(viewT);
            this.q.setVisibility(0);
        }
        this.e = true;
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.a.s(this.b);
        w(null);
        this.f = false;
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 25 && i2 != 24) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (this.w || !this.h0) {
            this.c.H(i2 == 25 ? -1 : 1);
        }
        return true;
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 25 || i2 == 24) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    boolean p() {
        return (this.W.b() & 516) != 0;
    }

    boolean q() {
        return (this.W.b() & 1) != 0;
    }

    boolean r(l0.h hVar) {
        return this.v && hVar.t() == 1;
    }

    void s() {
        this.n0 = this.h0 ? this.o0 : this.p0;
    }

    public View t(Bundle bundle) {
        return null;
    }

    void updateLayout() {
        int iB = androidx.mediarouter.app.f.b(this.d);
        getWindow().setLayout(iB, -2);
        View decorView = getWindow().getDecorView();
        this.g = (iB - decorView.getPaddingLeft()) - decorView.getPaddingRight();
        Resources resources = this.d.getResources();
        this.K = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_icon_size);
        this.L = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_height);
        this.M = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_max_height);
        this.Z = null;
        this.b0 = null;
        A();
        z(false);
    }

    void x() {
        e(true);
        this.B.requestLayout();
        this.B.getViewTreeObserver().addOnGlobalLayoutListener(new b());
    }

    void y() {
        Set set = this.E;
        if (set == null || set.size() == 0) {
            h(true);
        } else {
            g();
        }
    }

    void z(boolean z) {
        if (this.J != null) {
            this.f0 = true;
            this.g0 = z | this.g0;
            return;
        }
        this.f0 = false;
        this.g0 = false;
        if (!this.c.C() || this.c.w()) {
            dismiss();
            return;
        }
        if (this.e) {
            this.u.setText(this.c.m());
            this.i.setVisibility(this.c.a() ? 0 : 8);
            if (this.h == null && this.c0) {
                if (l(this.d0)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Can't set artwork image with recycled bitmap: ");
                    sb.append(this.d0);
                } else {
                    this.r.setImageBitmap(this.d0);
                    this.r.setBackgroundColor(this.e0);
                }
                f();
            }
            F();
            E();
            B(z);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public c(Context context, int i2) {
        Context contextB = androidx.mediarouter.app.i.b(context, i2, true);
        super(contextB, androidx.mediarouter.app.i.c(contextB));
        this.v = true;
        this.s0 = new d();
        this.d = getContext();
        this.V = new o();
        l0 l0VarJ = l0.j(this.d);
        this.a = l0VarJ;
        this.w = l0.o();
        this.b = new p();
        this.c = l0VarJ.n();
        w(l0VarJ.k());
        this.N = this.d.getResources().getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_padding_top);
        this.r0 = (AccessibilityManager) this.d.getSystemService("accessibility");
        this.o0 = AnimationUtils.loadInterpolator(contextB, R$interpolator.mr_linear_out_slow_in);
        this.p0 = AnimationUtils.loadInterpolator(contextB, R$interpolator.mr_fast_out_slow_in);
        this.q0 = new AccelerateDecelerateInterpolator();
    }
}
