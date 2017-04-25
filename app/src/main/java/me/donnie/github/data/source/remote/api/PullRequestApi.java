package me.donnie.github.data.source.remote.api;

import java.util.List;

import io.reactivex.Observable;
import me.donnie.github.data.entity.PullRequest;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */

public interface PullRequestApi {

    @GET("repos/{owner}/{repo}/pulls")
    Observable<List<PullRequest>> getPullRequests(@Path("owner") String owner,
                                                  @Path("repo") String repo,
                                                  @Query("state") String state,
                                                  @Query("page") int page);

    @GET("repos/{owner}/{repo}/pulls/{number}")
    @Headers("Accept: application/vnd.github.VERSION.html")
    Observable<PullRequest> getPullRequest(@Path("owner") String owner,
                                           @Path("repo") String repo,
                                           @Path("number") int number);

}
