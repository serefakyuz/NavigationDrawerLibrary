package com.serefakyuz.navigationdrawerlib.listener;

import android.view.View;

/**
 * Created by seref-akyuz on 5/18/16.
 */
public interface RecyclerViewItemClickListener {
    void onItemClicked(View view, int position);
    void notifyItems(int[] positions);
}
