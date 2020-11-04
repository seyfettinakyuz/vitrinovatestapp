package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BaseData implements Parcelable {
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;

    public BaseData() {
    }

    protected BaseData(Parcel in) {
        type = in.readString();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseData> CREATOR = new Creator<BaseData>() {
        @Override
        public BaseData createFromParcel(Parcel in) {
            return new BaseData(in);
        }

        @Override
        public BaseData[] newArray(int size) {
            return new BaseData[size];
        }
    };

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
