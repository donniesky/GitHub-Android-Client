package me.donnie.github.ui.main;

import com.github.pwittchen.prefser.library.Prefser;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import me.donnie.github.common.utils.RxUtils;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainContract.View view;

    @Inject
    Prefser prefser;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public MainPresenter() {
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }
}
