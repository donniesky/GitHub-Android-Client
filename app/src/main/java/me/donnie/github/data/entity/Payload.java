package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class Payload implements Parcelable {

    private long push_id;

    private int size;

    private int distinct_size;

    private String ref;

    private String head;

    private String before;

    private List<CommitModel> commits;

    private String action;

    private PullRequest pull_request;

    private Forkee forkee;

    private Issue issue;

    private Comment comment;

    private List<Page> pages;

    @SerializedName("public")
    private boolean pub;

    private Date created_at;

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public PullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequest pull_request) {
        this.pull_request = pull_request;
    }

    public long getPush_id() {
        return push_id;
    }

    public void setPush_id(long push_id) {
        this.push_id = push_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDistinct_size() {
        return distinct_size;
    }

    public void setDistinct_size(int distinct_size) {
        this.distinct_size = distinct_size;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<CommitModel> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitModel> commits) {
        this.commits = commits;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Forkee getForkee() {
        return forkee;
    }

    public void setForkee(Forkee forkee) {
        this.forkee = forkee;
    }

    public boolean isPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Payload() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.push_id);
        dest.writeInt(this.size);
        dest.writeInt(this.distinct_size);
        dest.writeString(this.ref);
        dest.writeString(this.head);
        dest.writeString(this.before);
        dest.writeTypedList(this.commits);
        dest.writeString(this.action);
        dest.writeParcelable(this.pull_request, flags);
        dest.writeParcelable(this.forkee, flags);
        dest.writeParcelable(this.issue, flags);
        dest.writeParcelable(this.comment, flags);
        dest.writeTypedList(this.pages);
        dest.writeByte(this.pub ? (byte) 1 : (byte) 0);
        dest.writeLong(this.created_at != null ? this.created_at.getTime() : -1);
    }

    protected Payload(Parcel in) {
        this.push_id = in.readLong();
        this.size = in.readInt();
        this.distinct_size = in.readInt();
        this.ref = in.readString();
        this.head = in.readString();
        this.before = in.readString();
        this.commits = in.createTypedArrayList(CommitModel.CREATOR);
        this.action = in.readString();
        this.pull_request = in.readParcelable(PullRequest.class.getClassLoader());
        this.forkee = in.readParcelable(Forkee.class.getClassLoader());
        this.issue = in.readParcelable(Issue.class.getClassLoader());
        this.comment = in.readParcelable(Comment.class.getClassLoader());
        this.pages = in.createTypedArrayList(Page.CREATOR);
        this.pub = in.readByte() != 0;
        long tmpCreated_at = in.readLong();
        this.created_at = tmpCreated_at == -1 ? null : new Date(tmpCreated_at);
    }

    public static final Creator<Payload> CREATOR = new Creator<Payload>() {
        @Override
        public Payload createFromParcel(Parcel source) {
            return new Payload(source);
        }

        @Override
        public Payload[] newArray(int size) {
            return new Payload[size];
        }
    };
}
