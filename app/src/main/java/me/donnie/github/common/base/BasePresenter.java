package me.donnie.github.common.base;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
