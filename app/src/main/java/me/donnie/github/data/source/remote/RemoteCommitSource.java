package me.donnie.github.data.source.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.common.injection.qualifier.Normal;
import me.donnie.github.data.entity.Comment;
import me.donnie.github.data.entity.CommentContent;
import me.donnie.github.data.entity.Commit;
import me.donnie.github.data.source.remote.api.CommitApi;
import retrofit2.Response;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class RemoteCommitSource {

    private final CommitApi mCommitApi;

    @Inject
    public RemoteCommitSource(@Normal CommitApi commitApi) {
        mCommitApi = commitApi;
    }

    public Observable<List<Commit>> getCommits(String owner, String repo,
                                        String branch, int page) {
        return mCommitApi.getCommits(owner, repo, branch, page);
    }

    public Observable<Commit> getCommit(String owner, String repo, String sha) {
        return mCommitApi.getCommit(owner, repo, sha);
    }

    public Observable<List<Comment>> getCommitComments(String owner, String repo,
                                                String sha, int page) {
        return mCommitApi.getCommitComments(owner, repo, sha, page);
    }

    public Observable<Comment> postCommitComment(String owner, String repo,
                                          String sha, CommentContent body) {
        return mCommitApi.postCommitComment(owner, repo, sha, body);
    }

    public Observable<Comment> editCommitComment(String owner, String repo,
                                          long id, CommentContent body) {
        return mCommitApi.editCommitComment(owner, repo, id, body);
    }

    public Observable<Response<Boolean>> deleteComment(String owner, String repo, long id) {
        return mCommitApi.deleteComment(owner, repo, id);
    }
}
