package com.kmdev.flix.retrofilt;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kajal@Brsoftech on 20/6/16.
 */
public class RestService {

    private static long CONNECTION_TIMEOUT = 30;

    // private static String API_URL = "http://192.168.1.253:81/";
    private static String API_URL = "https://api.themoviedb.org/3/";


    public static Rest getService() {
        OkHttpClient client = getOkHttpClient();
        Rest rest = new Retrofit.Builder().baseUrl(API_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build().create(Rest.class);
        return rest;
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        //okClientBuilder.addInterceptor(headerAuthorizationInterceptor);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClientBuilder.addInterceptor(httpLoggingInterceptor);
        okClientBuilder.addNetworkInterceptor(new StethoInterceptor());
//        final @Nullable File baseDir = context.getCacheDir();
//        if (baseDir != null) {
//            final File cacheDir = new File(baseDir, “HttpResponseCache”);
//            okClientBuilder.cache(new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));
//        }
        okClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        return okClientBuilder.build();
    }
}
