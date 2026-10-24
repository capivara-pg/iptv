package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import h0.g0;
import h0.j0;
import h0.k0;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class h extends androidx.appcompat.app.j {
    static final boolean Q = Log.isLoggable("MediaRouteCtrlDialog", 3);
    private View A;
    ImageView B;
    private TextView C;
    private TextView D;
    private String E;
    MediaControllerCompat F;
    e G;
    MediaDescriptionCompat H;
    d I;
    Bitmap J;
    Uri K;
    boolean L;
    Bitmap M;
    int N;
    final boolean O;
    final k0 a;
    private final g b;
    private j0 c;
    k0.h d;
    final List e;
    final List f;
    final List g;
    final List h;
    Context i;
    private boolean j;
    private boolean k;
    private long l;
    final Handler m;
    RecyclerView n;
    C0041h o;
    j p;
    Map q;
    k0.h r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    Map f30s;
    boolean t;
    boolean u;
    private boolean v;
    private boolean w;
    private ImageButton x;
    private Button y;
    private ImageView z;

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                h.this.k();
                return;
            }
            if (i != 2) {
                return;
            }
            h hVar = h.this;
            if (hVar.r != null) {
                hVar.r = null;
                hVar.l();
            }
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h.this.dismiss();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (h.this.d.C()) {
                h.this.a.z(2);
            }
            h.this.dismiss();
        }
    }

    private class d extends AsyncTask {
        private final Bitmap a;
        private final Uri b;
        private int c;

        d() {
            MediaDescriptionCompat mediaDescriptionCompat = h.this.H;
            Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
            this.a = h.d(bitmapB) ? null : bitmapB;
            MediaDescriptionCompat mediaDescriptionCompat2 = h.this.H;
            this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        }

        private InputStream e(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = h.this.i.getContentResolver().openInputStream(uri);
            } else {
                URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(30000);
                uRLConnectionOpenConnection.setReadTimeout(30000);
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
                                options.inSampleSize = Math.max(1, Integer.highestOneBit(options.outHeight / h.this.i.getResources().getDimensionPixelSize(R$dimen.mr_cast_meta_art_size)));
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
            if (h.d(bitmap)) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Can't use recycled bitmap: ");
                sb4.append(bitmap);
                return null;
            }
            if (bitmap != null && bitmap.getWidth() < bitmap.getHeight()) {
                j0.b bVarA = new j0.b.b(bitmap).c(1).a();
                this.c = bVarA.f().isEmpty() ? 0 : ((j0.b.d) bVarA.f().get(0)).e();
            }
            return bitmap;
        }

        Bitmap b() {
            return this.a;
        }

        Uri c() {
            return this.b;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            h hVar = h.this;
            hVar.I = null;
            if (x.c.a(hVar.J, this.a) && x.c.a(h.this.K, this.b)) {
                return;
            }
            h hVar2 = h.this;
            hVar2.J = this.a;
            hVar2.M = bitmap;
            hVar2.K = this.b;
            hVar2.N = this.c;
            hVar2.L = true;
            hVar2.i();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            h.this.b();
        }
    }

    private final class e extends MediaControllerCompat.a {
        e() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            h.this.H = mediaMetadataCompat == null ? null : mediaMetadataCompat.e();
            h.this.e();
            h.this.i();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void i() {
            h hVar = h.this;
            MediaControllerCompat mediaControllerCompat = hVar.F;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.g(hVar.G);
                h.this.F = null;
            }
        }
    }

    private abstract class f extends RecyclerView.ViewHolder {
        k0.h a;
        final ImageButton b;
        final MediaRouteVolumeSlider c;

        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                h hVar = h.this;
                if (hVar.r != null) {
                    hVar.m.removeMessages(2);
                }
                f fVar = f.this;
                h.this.r = fVar.a;
                boolean z = !view.isActivated();
                int iB = z ? 0 : f.this.b();
                f.this.c(z);
                f.this.c.setProgress(iB);
                f.this.a.G(iB);
                h.this.m.sendEmptyMessageDelayed(2, 500L);
            }
        }

        f(View view, ImageButton imageButton, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
            super(view);
            this.b = imageButton;
            this.c = mediaRouteVolumeSlider;
            imageButton.setImageDrawable(androidx.mediarouter.app.i.k(h.this.i));
            androidx.mediarouter.app.i.v(h.this.i, mediaRouteVolumeSlider);
        }

        void a(k0.h hVar) {
            this.a = hVar;
            int iS = hVar.s();
            this.b.setActivated(iS == 0);
            this.b.setOnClickListener(new a());
            this.c.setTag(this.a);
            this.c.setMax(hVar.u());
            this.c.setProgress(iS);
            this.c.setOnSeekBarChangeListener(h.this.p);
        }

        int b() {
            Integer num = (Integer) h.this.f30s.get(this.a.k());
            if (num == null) {
                return 1;
            }
            return Math.max(1, num.intValue());
        }

        void c(boolean z) {
            if (this.b.isActivated() == z) {
                return;
            }
            this.b.setActivated(z);
            if (z) {
                h.this.f30s.put(this.a.k(), Integer.valueOf(this.c.getProgress()));
            } else {
                h.this.f30s.remove(this.a.k());
            }
        }

        void d() {
            int iS = this.a.s();
            c(iS == 0);
            this.c.setProgress(iS);
        }
    }

    private final class g extends k0.a {
        g() {
        }

        public void onRouteAdded(k0 k0Var, k0.h hVar) {
            h.this.k();
        }

        public void onRouteChanged(k0 k0Var, k0.h hVar) {
            boolean z;
            k0.h.a aVarH;
            if (hVar == h.this.d && hVar.g() != null) {
                Iterator it = hVar.q().f().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    k0.h hVar2 = (k0.h) it.next();
                    if (!h.this.d.l().contains(hVar2) && (aVarH = h.this.d.h(hVar2)) != null && aVarH.b() && !h.this.f.contains(hVar2)) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            if (!z) {
                h.this.k();
            } else {
                h.this.l();
                h.this.j();
            }
        }

        public void onRouteRemoved(k0 k0Var, k0.h hVar) {
            h.this.k();
        }

        public void onRouteSelected(k0 k0Var, k0.h hVar) {
            h hVar2 = h.this;
            hVar2.d = hVar;
            hVar2.t = false;
            hVar2.l();
            h.this.j();
        }

        public void onRouteUnselected(k0 k0Var, k0.h hVar) {
            h.this.k();
        }

        public void onRouteVolumeChanged(k0 k0Var, k0.h hVar) {
            f fVar;
            int iS = hVar.s();
            if (h.Q) {
                StringBuilder sb = new StringBuilder();
                sb.append("onRouteVolumeChanged(), route.getVolume:");
                sb.append(iS);
            }
            h hVar2 = h.this;
            if (hVar2.r == hVar || (fVar = (f) hVar2.q.get(hVar.k())) == null) {
                return;
            }
            fVar.d();
        }
    }

    /* JADX INFO: renamed from: androidx.mediarouter.app.h$h, reason: collision with other inner class name */
    private final class C0041h extends RecyclerView.Adapter {
        private final LayoutInflater b;
        private final Drawable c;
        private final Drawable d;
        private final Drawable e;
        private final Drawable f;
        private f g;
        private final int h;
        private final ArrayList a = new ArrayList();
        private final Interpolator i = new AccelerateDecelerateInterpolator();

        /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$a */
        class a extends Animation {
            final /* synthetic */ int a;
            final /* synthetic */ int b;
            final /* synthetic */ View c;

            a(int i, int i2, View view) {
                this.a = i;
                this.b = i2;
                this.c = view;
            }

            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                int i = this.a;
                int i2 = this.b;
                h.f(this.c, i2 + ((int) ((i - i2) * f)));
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$b */
        class b implements Animation.AnimationListener {
            b() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                h hVar = h.this;
                hVar.u = false;
                hVar.l();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                h.this.u = true;
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$c */
        private class c extends RecyclerView.ViewHolder {
            final View a;
            final ImageView b;
            final ProgressBar c;
            final TextView d;
            final float e;
            k0.h f;

            /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$c$a */
            class a implements View.OnClickListener {
                a() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    c cVar = c.this;
                    h.this.a.y(cVar.f);
                    c.this.b.setVisibility(4);
                    c.this.c.setVisibility(0);
                }
            }

            c(View view) {
                super(view);
                this.a = view;
                this.b = (ImageView) view.findViewById(R$id.mr_cast_group_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_cast_group_progress_bar);
                this.c = progressBar;
                this.d = (TextView) view.findViewById(R$id.mr_cast_group_name);
                this.e = androidx.mediarouter.app.i.h(h.this.i);
                androidx.mediarouter.app.i.t(h.this.i, progressBar);
            }

            private boolean b(k0.h hVar) {
                List listL = h.this.d.l();
                return (listL.size() == 1 && listL.get(0) == hVar) ? false : true;
            }

            void a(f fVar) {
                k0.h hVar = (k0.h) fVar.a();
                this.f = hVar;
                this.b.setVisibility(0);
                this.c.setVisibility(4);
                this.a.setAlpha(b(hVar) ? 1.0f : this.e);
                this.a.setOnClickListener(new a());
                this.b.setImageDrawable(C0041h.this.c(hVar));
                this.d.setText(hVar.m());
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$d */
        private class d extends f {
            private final TextView e;
            private final int f;

            d(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.e = (TextView) view.findViewById(R$id.mr_group_volume_route_name);
                Resources resources = h.this.i.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_volume_group_list_item_height, typedValue, true);
                this.f = (int) typedValue.getDimension(displayMetrics);
            }

            void e(f fVar) {
                h.f(this.itemView, C0041h.this.e() ? this.f : 0);
                k0.h hVar = (k0.h) fVar.a();
                super.a(hVar);
                this.e.setText(hVar.m());
            }

            int f() {
                return this.f;
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$e */
        private class e extends RecyclerView.ViewHolder {
            private final TextView a;

            e(View view) {
                super(view);
                this.a = (TextView) view.findViewById(R$id.mr_cast_header_name);
            }

            void a(f fVar) {
                this.a.setText(fVar.a().toString());
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$f */
        private class f {
            private final Object a;
            private final int b;

            f(Object obj, int i) {
                this.a = obj;
                this.b = i;
            }

            public Object a() {
                return this.a;
            }

            public int b() {
                return this.b;
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$g */
        private class g extends f {
            final View e;
            final ImageView f;
            final ProgressBar g;
            final TextView h;
            final RelativeLayout i;
            final CheckBox j;
            final float k;
            final int l;
            final int m;
            final View.OnClickListener n;

            /* JADX INFO: renamed from: androidx.mediarouter.app.h$h$g$a */
            class a implements View.OnClickListener {
                a() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    g gVar = g.this;
                    boolean z = !gVar.g(gVar.a);
                    boolean zY = g.this.a.y();
                    if (z) {
                        g gVar2 = g.this;
                        h.this.a.c(gVar2.a);
                    } else {
                        g gVar3 = g.this;
                        h.this.a.t(gVar3.a);
                    }
                    g.this.h(z, !zY);
                    if (zY) {
                        List listL = h.this.d.l();
                        for (k0.h hVar : g.this.a.l()) {
                            if (listL.contains(hVar) != z) {
                                f fVar = (f) h.this.q.get(hVar.k());
                                if (fVar instanceof g) {
                                    ((g) fVar).h(z, true);
                                }
                            }
                        }
                    }
                    g gVar4 = g.this;
                    C0041h.this.f(gVar4.a, z);
                }
            }

            g(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.n = new a();
                this.e = view;
                this.f = (ImageView) view.findViewById(R$id.mr_cast_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_cast_route_progress_bar);
                this.g = progressBar;
                this.h = (TextView) view.findViewById(R$id.mr_cast_route_name);
                this.i = (RelativeLayout) view.findViewById(R$id.mr_cast_volume_layout);
                CheckBox checkBox = (CheckBox) view.findViewById(R$id.mr_cast_checkbox);
                this.j = checkBox;
                checkBox.setButtonDrawable(androidx.mediarouter.app.i.e(h.this.i));
                androidx.mediarouter.app.i.t(h.this.i, progressBar);
                this.k = androidx.mediarouter.app.i.h(h.this.i);
                Resources resources = h.this.i.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_dialog_row_height, typedValue, true);
                this.l = (int) typedValue.getDimension(displayMetrics);
                this.m = 0;
            }

            private boolean f(k0.h hVar) {
                if (h.this.h.contains(hVar)) {
                    return false;
                }
                if (g(hVar) && h.this.d.l().size() < 2) {
                    return false;
                }
                if (!g(hVar)) {
                    return true;
                }
                k0.h.a aVarH = h.this.d.h(hVar);
                return aVarH != null && aVarH.d();
            }

            void e(f fVar) {
                k0.h hVar = (k0.h) fVar.a();
                if (hVar == h.this.d && hVar.l().size() > 0) {
                    for (k0.h hVar2 : hVar.l()) {
                        if (!h.this.f.contains(hVar2)) {
                            hVar = hVar2;
                            break;
                        }
                    }
                }
                a(hVar);
                this.f.setImageDrawable(C0041h.this.c(hVar));
                this.h.setText(hVar.m());
                this.j.setVisibility(0);
                boolean zG = g(hVar);
                boolean zF = f(hVar);
                this.j.setChecked(zG);
                this.g.setVisibility(4);
                this.f.setVisibility(0);
                this.e.setEnabled(zF);
                this.j.setEnabled(zF);
                this.b.setEnabled(zF || zG);
                this.c.setEnabled(zF || zG);
                this.e.setOnClickListener(this.n);
                this.j.setOnClickListener(this.n);
                h.f(this.i, (!zG || this.a.y()) ? this.m : this.l);
                float f = 1.0f;
                this.e.setAlpha((zF || zG) ? 1.0f : this.k);
                CheckBox checkBox = this.j;
                if (!zF && zG) {
                    f = this.k;
                }
                checkBox.setAlpha(f);
            }

            boolean g(k0.h hVar) {
                if (hVar.C()) {
                    return true;
                }
                k0.h.a aVarH = h.this.d.h(hVar);
                return aVarH != null && aVarH.a() == 3;
            }

            void h(boolean z, boolean z2) {
                this.j.setEnabled(false);
                this.e.setEnabled(false);
                this.j.setChecked(z);
                if (z) {
                    this.f.setVisibility(4);
                    this.g.setVisibility(0);
                }
                if (z2) {
                    C0041h.this.a(this.i, z ? this.l : this.m);
                }
            }
        }

        C0041h() {
            this.b = LayoutInflater.from(h.this.i);
            this.c = androidx.mediarouter.app.i.g(h.this.i);
            this.d = androidx.mediarouter.app.i.q(h.this.i);
            this.e = androidx.mediarouter.app.i.m(h.this.i);
            this.f = androidx.mediarouter.app.i.n(h.this.i);
            this.h = h.this.i.getResources().getInteger(R$integer.mr_cast_volume_slider_layout_animation_duration_ms);
            h();
        }

        private Drawable b(k0.h hVar) {
            int iF = hVar.f();
            if (iF == 1) {
                return this.d;
            }
            if (iF != 2) {
                return hVar.y() ? this.f : this.c;
            }
            return this.e;
        }

        void a(View view, int i) {
            a aVar = new a(i, view.getLayoutParams().height, view);
            aVar.setAnimationListener(new b());
            aVar.setDuration(this.h);
            aVar.setInterpolator(this.i);
            view.startAnimation(aVar);
        }

        Drawable c(k0.h hVar) {
            Uri uriJ = hVar.j();
            if (uriJ != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(h.this.i.getContentResolver().openInputStream(uriJ), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to load ");
                    sb.append(uriJ);
                }
            }
            return b(hVar);
        }

        public f d(int i) {
            return i == 0 ? this.g : (f) this.a.get(i - 1);
        }

        boolean e() {
            h hVar = h.this;
            return hVar.O && hVar.d.l().size() > 1;
        }

        void f(k0.h hVar, boolean z) {
            List listL = h.this.d.l();
            int iMax = Math.max(1, listL.size());
            if (hVar.y()) {
                Iterator it = hVar.l().iterator();
                while (it.hasNext()) {
                    if (listL.contains((k0.h) it.next()) != z) {
                        iMax += z ? 1 : -1;
                    }
                }
            } else {
                iMax += z ? 1 : -1;
            }
            boolean zE = e();
            h hVar2 = h.this;
            boolean z2 = hVar2.O && iMax >= 2;
            if (zE != z2) {
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = hVar2.n.findViewHolderForAdapterPosition(0);
                if (viewHolderFindViewHolderForAdapterPosition instanceof d) {
                    d dVar = (d) viewHolderFindViewHolderForAdapterPosition;
                    a(dVar.itemView, z2 ? dVar.f() : 0);
                }
            }
        }

        void g() {
            h.this.h.clear();
            h hVar = h.this;
            hVar.h.addAll(androidx.mediarouter.app.f.g(hVar.f, hVar.c()));
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.a.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return d(i).b();
        }

        void h() {
            this.a.clear();
            this.g = new f(h.this.d, 1);
            if (h.this.e.isEmpty()) {
                this.a.add(new f(h.this.d, 3));
            } else {
                Iterator it = h.this.e.iterator();
                while (it.hasNext()) {
                    this.a.add(new f((k0.h) it.next(), 3));
                }
            }
            boolean z = false;
            if (!h.this.f.isEmpty()) {
                boolean z2 = false;
                for (k0.h hVar : h.this.f) {
                    if (!h.this.e.contains(hVar)) {
                        if (!z2) {
                            g0.b bVarG = h.this.d.g();
                            String strJ = bVarG != null ? bVarG.j() : null;
                            if (TextUtils.isEmpty(strJ)) {
                                strJ = h.this.i.getString(R$string.mr_dialog_groupable_header);
                            }
                            this.a.add(new f(strJ, 2));
                            z2 = true;
                        }
                        this.a.add(new f(hVar, 3));
                    }
                }
            }
            if (!h.this.g.isEmpty()) {
                for (k0.h hVar2 : h.this.g) {
                    k0.h hVar3 = h.this.d;
                    if (hVar3 != hVar2) {
                        if (!z) {
                            g0.b bVarG2 = hVar3.g();
                            String strK = bVarG2 != null ? bVarG2.k() : null;
                            if (TextUtils.isEmpty(strK)) {
                                strK = h.this.i.getString(R$string.mr_dialog_transferable_header);
                            }
                            this.a.add(new f(strK, 2));
                            z = true;
                        }
                        this.a.add(new f(hVar2, 4));
                    }
                }
            }
            g();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int itemViewType = getItemViewType(i);
            f fVarD = d(i);
            if (itemViewType == 1) {
                h.this.q.put(((k0.h) fVarD.a()).k(), (f) viewHolder);
                ((d) viewHolder).e(fVarD);
            } else {
                if (itemViewType == 2) {
                    ((e) viewHolder).a(fVarD);
                    return;
                }
                if (itemViewType != 3) {
                    if (itemViewType != 4) {
                        return;
                    }
                    ((c) viewHolder).a(fVarD);
                } else {
                    h.this.q.put(((k0.h) fVarD.a()).k(), (f) viewHolder);
                    ((g) viewHolder).e(fVarD);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new d(this.b.inflate(R$layout.mr_cast_group_volume_item, viewGroup, false));
            }
            if (i == 2) {
                return new e(this.b.inflate(R$layout.mr_cast_header_item, viewGroup, false));
            }
            if (i == 3) {
                return new g(this.b.inflate(R$layout.mr_cast_route_item, viewGroup, false));
            }
            if (i != 4) {
                return null;
            }
            return new c(this.b.inflate(R$layout.mr_cast_group_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
            super.onViewRecycled(viewHolder);
            h.this.q.values().remove(viewHolder);
        }
    }

    static final class i implements Comparator {
        static final i a = new i();

        i() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(k0.h hVar, k0.h hVar2) {
            return hVar.m().compareToIgnoreCase(hVar2.m());
        }
    }

    private class j implements SeekBar.OnSeekBarChangeListener {
        j() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                k0.h hVar = (k0.h) seekBar.getTag();
                f fVar = (f) h.this.q.get(hVar.k());
                if (fVar != null) {
                    fVar.c(i == 0);
                }
                hVar.G(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            h hVar = h.this;
            if (hVar.r != null) {
                hVar.m.removeMessages(2);
            }
            h.this.r = (k0.h) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            h.this.m.sendEmptyMessageDelayed(2, 500L);
        }
    }

    public h(Context context) {
        this(context, 0);
    }

    private static Bitmap a(Bitmap bitmap, float f2, Context context) {
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        scriptIntrinsicBlurCreate.setRadius(f2);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        Bitmap bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
        allocationCreateTyped.copyTo(bitmapCopy);
        allocationCreateFromBitmap.destroy();
        allocationCreateTyped.destroy();
        scriptIntrinsicBlurCreate.destroy();
        renderScriptCreate.destroy();
        return bitmapCopy;
    }

    static boolean d(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    static void f(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    private void g(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.F;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.g(this.G);
            this.F = null;
        }
        if (token != null && this.k) {
            MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.i, token);
            this.F = mediaControllerCompat2;
            mediaControllerCompat2.e(this.G);
            MediaMetadataCompat mediaMetadataCompatA = this.F.a();
            this.H = mediaMetadataCompatA != null ? mediaMetadataCompatA.e() : null;
            e();
            i();
        }
    }

    private boolean h() {
        if (this.r != null || this.t || this.u) {
            return true;
        }
        return !this.j;
    }

    void b() {
        this.L = false;
        this.M = null;
        this.N = 0;
    }

    List c() {
        ArrayList arrayList = new ArrayList();
        for (k0.h hVar : this.d.q().f()) {
            k0.h.a aVarH = this.d.h(hVar);
            if (aVarH != null && aVarH.b()) {
                arrayList.add(hVar);
            }
        }
        return arrayList;
    }

    void e() {
        MediaDescriptionCompat mediaDescriptionCompat = this.H;
        Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.H;
        Uri uriC = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        d dVar = this.I;
        Bitmap bitmapB2 = dVar == null ? this.J : dVar.b();
        d dVar2 = this.I;
        Uri uriC2 = dVar2 == null ? this.K : dVar2.c();
        if (bitmapB2 != bitmapB || (bitmapB2 == null && !x.c.a(uriC2, uriC))) {
            d dVar3 = this.I;
            if (dVar3 != null) {
                dVar3.cancel(true);
            }
            d dVar4 = new d();
            this.I = dVar4;
            dVar4.execute(new Void[0]);
        }
    }

    void i() {
        if (h()) {
            this.w = true;
            return;
        }
        this.w = false;
        if (!this.d.C() || this.d.w()) {
            dismiss();
        }
        if (!this.L || d(this.M) || this.M == null) {
            if (d(this.M)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Can't set artwork image with recycled bitmap: ");
                sb.append(this.M);
            }
            this.B.setVisibility(8);
            this.A.setVisibility(8);
            this.z.setImageBitmap(null);
        } else {
            this.B.setVisibility(0);
            this.B.setImageBitmap(this.M);
            this.B.setBackgroundColor(this.N);
            this.A.setVisibility(0);
            this.z.setImageBitmap(a(this.M, 10.0f, this.i));
        }
        b();
        MediaDescriptionCompat mediaDescriptionCompat = this.H;
        CharSequence charSequenceF = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.f();
        boolean z = !TextUtils.isEmpty(charSequenceF);
        MediaDescriptionCompat mediaDescriptionCompat2 = this.H;
        CharSequence charSequenceE = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.e() : null;
        boolean zIsEmpty = true ^ TextUtils.isEmpty(charSequenceE);
        if (z) {
            this.C.setText(charSequenceF);
        } else {
            this.C.setText(this.E);
        }
        if (!zIsEmpty) {
            this.D.setVisibility(8);
        } else {
            this.D.setText(charSequenceE);
            this.D.setVisibility(0);
        }
    }

    void j() {
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.e.addAll(this.d.l());
        for (k0.h hVar : this.d.q().f()) {
            k0.h.a aVarH = this.d.h(hVar);
            if (aVarH != null) {
                if (aVarH.b()) {
                    this.f.add(hVar);
                }
                if (aVarH.c()) {
                    this.g.add(hVar);
                }
            }
        }
        onFilterRoutes(this.f);
        onFilterRoutes(this.g);
        List list = this.e;
        i iVar = i.a;
        Collections.sort(list, iVar);
        Collections.sort(this.f, iVar);
        Collections.sort(this.g, iVar);
        this.o.h();
    }

    void k() {
        if (this.k) {
            if (SystemClock.uptimeMillis() - this.l < 300) {
                this.m.removeMessages(1);
                this.m.sendEmptyMessageAtTime(1, this.l + 300);
            } else {
                if (h()) {
                    this.v = true;
                    return;
                }
                this.v = false;
                if (!this.d.C() || this.d.w()) {
                    dismiss();
                }
                this.l = SystemClock.uptimeMillis();
                this.o.g();
            }
        }
    }

    void l() {
        if (this.v) {
            k();
        }
        if (this.w) {
            i();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.k = true;
        this.a.b(this.c, this.b, 1);
        j();
        g(this.a.k());
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_cast_dialog);
        androidx.mediarouter.app.i.s(this.i, this);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_cast_close_button);
        this.x = imageButton;
        imageButton.setColorFilter(-1);
        this.x.setOnClickListener(new b());
        Button button = (Button) findViewById(R$id.mr_cast_stop_button);
        this.y = button;
        button.setTextColor(-1);
        this.y.setOnClickListener(new c());
        this.o = new C0041h();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_cast_list);
        this.n = recyclerView;
        recyclerView.setAdapter(this.o);
        this.n.setLayoutManager(new LinearLayoutManager(this.i));
        this.p = new j();
        this.q = new HashMap();
        this.f30s = new HashMap();
        this.z = (ImageView) findViewById(R$id.mr_cast_meta_background);
        this.A = findViewById(R$id.mr_cast_meta_black_scrim);
        this.B = (ImageView) findViewById(R$id.mr_cast_meta_art);
        TextView textView = (TextView) findViewById(R$id.mr_cast_meta_title);
        this.C = textView;
        textView.setTextColor(-1);
        TextView textView2 = (TextView) findViewById(R$id.mr_cast_meta_subtitle);
        this.D = textView2;
        textView2.setTextColor(-1);
        this.E = this.i.getResources().getString(R$string.mr_cast_dialog_title_view_placeholder);
        this.j = true;
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.k = false;
        this.a.s(this.b);
        this.m.removeCallbacksAndMessages(null);
        g(null);
    }

    public boolean onFilterRoute(k0.h hVar) {
        return !hVar.w() && hVar.x() && hVar.E(this.c) && this.d != hVar;
    }

    public void onFilterRoutes(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!onFilterRoute((k0.h) list.get(size))) {
                list.remove(size);
            }
        }
    }

    public void setRouteSelector(j0 j0Var) {
        if (j0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.c.equals(j0Var)) {
            return;
        }
        this.c = j0Var;
        if (this.k) {
            this.a.s(this.b);
            this.a.b(j0Var, this.b, 1);
            j();
        }
    }

    void updateLayout() {
        getWindow().setLayout(androidx.mediarouter.app.f.c(this.i), androidx.mediarouter.app.f.a(this.i));
        this.J = null;
        this.K = null;
        e();
        i();
        k();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public h(Context context, int i2) {
        Context contextB = androidx.mediarouter.app.i.b(context, i2, false);
        super(contextB, androidx.mediarouter.app.i.c(contextB));
        this.c = j0.c;
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.m = new a();
        Context context2 = getContext();
        this.i = context2;
        k0 k0VarJ = k0.j(context2);
        this.a = k0VarJ;
        this.O = k0.o();
        this.b = new g();
        this.d = k0VarJ.n();
        this.G = new e();
        g(k0VarJ.k());
    }
}
