package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.model.common.ImagesData;
import com.google.gson.annotations.SerializedName;

public class NewShop implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("definition")
    private String definition;
    @SerializedName("product_count")
    private Integer productCount;
    @SerializedName("logo")
    private ImagesData logo;

    @SerializedName("cover")
    private ImagesData cover;

    public NewShop() {
    }


    protected NewShop(Parcel in) {
        name = in.readString();
        definition = in.readString();
        if (in.readByte() == 0) {
            productCount = null;
        } else {
            productCount = in.readInt();
        }
        logo = in.readParcelable(ImagesData.class.getClassLoader());
        cover = in.readParcelable(ImagesData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(definition);
        if (productCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productCount);
        }
        dest.writeParcelable(logo, flags);
        dest.writeParcelable(cover, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewShop> CREATOR = new Creator<NewShop>() {
        @Override
        public NewShop createFromParcel(Parcel in) {
            return new NewShop(in);
        }

        @Override
        public NewShop[] newArray(int size) {
            return new NewShop[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public String getProductCountStr(){
        return productCount + " ÜRÜN";
    }

    public ImagesData getLogo() {
        return logo;
    }

    public ImagesData getCover() {
        return cover;
    }

    public String getLogoUrl(){
        if(logo == null){
            return String.valueOf(getName().charAt(0));
        }
        return logo.getThumbnail().getUrl();
    }
}
