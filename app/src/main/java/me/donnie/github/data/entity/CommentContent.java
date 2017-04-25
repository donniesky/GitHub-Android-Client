package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommentContent implements Parcelable {

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
    }

    public CommentContent() {
    }

    protected CommentContent(Parcel in) {
        this.body = in.readString();
    }

    public static final Parcelable.Creator<CommentContent> CREATOR = new Parcelable.Creator<CommentContent>() {
        @Override
        public CommentContent createFromParcel(Parcel source) {
            return new CommentContent(source);
        }

        @Override
        public CommentContent[] newArray(int size) {
            return new CommentContent[size];
        }
    };
}
