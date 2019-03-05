package jjr.com.playandroids.global;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApp extends Application {
    private static MyApp sMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;

        CrashReport.initCrashReport(getApplicationContext(), "a2ad368014", true);
    }

    public static MyApp getApplication() {
        return sMyApp;
    }


}
