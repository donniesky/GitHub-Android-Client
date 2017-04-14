package me.donnie.github.ui.main;

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
public class MainModule {

    private final MainActivity mActivity;

    public MainModule(MainActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    MainContract.Navigator provideMainNavigator(MainNavigator navigator) {
        return navigator;
    }

    @Provides
    @ActivityScope
    MainContract.Presenter provideMainPresenter(MainPresenter presenter) {
        return presenter;
    }
}
