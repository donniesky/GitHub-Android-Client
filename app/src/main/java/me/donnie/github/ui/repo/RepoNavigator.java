package me.donnie.github.ui.repo;

import javax.inject.Inject;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class RepoNavigator implements RepoContract.Navigator {

    private final RepoActivity mActivity;

    @Inject
    public RepoNavigator(RepoActivity activity) {
        mActivity = activity;
    }
}
