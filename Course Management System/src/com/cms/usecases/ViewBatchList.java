package com.cms.usecases;

import java.util.List;

import com.cms.bean.Batch;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.BatchException;

public class ViewBatchList {

	public static void main(String[] args) {
		
		AdminDao adminDao = new AdminDaoImpl();
		
		try {
			List<Batch> batchs = adminDao.viewBatches();
			
			batchs.forEach(b -> {
				System.out.println("Batch ID: " + b.getBatchId());
				System.out.println("Course ID: " + b.getCourseId());
				System.out.println("Faculty ID: " + b.getFacultyId());
				System.out.println("Batch Total Students: " + b.getNoOfStudents());
				System.out.println("Batch Start Date: " + b.getDate());
				System.out.println("Batch Duration: " + b.getDuration());
				System.out.println("***********************************************");
			});
			
		} catch (BatchException e) {
			System.out.println(e.getMessage());
		}
		
		

	}

}
