package me.donnie.github.common.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.App;
import me.donnie.github.common.injection.qualifier.ApplicationContext;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @ApplicationContext
    @Singleton
    @Provides
    Context provideContext() {
        return app;
    }
}
