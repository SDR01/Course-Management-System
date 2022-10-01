package com.cms.dao;

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

public interface AdminDao {
	
	public Administrator loginAdmin(String username, String password) throws AdminException;
	
	public String createCourse(Course course);
	
	public List<Course> viewCourses() throws CourseException;
	
	public Course viewCourse(int courseid) throws CourseException;
	
	public String updateCourse(Course course);
	
	public String createBatch(Batch batch);
	
	public List<Batch> viewBatches() throws BatchException;
	
	public Batch viewBatchByID(int batchid) throws BatchException;
	
	public String updateBatch(Batch batch);
	
	public String registerFaculty(Faculty faculty);
	
	public List<Faculty> viewFacutly() throws FacultyException;
	
	public Faculty viewFacultyByID(int facultyId) throws FacultyException;
	
	public String updateFaculty(Faculty faculty);
	
	public String allocateFacultyToBatch(Batch batch);
	
	public String createCoursePlan(CoursePlan coursePlan);
	
	public List<CoursePlan> viewCoursePlans() throws CoursePlanException;
	
	public CoursePlan viewCoursePlanByID(int planId) throws CoursePlanException;
	
	public String updateCoursePlan(CoursePlan coursePlan);
}
