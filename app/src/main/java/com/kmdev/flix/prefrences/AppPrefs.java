package com.kmdev.flix.prefrences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPrefs {


    public static final String KEY_MENU_VALUE = "menu_value";
    public static final String KEY_TV_MENU_VALUE = "tv_menu_value";
    private static String Prefsname = "Flix";


    public static void clearPrefsdata(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        prefs.edit().clear().commit();
    }

    public static void setStringKeyvaluePrefs(Context ctx, String key,
                                              String value) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static String getStringKeyvaluePrefs(Context ctx, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }


}
