package me.donnie.github.common.base;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kennyc.view.MultiStateView;

/**
 * @author donnieSky
 * @created_at 2017/4/18.
 * @description
 */

public abstract class LazyFragment extends BaseFragment {

    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected boolean canLoadData(@NonNull MultiStateView stateView, @NonNull BaseQuickAdapter adapter) {
        @MultiStateView.ViewState
        int viewState = stateView.getViewState();
        if (viewState == MultiStateView.VIEW_STATE_LOADING ||
                (viewState == MultiStateView.VIEW_STATE_CONTENT &&
                        adapter.getItemCount() > 0) ||
                viewState == MultiStateView.VIEW_STATE_EMPTY ||
                viewState == MultiStateView.VIEW_STATE_ERROR) {
            return false;
        }
        return true;
    }

    protected abstract void lazyLoad();

    protected void onVisible() {
        if (isVisible && isPrepared) {
            lazyLoad();
        }
    }

    protected void onInvisible(){}
}
