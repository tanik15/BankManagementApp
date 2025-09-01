package com.aurionpro.model;

import java.util.Date;

public class BeneficiaryDetails {
    private int beneficiaryId;
    private int userId;
    private String beneficiaryName;
    private long beneficiaryAccount;
    private String bankName;
    private String ifscCode;
    private Date addedOn;

    // --- Constructors ---
    public BeneficiaryDetails() {
    }

    public BeneficiaryDetails(int beneficiaryId, int userId, String beneficiaryName,
                                   long beneficiaryAccount, String bankName,
                                   String ifscCode, Date addedOn) {
        this.beneficiaryId = beneficiaryId;
        this.userId = userId;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryAccount = beneficiaryAccount;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.addedOn = addedOn;
    }

    // --- Getters and Setters ---
    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public long getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(long beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

}
