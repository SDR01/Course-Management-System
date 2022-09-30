package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public String allocateFacultyToBatch(int cid, int fid) {
		
		String message = "Not Allocated";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from faculty where facultyid = ?");
			
			ps.setInt(1, fid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				PreparedStatement ps2 = conn.prepareStatement("select * from course where courseId = ?");
				
				ps2.setInt(2, cid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				if(rs2.next()) {
					
					PreparedStatement ps3 = conn.prepareStatement("insert into batch(courseid,facultyid) values(?,?)");
					
					ps3.setInt(1, cid);
					ps3.setInt(2, fid);
					
					int x = ps3.executeUpdate();
					
					if(x > 0) {
						message = "Allocated Faculty in Batch successfully";
								
					}
				}
 			}
			
		} catch (Exception e) {
			// TODO: handle exception
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



}
