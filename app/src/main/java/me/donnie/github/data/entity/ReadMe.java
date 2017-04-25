package me.donnie.github.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author donnieSky
 * @created_at 2017/4/17.
 * @description
 */

public class ReadMe implements Parcelable {

    private String name;

    private String path;

    private String sha;

    private long size;

    private String url;

    private String html_url;

    private String git_url;

    private String download_url;

    private String type;

    private String content;

    private String encoding;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeString(this.sha);
        dest.writeLong(this.size);
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.git_url);
        dest.writeString(this.download_url);
        dest.writeString(this.type);
        dest.writeString(this.content);
        dest.writeString(this.encoding);
    }

    public ReadMe() {
    }

    protected ReadMe(Parcel in) {
        this.name = in.readString();
        this.path = in.readString();
        this.sha = in.readString();
        this.size = in.readLong();
        this.url = in.readString();
        this.html_url = in.readString();
        this.git_url = in.readString();
        this.download_url = in.readString();
        this.type = in.readString();
        this.content = in.readString();
        this.encoding = in.readString();
    }

    public static final Parcelable.Creator<ReadMe> CREATOR = new Parcelable.Creator<ReadMe>() {
        @Override
        public ReadMe createFromParcel(Parcel source) {
            return new ReadMe(source);
        }

        @Override
        public ReadMe[] newArray(int size) {
            return new ReadMe[size];
        }
    };
}
