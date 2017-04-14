package me.donnie.github.ui.login;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.module.ApiModule;
import me.donnie.github.common.injection.scope.ActivityScope;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
@Module(includes = ApiModule.class)
public class LoginModule {

    private final LoginActivity mActivity;

    public LoginModule(LoginActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    LoginContract.Navigator provideLoginNavigator(LoginNavigator navigator) {
        return navigator;
    }

    @Provides
    @ActivityScope
    LoginContract.Presenter provideLoginPresenter(LoginPresenter presenter) {
        return presenter;
    }

}
