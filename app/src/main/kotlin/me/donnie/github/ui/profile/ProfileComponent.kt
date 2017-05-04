package me.donnie.github.ui.profile

import dagger.Component
import me.donnie.github.common.injection.component.AppComponent
import me.donnie.github.common.injection.scope.ActivityScope
import me.donnie.github.ui.profile.event.UserEventComponent
import me.donnie.github.ui.profile.event.UserEventModule
import me.donnie.github.ui.profile.followers.FollowerComponent
import me.donnie.github.ui.profile.followers.FollowerModule
import me.donnie.github.ui.profile.following.FollowingComponent
import me.donnie.github.ui.profile.following.FollowingModule
import me.donnie.github.ui.profile.overview.OverViewComponent
import me.donnie.github.ui.profile.overview.OverViewModule
import me.donnie.github.ui.profile.repos.ReposComponent
import me.donnie.github.ui.profile.repos.ReposModule
import me.donnie.github.ui.profile.stars.StarsComponent
import me.donnie.github.ui.profile.stars.StarsModule

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

    fun plus(userEventModule: UserEventModule): UserEventComponent

    fun plus(module: ReposModule): ReposComponent

    fun plus(module: StarsModule): StarsComponent

    fun plus(module: FollowerModule): FollowerComponent

    fun plus(module: FollowingModule): FollowingComponent
}