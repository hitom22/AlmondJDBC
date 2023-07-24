package com.kh.jdbc.day03.student.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day03.student.controller.StudentController;
import com.kh.jdbc.day03.student.model.vo.Student;

public class StudentView {

	private StudentController controller;
	
	public StudentView() {
		controller = new StudentController();
	}
	
	public void studentProgram() {
		List<Student> sList = null;
		Student student = null;
		int result = 0;
		theEnd : 
		while(true) {
			int input = printMenu();
			switch(input) {
				case 1 : 
					sList = 
						controller.selectAllStudent();
					printAllStudents(sList);
					break;
				case 2 : 
					// SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01'
					String studentId = inputStdId();
					student = controller.selectOneById(studentId);
					printOneStudent(student);
					break;
				case 3 : 
					// SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '일용자'
					String studentName = inputStdName();
					sList = controller.selectAllByName(studentName);
					printAllStudents(sList);
					break;
				case 4 : 
					// INSER INTO STUDENT_TBL
					// VALUES(1,2,3,4,5,6,7,8,9,SYSDATE)
					student = inputStudent();
					result = controller.insertStudent(student);
					if(result > 0) {
						//
					} else {
						//
					}
					break;
				case 5 : 
					// SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01'
					studentId = inputStdId();
					student = controller.selectOneById(studentId);
					if(student != null) {
						//있는 것
						student = modifyStudent();
						student.setStudentId(studentId);
						result = controller.updateStudent(student);
					}else {
						//없는 것
					}
					break;
				case 6 : 
					// DELETE FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01'
					studentId = inputStdId();
					result = controller.deleteStudent(studentId);
					if(result > 0) {
						//성공
					}else {
						//실패
					}
					break;
				case 9 :
					// SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01' AND STUDENT_PWD = '1234'
					student = inputLoginInfo();
					student = controller.studentLogin(student);
					if(student != null) {
						//로그인 성공
						displaySuccess("로그인 성공");
					}else {
						//로그인 실패
						displayError("해당 정보가 존재하지 않습니다.");
					}
					break;
				case 0 : break theEnd;
			}
		}
	}

	private Student inputLoginInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 로그인 =====");
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		Student student = new Student(studentId, studentPw);
//		System.out.println(student.toString());
		return student;
	}

	private void displayError(String message) {
		System.out.println("[서비스 실패] :" + message);
	}

	private void displaySuccess(String message) {
		System.out.println("[서비스 성공] :" + message);
		
	}
	

	private Student modifyStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 정보 수정 =====");
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();  // 공백 제거, 엔터 제거
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();
		Student student = new Student(studentPw, email, phone, address, hobby);
		return student;
	}

	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생관리 프로그램 =====");
		System.out.println("1. 학생 전체 조회");
		System.out.println("2. 학생 아이디로 조회");
		System.out.println("3. 학생 이름으로 조회");
		System.out.println("4. 학생 정보 등록");
		System.out.println("5. 학생 정보 수정");
		System.out.println("6. 학생 정보 삭제");
		System.out.println("9. 학생 로그인");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		return input;	
	}

	private Student inputStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		System.out.print("이름 : ");
		String studentName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();  // 공백 제거, 엔터 제거
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();
		Student student = new Student(studentId, studentPw, studentName, gender, age, email, phone, address, hobby);	
		return student;
		
	}

	private String inputStdId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 아이디 입력 : ");
		String studentId = sc.next();
		return studentId;
	}

	private String inputStdName() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 이름 입력 : ");
		String studentName = sc.next();
		return studentName;
	}

	private void printOneStudent(Student student) {
		System.out.println("===== 학생 정보 출력(아이디로 조회) =====");
		System.out.println("===== 학생 전체 정보 출력 =====");
			System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s"
					+ ", 성별 : %s, 이메일 : %s, 전화번호 : %s, 주소 : %s"
					+ ", 취미 : %s, 가입날짜 : %s\n"
					, student.getStudentName()
					, student.getAge()
					, student.getStudentId()
					, student.getGender()
					, student.getEmail()
					, student.getPhone()
					, student.getAddress()
					, student.getHobby()
					, student.getEnrollDate());
	}

	private void printAllStudents(List<Student> sList) {
		System.out.println("===== 학생 전체 조회 =====");
		for(Student student : sList) {
			System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s"
					+ ", 성별 : %s, 이메일 : %s, 전화번호 : %s, 주소 : %s"
					+ ", 취미 : %s, 가입날짜 : %s\n"
					, student.getStudentName()
					, student.getAge()
					, student.getStudentId()
					, student.getGender()
					, student.getEmail()
					, student.getPhone()
					, student.getAddress()
					, student.getHobby()
					, student.getEnrollDate());
		}
		
	}
}
