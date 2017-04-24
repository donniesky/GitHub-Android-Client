package me.donnie.github.ui.repo.commit;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */
@Module
public class CommitModule {

    @Provides
    @FragmentScope
    CommitContract.Navigator provideCommitNavigator(CommitNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    CommitContract.Presenter provideCommitPresenter(CommitPresenter presenter) {
        return presenter;
    }

}
