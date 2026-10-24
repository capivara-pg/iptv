package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class Toolbar extends ViewGroup {
    private static final String TAG = "Toolbar";
    private androidx.appcompat.view.menu.m.a mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private i2 mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private d mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private androidx.appcompat.view.menu.g.a mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final ActionMenuView.e mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    f mOnMenuItemClickListener;
    private androidx.appcompat.widget.d mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private ColorStateList mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private ColorStateList mTitleTextColor;
    private TextView mTitleTextView;
    private s2 mWrapper;

    class a implements ActionMenuView.e {
        a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.mOnMenuItemClickListener;
            if (fVar != null) {
                return fVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.showOverflowMenu();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.collapseActionView();
        }
    }

    private class d implements androidx.appcompat.view.menu.m {
        androidx.appcompat.view.menu.g a;
        androidx.appcompat.view.menu.i b;

        d() {
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean collapseItemActionView(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.i iVar) {
            f.c cVar = Toolbar.this.mExpandedActionView;
            if (cVar instanceof f.c) {
                cVar.c();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.mExpandedActionView);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.mCollapseButtonView);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.mExpandedActionView = null;
            toolbar3.addChildrenForExpandedActionView();
            this.b = null;
            Toolbar.this.requestLayout();
            iVar.r(false);
            return true;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean expandItemActionView(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.i iVar) {
            Toolbar.this.ensureCollapseButtonView();
            ViewParent parent = Toolbar.this.mCollapseButtonView.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.mCollapseButtonView);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = iVar.getActionView();
            this.b = iVar;
            ViewParent parent2 = Toolbar.this.mExpandedActionView.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.mExpandedActionView);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.a = (toolbar4.mButtonGravity & R$styleable.AppCompatTheme_tooltipForegroundColor) | 8388611;
                eVarGenerateDefaultLayoutParams.b = 2;
                toolbar4.mExpandedActionView.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.mExpandedActionView);
            }
            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            iVar.r(true);
            f.c cVar = Toolbar.this.mExpandedActionView;
            if (cVar instanceof f.c) {
                cVar.a();
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean flagActionItems() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m
        public int getId() {
            return 0;
        }

        @Override // androidx.appcompat.view.menu.m
        public void initForMenu(Context context, androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.i iVar;
            androidx.appcompat.view.menu.g gVar2 = this.a;
            if (gVar2 != null && (iVar = this.b) != null) {
                gVar2.collapseItemActionView(iVar);
            }
            this.a = gVar;
        }

        @Override // androidx.appcompat.view.menu.m
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.m
        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        @Override // androidx.appcompat.view.menu.m
        public Parcelable onSaveInstanceState() {
            return null;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean onSubMenuSelected(androidx.appcompat.view.menu.r rVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m
        public void updateMenuView(boolean z) {
            if (this.b != null) {
                androidx.appcompat.view.menu.g gVar = this.a;
                boolean z2 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    for (int i = 0; i < size; i++) {
                        if (this.a.getItem(i) == this.b) {
                            z2 = true;
                            break;
                        }
                    }
                }
                if (z2) {
                    return;
                }
                collapseItemActionView(this.a, this.b);
            }
        }
    }

    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    private void a(List list, int i) {
        boolean z = androidx.core.view.c1.z(this) == 1;
        int childCount = getChildCount();
        int iB = androidx.core.view.j.b(i, androidx.core.view.c1.z(this));
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.b == 0 && u(childAt) && h(eVar.a) == iB) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.b == 0 && u(childAt2) && h(eVar2.a) == iB) {
                list.add(childAt2);
            }
        }
    }

    private void b(View view, boolean z) {
        e eVarGenerateLayoutParams;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            eVarGenerateLayoutParams = generateDefaultLayoutParams();
        } else {
            eVarGenerateLayoutParams = !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        }
        eVarGenerateLayoutParams.b = 1;
        if (!z || this.mExpandedActionView == null) {
            addView(view, eVarGenerateLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateLayoutParams);
            this.mHiddenViews.add(view);
        }
    }

    private void c() {
        if (this.mContentInsets == null) {
            this.mContentInsets = new i2();
        }
    }

    private void d() {
        if (this.mLogoView == null) {
            this.mLogoView = new q(getContext());
        }
    }

    private void e() {
        f();
        if (this.mMenuView.s() == null) {
            androidx.appcompat.view.menu.g gVar = (androidx.appcompat.view.menu.g) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new d();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            gVar.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void f() {
        if (this.mMenuView == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.mMenuView = actionMenuView;
            actionMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.t(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.a = (this.mButtonGravity & R$styleable.AppCompatTheme_tooltipForegroundColor) | 8388613;
            this.mMenuView.setLayoutParams(eVarGenerateDefaultLayoutParams);
            b(this.mMenuView, false);
        }
    }

    private void g() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new n(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.a = (this.mButtonGravity & R$styleable.AppCompatTheme_tooltipForegroundColor) | 8388611;
            this.mNavButtonView.setLayoutParams(eVarGenerateDefaultLayoutParams);
        }
    }

    private MenuInflater getMenuInflater() {
        return new f.g(getContext());
    }

    private int h(int i) {
        int iZ = androidx.core.view.c1.z(this);
        int iB = androidx.core.view.j.b(i, iZ) & 7;
        if (iB == 1 || iB == 3 || iB == 5) {
            return iB;
        }
        return iZ == 1 ? 5 : 3;
    }

    private int i(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int iJ = j(eVar.a);
        if (iJ == 48) {
            return getPaddingTop() - i2;
        }
        if (iJ == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i3) {
            iMax = i3;
        } else {
            int i4 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i4 < i5) {
                iMax = Math.max(0, iMax - (i5 - i4));
            }
        }
        return paddingTop + iMax;
    }

    private int j(int i) {
        int i2 = i & R$styleable.AppCompatTheme_tooltipForegroundColor;
        return (i2 == 16 || i2 == 48 || i2 == 80) ? i2 : this.mGravity & R$styleable.AppCompatTheme_tooltipForegroundColor;
    }

    private int k(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return androidx.core.view.m.b(marginLayoutParams) + androidx.core.view.m.a(marginLayoutParams);
    }

    private int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int m(List list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = 0;
        int measuredWidth = 0;
        while (i3 < size) {
            View view = (View) list.get(i3);
            e eVar = (e) view.getLayoutParams();
            int i4 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i;
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i2;
            int iMax = Math.max(0, i4);
            int iMax2 = Math.max(0, i5);
            int iMax3 = Math.max(0, -i4);
            int iMax4 = Math.max(0, -i5);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i3++;
            i2 = iMax4;
            i = iMax3;
        }
        return measuredWidth;
    }

    private boolean n(View view) {
        return view.getParent() == this || this.mHiddenViews.contains(view);
    }

    private int o(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int i4 = i(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, i4, iMax + measuredWidth, view.getMeasuredHeight() + i4);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    private int p(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int i4 = i(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, i4, iMax, view.getMeasuredHeight() + i4);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    private int q(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    private void r(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void s() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    private boolean t() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (u(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean u(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    void addChildrenForExpandedActionView() {
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            addView(this.mHiddenViews.get(size));
        }
        this.mHiddenViews.clear();
    }

    public boolean canShowOverflowMenu() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.mMenuView) != null && actionMenuView.p();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public void collapseActionView() {
        d dVar = this.mExpandedMenuPresenter;
        androidx.appcompat.view.menu.i iVar = dVar == null ? null : dVar.b;
        if (iVar != null) {
            iVar.collapseActionView();
        }
    }

    public void dismissPopupMenus() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.g();
        }
    }

    void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            n nVar = new n(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView = nVar;
            nVar.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.a = (this.mButtonGravity & R$styleable.AppCompatTheme_tooltipForegroundColor) | 8388611;
            eVarGenerateDefaultLayoutParams.b = 2;
            this.mCollapseButtonView.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener(new c());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        i2 i2Var = this.mContentInsets;
        if (i2Var != null) {
            return i2Var.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.mContentInsetEndWithActions;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        i2 i2Var = this.mContentInsets;
        if (i2Var != null) {
            return i2Var.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        i2 i2Var = this.mContentInsets;
        if (i2Var != null) {
            return i2Var.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        i2 i2Var = this.mContentInsets;
        if (i2Var != null) {
            return i2Var.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.mContentInsetStartWithNavigation;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        androidx.appcompat.view.menu.g gVarS;
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && (gVarS = actionMenuView.s()) != null && gVarS.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return androidx.core.view.c1.z(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return androidx.core.view.c1.z(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.mMenuView.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    androidx.appcompat.widget.d getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.mMenuView.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    final TextView getSubtitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    final TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public i1 getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new s2(this, true);
        }
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        d dVar = this.mExpandedMenuPresenter;
        return (dVar == null || dVar.b == null) ? false : true;
    }

    public boolean hideOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.m();
    }

    public void inflateMenu(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.n();
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.o();
    }

    public boolean isTitleTruncated() {
        Layout layout;
        TextView textView = this.mTitleTextView;
        if (textView == null || (layout = textView.getLayout()) == null) {
            return false;
        }
        int lineCount = layout.getLineCount();
        for (int i = 0; i < lineCount; i++) {
            if (layout.getEllipsisCount(i) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0289  */
    /* JADX WARN: Code duplicated, block: B:102:0x028c  */
    /* JADX WARN: Code duplicated, block: B:105:0x029f A[LOOP:0: B:104:0x029d->B:105:0x029f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:108:0x02c1 A[LOOP:1: B:107:0x02bf->B:108:0x02c1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:112:0x02eb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x02ed  */
    /* JADX WARN: Code duplicated, block: B:114:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:117:0x02fa A[LOOP:2: B:116:0x02f8->B:117:0x02fa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:19:0x005f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x0061  */
    /* JADX WARN: Code duplicated, block: B:21:0x0068  */
    /* JADX WARN: Code duplicated, block: B:24:0x0076 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:25:0x0078  */
    /* JADX WARN: Code duplicated, block: B:26:0x007f  */
    /* JADX WARN: Code duplicated, block: B:29:0x00b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:31:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:34:0x00ca A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:40:0x0100  */
    /* JADX WARN: Code duplicated, block: B:42:0x0105  */
    /* JADX WARN: Code duplicated, block: B:43:0x011d  */
    /* JADX WARN: Code duplicated, block: B:49:0x012b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x012d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0130  */
    /* JADX WARN: Code duplicated, block: B:53:0x0134  */
    /* JADX WARN: Code duplicated, block: B:54:0x0137  */
    /* JADX WARN: Code duplicated, block: B:57:0x0147  */
    /* JADX WARN: Code duplicated, block: B:59:0x014f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:66:0x0168  */
    /* JADX WARN: Code duplicated, block: B:68:0x016c  */
    /* JADX WARN: Code duplicated, block: B:70:0x017e  */
    /* JADX WARN: Code duplicated, block: B:71:0x0181  */
    /* JADX WARN: Code duplicated, block: B:73:0x018c  */
    /* JADX WARN: Code duplicated, block: B:75:0x0198  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:78:0x01b7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:80:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:83:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:84:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:86:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:87:0x021b  */
    /* JADX WARN: Code duplicated, block: B:89:0x021e  */
    /* JADX WARN: Code duplicated, block: B:91:0x0227 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x0229  */
    /* JADX WARN: Code duplicated, block: B:93:0x022d  */
    /* JADX WARN: Code duplicated, block: B:96:0x0241  */
    /* JADX WARN: Code duplicated, block: B:97:0x0264  */
    /* JADX WARN: Code duplicated, block: B:99:0x0267  */
    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iO;
        int iP;
        int iMax;
        int iMin;
        boolean zU;
        boolean zU2;
        int measuredHeight;
        TextView textView;
        TextView textView2;
        e eVar;
        e eVar2;
        boolean z2;
        int i5;
        int i6;
        int paddingTop;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iMax2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int size;
        int i19;
        int i20;
        int size2;
        int i21;
        int i22;
        int i23;
        int size3;
        boolean z3 = androidx.core.view.c1.z(this) == 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop2 = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i24 = width - paddingRight;
        int[] iArr = this.mTempMargins;
        iArr[1] = 0;
        iArr[0] = 0;
        int iA = androidx.core.view.c1.A(this);
        int iMin2 = iA >= 0 ? Math.min(iA, i4 - i2) : 0;
        if (u(this.mNavButtonView)) {
            if (z3) {
                iP = p(this.mNavButtonView, i24, iArr, iMin2);
                iO = paddingLeft;
            } else {
                iO = o(this.mNavButtonView, paddingLeft, iArr, iMin2);
            }
            if (u(this.mCollapseButtonView)) {
                if (z3) {
                    iP = p(this.mCollapseButtonView, iP, iArr, iMin2);
                } else {
                    iO = o(this.mCollapseButtonView, iO, iArr, iMin2);
                }
            }
            if (u(this.mMenuView)) {
                if (z3) {
                    iO = o(this.mMenuView, iO, iArr, iMin2);
                } else {
                    iP = p(this.mMenuView, iP, iArr, iMin2);
                }
            }
            int currentContentInsetLeft = getCurrentContentInsetLeft();
            int currentContentInsetRight = getCurrentContentInsetRight();
            iArr[0] = Math.max(0, currentContentInsetLeft - iO);
            iArr[1] = Math.max(0, currentContentInsetRight - (i24 - iP));
            iMax = Math.max(iO, currentContentInsetLeft);
            iMin = Math.min(iP, i24 - currentContentInsetRight);
            if (u(this.mExpandedActionView)) {
                if (z3) {
                    iMin = p(this.mExpandedActionView, iMin, iArr, iMin2);
                } else {
                    iMax = o(this.mExpandedActionView, iMax, iArr, iMin2);
                }
            }
            if (u(this.mLogoView)) {
                if (z3) {
                    iMin = p(this.mLogoView, iMin, iArr, iMin2);
                } else {
                    iMax = o(this.mLogoView, iMax, iArr, iMin2);
                }
            }
            zU = u(this.mTitleTextView);
            zU2 = u(this.mSubtitleTextView);
            if (zU) {
                e eVar3 = (e) this.mTitleTextView.getLayoutParams();
                measuredHeight = ((ViewGroup.MarginLayoutParams) eVar3).topMargin + this.mTitleTextView.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar3).bottomMargin + 0;
            } else {
                measuredHeight = 0;
            }
            if (zU2) {
                e eVar4 = (e) this.mSubtitleTextView.getLayoutParams();
                measuredHeight += ((ViewGroup.MarginLayoutParams) eVar4).topMargin + this.mSubtitleTextView.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar4).bottomMargin;
            }
            if (!zU || zU2) {
                if (zU) {
                    textView = this.mTitleTextView;
                } else {
                    textView = this.mSubtitleTextView;
                }
                if (zU2) {
                    textView2 = this.mSubtitleTextView;
                } else {
                    textView2 = this.mTitleTextView;
                }
                eVar = (e) textView.getLayoutParams();
                eVar2 = (e) textView2.getLayoutParams();
                z2 = (!zU && this.mTitleTextView.getMeasuredWidth() > 0) || (zU2 && this.mSubtitleTextView.getMeasuredWidth() > 0);
                i5 = this.mGravity & R$styleable.AppCompatTheme_tooltipForegroundColor;
                i6 = paddingLeft;
                if (i5 == 48) {
                    paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + this.mTitleMarginTop;
                } else if (i5 != 80) {
                    iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                    i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                    i15 = this.mTitleMarginTop;
                    if (iMax2 < i14 + i15) {
                        iMax2 = i14 + i15;
                    } else {
                        i16 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                        i17 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                        i18 = this.mTitleMarginBottom;
                        if (i16 < i17 + i18) {
                            iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) eVar2).bottomMargin + i18) - i16));
                        }
                    }
                    paddingTop = paddingTop2 + iMax2;
                } else {
                    paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin) - this.mTitleMarginBottom) - measuredHeight;
                }
                if (z3) {
                    if (z2) {
                        i11 = this.mTitleMarginStart;
                    } else {
                        i11 = 0;
                    }
                    int i25 = i11 - iArr[1];
                    iMin -= Math.max(0, i25);
                    iArr[1] = Math.max(0, -i25);
                    if (zU) {
                        e eVar5 = (e) this.mTitleTextView.getLayoutParams();
                        int measuredWidth = iMin - this.mTitleTextView.getMeasuredWidth();
                        int measuredHeight2 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                        this.mTitleTextView.layout(measuredWidth, paddingTop, iMin, measuredHeight2);
                        i12 = measuredWidth - this.mTitleMarginEnd;
                        paddingTop = measuredHeight2 + ((ViewGroup.MarginLayoutParams) eVar5).bottomMargin;
                    } else {
                        i12 = iMin;
                    }
                    if (zU2) {
                        int i26 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                        this.mSubtitleTextView.layout(iMin - this.mSubtitleTextView.getMeasuredWidth(), i26, iMin, this.mSubtitleTextView.getMeasuredHeight() + i26);
                        i13 = iMin - this.mTitleMarginEnd;
                    } else {
                        i13 = iMin;
                    }
                    if (z2) {
                        iMin = Math.min(i12, i13);
                    }
                    iMax = iMax;
                } else {
                    if (z2) {
                        i8 = this.mTitleMarginStart;
                        i7 = 0;
                    } else {
                        i7 = 0;
                        i8 = 0;
                    }
                    int i27 = i8 - iArr[i7];
                    iMax += Math.max(i7, i27);
                    iArr[i7] = Math.max(i7, -i27);
                    if (zU) {
                        e eVar6 = (e) this.mTitleTextView.getLayoutParams();
                        int measuredWidth2 = this.mTitleTextView.getMeasuredWidth() + iMax;
                        int measuredHeight3 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                        this.mTitleTextView.layout(iMax, paddingTop, measuredWidth2, measuredHeight3);
                        i9 = measuredWidth2 + this.mTitleMarginEnd;
                        paddingTop = measuredHeight3 + ((ViewGroup.MarginLayoutParams) eVar6).bottomMargin;
                    } else {
                        i9 = iMax;
                    }
                    if (zU2) {
                        int i28 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                        int measuredWidth3 = this.mSubtitleTextView.getMeasuredWidth() + iMax;
                        this.mSubtitleTextView.layout(iMax, i28, measuredWidth3, this.mSubtitleTextView.getMeasuredHeight() + i28);
                        i10 = measuredWidth3 + this.mTitleMarginEnd;
                    } else {
                        i10 = iMax;
                    }
                    if (z2) {
                        iMax = Math.max(i9, i10);
                    }
                }
                a(this.mTempViews, 3);
                size = this.mTempViews.size();
                for (i19 = 0; i19 < size; i19++) {
                    iMax = o(this.mTempViews.get(i19), iMax, iArr, iMin2);
                }
                i20 = iMin2;
                a(this.mTempViews, 5);
                size2 = this.mTempViews.size();
                for (i21 = 0; i21 < size2; i21++) {
                    iMin = p(this.mTempViews.get(i21), iMin, iArr, i20);
                }
                a(this.mTempViews, 1);
                int iM = m(this.mTempViews, iArr);
                i22 = (i6 + (((width - i6) - paddingRight) / 2)) - (iM / 2);
                i23 = iM + i22;
                if (i22 >= iMax) {
                    if (i23 > iMin) {
                        iMax = i22 - (i23 - iMin);
                    } else {
                        iMax = i22;
                    }
                }
                size3 = this.mTempViews.size();
                while (i7 < size3) {
                    iMax = o(this.mTempViews.get(i7), iMax, iArr, i20);
                    i7++;
                }
                this.mTempViews.clear();
            }
            i6 = paddingLeft;
            iMin2 = iMin2;
            i7 = 0;
            a(this.mTempViews, 3);
            size = this.mTempViews.size();
            while (i19 < size) {
                iMax = o(this.mTempViews.get(i19), iMax, iArr, iMin2);
            }
            i20 = iMin2;
            a(this.mTempViews, 5);
            size2 = this.mTempViews.size();
            while (i21 < size2) {
                iMin = p(this.mTempViews.get(i21), iMin, iArr, i20);
            }
            a(this.mTempViews, 1);
            int iM2 = m(this.mTempViews, iArr);
            i22 = (i6 + (((width - i6) - paddingRight) / 2)) - (iM2 / 2);
            i23 = iM2 + i22;
            if (i22 >= iMax) {
                if (i23 > iMin) {
                    iMax = i22 - (i23 - iMin);
                } else {
                    iMax = i22;
                }
            }
            size3 = this.mTempViews.size();
            while (i7 < size3) {
                iMax = o(this.mTempViews.get(i7), iMax, iArr, i20);
                i7++;
            }
            this.mTempViews.clear();
        }
        iO = paddingLeft;
        iP = i24;
        if (u(this.mCollapseButtonView)) {
            if (z3) {
                iP = p(this.mCollapseButtonView, iP, iArr, iMin2);
            } else {
                iO = o(this.mCollapseButtonView, iO, iArr, iMin2);
            }
        }
        if (u(this.mMenuView)) {
            if (z3) {
                iO = o(this.mMenuView, iO, iArr, iMin2);
            } else {
                iP = p(this.mMenuView, iP, iArr, iMin2);
            }
        }
        int currentContentInsetLeft2 = getCurrentContentInsetLeft();
        int currentContentInsetRight2 = getCurrentContentInsetRight();
        iArr[0] = Math.max(0, currentContentInsetLeft2 - iO);
        iArr[1] = Math.max(0, currentContentInsetRight2 - (i24 - iP));
        iMax = Math.max(iO, currentContentInsetLeft2);
        iMin = Math.min(iP, i24 - currentContentInsetRight2);
        if (u(this.mExpandedActionView)) {
            if (z3) {
                iMin = p(this.mExpandedActionView, iMin, iArr, iMin2);
            } else {
                iMax = o(this.mExpandedActionView, iMax, iArr, iMin2);
            }
        }
        if (u(this.mLogoView)) {
            if (z3) {
                iMin = p(this.mLogoView, iMin, iArr, iMin2);
            } else {
                iMax = o(this.mLogoView, iMax, iArr, iMin2);
            }
        }
        zU = u(this.mTitleTextView);
        zU2 = u(this.mSubtitleTextView);
        if (zU) {
            e eVar7 = (e) this.mTitleTextView.getLayoutParams();
            measuredHeight = ((ViewGroup.MarginLayoutParams) eVar7).topMargin + this.mTitleTextView.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar7).bottomMargin + 0;
        } else {
            measuredHeight = 0;
        }
        if (zU2) {
            e eVar8 = (e) this.mSubtitleTextView.getLayoutParams();
            measuredHeight += ((ViewGroup.MarginLayoutParams) eVar8).topMargin + this.mSubtitleTextView.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar8).bottomMargin;
        }
        if (zU) {
            if (zU) {
                textView = this.mTitleTextView;
            } else {
                textView = this.mSubtitleTextView;
            }
            if (zU2) {
                textView2 = this.mSubtitleTextView;
            } else {
                textView2 = this.mTitleTextView;
            }
            eVar = (e) textView.getLayoutParams();
            eVar2 = (e) textView2.getLayoutParams();
            if (zU) {
            }
            i5 = this.mGravity & R$styleable.AppCompatTheme_tooltipForegroundColor;
            i6 = paddingLeft;
            if (i5 == 48) {
                paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + this.mTitleMarginTop;
            } else if (i5 != 80) {
                iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                i15 = this.mTitleMarginTop;
                if (iMax2 < i14 + i15) {
                    iMax2 = i14 + i15;
                } else {
                    i16 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                    i17 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                    i18 = this.mTitleMarginBottom;
                    if (i16 < i17 + i18) {
                        iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) eVar2).bottomMargin + i18) - i16));
                    }
                }
                paddingTop = paddingTop2 + iMax2;
            } else {
                paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin) - this.mTitleMarginBottom) - measuredHeight;
            }
            if (z3) {
                if (z2) {
                    i11 = this.mTitleMarginStart;
                } else {
                    i11 = 0;
                }
                int i29 = i11 - iArr[1];
                iMin -= Math.max(0, i29);
                iArr[1] = Math.max(0, -i29);
                if (zU) {
                    e eVar9 = (e) this.mTitleTextView.getLayoutParams();
                    int measuredWidth4 = iMin - this.mTitleTextView.getMeasuredWidth();
                    int measuredHeight4 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                    this.mTitleTextView.layout(measuredWidth4, paddingTop, iMin, measuredHeight4);
                    i12 = measuredWidth4 - this.mTitleMarginEnd;
                    paddingTop = measuredHeight4 + ((ViewGroup.MarginLayoutParams) eVar9).bottomMargin;
                } else {
                    i12 = iMin;
                }
                if (zU2) {
                    int i210 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                    this.mSubtitleTextView.layout(iMin - this.mSubtitleTextView.getMeasuredWidth(), i210, iMin, this.mSubtitleTextView.getMeasuredHeight() + i210);
                    i13 = iMin - this.mTitleMarginEnd;
                } else {
                    i13 = iMin;
                }
                if (z2) {
                    iMin = Math.min(i12, i13);
                }
                iMax = iMax;
                i7 = 0;
            } else {
                if (z2) {
                    i8 = this.mTitleMarginStart;
                    i7 = 0;
                } else {
                    i7 = 0;
                    i8 = 0;
                }
                int i211 = i8 - iArr[i7];
                iMax += Math.max(i7, i211);
                iArr[i7] = Math.max(i7, -i211);
                if (zU) {
                    e eVar10 = (e) this.mTitleTextView.getLayoutParams();
                    int measuredWidth5 = this.mTitleTextView.getMeasuredWidth() + iMax;
                    int measuredHeight5 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                    this.mTitleTextView.layout(iMax, paddingTop, measuredWidth5, measuredHeight5);
                    i9 = measuredWidth5 + this.mTitleMarginEnd;
                    paddingTop = measuredHeight5 + ((ViewGroup.MarginLayoutParams) eVar10).bottomMargin;
                } else {
                    i9 = iMax;
                }
                if (zU2) {
                    int i212 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                    int measuredWidth6 = this.mSubtitleTextView.getMeasuredWidth() + iMax;
                    this.mSubtitleTextView.layout(iMax, i212, measuredWidth6, this.mSubtitleTextView.getMeasuredHeight() + i212);
                    i10 = measuredWidth6 + this.mTitleMarginEnd;
                } else {
                    i10 = iMax;
                }
                if (z2) {
                    iMax = Math.max(i9, i10);
                }
            }
        } else {
            if (zU) {
                textView = this.mTitleTextView;
            } else {
                textView = this.mSubtitleTextView;
            }
            if (zU2) {
                textView2 = this.mSubtitleTextView;
            } else {
                textView2 = this.mTitleTextView;
            }
            eVar = (e) textView.getLayoutParams();
            eVar2 = (e) textView2.getLayoutParams();
            if (zU) {
            }
            i5 = this.mGravity & R$styleable.AppCompatTheme_tooltipForegroundColor;
            i6 = paddingLeft;
            if (i5 == 48) {
                paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + this.mTitleMarginTop;
            } else if (i5 != 80) {
                iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                i15 = this.mTitleMarginTop;
                if (iMax2 < i14 + i15) {
                    iMax2 = i14 + i15;
                } else {
                    i16 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                    i17 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                    i18 = this.mTitleMarginBottom;
                    if (i16 < i17 + i18) {
                        iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) eVar2).bottomMargin + i18) - i16));
                    }
                }
                paddingTop = paddingTop2 + iMax2;
            } else {
                paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin) - this.mTitleMarginBottom) - measuredHeight;
            }
            if (z3) {
                if (z2) {
                    i11 = this.mTitleMarginStart;
                } else {
                    i11 = 0;
                }
                int i213 = i11 - iArr[1];
                iMin -= Math.max(0, i213);
                iArr[1] = Math.max(0, -i213);
                if (zU) {
                    e eVar11 = (e) this.mTitleTextView.getLayoutParams();
                    int measuredWidth7 = iMin - this.mTitleTextView.getMeasuredWidth();
                    int measuredHeight6 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                    this.mTitleTextView.layout(measuredWidth7, paddingTop, iMin, measuredHeight6);
                    i12 = measuredWidth7 - this.mTitleMarginEnd;
                    paddingTop = measuredHeight6 + ((ViewGroup.MarginLayoutParams) eVar11).bottomMargin;
                } else {
                    i12 = iMin;
                }
                if (zU2) {
                    int i214 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                    this.mSubtitleTextView.layout(iMin - this.mSubtitleTextView.getMeasuredWidth(), i214, iMin, this.mSubtitleTextView.getMeasuredHeight() + i214);
                    i13 = iMin - this.mTitleMarginEnd;
                } else {
                    i13 = iMin;
                }
                if (z2) {
                    iMin = Math.min(i12, i13);
                }
                iMax = iMax;
                i7 = 0;
            } else {
                if (z2) {
                    i8 = this.mTitleMarginStart;
                    i7 = 0;
                } else {
                    i7 = 0;
                    i8 = 0;
                }
                int i215 = i8 - iArr[i7];
                iMax += Math.max(i7, i215);
                iArr[i7] = Math.max(i7, -i215);
                if (zU) {
                    e eVar12 = (e) this.mTitleTextView.getLayoutParams();
                    int measuredWidth8 = this.mTitleTextView.getMeasuredWidth() + iMax;
                    int measuredHeight7 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                    this.mTitleTextView.layout(iMax, paddingTop, measuredWidth8, measuredHeight7);
                    i9 = measuredWidth8 + this.mTitleMarginEnd;
                    paddingTop = measuredHeight7 + ((ViewGroup.MarginLayoutParams) eVar12).bottomMargin;
                } else {
                    i9 = iMax;
                }
                if (zU2) {
                    int i216 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                    int measuredWidth9 = this.mSubtitleTextView.getMeasuredWidth() + iMax;
                    this.mSubtitleTextView.layout(iMax, i216, measuredWidth9, this.mSubtitleTextView.getMeasuredHeight() + i216);
                    i10 = measuredWidth9 + this.mTitleMarginEnd;
                } else {
                    i10 = iMax;
                }
                if (z2) {
                    iMax = Math.max(i9, i10);
                }
            }
        }
        a(this.mTempViews, 3);
        size = this.mTempViews.size();
        while (i19 < size) {
            iMax = o(this.mTempViews.get(i19), iMax, iArr, iMin2);
        }
        i20 = iMin2;
        a(this.mTempViews, 5);
        size2 = this.mTempViews.size();
        while (i21 < size2) {
            iMin = p(this.mTempViews.get(i21), iMin, iArr, i20);
        }
        a(this.mTempViews, 1);
        int iM3 = m(this.mTempViews, iArr);
        i22 = (i6 + (((width - i6) - paddingRight) / 2)) - (iM3 / 2);
        i23 = iM3 + i22;
        if (i22 >= iMax) {
            if (i23 > iMin) {
                iMax = i22 - (i23 - iMin);
            } else {
                iMax = i22;
            }
        }
        size3 = this.mTempViews.size();
        while (i7 < size3) {
            iMax = o(this.mTempViews.get(i7), iMax, iArr, i20);
            i7++;
        }
        this.mTempViews.clear();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int iCombineMeasuredStates2;
        int iMax2;
        int measuredHeight;
        int[] iArr = this.mTempMargins;
        boolean zB = y2.b(this);
        int i3 = !zB ? 1 : 0;
        if (u(this.mNavButtonView)) {
            r(this.mNavButtonView, i, 0, i2, 0, this.mMaxButtonHeight);
            measuredWidth = this.mNavButtonView.getMeasuredWidth() + k(this.mNavButtonView);
            iMax = Math.max(0, this.mNavButtonView.getMeasuredHeight() + l(this.mNavButtonView));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (u(this.mCollapseButtonView)) {
            r(this.mCollapseButtonView, i, 0, i2, 0, this.mMaxButtonHeight);
            measuredWidth = this.mCollapseButtonView.getMeasuredWidth() + k(this.mCollapseButtonView);
            iMax = Math.max(iMax, this.mCollapseButtonView.getMeasuredHeight() + l(this.mCollapseButtonView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mCollapseButtonView.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[zB ? 1 : 0] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (u(this.mMenuView)) {
            r(this.mMenuView, i, iMax3, i2, 0, this.mMaxButtonHeight);
            measuredWidth2 = this.mMenuView.getMeasuredWidth() + k(this.mMenuView);
            iMax = Math.max(iMax, this.mMenuView.getMeasuredHeight() + l(this.mMenuView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mMenuView.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[i3] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (u(this.mExpandedActionView)) {
            iMax4 += q(this.mExpandedActionView, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.mExpandedActionView.getMeasuredHeight() + l(this.mExpandedActionView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mExpandedActionView.getMeasuredState());
        }
        if (u(this.mLogoView)) {
            iMax4 += q(this.mLogoView, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.mLogoView.getMeasuredHeight() + l(this.mLogoView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mLogoView.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (((e) childAt.getLayoutParams()).b == 0 && u(childAt)) {
                iMax4 += q(childAt, i, iMax4, i2, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + l(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i5 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int i6 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (u(this.mTitleTextView)) {
            q(this.mTitleTextView, i, iMax4 + i6, i2, i5, iArr);
            int measuredWidth3 = this.mTitleTextView.getMeasuredWidth() + k(this.mTitleTextView);
            measuredHeight = this.mTitleTextView.getMeasuredHeight() + l(this.mTitleTextView);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.mTitleTextView.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
            measuredHeight = 0;
        }
        if (u(this.mSubtitleTextView)) {
            iMax2 = Math.max(iMax2, q(this.mSubtitleTextView, i, iMax4 + i6, i2, measuredHeight + i5, iArr));
            measuredHeight += this.mSubtitleTextView.getMeasuredHeight() + l(this.mSubtitleTextView);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.mSubtitleTextView.getMeasuredState());
        }
        int iMax5 = Math.max(iMax, measuredHeight);
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2), t() ? 0 : View.resolveSizeAndState(Math.max(iMax5 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.getSuperState());
        ActionMenuView actionMenuView = this.mMenuView;
        r.a aVarS = actionMenuView != null ? actionMenuView.s() : null;
        int i = gVar.a;
        if (i != 0 && this.mExpandedMenuPresenter != null && aVarS != null && (menuItemFindItem = aVarS.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (gVar.b) {
            s();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        c();
        this.mContentInsets.f(i == 1);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.os.Parcelable, androidx.appcompat.widget.Toolbar$g] */
    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        androidx.appcompat.view.menu.i iVar;
        ?? gVar = new g(super.onSaveInstanceState());
        d dVar = this.mExpandedMenuPresenter;
        if (dVar != null && (iVar = dVar.b) != null) {
            gVar.a = iVar.getItemId();
        }
        gVar.b = isOverflowMenuShowing();
        return gVar;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    void removeChildrenForExpandedActionView() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).b != 2 && childAt != this.mMenuView) {
                removeViewAt(childCount);
                this.mHiddenViews.add(childAt);
            }
        }
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(d.b.d(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetsAbsolute(int i, int i2) {
        c();
        this.mContentInsets.e(i, i2);
    }

    public void setContentInsetsRelative(int i, int i2) {
        c();
        this.mContentInsets.g(i, i2);
    }

    public void setLogo(int i) {
        setLogo(d.b.d(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setMenu(androidx.appcompat.view.menu.g gVar, androidx.appcompat.widget.d dVar) {
        if (gVar == null && this.mMenuView == null) {
            return;
        }
        f();
        androidx.appcompat.view.menu.g gVarS = this.mMenuView.s();
        if (gVarS == gVar) {
            return;
        }
        if (gVarS != null) {
            gVarS.removeMenuPresenter(this.mOuterActionMenuPresenter);
            gVarS.removeMenuPresenter(this.mExpandedMenuPresenter);
        }
        if (this.mExpandedMenuPresenter == null) {
            this.mExpandedMenuPresenter = new d();
        }
        dVar.x(true);
        if (gVar != null) {
            gVar.addMenuPresenter(dVar, this.mPopupContext);
            gVar.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        } else {
            dVar.initForMenu(this.mPopupContext, null);
            this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
            dVar.updateMenuView(true);
            this.mExpandedMenuPresenter.updateMenuView(true);
        }
        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setPresenter(dVar);
        this.mOuterActionMenuPresenter = dVar;
    }

    public void setMenuCallbacks(androidx.appcompat.view.menu.m.a aVar, androidx.appcompat.view.menu.g.a aVar2) {
        this.mActionMenuPresenterCallback = aVar;
        this.mMenuBuilderCallback = aVar2;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.t(aVar, aVar2);
        }
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(d.b.d(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.mOnMenuItemClickListener = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.mMenuView.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextAppearance(Context context, int i) {
        this.mSubtitleTextAppearance = i;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMargin(int i, int i2, int i3, int i4) {
        this.mTitleMarginStart = i;
        this.mTitleMarginTop = i2;
        this.mTitleMarginEnd = i3;
        this.mTitleMarginBottom = i4;
        requestLayout();
    }

    public void setTitleMarginBottom(int i) {
        this.mTitleMarginBottom = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.mTitleMarginEnd = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.mTitleMarginStart = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.mTitleMarginTop = i;
        requestLayout();
    }

    public void setTitleTextAppearance(Context context, int i) {
        this.mTitleTextAppearance = i;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    public boolean showOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.u();
    }

    public static class e extends androidx.appcompat.app.a.C0007a {
        int b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i, int i2) {
            super(i, i2);
            this.b = 0;
            this.a = 8388627;
        }

        public e(e eVar) {
            super((androidx.appcompat.app.a.C0007a) eVar);
            this.b = 0;
            this.b = eVar.b;
        }

        public e(androidx.appcompat.app.a.C0007a c0007a) {
            super(c0007a);
            this.b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList<>();
        this.mHiddenViews = new ArrayList<>();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new a();
        this.mShowOverflowMenuRunnable = new b();
        r2 r2VarU = r2.u(getContext(), attributeSet, R$styleable.M, i, 0);
        this.mTitleTextAppearance = r2VarU.n(R$styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = r2VarU.n(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = r2VarU.l(R$styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = r2VarU.l(R$styleable.Toolbar_buttonGravity, 48);
        int iE = r2VarU.e(R$styleable.Toolbar_titleMargin, 0);
        int i2 = R$styleable.Toolbar_titleMargins;
        iE = r2VarU.r(i2) ? r2VarU.e(i2, iE) : iE;
        this.mTitleMarginBottom = iE;
        this.mTitleMarginTop = iE;
        this.mTitleMarginEnd = iE;
        this.mTitleMarginStart = iE;
        int iE2 = r2VarU.e(R$styleable.Toolbar_titleMarginStart, -1);
        if (iE2 >= 0) {
            this.mTitleMarginStart = iE2;
        }
        int iE3 = r2VarU.e(R$styleable.Toolbar_titleMarginEnd, -1);
        if (iE3 >= 0) {
            this.mTitleMarginEnd = iE3;
        }
        int iE4 = r2VarU.e(R$styleable.Toolbar_titleMarginTop, -1);
        if (iE4 >= 0) {
            this.mTitleMarginTop = iE4;
        }
        int iE5 = r2VarU.e(R$styleable.Toolbar_titleMarginBottom, -1);
        if (iE5 >= 0) {
            this.mTitleMarginBottom = iE5;
        }
        this.mMaxButtonHeight = r2VarU.f(R$styleable.Toolbar_maxButtonHeight, -1);
        int iE6 = r2VarU.e(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iE7 = r2VarU.e(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iF = r2VarU.f(R$styleable.Toolbar_contentInsetLeft, 0);
        int iF2 = r2VarU.f(R$styleable.Toolbar_contentInsetRight, 0);
        c();
        this.mContentInsets.e(iF, iF2);
        if (iE6 != Integer.MIN_VALUE || iE7 != Integer.MIN_VALUE) {
            this.mContentInsets.g(iE6, iE7);
        }
        this.mContentInsetStartWithNavigation = r2VarU.e(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = r2VarU.e(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = r2VarU.g(R$styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = r2VarU.p(R$styleable.Toolbar_collapseContentDescription);
        CharSequence charSequenceP = r2VarU.p(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceP)) {
            setTitle(charSequenceP);
        }
        CharSequence charSequenceP2 = r2VarU.p(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceP2)) {
            setSubtitle(charSequenceP2);
        }
        this.mPopupContext = getContext();
        setPopupTheme(r2VarU.n(R$styleable.Toolbar_popupTheme, 0));
        Drawable drawableG = r2VarU.g(R$styleable.Toolbar_navigationIcon);
        if (drawableG != null) {
            setNavigationIcon(drawableG);
        }
        CharSequence charSequenceP3 = r2VarU.p(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceP3)) {
            setNavigationContentDescription(charSequenceP3);
        }
        Drawable drawableG2 = r2VarU.g(R$styleable.Toolbar_logo);
        if (drawableG2 != null) {
            setLogo(drawableG2);
        }
        CharSequence charSequenceP4 = r2VarU.p(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceP4)) {
            setLogoDescription(charSequenceP4);
        }
        int i3 = R$styleable.Toolbar_titleTextColor;
        if (r2VarU.r(i3)) {
            setTitleTextColor(r2VarU.c(i3));
        }
        int i4 = R$styleable.Toolbar_subtitleTextColor;
        if (r2VarU.r(i4)) {
            setSubtitleTextColor(r2VarU.c(i4));
        }
        int i5 = R$styleable.Toolbar_menu;
        if (r2VarU.r(i5)) {
            inflateMenu(r2VarU.n(i5, 0));
        }
        r2VarU.v();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureCollapseButtonView();
        }
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            ensureCollapseButtonView();
            this.mCollapseButtonView.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.mCollapseButtonView;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.mCollapseIcon);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            d();
            if (!n(this.mLogoView)) {
                b(this.mLogoView, true);
            }
        } else {
            ImageView imageView = this.mLogoView;
            if (imageView != null && n(imageView)) {
                removeView(this.mLogoView);
                this.mHiddenViews.remove(this.mLogoView);
            }
        }
        ImageView imageView2 = this.mLogoView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            d();
        }
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!n(this.mNavButtonView)) {
                b(this.mNavButtonView, true);
            }
        } else {
            ImageButton imageButton = this.mNavButtonView;
            if (imageButton != null && n(imageButton)) {
                removeView(this.mNavButtonView);
                this.mHiddenViews.remove(this.mNavButtonView);
            }
        }
        ImageButton imageButton2 = this.mNavButtonView;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mSubtitleTextView;
            if (textView != null && n(textView)) {
                removeView(this.mSubtitleTextView);
                this.mHiddenViews.remove(this.mSubtitleTextView);
            }
        } else {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                q0 q0Var = new q0(context);
                this.mSubtitleTextView = q0Var;
                q0Var.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.mSubtitleTextAppearance;
                if (i != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.mSubtitleTextColor;
                if (colorStateList != null) {
                    this.mSubtitleTextView.setTextColor(colorStateList);
                }
            }
            if (!n(this.mSubtitleTextView)) {
                b(this.mSubtitleTextView, true);
            }
        }
        TextView textView2 = this.mSubtitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.mSubtitleTextColor = colorStateList;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mTitleTextView;
            if (textView != null && n(textView)) {
                removeView(this.mTitleTextView);
                this.mHiddenViews.remove(this.mTitleTextView);
            }
        } else {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                q0 q0Var = new q0(context);
                this.mTitleTextView = q0Var;
                q0Var.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.mTitleTextAppearance;
                if (i != 0) {
                    this.mTitleTextView.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.mTitleTextColor;
                if (colorStateList != null) {
                    this.mTitleTextView.setTextColor(colorStateList);
                }
            }
            if (!n(this.mTitleTextView)) {
                b(this.mTitleTextView, true);
            }
        }
        TextView textView2 = this.mTitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.mTitleTextColor = colorStateList;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class g extends b0.a {
        public static final Parcelable.Creator<g> CREATOR = new a();
        int a;
        boolean b;

        static class a implements Parcelable.ClassLoaderCreator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel) {
                return new g(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public g[] newArray(int i) {
                return new g[i];
            }
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
            this.b = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b ? 1 : 0);
        }

        public g(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.view.ViewGroup
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof e) {
            return new e((e) layoutParams);
        }
        if (layoutParams instanceof androidx.appcompat.app.a.C0007a) {
            return new e((androidx.appcompat.app.a.C0007a) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new e((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new e(layoutParams);
    }
}
