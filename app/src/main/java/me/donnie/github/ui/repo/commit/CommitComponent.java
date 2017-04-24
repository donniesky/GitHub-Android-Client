package me.donnie.github.ui.repo.commit;

import dagger.Subcomponent;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */
@FragmentScope
@Subcomponent(modules = CommitModule.class)
public interface CommitComponent {

    void inject(CommitFragment fragment);

}
