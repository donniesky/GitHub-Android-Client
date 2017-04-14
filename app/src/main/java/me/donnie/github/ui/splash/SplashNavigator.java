package me.donnie.github.ui.splash;

import android.content.Intent;

import javax.inject.Inject;

import me.donnie.github.ui.login.LoginActivity;
import me.donnie.github.ui.main.MainActivity;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class SplashNavigator implements SplashContract.Navigator {

    private final SplashActivity mActivity;

    @Inject
    public SplashNavigator(SplashActivity activity) {
        mActivity = activity;
    }

    @Override
    public void navigateToLogin() {
        Intent callingIntent = LoginActivity.getCallingIntent(mActivity);
        callingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivity.startActivity(callingIntent);
        mActivity.finishAffinity();
    }

    @Override
    public void navigateToMain() {
        Intent callingIntent = MainActivity.getCallingIntent(mActivity);
        callingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivity.startActivity(callingIntent);
        mActivity.finishAffinity();
    }
}
