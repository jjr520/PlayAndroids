package jjr.com.playandroids.global;

import android.app.Application;

public class MyApp extends Application {
    private static MyApp sMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
    }

    public static MyApp getApplication() {
        return sMyApp;
    }
}
