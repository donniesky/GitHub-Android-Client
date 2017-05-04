package me.donnie.github.ui.profile.event

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_common_view.*
import me.donnie.github.R
import me.donnie.github.common.adapter.RLoadMoreView
import me.donnie.github.common.base.LazyFragment
import me.donnie.github.data.entity.Event
import me.donnie.github.ui.event.EventAdapter
import me.donnie.github.ui.profile.ProfileActivity
import me.donnie.github.ui.profile.ProfileComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/2.
 * @description
 * @version
 */
class UserEventFragment : LazyFragment(), UserEventContract.View,
BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    lateinit var presenter: UserEventContract.Presenter

    @Inject
    lateinit var navigator: UserEventContract.Navigator

    private val profileComponent: ProfileComponent? get() = (activity as? ProfileActivity)?.mProfileComponent

    private var userEventComponent: UserEventComponent? = null

    private var mAdapter: EventAdapter? = null

    companion object {
        @JvmStatic fun newInstance(): UserEventFragment {
            val args = Bundle()
            val fragment = UserEventFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        userEventComponent = profileComponent?.plus(UserEventModule())
        userEventComponent?.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_common_view
    }

    override fun initView(view: View?) {
        presenter.attachView(this)

        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        refresh_layout.setOnRefreshListener {
            (presenter as UserEventPresenter).refresh()
        }

    }

    override fun onLoadMoreRequested() {
        (presenter as UserEventPresenter).loadmore()
    }

    override fun initData() {

        if (mAdapter != null) {
            rv.adapter = mAdapter
        } else {
            mAdapter = EventAdapter()
            mAdapter!!.setEnableLoadMore(true)
            mAdapter!!.setLoadMoreView(RLoadMoreView())
            mAdapter!!.setOnLoadMoreListener(this, rv)
            rv.adapter = mAdapter
            lazyLoad()
        }
    }

    override fun lazyLoad() {
        if (!isVisible && !isPrepared) {
            return
        }

        if (!canLoadData(state_view, mAdapter!!)) {
            return
        }

        refresh_layout.isRefreshing = true
        state_view.viewState = MultiStateView.VIEW_STATE_LOADING
        (presenter as UserEventPresenter).refresh()

    }

    override fun loadUserEventSuccess(events: List<Event>, page: Int) {
        refresh_layout.isRefreshing = false
        mAdapter!!.loadMoreComplete()
        if (page == 1) {
            mAdapter!!.setNewData(events)
        } else if (page > 1) {
            mAdapter!!.addData(events)
        }
        state_view.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    override fun onError() {
        refresh_layout.isRefreshing = false
        mAdapter!!.loadMoreFail()
        if (mAdapter!!.data.size > 0) {
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