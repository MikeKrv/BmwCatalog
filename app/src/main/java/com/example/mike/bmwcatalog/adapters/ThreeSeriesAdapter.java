package com.example.mike.bmwcatalog.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mike.bmwcatalog.fragment.CarsRVFragment;

/**
 * Created by Mike on 29.07.2017.
 */

public class ThreeSeriesAdapter extends FragmentStatePagerAdapter {

    public ThreeSeriesAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position % 3){
            case 0:
                return CarsRVFragment.newInstance("3-series", "Sedan");
            case 1:
                return CarsRVFragment.newInstance("3-series", "Touring");
            case 2:
                return CarsRVFragment.newInstance("3-series", "GT");
            default:
                return CarsRVFragment.newInstance(" ", " ");
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position % 3){
            case 0:
                return "Sedan";
            case 1:
                return "Touring";
            case 2:
                return "GT";
        }
        return "";
    }
}
