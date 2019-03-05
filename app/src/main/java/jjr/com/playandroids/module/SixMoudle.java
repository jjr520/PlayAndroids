package jjr.com.playandroids.module;

import java.util.List;

import jjr.com.playandroids.base.model.BaseModel;

import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.beans.sixlistbean.Login;
import jjr.com.playandroids.beans.sixlistbean.Register;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.only.OnlySix;
import jjr.com.playandroids.utils.RxUtils;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

import static jjr.com.playandroids.only.OnlyOne.NAVI;
import static jjr.com.playandroids.only.OnlyOne.wechattab;

/**
 * Created by Administrator on 2019/2/27.
 */

public class SixMoudle {
    public interface CallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }

    public void getDataM(final CallBack callBack, final String onlyOne, Object object) {
        switch (onlyOne){
            case OnlySix.LOGIN:
                RequestBody requestBody = (RequestBody) object;
                MyServer myServer = HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class);
                myServer.getLoginData(requestBody).compose(RxUtils.<Login>rxScheduleThread())
                        .subscribe(new BaseObserver<Login>(callBack) {
                            @Override
                            public void onNext(Login value) {
                                callBack.setData(value,onlyOne);
                            }
                        });
                break;

            case OnlySix.REGISTER:

                RequestBody requestBody1 = (RequestBody) object;
                MyServer server = HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class);
                server.getRegisterData(requestBody1).compose(RxUtils.<Register>rxScheduleThread())
                        .subscribe(new BaseObserver<Register>(callBack) {
                            @Override
                            public void onNext(Register value) {
                                callBack.setData(value,onlyOne);
                            }
                        });
        }
    }
}
