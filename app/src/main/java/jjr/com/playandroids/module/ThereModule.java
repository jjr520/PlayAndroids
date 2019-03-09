package jjr.com.playandroids.module;

import android.telecom.Call;

import java.util.HashMap;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.collect.CollectDataList;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.beans.wechat.WeChatTabBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.only.OnlyTwo;
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
            case OnlyThere.SEARCH:
                HttpManager.getInstance().getRetrofit(MyServer.HOST)
                        .create(MyServer.class).getSearchWx((String) map.get("id"),(String) map.get("page"),(String) map.get("k"))
                        .compose(RxUtils.<WeChatHistoryBean>rxScheduleThread())
                        .subscribe(new BaseObserver<WeChatHistoryBean>(thereCallBack) {
                            @Override
                            public void onNext(WeChatHistoryBean value) {
                                thereCallBack.setData(value,OnlyThere.SEARCH);
                            }
                        });
                break;
            case OnlyThere.COLLECTION:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class)
                        .getCollectData((Integer) map.get("id")).compose(RxUtils.<CollectDataList>rxScheduleThread())
                        .subscribe(new BaseObserver<CollectDataList>(thereCallBack) {
                            @Override
                            public void onNext(CollectDataList value) {
                                thereCallBack.setData(value,OnlyThere.COLLECTION);
                            }
                        });
                break;
            case OnlyThere.CANCLECOLLECTION:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class)
                        .getUncoolect((Integer) map.get("id")).compose(RxUtils.<CollectDataList>rxScheduleThread())
                        .subscribe(new BaseObserver<CollectDataList>(thereCallBack) {
                            @Override
                            public void onNext(CollectDataList value) {
                                thereCallBack.setData(value,OnlyThere.CANCLECOLLECTION);
                            }
                        });
                break;
        }
    }
}
