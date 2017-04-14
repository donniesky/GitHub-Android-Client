package me.donnie.github.data.source.remote;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Trending;
import me.donnie.github.data.source.remote.api.ExploreApi;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class RemoteTrendingSource {

    private final ExploreApi mExploreApi;

    @Inject
    public RemoteTrendingSource(@me.donnie.github.common.injection.qualifier.Trending ExploreApi exploreApi) {
        mExploreApi = exploreApi;
    }

    public Observable<List<Trending>> listTrending() {
        return mExploreApi.listTrending();
    }

    public Observable<List<Trending>> listTrending(String language) {
        return mExploreApi.listTrending(language);
    }

    public Observable<List<Trending>> listTrending(Map<String, String> params) {
        return mExploreApi.listTrending(params);
    }
}
