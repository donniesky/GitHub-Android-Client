package me.donnie.github.data.source.remote.api;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Auth;
import me.donnie.github.data.entity.Event;
import me.donnie.github.data.entity.Token;
import me.donnie.github.data.entity.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public interface UserApi {

    @POST("authorizations")
    Observable<Token> login(@NonNull @Body Auth auth);

    @GET("user")
    Observable<User> getUser();

    @GET("users/{username}")
    Observable<User> getUser(@NonNull @Path("username") String username);

    @GET("users/{username}/received_events")
    Observable<List<Event>> receiveEvents(@NonNull @Path("username") String username,
                                          @Query("page") int page);

}
