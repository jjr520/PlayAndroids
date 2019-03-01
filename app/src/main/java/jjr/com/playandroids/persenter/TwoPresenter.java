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

    public void getDataTwoP(String onlyTwo, int page, int cid) {
        view.showProgressbar();
        twoModule.getDataTwo(this, onlyTwo, page, cid);
    }

    @Override
    public void setData(Object o, String onlyTwo) {
        view.hideProgressbar();
        if (view != null) {
            view.showDataTwo(o, onlyTwo);
        }
    }

    @Override
    public void setshowProgressbar() {
        if (view != null) {
            view.showProgressbar();
        }
    }

    @Override
    public void sethideProgressbar() {
        if (view != null) {
            view.hideProgressbar();
        }
    }

    @Override
    public void setshowError(String error) {
        if (view != null) {
            view.hideProgressbar();
            view.showError(error);
        }
    }
}
