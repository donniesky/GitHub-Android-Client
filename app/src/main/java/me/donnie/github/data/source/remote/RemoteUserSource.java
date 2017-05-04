package me.donnie.github.data.source.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.common.injection.qualifier.Login;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.data.entity.Auth;
import me.donnie.github.data.entity.Event;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.Token;
import me.donnie.github.data.entity.User;
import me.donnie.github.data.source.remote.api.AuthApi;
import me.donnie.github.data.source.remote.api.UserApi;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
public class RemoteUserSource {

    private final UserApi mUserApi;

    private final UserApi normalApi;

    private final AuthApi mAuthApi;

    @Inject
    public RemoteUserSource(@Login final UserApi userApi,@Normal final UserApi normalApi,
                            @me.donnie.github.common.injection.qualifier.Auth final AuthApi authApi) {
        this.mUserApi = userApi;
        this.normalApi = normalApi;
        this.mAuthApi = authApi;
    }

    public Observable<Token> getAccessToken(String code, String clientId, String clientSecret, String state,
                                            String redirectUrl) {
        return mAuthApi.getAccessToken(code, clientId, clientSecret, state, redirectUrl);
    }

    public Observable<Token> login(Auth auth) {
        return mUserApi.login(auth);
    }

    public Observable<User> getUser() {
        return mUserApi.getUser();
    }

    public Observable<User> getUser(String username) {
        return mUserApi.getUser(username);
    }

    public Observable<List<Event>> receiveEvents(String username, int page) {
        return normalApi.receiveEvents(username, page);
    }

    public Observable<List<Event>> getEvents(String username, int page) {
        return normalApi.getEvents(username, page);
    }

    public Observable<List<Repo>> getRepos(String username, int page) {
        return normalApi.getRepos(username, page);
    }

    public Observable<List<Repo>> getStars(String username, int page) {
        return normalApi.getStars(username, page);
    }

    public Observable<List<User>> getFollowers(String username, int page) {
        return normalApi.getFollowers(username, page);
    }

    public Observable<List<User>> getFollowing(String username, int page) {
        return normalApi.getFollowing(username, page);
    }
}
