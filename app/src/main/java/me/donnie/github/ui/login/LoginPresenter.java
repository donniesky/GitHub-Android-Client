package me.donnie.github.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;

import com.github.pwittchen.prefser.library.Prefser;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.BuildConfig;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.common.utils.Util;
import me.donnie.github.data.UserRepository;
import me.donnie.github.data.entity.Auth;
import me.donnie.github.data.entity.Token;
import me.donnie.github.data.entity.User;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginContract.View view;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final UserRepository mRepository;

    @Inject
    Prefser prefser;

    @Inject
    public LoginPresenter(final UserRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    @Override
    public void loginByBrower(@NonNull Context context) {
        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority("github.com")
                .appendPath("login")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
                .appendQueryParameter("redirect_uri", BuildConfig.REDIRECT_URL)
                .appendQueryParameter("scope", "user,repo,gist,notifications")
                .appendQueryParameter("state", BuildConfig.APPLICATION_ID)
                .build();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setShowTitle(true);
        builder.enableUrlBarHiding();
        CustomTabsIntent intent = builder.build();
        intent.launchUrl(context, uri);
    }

    @Override
    public void handleAuthIntent(@Nullable Intent intent) {
        if (intent != null && intent.getData() != null) {
            Uri uri = intent.getData();
            Timber.tag(TAG).d(uri.toString());
            if (uri.toString().startsWith(BuildConfig.REDIRECT_URL)) {
                String tokenCode = uri.getQueryParameter("code");
                if (!TextUtils.isEmpty(tokenCode)) {
                    mDisposable.add(mRepository.getAccessToken(tokenCode, BuildConfig.GITHUB_CLIENT_ID,
                            BuildConfig.GITHUB_SECRET, BuildConfig.APPLICATION_ID, BuildConfig.REDIRECT_URL)
                            .compose(new SchedulerTransformer<Token>())
                            .subscribe(new Consumer<Token>() {
                                @Override
                                public void accept(Token token) throws Exception {
                                    onTokenResponse(token);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Timber.tag(TAG).e(throwable.getMessage());
                                }
                            }));
                }
            }
        }
    }

    @Override
    public void onTokenResponse(@Nullable Token token) {
        if (token != null) {
            String tokens = token.getToken() != null ? token.getToken() : token.getAccessToken();
            if (!TextUtils.isEmpty(tokens)) {
                Timber.tag(TAG).d("token: "+tokens);
                prefser.put("token", tokens);
                mDisposable.add(mRepository.getUser()
                .compose(new SchedulerTransformer<User>())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        onUserResponse(user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.tag(TAG).e(throwable.getMessage());
                    }
                }));
            }
        }
    }

    @Override
    public void onUserResponse(@Nullable User user) {
        if (user != null) {
            Timber.tag(TAG).d(user.getName());
            prefser.put("username", user.getLogin());
            view.onLoginSuccess(user);
        }
    }

    @Override
    public void login(@NonNull String username, @NonNull String password, @Nullable String twoFactorCode) {
        if (view == null) return;
        boolean nameIsEmpty = TextUtils.isEmpty(username);
        if (nameIsEmpty) {
            view.onEmptyUserName(nameIsEmpty);
            return;
        }
        boolean pwdIsEmpty = TextUtils.isEmpty(password);
        if (pwdIsEmpty) {
            view.onEmptyPassword(pwdIsEmpty);
            return;
        }
        if (!nameIsEmpty && !pwdIsEmpty) {
            String authToken = Util.generate(username, password);
            Timber.tag(TAG).d("authToken: " + authToken);
            prefser.put("authtoken", authToken);
            Auth auth = new Auth();
            auth.setScopes(Arrays.asList("user", "repo", "gist", "notifications"));
            auth.setNote(BuildConfig.APPLICATION_ID);
            auth.setNoteUrl(BuildConfig.REDIRECT_URL);
            auth.setClientId(BuildConfig.GITHUB_CLIENT_ID);
            auth.setClientSecret(BuildConfig.GITHUB_SECRET);

            mDisposable.add(mRepository.login(auth)
                    .compose(new SchedulerTransformer<Token>())
                    .subscribe(new Consumer<Token>() {
                        @Override
                        public void accept(Token token) throws Exception {
                            onTokenResponse(token);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Timber.tag(TAG).e(throwable.getMessage());
                        }
                    }));
        }
    }
}
