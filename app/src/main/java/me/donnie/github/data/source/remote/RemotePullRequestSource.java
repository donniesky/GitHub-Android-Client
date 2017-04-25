package me.donnie.github.data.source.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.data.entity.PullRequest;
import me.donnie.github.data.source.remote.api.PullRequestApi;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */

public class RemotePullRequestSource {

    private final PullRequestApi mApi;

    @Inject
    public RemotePullRequestSource(@Normal PullRequestApi api) {
        mApi = api;
    }

    public Observable<List<PullRequest>> getPullRequests(String owner, String repo,
                                                         String state, int page) {
        return mApi.getPullRequests(owner, repo, state, page);
    }

    public Observable<PullRequest> getPullRequest(String owner, String repo, int number) {
        return mApi.getPullRequest(owner, repo, number);
    }
}
