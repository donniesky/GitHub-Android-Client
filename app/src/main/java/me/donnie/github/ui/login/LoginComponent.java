package me.donnie.github.ui.login;

import dagger.Component;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.injection.scope.ActivityScope;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
