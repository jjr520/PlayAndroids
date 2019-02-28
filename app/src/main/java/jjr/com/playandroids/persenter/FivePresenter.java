package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.FiveModule;
import jjr.com.playandroids.module.FourModule;
import jjr.com.playandroids.view.FiveView;
import jjr.com.playandroids.view.FourView;

public class FivePresenter<V extends FiveView> extends IBasePresenter<V> implements FiveModule.FiveCallBack {

    private FiveModule fiveModule = new FiveModule();

    public void getDataFiveP(String onlyOne, Object object) {
        fiveModule.getDataFive(this, onlyOne, object);
    }

    @Override
    public void setData(Object o, String onlyOne) {
        if (view != null) {
            view.showDataFive(o, onlyOne);
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
        if (view != null) {
            view.showError(error);
        }
    }
}
