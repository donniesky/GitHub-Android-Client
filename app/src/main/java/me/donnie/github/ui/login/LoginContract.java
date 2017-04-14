package me.donnie.github.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.Token;
import me.donnie.github.data.entity.User;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public interface LoginContract {

    interface Navigator extends BaseNavigator {

        void navigateToMain();

    }

    interface View extends BaseView {
        void onRequire2Factor();
        void onEmptyUserName(boolean isEmpty);
        void onEmptyPassword(boolean isEmpty);
        void onLoginSuccess(User user);
    }

    interface Presenter extends BasePresenter<View> {
        void loginByBrower(@NonNull Context context);
        void handleAuthIntent(@Nullable Intent intent);
        void onTokenResponse(@Nullable Token token);
        void onUserResponse(@Nullable User user);
        void login(@NonNull String username, @NonNull String password,
                   @Nullable String twoFactorCode);
    }

}
