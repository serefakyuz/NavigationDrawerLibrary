package com.serefakyuz.navigationdrawersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by seref-akyuz on 7/26/17.
 */

public class SampleFragment extends Fragment{
    private static final String TAG = SampleFragment.class.getSimpleName();

    private static final String EXTRA_PAGE_NAME = "page_name";

    public static SampleFragment getInstance(String pageName){
        SampleFragment fragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_PAGE_NAME, pageName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            TextView textView = view.findViewById(R.id.TextViewPageName);
            textView.setText(bundle.getString(EXTRA_PAGE_NAME));
        }
    }
}
