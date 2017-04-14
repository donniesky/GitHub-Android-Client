package me.donnie.github.ui.splash;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.module.ApiModule;
import me.donnie.github.common.injection.scope.ActivityScope;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */
@Module(includes = ApiModule.class)
public class SplashModule {

    private final SplashActivity mActivity;

    public SplashModule(SplashActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    SplashActivity provideSplashActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    SplashContract.Navigator provideSplashNavigator(SplashNavigator navigator) {
        return navigator;
    }

    @Provides
    @ActivityScope
    SplashContract.Presenter provideSplashPresenter(SplashPresenter presenter) {
        return presenter;
    }
}
