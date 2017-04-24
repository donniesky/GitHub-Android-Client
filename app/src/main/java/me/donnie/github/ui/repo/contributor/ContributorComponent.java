package me.donnie.github.ui.repo.contributor;

import dagger.Subcomponent;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */
@FragmentScope
@Subcomponent(modules = ContributorModule.class)
public interface ContributorComponent {

    void inject(ContributorFragment fragment);

}
