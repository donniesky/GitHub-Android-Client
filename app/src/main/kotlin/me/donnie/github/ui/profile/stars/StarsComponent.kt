package me.donnie.github.ui.profile.stars

import dagger.Subcomponent
import me.donnie.github.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(StarsModule::class))
interface StarsComponent {

    fun inject(fragment: StarsFragment)

}