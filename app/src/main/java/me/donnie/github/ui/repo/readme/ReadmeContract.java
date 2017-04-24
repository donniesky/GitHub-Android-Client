package me.donnie.github.ui.repo.readme;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;

/**
 * @author donnieSky
 * @created_at 2017/4/17.
 * @description
 */

public interface ReadmeContract {

    interface Navigator extends BaseNavigator {

    }

    interface View extends BaseView {
        void loadReadmeSuccess(String content);
        void onNetWorkError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadReadme(String owner, String repo);
        void onReadmeReponse(String content);
    }

}
