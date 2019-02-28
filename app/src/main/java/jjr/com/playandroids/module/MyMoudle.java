package jjr.com.playandroids.module;

import java.util.List;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.WeChatTabBean;
import jjr.com.playandroids.entityclass.NaviListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyFour;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.utils.RxUtils;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MyMoudle {
    public interface CallBack<T> extends BaseModel{
        void setData(T t, String onlyOne);
    }
    public void getDataM(final CallBack callBack, final String onlyOne, Object object){
        switch (onlyOne){
            case OnlyFour.NAVI:
                HttpManager.getInstance().getServer(MyServer.HOST,MyServer.class).getNaviList().compose(RxUtils.<NaviListBean>rxScheduleThread()).subscribe(new BaseObserver<NaviListBean>(callBack) {
                    @Override
                    public void onNext(NaviListBean value) {
                        callBack.setData(value,onlyOne);
                    }
                });
                break;
        }
    }
}
