package jjr.com.playandroids.base.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.base.view.BaseView;


/**
 * Created by jjr on 2019/2/27.
 */

public abstract class BaseFragment<V, P extends IBasePresenter<V>>
        extends SimperFragment implements BaseView {

    public P presenter;
    private ProgressBar progressBar;

    @Override
    public void viewCreate(View view) {
        super.viewCreate(view);
        View view1 = View.inflate(mActivity, R.layout.progressbar_layout, (ViewGroup) view);
        progressBar = view1.findViewById(R.id.progressBar);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attchView((V) this);
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
            presenter.attchView((V) this);
        }
    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }
}
