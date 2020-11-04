package com.seyfettin.vitrinovaapplication.data.remote.model.new_product;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.model.common.ImagesData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("old_price")
    private Integer oldPrice;

    @SerializedName("price")
    private Integer price;

    @SerializedName("shop")
    private Shop shop;

    @SerializedName("images")
    private List<ImagesData> images;

    public Product() {
    }

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        if (in.readByte() == 0) {
            oldPrice = null;
        } else {
            oldPrice = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        shop = in.readParcelable(Shop.class.getClassLoader());
        images = in.createTypedArrayList(ImagesData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        if (oldPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(oldPrice);
        }
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(price);
        }
        dest.writeParcelable(shop, flags);
        dest.writeTypedList(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Shop getShop() {
        return shop;
    }

    public List<ImagesData> getImages() {
        return images;
    }

    public String getOldPriceAndCurrency() {
        if (oldPrice == null) {
            return "";
        }
        return oldPrice + " TL";
    }

    public String getPriceAndCurrency() {
        return price + " TL";
    }
}
