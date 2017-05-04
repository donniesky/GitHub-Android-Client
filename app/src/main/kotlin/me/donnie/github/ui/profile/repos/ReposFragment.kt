package me.donnie.github.ui.profile.repos

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
import me.donnie.github.data.entity.Repo
import me.donnie.github.ui.profile.ProfileActivity
import me.donnie.github.ui.profile.ProfileComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
class ReposFragment : LazyFragment(), ReposContract.View,
BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    lateinit var presenter: ReposContract.Presenter

    @Inject
    lateinit var navigator: ReposContract.Navigator

    private val profileComponent: ProfileComponent? get() = (activity as? ProfileActivity)?.mProfileComponent

    private var reposComponent: ReposComponent? = null

    private var adapter: ReposAdapter? = null

    companion object {
        @JvmStatic fun newInstance(): ReposFragment {
            val args = Bundle()
            val fragment = ReposFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        reposComponent = profileComponent?.plus(ReposModule())
        reposComponent?.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_common_view
    }

    override fun initView(view: View?) {
        presenter.attachView(this)

        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        refresh_layout.setOnRefreshListener {
            (presenter as ReposPresenter).refresh()
        }
    }

    override fun onLoadMoreRequested() {
        (presenter as ReposPresenter).loadmore()
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = ReposAdapter()
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
        (presenter as ReposPresenter).refresh()
    }

    override fun loadRepoSuccess(repos: List<Repo>, page: Int) {
        refresh_layout.isRefreshing = false
        adapter!!.loadMoreComplete()
        if (page == 1) {
            adapter!!.setNewData(repos)
        } else if (page > 1) {
            adapter!!.addData(repos)
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