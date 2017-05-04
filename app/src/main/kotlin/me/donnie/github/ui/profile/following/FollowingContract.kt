package me.donnie.github.ui.profile.following

import me.donnie.github.common.base.BaseNavigator
import me.donnie.github.common.base.BasePresenter
import me.donnie.github.common.base.BaseView
import me.donnie.github.data.entity.User

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
interface FollowingContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadFollowingSuccess(users: List<User>, page: Int)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadFollowing(username: String, page: Int)
        fun onFollowingResponse(users: List<User>)
    }

}