package com.seyfettin.vitrinovaapplication.data.remote.model.new_product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Shop implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Shop() {
    }

    protected Shop(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
