package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.CollectModule;
import jjr.com.playandroids.module.FourModule;
import jjr.com.playandroids.view.CollectView;
import jjr.com.playandroids.view.FourView;

public class CollectPresenter<V extends CollectView> extends IBasePresenter<V> implements CollectModule.CollectCallback {
    private CollectModule collectModule = new CollectModule();
    public void getCollectP(String onlyCollect, Object object){
        view.showProgressbar();
        collectModule.getCollectList(this,onlyCollect,object);
    }
    @Override
    public void setCollectList(Object o, String only) {
        if (view!=null){
            view.hideProgressbar();
            view.setCollect(o,only);
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
