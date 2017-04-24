package me.donnie.github.ui.repo.pullrequest;

import java.util.List;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.PullRequest;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */

public interface PullRequestContract {

    interface Navigator extends BaseNavigator {

    }

    interface View extends BaseView {
        void loadPullRequestsSuccess(List<PullRequest> pullRequests, int page);
        void onError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadPullRequests(String owner, String repo,
                              String state, int page);

        void onPullRequestResponse(List<PullRequest> pullRequests);
    }

}
