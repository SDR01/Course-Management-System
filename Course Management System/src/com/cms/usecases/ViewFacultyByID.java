package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Faculty;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.FacultyException;

public class ViewFacultyByID {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Faculty ID:");
		int fid = sc.nextInt();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		
		try {
			Faculty result = adminDao.viewFacultyByID(fid);
			
			System.out.println(result.toString());
			
		} catch (FacultyException e) {
			System.out.println(e.getMessage());
		}

	}

}
