package me.donnie.github.ui.trending;

import dagger.Subcomponent;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */
@FragmentScope
@Subcomponent(modules = TrendingModule.class)
public interface TrendingComponent {

    void inject(TrendingFragment fragment);

}
