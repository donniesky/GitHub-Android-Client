package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * @author donnieSky
 * @created_at 2017/4/13.
 * @description
 */

public class Issue implements Parcelable {

    private String url;

    private String repository_url;

    private String labels_url;

    private String comments_url;

    private String events_url;

    private String html_url;

    private long id;

    private int number;

    private String title;

    private User user;

    private List<Label> labels;

    private StateType state;

    private boolean locked;

    private Assignee assignee;

    private List<Assignee> assignees;

    private MileStone milestone;

    private int comments;

    private Date created_at;

    private Date updated_at;

    private Date closed_at;

    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public MileStone getMilestone() {
        return milestone;
    }

    public void setMilestone(MileStone milestone) {
        this.milestone = milestone;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Issue() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.repository_url);
        dest.writeString(this.labels_url);
        dest.writeString(this.comments_url);
        dest.writeString(this.events_url);
        dest.writeString(this.html_url);
        dest.writeLong(this.id);
        dest.writeInt(this.number);
        dest.writeString(this.title);
        dest.writeParcelable(this.user, flags);
        dest.writeTypedList(this.labels);
        dest.writeInt(this.state == null ? -1 : this.state.ordinal());
        dest.writeByte(this.locked ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.assignee, flags);
        dest.writeTypedList(this.assignees);
        dest.writeParcelable(this.milestone, flags);
        dest.writeInt(this.comments);
        dest.writeLong(this.created_at != null ? this.created_at.getTime() : -1);
        dest.writeLong(this.updated_at != null ? this.updated_at.getTime() : -1);
        dest.writeLong(this.closed_at != null ? this.closed_at.getTime() : -1);
        dest.writeString(this.body);
    }

    protected Issue(Parcel in) {
        this.url = in.readString();
        this.repository_url = in.readString();
        this.labels_url = in.readString();
        this.comments_url = in.readString();
        this.events_url = in.readString();
        this.html_url = in.readString();
        this.id = in.readLong();
        this.number = in.readInt();
        this.title = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.labels = in.createTypedArrayList(Label.CREATOR);
        int tmpState = in.readInt();
        this.state = tmpState == -1 ? null : StateType.values()[tmpState];
        this.locked = in.readByte() != 0;
        this.assignee = in.readParcelable(Assignee.class.getClassLoader());
        this.assignees = in.createTypedArrayList(Assignee.CREATOR);
        this.milestone = in.readParcelable(MileStone.class.getClassLoader());
        this.comments = in.readInt();
        long tmpCreated_at = in.readLong();
        this.created_at = tmpCreated_at == -1 ? null : new Date(tmpCreated_at);
        long tmpUpdated_at = in.readLong();
        this.updated_at = tmpUpdated_at == -1 ? null : new Date(tmpUpdated_at);
        long tmpClosed_at = in.readLong();
        this.closed_at = tmpClosed_at == -1 ? null : new Date(tmpClosed_at);
        this.body = in.readString();
    }

    public static final Creator<Issue> CREATOR = new Creator<Issue>() {
        @Override
        public Issue createFromParcel(Parcel source) {
            return new Issue(source);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };
}
