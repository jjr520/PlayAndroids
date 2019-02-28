package jjr.com.playandroids.module;

import java.util.List;

import jjr.com.playandroids.base.model.BaseModel;

import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.utils.RxUtils;
import retrofit2.Retrofit;

import static jjr.com.playandroids.only.OnlyOne.NAVI;
import static jjr.com.playandroids.only.OnlyOne.wechattab;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MyMoudle {
    public interface CallBack<T> extends BaseModel{
        void setData(T t, String onlyOne);
    }
    public void getDataM(final CallBack callBack, final String onlyOne, Object object){
        switch (onlyOne){
            //微信公众号外部tab
            case wechattab:
               /* List<WeChatTabBean.DataBean> weChatTabData = HttpManager.getInstance().getRetrofit(MyServer.HOST)
                        .create(WeChatTabBean.class)
                        .getData();
                callBack.setData(weChatTabData,onlyOne);*/
                break;
            case NAVI:
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
