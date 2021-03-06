package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.FiveModule;
import jjr.com.playandroids.module.FourModule;
import jjr.com.playandroids.view.FiveView;
import jjr.com.playandroids.view.FourView;

public class FivePresenter<V extends FiveView> extends IBasePresenter<V> implements FiveModule.FiveCallBack {

    private FiveModule fiveModule = new FiveModule();

    public void getDataFiveP(String onlyOne, Object object) {
        view.showProgressbar();
        fiveModule.getDataFive(this, onlyOne, object);
    }

    @Override
    public void setData(Object o, String onlyOne) {

        if (view != null) {
            view.hideProgressbar();
            view.showDataFive(o, onlyOne);
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
