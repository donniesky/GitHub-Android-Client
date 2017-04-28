package me.donnie.github.ui.trending;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.entity.Trending;
import me.donnie.github.data.repository.TrendingRepository;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */

public class TrendingPresenter implements TrendingContract.Presenter {

    private static final String TAG  = TrendingPresenter.class.getSimpleName();

    @Inject
    Prefser prefser;

    TrendingContract.View view;

    private int currentPage = 1;

    private final TrendingRepository mRepository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public TrendingPresenter(TrendingRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(TrendingContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    @Override
    public void listTrending() {
        mDisposable.add(mRepository.listTrending()
        .compose(new SchedulerTransformer<List<Trending>>())
        .subscribe(new Consumer<List<Trending>>() {
            @Override
            public void accept(List<Trending> trendings) throws Exception {
                onTrendingResponse(trendings);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                FirebaseCrash.report(throwable);
                Timber.tag(TAG).e(throwable.getMessage());
                view.onNetWorkError("");
            }
        }));
    }

    @Override
    public void listTrending(String language) {
        mDisposable.add(mRepository.listTrending(language)
        .compose(new SchedulerTransformer<List<Trending>>())
        .subscribe(new Consumer<List<Trending>>() {
            @Override
            public void accept(List<Trending> trendings) throws Exception {
                onTrendingResponse(trendings);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                FirebaseCrash.report(throwable);
                Timber.tag(TAG).e(throwable.getMessage());
                view.onNetWorkError("");
            }
        }));
    }

    @Override
    public void listTrending(Map<String, String> params) {
        mDisposable.add(mRepository.listTrending(params)
        .compose(new SchedulerTransformer<List<Trending>>())
        .subscribe(new Consumer<List<Trending>>() {
            @Override
            public void accept(List<Trending> trendings) throws Exception {
                onTrendingResponse(trendings);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                FirebaseCrash.report(throwable);
                Timber.tag(TAG).e(throwable.getMessage());
                view.onNetWorkError("");
            }
        }));
    }

    @Override
    public void onTrendingResponse(List<Trending> trendings) {
        if (trendings.size() > 0) {
            view.showTrendingList(trendings, currentPage);
        }
    }
}
