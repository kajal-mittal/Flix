package com.brsoftech.core_utils.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by ubuntu on 9/2/16.
 */
public class Fontutils {
    private static final String TAG = Fontutils.class.getSimpleName();

    private static Fontutils instance;
    HashMap<String, Typeface> fontsMap;

    public static Fontutils getInstance() {
        if (instance == null) {
            instance = new Fontutils();
        }
        return instance;
    }


    private void makeHasmap() {
        if (fontsMap == null) {
            fontsMap = new HashMap<String, Typeface>();
        }
    }

    public Typeface getFont(Context _context, String font) {
        makeHasmap();
        if (fontsMap.containsKey(font)) {
            return fontsMap.get(font);
        } else {
            Typeface mFont = Typeface.createFromAsset(_context.getAssets(),
                    font);
            fontsMap.put(font, mFont);
            return mFont;
        }


    }
}
