package me.donnie.github.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.donnie.github.data.entity.Comment;
import me.donnie.github.data.entity.CommentContent;
import me.donnie.github.data.entity.Commit;
import me.donnie.github.data.source.remote.RemoteCommitSource;
import retrofit2.Response;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommitRepository {

    private final RemoteCommitSource mRemoteCommitSource;

    @Inject
    public CommitRepository(RemoteCommitSource remoteCommitSource) {
        mRemoteCommitSource = remoteCommitSource;
    }

    public Observable<List<Commit>> getCommits(String owner, String repo,
                                        String branch, int page) {
        return mRemoteCommitSource.getCommits(owner, repo, branch, page);
    }

    public Observable<Commit> getCommit(String owner, String repo, String sha) {
        return mRemoteCommitSource.getCommit(owner, repo, sha);
    }

    public Observable<List<Comment>> getCommitComments(String owner, String repo,
                                                String sha, int page) {
        return mRemoteCommitSource.getCommitComments(owner, repo, sha, page);
    }

    public Observable<Comment> postCommitComment(String owner, String repo,
                                          String sha, CommentContent body) {
        return mRemoteCommitSource.postCommitComment(owner, repo, sha, body);
    }

    public Observable<Comment> editCommitComment(String owner, String repo,
                                          long id, CommentContent body) {
        return mRemoteCommitSource.editCommitComment(owner, repo, id, body);
    }

    public Observable<Response<Boolean>> deleteComment(String owner, String repo, long id) {
        return mRemoteCommitSource.deleteComment(owner, repo, id);
    }
}
