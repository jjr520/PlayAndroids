package jjr.com.playandroids.http;

import io.reactivex.Observable;
import jjr.com.playandroids.beans.WeChatTabBean;
import retrofit2.http.GET;

/**
 * Created by jjr on 2019/2/27.
 */

public interface MyServer {

    String HOST = "http://www.wanandroid.com/";

    //微信
    @GET("wxarticle/chapters/json")
    Observable<WeChatTabBean> getWeChatTab();

}
