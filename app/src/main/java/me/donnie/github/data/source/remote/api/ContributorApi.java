package me.donnie.github.data.source.remote.api;

import java.util.List;

import io.reactivex.Observable;
import me.donnie.github.data.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */

public interface ContributorApi {

    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<User>> getContributors(@Path("owner") String owner,
                                           @Path("repo") String repo,
                                           @Query("page") int page);

}
