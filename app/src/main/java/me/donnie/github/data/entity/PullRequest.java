package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class PullRequest implements Parcelable {

    private String url;

    private long id;

    private String html_url;

    private String diff_url;

    private String patch_url;

    private String issue_url;

    private int number;

    private StateType state;

    private boolean locked;

    private String title;

    private User user;

    private String body;

    private Date created_at;

    private Date updated_at;

    private Date closed_at;

    private Date merged_at;

    private String merge_commit_sha;

    private Assignee assignee;

    private List<Assignee> assignees;

    private String milestone;

    private String commits_url;

    private String review_comments_url;

    private String review_comment_url;

    private String comments_url;

    private String statues_url;

    private Head head;

    private Head base;

    private boolean merged;

    private String mergeable_state;

    private int comments;
    private int review_comments;
    private boolean maintainer_can_modify;
    private int commits;
    private int additions;
    private int deletions;
    private int changed_files;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public void setDiff_url(String diff_url) {
        this.diff_url = diff_url;
    }

    public String getPatch_url() {
        return patch_url;
    }

    public void setPatch_url(String patch_url) {
        this.patch_url = patch_url;
    }

    public String getIssue_url() {
        return issue_url;
    }

    public void setIssue_url(String issue_url) {
        this.issue_url = issue_url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Date closed_at) {
        this.closed_at = closed_at;
    }

    public Date getMerged_at() {
        return merged_at;
    }

    public void setMerged_at(Date merged_at) {
        this.merged_at = merged_at;
    }

    public String getMerge_commit_sha() {
        return merge_commit_sha;
    }

    public void setMerge_commit_sha(String merge_commit_sha) {
        this.merge_commit_sha = merge_commit_sha;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getReview_comments_url() {
        return review_comments_url;
    }

    public void setReview_comments_url(String review_comments_url) {
        this.review_comments_url = review_comments_url;
    }

    public String getReview_comment_url() {
        return review_comment_url;
    }

    public void setReview_comment_url(String review_comment_url) {
        this.review_comment_url = review_comment_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getStatues_url() {
        return statues_url;
    }

    public void setStatues_url(String statues_url) {
        this.statues_url = statues_url;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Head getBase() {
        return base;
    }

    public void setBase(Head base) {
        this.base = base;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public String getMergeable_state() {
        return mergeable_state;
    }

    public void setMergeable_state(String mergeable_state) {
        this.mergeable_state = mergeable_state;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getReview_comments() {
        return review_comments;
    }

    public void setReview_comments(int review_comments) {
        this.review_comments = review_comments;
    }

    public boolean isMaintainer_can_modify() {
        return maintainer_can_modify;
    }

    public void setMaintainer_can_modify(boolean maintainer_can_modify) {
        this.maintainer_can_modify = maintainer_can_modify;
    }

    public int getCommits() {
        return commits;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    public int getAdditions() {
        return additions;
    }

    public void setAdditions(int additions) {
        this.additions = additions;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public int getChanged_files() {
        return changed_files;
    }

    public void setChanged_files(int changed_files) {
        this.changed_files = changed_files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeLong(this.id);
        dest.writeString(this.html_url);
        dest.writeString(this.diff_url);
        dest.writeString(this.patch_url);
        dest.writeString(this.issue_url);
        dest.writeInt(this.number);
        dest.writeInt(this.state == null ? -1 : this.state.ordinal());
        dest.writeByte(this.locked ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.body);
        dest.writeLong(this.created_at != null ? this.created_at.getTime() : -1);
        dest.writeLong(this.updated_at != null ? this.updated_at.getTime() : -1);
        dest.writeLong(this.closed_at != null ? this.closed_at.getTime() : -1);
        dest.writeLong(this.merged_at != null ? this.merged_at.getTime() : -1);
        dest.writeString(this.merge_commit_sha);
        dest.writeParcelable(this.assignee, flags);
        dest.writeTypedList(this.assignees);
        dest.writeString(this.milestone);
        dest.writeString(this.commits_url);
        dest.writeString(this.review_comments_url);
        dest.writeString(this.review_comment_url);
        dest.writeString(this.comments_url);
        dest.writeString(this.statues_url);
        dest.writeParcelable(this.head, flags);
        dest.writeParcelable(this.base, flags);
        dest.writeByte(this.merged ? (byte) 1 : (byte) 0);
        dest.writeString(this.mergeable_state);
        dest.writeInt(this.comments);
        dest.writeInt(this.review_comments);
        dest.writeByte(this.maintainer_can_modify ? (byte) 1 : (byte) 0);
        dest.writeInt(this.commits);
        dest.writeInt(this.additions);
        dest.writeInt(this.deletions);
        dest.writeInt(this.changed_files);
    }

    public PullRequest() {
    }

    protected PullRequest(Parcel in) {
        this.url = in.readString();
        this.id = in.readLong();
        this.html_url = in.readString();
        this.diff_url = in.readString();
        this.patch_url = in.readString();
        this.issue_url = in.readString();
        this.number = in.readInt();
        int tmpState = in.readInt();
        this.state = tmpState == -1 ? null : StateType.values()[tmpState];
        this.locked = in.readByte() != 0;
        this.title = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.body = in.readString();
        long tmpCreated_at = in.readLong();
        this.created_at = tmpCreated_at == -1 ? null : new Date(tmpCreated_at);
        long tmpUpdated_at = in.readLong();
        this.updated_at = tmpUpdated_at == -1 ? null : new Date(tmpUpdated_at);
        long tmpClosed_at = in.readLong();
        this.closed_at = tmpClosed_at == -1 ? null : new Date(tmpClosed_at);
        long tmpMerged_at = in.readLong();
        this.merged_at = tmpMerged_at == -1 ? null : new Date(tmpMerged_at);
        this.merge_commit_sha = in.readString();
        this.assignee = in.readParcelable(Assignee.class.getClassLoader());
        this.assignees = in.createTypedArrayList(Assignee.CREATOR);
        this.milestone = in.readString();
        this.commits_url = in.readString();
        this.review_comments_url = in.readString();
        this.review_comment_url = in.readString();
        this.comments_url = in.readString();
        this.statues_url = in.readString();
        this.head = in.readParcelable(Head.class.getClassLoader());
        this.base = in.readParcelable(Head.class.getClassLoader());
        this.merged = in.readByte() != 0;
        this.mergeable_state = in.readString();
        this.comments = in.readInt();
        this.review_comments = in.readInt();
        this.maintainer_can_modify = in.readByte() != 0;
        this.commits = in.readInt();
        this.additions = in.readInt();
        this.deletions = in.readInt();
        this.changed_files = in.readInt();
    }

    public static final Parcelable.Creator<PullRequest> CREATOR = new Parcelable.Creator<PullRequest>() {
        @Override
        public PullRequest createFromParcel(Parcel source) {
            return new PullRequest(source);
        }

        @Override
        public PullRequest[] newArray(int size) {
            return new PullRequest[size];
        }
    };
}
