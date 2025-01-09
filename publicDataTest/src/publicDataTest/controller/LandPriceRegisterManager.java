package publicDataTest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import publicDataTest.PublicDataAPITest;
import publicDataTest.model.LandPriceVO;


public class LandPriceRegisterManager
{
	public Scanner sc = new Scanner(System.in);
	// 과목등록(INSERT)
	public void insertManager ()
	{
		LandPriceDAO lpdao = new LandPriceDAO();
		boolean successFlag = false;
		
		// 네트워크로부터 데이터를 입력받음.
		ArrayList <LandPriceVO> landPriceVOList = PublicDataAPITest.apiDataLoad();

		try
		{
			for (LandPriceVO lpvo : landPriceVOList)
			{
				int count = lpdao.landPriceCheckNodeNoSelect(lpvo);
				if (count <= 0)
				{
					successFlag = lpdao.landPriceInsert(lpvo);
				} else
				{
					successFlag = lpdao.landPriceUpdate(lpvo);
				}
			}
			// 화면출력.
			if (successFlag == true)
			{
				System.out.println("데이터 입력/수정 완료.");
			} else
			{
				System.out.println("데이터 입력/수정 실패.");
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	// 과목목록(SELECT)
	public void selectManager ()
	{
		LandPriceDAO lpdao = new LandPriceDAO();
		
		
		// 화면으로부터 입력받는다.
		// 데이터베이스 요청.
		LandPriceVO lpvo = new LandPriceVO();
		ArrayList <LandPriceVO> landPriceVOList = lpdao.landPriceSelect();
		
		 // 화면출력.
		if (landPriceVOList.size() != 0)
		{
			printLandPriceList(landPriceVOList);
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
		LandPriceDAO lpdao = new LandPriceDAO();
		ArrayList <LandPriceVO> landPriceVOList = lpdao.landPriceSelect();

		
		 // 화면출력.
		if (landPriceVOList.size() != 0)
		{
			printLandPriceList(landPriceVOList);
		} else
		{
			System.out.println("출력 데이터 없음.");
			return;
		}
		
		// 화면으로부터 입력받는다.
		System.out.println("삭제 할 번호 >> ");
		int nodeno = Integer.parseInt((sc.nextLine()).trim());
		LandPriceVO lpvo = new LandPriceVO();
		
		// LessonVO lvo = new LessonVO();
		lpvo.setNodeno(nodeno);
		boolean successFlag = lpdao.landPriceDelete(lpvo);
		
		// 화면출력.
		if (successFlag == true)
		{
			System.out.println(nodeno + "번 삭제 완료.");
		} else
		{
			System.out.println(nodeno + "번 삭제 실패.");
		}
	}
	
	// 과목수정(UPDATE)
	public void updateManager ()
	{
		LandPriceDAO lpdao = new LandPriceDAO();
		LandPriceVO lpvo = new LandPriceVO();
		
		ArrayList<LandPriceVO> landPriceVOList;
		try
		{
			landPriceVOList = lpdao.landPriceSelect();
			// 화면출력.
			if (landPriceVOList.size() != 0)
			{
				printLandPriceList(landPriceVOList);
			} else
			{
				System.out.println("출력 데이터 없음.");
				return;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// 수정을 위한 전체출력 요청.
		// LessonVO lvo = new LessonVO();
		// ArrayList <LessonVO> lessonList = ldao.lessonSelect(lvo);
		// ArrayList <LessonVO> lessonList = ldao.lessonSelect(new LessonVO());
		
		
		// 화면으로부터 입력받는
		System.out.print("수정할 번호 선택 >> ");
		int nodeno = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 위도 입력 >> ");
		Double gpslati = Double.parseDouble((sc.nextLine()).trim());	// 양쪽의 공간을 없애는 trim().
		
		System.out.print("수정할 경도 입력 >> ");
		Double gpslong = Double.parseDouble((sc.nextLine()).trim());	// 양쪽의 공간을 없애는 trim().
		
		System.out.print("수정할 아이디 입력 >> ");
		String noneid = sc.nextLine().trim();	// 양쪽의 공간을 없애는 trim().
		
		System.out.print("수정할 정류소 입력 >> ");
		String nonenm = sc.nextLine().trim();	// 양쪽의 공간을 없애는 trim().
		
		// LessonVO lvo = new LessonVO(abbre, name);
		lpvo = new LandPriceVO(nodeno, gpslati, gpslong, noneid, nonenm);
		
		boolean successFlag;
		try
		{
			successFlag = lpdao.landPriceUpdate(lpvo);
			// 화면출력.
			if (successFlag == true)
			{
				System.out.println(nodeno + "번 과목 수정 완료.");
			} else
			{
				System.out.println(nodeno + "번 과목 수정 실패.");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void printLandPriceList(ArrayList <LandPriceVO> landPriceVOList)
	{
		for (LandPriceVO p : landPriceVOList)
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