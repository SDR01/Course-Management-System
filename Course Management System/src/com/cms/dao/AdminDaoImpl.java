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



}
