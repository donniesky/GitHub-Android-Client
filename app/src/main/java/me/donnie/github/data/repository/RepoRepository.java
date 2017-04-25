package me.donnie.github.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.MarkDown;
import me.donnie.github.data.entity.ReadMe;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.Subscription;
import me.donnie.github.data.source.remote.RemoteRepoSource;
import retrofit2.Response;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class RepoRepository {

    private final RemoteRepoSource mRemoteRepoSource;

    @Inject
    public RepoRepository(RemoteRepoSource remoteRepoSource) {
        mRemoteRepoSource = remoteRepoSource;
    }

    public Observable<Repo> getRepoByFullUrl(String url) {
        return mRemoteRepoSource.getRepoByFullUrl(url);
    }

    public Observable<Repo> getRepo(String repoId) {
        return mRemoteRepoSource.getRepo(repoId);
    }

    public Observable<Repo> getRepo(String login, String repoId) {
        return mRemoteRepoSource.getRepo(login, repoId);
    }

    public Observable<String> readme(String owner, String repo) {
        return mRemoteRepoSource.readme(owner, repo);
    }

    public Observable<ReadMe> readmes(String owner, String repo) {
        return mRemoteRepoSource.readmes(owner, repo);
    }

    public Observable<String> convertReadmeToHtml(MarkDown markDown) {
        return mRemoteRepoSource.convertReadmeToHtml(markDown);
    }

    public Observable<String> getReadmeHtml(String url) {
        return mRemoteRepoSource.getReadmeHtml(url);
    }

    public Observable<Response<Boolean>> checkStaring(String login, String repo) {
        return mRemoteRepoSource.checkStaring(login, repo);
    }

    public Observable<Response<Boolean>> starRepo(String login, String repoId) {
        return mRemoteRepoSource.starRepo(login, repoId);
    }

    public Observable<Response<Boolean>> unstarRepo(String login, String repoId) {
        return mRemoteRepoSource.unstarRepo(login, repoId);
    }

    public Observable<Repo> forkRepo(String login, String repoId) {
        return mRemoteRepoSource.forkRepo(login, repoId);
    }

    public Observable<Subscription> checkWatching(String owner, String repo) {
        return mRemoteRepoSource.checkWatching(owner, repo);
    }

    public Observable<Response<Boolean>> watchRepo(String owner, String repo) {
        return mRemoteRepoSource.watchRepo(owner, repo);
    }

    public Observable<Response<Boolean>> unwatchRepo(String owner, String repo) {
        return mRemoteRepoSource.unwatchRepo(owner, repo);
    }
}
