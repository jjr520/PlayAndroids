package jjr.com.playandroids.base.activity;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.base.view.BaseView;


/**
 * Created by jjr on 2019/2/27.
 */

public abstract class BaseActivity<V, P extends IBasePresenter<V>> extends SimperActivity implements BaseView {

    public P presenter;
    private LottieAnimationView progressBar;

    @Override
    public void viewCreated(View view) {
        super.viewCreated(view);
        View inflate = View.inflate(this, R.layout.progressbar_layout, (ViewGroup) view);
        progressBar = inflate.findViewById(R.id.progressBar);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attchView((V) this);
        }
    }

    //创建子类的P层对象
    protected abstract P createPresenter();

    @Override
    public void showProgressbar() {
        progressBar.setAnimation("loading_bus.json");
        progressBar.loop(true);
        progressBar.playAnimation();
    }

    @Override
    public void hideProgressbar() {
        progressBar.cancelAnimation();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.datechView();
            presenter = null;
        }
    }

}
