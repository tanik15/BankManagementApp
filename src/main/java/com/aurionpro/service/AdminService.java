package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.AdminDao;
import com.aurionpro.model.AccountDetails;
import com.aurionpro.model.CustomerDetails;
import com.aurionpro.model.Loan;
import com.aurionpro.model.TransactionModel;

public class AdminService {
	private AdminDao dao = null;

	public AdminService() {
		if (dao == null) {
			dao = AdminDao.getInstance();
		}
	}

	public List<CustomerDetails> getAllCustomers() {
		return dao.getCustomerDetails();
	}
	
	public List<AccountDetails> getPendingAccounts() {
		return dao.getPendingAccountRequests(true);
	}
	
	public List<AccountDetails> getAllAccounts() {
		return dao.getPendingAccountRequests(false);
	}
	
	public List<TransactionModel> getAllTransactions() {
		return dao.getAllTransactions();
	}
	
	public List<Loan> getUsersPendingLoans() {
		return dao.getUsersPendingLoans();
	}
	public boolean updateAccountRequest(Long accountId, String request) {
		return dao.updateAccountRequest(accountId,request);
	}
	public boolean updateLoanRequest(int loanId, String request) {
		return dao.updateLoanRequest(loanId,request);
	}
	
}
