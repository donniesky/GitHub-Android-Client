package me.donnie.github.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.MarkDown;
import me.donnie.github.data.entity.ReadMe;
import me.donnie.github.data.source.remote.RemoteReadmeSource;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class ReadmeRepository {

    private final RemoteReadmeSource mRemoteReadmeSource;

    @Inject
    public ReadmeRepository(RemoteReadmeSource remoteReadmeSource) {
        mRemoteReadmeSource = remoteReadmeSource;
    }

    public Observable<String> readme(String owner, String repo) {
        return mRemoteReadmeSource.readme(owner, repo);
    }

    public Observable<ReadMe> readmes(String owner, String repo) {
        return mRemoteReadmeSource.readmes(owner, repo);
    }

    public Observable<String> convertReadmeToHtml(MarkDown markDown) {
        return mRemoteReadmeSource.convertReadmeToHtml(markDown);
    }

    public Observable<String> getReadmeHtml(String url) {
        return mRemoteReadmeSource.getReadmeHtml(url);
    }
}
