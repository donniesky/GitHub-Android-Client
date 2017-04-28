package me.donnie.github.common.injection.component;

import android.content.Context;

import com.github.pwittchen.prefser.library.Prefser;

import javax.inject.Singleton;

import dagger.Component;
import me.donnie.github.common.injection.module.AppModule;
import me.donnie.github.common.injection.module.ProviderModule;
import me.donnie.github.common.injection.module.UtilsModule;
import me.donnie.github.common.injection.qualifier.ApplicationContext;
import me.donnie.github.common.injection.qualifier.Auth;
import me.donnie.github.common.injection.qualifier.Login;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.common.injection.qualifier.Trending;
import me.donnie.github.common.utils.NetWorkUtil;
import me.donnie.github.common.utils.ToastUtil;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
@Singleton
@Component(modules = {AppModule.class, ProviderModule.class,
        UtilsModule.class})
public interface AppComponent {

    @ApplicationContext
    Context getApplicationContext();

    NetWorkUtil netWork();

    ToastUtil toast();

    Prefser prefser();

    @Auth
    Retrofit auth();

    @Login
    Retrofit login();

    @Normal
    Retrofit normal();

    @Trending
    Retrofit trending();

    @Login
    OkHttpClient loginClient();

    @Normal
    OkHttpClient normalClient();

    @Trending
    OkHttpClient trendClient();

}
