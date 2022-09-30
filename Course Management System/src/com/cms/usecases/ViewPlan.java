package com.cms.usecases;

import java.util.Scanner;
import com.cms.bean.CoursePlan;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;
import com.cms.exceptions.CoursePlanException;

public class ViewPlan {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Courseplan ID:");
		int cpid = sc.nextInt();
		
		FacultyDao facultyDao = new FacultyDaoImpl();
		
		
		try {
			CoursePlan result = facultyDao.viewCoursePlanByID(cpid);
			
			System.out.println(result.toString());
			
		} catch (CoursePlanException e) {
			System.out.println(e.getMessage());
		}

	}

}
