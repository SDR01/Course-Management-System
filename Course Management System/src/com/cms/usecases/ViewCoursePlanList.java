package com.cms.usecases;

import java.util.List;

import com.cms.bean.CoursePlan;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.CoursePlanException;

public class ViewCoursePlanList {

	public static void main(String[] args) {
		
		AdminDao adminDao = new AdminDaoImpl();
		
		try {
			List<CoursePlan> coursePlans = adminDao.viewCoursePlans();
			
			coursePlans.forEach(p -> {
				System.out.println("CoursePlan ID: " + p.getPlanId());
				System.out.println("Batch ID: " + p.getBatchId());
				System.out.println("CoursePlan Day: " + p.getDaynumber());
				System.out.println("CoursePlan Topic: " + p.getTopic());
				System.out.println("CoursePlan Status: " + p.getStatus());
				System.out.println("*********************************************");
			});
			
		} catch (CoursePlanException e) {
			System.out.println(e.getMessage());
		}

	}

}
