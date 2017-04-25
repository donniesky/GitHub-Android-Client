package me.donnie.github.data.source.remote;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.data.entity.MarkDown;
import me.donnie.github.data.entity.ReadMe;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.Subscription;
import me.donnie.github.data.source.remote.api.RepoApi;
import retrofit2.Response;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class RemoteRepoSource {

    private final RepoApi mRepoApi;

    @Inject
    public RemoteRepoSource(@Normal RepoApi repoApi) {
        mRepoApi = repoApi;
    }

    public Observable<Repo> getRepoByFullUrl(String url) {
        return mRepoApi.getRepoByFullUrl(url);
    }

    public Observable<Repo> getRepo(String repoId) {
        return mRepoApi.getRepo(repoId);
    }

    public Observable<Repo> getRepo(String login, String repoId) {
        return mRepoApi.getRepo(login, repoId);
    }

    public Observable<String> readme(String owner, String repo) {
        return mRepoApi.readme(owner, repo);
    }

    public Observable<ReadMe> readmes(String owner, String repo) {
        return mRepoApi.readmes(owner, repo);
    }

    public Observable<String> convertReadmeToHtml(MarkDown markDown) {
        return mRepoApi.convertReadmeToHtml(markDown);
    }

    public Observable<String> getReadmeHtml(String url) {
        return mRepoApi.getReadmeHtml(url);
    }

    public Observable<Response<Boolean>> checkStaring(String login, String repo) {
        return mRepoApi.checkStarring(login, repo);
    }

    public Observable<Response<Boolean>> starRepo(String login, String repoId) {
        return mRepoApi.starRepo(login, repoId);
    }

    public Observable<Response<Boolean>> unstarRepo(String login, String repoId) {
        return mRepoApi.unstarRepo(login, repoId);
    }

    public Observable<Repo> forkRepo(String login, String repoId) {
        return mRepoApi.forkRepo(login, repoId);
    }

    public Observable<Subscription> checkWatching(String owner, String repo) {
        return mRepoApi.isWatchingRepo(owner, repo);
    }

    public Observable<Response<Boolean>> watchRepo(String owner, String repo) {
        return mRepoApi.watchRepo(owner, repo);
    }

    public Observable<Response<Boolean>> unwatchRepo(String owner, String repo) {
        return mRepoApi.unwatchRepo(owner, repo);
    }
}
