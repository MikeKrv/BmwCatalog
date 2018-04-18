package com.example.mike.bmwcatalog.listeners;

import com.example.mike.bmwcatalog.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

/**
 * Created by Mike on 29.07.2017.
 */

public class ThreeSeriesListener implements MaterialViewPager.Listener {

    @Override
    public HeaderDesign getHeaderDesign(int page) {
        switch (page) {
            case 0:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.blue,
                        "https://media.ed.edmunds-media.com/bmw/3-series/2017/oem/2017_bmw_3-series_sedan_340i_fq_oem_3_1280.jpg");
            case 1:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.green_teal,
                        "http://www.bmwcoop.com/wp-content/images/2015/05/2015-BMW-3-Series-Facelift-29.jpg");
            case 2:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.lightGray,
                        "http://lln5.mnmcdn.com/content/january2016/bmw-3-series-gt-new-server-ri-2.jpg");
        }
        return null;
    }
}
