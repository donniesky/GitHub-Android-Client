package me.donnie.github.ui.repo.contributor;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */
@Module
public class ContributorModule {

    @FragmentScope
    @Provides
    ContributorContract.Navigator provideContributorNavigator(ContributorNavigator navigator) {
        return navigator;
    }

    @FragmentScope
    @Provides
    ContributorContract.Presenter provideContributorPresenter(ContributorPresenter presenter) {
        return presenter;
    }

}
