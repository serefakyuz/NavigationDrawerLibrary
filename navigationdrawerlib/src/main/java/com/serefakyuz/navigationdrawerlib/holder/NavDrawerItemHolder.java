package com.serefakyuz.navigationdrawerlib.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.serefakyuz.navigationdrawerlib.R;


/**
 * Created by seref-akyuz on 5/18/16.
 */
public class NavDrawerItemHolder extends RecyclerView.ViewHolder {
    private ImageView mImageViewItemIcon;
    private TextView mTextViewText;
    private TextView mTextViewSubTitle;
    private ImageView mImageViewArrow;
    private LinearLayout mLinearLayoutListItemArea;
    private ViewGroup mExpandableArea;

    public NavDrawerItemHolder(View itemView) {
        super(itemView);
        mImageViewItemIcon = (ImageView) this.itemView.findViewById(R.id.ImageViewDrawerItemIcon);
        mTextViewText = (TextView) this.itemView.findViewById(R.id.TextViewDrawerItemTitle);
        mTextViewSubTitle = (TextView) this.itemView.findViewById(R.id.TextViewDrawerItemSubTitle);
        mImageViewArrow = (ImageView)this.itemView.findViewById(R.id.ImageViewLeftDrawerItemArrow);
        mLinearLayoutListItemArea = (LinearLayout)this.itemView.findViewById(R.id.LinearLayoutListItemArea);
        mExpandableArea = (ViewGroup)this.itemView.findViewById(R.id.LinearLayoutExpandableArea);
    }


    public ImageView getImageViewItemIcon() {
        return mImageViewItemIcon;
    }

    public void setImageViewItemIcon(ImageView mImageViewItemIcon) {
        this.mImageViewItemIcon = mImageViewItemIcon;
    }

    public TextView getTextViewText() {
        return mTextViewText;
    }

    public void setTextViewText(TextView mTextViewText) {
        this.mTextViewText = mTextViewText;
    }

    public ImageView getImageViewArrow() {
        return mImageViewArrow;
    }

    public void setImageViewArrow(ImageView mImageViewArrow) {
        this.mImageViewArrow = mImageViewArrow;
    }

    public ViewGroup getExpandableArea() {
        return mExpandableArea;
    }

    public void setExpandableArea(ViewGroup mExpandableArea) {
        this.mExpandableArea = mExpandableArea;
    }

    public LinearLayout getLinearLayoutListItemArea() {
        return mLinearLayoutListItemArea;
    }

    public void setLinearLayoutListItemArea(LinearLayout mLinearLayoutListItemArea) {
        this.mLinearLayoutListItemArea = mLinearLayoutListItemArea;
    }

    public TextView getTextViewSubTitle() {
        return mTextViewSubTitle;
    }

    public void setTextViewSubTitle(TextView mTextViewSubTitle) {
        this.mTextViewSubTitle = mTextViewSubTitle;
    }
}
