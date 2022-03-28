package com.vcc.apigrocerystore.entities;

public class ItemEntity extends BaseEntity {

    private String code;
    private String name;
    private long fromDate;
    private long toDate;
    private long price;
    private String brand;

    public ItemEntity() {
    }

    public ItemEntity(String code, String name, long fromDate, long toDate, long price, String brand) {
        this.code = code;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.brand = brand;
    }

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

    public long getFromDate() {
        return fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public long getToDate() {
        return toDate;
    }

    public void setToDate(long toDate) {
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
