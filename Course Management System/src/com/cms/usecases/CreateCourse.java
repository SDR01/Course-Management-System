package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.Course;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class CreateCourse {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course ID: ");
		int cid = sc.nextInt();
		System.out.println("Enter Course Name: ");
		String cname = sc.next();
		System.out.println("Enter Course Fee: ");
		int cfee = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Course Description: ");
		String cdesc = sc.nextLine();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		Course c1 = new Course(cid, cname, cfee, cdesc);
		
		String a1 = adminDao.createCourse(c1);
		
		System.out.println(a1);

	}

}
