package com.vcc.apigrocerystore.entities;

public class UserEntity extends BaseEntity {

    private String userName;
    private String fullName;
    private String password;
    private String address;
    private int role;

    public UserEntity() {
    }

    public UserEntity(String userName, String fullName, String password, String address, int role) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.role = role;
    }

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
