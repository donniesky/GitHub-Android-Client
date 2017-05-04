package me.donnie.github.ui.profile.repos

import me.donnie.github.common.base.BaseNavigator
import me.donnie.github.common.base.BasePresenter
import me.donnie.github.common.base.BaseView
import me.donnie.github.data.entity.Repo

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
interface ReposContract {

    interface Navigator : BaseNavigator

    interface View: BaseView {
        fun loadRepoSuccess(repos: List<Repo>, page: Int)
        fun onError()
    }

    interface Presenter: BasePresenter<View> {
        fun loadRepos(username: String, page: Int)
        fun onReposResponse(repos: List<Repo>)
    }

}