package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Faculty;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;
import com.cms.exceptions.FacultyException;

public class LoginFaculty {

	public static boolean main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Username:");
		String username = sc.next();
		
		System.out.println("Enter Password:");
		String password = sc.next();
		
		FacultyDao fDao = new FacultyDaoImpl();
		
		boolean flag = false;
		
		try {
			Faculty faculty = fDao.loginFaculty(username, password);
			
			System.out.println("Welcome "+ faculty.getFacultyname());
			
			if( faculty.getUsername().equals(username) && faculty.getPassword().equals(password) ) {
				flag = true;
			}
			
		} catch (FacultyException e) {
			System.out.println(e.getMessage());
		}
		
		return flag;
	}

}
