package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.model.common.ImagesData;
import com.google.gson.annotations.SerializedName;

public class Feature implements Parcelable {
    @SerializedName("type")
    private String type;

    @SerializedName("cover")
    private ImagesData cover;

    @SerializedName("title")
    private String title;

    @SerializedName("sub_title")
    private String subTitle;

    public Feature() {
    }

    protected Feature(Parcel in) {
        type = in.readString();
        cover = in.readParcelable(ImagesData.class.getClassLoader());
        title = in.readString();
        subTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeParcelable(cover, flags);
        dest.writeString(title);
        dest.writeString(subTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Feature> CREATOR = new Creator<Feature>() {
        @Override
        public Feature createFromParcel(Parcel in) {
            return new Feature(in);
        }

        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };

    public ImagesData getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
