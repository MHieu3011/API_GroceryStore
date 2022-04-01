package com.vcc.apigrocerystore.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillRegistrationFormRequest {

    @Expose
    @SerializedName("id_customer")
    @JsonProperty("id_customer")
    private long idCustomer;

    @Expose
    @SerializedName("id_user")
    @JsonProperty("id_user")
    private long idUser;

    @Expose
    @SerializedName("date")
    @JsonProperty("date")
    private String date;

    @Expose
    @SerializedName("bill_details")
    @JsonProperty("bill_details")
    private List<BillDetailRegistrationFormRequest> billDetails;

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

    public List<BillDetailRegistrationFormRequest> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetailRegistrationFormRequest> billDetails) {
        this.billDetails = billDetails;
    }
}
