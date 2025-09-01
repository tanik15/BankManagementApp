package com.aurionpro.test;

import com.aurionpro.dao.UserDao;
import com.aurionpro.model.UserModel;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserModel user = new UserModel("cust_tanik","tanik@123","CUSTOMER");
		UserDao dao = UserDao.getInstance();
		System.out.println(dao.loginVerification(user));

	}

}
