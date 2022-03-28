package com.vcc.apigrocerystore.entities;

public class StoreHouse extends BaseEntity {

    private long idItem;
    private String codeItem;
    private int number;
    private long date;

    public StoreHouse() {
    }

    public StoreHouse(long idItem, String codeItem, int number, long date) {
        this.idItem = idItem;
        this.codeItem = codeItem;
        this.number = number;
        this.date = date;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public String getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(String codeItem) {
        this.codeItem = codeItem;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
