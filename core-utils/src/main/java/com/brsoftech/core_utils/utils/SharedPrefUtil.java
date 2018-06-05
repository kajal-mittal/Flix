package com.brsoftech.core_utils.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kartik Sharma @ B.R. Softech on 3/3/16.
 */
public class SharedPrefUtil {
    private static final String DEFAULT_SHARED_PREFERENCES = "default_shared_preferences";

    private static final String DEFAULT_STRING_VALUE = "";
    private static final int DEFAULT_INT_VALUE = 0;
    private static final boolean DEFAULT_BOOLEAN_VALUE = false;

    public static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, DEFAULT_STRING_VALUE);
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, DEFAULT_INT_VALUE);
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, DEFAULT_BOOLEAN_VALUE);
    }

    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
