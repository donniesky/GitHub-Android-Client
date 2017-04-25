package me.donnie.github.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.User;
import me.donnie.github.data.source.remote.RemoteContributorSource;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */

public class ContributorRepository {

    private final RemoteContributorSource mRemoteContributorSource;

    @Inject
    public ContributorRepository(RemoteContributorSource remoteContributorSource) {
        mRemoteContributorSource = remoteContributorSource;
    }

    public Observable<List<User>> getContributors(String owner, String repo, int page) {
        return mRemoteContributorSource.getContributors(owner, repo, page);
    }
}
