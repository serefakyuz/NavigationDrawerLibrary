package com.serefakyuz.navigationdrawerlib.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.serefakyuz.navigationdrawerlib.interfaces.ILanguageSwitcher;

import java.util.Locale;


/**
 * Created by seref-akyuz on 6/1/16.
 */
public class LanguageSwitcherActivity extends AppCompatActivity implements ILanguageSwitcher {

    private static final String TAG = "LanguageSwitchActivity";

    protected void onCreate(Bundle savedInstanceState, Locale language) {
        super.onCreate(savedInstanceState);
        switchLanguage(language);
    }



    @Override
    public void switchLanguage(Locale langCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = langCode;
        res.updateConfiguration(conf, dm);
    }
}
