package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.model.common.ImagesData;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("parent_id")
    private Integer parentId;

    @SerializedName("order")
    private Integer order;

    @SerializedName("parent_category")
    private Object parentCategory;

    @SerializedName("logo")
    private ImagesData logo;

    @SerializedName("cover")
    private ImagesData cover;


    public Category() {
    }

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        if (in.readByte() == 0) {
            parentId = null;
        } else {
            parentId = in.readInt();
        }
        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }
        logo = in.readParcelable(ImagesData.class.getClassLoader());
        cover = in.readParcelable(ImagesData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        if (parentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(parentId);
        }
        if (order == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(order);
        }
        dest.writeParcelable(logo, flags);
        dest.writeParcelable(cover, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public Object getParentCategory() {
        return parentCategory;
    }

    public ImagesData getLogo() {
        return logo;
    }

    public ImagesData getCover() {
        return cover;
    }
}
