package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author donnieSky
 * @created_at 2017/4/10.
 * @description
 */

public class Payload implements Parcelable {

    private String action;

    private Forkee forkee;

    private Issue issue;

    private Comment comment;

    @SerializedName("public")
    private boolean pub;

    private Date created_at;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.action);
        dest.writeParcelable(this.forkee, flags);
        dest.writeParcelable(this.issue, flags);
        dest.writeParcelable(this.comment, flags);
        dest.writeByte(this.pub ? (byte) 1 : (byte) 0);
        dest.writeLong(this.created_at != null ? this.created_at.getTime() : -1);
    }

    public Payload() {
    }

    protected Payload(Parcel in) {
        this.action = in.readString();
        this.forkee = in.readParcelable(Forkee.class.getClassLoader());
        this.issue = in.readParcelable(Issue.class.getClassLoader());
        this.comment = in.readParcelable(Comment.class.getClassLoader());
        this.pub = in.readByte() != 0;
        long tmpCreated_at = in.readLong();
        this.created_at = tmpCreated_at == -1 ? null : new Date(tmpCreated_at);
    }

    public static final Parcelable.Creator<Payload> CREATOR = new Parcelable.Creator<Payload>() {
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
