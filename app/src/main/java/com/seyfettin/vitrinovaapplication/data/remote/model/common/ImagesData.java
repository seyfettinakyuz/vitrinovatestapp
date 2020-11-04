package com.seyfettin.vitrinovaapplication.data.remote.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ImagesData implements Parcelable {
    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("url")
    private String url;

    @SerializedName("medium")
    private ChildImage medium;

    @SerializedName("thumbnail")
    private ChildImage thumbnail;

    public ImagesData() {
    }

    protected ImagesData(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        url = in.readString();
        medium = in.readParcelable(ChildImage.class.getClassLoader());
        thumbnail = in.readParcelable(ChildImage.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(url);
        dest.writeParcelable(medium, flags);
        dest.writeParcelable(thumbnail, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImagesData> CREATOR = new Creator<ImagesData>() {
        @Override
        public ImagesData createFromParcel(Parcel in) {
            return new ImagesData(in);
        }

        @Override
        public ImagesData[] newArray(int size) {
            return new ImagesData[size];
        }
    };

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ChildImage getMedium() {
        return medium;
    }

    public void setMedium(ChildImage medium) {
        this.medium = medium;
    }

    public ChildImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ChildImage thumbnail) {
        this.thumbnail = thumbnail;
    }
}
