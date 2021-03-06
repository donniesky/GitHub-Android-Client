package me.donnie.github.ui.profile.repos

import dagger.Subcomponent
import me.donnie.github.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(ReposModule::class))
interface ReposComponent {

    fun inject(fragment: ReposFragment)

}