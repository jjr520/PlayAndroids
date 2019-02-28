package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.FourModule;
import jjr.com.playandroids.module.NaviModule;
import jjr.com.playandroids.view.FourView;

public class FourPresenter<V extends FourView> extends IBasePresenter<V> implements FourModule.FourCallBack  {
    private FourModule fourModule = new FourModule();

    public void getDataFourP(String onlyOne,Object object){
        fourModule.getDataFour(this,onlyOne,object);
    }
    @Override
    public void setData(Object o, String onlyOne) {
        view.showDataFour(o,onlyOne);
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
