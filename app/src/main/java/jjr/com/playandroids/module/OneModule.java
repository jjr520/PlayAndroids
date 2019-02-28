package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.entityclass.NaviListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyFour;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.utils.RxUtils;

public class OneModule {
    public interface OneCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }
    public void getDataOne(final OneCallBack oneCallBack, final String onlyOne, Object object){
        switch (onlyOne){
            case OnlyOne.ONE:

                break;
        }
    }
}
