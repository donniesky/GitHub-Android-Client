package me.donnie.github.ui.profile.event

import dagger.Module
import dagger.Provides
import me.donnie.github.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/2.
 * @description
 * @version
 */
@Module
class UserEventModule {

    @FragmentScope
    @Provides
    fun provideUserEventNavigator(navigator: UserEventNavigator) : UserEventContract.Navigator {
        return navigator
    }

    @FragmentScope
    @Provides
    fun provideUserEventPresenter(presenter: UserEventPresenter) : UserEventContract.Presenter {
        return presenter
    }

}