package com.kh.jdbc.day03.student.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.student.model.vo.Student;

public class StudentDAO {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";


	
	
	public List<Student> selectAll() {
		// 쿼리문을 보고 실행할 메소드와 리턴타입을 결정해야한다.
		String query = "SELECT * FROM STUDENT_TBL";
		List<Student> sList = new ArrayList<Student>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				Student student = rsetToStudent(rset);
				sList.add(student);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
		}
	}

	private Student rsetToStudent(ResultSet rset) throws SQLException {
		Student student = new Student();
		student.setStudentId(rset.getString(1));
		student.setStudentPwd(rset.getString("STUDENT_Pwd"));
		student.setStudentName(rset.getString("STUDENT_NAME"));
		student.setAge(rset.getInt("AGE"));
		student.setEmail(rset.getString("EMAIL"));
		student.setPhone(rset.getString("PHONE"));
		// 문자는 문자열에 문자로 잘라서 사용, charAt() 메소드 사용
		student.setGender(rset.getString("GENDER").charAt(0));
		student.setAddress(rset.getString("ADDRESS"));
		student.setHobby(rset.getString("HOBBY"));
		student.setEnrollDate(rset.getDate("ENROLL_DATE"));
		return student;
	}

	public Student selectOneById(String studentId) {
		// 1. 위치홀더 셋팅
		// 2. PreparedStatement 객체 생성 with query
		// 3. 입력값 셋팅
		// 4. 쿼리문 실행 및 결과 받기(feat. method())
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = ?";
		Student student = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  studentId);
			rset = pstmt.executeQuery();
//			Statement stmt = conn.createStatement();
//			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) {
				student = rsetToStudent(rset);
			}
			rset.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	public List<Student> selectAllByName(String studentName) {
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = ?";
		List<Student> sList = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentName);
			rset = pstmt.executeQuery();
//			Statement stmt = conn.createStatement();
//			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) {
				Student student = rsetToStudent(rset);
				sList.add(student);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sList;
	}
	
	public int updateStudent(Student student) {
		String query = "UPDATE STUDENT_TBL SET STUDENT_PWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE STUDENT_ID = ?";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  student.getStudentPwd());
			pstmt.setString(2,  student.getEmail());
			pstmt.setString(3,  student.getPhone());
			pstmt.setString(4,  student.getAddress());
			pstmt.setString(5,  student.getHobby());
			pstmt.setString(6,  student.getStudentId());
			result = pstmt.executeUpdate();
//			Statement stmt = conn.createStatement();
//			result = stmt.executeUpdate(query);
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int deleteStudent(String studentId) {
		String query = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID = ?";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  studentId);
			result = pstmt.executeUpdate();
//			Statement stmt = conn.createStatement();
//			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertStudent(Student student) {
		String query = "INSERT INTO STUDENT_TBL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPwd());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, String.valueOf(student.getGender()));
			pstmt.setInt(5, student.getAge());
			pstmt.setString(6, student.getEmail());
			pstmt.setString(7, student.getPhone());
			pstmt.setString(8, student.getAddress());
			pstmt.setString(9, student.getHobby());
			result = pstmt.executeUpdate();   // **********쿼리문 실행 빼먹지 말기! *********
//			Statement stmt = conn.createStatement();
//			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Student selectLoginInfo(Student student) {
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = ? AND STUDENT_PWD = ?";
		Student result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentId());  // 시작은 1로 하고
			pstmt.setString(2, student.getStudentPwd());	// 마지막 수는 물음표의 갯수와 일치해야 한다.(물음표 = 위치홀더)
			rset = pstmt.executeQuery();
			// PrepareStatement는 실행할때 반드시 쿼리문이 필요하다!
//			Statement stmt = conn.createStatement();
//			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rsetToStudent(rset);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
