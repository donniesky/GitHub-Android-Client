package me.donnie.github.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pwittchen.prefser.library.Prefser;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import me.donnie.github.common.App;
import me.donnie.github.common.Navigator;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.utils.NetWorkUtil;
import me.donnie.github.common.utils.ToastUtil;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    protected Navigator navigator;

    protected ToastUtil toast;

    protected NetWorkUtil network;

    protected Prefser prefser;

    protected boolean isPrepared;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setupComponent();
        super.onCreate(savedInstanceState);
        Timber.tag("BaseFragment").d("onCreate");
        navigator = getAppComponent().navigator();
        toast = getAppComponent().toast();
        network = getAppComponent().netWork();
        prefser = getAppComponent().prefser();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        initView(view);
        initData();

        isPrepared = true;
    }

    protected abstract void setupComponent();

    protected abstract @LayoutRes int getLayoutResId();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected AppComponent getAppComponent() {
        return ((App) getActivity().getApplication()).getAppComponent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.tag("BaseFragment").d("onDestoryView");
        mUnbinder.unbind();
    }
}
