package jjr.com.playandroids.module;

import java.util.List;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.WeChatTabBean;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.utils.RxUtils;
import retrofit2.Retrofit;

import static jjr.com.playandroids.only.OnlyOne.KonwData;
import static jjr.com.playandroids.only.OnlyOne.wechattab;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MyMoudle {
    public interface CallBack<T> extends BaseModel {
        void setData(T t, OnlyOne onlyOne);
    }

    public void getDataM(final CallBack callBack, OnlyOne onlyOne, Object object) {
        callBack.setshowProgressbar();
        switch (onlyOne.toString()) {
            //微信公众号外部tab
            case wechattab:
                List<WeChatTabBean.DataBean> weChatTabData = HttpManager.getInstance().getRetrofit(MyServer.HOST)
                        .create(WeChatTabBean.class)
                        .getData();
                callBack.setData(weChatTabData, onlyOne);
                break;
            case KonwData:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class)
                        .getDatas().compose(RxUtils.<KonwDataBean>rxScheduleThread())
                        .subscribe(new BaseObserver<KonwDataBean>(callBack) {
                            @Override
                            public void onNext(KonwDataBean value) {
                                //callBack.setData(value, KonwData);
                            }
                        });
                break;
        }
        callBack.sethideProgressbar();
    }
}
