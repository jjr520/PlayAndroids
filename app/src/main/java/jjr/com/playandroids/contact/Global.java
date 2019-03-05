package jjr.com.playandroids.contact;

import android.graphics.Color;

import java.io.File;

import jjr.com.playandroids.R;
import jjr.com.playandroids.global.MyApp;

/**
 * Created by jjr on 2019/2/27.
 */

public class Global {

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    /**
     * Refresh theme color
     */
    public static final int BLUE_THEME = R.color.colorPrimary;

    /**
     * Phone MANUFACTURER
     */
    public static final String SAMSUNG = "samsung";

    //================= PATH ====================
    public static final String PATH_DATA = MyApp.getApplication().getCacheDir() + File.separator/* + "Cache"*/;

    public static final String PATH_CACHE = PATH_DATA/* + "/Cache"*/;

}
