package me.donnie.github.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

public abstract class BaseActivity extends AppCompatActivity {

    protected Navigator navigator;

    protected ToastUtil toast;

    protected NetWorkUtil network;

    protected Prefser prefser;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupComponent(getAppComponent());
        super.onCreate(savedInstanceState);
        Timber.tag("BaseActivity").d("onCreate");
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(getLayoutResId());
        navigator = getAppComponent().navigator();
        toast = getAppComponent().toast();
        network = getAppComponent().netWork();
        prefser = getAppComponent().prefser();
        initView(savedInstanceState);
        initData();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.tag("BaseActivity").d("onDestory");
        mUnbinder.unbind();
    }

    protected abstract void setupComponent(AppComponent component);
    protected abstract @LayoutRes int getLayoutResId();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData();

    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }
}
