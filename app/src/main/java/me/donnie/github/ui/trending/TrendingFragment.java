package me.donnie.github.ui.trending;

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
import me.donnie.github.common.base.BaseFragment;
import me.donnie.github.data.entity.Trending;
import me.donnie.github.ui.main.MainActivity;
import me.donnie.github.ui.main.MainComponent;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */

public class TrendingFragment extends BaseFragment implements
TrendingContract.View {

    public static final String TAG = TrendingFragment.class.getSimpleName();

    @BindView(R.id.state_view)
    MultiStateView mStateView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @Inject
    TrendingContract.Presenter mPresenter;

    @Inject
    TrendingContract.Navigator mNavigator;

    TrendingComponent mTrendingComponent;

    private TrendingAdapter mAdapter;

    public static TrendingFragment newInstance() {

        Bundle args = new Bundle();

        TrendingFragment fragment = new TrendingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupComponent() {
        mTrendingComponent = getMainComponent().plus(new TrendingModule());
        mTrendingComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_common_view;
    }

    @Override
    protected void initView(View view) {
        mPresenter.attachView(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh_layout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initData() {
        if (mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter = new TrendingAdapter();
            mRecyclerView.setAdapter(mAdapter);
            refresh_layout.setRefreshing(true);
            mStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
            mPresenter.listTrending("java");
        }

        mAdapter.setEnableLoadMore(false);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Trending item = mAdapter.getItem(position);
                if (item != null) {
                    mNavigator.navigateToRepo(item);
                }
            }
        });
    }

    @Override
    public void showTrendingList(List<Trending> trendings, int page) {
        refresh_layout.setRefreshing(false);
        if (page == 1) {
            mAdapter.setNewData(trendings);
        } else if (page > 1) {
            mAdapter.addData(trendings);
        }
        mStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void onNetWorkError(String description) {
        refresh_layout.setRefreshing(false);
        if (mAdapter.getData().size() > 0) {
            toast.toast(description);
        } else {
            mStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    private MainComponent getMainComponent() {
        return ((MainActivity) getActivity()).getMainComponent();
    }
}
