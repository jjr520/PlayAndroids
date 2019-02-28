package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.FourModule;
import jjr.com.playandroids.module.OneModule;
import jjr.com.playandroids.view.FourView;
import jjr.com.playandroids.view.OneView;

public class OnePresenter<V extends OneView> extends IBasePresenter<V> implements OneModule.OneCallBack {

    private OneModule oneModule = new OneModule();
    public void getDataOneP(String onlyOne,Object object){
        oneModule.getDataOne(this,onlyOne,object);
    }
    @Override
    public void setData(Object o, String onlyOne) {

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
