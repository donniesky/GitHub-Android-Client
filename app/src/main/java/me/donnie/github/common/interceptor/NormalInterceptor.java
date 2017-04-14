package me.donnie.github.common.interceptor;

import android.text.TextUtils;

import com.github.pwittchen.prefser.library.Prefser;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author donnieSky
 * @created_at 2017/4/7.
 * @description
 */

public class NormalInterceptor implements Interceptor {

    private final Prefser mPrefser;

    public NormalInterceptor(Prefser prefser) {
        mPrefser = prefser;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        String token = mPrefser.get("token", String.class, "");
        if (!TextUtils.isEmpty(token)) {
            requestBuilder.header("Authorization", "token " + token);
        }

        requestBuilder.addHeader("Accept", "application/vnd.github.v3+json")
                .addHeader("Content-type", "application/vnd.github.v3+json");

        requestBuilder.method(original.method(), original.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
