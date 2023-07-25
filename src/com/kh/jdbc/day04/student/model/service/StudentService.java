package com.kh.jdbc.day04.student.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.day04.student.common.JDBCTemplate;
import com.kh.jdbc.day04.student.common.JDBCTemplate_old;
import com.kh.jdbc.day04.student.model.dao.StudentDAO;
import com.kh.jdbc.day04.student.model.vo.Student;

public class StudentService {

	private StudentDAO sDao;
	private JDBCTemplate_old jdbcTemplate;  // 객체 생성 필수
	
	public StudentService() {
		sDao = new StudentDAO();
//		jdbcTemplate = new JDBCTemplate();  // 생성자가 private이기 때문에 사용 못함!
		jdbcTemplate = JDBCTemplate_old.getInstance();
	}
	
	public List<Student> selectAll() {
		Connection conn 
			= jdbcTemplate.createConnection();
		List<Student> sList
			 = sDao.selectAll(conn);
		jdbcTemplate.close();
		return sList;
	}

	public Student selectOneById(String studentId) {
		Connection conn 
			= jdbcTemplate.createConnection();
		Student student 
			= sDao.selectOneById(conn, studentId);
		jdbcTemplate.close();
		return student;
	}

	public List<Student> selectAllByName(String studentName) {
		Connection conn 
		= jdbcTemplate.createConnection();
	List<Student> sList
		= sDao.selectAllByName(conn, studentName);
		jdbcTemplate.close();
		return sList;
	}

	public int insertStudent(Student student) {
		Connection conn = jdbcTemplate.createConnection();
		int result = sDao.insertStudent(conn, student);
		result += sDao.updateStudent(conn, student);
		
		if(result > 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		jdbcTemplate.close();
		return result;
	}

	public int updateStudent(Student student) {
		Connection conn 
			= jdbcTemplate.createConnection();
		int result 
		= sDao.updateStudent(conn, student);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		jdbcTemplate.close();
		return result;
	}

	public int deleteStudent(String studentId) {
		Connection conn 
			= jdbcTemplate.createConnection();
		int result 
			= sDao.deleteStudent(conn, studentId);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		jdbcTemplate.close();
		return result;
	}
	
	
}
