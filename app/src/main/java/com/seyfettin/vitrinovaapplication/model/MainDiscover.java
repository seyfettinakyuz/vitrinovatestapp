package com.seyfettin.vitrinovaapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.seyfettin.vitrinovaapplication.data.remote.Resource;
import com.seyfettin.vitrinovaapplication.data.remote.model.Collection;
import com.seyfettin.vitrinovaapplication.data.remote.model.Category;
import com.seyfettin.vitrinovaapplication.data.remote.model.EditorShop;
import com.seyfettin.vitrinovaapplication.data.remote.model.Feature;
import com.seyfettin.vitrinovaapplication.data.remote.model.NewShop;
import com.seyfettin.vitrinovaapplication.data.remote.model.new_product.Product;

import java.util.List;

public class MainDiscover implements Parcelable {
    private Resource<List<Feature>> features;
    private String newProductsTitle;
    private Resource<List<Product>> newProducts;
    private String categoriesTitle;
    private Resource<List<Category>> categories;
    private String collectionsTitle;
    private Resource<List<Collection>> collections;
    private String editorShopsTitle;
    private Resource<List<EditorShop>> editorShops;
    private String newShopsTitle;
    private Resource<List<NewShop>> newShops;

    public MainDiscover() {
    }

    protected MainDiscover(Parcel in) {
        newProductsTitle = in.readString();
        categoriesTitle = in.readString();
        collectionsTitle = in.readString();
        editorShopsTitle = in.readString();
        newShopsTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newProductsTitle);
        dest.writeString(categoriesTitle);
        dest.writeString(collectionsTitle);
        dest.writeString(editorShopsTitle);
        dest.writeString(newShopsTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainDiscover> CREATOR = new Creator<MainDiscover>() {
        @Override
        public MainDiscover createFromParcel(Parcel in) {
            return new MainDiscover(in);
        }

        @Override
        public MainDiscover[] newArray(int size) {
            return new MainDiscover[size];
        }
    };

    public Resource<List<Feature>> getFeatures() {
        return features;
    }

    public void setFeatures(Resource<List<Feature>> features) {
        this.features = features;
    }

    public Resource<List<Product>> getNewProducts() {
        return newProducts;
    }

    public void setNewProducts(Resource<List<Product>> newProducts) {
        this.newProducts = newProducts;
    }

    public Resource<List<Category>> getCategories() {
        return categories;
    }

    public void setCategories(Resource<List<Category>> categories) {
        this.categories = categories;
    }

    public Resource<List<Collection>> getCollections() {
        return collections;
    }

    public void setCollections(Resource<List<Collection>> collections) {
        this.collections = collections;
    }

    public Resource<List<EditorShop>> getEditorShops() {
        return editorShops;
    }

    public void setEditorShops(Resource<List<EditorShop>> editorShops) {
        this.editorShops = editorShops;
    }

    public String getNewProductsTitle() {
        return newProductsTitle;
    }

    public void setNewProductsTitle(String newProductsTitle) {
        this.newProductsTitle = newProductsTitle;
    }

    public String getCategoriesTitle() {
        return categoriesTitle;
    }

    public void setCategoriesTitle(String categoriesTitle) {
        this.categoriesTitle = categoriesTitle;
    }

    public String getCollectionsTitle() {
        return collectionsTitle;
    }

    public void setCollectionsTitle(String collectionsTitle) {
        this.collectionsTitle = collectionsTitle;
    }

    public String getEditorShopsTitle() {
        return editorShopsTitle;
    }

    public void setEditorShopsTitle(String editorShopsTitle) {
        this.editorShopsTitle = editorShopsTitle;
    }

    public String getNewShopsTitle() {
        return newShopsTitle;
    }

    public void setNewShopsTitle(String newShopsTitle) {
        this.newShopsTitle = newShopsTitle;
    }

    public Resource<List<NewShop>> getNewShops() {
        return newShops;
    }

    public void setNewShops(Resource<List<NewShop>> newShops) {
        this.newShops = newShops;
    }
}
