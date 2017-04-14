package me.donnie.github.data.source.remote.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Trending;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */

public interface ExploreApi {

    @GET("trending")
    Observable<List<Trending>> listTrending();

    @GET("trending")
    Observable<List<Trending>> listTrending(@Query("language") String language);

    @GET("trending")
    Observable<List<Trending>> listTrending(@QueryMap Map<String, String> params);//key: language & since


}
