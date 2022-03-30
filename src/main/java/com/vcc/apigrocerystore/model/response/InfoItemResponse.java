package com.vcc.apigrocerystore.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoItemResponse {

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("from_date")
    private String fromDate;

    @Expose
    @SerializedName("to_date")
    private String toDate;

    @Expose
    @SerializedName("price")
    private long price;

    @Expose
    @SerializedName("brand")
    private String brand;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
