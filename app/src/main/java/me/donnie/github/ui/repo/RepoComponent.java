package me.donnie.github.ui.repo;

import dagger.Component;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.injection.scope.ActivityScope;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */
@ActivityScope
@Component(dependencies = AppComponent.class,
modules = RepoModule.class)
public interface RepoComponent {

    void inject(RepoActivity activity);

}
