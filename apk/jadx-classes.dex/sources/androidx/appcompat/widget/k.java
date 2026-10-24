package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class k {
    private static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    private static k c;
    private e2 a;

    static class a implements e2.e {
        private final int[] a = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};
        private final int[] b = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};
        private final int[] c = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};
        private final int[] d = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
        private final int[] e = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
        private final int[] f = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

        a() {
        }

        private boolean f(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        private ColorStateList g(Context context) {
            return h(context, 0);
        }

        private ColorStateList h(Context context, int i) {
            int iB = n2.b(context, R$attr.colorControlHighlight);
            return new ColorStateList(new int[][]{n2.b, n2.e, n2.c, n2.i}, new int[]{n2.a(context, R$attr.colorButtonNormal), p.a.i(iB, i), p.a.i(iB, i), i});
        }

        private ColorStateList i(Context context) {
            return h(context, n2.b(context, R$attr.colorAccent));
        }

        private ColorStateList j(Context context) {
            return h(context, n2.b(context, R$attr.colorButtonNormal));
        }

        private ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i = R$attr.colorSwitchThumbNormal;
            ColorStateList colorStateListD = n2.d(context, i);
            if (colorStateListD == null || !colorStateListD.isStateful()) {
                iArr[0] = n2.b;
                iArr2[0] = n2.a(context, i);
                iArr[1] = n2.f;
                iArr2[1] = n2.b(context, R$attr.colorControlActivated);
                iArr[2] = n2.i;
                iArr2[2] = n2.b(context, i);
            } else {
                int[] iArr3 = n2.b;
                iArr[0] = iArr3;
                iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
                iArr[1] = n2.f;
                iArr2[1] = n2.b(context, R$attr.colorControlActivated);
                iArr[2] = n2.i;
                iArr2[2] = colorStateListD.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        private void l(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (o1.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = k.b;
            }
            drawable.setColorFilter(k.e(i, mode));
        }

        @Override // androidx.appcompat.widget.e2.e
        public Drawable a(e2 e2Var, Context context, int i) {
            if (i == R$drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{e2Var.j(context, R$drawable.abc_cab_background_internal_bg), e2Var.j(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        @Override // androidx.appcompat.widget.e2.e
        public ColorStateList b(Context context, int i) {
            if (i == R$drawable.abc_edit_text_material) {
                return d.b.c(context, R$color.abc_tint_edittext);
            }
            if (i == R$drawable.abc_switch_track_mtrl_alpha) {
                return d.b.c(context, R$color.abc_tint_switch_track);
            }
            if (i == R$drawable.abc_switch_thumb_material) {
                return k(context);
            }
            if (i == R$drawable.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i == R$drawable.abc_btn_borderless_material) {
                return g(context);
            }
            if (i == R$drawable.abc_btn_colored_material) {
                return i(context);
            }
            if (i == R$drawable.abc_spinner_mtrl_am_alpha || i == R$drawable.abc_spinner_textfield_background_material) {
                return d.b.c(context, R$color.abc_tint_spinner);
            }
            if (f(this.b, i)) {
                return n2.d(context, R$attr.colorControlNormal);
            }
            if (f(this.e, i)) {
                return d.b.c(context, R$color.abc_tint_default);
            }
            if (f(this.f, i)) {
                return d.b.c(context, R$color.abc_tint_btn_checkable);
            }
            if (i == R$drawable.abc_seekbar_thumb_material) {
                return d.b.c(context, R$color.abc_tint_seek_thumb);
            }
            return null;
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0052  */
        /* JADX WARN: Code duplicated, block: B:24:0x0058  */
        /* JADX WARN: Code duplicated, block: B:27:0x0069  */
        /* JADX WARN: Code duplicated, block: B:29:0x006d A[RETURN] */
        @Override // androidx.appcompat.widget.e2.e
        public boolean c(Context context, int i, Drawable drawable) {
            PorterDuff.Mode mode;
            int i2;
            int iRound;
            boolean z;
            PorterDuff.Mode mode2 = k.b;
            if (!f(this.a, i)) {
                if (f(this.c, i)) {
                    i2 = R$attr.colorControlActivated;
                } else {
                    if (f(this.d, i)) {
                        mode2 = PorterDuff.Mode.MULTIPLY;
                    } else if (i == R$drawable.abc_list_divider_mtrl_alpha) {
                        mode = mode2;
                        z = true;
                        iRound = Math.round(40.8f);
                        i2 = R.attr.colorForeground;
                    } else if (i != R$drawable.abc_dialog_material_background) {
                        mode = mode2;
                        i2 = 0;
                        iRound = -1;
                        z = false;
                    }
                    mode = mode2;
                    i2 = R.attr.colorBackground;
                    iRound = -1;
                    z = true;
                }
                if (z) {
                    return false;
                }
                if (o1.a(drawable)) {
                    drawable = drawable.mutate();
                }
                drawable.setColorFilter(k.e(n2.b(context, i2), mode));
                if (iRound != -1) {
                    drawable.setAlpha(iRound);
                }
                return true;
            }
            i2 = R$attr.colorControlNormal;
            mode = mode2;
            iRound = -1;
            z = true;
            if (z) {
                return false;
            }
            if (o1.a(drawable)) {
                drawable = drawable.mutate();
            }
            drawable.setColorFilter(k.e(n2.b(context, i2), mode));
            if (iRound != -1) {
                drawable.setAlpha(iRound);
            }
            return true;
        }

        @Override // androidx.appcompat.widget.e2.e
        public PorterDuff.Mode d(int i) {
            if (i == R$drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        @Override // androidx.appcompat.widget.e2.e
        public boolean e(Context context, int i, Drawable drawable) {
            if (i == R$drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i2 = R$attr.colorControlNormal;
                l(drawableFindDrawableByLayerId, n2.b(context, i2), k.b);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), n2.b(context, i2), k.b);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), n2.b(context, R$attr.colorControlActivated), k.b);
                return true;
            }
            if (i != R$drawable.abc_ratingbar_material && i != R$drawable.abc_ratingbar_indicator_material && i != R$drawable.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), n2.a(context, R$attr.colorControlNormal), k.b);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i3 = R$attr.colorControlActivated;
            l(drawableFindDrawableByLayerId2, n2.b(context, i3), k.b);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), n2.b(context, i3), k.b);
            return true;
        }
    }

    public static synchronized k b() {
        if (c == null) {
            h();
        }
        return c;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        return e2.l(i, mode);
    }

    public static synchronized void h() {
        if (c == null) {
            k kVar = new k();
            c = kVar;
            kVar.a = e2.h();
            c.a.u(new a());
        }
    }

    static void i(Drawable drawable, p2 p2Var, int[] iArr) {
        e2.w(drawable, p2Var, iArr);
    }

    public synchronized Drawable c(Context context, int i) {
        return this.a.j(context, i);
    }

    synchronized Drawable d(Context context, int i, boolean z) {
        return this.a.k(context, i, z);
    }

    synchronized ColorStateList f(Context context, int i) {
        return this.a.m(context, i);
    }

    public synchronized void g(Context context) {
        this.a.s(context);
    }
}
