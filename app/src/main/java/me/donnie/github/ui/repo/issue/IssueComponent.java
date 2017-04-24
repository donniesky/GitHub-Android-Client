package me.donnie.github.ui.repo.issue;

import dagger.Subcomponent;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */
@FragmentScope
@Subcomponent(modules = IssueModule.class)
public interface IssueComponent {

    void inject(IssueFragment fragment);

}
