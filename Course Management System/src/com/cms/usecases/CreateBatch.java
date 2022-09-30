package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Batch;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class CreateBatch {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Batch ID:");
		int bid = sc.nextInt();
		
		System.out.println("Enter Course ID:");
		int cid = sc.nextInt();
		
		System.out.println("Enter Faculty ID:");
		int fid = sc.nextInt();
		
		System.out.println("Enter Number of Students:");
		int strength = sc.nextInt();
		
		System.out.println("Enter Date:");
		String date = sc.next();
		
		System.out.println("Enter Duration:");
		sc.nextLine();
		String duration = sc.nextLine();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		Batch b1 = new Batch(bid, cid, fid, strength, date, duration);
		
		String result = adminDao.createBatch(b1);
		
		System.out.println(result);
		
//		Batch b1 = new Batch(bid, cid, fid, strength, date, duration);
//		System.out.println(b1.toString());
	}

}
