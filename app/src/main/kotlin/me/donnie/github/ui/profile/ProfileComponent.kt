package me.donnie.github.ui.profile

import dagger.Component
import me.donnie.github.common.injection.component.AppComponent
import me.donnie.github.common.injection.scope.ActivityScope
import me.donnie.github.ui.profile.overview.OverViewComponent
import me.donnie.github.ui.profile.overview.OverViewModule

/**
 * @author donnieSky
 * @created_at 2017/4/26.
 * @description
 * @version
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ProfileModule::class))
interface ProfileComponent {

    fun inject(activity: ProfileActivity)

    fun plus(overViewModule: OverViewModule): OverViewComponent

}