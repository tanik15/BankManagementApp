package com.aurionpro.model;

import java.sql.Date;

public class Loan {
    private int loanId;
    private int userId;
    private String loanType;
    private double loanAmount;
    private long accountNo;
    public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	private int tenure; // in months
    private double interestRate;
    private String status; // PENDING, APPROVED, REJECTED
    private Date applicationDate;

    // 🔹 Constructors
    public Loan() {}

    public Loan(int loanId, int userId, String loanType, double loanAmount, int tenure, double interestRate, String status, Date applicationDate) {
        this.loanId = loanId;
        this.userId = userId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.tenure = tenure;
        this.interestRate = interestRate;
        this.status = status;
        this.applicationDate = applicationDate;
    }

    // 🔹 Getters & Setters
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    // 🔹 Utility method for EMI calculation
    public double calculateEMI() {
        double monthlyRate = interestRate / (12 * 100); 
        return (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, tenure)) /
               (Math.pow(1 + monthlyRate, tenure) - 1);
    }

    @Override
    public String toString() {
        return "Loan [loanId=" + loanId + ", userId=" + userId + ", loanType=" + loanType + 
               ", loanAmount=" + loanAmount + ", tenure=" + tenure + 
               ", interestRate=" + interestRate + ", status=" + status + 
               ", applicationDate=" + applicationDate + "]";
    }
}
