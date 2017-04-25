package me.donnie.github.data.source.remote;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.data.entity.MarkDown;
import me.donnie.github.data.entity.ReadMe;
import me.donnie.github.data.source.remote.api.ReadmeApi;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class RemoteReadmeSource {

    private final ReadmeApi mReadmeApi;

    @Inject
    public RemoteReadmeSource(@Normal ReadmeApi readmeApi) {
        mReadmeApi = readmeApi;
    }

    public Observable<String> readme(String owner, String repo) {
        return mReadmeApi.readme(owner, repo);
    }

    public Observable<ReadMe> readmes(String owner, String repo) {
        return mReadmeApi.readmes(owner, repo);
    }

    public Observable<String> convertReadmeToHtml(MarkDown markDown) {
        return mReadmeApi.convertReadmeToHtml(markDown);
    }

    public Observable<String> getReadmeHtml(String url) {
        return mReadmeApi.getReadmeHtml(url);
    }
}
