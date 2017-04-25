package me.donnie.github.data.source.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.data.entity.Issue;
import me.donnie.github.data.source.remote.api.IssueApi;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class RemoteIssueSource {

    private final IssueApi mIssueApi;

    @Inject
    public RemoteIssueSource(@Normal IssueApi issueApi) {
        mIssueApi = issueApi;
    }

    public Observable<List<Issue>> issues(String owner, String repo,
                                          String state,
                                          int page) {
        return mIssueApi.issues(owner, repo, state, page);
    }
}
