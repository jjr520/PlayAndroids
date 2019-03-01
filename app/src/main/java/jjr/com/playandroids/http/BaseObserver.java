package jjr.com.playandroids.http;


import android.util.Log;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jjr.com.playandroids.base.model.BaseModel;
import retrofit2.HttpException;


public abstract class BaseObserver<T> implements Observer<T> {

    //回调结果处理
    private BaseModel httpFinishCallback;

    public BaseObserver(BaseModel httpFinishCallback) {
        this.httpFinishCallback = httpFinishCallback;
    }

    //管理内存网络请求
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        if (httpFinishCallback != null) {
            if (e instanceof HttpException) {
                httpFinishCallback.setshowError("网络请求错误");
            } else if (e instanceof JSONException) {
                httpFinishCallback.setshowError("解析错误");
            } else if (e instanceof TimeoutException) {
                httpFinishCallback.setshowError("超时异常");
            } else if(e instanceof SocketTimeoutException){
                Log.d("错误是--->", ""+e.getMessage());
                httpFinishCallback.setshowError("套接字超时异常");
            }else {
                httpFinishCallback.setshowError("其他请求错误");
                Log.d("错误是--->", ""+e.getMessage());
            }
           // Logger.getLogger(e.getMessage());
            Log.e("走了哑巴",e.getMessage().toString());
            httpFinishCallback.sethideProgressbar();
        }
    }

    @Override
    public void onComplete() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        if (httpFinishCallback != null) {
            httpFinishCallback.sethideProgressbar();
        }

    }

}
