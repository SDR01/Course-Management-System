package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Batch;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.BatchException;

public class ViewBatchByID {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter batch ID:");
		int bid = sc.nextInt();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		
		try {
			Batch result = adminDao.viewBatchByID(bid);
			
			System.out.println(result.toString());
			
		} catch (BatchException e) {
			System.out.println(e.getMessage());
		}

	}

}
