package me.donnie.github.ui.repo.contributor;

import com.github.pwittchen.prefser.library.Prefser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.User;
import me.donnie.github.data.repository.ContributorRepository;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */

public class ContributorPresenter implements ContributorContract.Presenter {

    private static final String TAG = ContributorPresenter.class.getSimpleName();

    @Inject
    Prefser prefser;

    private ContributorContract.View view;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final ContributorRepository mRepository;

    private int currentPage = 1;

    @Inject
    public ContributorPresenter(ContributorRepository repository) {
        mRepository = repository;
    }

    @Override
    public void attachView(ContributorContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    public void refresh(Repo repo) {
        this.currentPage = 1;
        loadContributors(repo.getOwner().getLogin(), repo.getName(), currentPage);
    }

    public void loadmore(Repo repo) {
        this.currentPage++;
        loadContributors(repo.getOwner().getLogin(), repo.getName(), currentPage);
    }

    @Override
    public void loadContributors(String owner, String repo, int page) {
        mDisposable.add(mRepository.getContributors(owner, repo, page)
        .compose(new SchedulerTransformer<List<User>>())
        .subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) throws Exception {
                onContributorsResponse(users);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Timber.tag(TAG).e(throwable.getMessage());
                view.onError();
            }
        }));
    }

    @Override
    public void onContributorsResponse(List<User> users) {
        view.loadContributorsSuccess(users, currentPage);
    }
}
