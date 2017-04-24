package me.donnie.github.ui.repo.contributor;

import javax.inject.Inject;

import me.donnie.github.ui.repo.RepoContract;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */

public class ContributorNavigator implements ContributorContract.Navigator {

    private final RepoContract.Navigator mNavigator;

    @Inject
    public ContributorNavigator(RepoContract.Navigator navigator) {
        mNavigator = navigator;
    }
}
