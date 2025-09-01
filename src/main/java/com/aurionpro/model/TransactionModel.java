package com.aurionpro.model;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionModel {
    private int transactionId;
    private long accountNumber;
    private String txnType; 
    private BigDecimal amount;
    private Date txnDate;
    private String description;
    private String beneficiaryName;
    public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	private long toAccountId; 

    // Constructors
    public TransactionModel() {}

    public TransactionModel(int transactionId, int accountNumber, String txnType, BigDecimal amount, Date txnDate,
                            String description, Integer toAccountId) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.txnType = txnType;
        this.amount = amount;
        this.txnDate = txnDate;
        this.description = description;
        this.toAccountId = toAccountId;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(long toAccountId) {
        this.toAccountId = toAccountId;
    }
}
