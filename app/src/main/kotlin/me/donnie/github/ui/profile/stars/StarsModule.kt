package me.donnie.github.ui.profile.stars

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
class StarsModule {

    @Provides
    @FragmentScope
    fun provideStarsNavigator(navigator: StarsNavigator): StarsContract.Navigator {
        return navigator
    }

    @Provides
    @FragmentScope
    fun provideStarsPresenter(presenter: StarsPresenter): StarsContract.Presenter {
        return presenter
    }

}