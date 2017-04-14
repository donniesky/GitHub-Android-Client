package me.donnie.github.ui.repo;

import com.github.pwittchen.prefser.library.Prefser;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.RepoRepository;
import me.donnie.github.data.entity.Repo;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class RepoPresenter implements RepoContract.Presenter {

    private static final String TAG = RepoPresenter.class.getSimpleName();

    @Inject
    Prefser prefser;

    private RepoContract.View view;

    private final RepoRepository mRepository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public RepoPresenter(final RepoRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(RepoContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    @Override
    public void getRepo(String login, String repoId) {
        mDisposable.add(mRepository.getRepo(login, repoId)
        .compose(new SchedulerTransformer<Repo>())
        .subscribe(new Consumer<Repo>() {
            @Override
            public void accept(Repo repo) throws Exception {
                view.getRepoSuccess(repo);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Timber.tag(TAG).e(throwable.getMessage());
            }
        }));
    }

    @Override
    public void getRepoByUrl(String url) {
        mDisposable.add(mRepository.getRepoByFullUrl(url)
        .compose(new SchedulerTransformer<Repo>())
        .subscribe(new Consumer<Repo>() {
            @Override
            public void accept(Repo repo) throws Exception {
                view.getRepoSuccess(repo);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Timber.tag(TAG).e(throwable.getMessage());
            }
        }));
    }
}
