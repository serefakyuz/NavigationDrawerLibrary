package com.serefakyuz.navigationdrawersample;

import com.serefakyuz.navigationdrawerlib.model.AbstractSubItemModel;

/**
 * Created by seref-akyuz on 7/24/17.
 */

public class SubListModel extends AbstractSubItemModel {
    private boolean isSelected;

    private String title;
    private String subTitle;
    private int imageResId;

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
