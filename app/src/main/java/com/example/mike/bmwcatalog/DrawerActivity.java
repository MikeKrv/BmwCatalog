package com.example.mike.bmwcatalog;

import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mike.bmwcatalog.adapters.ModelsNameAdapter;
import com.example.mike.bmwcatalog.model.NavItem;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity {
    private final static String TAG = DrawerActivity.class.getSimpleName();

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView listView;
    private List<NavItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lst_menu_items);
        items.add(new NavItem("1-series", false));
        items.add(new NavItem("2-series", false));
        items.add(new NavItem("3-series", false));
        items.add(new NavItem("4-series", false));
        items.add(new NavItem("5-series", false));
        items.add(new NavItem("6-series", false));
        items.add(new NavItem("7-series", false));
        items.add(new NavItem("X-series", false));
        items.add(new NavItem("Z-series", false));
        items.add(new NavItem("i-series", false));
        items.add(new NavItem("M-series", false));
        ModelsNameAdapter adapter = new ModelsNameAdapter(this, items);
        listView.setAdapter(adapter);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.addDrawerListener(mDrawerToggle);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listView.getChildCount(); i++){
                    if (position == i){
                        listView.getChildAt(i).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGray));
                        String categoryName = ((NavItem)listView.getItemAtPosition(i)).getModelName();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("category", categoryName);
                        startActivity(intent);
                    } else {
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                mDrawer.closeDrawers();
            }
        });

        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
