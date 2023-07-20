package com.kh.jdbc.day01.student.view;

import java.util.*;

import com.kh.jdbc.day01.student.controller.StudentController;
import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentView {

	private StudentController controller;
	
	public StudentView() {
		controller = new StudentController();
	}
	
	public void startProgram() {
		while(true) {
			int choice = printMenu();
			switch(choice) {
				case 1 : 
					// SELECT * FROM STUDENT_TBL
					List<Student> sList = 
						controller.printStudentList();
					showAllStudents(sList);
					break;
				case 2 : 
					break;
				case 3 : 
					break;
				case 4 : 
					break;
				case 5 : 
					break;
				case 6 : 
					break;
				case 0 : 
					break;
			}
		}
	}
	
	private void showAllStudents(List<Student> sList) {
		System.out.println("===== 학생 전체 정보 출력 =====");
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

	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생관리 프로그램 =====");
		System.out.println("1. 학생 전체 조회");
		System.out.println("2. 학생 아이디로 조회");
		System.out.println("3. 학생 이름으로 조회");
		System.out.println("4. 학생 정보 등록");
		System.out.println("5. 학생 정보 수정");
		System.out.println("6. 학생 정보 삭제");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		return input;
	}
}




