package me.donnie.github.ui.profile

import me.donnie.github.common.base.BaseNavigator
import me.donnie.github.common.base.BasePresenter
import me.donnie.github.common.base.BaseView
import me.donnie.github.data.entity.User

/**
 * @author donnieSky
 * @created_at 2017/4/26.
 * @description
 * @version
 */
interface ProfileContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadUserSuccess(user: User)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadUser()
        fun onUserResponse(user: User)
    }

}