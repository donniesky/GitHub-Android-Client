package me.donnie.github.ui.repo.commit;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kennyc.view.MultiStateView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.donnie.github.R;
import me.donnie.github.common.adapter.RLoadMoreView;
import me.donnie.github.common.base.LazyFragment;
import me.donnie.github.data.entity.Commit;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.ui.repo.RepoActivity;
import me.donnie.github.ui.repo.RepoComponent;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommitFragment extends LazyFragment implements
CommitContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String TAG = CommitFragment.class.getSimpleName();
    private static final String PARAM_REPO = "param_commit_repo";

    @BindView(R.id.state_view)
    MultiStateView mStateView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @Inject
    CommitContract.Presenter mPresenter;

    @Inject
    CommitContract.Navigator mNavigator;

    private CommitComponent mCommitComponent;

    private Repo mRepo;

    private CommitAdapter mAdapter;

    public static CommitFragment newInstance(Repo repo) {

        Bundle args = new Bundle();
        args.putParcelable(PARAM_REPO, repo);
        CommitFragment fragment = new CommitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupComponent() {
        mCommitComponent = getRepoComponent().plus(new CommitModule());
        mCommitComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_common_view;
    }

    @Override
    protected void initView(View view) {
        mPresenter.attachView(this);

        mRepo = (Repo)getArguments().getParcelable(PARAM_REPO);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((CommitPresenter) mPresenter).refresh(mRepo);
            }
        });
    }

    @Override
    protected void initData() {
        if (mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter = new CommitAdapter();
            mAdapter.setEnableLoadMore(true);
            mAdapter.setLoadMoreView(new RLoadMoreView());
            mAdapter.setOnLoadMoreListener(this, mRecyclerView);
            mAdapter.disableLoadMoreIfNotFullPage();
            mRecyclerView.setAdapter(mAdapter);
            lazyLoad();
        }
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }

        if (!canLoadData(mStateView, mAdapter)) {
            return;
        }

        refresh_layout.setRefreshing(true);
        mStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        ((CommitPresenter) mPresenter).refresh(mRepo);
    }

    @Override
    public void onLoadMoreRequested() {
        ((CommitPresenter) mPresenter).loadmore(mRepo);
    }

    @Override
    public void loadCommitsSuccess(List<Commit> commits, int page) {
        refresh_layout.setRefreshing(false);
        mAdapter.loadMoreComplete();
        if (page == 1) {
            mAdapter.setNewData(commits);
        } else if (page > 1) {
            mAdapter.addData(commits);
        }
        mStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void onError() {
        refresh_layout.setRefreshing(false);
        mAdapter.loadMoreFail();
        if (mAdapter.getData().size() > 0) {
            toast.toast("error...");
        } else {
            mStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }

    public RepoComponent getRepoComponent() {
        return ((RepoActivity)getActivity()).getRepoComponent();
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }
}
