package jjr.com.playandroids.base.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.presenter.BasePresenter;
import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.base.view.BaseView;


/**
 * Created by jjr on 2019/2/27.
 */

public abstract class BaseFragment<V, P extends BasePresenter<V>>
        extends SimperFragment implements BaseView {

    public P presenter;
    private LottieAnimationView progressBar;
    private LottieAnimationView mLottieAnimationViews;

    @Override
    public void viewCreate(View view) {
        super.viewCreate(view);
        View view1 = View.inflate(mActivity, R.layout.progressbar_layout, (ViewGroup) view);

        progressBar = view1.findViewById(R.id.progressBar);
        if (presenter == null) {
            presenter = createPresenter();
            if (presenter != null) {
                //将对应的P层和V层进行绑定
                presenter.attchView((V) this);
            }
        }
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.datechView();
            presenter = null;
        }
    }

    @Override
    public void load() {
        super.load();
        if (presenter == null) {
            presenter = createPresenter();
            if (presenter != null) {
                presenter.attchView((V) this);
            }
        }
    }


    @Override
    public void showProgressbar() {
        //mLottieAnimationViews = new LottieAnimationView(context);
        progressBar.setAnimation("loading_bus.json");
        progressBar.loop(true);
        progressBar.playAnimation();
    }

    @Override
    public void hideProgressbar() {
        progressBar.cancelAnimation();
        progressBar.setVisibility(View.GONE);
    }
}
