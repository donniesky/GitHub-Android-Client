package me.donnie.github.ui.repo.contributor;

import java.util.List;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.User;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */

public interface ContributorContract {

    interface Navigator extends BaseNavigator {

    }

    interface View extends BaseView {
        void loadContributorsSuccess(List<User> users , int page);
        void onError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadContributors(String owner, String repo, int page);
        void onContributorsResponse(List<User> users);
    }

}
