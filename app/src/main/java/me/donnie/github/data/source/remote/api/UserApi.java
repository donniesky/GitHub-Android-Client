package me.donnie.github.data.source.remote.api;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Auth;
import me.donnie.github.data.entity.Event;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.Token;
import me.donnie.github.data.entity.User;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("users/{username}/events")
    Observable<List<Event>> getEvents(@Path("username") String username,
                                      @Query("page") int page);

    @GET("users/{username}/repos?sort=pushed&direction=desc")
    Observable<List<Repo>> getRepos(@Path("username") String username,
                                    @Query("page") int page);

    @GET("users/{username}/starred")
    Observable<List<Repo>> getStars(@Path("username") String usernmae,
                                      @Query("page") int page);

    @GET("users/{username}/following")
    Observable<List<User>> getFollowing(@Path("username") String username,
                                        @Query("page") int page);

    @GET("users/{username}/followers")
    Observable<List<User>> getFollowers(@Path("username") String username,
                                        @Query("page") int page);

    @GET("user/following/{username}")
    Observable<Response<Boolean>> isFollow(@Path("username") String username);

    @PUT("user/following/{username}")
    Observable<Response<Boolean>> follow(@Path("username") String username);

    @DELETE("user/following/{username}")
    Observable<Response<Boolean>> unfollow(@Path("username") String username);
}
