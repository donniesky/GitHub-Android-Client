package me.donnie.github.ui.profile.overview

import dagger.Module
import dagger.Provides
import me.donnie.github.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/4/28.
 * @description
 * @version
 */
@Module
class OverViewModule {

    @Provides
    @FragmentScope
    fun provideOverViewNavigator(navigator: OverViewNavigator): OverViewContract.Navigator {
        return navigator
    }

    @Provides
    @FragmentScope
    fun provideOverViewPresenter(presenter: OverViewPresenter): OverViewContract.Presenter {
        return presenter
    }

}