package me.donnie.github.ui.profile.repos

import dagger.Module
import dagger.Provides
import me.donnie.github.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
@Module
class ReposModule {

    @Provides
    @FragmentScope
    fun provideReposNavigator(navigator: ReposNavigator) : ReposContract.Navigator {
        return navigator
    }

    @Provides
    @FragmentScope
    fun provideReposPresenter(presenter: ReposPresenter) : ReposContract.Presenter {
        return presenter
    }

}