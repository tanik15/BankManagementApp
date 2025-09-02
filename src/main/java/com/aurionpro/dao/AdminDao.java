package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.AccountDetails;
import com.aurionpro.model.CustomerDetails;
import com.aurionpro.model.Loan;
import com.aurionpro.model.TransactionModel;
import com.aurionpro.utils.DbSource;

public class AdminDao {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private static AdminDao adminDao;

	private AdminDao() {
		if (connection == null) {
			connection = DbSource.getConnection();
		}
	}

	public static AdminDao getInstance() {
		if (adminDao == null) {
			adminDao = new AdminDao();
		}
		return adminDao;
	}

	public List<CustomerDetails> getCustomerDetails() {
		List<CustomerDetails> customers = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("select c.*,u.username from customer c left join users u on c.user_id = u.user_id");
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					CustomerDetails customer = new CustomerDetails();
					customer.setFullName(result.getString(2));
					customer.setDob(result.getDate(3));
					customer.setEmail(result.getString(4));
					customer.setPhone(result.getString(5));
					customer.setAddress(result.getString(6));
					customer.setAadharNo(result.getString(7));
					customer.setPanNo(result.getString(8));
					customer.setCreatedAt(result.getDate(9));
					customer.setUserName(result.getString(10));
					customers.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customers;
	}
	
	public List<AccountDetails> getPendingAccountRequests(boolean isPending) {
		List<AccountDetails> accounts = new ArrayList<>();
		if (connection != null) {
			try {
				if(isPending) {
					preparedStatement = connection.prepareStatement("select a.*,c.full_name from accounts a left join customer c on a.user_id=c.user_id where request = 'pending'");
				}else {
					preparedStatement = connection.prepareStatement("select a.*,c.full_name from accounts a left join customer c on a.user_id=c.user_id where request = 'Accept'");
				}
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					AccountDetails account = new AccountDetails();
					account.setBalance(result.getBigDecimal("balance"));
					account.setAccountId(result.getLong("account_id"));
					account.setIfsc(result.getString("ifsc"));
					account.setBranch(result.getString("branch"));
					account.setOpenedOn(result.getDate("opened_on"));
					account.setAccountType(result.getString("type"));
					account.setBussinessType(result.getString("business_type"));
					account.setBussinessPan(result.getLong("business_pan"));
					account.setNominee(result.getString("nominee"));	
					account.setFullName(result.getString("full_name"));	
					account.setAccountNo(result.getLong("account_number"));	
					accounts.add(account);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return accounts;
	}
	public boolean  updateAccountRequest(Long accountId, String request) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("update accounts set request = ? where account_id=?");
				preparedStatement.setString(1, request);
				preparedStatement.setLong(2, accountId);
				int update = preparedStatement.executeUpdate();
				if(update>0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<TransactionModel> getAllTransactions() {
		List<TransactionModel> transactions = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM transactions");
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					TransactionModel transaction = new TransactionModel();
					transaction.setTransactionId(result.getInt(1));
					transaction.setTxnType(result.getString(2));
					transaction.setAmount(result.getBigDecimal(3));
					transaction.setTxnDate(result.getDate(4));
					transaction.setDescription(result.getString(5));
					transaction.setToAccountId(result.getLong(6));
					transaction.setAccountNumber(result.getLong(7));
					transaction.setBeneficiaryName(result.getString(9));
					transactions.add(transaction);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return transactions;
	}
	
	public List<Loan> getUsersPendingLoans() {
		List<Loan> loans = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(
						"select l.*, c.full_name from loans l left join customer c on l.user_id=c.user_id where status='pending'");
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					Loan loan = new Loan();
					loan.setLoanId(rs.getInt("loan_id"));
	                loan.setLoanType(rs.getString("loan_type"));
	                loan.setAccountNo(rs.getLong("account_number"));
	                loan.setLoanAmount(rs.getBigDecimal("loan_amount"));
	                loan.setTenure(rs.getInt("tenure_months"));
	                loan.setInterestRate(rs.getDouble("interest_rate"));
	                loan.setApplicationDate(rs.getDate("application_date"));
	                loan.setStatus(rs.getString("status"));
	                loan.setName(rs.getString("full_name"));
	                loans.add(loan);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loans;
	}
	
	public boolean  updateLoanRequest(int loanId, String request) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("update loans set status = ? where loan_id=?");
				preparedStatement.setString(1, request);
				preparedStatement.setInt(2, loanId);
				int update = preparedStatement.executeUpdate();
				if(update>0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
