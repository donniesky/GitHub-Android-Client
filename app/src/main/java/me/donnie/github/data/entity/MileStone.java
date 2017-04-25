package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class MileStone implements Parcelable {

    private String html_url;

    private String title;

    private int number;

    private String state;

    private String description;

    private User creator;

    private int open_issues;

    private int closed_issues;

    private Date created_at;

    private Date updated_at;

    private String due_on;

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public int getClosed_issues() {
        return closed_issues;
    }

    public void setClosed_issues(int closed_issues) {
        this.closed_issues = closed_issues;
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

    public String getDue_on() {
        return due_on;
    }

    public void setDue_on(String due_on) {
        this.due_on = due_on;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.html_url);
        dest.writeString(this.title);
        dest.writeInt(this.number);
        dest.writeString(this.state);
        dest.writeString(this.description);
        dest.writeParcelable(this.creator, flags);
        dest.writeInt(this.open_issues);
        dest.writeInt(this.closed_issues);
        dest.writeLong(this.created_at != null ? this.created_at.getTime() : -1);
        dest.writeLong(this.updated_at != null ? this.updated_at.getTime() : -1);
        dest.writeString(this.due_on);
    }

    public MileStone() {
    }

    protected MileStone(Parcel in) {
        this.html_url = in.readString();
        this.title = in.readString();
        this.number = in.readInt();
        this.state = in.readString();
        this.description = in.readString();
        this.creator = in.readParcelable(User.class.getClassLoader());
        this.open_issues = in.readInt();
        this.closed_issues = in.readInt();
        long tmpCreated_at = in.readLong();
        this.created_at = tmpCreated_at == -1 ? null : new Date(tmpCreated_at);
        long tmpUpdated_at = in.readLong();
        this.updated_at = tmpUpdated_at == -1 ? null : new Date(tmpUpdated_at);
        this.due_on = in.readString();
    }

    public static final Parcelable.Creator<MileStone> CREATOR = new Parcelable.Creator<MileStone>() {
        @Override
        public MileStone createFromParcel(Parcel source) {
            return new MileStone(source);
        }

        @Override
        public MileStone[] newArray(int size) {
            return new MileStone[size];
        }
    };
}
