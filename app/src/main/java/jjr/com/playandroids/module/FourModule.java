package jjr.com.playandroids.module;

import java.util.HashMap;
import java.util.Map;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.collect.CollectDataList;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyCollect;
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
            case OnlyFour.Articles_INSTATION://收藏站内
                break;
            case OnlyFour.Articles_outside://收藏站外
                Map<String,Object> map = (Map<String, Object>) object;
                HttpManager.getInstance().getServer(MyServer.HOST,MyServer.class).getDataAdd(map).compose(RxUtils.<CollectDataList>rxScheduleThread()).subscribe(new BaseObserver<CollectDataList>(naviCallBack) {
                    @Override
                    public void onNext(CollectDataList value) {
                        naviCallBack.setData(value,onlyOne);
                    }
                });
                break;
            case OnlyFour.A_DELETE_COLLECTION:
                int id2 = (int) object;
                HttpManager.getInstance().getServer(MyServer.HOST,MyServer.class).getUncoolect(id2).compose(RxUtils.<CollectDataList>rxScheduleThread()).subscribe(new BaseObserver<CollectDataList>(naviCallBack) {
                    @Override
                    public void onNext(CollectDataList value) {
                        naviCallBack.setData(value,onlyOne);
                    }
                });
                break;
        }
    }
}
