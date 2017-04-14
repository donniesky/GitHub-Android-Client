package me.donnie.github.ui.event;

import java.util.List;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.Event;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public interface EventContract {

    interface Navigator extends BaseNavigator {
        void navigateToRepo(Event event);
    }

    interface View extends BaseView {
        void dataIsEmpty(boolean isRefresh);
        void showEvents(List<Event> events, int page);
        void onEventError(String description);
    }

    interface Presenter extends BasePresenter<View> {

        void receiveEvents(String username, int page);

        void onEventResponse(List<Event> events);

    }

}
