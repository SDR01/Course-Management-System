package com.cms.usecases;

import java.util.Scanner;
import com.cms.bean.CoursePlan;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.CoursePlanException;

public class ViewCoursePlanByID {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Courseplan ID:");
		int cpid = sc.nextInt();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		
		try {
			CoursePlan result = adminDao.viewCoursePlanByID(cpid);
			
			System.out.println(result.toString());
			
		} catch (CoursePlanException e) {
			System.out.println(e.getMessage());
		}

	}

}
