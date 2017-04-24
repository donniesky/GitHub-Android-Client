package me.donnie.github.ui.repo.readme;

import dagger.Subcomponent;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/17.
 * @description
 */
@FragmentScope
@Subcomponent(modules = ReadmeModule.class)
public interface ReadmeComponent {

    void inject(ReadmeFragment fragment);

}
