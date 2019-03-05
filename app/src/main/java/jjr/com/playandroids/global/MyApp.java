package jjr.com.playandroids.global;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApp extends Application {
    private static MyApp sMyApp;

    //static 代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

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
