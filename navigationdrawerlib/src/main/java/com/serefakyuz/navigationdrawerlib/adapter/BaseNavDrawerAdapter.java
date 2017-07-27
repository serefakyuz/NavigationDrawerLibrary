package com.serefakyuz.navigationdrawerlib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serefakyuz.navigationdrawerlib.R;
import com.serefakyuz.navigationdrawerlib.holder.NavDrawerItemHolder;
import com.serefakyuz.navigationdrawerlib.listener.ExpandableViewListener;
import com.serefakyuz.navigationdrawerlib.listener.RecyclerViewItemClickListener;
import com.serefakyuz.navigationdrawerlib.listener.SubItemClickListener;
import com.serefakyuz.navigationdrawerlib.model.AbstractSubItemModel;
import com.serefakyuz.navigationdrawerlib.model.NavigationDrawerItemModel;

import java.util.List;


/**
 * Created by seref-akyuz on 3/23/16.
 */
public abstract class BaseNavDrawerAdapter<T extends AbstractSubItemModel> extends RecyclerView.Adapter<NavDrawerItemHolder> {

    protected Context mContext;
    protected List<NavigationDrawerItemModel> mNavigationDrawerItemModelList;
    protected RecyclerViewItemClickListener mClickListener;
    protected SubItemClickListener mSubItemClickListener;
    protected ExpandableViewListener mExpandableViewListener;

    public BaseNavDrawerAdapter(Context context, List<NavigationDrawerItemModel> navigationDrawerItemModelList, SubItemClickListener subItemClickListener, ExpandableViewListener expandableViewListener) {
        this.mContext = context;
        this.mNavigationDrawerItemModelList = navigationDrawerItemModelList;
        mClickListener = (RecyclerViewItemClickListener) context;
        this.mSubItemClickListener = subItemClickListener;
        this.mExpandableViewListener = expandableViewListener;
    }


    @Override
    public NavDrawerItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavDrawerItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(final NavDrawerItemHolder holder, final int position) {

        holder.getTextViewText().setText(getItem(position).getTitle());
        if (getItem(position).getSubTitle().equals("")) {
            holder.getTextViewSubTitle().setVisibility(View.GONE);
        } else {
            holder.getTextViewSubTitle().setVisibility(View.VISIBLE);
            holder.getTextViewSubTitle().setText(getItem(position).getSubTitle());
        }
        if (getItem(position).getImageResId() != 0) {
            holder.getImageViewItemIcon().setImageResource(getItem(position).getImageResId());
            holder.getImageViewItemIcon().setVisibility(View.VISIBLE);
        } else {
            holder.getImageViewItemIcon().setVisibility(View.GONE);
        }

        holder.getExpandableArea().setVisibility(getItem(position).isExpanded() ? View.VISIBLE : View.GONE);
        if (getItem(position).isNeedArrow()) {
            holder.getImageViewArrow().setImageResource(getItem(position).isExpanded() ? R.drawable.top_arrow : R.drawable.bottom_arrow_white);
            addSubItems(getItem(position).getTitle(), holder.getExpandableArea(), getItem(position).getSubList(), position);
            holder.getImageViewArrow().setVisibility(View.VISIBLE);
        } else {
            holder.getImageViewArrow().setVisibility(View.GONE);
        }


        holder.getImageViewArrow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandableViewListener.onItemExpanded(holder.getLinearLayoutListItemArea(), position);
                int expandedItemIndex = getExpandedItemPosition();
                getItem(position).setIsExpanded(!getItem(position).isExpanded());
                if (expandedItemIndex != position && expandedItemIndex != -1) {
                    getItem(expandedItemIndex).setIsExpanded(false);
                    mClickListener.notifyItems(new int[]{position, expandedItemIndex});
                } else {
                    mClickListener.notifyItems(new int[]{position});
                    if (!getItem(position).isExpanded()) {
                        return;
                    }
                }
                mClickListener.notifyItems(new int[]{position});
            }
        });

        holder.getLinearLayoutListItemArea().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSubItemSelection();
                int selectedItemPosition = getSelectedMenuItemPosition();
                int expandedItemPosition = getExpandedItemPosition();
                if (selectedItemPosition == position) {
                    return;
                }
                getItem(position).setIsSelected(true);
                if (selectedItemPosition == -1) {
                    if (expandedItemPosition != -1) {
                        getItem(expandedItemPosition).setIsExpanded(false);
                    }
                    mClickListener.notifyItems(new int[]{position, expandedItemPosition});
                } else {
                    getItem(selectedItemPosition).setIsSelected(false);
                    if (getItem(selectedItemPosition).isNeedArrow()) {
                        getItem(selectedItemPosition).setIsExpanded(false);
                    }
                    if (expandedItemPosition != -1) {
                        getItem(expandedItemPosition).setIsExpanded(false);
                        mClickListener.notifyItems(new int[]{position, selectedItemPosition, expandedItemPosition});
                    } else {
                        mClickListener.notifyItems(new int[]{position, selectedItemPosition});
                    }
                }
                mClickListener.onItemClicked(v, position);
            }
        });
        if (getItem(position).isSelected()) {
            holder.getLinearLayoutListItemArea().setBackgroundResource(R.color.black_lighter);
        } else {
            holder.getLinearLayoutListItemArea().setBackgroundResource(R.color.transparent);
        }
    }


    @Override
    public int getItemCount() {
        return mNavigationDrawerItemModelList.size();
    }

    protected NavigationDrawerItemModel getItem(int position) {
        return mNavigationDrawerItemModelList.get(position);
    }

    private void addSubItems(String parentId, ViewGroup parent, List<T> subItems, int parentPosition) {
        parent.removeAllViews();
        for (int i = 0; i < subItems.size(); i++) {
            parent.addView(getSubListItemView(parentId, subItems.get(i), i, parentPosition, parent));

        }

    }

    protected abstract View getSubListItemView(final String parentId, final T subItem, final int position, final int parentPosition, ViewGroup parent);

    protected void removeSubItemSelection() {
        for (int i = 0; i < mNavigationDrawerItemModelList.size(); i++) {
            NavigationDrawerItemModel navigationDrawerItem = mNavigationDrawerItemModelList.get(i);
            if (navigationDrawerItem.getSubList() != null) {
                int selectedItemPos = getSelectedSubItemPosition(navigationDrawerItem.getSubList());
                if (selectedItemPos != -1) {
                    ((AbstractSubItemModel) navigationDrawerItem.getSubList().get(selectedItemPos)).setSelected(false);
                }
            }
        }
    }

    protected boolean applySubItemChanges(List<T> subList, int position) {
        int selectedPosition = getSelectedSubItemPosition(subList);
        if (selectedPosition == -1) {
            subList.get(position).setSelected(true);
            notifyDataSetChanged();
        } else if (selectedPosition == position) {
            return false;
        } else {
            subList.get(selectedPosition).setSelected(false);
            subList.get(position).setSelected(true);
            notifyDataSetChanged();
        }
        return true;
    }

    private int getSelectedSubItemPosition(List<T> subItems) {
        for (int i = 0; i < subItems.size(); i++) {
            if (subItems.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private int getExpandedItemPosition() {
        for (int i = 0; i < mNavigationDrawerItemModelList.size(); i++) {
            if (mNavigationDrawerItemModelList.get(i).isExpanded()) {
                return i;
            }
        }
        return -1;
    }

    protected int getSelectedMenuItemPosition() {
        for (int i = 0; i < mNavigationDrawerItemModelList.size(); i++) {
            if (mNavigationDrawerItemModelList.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

}
