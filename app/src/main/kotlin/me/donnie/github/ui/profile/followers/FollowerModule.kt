package me.donnie.github.ui.profile.followers

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
class FollowerModule {

    @FragmentScope
    @Provides
    fun provideFollowerNavigator(navigator: FollowerNavigator): FollowerContract.Navigator {
        return navigator
    }

    @FragmentScope
    @Provides
    fun provideFollowerPresenter(presenter: FollowerPresenter): FollowerContract.Presenter {
        return presenter
    }

}