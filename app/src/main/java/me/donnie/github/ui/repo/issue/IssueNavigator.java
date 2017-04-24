package me.donnie.github.ui.repo.issue;

import javax.inject.Inject;

import me.donnie.github.ui.repo.RepoContract;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class IssueNavigator implements IssueContract.Navigator {

    private final RepoContract.Navigator mNavigator;

    @Inject
    public IssueNavigator(RepoContract.Navigator navigator) {
        mNavigator = navigator;
    }
}
