package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Faculty;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;

public class RegisterFaculty {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Faculty ID: ");
		int facultyid = sc.nextInt();
		
		System.out.println("Enter Faculty Name:");
		String facultyname = sc.next();
		
		System.out.println("Enter Faculty Address:");
		String facultyaddress = sc.next();
		
		System.out.println("Enter Faculty Mobile Number:");
		String facultymobile = sc.next();
		
		System.out.println("Enter Faculty Email:");
		String email = sc.next();
		
		System.out.println("Enter Faculty Username:");
		String username = sc.next();
		
		System.out.println("Enter Student password:");
		String password = sc.next();
		
		
		FacultyDao fDao = new FacultyDaoImpl();
		
		Faculty faculty = new Faculty();
		faculty.setFacultyid(facultyid);
		faculty.setFacultyname(facultyname);
		faculty.setFacultyaddres(facultyaddress);
		faculty.setMobile(facultymobile);
		faculty.setEmail(email);
		faculty.setUsername(username);
		faculty.setPassword(password);
		
		String result = fDao.registerFaculty(faculty);
		System.out.println(result);
		
	}

}
