package com.aurionpro.model;


import java.sql.Date;

public class CustomerDetails {
    private int customerId;
    private int userId;
    private String fullName;
    private Date dob;
    private String email;
    private String phone;
    private String address;
    private String aadharNo;
    private String panNo;
    private Date createdAt;

    // --- Constructors ---
    public CustomerDetails() {
    }

    public CustomerDetails(int customerId, int userId, String fullName, Date dob,
                         String email, String phone, String address,
                         String aadharNo, String panNo, Date createdAt) {
        this.customerId = customerId;
        this.userId = userId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.aadharNo = aadharNo;
        this.panNo = panNo;
        this.createdAt = createdAt;
    }

    // --- Getters and Setters ---
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
