package me.donnie.github.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * @author donnieSky
 * @created_at 2017/2/23.
 * @description 吐司工具类
 */
public class ToastUtil {

    private final Context context;
    private final Handler mainHandler;

    public ToastUtil(@NonNull final Context context) {
        this.context = context;
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public void toast(final String content) {
        doToast(content, Toast.LENGTH_SHORT);
    }

    public void toast(@StringRes final int contentResId) {
        doToast(contentResId, Toast.LENGTH_SHORT);
    }

    public void longToast(final String content) {
        doToast(content, Toast.LENGTH_LONG);
    }

    public void longToast(@StringRes final int contentResId) {
        doToast(contentResId, Toast.LENGTH_LONG);
    }

    private void doToast(final String content, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, content, duration).show();
        } else {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, content, duration).show();
                }
            });
        }
    }

    private void doToast(@StringRes final int contentResId, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, contentResId, duration).show();
        } else {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, contentResId, duration).show();
                }
            });
        }
    }
}
