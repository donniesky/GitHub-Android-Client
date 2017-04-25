package me.donnie.github.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Issue;
import me.donnie.github.data.source.remote.RemoteIssueSource;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class IssueRepository {

    private final RemoteIssueSource mRemoteIssueSource;

    @Inject
    public IssueRepository(RemoteIssueSource remoteIssueSource) {
        mRemoteIssueSource = remoteIssueSource;
    }

    public Observable<List<Issue>> issues(String owner, String repo,
                                          String state,
                                          int page) {
        return mRemoteIssueSource.issues(owner, repo, state, page);
    }
}
