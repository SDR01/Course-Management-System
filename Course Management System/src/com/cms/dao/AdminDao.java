package com.cms.dao;

import com.cms.bean.Administrator;
import com.cms.bean.Batch;
import com.cms.bean.Course;
import com.cms.bean.CoursePlan;
import com.cms.bean.Faculty;
import com.cms.exceptions.AdminException;

public interface AdminDao {
	
	public Administrator loginAdmin(String username, String password) throws AdminException;
	
	public String createCourse(Course course);
	
	public String createBatch(Batch batch);
	
	public String registerFaculty(Faculty faculty);
	
	public String allocateFacultyToBatch(int cid, int fid);
	
	public String createCoursePlan(CoursePlan coursePlan);
}
