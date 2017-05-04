package me.donnie.github.ui.profile.event

import dagger.Subcomponent
import me.donnie.github.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/2.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(UserEventModule::class))
interface UserEventComponent {

    fun inject(fragment: UserEventFragment)

}