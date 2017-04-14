package me.donnie.github.ui.event;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */
@Module
public class EventModule {

    @Provides
    @FragmentScope
    EventContract.Navigator provideEventNavigator(EventNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    EventContract.Presenter provideEventPresenter(EventPresenter presenter) {
        return presenter;
    }

}
