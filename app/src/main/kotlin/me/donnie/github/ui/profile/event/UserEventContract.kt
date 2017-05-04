package me.donnie.github.ui.profile.event

import me.donnie.github.common.base.BaseNavigator
import me.donnie.github.common.base.BasePresenter
import me.donnie.github.common.base.BaseView
import me.donnie.github.data.entity.Event

/**
 * @author donnieSky
 * @created_at 2017/5/2.
 * @description
 * @version
 */
interface UserEventContract {

    interface Navigator : BaseNavigator

    interface View: BaseView {
        fun loadUserEventSuccess(events: List<Event>, page: Int)
        fun onError()
    }

    interface Presenter: BasePresenter<View> {
        fun loadUserEvents(username: String, page: Int)
        fun onUserEventsResponse(events: List<Event>)
    }

}