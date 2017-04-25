package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class Page implements Parcelable {

    private String page_name;

    private String title;

    private String summary;

    private String action;

    private String sha;

    private String html_url;

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.page_name);
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.action);
        dest.writeString(this.sha);
        dest.writeString(this.html_url);
    }

    public Page() {
    }

    protected Page(Parcel in) {
        this.page_name = in.readString();
        this.title = in.readString();
        this.summary = in.readString();
        this.action = in.readString();
        this.sha = in.readString();
        this.html_url = in.readString();
    }

    public static final Parcelable.Creator<Page> CREATOR = new Parcelable.Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel source) {
            return new Page(source);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };
}
