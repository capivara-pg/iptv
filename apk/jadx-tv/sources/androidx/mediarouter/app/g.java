package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.j;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import h0.j0;
import h0.k0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g extends j {
    final k0 a;
    private final c b;
    Context c;
    private j0 d;
    List e;
    private ImageButton f;
    private d g;
    private RecyclerView h;
    private boolean i;
    k0.h j;
    private long k;
    private long l;
    private final Handler m;

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            g.this.updateRoutes((List) message.obj);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.dismiss();
        }
    }

    private final class c extends k0.a {
        c() {
        }

        public void onRouteAdded(k0 k0Var, k0.h hVar) {
            g.this.refreshRoutes();
        }

        public void onRouteChanged(k0 k0Var, k0.h hVar) {
            g.this.refreshRoutes();
        }

        public void onRouteRemoved(k0 k0Var, k0.h hVar) {
            g.this.refreshRoutes();
        }

        public void onRouteSelected(k0 k0Var, k0.h hVar) {
            g.this.dismiss();
        }
    }

    private final class d extends RecyclerView.Adapter {
        private final ArrayList a = new ArrayList();
        private final LayoutInflater b;
        private final Drawable c;
        private final Drawable d;
        private final Drawable e;
        private final Drawable f;

        private class a extends RecyclerView.ViewHolder {
            TextView a;

            a(View view) {
                super(view);
                this.a = (TextView) view.findViewById(R$id.mr_picker_header_name);
            }

            public void a(b bVar) {
                this.a.setText(bVar.a().toString());
            }
        }

        private class b {
            private final Object a;
            private final int b;

            b(Object obj) {
                this.a = obj;
                if (obj instanceof String) {
                    this.b = 1;
                } else if (obj instanceof k0.h) {
                    this.b = 2;
                } else {
                    this.b = 0;
                }
            }

            public Object a() {
                return this.a;
            }

            public int b() {
                return this.b;
            }
        }

        private class c extends RecyclerView.ViewHolder {
            final View a;
            final ImageView b;
            final ProgressBar c;
            final TextView d;

            class a implements View.OnClickListener {
                final /* synthetic */ k0.h a;

                a(k0.h hVar) {
                    this.a = hVar;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    g gVar = g.this;
                    k0.h hVar = this.a;
                    gVar.j = hVar;
                    hVar.I();
                    c.this.b.setVisibility(4);
                    c.this.c.setVisibility(0);
                }
            }

            c(View view) {
                super(view);
                this.a = view;
                this.b = (ImageView) view.findViewById(R$id.mr_picker_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_picker_route_progress_bar);
                this.c = progressBar;
                this.d = (TextView) view.findViewById(R$id.mr_picker_route_name);
                i.t(g.this.c, progressBar);
            }

            public void a(b bVar) {
                k0.h hVar = (k0.h) bVar.a();
                this.a.setVisibility(0);
                this.c.setVisibility(4);
                this.a.setOnClickListener(new a(hVar));
                this.d.setText(hVar.m());
                this.b.setImageDrawable(d.this.b(hVar));
            }
        }

        d() {
            this.b = LayoutInflater.from(g.this.c);
            this.c = i.g(g.this.c);
            this.d = i.q(g.this.c);
            this.e = i.m(g.this.c);
            this.f = i.n(g.this.c);
            d();
        }

        private Drawable a(k0.h hVar) {
            int iF = hVar.f();
            if (iF == 1) {
                return this.d;
            }
            if (iF != 2) {
                return hVar.y() ? this.f : this.c;
            }
            return this.e;
        }

        Drawable b(k0.h hVar) {
            Uri uriJ = hVar.j();
            if (uriJ != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(g.this.c.getContentResolver().openInputStream(uriJ), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to load ");
                    sb.append(uriJ);
                }
            }
            return a(hVar);
        }

        public b c(int i) {
            return (b) this.a.get(i);
        }

        void d() {
            this.a.clear();
            this.a.add(new b(g.this.c.getString(R$string.mr_chooser_title)));
            Iterator it = g.this.e.iterator();
            while (it.hasNext()) {
                this.a.add(new b((k0.h) it.next()));
            }
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.a.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return ((b) this.a.get(i)).b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int itemViewType = getItemViewType(i);
            b bVarC = c(i);
            if (itemViewType == 1) {
                ((a) viewHolder).a(bVarC);
            } else {
                if (itemViewType != 2) {
                    return;
                }
                ((c) viewHolder).a(bVarC);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new a(this.b.inflate(R$layout.mr_picker_header_item, viewGroup, false));
            }
            if (i != 2) {
                return null;
            }
            return new c(this.b.inflate(R$layout.mr_picker_route_item, viewGroup, false));
        }
    }

    static final class e implements Comparator {
        public static final e a = new e();

        e() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(k0.h hVar, k0.h hVar2) {
            return hVar.m().compareToIgnoreCase(hVar2.m());
        }
    }

    public g(Context context) {
        this(context, 0);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.i = true;
        this.a.b(this.d, this.b, 1);
        refreshRoutes();
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_picker_dialog);
        i.s(this.c, this);
        this.e = new ArrayList();
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_picker_close_button);
        this.f = imageButton;
        imageButton.setOnClickListener(new b());
        this.g = new d();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_picker_list);
        this.h = recyclerView;
        recyclerView.setAdapter(this.g);
        this.h.setLayoutManager(new LinearLayoutManager(this.c));
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i = false;
        this.a.s(this.b);
        this.m.removeMessages(1);
    }

    public boolean onFilterRoute(k0.h hVar) {
        return !hVar.w() && hVar.x() && hVar.E(this.d);
    }

    public void onFilterRoutes(List list) {
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            if (!onFilterRoute((k0.h) list.get(i))) {
                list.remove(i);
            }
            size = i;
        }
    }

    public void refreshRoutes() {
        if (this.j == null && this.i) {
            ArrayList arrayList = new ArrayList(this.a.m());
            onFilterRoutes(arrayList);
            Collections.sort(arrayList, e.a);
            if (SystemClock.uptimeMillis() - this.l >= this.k) {
                updateRoutes(arrayList);
                return;
            }
            this.m.removeMessages(1);
            Handler handler = this.m;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.l + this.k);
        }
    }

    public void setRouteSelector(j0 j0Var) {
        if (j0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.d.equals(j0Var)) {
            return;
        }
        this.d = j0Var;
        if (this.i) {
            this.a.s(this.b);
            this.a.b(j0Var, this.b, 1);
        }
        refreshRoutes();
    }

    void updateLayout() {
        getWindow().setLayout(f.c(this.c), f.a(this.c));
    }

    void updateRoutes(List list) {
        this.l = SystemClock.uptimeMillis();
        this.e.clear();
        this.e.addAll(list);
        this.g.d();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public g(Context context, int i) {
        Context contextB = i.b(context, i, false);
        super(contextB, i.c(contextB));
        this.d = j0.c;
        this.m = new a();
        Context context2 = getContext();
        this.a = k0.j(context2);
        this.b = new c();
        this.c = context2;
        this.k = context2.getResources().getInteger(R$integer.mr_update_routes_delay_ms);
    }
}
