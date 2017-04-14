package me.donnie.github.ui.repo;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.module.ApiModule;
import me.donnie.github.common.injection.scope.ActivityScope;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */
@Module(includes = ApiModule.class)
public class RepoModule {

    private final RepoActivity mActivity;

    public RepoModule(RepoActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    RepoActivity provideRepoActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    RepoContract.Navigator provideRepoNavigator(RepoNavigator navigator) {
        return navigator;
    }

    @Provides
    @ActivityScope
    RepoContract.Presenter provideRepoPresenter(RepoPresenter presenter) {
        return presenter;
    }
}
