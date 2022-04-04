package com.vcc.apigrocerystore.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDetailRegistrationFormRequest extends BaseFormRequest{

    @Expose
    @SerializedName("id_item")
    @JsonProperty("id_item")
    private long idItem;

    @Expose
    @SerializedName("number")
    @JsonProperty("number")
    private int number;

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
