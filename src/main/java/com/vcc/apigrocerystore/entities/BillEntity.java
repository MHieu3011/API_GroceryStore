package com.vcc.apigrocerystore.entities;

public class BillEntity extends BaseEntity {

    private long idCustomer;
    private long idUser;
    private long date;
    private long totalMoney;

    public BillEntity() {
    }

    public BillEntity(long idCustomer, long idUser, long date, long totalMoney) {
        this.idCustomer = idCustomer;
        this.idUser = idUser;
        this.date = date;
        this.totalMoney = totalMoney;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
