package com.cms.dao;

import com.cms.bean.Administrator;

public interface AdminDao {
	
	public Administrator loginAdmin(String username, String password);

}
