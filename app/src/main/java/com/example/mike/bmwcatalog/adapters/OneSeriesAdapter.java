package com.example.mike.bmwcatalog.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mike.bmwcatalog.fragment.CarsRVFragment;

/**
 * Created by Mike on 15.08.2017.
 */

public class OneSeriesAdapter extends FragmentStatePagerAdapter {

    public OneSeriesAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position % 2){
            case 0:
                return CarsRVFragment.newInstance("1-series", "3d");
            case 1:
                return CarsRVFragment.newInstance("1-series", "5d");

            default:
                return CarsRVFragment.newInstance(" ", " ");

        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position % 2){
            case 0:
                return "3D";
            case 1:
                return "5D";
        }
        return "";
    }

}
