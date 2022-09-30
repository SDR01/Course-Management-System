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
