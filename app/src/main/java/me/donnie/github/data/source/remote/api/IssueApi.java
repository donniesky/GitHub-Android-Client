package me.donnie.github.data.source.remote.api;

import java.util.List;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Issue;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public interface IssueApi {

    @GET("repos/{owner}/{repo}/issues?sort=updated")
    Observable<List<Issue>> issues(@Path("owner") String owner,
                                   @Path("repo") String repo,
                                   @Query("state") String state,
                                   @Query("page") int page);

    @GET("repos/{owner}/{repo}/issues/{number}")
    @Headers("Accept: application/vnd.github.VERSION.full+json")
    Observable<Issue> getIssue(@Path("owner") String owner,
                               @Path("repo") String repo,
                               @Path("number") int number);

}
