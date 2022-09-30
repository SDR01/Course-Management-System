package com.cms.bean;

public class Batch {
	
	private int batchId;
	private int courseId;
	private int facultyId;
	private int noOfStudents;
	private String date;
	private String duration;
	
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public Batch() {
		// TODO Auto-generated constructor stub
	}
	
	public Batch(int batchId, int courseId, int facultyId, int noOfStudents, String date, String duration) {
		this.batchId = batchId;
		this.courseId = courseId;
		this.facultyId = facultyId;
		this.noOfStudents = noOfStudents;
		this.date = date;
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", courseId=" + courseId + ", facultyId=" + facultyId + ", noOfStudents="
				+ noOfStudents + ", date=" + date + ", duration=" + duration + "]";
	}
	
	
	

}
