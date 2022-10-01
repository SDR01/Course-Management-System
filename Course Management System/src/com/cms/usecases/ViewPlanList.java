package com.cms.usecases;

import java.util.List;

import com.cms.bean.CoursePlan;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;
import com.cms.exceptions.CoursePlanException;

public class ViewPlanList {
	
	public static void main(String[] args) {
		
		FacultyDao facultyDao = new FacultyDaoImpl();
		
		try {
			List<CoursePlan> coursePlans = facultyDao.viewCoursePlans();
			
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
