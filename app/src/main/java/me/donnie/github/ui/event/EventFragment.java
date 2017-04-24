package me.donnie.github.ui.event;

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
import me.donnie.github.common.base.BaseFragment;
import me.donnie.github.data.entity.Event;
import me.donnie.github.ui.main.MainActivity;
import me.donnie.github.ui.main.MainComponent;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class EventFragment extends BaseFragment implements
        EventContract.View,BaseQuickAdapter.RequestLoadMoreListener {

    public static final String TAG = EventFragment.class.getSimpleName();

    @BindView(R.id.state_view)
    MultiStateView mStateView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @Inject
    EventContract.Presenter mPresenter;

    @Inject
    EventContract.Navigator mNavigator;

    EventComponent mEventComponent;

    private EventAdapter mAdapter;

    public static EventFragment newInstance() {

        Bundle args = new Bundle();

        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupComponent() {
        mEventComponent = getMainComponent().plus(new EventModule());
        mEventComponent.inject(this);
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
                ((EventPresenter)mPresenter).refresh();
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        ((EventPresenter)mPresenter).loadmore();
    }

    @Override
    protected void initData() {
        if (mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter = new EventAdapter();
            mRecyclerView.setAdapter(mAdapter);
            refresh_layout.setRefreshing(true);
            mStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
            ((EventPresenter)mPresenter).refresh();
        }

        mAdapter.setEnableLoadMore(true);
        mAdapter.setAutoLoadMoreSize(2);
        mAdapter.setLoadMoreView(new RLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage();

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Event item = mAdapter.getItem(position);
                if (item != null) {
                    mNavigator.navigateToRepo(item);
                }
            }
        });
    }

    @Override
    public void dataIsEmpty(boolean isRefresh) {
        if (isRefresh) {
            mStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }

    @Override
    public void showEvents(List<Event> events, int page) {
        refresh_layout.setRefreshing(false);
        mAdapter.loadMoreComplete();
        if (page == 1) {
            mAdapter.setNewData(events);
        } else if (page > 1) {
            mAdapter.addData(events);
        }
        mStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void onEventError(String description) {
        refresh_layout.setRefreshing(false);
        mAdapter.loadMoreFail();
        if (mAdapter.getData().size() > 0) {
            toast.toast(description);
        } else {
            mStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }

    private MainComponent getMainComponent() {
        return ((MainActivity) getActivity()).getMainComponent();
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }


}
