package com.vcc.apigrocerystore.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoBillResponse {

    @Expose
    @SerializedName("id_customer")
    private long idCustomer;

    @Expose
    @SerializedName("id_user")
    private long idUser;

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("bill_details")
    private List<InfoBillDetailResponse> billDetails;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<InfoBillDetailResponse> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<InfoBillDetailResponse> billDetails) {
        this.billDetails = billDetails;
    }
}
