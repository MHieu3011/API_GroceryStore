package com.vcc.apigrocerystore.model.request;

public class BillDetailFormRequest extends BaseFormRequest {

    private long idBill;
    private long idItem;
    private int number;

    public BillDetailFormRequest() {
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
