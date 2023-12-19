package com.example.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* todo Created by Tejas Dani on 18/Dec/2023
 *   POJO class which implements Parcelable class to send data in the form of bundle as well
 * */

public class News implements Parcelable {

    private int id;

    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("author")
    @Expose
    String author;

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("content")
    @Expose
    String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getImageUrl() {
        return url;
    }

    public void setImageUrl(String imageUrl) {
        this.url = imageUrl;
    }
    @SerializedName("urlToImage")
    @Expose
    String urlToImage;
    @SerializedName("url")
    @Expose
    String url;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @SerializedName("publishedAt")
    @Expose
    String publishedAt;

    public News(String title, String content, String author, String urlToImage, String imageUrl) {
        this.title = title;
        this.description = content;
        this.author = author;
        this.urlToImage = urlToImage;
        this.url = imageUrl;
    }

    public int getId() {
        return urlToImage.hashCode();
    }

    protected News(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        author = in.readString();
        content = in.readString();
        urlToImage = in.readString();
        url = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(urlToImage);
        dest.writeString(url);
        dest.writeString(publishedAt);
    }

}
