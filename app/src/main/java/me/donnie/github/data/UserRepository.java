package me.donnie.github.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Auth;
import me.donnie.github.data.entity.Event;
import me.donnie.github.data.entity.Token;
import me.donnie.github.data.entity.User;
import me.donnie.github.data.source.remote.RemoteUserSource;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
public class UserRepository {

    private final RemoteUserSource mRemoteUserSource;

    @Inject
    public UserRepository(RemoteUserSource remoteUserSource) {
        mRemoteUserSource = remoteUserSource;
    }

    public Observable<Token> getAccessToken(String code, String clientId, String clientSecret, String state,
                                            String redirectUrl) {
        return mRemoteUserSource.getAccessToken(code, clientId, clientSecret, state, redirectUrl);
    }

    public Observable<Token> login(Auth auth) {
        return mRemoteUserSource.login(auth);
    }

    public Observable<User> getUser() {
        return mRemoteUserSource.getUser();
    }

    public Observable<User> getUser(String username) {
        return mRemoteUserSource.getUser(username);
    }

    public Observable<List<Event>> receiveEvents(String username, int page) {
        return mRemoteUserSource.receiveEvents(username, page);
    }
}
