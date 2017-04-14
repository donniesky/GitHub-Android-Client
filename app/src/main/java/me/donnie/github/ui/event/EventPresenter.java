package me.donnie.github.ui.event;

import com.github.pwittchen.prefser.library.Prefser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import me.donnie.github.common.utils.RxUtils;
import me.donnie.github.common.utils.SchedulerTransformer;
import me.donnie.github.data.UserRepository;
import me.donnie.github.data.entity.Event;
import timber.log.Timber;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class EventPresenter implements EventContract.Presenter {

    private static final String TAG = EventPresenter.class.getSimpleName();

    private EventContract.View view;

    @Inject
    Prefser prefser;

    private int currentPage = 1;

    private String username;

    private final UserRepository mRepository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public EventPresenter(UserRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(EventContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        RxUtils.dispose(mDisposable);
    }

    public void refresh() {
        username = prefser.get("username",String.class, "");
        currentPage = 1;
        receiveEvents(username, currentPage);
    }

    public void loadmore() {
        currentPage++;
        receiveEvents(username, currentPage);
    }

    @Override
    public void receiveEvents(String username, int page) {
        mDisposable.add(mRepository.receiveEvents(username, page)
        .compose(new SchedulerTransformer<List<Event>>())
        .subscribe(new Consumer<List<Event>>() {
            @Override
            public void accept(List<Event> eventResult) throws Exception {
                onEventResponse(eventResult);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Timber.tag(TAG).e(throwable.getMessage());
                view.onEventError(throwable.getMessage());
            }
        }));
    }

    @Override
    public void onEventResponse(List<Event> events) {
        if (events.size() > 0) {
            Timber.tag(TAG).d("events size: "+events.size());
            view.showEvents(events, currentPage);
        } else {
            view.dataIsEmpty(currentPage>1);
            Timber.tag(TAG).e("events size null !!!");
        }
    }
}
