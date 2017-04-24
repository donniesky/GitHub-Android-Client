package me.donnie.github.ui.repo.commit;

import java.util.List;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.Commit;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public interface CommitContract {

    interface Navigator extends BaseNavigator {

    }

    interface View extends BaseView {
        void loadCommitsSuccess(List<Commit> commits, int page);
        void onError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadCommits(String owner, String repo,
                         String branch, int page);

        void onCommitsResponse(List<Commit> commits);
    }

}
