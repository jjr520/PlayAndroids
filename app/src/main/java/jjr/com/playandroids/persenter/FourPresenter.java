package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.FourModule;
import jjr.com.playandroids.view.FourView;

public class FourPresenter<V extends FourView> extends IBasePresenter<V> implements FourModule.FourCallBack  {
    private FourModule fourModule = new FourModule();

    public void getDataFourP(String onlyOne,Object object){
        view.showProgressbar();
        fourModule.getDataFour(this,onlyOne,object);
    }
    @Override
    public void setData(Object o, String onlyOne) {
        if (view!=null){
            view.hideProgressbar();
            view.showDataFour(o,onlyOne);
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

    }
}
