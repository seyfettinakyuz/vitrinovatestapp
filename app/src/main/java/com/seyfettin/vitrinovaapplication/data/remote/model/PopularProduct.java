package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.model.common.ImagesData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularProduct implements Parcelable {
    @SerializedName("images")
    private List<ImagesData> images;

    public PopularProduct() {
    }

    protected PopularProduct(Parcel in) {
        images = in.createTypedArrayList(ImagesData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PopularProduct> CREATOR = new Creator<PopularProduct>() {
        @Override
        public PopularProduct createFromParcel(Parcel in) {
            return new PopularProduct(in);
        }

        @Override
        public PopularProduct[] newArray(int size) {
            return new PopularProduct[size];
        }
    };

    public List<ImagesData> getImages() {
        return images;
    }
}
