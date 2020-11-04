package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseDiscoverObject implements Parcelable {
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName(value = "featured" , alternate = {"products","categories","collections","shops"})
    private List<Object> response;

    public BaseDiscoverObject() {
    }

    protected BaseDiscoverObject(Parcel in) {
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

    public static final Creator<BaseDiscoverObject> CREATOR = new Creator<BaseDiscoverObject>() {
        @Override
        public BaseDiscoverObject createFromParcel(Parcel in) {
            return new BaseDiscoverObject(in);
        }

        @Override
        public BaseDiscoverObject[] newArray(int size) {
            return new BaseDiscoverObject[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getResponse() {
        return response;
    }

    public void setResponse(List<Object> response) {
        this.response = response;
    }
}
