package com.vcc.apigrocerystore.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoItemBestSellerResponse {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("brand")
    private String brand;

    @Expose
    @SerializedName("numbers")
    private int numbers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }
}
