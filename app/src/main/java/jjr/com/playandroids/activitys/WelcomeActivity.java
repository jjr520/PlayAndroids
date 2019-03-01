package jjr.com.playandroids.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.SimperActivity;

public class WelcomeActivity extends SimperActivity {


    private LottieAnimationView mOneAnimation;
    private LottieAnimationView mTwoAnimation;
    private LottieAnimationView mThreeAnimation;
    private LottieAnimationView mFourAnimation;
    private LottieAnimationView mFiveAnimation;
    private LottieAnimationView mSixAnimation;
    private LottieAnimationView mSevenAnimation;
    private LottieAnimationView mEightAnimation;
    private LottieAnimationView mNineAnimation;
    private LottieAnimationView mTenAnimation;

    @Override
    protected void initData() {
        initView();


        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mOneAnimation.setAnimation("W.json");
                mOneAnimation.loop(true);
                mOneAnimation.playAnimation();
                mTwoAnimation.setAnimation("A.json");
                mTwoAnimation.loop(true);
                mTwoAnimation.playAnimation();
                mThreeAnimation.setAnimation("N.json");
                mThreeAnimation.loop(true);
                mThreeAnimation.playAnimation();
                mFourAnimation.setAnimation("A.json");
                mFourAnimation.loop(true);
                mFourAnimation.playAnimation();
                mFiveAnimation.setAnimation("N.json");
                mFiveAnimation.loop(true);
                mFiveAnimation.playAnimation();
                mSixAnimation.setAnimation("D.json");
                mSixAnimation.loop(true);
                mSixAnimation.playAnimation();
                mSevenAnimation.setAnimation("R.json");
                mSevenAnimation.loop(true);
                mSevenAnimation.playAnimation();
                mEightAnimation.setAnimation("O.json");
                mEightAnimation.loop(true);
                mEightAnimation.playAnimation();
                mNineAnimation.setAnimation("O.json");
                mNineAnimation.loop(true);
                mNineAnimation.playAnimation();
                mTenAnimation.setAnimation("I.json");
                mTenAnimation.loop(true);
                mTenAnimation.playAnimation();
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                cancelAnimation();
                finish();

            }
        }.start();
    }



    private void cancelAnimation() {
        mOneAnimation.cancelAnimation();
        mTwoAnimation.cancelAnimation();
        mThreeAnimation.cancelAnimation();
        mFourAnimation.cancelAnimation();
        mFiveAnimation.cancelAnimation();
        mSixAnimation.cancelAnimation();
        mSevenAnimation.cancelAnimation();
        mEightAnimation.cancelAnimation();
        mNineAnimation.cancelAnimation();
        mTenAnimation.cancelAnimation();
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_welcome;
    }


    public void initView() {
        mOneAnimation = (LottieAnimationView) findViewById(R.id.one_animation);
        mTwoAnimation = (LottieAnimationView) findViewById(R.id.two_animation);
        mThreeAnimation = (LottieAnimationView) findViewById(R.id.three_animation);
        mFourAnimation = (LottieAnimationView) findViewById(R.id.four_animation);
        mFiveAnimation = (LottieAnimationView) findViewById(R.id.five_animation);
        mSixAnimation = (LottieAnimationView) findViewById(R.id.six_animation);
        mSevenAnimation = (LottieAnimationView) findViewById(R.id.seven_animation);
        mEightAnimation = (LottieAnimationView) findViewById(R.id.eight_animation);
        mNineAnimation = (LottieAnimationView) findViewById(R.id.nine_animation);
        mTenAnimation = (LottieAnimationView) findViewById(R.id.ten_animation);
    }
}
