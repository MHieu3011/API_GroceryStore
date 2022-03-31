package com.vcc.apigrocerystore.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoItemByExpireResponse {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("number")
    private int number;

    @Expose
    @SerializedName("from_date")
    private String fromDate;

    @Expose
    @SerializedName("to_date")
    private String toDate;

    @Expose
    @SerializedName("brand")
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
