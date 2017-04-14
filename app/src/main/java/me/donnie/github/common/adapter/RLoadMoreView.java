package me.donnie.github.common.adapter;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

import me.donnie.github.R;

/**
 * @author donnieSky
 * @created_at 2017/4/13.
 * @description
 */

public class RLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
