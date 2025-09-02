package com.aurionpro.model;

import java.math.BigDecimal;
import java.sql.Date;

public class AccountDetails {
	private Long accountNo;
	private Long accountId;
	private String fullName;
	private int userId;
	private String accountType;
	private BigDecimal balance;
	private String nominee;
	private String ifsc;
	private String branch;
	private Date openedOn;
	private String status;
	private String request;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOpenedOn() {
		return openedOn;
	}

	public void setOpenedOn(Date openedOn) {
		this.openedOn = openedOn;
	}

	public String getBranch() {
		return branch;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	private String bussinessType;
	private long bussinessPan;

	public String getBussinessType() {
		return bussinessType;
	}

	public long getBussinessPan() {
		return bussinessPan;
	}

	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}

	public void setBussinessPan(long bussinessPan) {
		this.bussinessPan = bussinessPan;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public AccountDetails() {
		super();
	}

	public AccountDetails(Long accountNo, int user_id, String accountType, BigDecimal balance) {
		super();
		this.accountNo = accountNo;
		this.userId = user_id;
		this.accountType = accountType;
		this.balance = balance;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int user_id) {
		this.userId = user_id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
