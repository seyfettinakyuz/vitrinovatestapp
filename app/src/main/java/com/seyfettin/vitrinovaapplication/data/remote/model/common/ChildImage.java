package com.seyfettin.vitrinovaapplication.data.remote.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ChildImage implements Parcelable {
    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("url")
    private String url;

    public ChildImage() {
    }

    protected ChildImage(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChildImage> CREATOR = new Creator<ChildImage>() {
        @Override
        public ChildImage createFromParcel(Parcel in) {
            return new ChildImage(in);
        }

        @Override
        public ChildImage[] newArray(int size) {
            return new ChildImage[size];
        }
    };

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }
}
