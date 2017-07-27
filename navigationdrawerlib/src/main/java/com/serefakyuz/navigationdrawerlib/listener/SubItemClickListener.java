package com.serefakyuz.navigationdrawerlib.listener;

import com.serefakyuz.navigationdrawerlib.model.AbstractSubItemModel;

/**
 * Created by seref-akyuz on 7/21/17.
 */

public interface SubItemClickListener<T extends AbstractSubItemModel> {
    void onSubItemClick(String parentType, T subItem);
}
