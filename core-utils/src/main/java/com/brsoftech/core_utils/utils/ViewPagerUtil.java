package com.brsoftech.core_utils.utils;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 20/7/16</p>
 * <p>Time: 11:07 AM</p>
 * <p>Project: Planbeep</p>
 */
public class ViewPagerUtil {
    public static void hideFabOnPosition(final FloatingActionButton floatingActionButton, final int position, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int currPosition) {
                if (position == currPosition) {
                    floatingActionButton.hide();
                } else {
                    floatingActionButton.show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
