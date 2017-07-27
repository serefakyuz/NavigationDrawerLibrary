package com.serefakyuz.navigationdrawersample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serefakyuz.navigationdrawerlib.adapter.BaseNavDrawerAdapter;
import com.serefakyuz.navigationdrawerlib.listener.ExpandableViewListener;
import com.serefakyuz.navigationdrawerlib.listener.SubItemClickListener;
import com.serefakyuz.navigationdrawerlib.model.AbstractSubItemModel;
import com.serefakyuz.navigationdrawerlib.model.NavigationDrawerItemModel;

import java.util.List;

/**
 * Created by seref-akyuz on 7/24/17.
 */

public class NavDrawerAdapter extends BaseNavDrawerAdapter {

    public NavDrawerAdapter(Context context, List<NavigationDrawerItemModel> navigationDrawerItemModelList, SubItemClickListener subItemClickListener, ExpandableViewListener expandableViewListener) {
        super(context, navigationDrawerItemModelList, subItemClickListener, expandableViewListener);
    }
    @Override
    protected View getSubListItemView(final String parentId, final AbstractSubItemModel subItem, final int position, final int parentPosition, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_nav_drawer_sub_list, parent, false);
        if (subItem.isSelected()) {
            v.setBackgroundResource(R.color.black_light);
        } else {
            v.setBackgroundResource(R.color.transparent);
        }
        TextView name = v.findViewById(R.id.TextViewSubItemName);
        name.setText(((SubListModel)subItem).getTitle());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSubItemSelection();
                if (applySubItemChanges(getItem(parentPosition).getSubList(), position)) {
                    int selectedItemPosition = getSelectedMenuItemPosition();
                    if (selectedItemPosition != -1) {
                        NavigationDrawerItemModel item = getItem(selectedItemPosition);
                        item.setIsSelected(false);
                        mClickListener.notifyItems(new int[]{selectedItemPosition});
                    }
                    mSubItemClickListener.onSubItemClick(parentId, subItem);
                }
            }
        });
        return v;
    }
}
