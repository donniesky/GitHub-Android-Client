package me.donnie.github.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import me.donnie.github.BuildConfig;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.injection.component.DaggerAppComponent;
import me.donnie.github.common.injection.module.AppModule;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        initInjector();
    }

    private void initInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
