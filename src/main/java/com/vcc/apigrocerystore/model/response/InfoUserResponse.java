package com.vcc.apigrocerystore.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoUserResponse {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("full_name")
    private String fullName;

    @Expose
    @SerializedName("sex")
    private int sex;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("role")
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
