package me.donnie.github.ui.main;

import android.content.Intent;

import javax.inject.Inject;

import me.donnie.github.R;
import me.donnie.github.common.utils.Util;
import me.donnie.github.data.entity.Event;
import me.donnie.github.data.entity.Trending;
import me.donnie.github.ui.event.EventFragment;
import me.donnie.github.ui.repo.RepoActivity;
import me.donnie.github.ui.trending.TrendingFragment;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class MainNavigator implements MainContract.Navigator {

    private final MainActivity mActivity;

    @Inject
    public MainNavigator(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public void navigateToEvent() {
        EventFragment eventFragment = EventFragment.newInstance();
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, eventFragment, EventFragment.TAG)
                .commitNow();
    }

    @Override
    public void navigateToTrending() {
        TrendingFragment trendingFragment = TrendingFragment.newInstance();
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, trendingFragment, TrendingFragment.TAG)
                .commitNow();
    }

    @Override
    public void navigateToRepo(Event event) {
        Intent callingIntent = RepoActivity.getCallingIntent(mActivity);
        callingIntent.putExtra(RepoActivity.PARAM_LOGIN, Util.parserUrl(event.getRepo().getUrl()).get(1));
        callingIntent.putExtra(RepoActivity.PARAM_REPO_ID, Util.parserUrl(event.getRepo().getUrl()).get(2));
        callingIntent.putExtra(RepoActivity.PARAM_REPO_URL, event.getRepo().getUrl());
        mActivity.startActivity(callingIntent);
    }

    @Override
    public void navigateToRepo(Trending trending) {
        Intent callingIntent = RepoActivity.getCallingIntent(mActivity);
        callingIntent.putExtra(RepoActivity.PARAM_LOGIN, trending.getOwner());
        callingIntent.putExtra(RepoActivity.PARAM_REPO_ID, trending.getName());
        callingIntent.putExtra(RepoActivity.PARAM_REPO_URL, trending.getUrl());
        mActivity.startActivity(callingIntent);
    }
}
