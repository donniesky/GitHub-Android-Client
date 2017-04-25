package me.donnie.github.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.PullRequest;
import me.donnie.github.data.source.remote.RemotePullRequestSource;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */

public class PullRequestRepository {

    private final RemotePullRequestSource mRemotePullRequestSource;

    @Inject
    public PullRequestRepository(RemotePullRequestSource remotePullRequestSource) {
        mRemotePullRequestSource = remotePullRequestSource;
    }

    public Observable<List<PullRequest>> getPullRequests(String owner, String repo,
                                                         String state, int page) {
        return mRemotePullRequestSource.getPullRequests(owner, repo, state, page);
    }

    public Observable<PullRequest> getPullRequest(String owner, String repo,
                                                  int number) {
        return mRemotePullRequestSource.getPullRequest(owner, repo, number);
    }
}
