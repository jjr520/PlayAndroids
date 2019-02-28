package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.ThereModule;
import jjr.com.playandroids.view.ThereView;
import jjr.com.playandroids.view.TwoView;

public class TherePresenter<V extends ThereView> extends IBasePresenter<V> implements ThereModule.ThereCallBack {

    private ThereModule thereModule = new ThereModule();
    public void getDataThereP(String onlyOne,Object object){
        thereModule.getDataThere(this,onlyOne,object);
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
