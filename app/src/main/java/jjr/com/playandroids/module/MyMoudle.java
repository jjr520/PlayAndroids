package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.entityclass.NaviListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.utils.RxUtils;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MyMoudle {
    public interface CallBack<T> extends BaseModel{
        void setData(T t, String onlyOne);
    }
    public void getDataM(CallBack callBack,String onlyOne,Object object){

    }
    public void getNaviList(final CallBack callBack, final String onlyOne, Object object){
        HttpManager.getInstance().getServer(MyServer.HOST,MyServer.class).getNaviList().compose(RxUtils.<NaviListBean>rxScheduleThread()).subscribe(new BaseObserver<NaviListBean>(callBack) {
            @Override
            public void onNext(NaviListBean value) {
                callBack.setData(value,OnlyOne.NAVI);
            }
        });

    }
}
