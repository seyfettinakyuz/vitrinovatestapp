package com.seyfettin.vitrinovaapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.model.common.ImagesData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditorShop implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("definition")
    private String definition;
    @SerializedName("popular_products")
    private List<PopularProduct> popularProducts;
    @SerializedName("product_count")
    private Integer productCount;
    @SerializedName("logo")
    private ImagesData logo;

    @SerializedName("cover")
    private ImagesData cover;

    public EditorShop() {
    }


    protected EditorShop(Parcel in) {
        name = in.readString();
        definition = in.readString();
        popularProducts = in.createTypedArrayList(PopularProduct.CREATOR);
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
        dest.writeTypedList(popularProducts);
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

    public static final Creator<EditorShop> CREATOR = new Creator<EditorShop>() {
        @Override
        public EditorShop createFromParcel(Parcel in) {
            return new EditorShop(in);
        }

        @Override
        public EditorShop[] newArray(int size) {
            return new EditorShop[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public List<PopularProduct> getPopularProducts() {
        return popularProducts;
    }

    public String getThumbImage1() {
        if(popularProducts.size() > 0 && popularProducts.get(0).getImages().size()>0){
            return popularProducts.get(0).getImages().get(0).getThumbnail().getUrl();
        }
        return cover.getThumbnail().getUrl();
    }

    public String getThumbImage2() {
        if(popularProducts.size() > 1 && popularProducts.get(1).getImages().size()>0){
            return popularProducts.get(1).getImages().get(0).getThumbnail().getUrl();
        }
        return cover.getThumbnail().getUrl();
    }

    public String getThumbImage3() {
        if(popularProducts.size() > 2 && popularProducts.get(2).getImages().size()>0){
            return popularProducts.get(2).getImages().get(0).getThumbnail().getUrl();
        }
        return cover.getThumbnail().getUrl();
    }

    public Integer getProductCount() {
        return productCount;
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

    public String getProductCountStr(){
        return productCount + " ÜRÜN";
    }
}
