package jjr.com.playandroids.beans.setting;

/**
 * Created by Administrator on 2019/3/5.
 */

public class NightModeEvent {

    private boolean isNightMode;

    public NightModeEvent(boolean isNightMode) {
        this.isNightMode = isNightMode;
    }

    public boolean isNightMode() {
        return isNightMode;
    }

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }
}
