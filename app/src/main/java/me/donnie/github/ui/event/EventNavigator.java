package me.donnie.github.ui.event;

import javax.inject.Inject;

import me.donnie.github.data.entity.Event;
import me.donnie.github.ui.main.MainContract;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class EventNavigator implements EventContract.Navigator {

    private final MainContract.Navigator mNavigator;

    @Inject
    public EventNavigator(MainContract.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void navigateToRepo(Event event) {
        mNavigator.navigateToRepo(event);
    }
}
