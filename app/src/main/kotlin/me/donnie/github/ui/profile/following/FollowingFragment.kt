package me.donnie.github.ui.profile.following

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_common_view.*
import me.donnie.github.R
import me.donnie.github.common.adapter.RLoadMoreView
import me.donnie.github.common.base.LazyFragment
import me.donnie.github.data.entity.User
import me.donnie.github.ui.profile.ProfileActivity
import me.donnie.github.ui.profile.ProfileComponent
import me.donnie.github.ui.repo.contributor.ContributorAdapter
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
class FollowingFragment : LazyFragment(), FollowingContract.View,
BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    lateinit var presenter: FollowingContract.Presenter

    @Inject
    lateinit var navigator: FollowingContract.Navigator

    private val profileComponent: ProfileComponent? get() = (activity as? ProfileActivity)?.mProfileComponent

    private var followingComponent: FollowingComponent? = null

    private var adapter: ContributorAdapter? = null

    companion object {
        @JvmStatic fun newInstance(): FollowingFragment {
            val args = Bundle()
            val fragment = FollowingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        followingComponent = profileComponent?.plus(FollowingModule())
        followingComponent?.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_common_view
    }

    override fun initView(view: View?) {
        presenter.attachView(this)

        rv.layoutManager = GridLayoutManager(activity, 2)
        refresh_layout.setOnRefreshListener {
            (presenter as FollowingPresenter).refresh()
        }
    }

    override fun onLoadMoreRequested() {
        (presenter as FollowingPresenter).loadmore()
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = ContributorAdapter()
            adapter!!.setEnableLoadMore(true)
            adapter!!.setLoadMoreView(RLoadMoreView())
            adapter!!.setOnLoadMoreListener(this, rv)
            rv.adapter = adapter
            lazyLoad()
        }
    }

    override fun lazyLoad() {
        if (!isVisible && !isPrepared) {
            return
        }

        if (!canLoadData(state_view, adapter!!)) {
            return
        }

        refresh_layout.isRefreshing = true
        state_view.viewState = MultiStateView.VIEW_STATE_LOADING
        (presenter as FollowingPresenter).refresh()
    }

    override fun loadFollowingSuccess(users: List<User>, page: Int) {
        refresh_layout.isRefreshing = false
        adapter!!.loadMoreComplete()
        if (page == 1) {
            adapter!!.setNewData(users)
        } else if (page > 1) {
            adapter!!.addData(users)
        }
        state_view.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    override fun onError() {
        refresh_layout.isRefreshing = false
        adapter!!.loadMoreFail()
        if (adapter!!.data.size > 0) {
            toast.toast("error")
        } else {
            state_view.viewState = MultiStateView.VIEW_STATE_ERROR
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}