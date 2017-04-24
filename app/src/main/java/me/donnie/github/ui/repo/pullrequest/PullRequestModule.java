package me.donnie.github.ui.repo.pullrequest;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */
@Module
public class PullRequestModule {

    @Provides
    @FragmentScope
    PullRequestContract.Navigator providePullRequestNavigator(PullRequestNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    PullRequestContract.Presenter providePullRequestPresenter(PullRequestPresenter presenter) {
        return presenter;
    }

}
