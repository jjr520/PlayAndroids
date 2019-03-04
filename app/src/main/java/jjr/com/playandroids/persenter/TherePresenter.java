package jjr.com.playandroids.persenter;

import java.io.BufferedReader;
import java.util.HashMap;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.ThereModule;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.view.ThereView;
import jjr.com.playandroids.view.TwoView;

import static jjr.com.playandroids.only.OnlyThere.SEARCH;
import static jjr.com.playandroids.only.OnlyThere.WCHISTORY;
import static jjr.com.playandroids.only.OnlyThere.WECHATTAB;
import static jjr.com.playandroids.only.OnlyThere.WXDETAIL;

public class TherePresenter<V extends ThereView> extends IBasePresenter<V> implements ThereModule.ThereCallBack {

    private ThereModule thereModule = new ThereModule();

    public void getDataThereP(String onlyOne, HashMap<String, Object> map) {
        view.showProgressbar();
        thereModule.getDataThere(this, onlyOne, map);
    }

    @Override
    public void setData(Object o, String onlyOne) {
        view.hideProgressbar();
        switch (onlyOne) {
            case WECHATTAB:
                view.showDataThere(o, OnlyThere.WECHATTAB);
                break;
            case WCHISTORY:
                view.showDataThere(o, OnlyThere.WCHISTORY);
                break;
            case WXDETAIL:
                view.showDataThere(o, OnlyThere.WXDETAIL);
                break;
            case SEARCH:
                view.showDataThere(o, OnlyThere.SEARCH);
                break;
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
