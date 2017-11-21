package com.serefakyuz.navigationdrawersample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serefakyuz.navigationdrawerlib.ui.activity.BaseNavigationDrawerActivity;
import com.serefakyuz.navigationdrawerlib.model.AbstractSubItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seref-akyuz on 7/25/17.
 */
public class NavigationDrawerActivity extends BaseNavigationDrawerActivity {

    private static final String TAG = NavigationDrawerActivity.class.getSimpleName();

    public static final int PAGE_TYPE_JUST_TITLE = 1;
    public static final int PAGE_TYPE_WITH_SUBTITLE = 2;
    public static final int PAGE_TYPE_WITH_SUBITEMS = 3;
    public static final int PAGE_TYPE_WITH_IMAGE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation_drawer);
        initNavigationDrawer();
        replaceFragment(SampleFragment.getInstance(getString(R.string.menu_just_title)), true);
    }

    @Override
    public void onNavigationItemSelected(View selectedView, int position) {
        switch (position + 1) {
            case PAGE_TYPE_JUST_TITLE:
                replaceFragment(SampleFragment.getInstance(getString(R.string.menu_just_title)), true);
                break;
            case PAGE_TYPE_WITH_SUBTITLE:
                replaceFragment(SampleFragment.getInstance(getString(R.string.menu_with_sub_title)), true);
                break;
            case PAGE_TYPE_WITH_SUBITEMS:
                replaceFragment(SampleFragment.getInstance(getString(R.string.menu_with_subitems)), true);
                break;
            case PAGE_TYPE_WITH_IMAGE:
                replaceFragment(SampleFragment.getInstance(getString(R.string.menu_with_image)), true);
                break;
            default:
                Toast.makeText(this, R.string.page_not_found_message, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void navigationDrawerOpened() {

    }

    @Override
    public void navigationDrawerClosed() {

    }

    @Override
    public void initNavigationDrawer() {
        addNavigationDrawerItems(0, getString(R.string.menu_just_title), "", false, true, null);
        addNavigationDrawerItems(0, getString(R.string.menu_with_sub_title), getString(R.string.sub_title_text), false, false, null);
        addNavigationDrawerItems(0, getString(R.string.menu_with_subitems), "", false, false, getSubList());
        addNavigationDrawerItems(R.mipmap.ic_launcher, getString(R.string.menu_with_image), "", false, false, null);
        initRecyclerView();
    }

    @Override
    protected void initToolbar(ViewGroup viewGroup) {
        // init toolbar
        MyToolbar toolbar = new MyToolbar(this, R.layout.my_toolbar, viewGroup, false);
        toolbar.getSearchView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationDrawerActivity.this, R.string.search_icon_click_text, Toast.LENGTH_SHORT).show();
            }
        });
        setToolbar(toolbar, viewGroup);
    }


    @Override
    protected void initRecyclerView() {
        mRecycerViewLeftDrawer.setLayoutManager(new LinearLayoutManager(this));
        NavDrawerAdapter adapter = new NavDrawerAdapter(this, mNavigationDrawerItemModelList, this, this);
        mRecycerViewLeftDrawer.setAdapter(adapter);
    }

    @Override
    public void onItemExpanded(View parent, int position) {

    }

    @Override
    public void notifyItems(int[] positions) {
        for (int position : positions) {
            mRecycerViewLeftDrawer.getAdapter().notifyItemChanged(position);
        }
    }

    @Override
    public void onSubItemClick(String parentType, AbstractSubItemModel subItem) {
        replaceFragment(SampleFragment.getInstance(((SubListModel)subItem).getTitle()), true);

    }

    private List<AbstractSubItemModel> getSubList() {
        List<AbstractSubItemModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SubListModel model = new SubListModel();
            model.setTitle(getString(R.string.subitem_title_text) + (i + 1));
            list.add(model);
        }
        return list;
    }
}