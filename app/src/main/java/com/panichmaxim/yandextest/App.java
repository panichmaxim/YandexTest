package com.panichmaxim.yandextest;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);

    }

    public static App getContext() { return sInstance; }

}
