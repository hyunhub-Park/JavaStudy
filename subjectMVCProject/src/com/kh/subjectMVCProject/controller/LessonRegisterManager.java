package com.kh.subjectMVCProject.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.LessonVO;


public class LessonRegisterManager
{
	public Scanner sc = new Scanner(System.in);
	// 과목등록(INSERT)
	public void insertManager ()
	{
		LessonDAO ldao = new LessonDAO();
		// 화면으로부터 입력받는다.
		System.out.print("과목요약 입력(O-운영 / A-어셈블 / C-컴파일  / J-자료  / P-프로그래밍 / D-디비 / S-소프트공학)>>");
		String abbre = (sc.nextLine()).trim();	// 양쪽의 공간을 없애는 trim().
		
		System.out.print("과목명 입력(O-운영 / A-어셈블 / C-컴파일  / J-자료  / P-프로그래밍 / D-디비 / S-소프트공학)>>");
		String name = (sc.nextLine()).trim();	// 양쪽의 공간을 없애는 trim().
		
		LessonVO lvo = new LessonVO(abbre, name);
		
		boolean successFlag = ldao.lessonInsert(lvo);
		
		// 화면출력.
		if (successFlag == true)
		{
			System.out.println(name + "번 입력 완료.");
		} else
		{
			System.out.println(name + "번 입력 실패.");
		}
	}
	
	// 과목목록(SELECT)
	public void selectManager ()
	{
		LessonDAO ldao = new LessonDAO();
		
		// 화면으로부터 입력받는다.
		// 데이터베이스 요청.
		LessonVO lvo = new LessonVO();
		ArrayList <LessonVO> lessonList = ldao.lessonSelect(lvo);
		
		 // 화면출력.
		if (lessonList.size() != 0)
		{
			printLessonList(lessonList);
		} else
		{
			System.out.println("출력 데이터 없음.");
		}
		 
//		for (LessonVO p : lessonList)
//		{
//			System.out.println(p);
//		}
//		
//		printLessonList (lessonList);
		
		//printLessonList(lessonList);
	}

	// 과목삭제(DELETE)
	public void deleteManager ()
	{
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();

		
		// 삭제번호를 확인하기 위해, 전체 List출력.
		ArrayList <LessonVO> lessonList = ldao.lessonSelect(lvo);
		
		 // 화면출력.
		if (lessonList.size() != 0)
		{
			printLessonList(lessonList);
		} else
		{
			System.out.println("출력 데이터 없음.");
			return;
		}
		
		// 화면으로부터 입력받는다.
		System.out.println("삭제 할 번호 >> ");
		int no = Integer.parseInt(sc.nextLine());
		
		// LessonVO lvo = new LessonVO();
		lvo = new LessonVO();
		lvo.setNo(no);
		boolean successFlag = ldao.lessonDelete(lvo);
		
		// 화면출력.
		if (successFlag == true)
		{
			System.out.println(no + "번 삭제 완료.");
		} else
		{
			System.out.println(no + "번 삭제 실패.");
		}
	}
	
	// 과목수정(UPDATE)
	public void updateManager ()
	{
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();
		
		ArrayList <LessonVO> lessonList = ldao.lessonSelect(lvo);
		
		// 수정을 위한 전체출력 요청.
		// LessonVO lvo = new LessonVO();
		// ArrayList <LessonVO> lessonList = ldao.lessonSelect(lvo);
		// ArrayList <LessonVO> lessonList = ldao.lessonSelect(new LessonVO());
		
		 // 화면출력.
		if (lessonList.size() != 0)
		{
			printLessonList(lessonList);
		} else
		{
			System.out.println("출력 데이터 없음.");
			return;
		}
		
		// 화면으로부터 입력받는
		System.out.print("수정할 번호 선택 >> ");
		int no = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 과목 입력(O-운영 / A-어셈블 / C-컴파일  / J-자료  / P-프로그래밍 / D-디비 / S-소프트공학)>>");
		String abbre = (sc.nextLine()).trim();	// 양쪽의 공간을 없애는 trim().
		
		System.out.print("수정할 과목명 입력(O-운영 / A-어셈블 / C-컴파일  / J-자료  / P-프로그래밍 / D-디비 / S-소프트공학)>>");
		String name = (sc.nextLine()).trim();	// 양쪽의 공간을 없애는 trim().
		
		// LessonVO lvo = new LessonVO(abbre, name);
		lvo = new LessonVO(no, abbre, name);	// 변수 하나 가지고 두 번 사용하는 것!
		
		boolean successFlag = ldao.lessonUpdate(lvo);
		
		// 화면출력.
		if (successFlag == true)
		{
			System.out.println(no + "번 과목 수정 완료.");
		} else
		{
			System.out.println(no + "번 과목 수정 실패.");
		}
	}
	
	// 과목정렬(SELECT)
	public void selectSortManager ()
	{
		LessonDAO ldao = new LessonDAO();
		
		LessonVO lvo = new LessonVO();

		ArrayList <LessonVO> lessonList = ldao.lessonSelectSort(lvo);
		
		// 화면출력.
		if (lessonList.size() != 0)
		{
			printLessonList(lessonList);
		} else
		{
			System.out.println("출력할 데이터가 없습니다.");
		}
	}
	
	private void printLessonList(ArrayList<LessonVO> lessonList)
	{
		for (LessonVO p : lessonList)
		{
			System.out.println(p);
		}
	}
}




//public static Scanner sc = new Scanner(System.in); 
////전체 학생리스트를 출력요청
//public static void totalSelectManager() throws SQLException {
//	ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
//	studentList = StudentDAO.studentSelect();
//	if(studentList == null) {
//		System.out.println("데이터가 존재하지 않습니다.");
//		return;
//	}
//	printStudentList(studentList); 
//}
//
//public static void insertManager() throws SQLException {
//	// 3.statement
//	System.out.print("학생 이름을 입력하세요: ");
//	String name = sc.nextLine();
//	System.out.print("국어 점수를 입력하세요: ");
//	int kor = Integer.parseInt(sc.nextLine());
//	System.out.print("영어 점수를 입력하세요: ");
//	int eng = Integer.parseInt(sc.nextLine());
//	System.out.print("수학 점수를 입력하세요: ");
//	int mat = Integer.parseInt(sc.nextLine());
//	
//	StudentVO studentVO = new StudentVO();  
//	boolean successFlag = StudentDAO.studentInsert(studentVO);
//	
//	if(successFlag == true) {
//		System.out.println("입력처리 성공");
//	}else {
//		System.out.println("입력처리 실패");
//	}
//}
//
//public static void updateManager() throws SQLException {
//	System.out.print("수정할 학생의 번호를 입력하세요: ");
//	int no = Integer.parseInt(sc.nextLine());
//	System.out.print("새로운 이름을 입력하세요: ");
//	String name = sc.nextLine();
//	System.out.print("새로운 국어 점수를 입력하세요: ");
//	int kor = Integer.parseInt(sc.nextLine());
//	System.out.print("새로운 영어 점수를 입력하세요: ");
//	int eng = Integer.parseInt(sc.nextLine());
//	System.out.print("새로운 수학 점수를 입력하세요: ");
//	int mat = Integer.parseInt(sc.nextLine());
//	
//	StudentVO svo = new StudentVO();
//	boolean successFlag = StudentDAO.studentUpdate(svo);
//	
//	if(successFlag == true) {
//		System.out.println("입력처리 성공");
//	}else {
//		System.out.println("입력처리 실패");
//	}
//}
//
//public static void deleteManager() throws SQLException {
//	System.out.print("삭제할 학생 번호를 입력하세요: ");
//	int no = Integer.parseInt(sc.nextLine());
//	StudentVO svo = new StudentVO();
//	svo.setNo(no);
//	boolean successFlag = StudentDAO.studentDelete(svo); 
//	
//	if(successFlag == true) {
//		System.out.println("삭제처리 성공");
//	}else {
//		System.out.println("삭제처리 실패");
//	}
//}
//
//public static void sortManager() throws SQLException {
//	ArrayList<StudentVO> studentList = null;
//	studentList =StudentDAO.studentSort(); 
//	printStudentList(studentList);
//}

//전체 학생리스트를 출력진행
//public static void printStudentList(ArrayList<StudentVO> studentList) {
//	System.out.println("============================================");
//	for( StudentVO sv :  studentList) {
//		System.out.println(sv.toString());
//	}
//	System.out.println("============================================");
//}
















//package com.kh.subjectMVCProject.controller;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import com.kh.subjectMVCProject.model.StudentVO;
//
//public class LessonRegisterManager
//{
//	public static Scanner sc = new Scanner(System.in);
//	// 전체를 계산하는 프로그램.
//	// 전체 학생리스트 출력기능.
//	public static void totalSelectManager() throws SQLException
//	{
//		ArrayList <StudentVO> studentList = new ArrayList<StudentVO>();
//		studentList = StudentDAO.studentSelect();	// 데이터를 받았으니, 화면에 출력해야 함. jsp로 만들 것.
//		// printStudentList(studentList);
//		
//		if (studentList == null)
//		{
//			System.out.println("데이터가 존재하지 않습니다.");
//			return;
//		}
//		printStudentList(studentList);
//	}
//	
//	public static void insertManager() throws SQLException
//	{
//		// 3.statement
//		System.out.print("학생 이름을 입력하세요: ");
//		String name = sc.nextLine();
//		System.out.print("국어 점수를 입력하세요: ");
//		int kor = Integer.parseInt(sc.nextLine());
//		System.out.print("영어 점수를 입력하세요: ");
//		int eng = Integer.parseInt(sc.nextLine());
//		System.out.print("수학 점수를 입력하세요: ");
//		int mat = Integer.parseInt(sc.nextLine());
//		
//		StudentVO studentVO = new StudentVO();
//		boolean successFlag = StudentDAO.studentInsert(studentVO);
//		
//		if (successFlag == true)
//		{
//			System.out.println("입력처리 성공! - callback까지 다 된 것.");
//		} else
//		{
//			System.out.println("입력처리 실패.");
//		}
//	}	
//
//	public static void updateManager() throws SQLException
//	{
//		System.out.print("수정할 학생의 번호를 입력하세요: ");
//		int no = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 이름을 입력하세요: ");
//		String name = sc.nextLine();
//		System.out.print("새로운 국어 점수를 입력하세요: ");
//		int kor = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 영어 점수를 입력하세요: ");
//		int eng = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 수학 점수를 입력하세요: ");
//		int mat = Integer.parseInt(sc.nextLine());
//		
//		StudentVO svo = new StudentVO();
//		boolean successFlag = StudentDAO.stuUpdate(svo);
//		
//		if (successFlag == true)
//		{
//			System.out.println("입력처리 성공! - callback까지 다 된 것.");
//		} else
//		{
//			System.out.println("입력처리 실패.");
//		}
//	}
//
//	public static void deleteManager() throws SQLException
//	{
//		System.out.print("삭제할 학생 번호를 입력하세요: ");
//		int no = Integer.parseInt(sc.nextLine());
//		StudentVO svo = new StudentVO();
//		svo.setNo(no);
//		
//		boolean successFlag = StudentDAO.studentDelete(svo);
//		
//		if (successFlag == true)
//		{
//			System.out.println("삭제처리 성공! - callback까지 다 된 것.");
//		} else
//		{
//			System.out.println("삭제처리 실패.");
//		}
//	}
//	
//	public static void sortManager() throws SQLException
//	{
//		ArrayList <StudentVO> studentList = null;
//		studentList = StudentDAO.studentSort();
//		printStudentList(studentList);
//	}
//
//	
//	// 전체 학생리스트 출력 실행.
//	public static void printStudentList(ArrayList <StudentVO> studentList)
//	{
//		System.out.println("------------------------------------");
//		
//		for (StudentVO sv : studentList)
//		{
//			System.out.println(sv.toString());
//		}
//		
//		System.out.println("------------------------------------");
//	}
//}