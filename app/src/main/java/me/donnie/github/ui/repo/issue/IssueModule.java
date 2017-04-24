package me.donnie.github.ui.repo.issue;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */
@Module
public class IssueModule {

    @Provides
    @FragmentScope
    IssueContract.Navigator provideIssueNavigator(IssueNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    IssueContract.Presenter provideIssuePresenter(IssuePresenter presenter) {
        return presenter;
    }

}
