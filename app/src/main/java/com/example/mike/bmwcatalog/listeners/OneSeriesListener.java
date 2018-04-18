package com.example.mike.bmwcatalog.listeners;

import com.example.mike.bmwcatalog.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

/**
 * Created by Mike on 15.08.2017.
 */

public class OneSeriesListener implements MaterialViewPager.Listener {
    @Override
    public HeaderDesign getHeaderDesign(int page) {
        switch (page) {
            case 0:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.darkGray,
                        "https://s.car.info/image_files/1920/bmw-1-series-3-door-front-side-0-188651.jpg"
                );
            case 1:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.blue,
                        "https://s1.cdn.autoevolution.com/images/news/2018-bmw-2-series-facelift-is-just-a-tiny-little-nose-job-also-a-swan-song-117692_1.jpg"
                );

        }
        return null;
    }
}
