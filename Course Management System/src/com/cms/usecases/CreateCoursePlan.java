package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.CoursePlan;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class CreateCoursePlan {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Plan ID: ");
		int pid = sc.nextInt();
		
		System.out.println("Enter Batch ID: ");
		int bid = sc.nextInt();
		
		System.out.println("Enter Day Number: ");
		int dayNum = sc.nextInt();
		
		System.out.println("Enter Topic: ");
		sc.nextLine();
		String topic = sc.nextLine();
		
		System.out.println("Enter Status: Completed or Pending ");
		String status = sc.next();
		
		AdminDao adminDao = new AdminDaoImpl();
		
		CoursePlan cp = new CoursePlan(pid, bid, dayNum, topic, status);
		
		String result = adminDao.createCoursePlan(cp);
		
		System.out.println(result);
		
	}

}
