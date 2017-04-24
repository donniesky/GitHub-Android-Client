package me.donnie.github.ui.main;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.Event;
import me.donnie.github.data.entity.Trending;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public interface MainContract {

    interface Navigator extends BaseNavigator {
        void logout();
        void navigateToLogin();
        void navigateToEvent();
        void navigateToTrending();
        void navigateToRepo(Event event);
        void navigateToRepo(Trending trending);
    }

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }

}
