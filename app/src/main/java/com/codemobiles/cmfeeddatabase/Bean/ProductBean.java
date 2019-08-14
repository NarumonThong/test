package com.codemobiles.cmfeeddatabase.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductBean implements Parcelable {

    private String id;
    private String id_product;
    private String name;
    private String detail;
    private String price;
    private String img_product;

    public static final String BASE_URL = "http://100.100.100.1/cmdev/";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.id_product);
        dest.writeString(this.name);
        dest.writeString(this.detail);
        dest.writeString(this.price);
        dest.writeString(this.img_product);
    }

    public ProductBean() {
    }

    protected ProductBean(Parcel in) {
        this.id = in.readString();
        this.id_product = in.readString();
        this.name = in.readString();
        this.detail = in.readString();
        this.price = in.readString();
        this.img_product = in.readString();
    }

    public static final Parcelable.Creator<ProductBean> CREATOR = new Parcelable.Creator<ProductBean>() {
        @Override
        public ProductBean createFromParcel(Parcel source) {
            return new ProductBean(source);
        }

        @Override
        public ProductBean[] newArray(int size) {
            return new ProductBean[size];
        }
    };
}
