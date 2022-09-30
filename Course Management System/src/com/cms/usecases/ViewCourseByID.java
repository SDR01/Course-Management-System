package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Course;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.CourseException;

public class ViewCourseByID {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter course ID:");
		int cid = sc.nextInt();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		
		try {
			Course result = adminDao.viewCourse(cid);
			
			System.out.println(result.toString());
			
		} catch (CourseException e) {
			System.out.println(e.getMessage());
		}

	}
	
}
