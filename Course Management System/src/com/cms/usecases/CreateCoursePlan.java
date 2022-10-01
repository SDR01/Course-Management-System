package com.cms.usecases;

import java.util.Scanner;

import com.cms.bean.CoursePlan;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;

public class CreateCoursePlan {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println(" ");
			System.out.println("1. Create Course Plan");
			System.out.println("2. Exit Course Plan");
			System.out.println(" ");
			System.out.println("Enter a number 1 or 2");
			
			int option = 0;
			
			try {
				option = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Enter an integer value");
				break;
			}
			
			if(option == 1) {
				
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
			if(option == 2) {
				System.out.println("Exit Successfull");
				break;
			}
			if(option != 1 && option != 2) {
				System.out.println("Wrong Choice, Please select 1 or 2 only");
			}
		};
		
		
	}

}
