package com.cms.usecases;

import java.util.List;

import com.cms.bean.Faculty;
import com.cms.dao.AdminDao;
import com.cms.dao.AdminDaoImpl;
import com.cms.exceptions.FacultyException;

public class ViewFacultyList {

	public static void main(String[] args) {
		
		AdminDao adminDao = new AdminDaoImpl();
		
		try {
			List<Faculty> faculties = adminDao.viewFacutly();
			
			faculties.forEach(f -> {
				System.out.println("Faculty ID: "+f.getFacultyid());
				System.out.println("Faculty Name: "+f.getFacultyname());
				System.out.println("Faculty Address: "+f.getFacultyaddres());
				System.out.println("Faculty Mobile: "+f.getMobile());
				System.out.println("Faculty Email: "+f.getEmail());
				System.out.println("Faculty User Name: "+f.getUsername());
				System.out.println("*********************************************");
			});
			
			
		} catch (FacultyException e) {
			System.out.println(e.getMessage());
		}

	}

}
