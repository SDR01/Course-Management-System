package com.cms.usecases;

import java.util.Scanner;

import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class AllocateFacultyToBatch {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course ID:");
		int cid = sc.nextInt();
		System.out.println("Enter Faculty ID:");
		int fid = sc.nextInt();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		String result = adminDao.allocateFacultyToBatch(cid, fid);
		
		System.out.println(result);
		
	}

}
