package jjr.com.playandroids.http;

import io.reactivex.Observable;
import jjr.com.playandroids.beans.fivelistbean.ProjectListBean;
import jjr.com.playandroids.beans.fivelistbean.TreeListBean;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import retrofit2.http.GET;
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


    //http://www.wanandroid.com/project/list/1/json?cid=294
    @GET("project/list/{id}/json?cid=294")
    Observable<ProjectListBean> getProjectListBean(@Path("id") String cid);




}
