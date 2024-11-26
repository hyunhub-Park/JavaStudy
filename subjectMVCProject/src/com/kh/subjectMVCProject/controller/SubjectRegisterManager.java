package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.SubjectVO;


public class SubjectRegisterManager {
	public static Scanner sc = new Scanner(System.in); 
	//전체 학생리스트를 출력요청
	public static void selectManager() throws SQLException {
		ArrayList<SubjectVO> subjectList = new ArrayList<SubjectVO>();
		SubjectDAO subDAO = new SubjectDAO(); 
		subjectList = subDAO.subjectSelect();
		if(subjectList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printSubjectList(subjectList); 
	}

	public static void insertManager() throws SQLException {
		SubjectDAO sd = new SubjectDAO();
		ArrayList<SubjectVO> subjectList = null; 
		String num; // 학과 번호
		String name; // 학과명

		System.out.println("학과 전체 리스트");
		subjectList = sd.subjectSelect();
		if(subjectList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
		} else
		{
			printSubjectList(subjectList); 
		}

		System.out.println("학과 정보 입력(학과번호:01,02,03,04,05)학과명01(IT학과),02(정보학과), 03(보안), 04(프런트),05(백엔드)");
		System.out.print("학과번호>>");
		num = sc.nextLine();
		System.out.print("학과명>>");
		name = sc.nextLine();

		SubjectVO svo = new SubjectVO(num,name);
		boolean successFlag = sd.subjectInsert(svo);

		if(successFlag == false) {
			System.out.println("입력처리 실패");
			return;
		}
		System.out.println();
		System.out.println("학과 전체 리스트");
		subjectList = sd.subjectSelect();
		if(subjectList == null) {
			System.out.println("학과정보가 없습니다.");
			return;
		}
		printSubjectList(subjectList); 
	}

	public static void updateManager() throws SQLException {
		SubjectDAO sd = new SubjectDAO();
		//전체학과리스트를 보여준다.
		ArrayList<SubjectVO> subjectList = sd.subjectSelect();
		if(subjectList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
		}
		printSubjectList(subjectList); 
		//학과번호 , 수정할 학과 이름을 입력
		System.out.print("수정할 학과의 번호를 입력하세요: ");
		String num = (sc.nextLine()).trim();
		System.out.print("수정할 학과 이름을 입력하세요: ");
		String name = sc.nextLine();
		
		SubjectVO svo = new SubjectVO(num, name);
		
		boolean successFlag = sd.subjectUpdate(svo); 
		
		if(successFlag == true) {
			System.out.println("수정처리 성공");
		}else {
			System.out.println("수정처리 실패");
		}
	}

	public static void deleteManager() throws SQLException {
		SubjectDAO sd = new SubjectDAO();
		
		System.out.print("삭제할 학과 번호를 입력하세요: ");
		String num = (sc.nextLine()).trim();
		SubjectVO svo = new SubjectVO();
		svo.setNum(num);
		boolean successFlag = sd.subjectDelete(svo);
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제처리 실패");
		}
	}

	public static void sortManager() throws SQLException {
		SubjectDAO sd = new SubjectDAO();
		ArrayList<SubjectVO> subjectList = null;
		subjectList = sd.subjectSort();
		if(subjectList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printSubjectList(subjectList); 
	}

	//전체 학생리스트를 출력진행
	public static void printSubjectList(ArrayList<SubjectVO> subjectList) {
		System.out.println("============================================");
		for( SubjectVO sv :  subjectList) {
			System.out.println(sv.toString());
		}
		System.out.println("============================================");
	}
}
















//package com.kh.subjectMVCProject.controller;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import com.kh.subjectMVCProject.model.SubjectVO;
//
//public class SubjectRegisterManager
//{
//	public static Scanner sc = new Scanner(System.in);
//	// 전체를 계산하는 프로그램.
//	// 전체 학생리스트 출력기능.
//	public static void selectManager() throws SQLException
//	{
//		ArrayList <SubjectVO> subjectList = new ArrayList<SubjectVO>();
//		SubjectDAO subDAO = new SubjectDAO();
//		
//		subjectList = subDAO.subjectSelect();	// 데이터를 받았으니, 화면에 출력해야 함. jsp로 만들 것.
//		// printStudentList(studentList);
//		
//		if (subjectList == null)
//		{
//			System.out.println("데이터가 존재하지 않습니다.");
//			return;
//		}
//		printSubjectList(subjectList);
//	}
//	
//	public static void insertManager() throws SQLException
//	{
//        SubjectDAO sd = new SubjectDAO();
//        // SubjectVO svo = new SubjectVO();	// setters로 값을 주고자 할 때,
//
//        ArrayList <SubjectVO> subjectList = null;
//        String num; // 학과 번호
//        String name; // 학과명
//
//        System.out.println("학과 전체 리스트");
//        // sd.getSubjectTotalList();
//        subjectList = sd.subjectSelect();
//		// printStudentList(studentList);
//		
//		if (subjectList == null)
//		{
//			System.out.println("데이터가 존재하지 않습니다.");
//		}
//		printSubjectList(subjectList);
//
//        System.out.println("학과 정보 입력 : 01(IT학과), 02(컴퓨터공학과), 03... IT");
//
//        System.out.print("학과번호  : ");
//        num = sc.nextLine();
//
//        System.out.print("학과명  : ");
//        name = sc.nextLine();
//        
//
//        // num, name을 줄 수 있음. no는 nextval이므로 불가.
//        SubjectVO svo = new SubjectVO(num, name);
//        boolean successFlag = sd.subjectInsert(svo);
//        
//        if (successFlag == false)
//		{
//			System.out.println("입력처리 실패.");
//			return;
//		}
//        
//        /*svo.setS_num(s_num);
//        svo.setS_name(s_name);*/
//
//
//        System.out.println();
//        System.out.println("학과 전체 리스트");
//        
//        subjectList = sd.subjectSelect();
//        if(subjectList == null)
//		{
//			System.out.println("학과 정보가 없습니다.");
//			return;
//		}
//        printSubjectList(subjectList);
//	}	
//
//	/*private static void printSubjectList(ArrayList<SubjectVO> subjectList) {
//		// TODO Auto-generated method stub
//		for(SubjectVO data : subjectList)
//		{
//			System.out.println(data);
//		}
//	}*/
//
//	public static void updateManager() throws SQLException
//	{
//		SubjectDAO sd = new SubjectDAO();
//		// 전체 학과 리스트 출력.
//
//		ArrayList <SubjectVO> subjectList = sd.subjectSelect();
//		subjectList = sd.subjectSelect();
//		if (subjectList == null)
//		{
//			System.out.println("데이터가 존재하지 않습니다.");
//		}
//		printSubjectList(subjectList);
//		
//		
//		System.out.print("수정할 학과의 번호를 입력하세요: ");
//		String num = (sc.nextLine()).trim();
//		System.out.print("수정할 학과의 이름을 입력하세요: ");
//		String name = sc.nextLine();
//		
//		SubjectVO svo = new SubjectVO(num, name);
//
//		boolean successFlag = sd.subjectUpdate(svo);
//		
//		if (successFlag == true)
//		{
//			System.out.println("수정처리 성공! - callback까지 다 된 것.");
//		} else
//		{
//			System.out.println("수정처리 실패.");
//		}
//	}
//
//	public static void deleteManager() throws SQLException
//	{
//		SubjectDAO sd = new SubjectDAO();
//		System.out.print("삭제할 학과 번호를 입력하세요: ");
//		String num = (sc.nextLine()).trim();	// 양 쪽 공백이 있을 시 방지.
//		SubjectVO svo = new SubjectVO();
//		svo.setNum(num);
//		
//		boolean successFlag = sd.subjectDelete(svo);
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
//	// 과목 리스트 정렬.
//	public static void sortManager() throws SQLException
//	{
//		SubjectDAO sd = new SubjectDAO();
//		ArrayList <SubjectVO> subjectList = null;
//		//subjectList = StudentDAO.studentSort();
//		subjectList = sd.subjectSort();
//		
//		if (subjectList == null)
//		{
//			System.out.println("데이터가 존재하지 않습니다.");
//			return;
//		}
//
//		printSubjectList(subjectList);
//	}
//
//	
//	// 전체 학생리스트 출력 실행.
//	public static void printSubjectList(ArrayList <SubjectVO> subjectList)
//	{
//		System.out.println("------------------------------------");
//		
//		for (SubjectVO sv : subjectList)
//		{
//			System.out.println(sv.toString());
//		}
//		
//		System.out.println("------------------------------------");
//	}
//}