package com.cms.usecases;

import java.util.Scanner;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;

public class UpdatePasswordByUName {
	
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Username:");
		String username = sc.next();
		
		System.out.println("Enter Password:");
		String password = sc.next();
		
		FacultyDao fDao = new FacultyDaoImpl();
		
		String faculty = fDao.updatePassword(username, password);
		
		System.out.println(faculty);

	}

}
