package me.donnie.github.ui.profile

import dagger.Module
import dagger.Provides
import me.donnie.github.common.injection.module.ApiModule
import me.donnie.github.common.injection.scope.ActivityScope

/**
 * @author donnieSky
 * @created_at 2017/4/26.
 * @description
 * @version
 */
@Module(includes = arrayOf(ApiModule::class))
class ProfileModule(var activity: ProfileActivity) {

    @Provides
    @ActivityScope
    fun provideProfileActivity(): ProfileActivity {
        return this.activity;
    }

    @Provides
    @ActivityScope
    fun provideProfileNavigator(navigator: ProfileNavigator): ProfileContract.Navigator {
        return navigator
    }

    @Provides
    @ActivityScope
    fun provideProfilePresenter(presenter: ProfilePresenter): ProfileContract.Presenter {
        return presenter
    }

}