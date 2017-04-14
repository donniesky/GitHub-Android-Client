package me.donnie.github.data.source.remote.api;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Token;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public interface AuthApi {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("access_token")
    Observable<Token> getAccessToken(@NonNull @Field("code") String code,
                                     @NonNull @Field("client_id") String clientId,
                                     @NonNull @Field("client_secret") String clientSecret,
                                     @NonNull @Field("state") String state,
                                     @NonNull @Field("redirect_uri") String redirectUrl);

}
