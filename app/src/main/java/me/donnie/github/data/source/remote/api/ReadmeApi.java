package me.donnie.github.data.source.remote.api;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import me.donnie.github.data.entity.MarkDown;
import me.donnie.github.data.entity.ReadMe;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public interface ReadmeApi {

    @Headers("Accept: application/vnd.github.html")
    @GET("repos/{owner}/{repo}/readme")
    Observable<String> readme(@Path("owner") String owner,
                              @Path("repo") String repo);

    @GET("repos/{owner}/{repo}/readme")
    Observable<ReadMe> readmes(@Path("owner") String owner,
                               @Path("repo") String repo);

    @Headers("Accept: application/vnd.github.html")
    @POST("markdown")
    Observable<String> convertReadmeToHtml(@Body MarkDown markDown);

    @GET
    @Headers("Accept: application/vnd.github.html")
    Observable<String> getReadmeHtml(@NonNull @Url String url);

}
