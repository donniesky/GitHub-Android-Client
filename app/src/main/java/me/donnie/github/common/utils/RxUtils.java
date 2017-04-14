package me.donnie.github.common.utils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author donnieSky
 * @created_at 2017/2/14.
 * @description
 */

public class RxUtils {

    public static void dispose(CompositeDisposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
