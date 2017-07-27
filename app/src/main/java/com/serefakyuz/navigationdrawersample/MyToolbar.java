package com.serefakyuz.navigationdrawersample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serefakyuz.navigationdrawerlib.ui.view.NavDrawerToolbarHolder;

/**
 * Created by seref-akyuz on 7/25/17.
 */

public class MyToolbar extends NavDrawerToolbarHolder {

    private ImageView mSearchView;
    private TextView mTitle;
    private ImageView mImageViewToolbarLogo;

    /**
     *
     * @param context
     * @param layoutResId
     * @param root
     * @param attachToRoot
     */
    public MyToolbar(Context context, int layoutResId, ViewGroup root, boolean attachToRoot) {
        super(context, layoutResId, root, attachToRoot);
    }

    @Override
    protected void initToolbar(View v) {

        mSearchView = v.findViewById(com.serefakyuz.navigationdrawerlib.R.id.ImageViewSearch);
        mTitle = v.findViewById(com.serefakyuz.navigationdrawerlib.R.id.TextViewTitle);
        mImageViewToolbarLogo = v.findViewById(com.serefakyuz.navigationdrawerlib.R.id.ImageViewToolbarLogo);
    }

    public ImageView getSearchView() {
        return mSearchView;
    }


    public TextView getToolbarTitle() {
        return mTitle;
    }

    public ImageView getImageViewToolbarLogo() {
        return mImageViewToolbarLogo;
    }
}
