package me.donnie.github.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.donnie.github.R;
import me.donnie.github.common.base.BaseActivity;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.data.entity.User;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public class LoginActivity extends BaseActivity
        implements LoginContract.View, View.OnClickListener {

    @Inject
    LoginContract.Presenter mPresenter;

    @Inject
    LoginContract.Navigator mNavigator;

    private LoginComponent mLoginComponent;

    @BindView(R.id.username)
    AppCompatEditText username;

    @BindView(R.id.password)
    AppCompatEditText password;

    public static Intent getCallingIntent(Context context){
        Intent callingIntent = new Intent(context,LoginActivity.class);
        return callingIntent;
    }

    @Override
    protected void setupComponent(AppComponent component) {
        mLoginComponent = DaggerLoginComponent.builder()
                .appComponent(component)
                .loginModule(new LoginModule(this))
                .build();
        mLoginComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPresenter.attachView(this);
    }

    @OnClick({R.id.btn_login, R.id.open_in_brower})
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                Bundle param = new Bundle();
                param.putString(FirebaseAnalytics.Param.ITEM_NAME, username.getText().toString());
                param.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "login_button");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, param);
                mPresenter.login(username.getText().toString(), password.getText().toString(), null);
                break;
            case R.id.open_in_brower:
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "brower_login_button");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                mPresenter.loginByBrower(this);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequire2Factor() {

    }

    @Override
    public void onEmptyUserName(boolean isEmpty) {
        if (isEmpty) {
            username.setError("请输入用户名或密码");
        }
    }

    @Override
    public void onEmptyPassword(boolean isEmpty) {
        if (isEmpty) {
            password.setError("请输入密码");
        }
    }

    @Override
    public void onLoginSuccess(User user) {
        toast.toast("登录成功!");
        mNavigator.navigateToMain();
    }

    public LoginComponent getLoginComponent() {
        return mLoginComponent;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mPresenter.handleAuthIntent(intent);
        setIntent(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.handleAuthIntent(getIntent());
        setIntent(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
