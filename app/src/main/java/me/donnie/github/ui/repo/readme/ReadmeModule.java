package me.donnie.github.ui.repo.readme;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/17.
 * @description
 */
@Module
public class ReadmeModule {

    @Provides
    @FragmentScope
    ReadmeContract.Navigator provideReadmeNavigator(ReadmeNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    ReadmeContract.Presenter provideReadmePresenter(ReadmePresenter presenter) {
        return presenter;
    }

}
