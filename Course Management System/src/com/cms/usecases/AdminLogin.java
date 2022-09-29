package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Administrator;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;



public class AdminLogin {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Username: ");
		String username = sc.next();
		
		System.out.println("Enter Password: ");
		String password = sc.next();
		
		AdminDao aDao = new AdminDaoImpl();
	
		Administrator admin = aDao.loginAdmin(username, password);
			
		System.out.println("Welcome");
			
	}

}
