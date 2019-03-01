package jjr.com.playandroids.module;

import android.telecom.Call;

import java.util.HashMap;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.beans.wechat.WeChatTabBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.utils.RxUtils;

public class ThereModule {
    public interface ThereCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }

    public void getDataThere(final ThereCallBack thereCallBack, final String onlyOne, HashMap<String,Object> map){
        switch (onlyOne){
            case OnlyThere.WECHATTAB:
                HttpManager.getInstance().getRetrofit(MyServer.HOST)
                        .create(MyServer.class).getWeChatTab()
                        .compose(RxUtils.<WeChatTabBean>rxScheduleThread())
                        .subscribe(new BaseObserver<WeChatTabBean>(thereCallBack) {
                            @Override
                            public void onNext(WeChatTabBean value) {
                                thereCallBack.setData(value,OnlyThere.WECHATTAB);
                            }
                        });
                break;
            case OnlyThere.WCHISTORY:
                HttpManager.getInstance().getRetrofit(MyServer.HOST)
                        .create(MyServer.class).getWeChatHistory((String) map.get("id"),(String) map.get("page"))
                        .compose(RxUtils.<WeChatHistoryBean>rxScheduleThread())
                        .subscribe(new BaseObserver<WeChatHistoryBean>(thereCallBack) {
                            @Override
                            public void onNext(WeChatHistoryBean value) {
                                thereCallBack.setData(value,OnlyThere.WCHISTORY);
                            }
                        });
                break;
        }
    }
}
