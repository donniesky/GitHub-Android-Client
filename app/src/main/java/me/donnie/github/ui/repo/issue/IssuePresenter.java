package me.donnie.github.ui.repo.issue;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.entity.Issue;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.entity.StateType;
import me.donnie.github.data.repository.IssueRepository;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class IssuePresenter implements IssueContract.Presenter {

    private static final String TAG = IssuePresenter.class.getSimpleName();

    @Inject
    Prefser prefser;

    private int currentPage = 1;

    private IssueContract.View view;

    private final IssueRepository mRepository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public IssuePresenter(IssueRepository repository) {
        mRepository = repository;
    }

    @Override
    public void attachView(IssueContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    public void refresh(Repo repo) {
        currentPage = 1;
        loadIssues(repo.getOwner().getLogin(), repo.getName(), StateType.open.name(), currentPage);
    }

    public void loadmore(Repo repo) {
        currentPage++;
        loadIssues(repo.getOwner().getLogin(), repo.getName(), StateType.open.name(), currentPage);
    }

    @Override
    public void loadIssues(String owner, String repo, String state, int page) {
        mDisposable.add(mRepository.issues(owner, repo, state, page)
        .compose(new SchedulerTransformer<List<Issue>>())
        .subscribe(new Consumer<List<Issue>>() {
            @Override
            public void accept(List<Issue> issues) throws Exception {
                onIssuesResponse(issues);
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
    public void onIssuesResponse(List<Issue> issues) {
        if (issues.size() > 0) {
            view.loadIssueSuccess(issues, currentPage);
        }
    }
}
