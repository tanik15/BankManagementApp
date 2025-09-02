package com.aurionpro.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.aurionpro.model.AccountDetails;
import com.aurionpro.model.BeneficiaryDetails;
import com.aurionpro.model.CustomerDetails;
import com.aurionpro.model.Loan;
import com.aurionpro.model.TransactionModel;
import com.aurionpro.model.UserModel;
import com.aurionpro.utils.DbSource;

public class UserDao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private static UserDao userDao;

	private UserDao() {
		if (connection == null) {
			connection = DbSource.getConnection();
		}
	}

	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public UserModel loginVerification(UserModel user) {
		if (connection != null) {
			try {
				System.out.println(user.getUsername());
				System.out.println(user.getPassword());
				System.out.println(user.getRole());
				preparedStatement = connection.prepareStatement(
						"SELECT u.user_id,c.full_name FROM users u left join customer c on u.user_id=c.user_id WHERE u.username = ? and u.password=? and u.role = ?");
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getRole());
				ResultSet result = preparedStatement.executeQuery();

				if (result.next()) {
					user.setUserId(result.getInt(1));
					user.setFullName(result.getString(2));
					System.out.println(":(" + user.getUserId());
					return user;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean addCustomerDetails(CustomerDetails details) {
		if (connection != null) {
			try {

				preparedStatement = connection.prepareStatement(
						"INSERT INTO customer (user_id, full_name, dob, email, phone, address, aadhar_no, pan_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, details.getUserId());
				preparedStatement.setString(2, details.getFullName());
				preparedStatement.setDate(3, details.getDob());
				preparedStatement.setString(4, details.getEmail());
				preparedStatement.setString(5, details.getPhone());
				preparedStatement.setString(6, details.getAddress());
				preparedStatement.setString(7, details.getAadharNo());
				preparedStatement.setString(8, details.getPanNo());
				int update = preparedStatement.executeUpdate();
				if (update > 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public CustomerDetails getCustomerDetails(int userId) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("select * from customer where user_id=?");
				preparedStatement.setInt(1, userId);
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
					return customer;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean addBeneficiaryDetails(BeneficiaryDetails details) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(
						"INSERT INTO beneficiaries (user_id, beneficiary_name, beneficiary_account, bank_name, ifsc_code) VALUES (?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, details.getuserId());
				preparedStatement.setString(2, details.getBeneficiaryName());
				preparedStatement.setLong(3, details.getBeneficiaryAccount());
				preparedStatement.setString(4, details.getBankName());
				preparedStatement.setString(5, details.getIfscCode());
				int update = preparedStatement.executeUpdate();
				if (update > 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<BeneficiaryDetails> getBeneficiaryList(int userId) {
		List<BeneficiaryDetails> beneficiaries = new ArrayList<>();
		if (connection != null) {
			try {
				System.out.println(userId);
				preparedStatement = connection.prepareStatement("SELECT * FROM beneficiaries WHERE user_id =?");
				preparedStatement.setInt(1, userId);
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					BeneficiaryDetails beneficiary = new BeneficiaryDetails();
					beneficiary.setBeneficiaryName(result.getString(2));
					beneficiary.setBeneficiaryAccount(result.getLong(3));
					beneficiary.setBankName(result.getString(4));
					beneficiary.setIfscCode(result.getString(5));
					beneficiaries.add(beneficiary);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return beneficiaries;
	}

	public boolean checkUserBankAccount(AccountDetails details) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("select 1 from accounts where user_id=? and type=?");
				preparedStatement.setInt(1, details.getUserId());
				preparedStatement.setString(2, details.getAccountType());
				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean checkUserAccount(UserModel details) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement("select 1 from users where username=? and role=?");
				preparedStatement.setString(1, details.getUsername());
				preparedStatement.setString(2, details.getRole());
				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String generateIFSC() {
		Random rand = new Random();

		// First 4 letters (Bank code)
		StringBuilder bankCode = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			char c = (char) ('A' + rand.nextInt(26));
			bankCode.append(c);
		}

		// Middle fixed '0'
		String mid = "0";

		// Last 6 digits (branch code)
		int branchNum = rand.nextInt(999999);
		String branchCode = String.format("%06d", branchNum);

		return bankCode.toString() + mid + branchCode;
	}

	public boolean createUserBankAccount(AccountDetails details) {
		if (connection != null) {
			try {
				if (!checkUserBankAccount(details)) {
					details.setIfsc(generateIFSC());
					details.setBranch("Naringi");
					System.out.println(details.getUserId());
					preparedStatement = connection.prepareStatement(
							"INSERT INTO accounts (user_id, type, balance, ifsc, branch, business_type, business_pan, nominee) VALUES(?, ?, ?, ?,?, ?, ?, ?)");
					preparedStatement.setInt(1, details.getUserId());
					preparedStatement.setString(2, details.getAccountType());
					preparedStatement.setBigDecimal(3, details.getBalance());
					preparedStatement.setString(4, details.getIfsc());
					preparedStatement.setString(5, details.getBranch());
					preparedStatement.setString(6, details.getBussinessType());
					preparedStatement.setLong(7, details.getBussinessPan());
					preparedStatement.setString(8, details.getNominee());
					int update = preparedStatement.executeUpdate();
					if (update > 0) {
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean updateBankAccountStatus(int userId, String status, long accountNo) {
		if (connection != null) {
			try {
				preparedStatement = connection
						.prepareStatement("update accounts set status=? where account_number=? and user_id=?");
				preparedStatement.setString(1, status);
				preparedStatement.setLong(2, accountNo);
				preparedStatement.setInt(3, userId);
				int update = preparedStatement.executeUpdate();
				if (update > 0)
					return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public UserModel createUserAccount(UserModel details) {
		if (connection != null) {
			try {
				if (!checkUserAccount(details)) {
					preparedStatement = connection
							.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
					preparedStatement.setString(1, details.getUsername());
					preparedStatement.setString(2, details.getPassword());
					preparedStatement.setString(3, details.getRole());
					int update = preparedStatement.executeUpdate();
					if (update > 0) {
						preparedStatement = connection.prepareStatement("SELECT user_id FROM users WHERE username=?");
						preparedStatement.setString(1, details.getUsername());
						ResultSet result = preparedStatement.executeQuery();
						if (result.next()) {
							details.setUserId(result.getInt(1));
						}
						return details;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<AccountDetails> getUserAccounts(int userId) {
		List<AccountDetails> accounts = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(
						"SELECT account_number,type,balance,branch,nominee,opened_on,ifsc,status FROM accounts WHERE user_id =?");
				preparedStatement.setInt(1, userId);
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					AccountDetails account = new AccountDetails();
					account.setAccountNo(result.getLong(1));
					account.setAccountType(result.getString(2));
					account.setBalance(result.getBigDecimal(3));
					account.setBranch(result.getString(4));
					account.setNominee(result.getString(5));
					account.setOpenedOn(result.getDate(6));
					account.setIfsc(result.getString(7));
					account.setStatus(result.getString(8));
					accounts.add(account);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return accounts;
	}

	public List<TransactionModel> getUserTransactions(int userId) {
		List<TransactionModel> transactions = new ArrayList<>();
		if (connection != null) {
			try {
				System.out.println(userId);
				preparedStatement = connection.prepareStatement("SELECT * FROM transactions WHERE user_id =?");
				preparedStatement.setInt(1, userId);
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

	public boolean makePayment(int userId, TransactionModel transaction) {

		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(
						"update accounts set balance=(balance-?) where account_number=? and user_id=?");
				preparedStatement.setBigDecimal(1, transaction.getAmount());
				preparedStatement.setLong(2, transaction.getAccountNumber());
				preparedStatement.setInt(3, userId);
				int update = preparedStatement.executeUpdate();
				if (update > 0) {
					preparedStatement = connection.prepareStatement(
							"INSERT INTO transactions(account_number,txn_type,amount,to_account_id,description,user_id,beneficiary_name) values (?,?,?,?,?,?,?)");
					preparedStatement.setLong(1, transaction.getAccountNumber());
					preparedStatement.setString(2, transaction.getTxnType());
					preparedStatement.setBigDecimal(3, transaction.getAmount());
					preparedStatement.setLong(4, transaction.getToAccountId());
					preparedStatement.setString(5, transaction.getDescription());
					preparedStatement.setInt(6, userId);
					preparedStatement.setString(7, transaction.getBeneficiaryName());
					update = preparedStatement.executeUpdate();
					if (update > 0) {
						return true;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean applyForLoan(HttpServletRequest request, Loan loan, int userId) {
		if (connection != null) {
			try {
				// 1. Insert Loan Details
				preparedStatement = connection.prepareStatement(
						"INSERT INTO loans (user_id, loan_type, loan_amount, tenure_months, status,account_number) VALUES (?, ?, ?, ?, 'PENDING', 502314789651)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, userId);
				preparedStatement.setString(2, loan.getLoanType());
				preparedStatement.setBigDecimal(3, loan.getLoanAmount());
				preparedStatement.setInt(4, loan.getTenure());

				int rows = preparedStatement.executeUpdate();

				int loanId = 0;
				if (rows > 0) {
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if (rs.next()) {
						loanId = rs.getInt(1);
					}
					rs.close();
				}

				// 2. Insert Documents as BLOBs
				if (loanId > 0) {
					String docSQL = "INSERT INTO loan_documents (loan_id, doc_type, document, file_name, content_type) VALUES (?, ?, ?, ?, ?)";
					preparedStatement = connection.prepareStatement(docSQL);

					try {
						uploadDocument(request, preparedStatement, loanId, "identity_proof", "Identity Proof");
						uploadDocument(request, preparedStatement, loanId, "address_proof", "Address Proof");
						uploadDocument(request, preparedStatement, loanId, "income_proof", "Income Proof");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private void uploadDocument(HttpServletRequest request, PreparedStatement stmtDoc, int loanId, String fieldName,
			String docType) throws Exception {
		Part filePart = request.getPart(fieldName);
		if (filePart != null && filePart.getSize() > 0) {
			try (InputStream inputStream = filePart.getInputStream()) { // ✅ Auto-close
	            stmtDoc.setInt(1, loanId);
	            stmtDoc.setString(2, docType);
	            stmtDoc.setBlob(3, inputStream);
	            stmtDoc.setString(4, filePart.getSubmittedFileName());
	            stmtDoc.setString(5, filePart.getContentType());
	            stmtDoc.executeUpdate();
	        }
		}
	}
	
	public List<Loan> getUserLoans(int userId) {
		List<Loan> loans = new ArrayList<>();
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(
						"SELECT * FROM loans WHERE user_id = ?");
				preparedStatement.setInt(1, userId);
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
	                loans.add(loan);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loans;
	}
	
	
	
}
