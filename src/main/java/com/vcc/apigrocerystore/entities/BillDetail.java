package com.vcc.apigrocerystore.entities;

public class BillDetail extends BaseEntity {

    private long idBill;
    private long idItem;
    private int number;

    public BillDetail() {
    }

    public BillDetail(long idBill, long idItem, int number) {
        this.idBill = idBill;
        this.idItem = idItem;
        this.number = number;
    }

    public long getIdBill() {
        return idBill;
    }

    public void setIdBill(long idBill) {
        this.idBill = idBill;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
