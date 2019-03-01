package jjr.com.playandroids.http;

import io.reactivex.Observable;
import jjr.com.playandroids.beans.fivelistbean.ProjectListBean;
import jjr.com.playandroids.beans.fivelistbean.TreeListBean;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.beans.wechat.WeChatTabBean;
import jjr.com.playandroids.beans.knowbean.KnowDetailsBean;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    @GET("wxarticle/list/{id}/{page}/json?k=Java")
    Observable<WeChatHistoryBean> getWeChatHistory(@Path("id") String id,@Path("page") String page);
    //http://www.wanandroid.com/project/list/1/json?cid=294
    @GET("project/list/{id}/json?cid=294")
    Observable<ProjectListBean> getProjectListBean(@Path("id") String cid);




    //2.1 体系数据
    //http://www.wanandroid.com/tree/json
    @GET("tree/json")
    Observable<KonwDataBean> getDatas();

    //2.2 知识体系下的文章
    //http://www.wanandroid.com/article/list/0/json?cid=60
    @GET("article/list/0/json?cid=60")
    Observable<KnowDetailsBean> getDetails();

}
