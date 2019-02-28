package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyFour;
import jjr.com.playandroids.utils.RxUtils;

public class FourModule {
    public interface FourCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }
    public void getDataFour(final FourCallBack naviCallBack, final String onlyOne, Object object){
        switch (onlyOne){
            case OnlyFour.NAVI:
                HttpManager.getInstance().getServer(MyServer.HOST,MyServer.class).getNaviList().compose(RxUtils.<NaviListBean>rxScheduleThread()).subscribe(new BaseObserver<NaviListBean>(naviCallBack) {
                    @Override
                    public void onNext(NaviListBean value) {
                        naviCallBack.setData(value,onlyOne);
                    }
                });
                break;
        }
    }
}
