package me.donnie.github.ui.event;

import dagger.Subcomponent;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */
@FragmentScope
@Subcomponent(modules = EventModule.class)
public interface EventComponent {

    void inject(EventFragment fragment);

}
