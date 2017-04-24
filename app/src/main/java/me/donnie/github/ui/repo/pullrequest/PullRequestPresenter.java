package me.donnie.github.ui.repo.pullrequest;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.entity.PullRequest;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.StateType;
import me.donnie.github.data.repository.PullRequestRepository;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/20.
 * @description
 */

public class PullRequestPresenter implements PullRequestContract.Presenter {

    private static final String TAG = PullRequestPresenter.class.getSimpleName();

    @Inject
    Prefser prefser;

    private PullRequestContract.View view;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final PullRequestRepository mRepository;

    private int currentPage = 1;

    @Inject
    public PullRequestPresenter(PullRequestRepository repository) {
        mRepository = repository;
    }

    @Override
    public void attachView(PullRequestContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    public void refresh(Repo repo) {
        this.currentPage = 1;
        loadPullRequests(repo.getOwner().getLogin(), repo.getName(), StateType.open.name(), currentPage);
    }

    public void loadmore(Repo repo) {
        this.currentPage++;
        loadPullRequests(repo.getOwner().getLogin(), repo.getName(), StateType.open.name(), currentPage);
    }

    @Override
    public void loadPullRequests(String owner, String repo, String state, int page) {
        mDisposable.add(mRepository.getPullRequests(owner, repo, state, page)
        .compose(new SchedulerTransformer<List<PullRequest>>())
        .subscribe(new Consumer<List<PullRequest>>() {
            @Override
            public void accept(List<PullRequest> pullRequests) throws Exception {
                onPullRequestResponse(pullRequests);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                FirebaseCrash.report(throwable);
                Timber.tag(TAG).e(throwable.getMessage());
                view.onError();
            }
        }));
    }

    @Override
    public void onPullRequestResponse(List<PullRequest> pullRequests) {
        view.loadPullRequestsSuccess(pullRequests, currentPage);
    }
}
