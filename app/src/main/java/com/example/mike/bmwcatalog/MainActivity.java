package com.example.mike.bmwcatalog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mike.bmwcatalog.adapters.OneSeriesAdapter;
import com.example.mike.bmwcatalog.adapters.ThreeSeriesAdapter;
import com.example.mike.bmwcatalog.listeners.OneSeriesListener;
import com.example.mike.bmwcatalog.listeners.ThreeSeriesListener;
import com.github.florent37.materialviewpager.MaterialViewPager;

public class MainActivity extends DrawerActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String CURRENT_CATEGORY_DATA = "key_category";

    MaterialViewPager mViewPager;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String category = intent.getStringExtra("category");
        if (category != null) {
            editor.putString(CURRENT_CATEGORY_DATA, category).apply();
            selectModelsCategory(category);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        //setContentView(R.layout.activnity_main);

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (savedInstanceState != null) {
            SharedPreferences sp = getPreferences(MODE_PRIVATE);
            String category;
            category = sp.getString(CURRENT_CATEGORY_DATA, "");
            selectModelsCategory(category);
        }
    }

    private void selectModelsCategory(String category){
        switch (category) {
            case "1-series":
                initializeViewPager(new OneSeriesAdapter(getSupportFragmentManager()), new OneSeriesListener());
                break;
            case "2-series":
                break;
            case "3-series":
                initializeViewPager(new ThreeSeriesAdapter(getSupportFragmentManager()), new ThreeSeriesListener());
                break;

        }
    }

    private void initializeViewPager(FragmentStatePagerAdapter adapter, MaterialViewPager.Listener listener) {
        mViewPager.getViewPager().setAdapter(adapter);
        mViewPager.setMaterialViewPagerListener(listener);
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.notifyHeaderChanged();
    }



}
