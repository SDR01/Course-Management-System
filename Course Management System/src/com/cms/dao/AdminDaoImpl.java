package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cms.bean.Administrator;
import com.cms.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public Administrator loginAdmin(String username, String password) {
		
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
				
			}

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return admin;
	}
	

}
