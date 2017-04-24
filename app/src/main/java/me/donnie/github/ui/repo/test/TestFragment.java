package me.donnie.github.ui.repo.test;

import android.os.Bundle;
import android.view.View;

import me.donnie.github.R;
import me.donnie.github.common.base.LazyFragment;

/**
 * @author donnieSky
 * @created_at 2017/4/13.
 * @description
 */

public class TestFragment extends LazyFragment {

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupComponent() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {

    }
}
