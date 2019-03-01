package jjr.com.playandroids.persenter;

import android.util.Log;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import jjr.com.playandroids.module.OneModule;
import jjr.com.playandroids.module.TwoModule;
import jjr.com.playandroids.module.TwoModule.TwoCallBack;
import jjr.com.playandroids.view.OneView;
import jjr.com.playandroids.view.TwoView;

public class TwoPresenter<V extends TwoView> extends IBasePresenter<V> implements TwoCallBack {

    private TwoModule twoModule = new TwoModule();

    public void getDataTwoP(String onlyTwo) {
        twoModule.getDataTwo(this, onlyTwo);
    }

    @Override
    public void setData(Object o, String onlyTwo) {
        if (view != null) {
            view.showDataTwo(o, onlyTwo);
        }
    }

    @Override
    public void setshowProgressbar() {

    }

    @Override
    public void sethideProgressbar() {

    }

    @Override
    public void setshowError(String error) {

    }
}
