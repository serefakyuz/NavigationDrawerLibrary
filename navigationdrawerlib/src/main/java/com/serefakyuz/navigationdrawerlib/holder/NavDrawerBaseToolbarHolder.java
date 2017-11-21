package com.serefakyuz.navigationdrawerlib.holder;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serefakyuz.navigationdrawerlib.R;


/**
 *
 * if the components in toolbar are not necessary, you can create your own toolbar;
 * * Create a toolbar in layout xml file, extend this class and define your components in your class
 *
 * Created by seref-akyuz on 7/25/17.
 */

public abstract class NavDrawerBaseToolbarHolder {

    private Toolbar mToolbar;


    /**
     *  Init components in toolbar
     *
     * @param v
     */
    protected abstract void initToolbar(View v);

    /**
     *
     * @param toolbar
     */
    public NavDrawerBaseToolbarHolder(Toolbar toolbar) {
        mToolbar = toolbar;
        initToolbar(mToolbar);
    }

    /**
     * you can use your own toolbar xml file
     *
     * @param context
     * @param layoutResId
     * @param root
     * @param attachToRoot
     */
    public NavDrawerBaseToolbarHolder(Context context, int layoutResId, ViewGroup root, boolean attachToRoot) {
        mToolbar = (Toolbar)LayoutInflater.from(context).inflate(layoutResId, root, attachToRoot);
        initToolbar(mToolbar);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
