package com.brsoftech.core_utils.utils;

import android.os.Bundle;

/**
 * Created by Kartik Sharma @ B.R. Softech on 3/3/16.
 */
public class BundleUtil {
    private Bundle mBundle;

    public BundleUtil() {
        mBundle = new Bundle();
    }

    public BundleUtil addString(String key, String value) {
        mBundle.putString(key, value);
        return this;
    }

    public BundleUtil addInt(String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }

    public BundleUtil addBoolean(String key, boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }

    public Bundle getBundle() {
        return mBundle;
    }
}
