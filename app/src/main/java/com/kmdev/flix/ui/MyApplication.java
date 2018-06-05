package com.kmdev.flix.ui;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.kmdev.flix.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Kajal on 11/23/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());


    }


}
