package me.donnie.github.ui.repo.readme;

import javax.inject.Inject;

import me.donnie.github.ui.repo.RepoContract;

/**
 * @author donnieSky
 * @created_at 2017/4/17.
 * @description
 */

public class ReadmeNavigator implements ReadmeContract.Navigator {

    private final RepoContract.Navigator mNavigator;

    @Inject
    public ReadmeNavigator(RepoContract.Navigator navigator) {
        mNavigator = navigator;
    }
}
