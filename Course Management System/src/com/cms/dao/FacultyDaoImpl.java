package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cms.bean.Faculty;
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
	
	

}
