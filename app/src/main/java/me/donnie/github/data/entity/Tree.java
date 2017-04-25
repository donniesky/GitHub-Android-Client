package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class Tree implements Parcelable {

    private String sha;

    private String url;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sha);
        dest.writeString(this.url);
    }

    public Tree() {
    }

    protected Tree(Parcel in) {
        this.sha = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Tree> CREATOR = new Parcelable.Creator<Tree>() {
        @Override
        public Tree createFromParcel(Parcel source) {
            return new Tree(source);
        }

        @Override
        public Tree[] newArray(int size) {
            return new Tree[size];
        }
    };
}
