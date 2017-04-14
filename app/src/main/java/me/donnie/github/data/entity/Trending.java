package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author donnieSky
 * @created_at 2017/4/11.
 * @description
 */

public class Trending implements Parcelable {

    private String owner;

    private String name;

    private String url;

    @SerializedName("avatarUrl")
    private String avatarUrl;

    private String description;

    private int stars;

    private int forks;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.owner);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.description);
        dest.writeInt(this.stars);
        dest.writeInt(this.forks);
    }

    public Trending() {
    }

    protected Trending(Parcel in) {
        this.owner = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.avatarUrl = in.readString();
        this.description = in.readString();
        this.stars = in.readInt();
        this.forks = in.readInt();
    }

    public static final Parcelable.Creator<Trending> CREATOR = new Parcelable.Creator<Trending>() {
        @Override
        public Trending createFromParcel(Parcel source) {
            return new Trending(source);
        }

        @Override
        public Trending[] newArray(int size) {
            return new Trending[size];
        }
    };
}
