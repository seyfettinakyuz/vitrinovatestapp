package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.model.common.ImagesData;
import com.google.gson.annotations.SerializedName;

public class Collection implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("definition")
    private String definition;
    @SerializedName("cover")
    private ImagesData cover;

    public Collection() {
    }

    protected Collection(Parcel in) {
        title = in.readString();
        definition = in.readString();
        cover = in.readParcelable(ImagesData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(definition);
        dest.writeParcelable(cover, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel in) {
            return new Collection(in);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDefinition() {
        return definition;
    }

    public ImagesData getCover() {
        return cover;
    }
}
