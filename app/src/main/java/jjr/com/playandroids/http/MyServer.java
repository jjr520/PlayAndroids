package jjr.com.playandroids.http;

import java.util.Map;

import io.reactivex.Observable;
import jjr.com.playandroids.beans.fivelistbean.HotSearch;
import jjr.com.playandroids.beans.fivelistbean.ProjectListBean;
import jjr.com.playandroids.beans.fivelistbean.SearchBean;
import jjr.com.playandroids.beans.fivelistbean.TreeListBean;
import jjr.com.playandroids.beans.fivelistbean.UseListBean;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.beans.one.Articlebean;
import jjr.com.playandroids.beans.one.Bannerbean;
import jjr.com.playandroids.beans.sixlistbean.Login;
import jjr.com.playandroids.beans.sixlistbean.Register;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.beans.wechat.WeChatTabBean;
import jjr.com.playandroids.beans.knowbean.KnowDetailsBean;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by jjr on 2019/2/27.
 */

public interface MyServer {

    String HOST = "http://www.wanandroid.com/";

    //
    @GET("navi/json")
    Observable<NaviListBean> getNaviList();

    //微信
    @GET("project/tree/json")
    Observable<TreeListBean> getTreeListBean();

    //微信公众号tab
    @GET("wxarticle/chapters/json")
    Observable<WeChatTabBean> getWeChatTab();

    //公众号列表
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<WeChatHistoryBean> getWeChatHistory(@Path("id") String id, @Path("page") String page);

    //微信搜索
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<WeChatHistoryBean> getSearchWx(@Path("id") String id, @Path("page") String page,@Query("k") String which);

    //http://www.wanandroid.com/project/list/1/json?cid=294
    @GET("project/list/{id}/json?cid=294")
    Observable<ProjectListBean> getProjectListBean(@Path("id") String cid);


    //2.1 体系数据
    //http://www.wanandroid.com/tree/json
    @GET("tree/json")
    Observable<KonwDataBean> getDatas();

    //2.2 知识体系下的文章
    //http://www.wanandroid.com/article/list/0/json?cid=60
    @GET("article/list/{page}/json?")
    Observable<KnowDetailsBean> getDetails(@Path("page") int page, @Query("cid") int cid);

    @POST("user/login")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<Login> getLoginData(@Body RequestBody requestBody);

    @POST("user/register")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<Register> getRegisterData(@Body RequestBody requestBody);



    //http://www.wanandroid.com/friend/json
    @GET("friend/json")
    Observable<UseListBean> getUseListBean();
    //banner数据
    @GET("banner/json")
    Observable<Bannerbean> getBanner();
    //
    @GET()
    Observable<Articlebean> getArticle(@Url String url);

    //http://www.wanandroid.com//hotkey/json
    @GET("hotkey/json")
    Observable<HotSearch> getHotSearch();



    //2.2 知识体系下的文章
    //http://www.wanandroid.com/article/list/0/json?cid=60

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<SearchBean> getSearchBean(@Path("page") String page,@Field("k") String key);

    //http://www.wanandroid.com/
}
