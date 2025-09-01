package com.aurionpro.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.aurionpro.dao.UserDao;
import com.aurionpro.model.AccountDetails;
import com.aurionpro.model.BeneficiaryDetails;
import com.aurionpro.model.CustomerDetails;
import com.aurionpro.model.Loan;
import com.aurionpro.model.TransactionModel;
import com.aurionpro.model.UserModel;

public class UserService {

	private UserDao dao = null;

	public UserService() {
		if (dao == null) {
			dao = UserDao.getInstance();
		}
	}

	public UserModel UserVerifications(UserModel user) {
		return dao.loginVerification(user);
	}

	public boolean addCustomerDetails(CustomerDetails details) {
		return dao.addCustomerDetails(details);
	}

	public boolean addBeneficiaryDetails(BeneficiaryDetails details) {
		return dao.addBeneficiaryDetails(details);
	}

	public boolean updateBankAccountStatus(int userId, String status, long accountNo) {
		return dao.updateBankAccountStatus(userId, status,accountNo);
	}

	public List<BeneficiaryDetails> getBeneficiaryDetails(int userId) {
		return dao.getBeneficiaryList(userId);
	}

	public boolean createUserBankAccount(AccountDetails details) {
		return dao.createUserBankAccount(details);
	}

	public boolean makePayment(int userId, TransactionModel transaction) {
		return dao.makePayment(userId, transaction);
	}

	public UserModel createUserAccount(UserModel details) {
		return dao.createUserAccount(details);
	}

	public List<AccountDetails> getUserAccounts(int userId) {
		return dao.getUserAccounts(userId);
	}
	
	public List<TransactionModel> getUserTransactions(int userId) {
		return dao.getUserTransactions(userId);
	}
	
	public CustomerDetails getCustomerDetails(int userId) {
		return dao.getCustomerDetails(userId);
	}
	
	public boolean applyForLoan(HttpServletRequest request,Loan loan,int userId) {
		return dao.applyForLoan(request, loan, userId);
	}
}
