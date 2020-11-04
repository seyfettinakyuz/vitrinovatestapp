package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ParentCategory implements Parcelable {
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

    public ParentCategory() {
    }

    protected ParentCategory(Parcel in) {
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParentCategory> CREATOR = new Creator<ParentCategory>() {
        @Override
        public ParentCategory createFromParcel(Parcel in) {
            return new ParentCategory(in);
        }

        @Override
        public ParentCategory[] newArray(int size) {
            return new ParentCategory[size];
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
}
