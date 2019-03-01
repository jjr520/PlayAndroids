package jjr.com.playandroids.module;

import android.util.Log;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.knowbean.KnowDetailsBean;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.only.OnlyTwo;
import jjr.com.playandroids.utils.RxUtils;

public class TwoModule {
    public interface TwoCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }

    public void getDataTwo(final TwoCallBack oneCallBack, final String onlyOne, int page, int cid) {
        oneCallBack.setshowProgressbar();
        switch (onlyOne) {
            case OnlyTwo.KonwData:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class)
                        .getDatas().compose(RxUtils.<KonwDataBean>rxScheduleThread())
                        .subscribe(new BaseObserver<KonwDataBean>(oneCallBack) {
                            @Override
                            public void onNext(KonwDataBean value) {
                                oneCallBack.setData(value, OnlyTwo.KonwData);
                            }
                        });
                break;
            case OnlyTwo.KnowDetails:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class)
                        .getDetails(page, cid).compose(RxUtils.<KnowDetailsBean>rxScheduleThread())
                        .subscribe(new BaseObserver<KnowDetailsBean>(oneCallBack) {
                            @Override
                            public void onNext(KnowDetailsBean value) {
                                oneCallBack.setData(value, OnlyTwo.KnowDetails);
                            }
                        });
                break;
        }
        oneCallBack.sethideProgressbar();
    }
}
