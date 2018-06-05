package com.brsoftech.core_utils.utils;

import android.graphics.Typeface;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 28/7/16</p>
 * <p>Time: 3:17 PM</p>
 * <p>Project: Planbeep</p>
 */
public class ToolbarUtil {
    private static final String TAG = ToolbarUtil.class.getSimpleName();

    /**
     * Set custom typeface for toolbar title text
     *
     * @param toolbar  current instance of toolbar
     * @param typeface typeface to be set on the toolbar title
     */
    public static void setTitleTypeface(Toolbar toolbar, Typeface typeface) {
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            TextView titleTextView = (TextView) f.get(toolbar);
            titleTextView.setTypeface(typeface);
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "setTitleTypeface: ", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "setTitleTypeface: ", e);
        }
    }
}
