package me.donnie.github.ui.splash;

import android.os.Bundle;

import javax.inject.Inject;

import me.donnie.github.R;
import me.donnie.github.common.base.BaseActivity;
import me.donnie.github.common.injection.component.AppComponent;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter mPresenter;

    @Inject
    SplashContract.Navigator mNavigator;

    SplashComponent mSplashComponent;

    @Override
    protected void setupComponent(AppComponent component) {
        mSplashComponent = DaggerSplashComponent.builder()
                .appComponent(component)
                .splashModule(new SplashModule(this))
                .build();
        mSplashComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        if (mPresenter.isLogin()) {
            mNavigator.navigateToMain();
        } else {
            mNavigator.navigateToLogin();
        }
    }

    @Override
    public void notLogin() {
        toast.toast("还未登录!");
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
