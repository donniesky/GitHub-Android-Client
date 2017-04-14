package me.donnie.github.ui.trending;


import javax.inject.Inject;

import me.donnie.github.data.entity.Trending;
import me.donnie.github.ui.main.MainContract;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */

public class TrendingNavigator implements TrendingContract.Navigator {

    private final MainContract.Navigator mNavigator;

    @Inject
    public TrendingNavigator(MainContract.Navigator navigator) {
        mNavigator = navigator;
    }


    @Override
    public void navigateToRepo(Trending trending) {
        mNavigator.navigateToRepo(trending);
    }
}
