package me.donnie.github.ui.splash;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public interface SplashContract {

    interface Navigator extends BaseNavigator {

        void navigateToLogin();

        void navigateToMain();

    }

    interface View extends BaseView {
        void notLogin();
    }

    interface Presenter extends BasePresenter<View> {
        boolean isLogin();
    }

}
