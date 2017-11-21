package com.serefakyuz.navigationdrawerlib.model;

import java.util.List;


/**
 * Created by seref-akyuz on 5/18/16.
 */
public class NavigationDrawerItemModel<T extends AbstractSubItemModel> {
    private String mTitle;
    private String mSubTitle;
    private int mImageResId;
    private boolean isExpanded = false;
    private boolean isSelected;
    private List<T> mSubList;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public void setImageResId(int mImageResId) {
        this.mImageResId = mImageResId;
    }

    public boolean isNeedArrow() {
        if(mSubList == null){
            return false;
        }else{
            return mSubList.size() != 0;
        }
    }

    public boolean isExpanded(){
        return isNeedArrow()? isExpanded : false;
    }

    public void setIsExpanded(boolean isExpanded){
        this.isExpanded = isExpanded;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public List<T> getSubList() {
        return mSubList;
    }

    public void setSubList(List<T> mSubList) {
        this.mSubList = mSubList;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String mSubTitle) {
        this.mSubTitle = mSubTitle;
    }
}
