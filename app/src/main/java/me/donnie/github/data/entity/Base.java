package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class Base implements Parcelable {

    private String label;

    private String ref;

    private String sha;

    private User user;

    private Repo repo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
        dest.writeString(this.ref);
        dest.writeString(this.sha);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.repo, flags);
    }

    public Base() {
    }

    protected Base(Parcel in) {
        this.label = in.readString();
        this.ref = in.readString();
        this.sha = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.repo = in.readParcelable(Repo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Base> CREATOR = new Parcelable.Creator<Base>() {
        @Override
        public Base createFromParcel(Parcel source) {
            return new Base(source);
        }

        @Override
        public Base[] newArray(int size) {
            return new Base[size];
        }
    };
}
