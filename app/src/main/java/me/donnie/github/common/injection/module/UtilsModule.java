package me.donnie.github.common.injection.module;

import android.content.Context;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.github.pwittchen.prefser.library.Prefser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.qualifier.ApplicationContext;
import me.donnie.github.common.utils.NetWorkUtil;
import me.donnie.github.common.utils.ToastUtil;

/**
 * @author donnieSky
 * @created_at 2017/2/23.
 * @description
 */
@Module
public class UtilsModule {

    @Singleton
    @Provides
    NetWorkUtil provideNetWorkUtil(@ApplicationContext final Context context) {
        return new NetWorkUtil(context);
    }

    @Singleton
    @Provides
    ToastUtil provideToastUtil(@ApplicationContext final Context context) {
        return new ToastUtil(context);
    }

    @Singleton
    @Provides
    RxSharedPreferences provideRxSharedPreferences(@ApplicationContext final Context context) {
        return RxSharedPreferences.create(context.getSharedPreferences("PREF_BIGMOUTH", Context.MODE_PRIVATE));
    }

    @Singleton
    @Provides
    Prefser providePrefser(@ApplicationContext final Context context) {
        return new Prefser(context);
    }

}
