package me.donnie.github.ui.repo.commit;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.entity.Commit;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.data.repository.CommitRepository;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommitPresenter implements CommitContract.Presenter {

    private static final String TAG = CommitPresenter.class.getSimpleName();

    @Inject
    Prefser prefser;

    private CommitContract.View view;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final CommitRepository mRepository;

    private int currentPage = 1;

    @Inject
    public CommitPresenter(CommitRepository repository) {
        mRepository = repository;
    }

    @Override
    public void attachView(CommitContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    public void refresh(Repo repo) {
        this.currentPage = 1;
        loadCommits(repo.getOwner().getLogin(), repo.getName(), repo.getDefault_branch(), currentPage);
    }

    public void loadmore(Repo repo) {
        this.currentPage++;
        loadCommits(repo.getOwner().getLogin(), repo.getName(), repo.getDefault_branch(), currentPage);
    }

    @Override
    public void loadCommits(String owner, String repo, String branch, int page) {
        mDisposable.add(mRepository.getCommits(owner, repo, branch, page)
        .compose(new SchedulerTransformer<List<Commit>>())
        .subscribe(new Consumer<List<Commit>>() {
            @Override
            public void accept(List<Commit> commits) throws Exception {
                onCommitsResponse(commits);
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
    public void onCommitsResponse(List<Commit> commits) {
        view.loadCommitsSuccess(commits, currentPage);
    }
}
