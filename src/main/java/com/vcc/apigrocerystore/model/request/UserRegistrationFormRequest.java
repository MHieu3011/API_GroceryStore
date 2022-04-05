package com.vcc.apigrocerystore.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegistrationFormRequest extends BaseFormRequest {
    @Expose
    @SerializedName("username")
    @JsonProperty("username")
    private String userName;

    @Expose
    @SerializedName("full_name")
    @JsonProperty("full_name")
    private String fullName;

    @Expose
    @SerializedName("sex")
    @JsonProperty("sex")
    private int sex;

    @Expose
    @SerializedName("password")
    @JsonProperty("password")
    private String password;

    @Expose
    @SerializedName("address")
    @JsonProperty("address")
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
