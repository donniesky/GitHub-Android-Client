package me.donnie.github.data.source.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.data.entity.User;
import me.donnie.github.data.source.remote.api.ContributorApi;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */

public class RemoteContributorSource {

    private final ContributorApi mApi;

    @Inject
    public RemoteContributorSource(@Normal ContributorApi api) {
        mApi = api;
    }

    public Observable<List<User>> getContributors(String owner, String repo, int page) {
        return mApi.getContributors(owner, repo, page);
    }
}
