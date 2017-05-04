package me.donnie.github.ui.profile.following

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
class FollowingModule {

    @Provides
    @FragmentScope
    fun provideFollowingNavigator(navigator: FollowingNavigator): FollowingContract.Navigator {
        return navigator
    }

    @Provides
    @FragmentScope
    fun provideFollowingPresenter(presenter: FollowingPresenter): FollowingContract.Presenter {
        return presenter
    }

}