package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Administrator;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.AdminException;



public class AdminLogin {
	
	public static boolean main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Username: ");
		String username = sc.next();
		
		System.out.println("Enter Password: ");
		String password = sc.next();
		
		AdminDao aDao = new AdminDaoImpl();
		
		boolean flag = false;
	
		try {
			Administrator admin = aDao.loginAdmin(username, password);
			
			System.out.println("Welcome "+ username);
			
			if( admin.getUsername().equals(username) && admin.getPassword().equals(password) ){
				flag = true;
			}
			
			
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
		return flag;
			
	}

}
