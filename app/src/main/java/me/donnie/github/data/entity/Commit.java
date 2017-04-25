package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class Commit implements Parcelable {

    private String sha;

    private CommitModel commit;

    private String url;

    private String html_url;

    private String comments_url;

    private User author;

    private User committer;

    private List<Commit> parents;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public CommitModel getCommit() {
        return commit;
    }

    public void setCommit(CommitModel commit) {
        this.commit = commit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
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

    public List<Commit> getParents() {
        return parents;
    }

    public void setParents(List<Commit> parents) {
        this.parents = parents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sha);
        dest.writeParcelable(this.commit, flags);
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.comments_url);
        dest.writeParcelable(this.author, flags);
        dest.writeParcelable(this.committer, flags);
        dest.writeList(this.parents);
    }

    public Commit() {
    }

    protected Commit(Parcel in) {
        this.sha = in.readString();
        this.commit = in.readParcelable(CommitModel.class.getClassLoader());
        this.url = in.readString();
        this.html_url = in.readString();
        this.comments_url = in.readString();
        this.author = in.readParcelable(User.class.getClassLoader());
        this.committer = in.readParcelable(User.class.getClassLoader());
        this.parents = new ArrayList<Commit>();
        in.readList(this.parents, Commit.class.getClassLoader());
    }

    public static final Parcelable.Creator<Commit> CREATOR = new Parcelable.Creator<Commit>() {
        @Override
        public Commit createFromParcel(Parcel source) {
            return new Commit(source);
        }

        @Override
        public Commit[] newArray(int size) {
            return new Commit[size];
        }
    };
}
