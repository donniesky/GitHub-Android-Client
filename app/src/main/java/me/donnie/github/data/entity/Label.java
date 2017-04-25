package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class Label implements Parcelable {

    private long id;

    private String url;

    private String name;

    private String color;

    @SerializedName("default")
    private boolean def;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDef() {
        return def;
    }

    public void setDef(boolean def) {
        this.def = def;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeString(this.color);
        dest.writeByte(this.def ? (byte) 1 : (byte) 0);
    }

    public Label() {
    }

    protected Label(Parcel in) {
        this.id = in.readLong();
        this.url = in.readString();
        this.name = in.readString();
        this.color = in.readString();
        this.def = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Label> CREATOR = new Parcelable.Creator<Label>() {
        @Override
        public Label createFromParcel(Parcel source) {
            return new Label(source);
        }

        @Override
        public Label[] newArray(int size) {
            return new Label[size];
        }
    };
}
