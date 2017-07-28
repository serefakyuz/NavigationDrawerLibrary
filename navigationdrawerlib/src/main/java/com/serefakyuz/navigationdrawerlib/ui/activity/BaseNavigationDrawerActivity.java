package com.serefakyuz.navigationdrawerlib.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.serefakyuz.navigationdrawerlib.R;
import com.serefakyuz.navigationdrawerlib.listener.ExpandableViewListener;
import com.serefakyuz.navigationdrawerlib.listener.RecyclerViewItemClickListener;
import com.serefakyuz.navigationdrawerlib.listener.SubItemClickListener;
import com.serefakyuz.navigationdrawerlib.model.AbstractSubItemModel;
import com.serefakyuz.navigationdrawerlib.model.NavigationDrawerItemModel;
import com.serefakyuz.navigationdrawerlib.ui.view.NavDrawerToolbarHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seref-akyuz on 4/5/16.
 */
public abstract class BaseNavigationDrawerActivity<T extends AbstractSubItemModel>  extends LanguageSwitcherActivity implements SubItemClickListener, View.OnClickListener, RecyclerViewItemClickListener, ExpandableViewListener {

    private static final String TAG = "BaseToolbarLibActivity";

    protected CharSequence mTitle;
    protected DrawerLayout mDrawerLayout;
    protected RecyclerView mRecycerViewLeftDrawer;
    protected String firstBackStateName;
    private TextView mTextViewTitle;
    protected List<NavigationDrawerItemModel> mNavigationDrawerItemModelList;
    protected ImageView mSearchView;
    private Toolbar mToolbar;

    protected ActionBarDrawerToggle mDrawerToggle;


    public abstract void onNavigationItemSelected(View selectedView, int position);
    protected abstract void initRecyclerView();
    protected abstract void initToolbar(ViewGroup viewGroup);
    public abstract void initNavigationDrawer();
    public abstract void navigationDrawerOpened();
    public abstract void navigationDrawerClosed();

    protected FrameLayout mFrameLayout;
    private float lastTranslate = 0.0f;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mNavigationDrawerItemModelList = new ArrayList<>();
    }


    @Override
    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initToolbar((ViewGroup)view);
        mFrameLayout = (FrameLayout)findViewById(R.id.content_frame);
    }



    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if (mTextViewTitle == null) {
            mTextViewTitle = mToolbar.findViewById(R.id.TextViewTitle);
        }
        mTextViewTitle.setText(mTitle);
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    protected void setToolbar(NavDrawerToolbarHolder toolbar, ViewGroup viewGroup){
        mToolbar = toolbar.getToolbar();
        viewGroup.addView(mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setDrawerToggle();
    }

    @Override
    public void onClick(View view) {
    }
    public void replaceFragmentBase(Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        boolean fragmentPopped = getFragmentManager().popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(backStateName).commit();
        }
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        }

        // update selected item title, then close_plan the drawer
        //setTitle(getString(title));
    }

    @Override
    public void onBackPressed() {
        try {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawers();
            } else if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStackImmediate(firstBackStateName, 0);
                //setTitle(mDrawerTitle);
                return;
            } else {
                finish();
            }
        } catch (Exception e) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStackImmediate(firstBackStateName, 0);
                //setTitle(mDrawerTitle);
                return;
            } else {
                finish();
            }
        }
    }

    private void setDrawerToggle(){

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mRecycerViewLeftDrawer = (RecyclerView) findViewById(R.id.left_drawer);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.yes, R.string.no) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
                mDrawerLayout.invalidate();

                float moveFactor = (mRecycerViewLeftDrawer.getWidth() * slideOffset);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    mFrameLayout.setTranslationX(moveFactor);
                    mToolbar.setTranslationX(moveFactor);
                } else {
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    mFrameLayout.startAnimation(anim);
                    mToolbar.startAnimation(anim);

                    lastTranslate = moveFactor;
                }
            }
        };

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaseNavigationDrawerActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    protected void addNavigationDrawerItems(int imgResId, int titleResId, String subTitle, boolean isExpanded, boolean isSelected, List<T> subList){
        NavigationDrawerItemModel item = new NavigationDrawerItemModel();
        item.setImageResId(imgResId);
        item.setTitle(getString(titleResId));
        item.setSubTitle(subTitle);
        item.setIsExpanded(isExpanded);
        item.setIsSelected(isSelected);
        item.setSubList(subList);
        mNavigationDrawerItemModelList.add(item);

    }

    protected void addNavigationDrawerItems(int imgResId, String title, String subTitle, boolean isExpanded, boolean isSelected, List<T> subList){
        NavigationDrawerItemModel item = new NavigationDrawerItemModel();
        item.setImageResId(imgResId);
        item.setTitle(title);
        item.setSubTitle(subTitle);
        item.setIsExpanded(isExpanded);
        item.setIsSelected(isSelected);
        item.setSubList(subList);
        mNavigationDrawerItemModelList.add(item);

    }

    @Override
    public void onItemClicked(View view, int position) {
        onNavigationItemSelected(view, position);
    }



    public void replaceFragment(final Fragment fragment, boolean closeDrawer) {
        if(closeDrawer) {
            closeDrawers();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).commit();


    }


    public void closeDrawers(){
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        }
    }

}
