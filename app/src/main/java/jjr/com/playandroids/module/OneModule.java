package jjr.com.playandroids.module;

import android.util.Log;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.one.Articlebean;
import jjr.com.playandroids.beans.one.Bannerbean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.utils.RxUtils;

public class OneModule {
    public interface OneCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }
    public void getDataOne(final OneCallBack oneCallBack, final String onlyOne, Object object){
        switch (object.toString()){
            case "1":
                HttpManager.getInstance()
                        .getServer(MyServer.HOST,MyServer.class)
                        .getBanner()
                        .compose(RxUtils.<Bannerbean>rxScheduleThread())
                        .subscribe(new BaseObserver<Bannerbean>(oneCallBack) {
                            @Override
                            public void onNext(Bannerbean value) {
                                oneCallBack.setData(value,onlyOne);
                                Log.e("杨旭",value.getData().get(0).getTitle());

                            }
                        });
                break;
            case "2":
                HttpManager.getInstance()
                        .getServer(MyServer.HOST,MyServer.class)
                        .getArticle(onlyOne)
                        .compose(RxUtils.<Articlebean>rxScheduleThread())
                        .subscribe(new BaseObserver<Articlebean>(oneCallBack) {
                            @Override
                            public void onNext(Articlebean value) {
                                oneCallBack.setData(value,onlyOne);
                                Log.e("杨旭2",value.getData().getDatas().get(0).getTitle());
                            }
                        });
                break;
        }
    }
}
