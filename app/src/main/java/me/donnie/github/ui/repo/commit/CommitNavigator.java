package me.donnie.github.ui.repo.commit;

import javax.inject.Inject;

import me.donnie.github.ui.repo.RepoContract;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommitNavigator implements CommitContract.Navigator {

    private final RepoContract.Navigator mNavigator;

    @Inject
    public CommitNavigator(RepoContract.Navigator navigator) {
        mNavigator = navigator;
    }
}
