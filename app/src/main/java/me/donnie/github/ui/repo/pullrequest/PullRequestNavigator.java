package me.donnie.github.ui.repo.pullrequest;

import javax.inject.Inject;

import me.donnie.github.ui.repo.RepoContract;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */

public class PullRequestNavigator implements PullRequestContract.Navigator {

    private final RepoContract.Navigator mNavigator;

    @Inject
    public PullRequestNavigator(RepoContract.Navigator navigator) {
        mNavigator = navigator;
    }
}
