package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.p1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class g implements r.a {
    private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
    private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
    private static final String PRESENTER_KEY = "android:menu:presenters";
    private static final String TAG = "MenuBuilder";
    private static final int[] sCategoryToOrder = {1, 4, 5, 3, 2, 0};
    private a mCallback;
    private final Context mContext;
    private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
    private i mExpandedItem;
    private SparseArray<Parcelable> mFrozenViewStates;
    Drawable mHeaderIcon;
    CharSequence mHeaderTitle;
    View mHeaderView;
    private boolean mOverrideVisibleItems;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    private int mDefaultShowAsAction = 0;
    private boolean mPreventDispatchingItemsChanged = false;
    private boolean mItemsChangedWhileDispatchPrevented = false;
    private boolean mStructureChangedWhileDispatchPrevented = false;
    private boolean mOptionalIconsVisible = false;
    private boolean mIsClosing = false;
    private ArrayList<i> mTempShortcutItemList = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<m>> mPresenters = new CopyOnWriteArrayList<>();
    private boolean mGroupDividerEnabled = false;
    private ArrayList<i> mItems = new ArrayList<>();
    private ArrayList<i> mVisibleItems = new ArrayList<>();
    private boolean mIsVisibleItemsStale = true;
    private ArrayList<i> mActionItems = new ArrayList<>();
    private ArrayList<i> mNonActionItems = new ArrayList<>();
    private boolean mIsActionItemsStale = true;

    public interface a {
        boolean onMenuItemSelected(g gVar, MenuItem menuItem);

        void onMenuModeChange(g gVar);
    }

    public interface b {
        boolean b(i iVar);
    }

    public g(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
        j(true);
    }

    private i a(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new i(this, i, i2, i3, i4, charSequence, i5);
    }

    private void b(boolean z) {
        if (this.mPresenters.isEmpty()) {
            return;
        }
        stopDispatchingItemsChanged();
        for (WeakReference<m> weakReference : this.mPresenters) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.mPresenters.remove(weakReference);
            } else {
                mVar.updateMenuView(z);
            }
        }
        startDispatchingItemsChanged();
    }

    private void c(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(PRESENTER_KEY);
        if (sparseParcelableArray == null || this.mPresenters.isEmpty()) {
            return;
        }
        for (WeakReference<m> weakReference : this.mPresenters) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.mPresenters.remove(weakReference);
            } else {
                int id = mVar.getId();
                if (id > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(id)) != null) {
                    mVar.onRestoreInstanceState(parcelable);
                }
            }
        }
    }

    private void d(Bundle bundle) {
        Parcelable parcelableOnSaveInstanceState;
        if (this.mPresenters.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        for (WeakReference<m> weakReference : this.mPresenters) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.mPresenters.remove(weakReference);
            } else {
                int id = mVar.getId();
                if (id > 0 && (parcelableOnSaveInstanceState = mVar.onSaveInstanceState()) != null) {
                    sparseArray.put(id, parcelableOnSaveInstanceState);
                }
            }
        }
        bundle.putSparseParcelableArray(PRESENTER_KEY, sparseArray);
    }

    private boolean e(r rVar, m mVar) {
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        boolean zOnSubMenuSelected = mVar != null ? mVar.onSubMenuSelected(rVar) : false;
        for (WeakReference<m> weakReference : this.mPresenters) {
            m mVar2 = weakReference.get();
            if (mVar2 == null) {
                this.mPresenters.remove(weakReference);
            } else if (!zOnSubMenuSelected) {
                zOnSubMenuSelected = mVar2.onSubMenuSelected(rVar);
            }
        }
        return zOnSubMenuSelected;
    }

    private static int f(ArrayList arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((i) arrayList.get(size)).f() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    private static int g(int i) {
        int i2 = ((-65536) & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = sCategoryToOrder;
            if (i2 < iArr.length) {
                return (i & 65535) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void h(int i, boolean z) {
        if (i < 0 || i >= this.mItems.size()) {
            return;
        }
        this.mItems.remove(i);
        if (z) {
            onItemsChanged(true);
        }
    }

    private void i(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resources = getResources();
        if (view != null) {
            this.mHeaderView = view;
            this.mHeaderTitle = null;
            this.mHeaderIcon = null;
        } else {
            if (i > 0) {
                this.mHeaderTitle = resources.getText(i);
            } else if (charSequence != null) {
                this.mHeaderTitle = charSequence;
            }
            if (i2 > 0) {
                this.mHeaderIcon = androidx.core.content.a.getDrawable(getContext(), i2);
            } else if (drawable != null) {
                this.mHeaderIcon = drawable;
            }
            this.mHeaderView = null;
        }
        onItemsChanged(false);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    private void j(boolean z) {
        boolean z2;
        if (z) {
            z2 = this.mResources.getConfiguration().keyboard != 1 && p1.f(ViewConfiguration.get(this.mContext), this.mContext);
        }
        this.mShortcutsVisible = z2;
    }

    public MenuItem add(CharSequence charSequence) {
        return addInternal(0, 0, 0, charSequence);
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i6 = 0; i6 < size; i6++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i6);
            int i7 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i7 < 0 ? intent : intentArr[i7]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = intent3;
            }
        }
        return size;
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [android.view.MenuItem, androidx.appcompat.view.menu.i, java.lang.Object] */
    protected MenuItem addInternal(int i, int i2, int i3, CharSequence charSequence) {
        int iG = g(i3);
        ?? A = a(i, i2, i3, iG, charSequence, this.mDefaultShowAsAction);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.mCurrentMenuInfo;
        if (contextMenuInfo != null) {
            A.v(contextMenuInfo);
        }
        ArrayList<i> arrayList = this.mItems;
        arrayList.add(f(arrayList, iG), (i) A);
        onItemsChanged(true);
        return A;
    }

    public void addMenuPresenter(m mVar) {
        addMenuPresenter(mVar, this.mContext);
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void changeMenuMode() {
        a aVar = this.mCallback;
        if (aVar != null) {
            aVar.onMenuModeChange(this);
        }
    }

    public void clear() {
        i iVar = this.mExpandedItem;
        if (iVar != null) {
            collapseItemActionView(iVar);
        }
        this.mItems.clear();
        onItemsChanged(true);
    }

    public void clearAll() {
        this.mPreventDispatchingItemsChanged = true;
        clear();
        clearHeader();
        this.mPresenters.clear();
        this.mPreventDispatchingItemsChanged = false;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
        onItemsChanged(true);
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.mHeaderTitle = null;
        this.mHeaderView = null;
        onItemsChanged(false);
    }

    public final void close(boolean z) {
        if (this.mIsClosing) {
            return;
        }
        this.mIsClosing = true;
        for (WeakReference<m> weakReference : this.mPresenters) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.mPresenters.remove(weakReference);
            } else {
                mVar.onCloseMenu(this, z);
            }
        }
        this.mIsClosing = false;
    }

    public boolean collapseItemActionView(i iVar) {
        boolean zCollapseItemActionView = false;
        if (!this.mPresenters.isEmpty() && this.mExpandedItem == iVar) {
            stopDispatchingItemsChanged();
            for (WeakReference<m> weakReference : this.mPresenters) {
                m mVar = weakReference.get();
                if (mVar != null) {
                    zCollapseItemActionView = mVar.collapseItemActionView(this, iVar);
                    if (zCollapseItemActionView) {
                        break;
                    }
                } else {
                    this.mPresenters.remove(weakReference);
                }
            }
            startDispatchingItemsChanged();
            if (zCollapseItemActionView) {
                this.mExpandedItem = null;
            }
        }
        return zCollapseItemActionView;
    }

    boolean dispatchMenuItemSelected(g gVar, MenuItem menuItem) {
        a aVar = this.mCallback;
        return aVar != null && aVar.onMenuItemSelected(gVar, menuItem);
    }

    public boolean expandItemActionView(i iVar) {
        boolean zExpandItemActionView = false;
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        stopDispatchingItemsChanged();
        for (WeakReference<m> weakReference : this.mPresenters) {
            m mVar = weakReference.get();
            if (mVar != null) {
                zExpandItemActionView = mVar.expandItemActionView(this, iVar);
                if (zExpandItemActionView) {
                    break;
                }
            } else {
                this.mPresenters.remove(weakReference);
            }
        }
        startDispatchingItemsChanged();
        if (zExpandItemActionView) {
            this.mExpandedItem = iVar;
        }
        return zExpandItemActionView;
    }

    public int findGroupIndex(int i) {
        return findGroupIndex(i, 0);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [android.view.MenuItem, androidx.appcompat.view.menu.i] */
    public MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.mItems.get(i2);
            if (iVar.getItemId() == i) {
                return iVar;
            }
            if (iVar.hasSubMenu() && (menuItemFindItem = iVar.getSubMenu().findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public int findItemIndex(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mItems.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    i findItemWithShortcutForKey(int i, KeyEvent keyEvent) {
        ArrayList<i> arrayList = this.mTempShortcutItemList;
        arrayList.clear();
        findItemsWithShortcutForKey(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean zIsQwertyMode = isQwertyMode();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = arrayList.get(i2);
            char alphabeticShortcut = zIsQwertyMode ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zIsQwertyMode && alphabeticShortcut == '\b' && i == 67))) {
                return iVar;
            }
        }
        return null;
    }

    void findItemsWithShortcutForKey(List<i> list, int i, KeyEvent keyEvent) {
        boolean zIsQwertyMode = isQwertyMode();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.mItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                i iVar = this.mItems.get(i2);
                if (iVar.hasSubMenu()) {
                    ((g) iVar.getSubMenu()).findItemsWithShortcutForKey(list, i, keyEvent);
                }
                char alphabeticShortcut = zIsQwertyMode ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zIsQwertyMode ? iVar.getAlphabeticModifiers() : iVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zIsQwertyMode && alphabeticShortcut == '\b' && i == 67)) && iVar.isEnabled()) {
                        list.add(iVar);
                    }
                }
            }
        }
    }

    public void flagActionItems() {
        ArrayList<i> visibleItems = getVisibleItems();
        if (this.mIsActionItemsStale) {
            boolean zFlagActionItems = false;
            for (WeakReference<m> weakReference : this.mPresenters) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.mPresenters.remove(weakReference);
                } else {
                    zFlagActionItems |= mVar.flagActionItems();
                }
            }
            if (zFlagActionItems) {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                int size = visibleItems.size();
                for (int i = 0; i < size; i++) {
                    i iVar = visibleItems.get(i);
                    if (iVar.l()) {
                        this.mActionItems.add(iVar);
                    } else {
                        this.mNonActionItems.add(iVar);
                    }
                }
            } else {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                this.mNonActionItems.addAll(getVisibleItems());
            }
            this.mIsActionItemsStale = false;
        }
    }

    public ArrayList<i> getActionItems() {
        flagActionItems();
        return this.mActionItems;
    }

    protected String getActionViewStatesKey() {
        return ACTION_VIEW_STATES_KEY;
    }

    public Context getContext() {
        return this.mContext;
    }

    public i getExpandedItem() {
        return this.mExpandedItem;
    }

    public Drawable getHeaderIcon() {
        return this.mHeaderIcon;
    }

    public CharSequence getHeaderTitle() {
        return this.mHeaderTitle;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public MenuItem getItem(int i) {
        return (MenuItem) this.mItems.get(i);
    }

    public ArrayList<i> getNonActionItems() {
        flagActionItems();
        return this.mNonActionItems;
    }

    boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    Resources getResources() {
        return this.mResources;
    }

    public g getRootMenu() {
        return this;
    }

    public ArrayList<i> getVisibleItems() {
        if (!this.mIsVisibleItemsStale) {
            return this.mVisibleItems;
        }
        this.mVisibleItems.clear();
        int size = this.mItems.size();
        for (int i = 0; i < size; i++) {
            i iVar = this.mItems.get(i);
            if (iVar.isVisible()) {
                this.mVisibleItems.add(iVar);
            }
        }
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
    }

    public boolean hasVisibleItems() {
        if (this.mOverrideVisibleItems) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.mItems.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public boolean isGroupDividerEnabled() {
        return this.mGroupDividerEnabled;
    }

    boolean isQwertyMode() {
        return this.mQwertyMode;
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return findItemWithShortcutForKey(i, keyEvent) != null;
    }

    public boolean isShortcutsVisible() {
        return this.mShortcutsVisible;
    }

    void onItemActionRequestChanged(i iVar) {
        this.mIsActionItemsStale = true;
        onItemsChanged(true);
    }

    void onItemVisibleChanged(i iVar) {
        this.mIsVisibleItemsStale = true;
        onItemsChanged(true);
    }

    public void onItemsChanged(boolean z) {
        if (this.mPreventDispatchingItemsChanged) {
            this.mItemsChangedWhileDispatchPrevented = true;
            if (z) {
                this.mStructureChangedWhileDispatchPrevented = true;
                return;
            }
            return;
        }
        if (z) {
            this.mIsVisibleItemsStale = true;
            this.mIsActionItemsStale = true;
        }
        b(z);
    }

    public boolean performIdentifierAction(int i, int i2) {
        return performItemAction(findItem(i), i2);
    }

    public boolean performItemAction(MenuItem menuItem, int i) {
        return performItemAction(menuItem, null, i);
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        r.b bVarFindItemWithShortcutForKey = findItemWithShortcutForKey(i, keyEvent);
        boolean zPerformItemAction = bVarFindItemWithShortcutForKey != null ? performItemAction(bVarFindItemWithShortcutForKey, i2) : false;
        if ((i2 & 2) != 0) {
            close(true);
        }
        return zPerformItemAction;
    }

    public void removeGroup(int i) {
        int iFindGroupIndex = findGroupIndex(i);
        if (iFindGroupIndex >= 0) {
            int size = this.mItems.size() - iFindGroupIndex;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.mItems.get(iFindGroupIndex).getGroupId() != i) {
                    break;
                }
                h(iFindGroupIndex, false);
                i2 = i3;
            }
            onItemsChanged(true);
        }
    }

    public void removeItem(int i) {
        h(findItemIndex(i), true);
    }

    public void removeItemAt(int i) {
        h(i, true);
    }

    public void removeMenuPresenter(m mVar) {
        for (WeakReference<m> weakReference : this.mPresenters) {
            m mVar2 = weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.mPresenters.remove(weakReference);
            }
        }
    }

    public void restoreActionViewStates(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(getActionViewStatesKey());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).restoreActionViewStates(bundle);
            }
        }
        int i2 = bundle.getInt(EXPANDED_ACTION_VIEW_ID);
        if (i2 <= 0 || (menuItemFindItem = findItem(i2)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public void restorePresenterStates(Bundle bundle) {
        c(bundle);
    }

    public void saveActionViewStates(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt(EXPANDED_ACTION_VIEW_ID, item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).saveActionViewStates(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(getActionViewStatesKey(), sparseArray);
        }
    }

    public void savePresenterStates(Bundle bundle) {
        d(bundle);
    }

    public void setCallback(a aVar) {
        this.mCallback = aVar;
    }

    public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.mCurrentMenuInfo = contextMenuInfo;
    }

    public g setDefaultShowAsAction(int i) {
        this.mDefaultShowAsAction = i;
        return this;
    }

    void setExclusiveItemChecked(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.mItems.size();
        stopDispatchingItemsChanged();
        for (int i = 0; i < size; i++) {
            i iVar = this.mItems.get(i);
            if (iVar.getGroupId() == groupId && iVar.m() && iVar.isCheckable()) {
                iVar.s(iVar == menuItem);
            }
        }
        startDispatchingItemsChanged();
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.mItems.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.mItems.get(i2);
            if (iVar.getGroupId() == i) {
                iVar.t(z2);
                iVar.setCheckable(z);
            }
        }
    }

    public void setGroupDividerEnabled(boolean z) {
        this.mGroupDividerEnabled = z;
    }

    public void setGroupEnabled(int i, boolean z) {
        int size = this.mItems.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.mItems.get(i2);
            if (iVar.getGroupId() == i) {
                iVar.setEnabled(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int size = this.mItems.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.mItems.get(i2);
            if (iVar.getGroupId() == i && iVar.y(z)) {
                z2 = true;
            }
        }
        if (z2) {
            onItemsChanged(true);
        }
    }

    protected g setHeaderIconInt(Drawable drawable) {
        i(0, null, 0, drawable, null);
        return this;
    }

    protected g setHeaderTitleInt(CharSequence charSequence) {
        i(0, charSequence, 0, null, null);
        return this;
    }

    protected g setHeaderViewInt(View view) {
        i(0, null, 0, null, view);
        return this;
    }

    public void setOptionalIconsVisible(boolean z) {
        this.mOptionalIconsVisible = z;
    }

    public void setOverrideVisibleItems(boolean z) {
        this.mOverrideVisibleItems = z;
    }

    public void setQwertyMode(boolean z) {
        this.mQwertyMode = z;
        onItemsChanged(false);
    }

    public void setShortcutsVisible(boolean z) {
        if (this.mShortcutsVisible == z) {
            return;
        }
        j(z);
        onItemsChanged(false);
    }

    public int size() {
        return this.mItems.size();
    }

    public void startDispatchingItemsChanged() {
        this.mPreventDispatchingItemsChanged = false;
        if (this.mItemsChangedWhileDispatchPrevented) {
            this.mItemsChangedWhileDispatchPrevented = false;
            onItemsChanged(this.mStructureChangedWhileDispatchPrevented);
        }
    }

    public void stopDispatchingItemsChanged() {
        if (this.mPreventDispatchingItemsChanged) {
            return;
        }
        this.mPreventDispatchingItemsChanged = true;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
    }

    public MenuItem add(int i) {
        return addInternal(0, 0, 0, this.mResources.getString(i));
    }

    public void addMenuPresenter(m mVar, Context context) {
        this.mPresenters.add(new WeakReference<>(mVar));
        mVar.initForMenu(context, this);
        this.mIsActionItemsStale = true;
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.mResources.getString(i));
    }

    public int findGroupIndex(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < size) {
            if (this.mItems.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean performItemAction(MenuItem menuItem, m mVar, int i) {
        i iVar = (i) menuItem;
        if (iVar == null || !iVar.isEnabled()) {
            return false;
        }
        boolean zK = iVar.k();
        androidx.core.view.b bVarA = iVar.a();
        boolean z = bVarA != null && bVarA.b();
        if (iVar.j()) {
            zK |= iVar.expandActionView();
            if (zK) {
                close(true);
            }
        } else if (iVar.hasSubMenu() || z) {
            if ((i & 4) == 0) {
                close(false);
            }
            if (!iVar.hasSubMenu()) {
                iVar.x(new r(getContext(), this, iVar));
            }
            r rVar = (r) iVar.getSubMenu();
            if (z) {
                bVarA.g(rVar);
            }
            zK |= e(rVar, mVar);
            if (!zK) {
                close(true);
            }
        } else if ((i & 1) == 0) {
            close(true);
        }
        return zK;
    }

    protected g setHeaderIconInt(int i) {
        i(0, null, i, null, null);
        return this;
    }

    protected g setHeaderTitleInt(int i) {
        i(i, null, 0, null, null);
        return this;
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return addInternal(i, i2, i3, charSequence);
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        i iVar = (i) addInternal(i, i2, i3, charSequence);
        r rVar = new r(this.mContext, this, iVar);
        iVar.x(rVar);
        return rVar;
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return addInternal(i, i2, i3, this.mResources.getString(i4));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.mResources.getString(i4));
    }

    public void close() {
        close(true);
    }
}
