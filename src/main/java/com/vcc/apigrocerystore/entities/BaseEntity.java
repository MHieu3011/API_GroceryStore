package com.vcc.apigrocerystore.entities;

import java.sql.Time;

public class BaseEntity {

    private long id;
    private Time createdDate;
    private Time modifiedDate;
    private String createdBy;
    private String modifiedBy;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Time getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Time createdDate) {
        this.createdDate = createdDate;
    }

    public Time getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Time modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
