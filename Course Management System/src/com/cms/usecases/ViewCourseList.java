package com.cms.usecases;

import java.util.List;

import com.cms.bean.Course;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.CourseException;

public class ViewCourseList {
	
	public static void main(String[] args) {
		
		AdminDao adminDao = new AdminDaoImpl();
		
		try {
			List<Course> courses = adminDao.viewCourses();
			
			courses.forEach(c -> {
				System.out.println("Course ID: " + c.getCourseId());
				System.out.println("Course Name: " + c.getCourseName());
				System.out.println("Course Fee: " + c.getCourseFee());
				System.out.println("Course Description: " + c.getCourseDescription());
				System.out.println("**************************************************");
			});
			
		} catch (CourseException e) {
			System.out.println(e.getMessage());
		}

	}

}
