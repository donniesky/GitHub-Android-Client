package me.donnie.github.ui.trending;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.scope.FragmentScope;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */
@Module
public class TrendingModule {

    @Provides
    @FragmentScope
    TrendingContract.Navigator provideTrendingNavigator(TrendingNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    TrendingContract.Presenter provideTrendingPresenter(TrendingPresenter presenter) {
        return presenter;
    }

}
