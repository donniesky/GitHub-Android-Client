package me.donnie.github.common.injection.module;

import dagger.Module;
import dagger.Provides;
import me.donnie.github.common.injection.qualifier.Auth;
import me.donnie.github.common.injection.qualifier.Login;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.common.injection.qualifier.Trending;
import me.donnie.github.data.source.remote.api.AuthApi;
import me.donnie.github.data.source.remote.api.CommitApi;
import me.donnie.github.data.source.remote.api.ContributorApi;
import me.donnie.github.data.source.remote.api.ExploreApi;
import me.donnie.github.data.source.remote.api.IssueApi;
import me.donnie.github.data.source.remote.api.PullRequestApi;
import me.donnie.github.data.source.remote.api.ReadmeApi;
import me.donnie.github.data.source.remote.api.RepoApi;
import me.donnie.github.data.source.remote.api.UserApi;
import retrofit2.Retrofit;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
@Module
public class ApiModule {

    @Provides
    @Auth
    AuthApi provideUserAuthApi(@Auth final Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }

    @Provides
    @Login
    UserApi provideUserLoginApi(@Login final Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Provides
    @Normal
    UserApi provideUserNormalApi(@Normal final Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Provides
    @Trending
    ExploreApi provideExploreApi(@Trending final Retrofit retrofit) {
        return retrofit.create(ExploreApi.class);
    }

    @Provides
    @Normal
    RepoApi provideRepoApi(@Normal final Retrofit retrofit) {
        return retrofit.create(RepoApi.class);
    }

    @Provides
    @Normal
    ReadmeApi provideReadmeApi(@Normal Retrofit retrofit) {
        return retrofit.create(ReadmeApi.class);
    }

    @Provides
    @Normal
    IssueApi provideIssueApi(@Normal final Retrofit retrofit) {
        return retrofit.create(IssueApi.class);
    }

    @Provides
    @Normal
    PullRequestApi providePullRequestApi(@Normal final Retrofit retrofit) {
        return retrofit.create(PullRequestApi.class);
    }

    @Provides
    @Normal
    CommitApi provideCommitApi(@Normal final Retrofit retrofit) {
        return retrofit.create(CommitApi.class);
    }

    @Provides
    @Normal
    ContributorApi provideContributorApi(@Normal final Retrofit retrofit) {
        return retrofit.create(ContributorApi.class);
    }

}
