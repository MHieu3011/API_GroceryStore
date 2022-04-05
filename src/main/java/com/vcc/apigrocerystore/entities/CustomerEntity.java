package com.vcc.apigrocerystore.entities;

public class CustomerEntity extends BaseEntity {

    private String fullName;
    private int sex;
    private String phoneNumber;

    public CustomerEntity() {
    }

    public CustomerEntity(String fullName, int sex, String phoneNumber) {
        this.fullName = fullName;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
