package me.donnie.github.ui.repo.pullrequest;

import dagger.Subcomponent;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */
@FragmentScope
@Subcomponent(modules = PullRequestModule.class)
public interface PullRequestComponent {

    void inject(PullRequestFragment fragment);

}
