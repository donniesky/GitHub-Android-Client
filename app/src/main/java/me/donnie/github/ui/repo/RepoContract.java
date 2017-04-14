package me.donnie.github.ui.repo;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.Repo;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public interface RepoContract {

    interface Navigator extends BaseNavigator {

    }

    interface View extends BaseView {
        void getRepoSuccess(Repo repo);
    }

    interface Presenter extends BasePresenter<View> {
        void getRepo(String login, String repoId);
        void getRepoByUrl(String url);
    }

}
