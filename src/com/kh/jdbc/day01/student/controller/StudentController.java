package com.kh.jdbc.day01.student.controller;

import java.util.List;

import com.kh.jdbc.day01.student.model.dao.StudentDAO;
import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentController {

	private StudentDAO studentDao;
	
	public StudentController() {
		studentDao = new StudentDAO();
	}

	public List<Student> printStudentList() {
		List<Student> sList = studentDao.selectAll(); 
		return sList;
	}

}
