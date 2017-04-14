package me.donnie.github.ui.splash;

import android.text.TextUtils;

import com.github.pwittchen.prefser.library.Prefser;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import me.donnie.github.common.utils.RxUtils;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class SplashPresenter implements SplashContract.Presenter {

    private static final String TAG = SplashPresenter.class.getSimpleName();

    private SplashContract.View view;

    @Inject
    Prefser prefser;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void attachView(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    @Override
    public boolean isLogin() {
        if (!TextUtils.isEmpty(prefser.get("token", String.class, ""))) {
            Timber.tag(TAG).d("token exist !");
            return true;
        } else {
            Timber.tag(TAG).d("token doesn't exist !");
            view.notLogin();
            return false;
        }
    }
}
