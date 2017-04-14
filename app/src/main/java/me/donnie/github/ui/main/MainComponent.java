package me.donnie.github.ui.main;

import dagger.Component;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.injection.scope.ActivityScope;
import me.donnie.github.ui.event.EventComponent;
import me.donnie.github.ui.event.EventModule;
import me.donnie.github.ui.trending.TrendingComponent;
import me.donnie.github.ui.trending.TrendingModule;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */
@ActivityScope
@Component(dependencies = AppComponent.class,
modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);

    EventComponent plus(EventModule eventModule);

    TrendingComponent plus(TrendingModule trendingModule);

}
