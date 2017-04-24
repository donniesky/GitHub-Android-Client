package me.donnie.github.ui.repo.readme;

import android.text.TextUtils;
import android.util.Base64;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.firebase.crash.FirebaseCrash;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.entity.MarkDown;
import me.donnie.github.data.repository.ReadmeRepository;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/17.
 * @description
 */

public class ReadmePresenter implements ReadmeContract.Presenter {

    private static final String TAG  = ReadmePresenter.class.getSimpleName();

    private ReadmeContract.View view;

    @Inject
    Prefser prefser;

    private final ReadmeRepository mRepository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public ReadmePresenter(ReadmeRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(ReadmeContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    @Override
    public void loadReadme(String owner, String repo) {
        mDisposable.add(mRepository.readme(owner, repo)
        .compose(new SchedulerTransformer<String>())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String content) throws Exception {
                if (!TextUtils.isEmpty(content)) {
                    view.loadReadmeSuccess(content);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                FirebaseCrash.report(throwable);
                Timber.tag(TAG).e(throwable.getMessage());
                view.onNetWorkError();
            }
        }));

        /*mDisposable.add(mRepository.readmes(owner, repo)
        .compose(new SchedulerTransformer<ReadMe>())
        .subscribe(new Consumer<ReadMe>() {
            @Override
            public void accept(ReadMe readMe) throws Exception {
                if (!TextUtils.isEmpty(readMe.getContent())) {
                    onReadmeReponse(readMe.getContent());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Timber.tag(TAG).e(throwable.getMessage());
            }
        }));*/
    }

    @Override
    public void onReadmeReponse(String content) {
        Timber.tag(TAG).d(content);
        view.loadReadmeSuccess(content);
        byte[] data = Base64.decode(content, Base64.DEFAULT);
        String s = "";
        try {
            s = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            FirebaseCrash.report(e);
            Timber.tag(TAG).e(e.getMessage());
            e.printStackTrace();
        }

        if (!TextUtils.isEmpty(s)) {

            MarkDown markDown = new MarkDown();
            markDown.setText(s);

            mDisposable.add(mRepository.convertReadmeToHtml(markDown)
            .compose(new SchedulerTransformer<String>())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    Timber.tag(TAG).d(s);
                    view.loadReadmeSuccess(s);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    FirebaseCrash.report(throwable);
                    Timber.tag(TAG).e(throwable.getMessage());
                }
            }));
        }
    }
}
