package jjr.com.playandroids.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowWebActivity;
import jjr.com.playandroids.adapter.project.UseAdapter;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.fivelistbean.UseListBean;
import jjr.com.playandroids.lt.MyUseView;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.utils.CircularAnimUtil;
import jjr.com.playandroids.view.FiveView;

public class Useful_sitessActivity extends BaseActivity<FiveView, FivePresenter<FiveView>> implements FiveView {
    @BindView(R.id.useView)
    MyUseView myUseView;
    @BindView(R.id.useback)
    ImageView useback;
    private UseAdapter mUseAdapters;
    private TextView mTextViews;
    private LinearLayout mLinearLayouts;

    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    protected void initData() {
        presenter.getDataFiveP(OnlyFive.USE, "");


    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_useful_sitess;
    }

    @Override
    public void showDataFive(Object o, String onlyOne) {
        UseListBean useListBean = (UseListBean) o;
        final List<UseListBean.DataBean> data = useListBean.getData();
        MyUseView.LayoutParams myView = new MyUseView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < data.size(); i++) {
            mLinearLayouts = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.use, null);
            mTextViews = mLinearLayouts.findViewById(R.id.commonItemTitle);
            mTextViews.setText(data.get(i).getName());
            Random myRandom = new Random();
            int ranColor = 0x55000000 | myRandom.nextInt(0x55ffffff);
            mTextViews.setBackgroundColor(ranColor);
            mTextViews.setTextColor(0xffffffff);
            if (myUseView != null) {
                myUseView.addView(mLinearLayouts, i, myView);
                final int finalI = i;
                mTextViews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        // String s = mTextViews.getText().toString();
                        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0);
                        alphaAnimation.setDuration(1000);
                        v.startAnimation(alphaAnimation);
                        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {

                            private Animation mAnimations;

                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation
                                        (v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                                Intent intent = new Intent(Useful_sitessActivity.this, KnowWebActivity.class);
                                intent.putExtra("allWeb", data.get(finalI).getLink());
                                intent.putExtra("allTitle", data.get(finalI).getLink());
                                intent.putExtra("biaoji", 1);
                                ActivityCompat.startActivity(Useful_sitessActivity.this, intent,
                                        compat.toBundle());

                                mAnimations = animation;

                                //mAnimations.setFillAfter(true);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                    }
                });
            }

        }

    }


    @Override
    public void showError(String error) {
        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();

    }


    @OnClick(R.id.useback)
    public void onViewClicked() {
        finish();
    }





   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_sitess);
    }*/
}
