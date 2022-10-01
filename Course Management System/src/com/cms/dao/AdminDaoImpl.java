package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.Administrator;
import com.cms.bean.Batch;
import com.cms.bean.Course;
import com.cms.bean.CoursePlan;
import com.cms.bean.Faculty;
import com.cms.exceptions.AdminException;
import com.cms.exceptions.BatchException;
import com.cms.exceptions.CourseException;
import com.cms.exceptions.CoursePlanException;
import com.cms.exceptions.FacultyException;
import com.cms.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public Administrator loginAdmin(String username, String password) throws AdminException {
		
		Administrator admin = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? AND password = ? ");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String uname = rs.getString("username");
				String upass = rs.getString("password");
				
				admin = new Administrator(uname, upass);
				
			}else {
				throw new AdminException("Invalid Username or Password");
			}

			
		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		return admin;
	}

	@Override
	public String createCourse(Course course) {
		String result = "Not added";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into course values(?,?,?,?)");
			
			ps.setInt(1, course.getCourseId());
			ps.setString(2, course.getCourseName());
			ps.setInt(3, course.getCourseFee());
			ps.setString(4, course.getCourseDescription());
			
			int x = ps.executeUpdate();
			
			if(x > 0) {
				result = "Created Course Successfully";
			}
			
		} catch (SQLException e) {
			result = e.getMessage();
		}
		
		return result;
	}
	
	@Override
	public Course viewCourse(int courseid) throws CourseException {
		
		Course c1 = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course where courseid = ?");
			
			ps.setInt(1, courseid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int id = rs.getInt("courseid");
				String name = rs.getString("courseName");
				int fee = rs.getInt("fee");
				String descr = rs.getString("courseDescription");
				
				c1 = new Course(courseid, name, courseid, descr);
				
			}
			else {
				throw new CourseException("No result found with this course id "+courseid);
			}
			
		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}

		return c1;
	}

	@Override
	public String updateCourse(Course course) {
		
		String message = "Not Updated";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update course set courseName = ?, fee = ?, courseDescription = ? where courseId = ?");
			
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getCourseFee());
			ps.setString(3, course.getCourseDescription());
			ps.setInt(4, course.getCourseId());
			
			int x = ps.executeUpdate();
			
			if(x > 0 ) {
				message = "Details Updated Successfully";
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String registerFaculty(Faculty faculty) {
		
		String message = "Not Inserted";
		
		try(Connection conn = DBUtil.provideConnection()){
			
		  PreparedStatement ps = conn.prepareStatement("insert into faculty values(?,?,?,?,?,?,?)");
		  
		  ps.setInt(1, faculty.getFacultyid());
		  ps.setString(2, faculty.getFacultyname());
		  ps.setString(3, faculty.getFacultyaddres());
		  ps.setString(4, faculty.getMobile());
		  ps.setString(5, faculty.getEmail());
		  ps.setString(6, faculty.getUsername());
		  ps.setString(7, faculty.getPassword());
			
		  int x = ps.executeUpdate();
		  
		  if(x > 0) {
			  message = "Record Inserted Successfully";
		  }
		  
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

	@Override
	public Faculty viewFacultyByID(int facultyId) throws FacultyException {
		
		Faculty f1 = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from Faculty where facultyid = ?");
			
			ps.setInt(1, facultyId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int fid = rs.getInt("facultyid");
				String fname = rs.getString("facultyname");
				String address = rs.getString("facultyaddress");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				f1 = new Faculty(facultyId, fname, address, mobile, email, username, password);
			}
			else {
				throw new FacultyException("No result found with this faculty id "+facultyId);
			}
			
		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}

		return f1;
	}

	@Override
	public String allocateFacultyToBatch(Batch batch) {
		
		String message = "Not Allocated";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from faculty where facultyid = ?");
			
			ps.setInt(1, batch.getFacultyId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
									
				PreparedStatement ps3 = conn.prepareStatement("update batch set facultyid = ? where batchid = ?");
						
				ps3.setInt(1, batch.getFacultyId());
				ps3.setInt(2, batch.getBatchId());
						
				int x = ps3.executeUpdate();
						
				if(x > 0) {
					message = "Allocated Faculty in Batch successfully";			
				}
				
 			}
			else {
				message = "No Faculty Found";
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}
	
	@Override
	public String createBatch(Batch batch) {
		
		String message = "Not insered";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into batch values(?,?,?,?,?,?)");
			
			ps.setInt(1, batch.getBatchId());
			ps.setInt(2, batch.getCourseId());
			ps.setInt(3, batch.getFacultyId());
			ps.setInt(4, batch.getNoOfStudents());
			ps.setString(5, batch.getDate());
			ps.setString(6, batch.getDuration());
			
			int x = ps.executeUpdate();
			
			if(x > 0) {
				message = "Created Batch Successfully";
			}
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

	@Override
	public Batch viewBatchByID(int batchid) throws BatchException {
		
		Batch b1 = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from batch where batchid = ?");
			
			ps.setInt(1, batchid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int bid = rs.getInt("batchid");
				int cid = rs.getInt("courseid");
				int fid = rs.getInt("facultyid");
				int numStundent = rs.getInt("numberOfStudent");
				String date = rs.getString("batchStartDate");
				String duration = rs.getString("duration");
				
				b1 = new Batch(bid, cid, fid, numStundent, date, duration);
				
			}
			else {
				throw new BatchException("No result found with this batch id "+batchid);
			}
			
		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}
		
		
		return b1;
	}

	@Override
	public String createCoursePlan(CoursePlan coursePlan) {
		
		String message = "Not insered";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into courseplan values(?,?,?,?,?)");
			
			ps.setInt(1, coursePlan.getPlanId());
			ps.setInt(2, coursePlan.getBatchId());
			ps.setInt(3, coursePlan.getDaynumber());
			ps.setString(4, coursePlan.getTopic());
			ps.setString(5, coursePlan.getStatus());
			
			int x = ps.executeUpdate();
			
			if(x > 0) {
				message = "Created Course Plan Successfully";
			}
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

	@Override
	public String updateBatch(Batch batch) {
		
		String message = "Not Updated";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update batch set courseId = ?, facultyId = ?, numberOfStudent = ?, batchStartDate = ?, duration = ? where batchId = ? ");
			
			ps.setInt(1, batch.getCourseId());
			ps.setInt(2, batch.getFacultyId());
			ps.setInt(3, batch.getNoOfStudents());
			ps.setString(4, batch.getDate());
			ps.setString(5, batch.getDuration());
			ps.setInt(6, batch.getBatchId());
			
			int x = ps.executeUpdate();
			
			if(x > 0 ) {
				message = "Details Updated Successfully";
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
		
	}

	@Override
	public String updateFaculty(Faculty faculty) {
		
String message = "Not Updated";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update faculty set facultyName = ?, facultyAddress = ?, mobile = ?, email = ?, username = ?, password = ? where facultyId = ? ");
			
			ps.setString(1, faculty.getFacultyname());
			ps.setString(2, faculty.getFacultyaddres());
			ps.setString(3, faculty.getMobile());
			ps.setString(4, faculty.getEmail());
			ps.setString(5, faculty.getUsername());
			ps.setString(6, faculty.getPassword());
			ps.setInt(7, faculty.getFacultyid());
			
			int x = ps.executeUpdate();
			
			if(x > 0 ) {
				message = "Details Updated Successfully";
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
		
	}

	@Override
	public CoursePlan viewCoursePlanByID(int planId) throws CoursePlanException {
		
		CoursePlan cp = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from coursePlan where planid = ?");
			
			ps.setInt(1, planId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int pid = rs.getInt("planid");
				int bid = rs.getInt("batchid");
				int day = rs.getInt("daynumber");
				String topic = rs.getString("topic");
				String status = rs.getString("status");
				
				cp = new CoursePlan(pid, bid, day, topic, status);
				
			}
			else {
				throw new CoursePlanException("No result found with this courseplan id "+planId);
			}
			
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return cp;
	}

	@Override
	public String updateCoursePlan(CoursePlan coursePlan) {
		
		String message = "Not Updated";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update courseplan set batchId = ?, daynumber = ?, topic = ?, status = ? where planId = ? ");
			
			ps.setInt(1, coursePlan.getBatchId());
			ps.setInt(2, coursePlan.getDaynumber());
			ps.setString(3, coursePlan.getTopic());
			ps.setString(4, coursePlan.getStatus());
			ps.setInt(5, coursePlan.getPlanId());
			
			int x = ps.executeUpdate();
			
			if(x > 0 ) {
				message = "Details Updated Successfully";
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
		
	}

	@Override
	public List<Faculty> viewFacutly() throws FacultyException {
		
		List<Faculty> faculties = new ArrayList<>();
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from faculty");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				int f= rs.getInt("facultyid");
				String n= rs.getString("facultyname");
				String a= rs.getString("facultyaddress");
				String m= rs.getString("mobile");
				String e= rs.getString("email");
				String u= rs.getString("username");
				String p= rs.getString("password");
				
				
			Faculty faculty = new Faculty(f, n, a, m, e, u, p);
				
			faculties.add(faculty);
			
			}
		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}
		
		if(faculties.size() == 0)
			throw new FacultyException("No Faculty found..");

		return faculties;
	}

	@Override
	public List<Batch> viewBatches() throws BatchException {
		
		List<Batch> batches = new ArrayList<>();
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from batch");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				int b= rs.getInt("batchid");
				int c= rs.getInt("courseid");
				int f= rs.getInt("facultyid");
				int n= rs.getInt("numberofstudent");
				String t= rs.getString("batchstartdate");
				String d= rs.getString("duration");
				
				
			Batch batch = new Batch(b, c, f, n, t, d);
				
			batches.add(batch);
			
			}
		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}
		
		if(batches.size() == 0)
			throw new BatchException("No Batch found..");

		return batches;
	}

	@Override
	public List<Course> viewCourses() throws CourseException {
		
		List<Course> courses = new ArrayList<>();
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from course");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				int c= rs.getInt("courseid");
				String n= rs.getString("coursename");
				int f= rs.getInt("fee");
				String d= rs.getString("coursedescription");
				
			Course course = new Course(c, n, f, d);
				
			courses.add(course);
			
			}
		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}
		
		if(courses.size() == 0)
			throw new CourseException("No Course found..");

		return courses;
	}

	@Override
	public List<CoursePlan> viewCoursePlans() throws CoursePlanException {
		
		List<CoursePlan> coursePlans = new ArrayList<>();
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from courseplan");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				int p = rs.getInt("planid");
				int b = rs.getInt("batchid");
				int n = rs.getInt("daynumber");
				String t = rs.getString("topic");
				String s = rs.getString("status");
				
			CoursePlan courseplan = new CoursePlan(p, b, n, t, s);
				
			coursePlans.add(courseplan);
			
			}
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}
		
		if(coursePlans.size() == 0)
			throw new CoursePlanException("No Course Plan found..");

		return coursePlans;
	}

	@Override
	public String dayWiseUpdate() {
		
		String message = "No Update found";
		
		try(Connection conn=DBUtil.provideConnection()){
			
			PreparedStatement ps=conn.prepareStatement(
				" select a.batchid, a.facultyid, a.numberofStudent, b.planid, b.topic, b.daynumber, b.status " +
						" from batch a INNER JOIN coursePlan b where a.batchid = b.batchid"
			);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				message = "Updated List" ;
				
				int bid = rs.getInt("batchid");
				int fid = rs.getInt("facultyid");
				int num = rs.getInt("numberOfStudent");
				int pid = rs.getInt("planid");
				String t = rs.getString("topic");
				int day = rs.getInt("daynumber");
				String s = rs.getString("status");
				 
				 
				System.out.println("Batch ID: " + bid);
				System.out.println("Faculty ID: " + fid);
				System.out.println("Number of Students: " + num);
				System.out.println("Plan ID: " + pid);
				System.out.println("Topic: " + " " + t);
				System.out.println("Day Number: " + " " + day);
				System.out.println("Status: " + s);
				System.out.println("-------------------------------------------");
				 
			 }
			 
		}
		catch(SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String generateReport() {
		
		String message = "";
		
		try(Connection conn=DBUtil.provideConnection()){
			
			PreparedStatement ps=conn.prepareStatement(
					"select a.batchid, a.numberofstudent, a.batchstartdate, a.duration, b.facultyname, c.courseid, c.coursename, d.daynumber, d.topic, d.status from batch a, faculty b, course c, courseplan d where a.facultyid = b.facultyid AND a.courseid = c.courseid AND a.batchid = d.batchid;"
				);
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int bid = rs.getInt("batchid");
				int num = rs.getInt("numberofstudent");
				String date = rs.getString("batchstartdate");
				String duration = rs.getString("duration");
				String fname = rs.getString("facultyname");
				int cid = rs.getInt("courseid");
				String cname = rs.getString("coursename");
				int day = rs.getInt("daynumber");
				String topic = rs.getString("topic");
				String status = rs.getString("status");
				
				System.out.println("Batch ID: " + bid);
				System.out.println("Number of Students: " + num);
				System.out.println("Date: " + date);
				System.out.println("Duration: " + duration);
				System.out.println("Faculty Name: " + fname);
				System.out.println("Course ID: " + cid);
				System.out.println("Course Name: " + cname);
				System.out.println("Day: " + day);
				System.out.println("Topic: " + topic);
				System.out.println("Status: " + status);
				System.out.println("-------------------------------------------");
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
	}




}
