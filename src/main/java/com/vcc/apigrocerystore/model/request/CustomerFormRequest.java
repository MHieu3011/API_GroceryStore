package com.vcc.apigrocerystore.model.request;

public class CustomerFormRequest extends BaseFormRequest {

    private String fullName;
    private String phoneNumber;

    public CustomerFormRequest() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
