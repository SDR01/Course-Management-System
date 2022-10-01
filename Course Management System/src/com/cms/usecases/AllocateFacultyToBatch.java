package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Batch;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class AllocateFacultyToBatch {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Faculty ID:");
		int fid = sc.nextInt();
		System.out.println("Enter Batch ID:");
		int bid = sc.nextInt();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		Batch batch = new Batch();
		batch.setBatchId(bid);
		batch.setFacultyId(fid);
		batch.getCourseId();
		batch.getNoOfStudents();
		batch.getDate();
		batch.getDuration();
		
		String result = adminDao.allocateFacultyToBatch(batch);
		
		System.out.println(result);
		
	}

}
