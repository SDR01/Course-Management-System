package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cms.bean.Administrator;
import com.cms.bean.CoursePlan;
import com.cms.bean.Faculty;
import com.cms.exceptions.CoursePlanException;
import com.cms.exceptions.FacultyException;
import com.cms.utility.DBUtil;

public class FacultyDaoImpl implements FacultyDao{

	@Override
	public Faculty loginFaculty(String username, String password) throws FacultyException {
		
		Faculty faculty = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from faculty where username = ? AND password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int id = rs.getInt("facultyId");
				String name = rs.getString("facultyName");
				String address = rs.getString("facultyAddress");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String uname = rs.getString("username");
				String pass = rs.getString("password");
				
				faculty = new Faculty(id, name, address, mobile, email, uname, pass);
				
			}
			else {
				throw new FacultyException("Invalid Username or Password");
			}
			
			
		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}
		
		return faculty;
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
	public String updatePassword(String username, String password) {
		
		String message = "Not Updated";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update faculty set password = ? where username = ? ");
			
			ps.setString(1, password);
			ps.setString(2, username);
			
			int x = ps.executeUpdate();
			
			if(x > 0 ) {
				message = "Password Updated Successfully";
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

}
