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
public class AuthInterceptor implements Interceptor {

    private Prefser mPrefser;

    public AuthInterceptor(Prefser prefser) {
        mPrefser = prefser;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        String authtoken = mPrefser.get("authtoken", String.class, "");
        if (!TextUtils.isEmpty(authtoken)) {
            builder.header("Authorization", authtoken);
        }
        Request request = builder.build();
        return chain.proceed(request);
    }
}
