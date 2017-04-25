package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author donnieSky
 * @created_at 2017/4/14.
 * @description
 */

public class MarkDown implements Parcelable {

    private String text;

    private String mode = "gfm";

    private String context = "markdown";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.mode);
        dest.writeString(this.context);
    }

    public MarkDown() {
    }

    protected MarkDown(Parcel in) {
        this.text = in.readString();
        this.mode = in.readString();
        this.context = in.readString();
    }

    public static final Parcelable.Creator<MarkDown> CREATOR = new Parcelable.Creator<MarkDown>() {
        @Override
        public MarkDown createFromParcel(Parcel source) {
            return new MarkDown(source);
        }

        @Override
        public MarkDown[] newArray(int size) {
            return new MarkDown[size];
        }
    };
}
