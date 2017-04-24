package me.donnie.github.ui.repo;

import dagger.Component;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.injection.scope.ActivityScope;
import me.donnie.github.ui.repo.commit.CommitComponent;
import me.donnie.github.ui.repo.commit.CommitModule;
import me.donnie.github.ui.repo.contributor.ContributorComponent;
import me.donnie.github.ui.repo.contributor.ContributorModule;
import me.donnie.github.ui.repo.issue.IssueComponent;
import me.donnie.github.ui.repo.issue.IssueModule;
import me.donnie.github.ui.repo.pullrequest.PullRequestComponent;
import me.donnie.github.ui.repo.pullrequest.PullRequestModule;
import me.donnie.github.ui.repo.readme.ReadmeComponent;
import me.donnie.github.ui.repo.readme.ReadmeModule;

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

    ReadmeComponent plus(ReadmeModule module);

    IssueComponent plus(IssueModule module);

    PullRequestComponent plus(PullRequestModule module);

    CommitComponent plus(CommitModule module);

    ContributorComponent plus(ContributorModule module);
}
