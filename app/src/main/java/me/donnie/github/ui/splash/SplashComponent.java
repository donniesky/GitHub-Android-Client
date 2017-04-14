package me.donnie.github.ui.splash;

import dagger.Component;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.injection.scope.ActivityScope;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */
@ActivityScope
@Component(dependencies = AppComponent.class,
modules = SplashModule.class)
public interface SplashComponent {

    void inject(SplashActivity activity);

}
