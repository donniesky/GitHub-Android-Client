package me.donnie.github.data.source.remote.api;

import java.util.List;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Comment;
import me.donnie.github.data.entity.CommentContent;
import me.donnie.github.data.entity.Commit;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public interface CommitApi {

    @GET("repos/{owner}/{repo}/commits")
    Observable<List<Commit>> getCommits(@Path("owner") String owner,
                                        @Path("repo") String repo,
                                        @Query("sha") String branch,
                                        @Query("page") int page);

    @GET("repos/{owner}/{repo}/commits/{sha}")
    Observable<Commit> getCommit(@Path("owner") String owner,
                                 @Path("repo") String repo,
                                 @Path("sha") String sha);

    @GET("repos/{owner}/{repo}/commits/{sha}/comments")
    @Headers("Accept: application/vnd.github.VERSION.full+json")
    Observable<List<Comment>> getCommitComments(@Path("owner") String owner,
                                                @Path("repo") String repo,
                                                @Path("sha") String sha,
                                                @Query("page") int page);

    @POST("repos/{owner}/{repo}/commits/{sha}/comments")
    @Headers("Accept: application/vnd.github.VERSION.full+json")
    Observable<Comment> postCommitComment(@Path("owner") String owner,
                                          @Path("repo") String repo,
                                          @Path("sha") String sha,
                                          @Body CommentContent body);

    @PATCH("repos/{owner}/{repo}/comments/{id}")
    @Headers("Accept: application/vnd.github.VERSION.full+json")
    Observable<Comment> editCommitComment(@Path("owner") String owner,
                                          @Path("repo") String repo,
                                          @Path("id") long id,
                                          @Body CommentContent body);

    @DELETE("repos/{owner}/{repo}/comments/{id}")
    Observable<Response<Boolean>> deleteComment(@Path("owner") String owner,
                                                @Path("repo") String repo,
                                                @Path("id") long id);
}
