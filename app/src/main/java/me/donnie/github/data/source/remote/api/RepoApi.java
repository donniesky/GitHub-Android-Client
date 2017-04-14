package me.donnie.github.data.source.remote.api;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.Subscription;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public interface RepoApi {

    @GET
    @Headers({"Accept: application/vnd.github.drax-preview+json"})
    Observable<Repo> getRepoByFullUrl(@Url String url);

    @GET("repositories/{repoId}")
    @Headers({"Accept: application/vnd.github.drax-preview+json"})
    Observable<Repo> getRepo(@Path("repoId") String repoId);

    @GET("repos/{login}/{repoId}")
    @Headers({"Accept: application/vnd.github.drax-preview+json"})
    Observable<Repo> getRepo(@Path("login") String login,
                             @Path("repoId") String repoId);

    @GET
    @Headers("Accept: application/vnd.github.html")
    Observable<String> getReadmeHtml(@NonNull @Url String url);

    @GET("user/starred/{owner}/{repo}")
    Observable<Response<Boolean>> checkStarring(@NonNull @Path("owner") String login,
                                                @NonNull @Path("repo") String repoId);

    @PUT("user/starred/{owner}/{repo}")
    Observable<Response<Boolean>> starRepo(@NonNull @Path("owner") String login,
                                           @NonNull @Path("repo") String repoId);

    @DELETE("user/starred/{owner}/{repo}")
    Observable<Response<Boolean>> unstarRepo(@NonNull @Path("owner") String login,
                                             @NonNull @Path("repo") String repoId);

    @POST("/repos/{owner}/{repo}/forks")
    Observable<Repo> forkRepo(@NonNull @Path("owner") String login,
                              @NonNull @Path("repo") String repoId);

    @GET("repos/{owner}/{repo}/subscription")
    Observable<Subscription> isWatchingRepo(@Path("owner") String owner,
                                            @Path("repo") String repo);

    @PUT("user/subscriptions/{owner}/{repo}")
    Observable<Response<Boolean>> watchRepo(@Path("owner") String owner,
                                            @Path("repo") String repo);

    @DELETE("user/subscriptions/{owner}/{repo}")
    Observable<Response<Boolean>> unwatchRepo(@Path("owner") String owner,
                                              @Path("repo") String repo);

}
