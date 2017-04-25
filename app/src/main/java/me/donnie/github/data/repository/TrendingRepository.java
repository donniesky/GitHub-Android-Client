package me.donnie.github.data.repository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Trending;
import me.donnie.github.data.source.remote.RemoteTrendingSource;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class TrendingRepository {

    private final RemoteTrendingSource mRemoteTrendingSource;

    @Inject
    public TrendingRepository(RemoteTrendingSource remoteTrendingSource) {
        mRemoteTrendingSource = remoteTrendingSource;
    }

    public Observable<List<Trending>> listTrending() {
        return mRemoteTrendingSource.listTrending();
    }

    public Observable<List<Trending>> listTrending(String language) {
        return mRemoteTrendingSource.listTrending(language);
    }

    public Observable<List<Trending>> listTrending(Map<String, String> params) {
        return mRemoteTrendingSource.listTrending(params);
    }
}
