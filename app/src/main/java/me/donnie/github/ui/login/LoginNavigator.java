package me.donnie.github.ui.login;

import android.content.Intent;

import javax.inject.Inject;

import me.donnie.github.common.injection.scope.ActivityScope;
import me.donnie.github.ui.main.MainActivity;

/**
 * @author donnieSky
 * @created_at 2017/4/7.
 * @description
 */

public class LoginNavigator implements LoginContract.Navigator {

    private final LoginActivity mActivity;

    @Inject
    public LoginNavigator(@ActivityScope final LoginActivity activity) {
        mActivity = activity;
    }

    @Override
    public void navigateToMain() {
        Intent callingIntent = MainActivity.getCallingIntent(mActivity);
        callingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivity.startActivity(callingIntent);
        mActivity.finishAffinity();
    }
}
