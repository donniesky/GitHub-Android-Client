package me.donnie.github.common.injection.module;

import android.content.Context;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.BuildConfig;
import me.donnie.github.common.injection.qualifier.ApplicationContext;
import me.donnie.github.common.injection.qualifier.Auth;
import me.donnie.github.common.injection.qualifier.Login;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.common.injection.qualifier.Trending;
import me.donnie.github.common.interceptor.AuthInterceptor;
import me.donnie.github.common.interceptor.NormalInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
@Module
public class ProviderModule {

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting()
                .create();
    }

    @Singleton
    @Provides
    @Trending
    OkHttpClient provideTrendingClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(3000, TimeUnit.SECONDS);
        builder.readTimeout(2000,TimeUnit.SECONDS);
        builder.writeTimeout(2000,TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Timber.tag("OkHttp").d(message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    @Singleton
    @Provides
    @Normal
    OkHttpClient provideOkhttpClient(@ApplicationContext final Context context, Prefser prefser) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(3000, TimeUnit.SECONDS);
        builder.readTimeout(2000,TimeUnit.SECONDS);
        builder.writeTimeout(2000,TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Timber.tag("OkHttp").d(message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        builder.addInterceptor(new NormalInterceptor(prefser));

        return builder.build();
    }

    @Singleton
    @Provides
    @Login
    OkHttpClient provideLoginClient(@ApplicationContext final Context context, Prefser prefser) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(3000, TimeUnit.SECONDS);
        builder.readTimeout(2000,TimeUnit.SECONDS);
        builder.writeTimeout(2000,TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Timber.tag("OkHttp").d(message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        builder.addInterceptor(new AuthInterceptor(prefser));

        return builder.build();
    }

    @Singleton
    @Provides
    @Login
    Retrofit provideLoginRetrofit(@Login final OkHttpClient okHttpClient, final Gson gson) {
        return new Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(io.reactivex.schedulers.Schedulers.io()))
                .build();
    }

    @Singleton
    @Provides
    @Auth
    Retrofit provideAuthRetrofit(@Login final OkHttpClient okHttpClient, final Gson gson) {
        return new Retrofit.Builder().baseUrl(BuildConfig.API_OAUTH_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(io.reactivex.schedulers.Schedulers.io()))
                .build();
    }

    @Singleton
    @Provides
    @Normal
    Retrofit provideRetrofit(@Normal final OkHttpClient okHttpClient, final Gson gson) {
        return new Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(io.reactivex.schedulers.Schedulers.io()))
                .build();
    }

    @Singleton
    @Provides
    @Trending
    Retrofit provideTrendingRetrofit(@Normal final OkHttpClient okHttpClient, final Gson gson) {
        return new Retrofit.Builder().baseUrl(BuildConfig.API_TRENDING_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(io.reactivex.schedulers.Schedulers.io()))
                .build();
    }

}
