package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cms.bean.Administrator;
import com.cms.bean.Faculty;
import com.cms.exceptions.FacultyException;
import com.cms.utility.DBUtil;

public class FacultyDaoImpl implements FacultyDao{

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

}
