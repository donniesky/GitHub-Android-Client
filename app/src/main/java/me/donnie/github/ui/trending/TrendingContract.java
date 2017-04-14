package me.donnie.github.ui.trending;

import java.util.List;
import java.util.Map;

import me.donnie.github.common.base.BaseNavigator;
import me.donnie.github.common.base.BasePresenter;
import me.donnie.github.common.base.BaseView;
import me.donnie.github.data.entity.Trending;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */

public interface TrendingContract {

    interface Navigator extends BaseNavigator {
        void navigateToRepo(Trending trending);
    }

    interface View extends BaseView {
        void showTrendingList(List<Trending> trendings, int page);
        void onNetWorkError(String description);
    }

    interface Presenter extends BasePresenter<View> {
        void listTrending();
        void listTrending(String language);
        void listTrending(Map<String, String> params);
        void onTrendingResponse(List<Trending> trendings);
    }

}
