package me.donnie.github.ui.profile.overview

import dagger.Subcomponent
import me.donnie.github.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/4/28.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(OverViewModule::class))
interface OverViewComponent {

    fun inject(fragment: OverViewFragment)

}