package com.kh.jdbc.day04.student.view;

import java.util.*;

import com.kh.jdbc.day04.student.controller.StudentController;
import com.kh.jdbc.day04.student.model.vo.Student;

public class StudentView {
	
	private StudentController controller;
	
	public StudentView() {
		// 초기화는 생성자에서 한다(객체를 생성하면서 초기화)
		controller = new StudentController();
	}

	public void startProgram() { 
		List<Student> sList = null;
		int result = 0;
		end :
		while(true) {
			int choice = printMenu();
			switch(choice) {
				case 1 :
					// 전체조회는 쿼리문을 생각하시오
					// SELECT * FROM STUDENT_TBL
												// SELECT COUNT(*) FROM STUDENT_TBL (카운트 갯수 세는거라면 int도 가능)
					sList = controller.selectAll();
					if(!sList.isEmpty()) {
						printAllStudents(sList);
					}else {
						displayError("데이터 조회에 실패하였습니다.");
					}
					break;
				case 2 :
					// SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01'
					String studentId = inputStdId("검색");
					Student student = controller.selectOneById(studentId);
					if(student != null) {
						printStudent(student);
					}else {
						displayError("데이터 조회에 실패하셨습니다.");
					}
					break;
				case 3 :
					// SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '일용자'
					String studentName = inputStdName();
					sList = controller.selectAllByName(studentName);
					if(!sList.isEmpty()) {
						printAllStudents(sList);
					}else {
						displayError("데이터 조회에 실패하였습니다.");
					}
					break;
				case 4 :
					student = inputStudent();
					result = controller.insertStudent(student);
					if(result > 0) {
						displaySuccess("데이터 등록에 성공하였습니다.");
					}else {
						displayError("데이터 등록을 완료하지 못했습니다.");
					}
					break;
				case 5 :
					studentId = inputStdId("수정");
					student = controller.selectOneById(studentId);
					if(student != null) {
						//수정정보 입력받기
						student = modifyStudent();
						result = controller.updateStudent(student);
						if(result > 0) {
							displaySuccess("데이터 등록에 성공하였습니다.");
						}else {
							displayError("데이터 등록을 완료하지 못했습니다.");
						}
					}else {
						displayError("데이터를 찾지 못했습니다.");
					}
					
					break;
				case 6 :
					studentId = inputStdId("삭제");
					// DELETE FROM STUDENT_TBL WHERE STUDENT_ID = 'khuser01'
					result = controller.deleteStudent(studentId);
					if(result > 0) {
						displaySuccess("데이터 삭제를 완료하였습니다.");
					}else {
						displayError("데이터 삭제를 완료하지 못했습니다.");
					}
					break;
				case 0 : break end;
			}
		}
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

	private String inputStdName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 이름으로 조회 =====");
		System.out.print("검색할 이름 입력 : ");
		String studentName = sc.next();
		return studentName;
	}

	private void printStudent(Student student) {
		System.out.println("===== 학생 정보 출력(아이디로 조회) =====");
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

	private String inputStdId(String category) {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 아이디 입력 : ");
		String studentId = sc.next();
		return studentId;
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
	
	private void displayError(String message) {
		System.out.println("[서비스 실패] :" + message);
	}

	private void displaySuccess(String message) {
		System.out.println("[서비스 성공] :" + message);
		
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
