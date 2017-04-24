package me.donnie.github.ui.repo.issue;

import java.util.List;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.Issue;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public interface IssueContract {

    interface Navigator extends BaseNavigator {

    }

    interface View extends BaseView {
        void loadIssueSuccess(List<Issue> issues, int page);
        void onError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadIssues(String owner, String repo,
                        String state,
                        int page);

        void onIssuesResponse(List<Issue> issues);
    }

}
