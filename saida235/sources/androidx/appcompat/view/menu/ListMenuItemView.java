package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.h2;
import androidx.core.view.m0;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {
    private i a;
    private ImageView b;
    private RadioButton c;
    private TextView d;
    private CheckBox e;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private LinearLayout i;
    private Drawable j;
    private int k;
    private Context l;
    private boolean m;
    private Drawable n;
    private boolean o;
    private LayoutInflater p;
    private boolean q;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    private void a(View view) {
        b(view, -1);
    }

    private void b(View view, int i) {
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    private void c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.e = checkBox;
        a(checkBox);
    }

    private void d() {
        ImageView imageView = (ImageView) getInflater().inflate(R$layout.abc_list_menu_item_icon, (ViewGroup) this, false);
        this.b = imageView;
        b(imageView, 0);
    }

    private void e() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.c = radioButton;
        a(radioButton);
    }

    private LayoutInflater getInflater() {
        if (this.p == null) {
            this.p = LayoutInflater.from(getContext());
        }
        return this.p;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.h;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
        rect.top += this.h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public void f(boolean z, char c) {
        int i = (z && this.a.A()) ? 0 : 8;
        if (i == 0) {
            this.f.setText(this.a.h());
        }
        if (this.f.getVisibility() != i) {
            this.f.setVisibility(i);
        }
    }

    @Override // androidx.appcompat.view.menu.n.a
    public i getItemData() {
        return this.a;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void initialize(i iVar, int i) {
        this.a = iVar;
        setVisibility(iVar.isVisible() ? 0 : 8);
        setTitle(iVar.i(this));
        setCheckable(iVar.isCheckable());
        f(iVar.A(), iVar.g());
        setIcon(iVar.getIcon());
        setEnabled(iVar.isEnabled());
        setSubMenuArrowVisible(iVar.hasSubMenu());
        setContentDescription(iVar.getContentDescription());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        m0.o0(this, this.j);
        TextView textView = (TextView) findViewById(R$id.title);
        this.d = textView;
        int i = this.k;
        if (i != -1) {
            textView.setTextAppearance(this.l, i);
        }
        this.f = (TextView) findViewById(R$id.shortcut);
        ImageView imageView = (ImageView) findViewById(R$id.submenuarrow);
        this.g = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.n);
        }
        this.h = (ImageView) findViewById(R$id.group_divider);
        this.i = (LinearLayout) findViewById(R$id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.b != null && this.m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            int i3 = layoutParams.height;
            if (i3 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i3;
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        View view;
        if (!z && this.c == null && this.e == null) {
            return;
        }
        if (this.a.m()) {
            if (this.c == null) {
                e();
            }
            compoundButton = this.c;
            view = this.e;
        } else {
            if (this.e == null) {
                c();
            }
            compoundButton = this.e;
            view = this.c;
        }
        if (z) {
            compoundButton.setChecked(this.a.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.e;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.c;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.a.m()) {
            if (this.c == null) {
                e();
            }
            compoundButton = this.c;
        } else {
            if (this.e == null) {
                c();
            }
            compoundButton = this.e;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.q = z;
        this.m = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility((this.o || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.a.z() || this.q;
        if (z || this.m) {
            ImageView imageView = this.b;
            if (imageView == null && drawable == null && !this.m) {
                return;
            }
            if (imageView == null) {
                d();
            }
            if (drawable == null && !this.m) {
                this.b.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.b;
            if (!z) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.b.getVisibility() != 0) {
                this.b.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.d.getVisibility() != 8) {
                this.d.setVisibility(8);
            }
        } else {
            this.d.setText(charSequence);
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        h2 h2VarV = h2.v(getContext(), attributeSet, R$styleable.C, i, 0);
        this.j = h2VarV.g(R$styleable.MenuView_android_itemBackground);
        this.k = h2VarV.n(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.m = h2VarV.a(R$styleable.MenuView_preserveIconSpacing, false);
        this.l = context;
        this.n = h2VarV.g(R$styleable.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, R$attr.dropDownListViewStyle, 0);
        this.o = typedArrayObtainStyledAttributes.hasValue(0);
        h2VarV.w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
