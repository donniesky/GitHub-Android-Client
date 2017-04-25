package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommitModel implements Parcelable {

    private String sha;

    private User author;

    private User committer;

    private String message;

    private Tree tree;

    private String url;

    private int comment_count;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getCommitter() {
        return committer;
    }

    public void setCommitter(User committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public CommitModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sha);
        dest.writeParcelable(this.author, flags);
        dest.writeParcelable(this.committer, flags);
        dest.writeString(this.message);
        dest.writeParcelable(this.tree, flags);
        dest.writeString(this.url);
        dest.writeInt(this.comment_count);
    }

    protected CommitModel(Parcel in) {
        this.sha = in.readString();
        this.author = in.readParcelable(User.class.getClassLoader());
        this.committer = in.readParcelable(User.class.getClassLoader());
        this.message = in.readString();
        this.tree = in.readParcelable(Tree.class.getClassLoader());
        this.url = in.readString();
        this.comment_count = in.readInt();
    }

    public static final Creator<CommitModel> CREATOR = new Creator<CommitModel>() {
        @Override
        public CommitModel createFromParcel(Parcel source) {
            return new CommitModel(source);
        }

        @Override
        public CommitModel[] newArray(int size) {
            return new CommitModel[size];
        }
    };
}
