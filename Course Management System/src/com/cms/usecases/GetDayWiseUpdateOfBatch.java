package com.cms.usecases;

import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class GetDayWiseUpdateOfBatch {

	public static void main(String[] args) {
		
		AdminDao adminDao = new AdminDaoImpl();
		
		String result = adminDao.dayWiseUpdate();
		
		System.out.println(result);
	}

}
