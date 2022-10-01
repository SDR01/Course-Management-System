package com.cms.main;

import java.util.Scanner;

import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;
import com.cms.usecases.AdminLogin;
import com.cms.usecases.AllocateFacultyToBatch;
import com.cms.usecases.CreateBatch;
import com.cms.usecases.CreateCourse;
import com.cms.usecases.CreateCoursePlan;
import com.cms.usecases.GenerateReport;
import com.cms.usecases.GetDayWiseUpdateOfBatch;
import com.cms.usecases.LoginFaculty;
import com.cms.usecases.RegisterFaculty;
import com.cms.usecases.UpdateBatchByID;
import com.cms.usecases.UpdateCourseByID;
import com.cms.usecases.UpdateCoursePlanByID;
import com.cms.usecases.UpdateFacultyByID;
import com.cms.usecases.UpdatePasswordByUName;
import com.cms.usecases.ViewBatchByID;
import com.cms.usecases.ViewBatchList;
import com.cms.usecases.ViewCourseByID;
import com.cms.usecases.ViewCourseList;
import com.cms.usecases.ViewCoursePlanByID;
import com.cms.usecases.ViewCoursePlanList;
import com.cms.usecases.ViewFacultyByID;
import com.cms.usecases.ViewFacultyList;
import com.cms.usecases.ViewPlan;
import com.cms.usecases.ViewPlanList;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		AdminDao adminDao = new AdminDaoImpl();
		
		FacultyDao facultyDao = new FacultyDaoImpl();
		
		System.out.println("-------- Welcome to Course Management System ---------");
		
		while(true){
			System.out.println(" ");
			System.out.println("1. Login as Admin ");
			System.out.println("2. Login as Faculty");
			System.out.println("3. Exit Application");
			System.out.println(" ");
			System.out.println("Enter a number 1 or 2 or 3");
			
			int login = 0;
			
			try {
				login = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Enter an integer value");
				break;
			}
			
			
			if(login == 1) {
				
				AdminLogin adminLogin = new AdminLogin();
				
				Boolean adminIn = false;
				
				if( adminLogin.main(args) ) {
					adminIn = true;
				}
				else {
					adminIn = false;
				}
				
				while(adminIn) {
					System.out.println("");
					System.out.println("1. Create Course");
					System.out.println("2. Update Course");
					System.out.println("3. View Course List");
					System.out.println("4. View Course By ID");
					System.out.println("5. Create Batch");
					System.out.println("6. Update Batch");
					System.out.println("7. View Batch List");
					System.out.println("8. View Batch By ID");
					System.out.println("9. Create Faculty");
					System.out.println("10. Update Faculty");
					System.out.println("11. View Faculty List");
					System.out.println("12. View Faculty By ID");
					System.out.println("13. Allocate Faculty to Batch");
					System.out.println("14. Create CoursePlan");
					System.out.println("15. Update CoursePlan");
					System.out.println("16. View CoursePlan List");
					System.out.println("17. View CoursePlan By ID");
					System.out.println("18. View Daywise update of every Batch");
					System.out.println("19. Generate Report of every Batch");
					System.out.println("20. Logout");
					System.out.println("");
					System.out.println("Enter your Choice: ");
					int choice = sc.nextInt();
					
					switch (choice) {
						case 1:
						{
							CreateCourse createCourse = new CreateCourse();
							createCourse.main(args);
							break;
						}
						case 2:
						{
							UpdateCourseByID updateCourseByID = new UpdateCourseByID();
							updateCourseByID.main(args);
							break;
						}
						case 3:
						{
							ViewCourseList viewCourseList = new ViewCourseList();
							viewCourseList.main(args);
							break;
						}
						case 4:
						{
							ViewCourseByID viewCourseByID = new ViewCourseByID();
							viewCourseByID.main(args);
							break;
						}
						case 5:
						{
							CreateBatch createBatch = new CreateBatch();
							createBatch.main(args);
							break;
						}
						case 6:
						{
							UpdateBatchByID updateBatchByID = new UpdateBatchByID();
							updateBatchByID.main(args);
							break;
						}
						case 7:
						{
							ViewBatchList viewBatchList = new ViewBatchList();
							viewBatchList.main(args);
							break;
						}
						case 8:
						{
							ViewBatchByID viewBatchByID = new ViewBatchByID();
							viewBatchByID.main(args);
							break;
						}
						case 9:
						{
							RegisterFaculty registerFaculty = new RegisterFaculty();
							registerFaculty.main(args);
							break;
						}
						case 10:
						{
							UpdateFacultyByID updateFacultyByID = new UpdateFacultyByID();
							updateFacultyByID.main(args);
							break;
						}
						case 11:
						{
							ViewFacultyList viewFacultyList = new ViewFacultyList();
							viewFacultyList.main(args);
							break;
						}
						case 12:
						{
							ViewFacultyByID viewFacultyByID = new ViewFacultyByID();
							viewFacultyByID.main(args);
							break;
						}
						case 13:
						{
							AllocateFacultyToBatch allocateFacultyToBatch = new AllocateFacultyToBatch();
							allocateFacultyToBatch.main(args);
							break;
						}
						case 14:
						{
							CreateCoursePlan createCoursePlan = new CreateCoursePlan();
							createCoursePlan.main(args);
							break;
						}
						case 15:
						{
							UpdateCoursePlanByID updateCoursePlanByID = new UpdateCoursePlanByID();
							updateCoursePlanByID.main(args);
							break;
						}
						case 16:
						{
							ViewCoursePlanList viewCoursePlanList = new ViewCoursePlanList();
							viewCoursePlanList.main(args);
							break;
						}
						case 17:
						{
							ViewCoursePlanByID viewCoursePlanByID = new ViewCoursePlanByID();
							viewCoursePlanByID.main(args);
							break;
						}
						case 18:
						{
							GetDayWiseUpdateOfBatch getDayWiseUpdateOfBatch = new GetDayWiseUpdateOfBatch();
							getDayWiseUpdateOfBatch.main(args);
							break;
						}
						case 19:
						{
							GenerateReport generateReport = new GenerateReport();
							generateReport.main(args);
							break;
						}
						case 20:
						{
							System.out.println("Admin Successfully Logged Out");
							adminIn = false;
							break;
						}
						default:
						{
							System.out.println("Admin Successfully Logged Out");
							adminIn = false;
							break;
						}
					}
				}
				
			}
			if(login == 2) {
				
				LoginFaculty loginFaculty = new LoginFaculty();
				
				Boolean facultyIn = false;
				
				if( loginFaculty.main(args) ) {
					facultyIn = true;
				}
				else {
					facultyIn = false;
				}
				
				while(facultyIn) {
					
					System.out.println(" ");
					System.out.println("1. View Course Plan by ID");
					System.out.println("2. View Course Plan List");
					System.out.println("3. Fill Up Day wise Planner");
					System.out.println("4. Upadate Password");
					System.out.println("");
					System.out.println("Enter your Choice: ");
					int choice = sc.nextInt();
					
					switch (choice) {
						case 1:
						{
							ViewPlan viewPlan = new ViewPlan();
							viewPlan.main(args);
							break;
						}
						case 2:
						{
							ViewPlanList viewPlanList = new ViewPlanList();
							viewPlanList.main(args);
							break;
						}
						case 3:
						{
							ViewPlan viewPlan = new ViewPlan();
							viewPlan.main(args);
							break;
						}
						case 4:
						{
							UpdatePasswordByUName updatePasswordByUName = new UpdatePasswordByUName();
							updatePasswordByUName.main(args);
							break;
						}
	
						default:
						{
							System.out.println("Faculty Successfully Logged Out");
							facultyIn = false;
							break;
						}
					}
				}
				
				
			}
			if(login == 3) {
				System.out.println("Thank you for using our application");
				break;
			}
			if(login != 1 && login != 2 && login != 3) {
				System.out.println("Wrong Choice, Please select 1 or 2 or 3 only");
			}
		}
	}

}
