package com.cms.usecases;

import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class GenerateReport {

	public static void main(String[] args) {
		
		AdminDao adminDao = new AdminDaoImpl();
		
		String result = adminDao.generateReport();
		
		System.out.println(result);
	}

}
